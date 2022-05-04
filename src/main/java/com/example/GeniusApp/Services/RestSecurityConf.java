package com.example.GeniusApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@Order(1)
public class RestSecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder/*()*/);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Private endpoints
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/song").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/songsDeleteId/{id}").hasAnyRole("USER","ADMIN");
        // Other endpoints are public
        http.authorizeRequests().anyRequest().permitAll();
        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf().disable();
        // Enable Basic Authentication
        http.httpBasic();
        // Disable Form login Authentication
        //http.formLogin().disable();
        //http.logout().disable();
        // Avoid creating session (because every request has credentials)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
