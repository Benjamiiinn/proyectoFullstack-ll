package com.levelup.proyectoFullstack_ll.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:Tienda FullStack II}")
    private String applicationName;

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        
        return new OpenAPI()
                .info(new Info()
                        .title("API Tienda FullStack II - Documentación")
                        .version("1.0.0")
                        .description("""
                                ## Documentación de la API REST para Tienda FullStack II
                                
                                Esta API proporciona servicios para gestionar una tienda en línea de videojuegos.
                                
                                ### Características principales:
                                - **Autenticación JWT**: Sistema de autenticación seguro con tokens JWT
                                - **Gestión de Productos**: CRUD completo para productos, categorías y plataformas
                                - **Carrito de Compras**: Gestión de pedidos y detalles de pedido
                                - **Gestión de Usuarios**: Registro, login y administración de usuarios
                                - **Panel de Administración**: Endpoints exclusivos para administradores
                                - **Sistema de Contacto**: Gestión de mensajes de contacto
                                
                                ### Autenticación:
                                La mayoría de los endpoints requieren autenticación JWT. Para usar estos endpoints:
                                1. Registrarse o iniciar sesión en `/auth/login` o `/auth/register`
                                2. Copiar el token JWT de la respuesta
                                3. Hacer clic en el botón "Authorize" (🔓) arriba
                                4. Ingresar el token en el formato: `Bearer <tu-token-aquí>`
                                5. Hacer clic en "Authorize" y luego "Close"
                                
                                ### Roles de Usuario:
                                - **USER**: Usuario estándar con permisos básicos
                                - **ADMIN**: Administrador con acceso completo al sistema
                                """)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("contacto@tiendafullstack.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desarrollo Local"),
                        new Server()
                                .url("http://100.24.109.46:8080")
                                .description("Servidor de Producción")
                ))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Ingrese el token JWT en el formato: Bearer <token>")));
    }
}
