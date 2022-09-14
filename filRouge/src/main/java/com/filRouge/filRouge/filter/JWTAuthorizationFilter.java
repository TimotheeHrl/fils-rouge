package com.filRouge.filRouge.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.filRouge.filRouge.security.SecParams;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    //check the token
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //get the token
        String jwt = request.getHeader("Authorization");

        if(jwt == null || !jwt.startsWith(SecParams.PREFIX)){
            // if the token is null or don't start with Bearer pass to the next token
            filterChain.doFilter(request, response);
            return;
        }
        // check the token with the algorithm and the secret key
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParams.SECRET)).build();
        // delete the Bearer prefix
        jwt = jwt.substring(SecParams.PREFIX.length());
        // Now we are decoding the token
        DecodedJWT decodedJWT = verifier.verify(jwt);
        //get the username
        String username = decodedJWT.getSubject();
        //get the authorities
        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
        //Give the authorities to Spring
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // Cast in SimpleGrantedAuthority
        for(String r : roles){
            authorities.add(new SimpleGrantedAuthority(r));
        }
        // Update the context of springSecurity
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username,null, authorities);
        SecurityContextHolder.getContext().setAuthentication(user);
        //pass to the next filter
        filterChain.doFilter(request, response);

    }
}
