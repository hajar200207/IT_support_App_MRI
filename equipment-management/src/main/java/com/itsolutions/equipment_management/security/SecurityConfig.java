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
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll() // Les endpoints de registre et de login sont accessibles à tous.
                        .requestMatchers("api/users/**").permitAll() // Permet l'accès aux utilisateurs pour toutes les requêtes sous "/api/users".
                        .requestMatchers("/api/users/delete").hasRole("ADMIN") // Le endpoint pour supprimer un utilisateur nécessite un rôle d'administrateur.
                        .requestMatchers("/api/pannes/**").hasAnyRole("ADMIN") // L'accès aux endpoints liés aux pannes est réservé aux administrateurs.
                        .requestMatchers("/api/tickets/assign/").authenticated() // Le endpoint d'assignation de ticket nécessite une authentification.
                        .requestMatchers("api/tickets/all").hasRole("ADMIN") // L'accès à tous les tickets est réservé aux administrateurs.
                        .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification.
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
// utiliser pour les put et delet  qui recupirer les donnes sun en forms
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
