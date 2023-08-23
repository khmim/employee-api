package com.employee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and().httpBasic();
    }
}
