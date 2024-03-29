package com.sda.onlineLibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/css/*").permitAll();
                    auth.requestMatchers("/photos/*").permitAll();

                    auth.requestMatchers("/home").permitAll();
                    auth.requestMatchers("/registration").permitAll();
                    auth.requestMatchers("login").permitAll();


                    auth.requestMatchers("/addBook").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/book/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/books").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/logout").hasAnyRole("ADMIN", "USER");

                    auth.requestMatchers("/addReview").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/reviews").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/review/**").hasAnyRole("ADMIN", "USER");


                });

        http.
                formLogin()
                     .loginPage("/login").defaultSuccessUrl("/home").permitAll()
                .and()
                     .logout().permitAll()
                .and()
                     .csrf().disable().authorizeHttpRequests()
                .and()
                     .cors().disable().authorizeHttpRequests();

        return http.build();

    }
}