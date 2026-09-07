package gt.edu.umg.config;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import gt.edu.umg.security.AudienceValidator;

/**
 * Configura el {@link JwtDecoder} del backend de clínicas como OAuth2 Resource Server.
 *
 * <p>Carga únicamente la <strong>llave pública RSA</strong> ({@code keys/public.pem})
 * para verificar la firma de los tokens generados por {@code security-service}.
 * La llave privada no existe ni se necesita en este servicio.
 *
 * <p>Validaciones registradas:
 * <ul>
 *   <li>Firma RSA / RS256 — realizada por Nimbus al construir el decoder</li>
 *   <li>Expiración ({@code exp}) — incluida por {@link JwtValidators#createDefaultWithIssuer}</li>
 *   <li>Issuer ({@code iss = clinicas-security}) — incluido por {@link JwtValidators#createDefaultWithIssuer}</li>
 *   <li>Audience ({@code aud = clinicas-backend}) — {@link AudienceValidator}</li>
 * </ul>
 */
@Configuration
public class RsaJwtConfig {

    /**
     * Construye el {@link JwtDecoder} con la llave pública RSA y los validadores
     * de issuer, expiración y audiencia.
     */
    @Bean
    public JwtDecoder jwtDecoder(
            @Value("${security.jwt.public-key}") Resource publicKeyResource,
            @Value("${security.jwt.issuer}") String issuer,
            @Value("${security.jwt.audience}") String audience) throws Exception {

        RSAPublicKey publicKey = readPublicKey(publicKeyResource);

        NimbusJwtDecoder decoder = NimbusJwtDecoder.withPublicKey(publicKey).build();

        // createDefaultWithIssuer valida iss + exp automáticamente
        OAuth2TokenValidator<Jwt> issuerValidator = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);

        decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(issuerValidator, audienceValidator));

        return decoder;
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private RSAPublicKey readPublicKey(Resource resource) throws Exception {
        String pem;
        try (InputStream in = resource.getInputStream()) {
            pem = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
        String cleaned = pem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] bytes = Base64.getDecoder().decode(cleaned);
        return (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(bytes));
    }
}
