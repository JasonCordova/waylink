package com.jasoncordova.waylink.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain (HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/favicon.ico", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/ws/**", "/topic/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/shorten", "/urls", "/urls/all", "/{shortUrl:[a-zA-Z0-9]{6,10}}/stats").authenticated()
                        .requestMatchers(HttpMethod.GET, "/404", "/", "/{shortUrl:[a-zA-Z0-9]{6,10}}", "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/{shortUrl:[a-zA-Z0-9]{6,10}}", "/test").permitAll()
                        .anyRequest().authenticated()           // everything else needs login
                )
                .oauth2Login(
                        oauth2 -> oauth2
                                .loginPage("/login")
                                .defaultSuccessUrl("/urls", true)
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(httpServletRequest ->
                                "GET".equalsIgnoreCase(httpServletRequest.getMethod()) &&
                                        "/logout".equals(httpServletRequest.getServletPath())
                        )
                        .logoutSuccessUrl("/")           // redirect after logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));

        return http.build();

    }

}
