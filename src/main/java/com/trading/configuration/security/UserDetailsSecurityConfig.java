package com.trading.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class UserDetailsSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService mongoUserDetails() {
        return new UserDetailsService();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        org.springframework.security.core.userdetails.UserDetailsService userDetailsService = mongoUserDetails();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/resources/**").permitAll()
                //.antMatchers("/home").access("hasRole('ROLE_USER') and hasRole('DBA')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureUrl("/login?login_error=t")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        /*
        * db.users.insert({"name":"John","surname":"doe","email":"john@doe.com","password":"cleartextpass","authorities":["ROLE_USER","admin"]})
        * */

    }
}