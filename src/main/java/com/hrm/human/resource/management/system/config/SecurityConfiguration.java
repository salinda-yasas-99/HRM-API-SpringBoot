package com.hrm.human.resource.management.system.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers(
                            "/api/v1/auth/**",
                            "/v2/api-docs" ,
                            "/v3/api-docs/**",
                            "/swagger-resources" ,
                            "/swagger-resources/**" ,
                            "/configuration/ui " ,
                            "/ configuration/ security",
                            "/swagger-ui/**",
                            "/webjars/**",
                            "/swagger-ui.html").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
//        http
//                .csrf().disable() // Disable CSRF protection (if needed)
//                .authorizeRequests()
//                .anyRequest().permitAll(); // Permit all requests without authentication
//        return http.build();
    }
}