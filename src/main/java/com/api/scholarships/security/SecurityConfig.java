package com.api.scholarships.security;

import com.api.scholarships.constants.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled=true)
@RequiredArgsConstructor
public class SecurityConfig {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    http
        .csrf()
        .disable()
        .authorizeHttpRequests((request)->request
            .requestMatchers(new AntPathRequestMatcher(Endpoints.AUTH+"/**","POST")).permitAll()
            .requestMatchers(new AntPathRequestMatcher(Endpoints.STATUS+"/**","GET")).permitAll()
            .requestMatchers(new AntPathRequestMatcher(Endpoints.SCHOLARSHIPS+"/**","GET")).permitAll()
            .requestMatchers(new AntPathRequestMatcher(Endpoints.ROLES+"/**","GET")).permitAll()
            .requestMatchers(new AntPathRequestMatcher(Endpoints.LANGUAGES+"/**","GET")).permitAll()
            .requestMatchers(new AntPathRequestMatcher(Endpoints.COURSE_TYPE+"/**","GET")).permitAll()
            .requestMatchers(new AntPathRequestMatcher(Endpoints.COUNTRIES+"/**","GET")).permitAll()
            .requestMatchers(new AntPathRequestMatcher(Endpoints.COMPANIES+"/**","GET")).permitAll()
            .anyRequest()
            .authenticated()
        )
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
