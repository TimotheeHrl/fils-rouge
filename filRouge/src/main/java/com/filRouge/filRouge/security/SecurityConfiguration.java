/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.security;

import com.filRouge.filRouge.filter.CustomAuthentifiactionFilter;
import com.filRouge.filRouge.filter.CustomAuthorizationFilter;
import com.filRouge.filRouge.filter.JWTAuthenticationFilter;
import com.filRouge.filRouge.filter.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author maxla
 */
@Configuration @EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private  final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
              .userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

    }

    protected void configure(HttpSecurity http) throws Exception {
        // disabled because we are using a jwt
        http.csrf().disable();
        //We can control exactly when our session gets created and how Spring Security will interact with it:
            //always – A session will always be created if one doesn't already exist.
            //ifRequired – A session will be created only if required (default).
            //never – The framework will never create a session itself, but it will use one if it already exists.
            //stateless – No session will be created or used by Spring Security.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/api/**").hasAnyAuthority("ADMIN", "USER");
        http.authorizeRequests().antMatchers("/orders").hasAuthority("USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        //asking to spring that read JWTAuthorizationFilter before the usernamePasswordAuthenticationFilter, the first
        // give the username and the info from the token
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
