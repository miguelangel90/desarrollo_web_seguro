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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        http.authorizeRequests().antMatchers("/songs/search").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/allsongs").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/songs/{id}/allcomments").permitAll();
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
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/song").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/songsDeleteId/{id}").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/songsUpdateLyrics/{id}").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/songs/{id}").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/songs/{sid}/{cid}").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/userDelete/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/allusers").hasAnyRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();

// Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/login_success");
        http.formLogin().failureUrl("/loginerror");

        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

// Disable CSRF at the moment
        //http.csrf().disable();

        //api rest

        http.httpBasic();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
