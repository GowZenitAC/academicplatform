package com.armando.academicplatform.security;

import com.armando.academicplatform.security.filter.JwtAuthenticationFilter;
import com.armando.academicplatform.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        JwtValidationFilter jwtValidationFilter = new JwtValidationFilter(authenticationManager());
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register").permitAll() // Permitir registro y login
                        .requestMatchers("/api/students/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/subjects").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/courses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/subjects").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.POST, "/api/notes").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.POST, "/api/materials").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/notes/student/{studentId}").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/api/notes/subject/{subjectId}").hasAnyRole("STUDENT", "TEACHER")
                        .requestMatchers(HttpMethod.GET, "/api/materials/subjects/{id}").hasAnyRole("STUDENT")
                        .requestMatchers("/api/teachers/**").hasRole("ADMIN")
                        .requestMatchers("/api/periods/**").hasRole("ADMIN")
                        .anyRequest().permitAll() // Otros endpoints requieren autenticación
                )
                .sessionManagement(m -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin sesiones
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .build();
    }

}
