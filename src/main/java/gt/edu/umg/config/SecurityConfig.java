package gt.edu.umg.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import gt.edu.umg.security.JwtAuthoritiesConverter;

/**
 * Configuración principal de Spring Security para el backend de clínicas
 * operando como <strong>OAuth2 Resource Server</strong>.
 *
 * <p>Flujo:
 * <ol>
 *   <li>El cliente envía {@code Authorization: Bearer <JWT>}.</li>
 *   <li>{@code NimbusJwtDecoder} (en {@link RsaJwtConfig}) verifica la firma RS256,
 *       expiración, issuer y audience.</li>
 *   <li>{@link JwtAuthoritiesConverter} extrae el claim {@code authorities} y lo
 *       convierte en {@code SimpleGrantedAuthority} sin prefijos.</li>
 *   <li>Spring Security aplica las reglas de {@code @PreAuthorize} en los controllers.</li>
 * </ol>
 *
 * <p>Rutas públicas: Swagger UI y OpenAPI spec.
 * <p>El resto requiere autenticación válida (401 si no hay token, 403 si no hay permisos).
 */
@Configuration
@EnableMethodSecurity   // habilita @PreAuthorize / @PostAuthorize
public class SecurityConfig {

    /**
     * Cadena de filtros de seguridad. Stateless: sin sesiones HTTP.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthoritiesConverter jwtAuthoritiesConverter,
            AuthenticationEntryPoint authenticationEntryPoint,
            AccessDeniedHandler accessDeniedHandler) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Swagger / OpenAPI — públicos
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs"
                        ).permitAll()
                        // Cualquier otro endpoint requiere JWT válido
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthoritiesConverter))
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .build();
    }

    /**
     * Responde 401 en JSON cuando el token no existe, es inválido o expiró.
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, ex) -> {
            response.setStatus(401);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(
                    "{\"status\":401,\"error\":\"UNAUTHORIZED\",\"message\":\"Token ausente o invalido\",\"timestamp\":\"" + LocalDateTime.now() + "\",\"details\":[]}"
            );
        };
    }

    /**
     * Responde 403 en JSON cuando el usuario está autenticado pero no posee
     * el permiso requerido por {@code @PreAuthorize}.
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, ex) -> {
            response.setStatus(403);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(
                    "{\"status\":403,\"error\":\"FORBIDDEN\",\"message\":\"No tiene permisos suficientes\",\"timestamp\":\"" + LocalDateTime.now() + "\",\"details\":[]}"
            );
        };
    }

    @Value("${cors.allowed-origins:http://localhost:4200}")
    private String allowedOrigins;

    /**
     * Configuración CORS para el frontend (configurable por variable de entorno).
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        List<String> origins = java.util.Arrays.stream(allowedOrigins.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();
        config.setAllowedOrigins(origins);
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

