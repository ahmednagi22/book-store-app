package com.bookstore.security;

import com.bookstore.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    private final MyUserDetailsService userDetailsService;

    public SecurityConfig(MyUserDetailsService myUserDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = myUserDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(customizer ->customizer.disable());
//        http.sessionManagement(session->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(
                                "/", "/home", "/register",
                                "/login", "/css/**", "/images/**",
                                "books").permitAll()
                        .requestMatchers("/book_form").hasRole("ADMIN")
                        .anyRequest().authenticated());
        http.formLogin(form ->form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/home",true)
                .failureUrl("/login?error")
                .permitAll()
        );// Uses default login page
        http.logout(Customizer.withDefaults());
        http.userDetailsService(userDetailsService);
        http.exceptionHandling(exception ->
                exception.accessDeniedPage("/error/access-denied"));
        return http.build();
    }

    // implement JWT token

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
