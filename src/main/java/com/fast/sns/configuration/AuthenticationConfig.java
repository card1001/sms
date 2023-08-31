package com.fast.sns.configuration;

import com.fast.sns.configuration.filter.JwtTokenFilter;
import com.fast.sns.exception.CustomAuthenticationEntryPoint;
import com.fast.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final UserService userService;
    @Value("${jwt.secret-key}")
    private String secretKey;

//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().regexMatchers("^(?!/api/).*");
//    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("^(?!/api/).*").denyAll()
                .requestMatchers("/api/*/users/join", "/api/*/users/login").permitAll()
                .requestMatchers("/api/*/users/alarm/subscribe/*").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}