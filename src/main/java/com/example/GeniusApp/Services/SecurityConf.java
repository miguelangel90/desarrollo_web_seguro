package com.example.GeniusApp.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

// Public pages

        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/songs").permitAll();
        http.authorizeRequests().antMatchers("/Song/{num}").permitAll();
        http.authorizeRequests().antMatchers("/new/user").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();

        // Css templates
        http.authorizeRequests().antMatchers("/css/delete.css").permitAll();
        http.authorizeRequests().antMatchers("/css/ErrorStyle.css").permitAll();
        http.authorizeRequests().antMatchers("/css/login_register.css").permitAll();
        http.authorizeRequests().antMatchers("/css/new_song.css").permitAll();
        http.authorizeRequests().antMatchers("/css/portalstyle.css").permitAll();
        http.authorizeRequests().antMatchers("/css/songstyle.css").permitAll();
        http.authorizeRequests().antMatchers("/css/StartStyle.css").permitAll();
        http.authorizeRequests().antMatchers("/css/success.css").permitAll();

        // Images
        http.authorizeRequests().antMatchers("/images/anonimo.png").permitAll();
        http.authorizeRequests().antMatchers("/images/backgorund.jpeg").permitAll();
        http.authorizeRequests().antMatchers("/images/button.png").permitAll();
        http.authorizeRequests().antMatchers("/images/logo.png").permitAll();
        http.authorizeRequests().antMatchers("/images/register.png").permitAll();
        http.authorizeRequests().antMatchers("/images/sesion.png").permitAll();
        http.authorizeRequests().antMatchers("/images/startbackground.jpg").permitAll();

// Private pages (all other pages)
        http.authorizeRequests().antMatchers("/new/song").hasAnyRole("USER");
        http.authorizeRequests().anyRequest().authenticated();

// Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/login_success");
        http.formLogin().failureUrl("/loginerror");

// Disable CSRF at the moment
        http.csrf().disable();
      }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("user")
                .password(encoder.encode("pass")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin")
                .password(encoder.encode("adminpass")).roles("USER", "ADMIN");*/
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
