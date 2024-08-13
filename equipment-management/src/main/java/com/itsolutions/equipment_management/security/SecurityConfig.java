package com.itsolutions.equipment_management.security;

import com.itsolutions.equipment_management.services.PersonneService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final PersonneService personneService;

    public SecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter, PersonneService personneService) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.personneService = personneService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll()
                                .requestMatchers("api/users/**").permitAll()
                        .requestMatchers("/api/users/delete").hasRole("ADMIN")
                                .requestMatchers("/api/pannes/**").hasAnyRole( "ADMIN")
                                .requestMatchers("/api/tickets/assign/").authenticated()
                                .requestMatchers("api/tickets/all").hasRole("ADMIN")
//                        .requestMatchers("/api/users/**", "/api/techniciens/**").hasRole("ADMIN")
//                        .requestMatchers("/api/tickets/create").hasRole("USER")
//                        .requestMatchers("/api/panne-equipment/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @PostConstruct
    public void initAdminUser() {
        personneService.createAdminUserIfNotExist();
    }
}
