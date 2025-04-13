package com.bookstore.security;

import com.bookstore.security.filter.JwtFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig{

    private final MyUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;
    public SecurityConfig(
            MyUserDetailsService myUserDetailsService,
            PasswordEncoder passwordEncoder,
            JwtFilter jwtFilter) {
        this.userDetailsService = myUserDetailsService;
        this.jwtFilter = jwtFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(customizer ->customizer.disable());
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(
                                "/", "/home", "/register",
                                "/login", "/css/**", "/images/**",
                                "books").permitAll()
                        .requestMatchers("/book_form").hasRole("ADMIN")
                        .anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
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
