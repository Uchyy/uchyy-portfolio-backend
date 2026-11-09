
package com.uchyy.portfolio.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.FirebaseApp;
import com.uchyy.portfolio.model.ApiErrorResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, FirebaseApp firebaseApp
    ) throws Exception {

        FirebaseTokenFilter firebaseTokenFilter = new FirebaseTokenFilter(firebaseApp);
        ObjectMapper objectMapper = new ObjectMapper();
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()) );

        http.csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
        .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {

                    ApiErrorResponse error = new ApiErrorResponse(
                            HttpStatus.UNAUTHORIZED.value(),
                            "Unauthorized",
                            "You are not authorized to access this resource.",
                            request.getRequestURI()
                    );

                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    objectMapper.writeValue(response.getOutputStream(), error);
                })

                .accessDeniedHandler((request, response, accessDeniedException) -> {

                    ApiErrorResponse error = new ApiErrorResponse(
                            HttpStatus.FORBIDDEN.value(),
                            "Forbidden",
                            "You do not have permission to access this resource.",
                            request.getRequestURI()
                    );
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    objectMapper.writeValue(response.getOutputStream(), error);
                })
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // Admin: all songs, including unpublished
            .requestMatchers(HttpMethod.GET, "/api/songs/admin").authenticated()
            // Public portfolio
            .requestMatchers(HttpMethod.GET, "/api/songs", "/api/songs/*").permitAll()
            // Public contact form
            .requestMatchers(HttpMethod.POST, "/api/enquiries").permitAll()
            // Everything else requires authentication
            .anyRequest().authenticated()
        )
        .addFilterBefore( firebaseTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:5173",  "http://localhost:5174", "https://uchyy.github.io")
        );

        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        configuration.setAllowedHeaders(
                List.of("Authorization", "Content-Type")
        );

        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
