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
    /*@Autowired
    RepositoryUserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder2());
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
// Private endpoints
        http.antMatcher("/api/**");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/song").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/songsDeleteId/{id}").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/songsUpdateLyrics/{id}").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/songs/{id}").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/songs/{sid}/{cid}").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/userDelete/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/allusers").hasAnyRole("ADMIN");

// Other endpoints are public
        http.authorizeRequests().anyRequest().permitAll();
// Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf().disable();
// Enable Basic Authentication
        http.httpBasic();
// Disable Form login Authentication
        http.formLogin().disable();
// Avoid creating session (because every request has credentials)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

/*

    @Configuration
    @Order(1)
    public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/api/**");
            // Private endpoints
            http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/**").hasRole("admin");
            http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/**").hasRole("admin");
            http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/**").hasRole("admin");

            http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/get_tokens").hasRole("admin");
            http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin/get_token/**").hasRole("admin");
            http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/get_token/**").hasAnyRole("user","USER");
            http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/get_users").hasRole("admin");
            http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/admin/get_user").hasRole("admin");
            http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/get_user").hasAnyRole("user","USER");

            // Other endpoints are public
            http.authorizeRequests().anyRequest().permitAll();

            // Disable CSRF protection (it is difficult to implement in REST APIs)
            http.csrf().disable();

            // Enable Basic Authentication
            http.httpBasic();

            // Disable Form login Authentication
            http.formLogin().disable();

            // Avoid creating session (because every request has credentials)
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        }
        */
    }




