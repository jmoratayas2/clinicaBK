package gt.edu.umg.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * Valida que el JWT contenga la audiencia requerida ({@code clinicas-backend}).
 * Se registra como parte del {@link org.springframework.security.oauth2.jwt.JwtDecoder}
 * en {@code RsaJwtConfig} y se invoca automáticamente en cada petición.
 */
public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final String audience;

    public AudienceValidator(String audience) {
        this.audience = audience;
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (token.getAudience().contains(audience)) {
            return OAuth2TokenValidatorResult.success();
        }
        return OAuth2TokenValidatorResult.failure(
                new OAuth2Error("invalid_token", "El token no contiene la audiencia requerida: " + audience, null)
        );
    }
}
