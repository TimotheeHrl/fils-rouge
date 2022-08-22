/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filRouge.filRouge.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 *
 * @author maxla
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()// Authentification en mémoire 
                .passwordEncoder(NoOpPasswordEncoder.getInstance())// on ne veut pas que le mot de passe soit crypté
                .withUser("admin").password("admin") // on créer l'utilisateur admin ==> Authentification
                .roles("USER", "ADMIN"); // on lui assigne les roles USER et ADMIN ==> Authorization
//                .and()
//                .withUser("maxime").password("admin")
//                .roles("MANAGER");            
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**") // pour les requets qui matchent cette URI 
                .permitAll() // Autoriser toutes les reqêtes ==> pas besoin d'être connecté
//                .antMatchers("/", "/todo**")
//                .hasAnyRole("USER")// pour toutes les autres requetes on demande une authentification
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin();
                ;
                // on active le formulaire de login
        //.successForwardUrl("/"); redirige vers la page en paramètre
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

}
