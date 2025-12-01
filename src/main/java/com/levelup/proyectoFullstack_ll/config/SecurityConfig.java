package com.levelup.proyectoFullstack_ll.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.levelup.proyectoFullstack_ll.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf( csrf ->
                csrf
                .disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(authRequest ->
                authRequest
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/auth/**").permitAll() //Registro
                .requestMatchers("/api/v1/usuarios/**").hasAuthority("ADMIN") //Gestion de usuarios
                //Productos
                .requestMatchers(HttpMethod.GET, "/api/v1/productos/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/productos/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/productos/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/productos/**").hasAuthority("ADMIN")
                //Usuario pedidos
                .requestMatchers(HttpMethod.POST, "/api/v1/pedidos").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/v1/pedidos/usuario/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/v1/pedidos/{id}").authenticated()
                //Usuario ADMIN
                .requestMatchers(HttpMethod.GET, "/api/v1/pedidos/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/pedidos/buscar").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/pedidos/*/estado").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/pedidos/**").hasAuthority("ADMIN")
                //Categorias y Plataformas
                .requestMatchers(HttpMethod.GET, "/api/v1/categorias/**", "/api/v1/plataformas/**").permitAll()
                .requestMatchers("/api/v1/categorias/**", "/api/v1/plataformas/**").hasAuthority("ADMIN")
                //Detalles y Envios
                .requestMatchers("/api/v1/detalles/**").authenticated()
                .requestMatchers("/api/v1/envios/**").authenticated()
                //Especificaciones
                .requestMatchers(HttpMethod.GET, "/api/v1/especificaciones").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/especificaciones").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/especificaciones").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/especificaciones").hasAuthority("ADMIN")
                //Contacto
                .requestMatchers(HttpMethod.POST, "/api/v1/contacto").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/contacto").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/contacto").hasAuthority("ADMIN")

                .anyRequest().authenticated()
                )
            .sessionManagement(sessionManager ->
                sessionManager
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite el puerto de tu frontend (ej: 5173 para Vite)
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); 
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "Accept"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
}
