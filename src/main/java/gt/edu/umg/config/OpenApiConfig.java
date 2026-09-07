package gt.edu.umg.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Configura la documentación OpenAPI / Swagger UI.
 *
 * <p>Agrega el esquema de seguridad {@code Bearer JWT} para que el botón
 * "Authorize" de Swagger UI permita probar endpoints protegidos sin tener
 * que configurar las cabeceras manualmente.
 */
@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "Bearer JWT";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Sistema de Clínicas")
                        .version("1.0.0")
                        .description("Documentación de los endpoints del sistema de gestión de clínicas de la Universidad Mariano Gálvez."
                                + "\n\nUsa el botón **Authorize** para pegar el JWT generado por el security-service.")
                        .contact(new Contact()
                                .name("Soporte Técnico / Desarrollo")
                                .email("soporte@umg.edu.gt")
                                .url("https://umg.edu.gt"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                // Esquema de seguridad: Authorization: Bearer <token>
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Pega el JWT generado por security-service. No incluir 'Bearer ' — Swagger lo agrega automáticamente.")))
                // Aplica el esquema globalmente a todos los endpoints
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME, List.of()));
    }
}
