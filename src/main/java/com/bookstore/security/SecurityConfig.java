package com.bookstore.security;

//import com.bookstore.service.MyUserDetailsService;
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

   // private final MyUserDetailsService userDetailsService;

    public SecurityConfig(/*MyUserDetailsService myUserDetailsService,*/ PasswordEncoder passwordEncoder) {
        /*this.userDetailsService = myUserDetailsService;*/
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(customizer ->customizer.disable());
//        http.sessionManagement(session->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(
                                "/", "/home1", "/api/auth/register",
                                "/login", "/css/**", "/images/**",
                                "books","/books/**","users","/users/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-ui/index.html",
                                "/swagger-ui/swagger-initializer.js", "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/api-docs",
                                "/api-docs/**",

                                // Swagger Resources
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",

                                // WebJars (for Swagger UI assets)
                                "/**").permitAll()
                        .requestMatchers("/book_form").hasRole("ADMIN")
                        .anyRequest().permitAll());
        http.formLogin(form ->form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/home",true)
                .failureUrl("/login?error")
                .permitAll()

        );// Uses default login page
        http.logout(Customizer.withDefaults());
        //http.userDetailsService(userDetailsService);
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
