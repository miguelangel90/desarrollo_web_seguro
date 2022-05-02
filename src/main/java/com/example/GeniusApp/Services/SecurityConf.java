package com.example.GeniusApp.Services;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
// Public pages

        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/songs").permitAll();
        http.authorizeRequests().antMatchers("/Song/{num}").permitAll();
        http.authorizeRequests().antMatchers("/new/user").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();

// Private pages (all other pages)
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
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodedPassword = encoder.encode("pass");
        auth.inMemoryAuthentication().withUser("user").password(encodedPassword).roles("USER");
    }
}
