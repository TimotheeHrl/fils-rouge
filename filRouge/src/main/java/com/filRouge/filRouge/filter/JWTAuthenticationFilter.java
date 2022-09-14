package com.filRouge.filRouge.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filRouge.filRouge.model.AppUser;
import com.filRouge.filRouge.security.SecParams;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //methods automatically call by spring when we have an attemptAuthentication
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AppUser user = null;
        try {
            // unserialize json format to a user from the request
            user = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // we retrieve the user from the Authentication
        org.springframework.security.core.userdetails.User springUser = (User) authResult.getPrincipal();
        // We are getting the roles from the springUser
        List<String> roles = new ArrayList<>();
        springUser.getAuthorities().forEach( aut -> {
            roles.add(aut.getAuthority());
        });

        // We can start to create the JWT

        String jwt = JWT.create()
                .withSubject(springUser.getUsername())
                //we give the roles collections in array format
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis()+SecParams.EXP_TIME))
                .sign(Algorithm.HMAC256(SecParams.SECRET));

        response.addHeader("Authorization",  jwt);

    }
}
