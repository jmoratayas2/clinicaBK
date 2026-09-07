package gt.edu.umg.security;

import java.util.Collection;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Convierte un {@link Jwt} en un {@link AbstractAuthenticationToken} extrayendo
 * el claim {@code authorities} del token y mapeando cada valor directamente a un
 * {@link SimpleGrantedAuthority} <strong>sin</strong> agregar prefijos ({@code ROLE_}
 * ni {@code SCOPE_}).
 *
 * <p>Ejemplo de claim esperado en el JWT:
 * <pre>{@code
 * "authorities": ["PACIENTE_READ", "CITA_CREATE", "MEDICO_UPDATE"]
 * }</pre>
 *
 * <p>Esto permite usar {@code @PreAuthorize("hasAuthority('PACIENTE_READ')")} directamente.
 */
@Component
public class JwtAuthoritiesConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        List<String> values = jwt.getClaimAsStringList("authorities");
        Collection<SimpleGrantedAuthority> authorities = (values == null)
                ? List.of()
                : values.stream().map(SimpleGrantedAuthority::new).toList();
        return new JwtAuthenticationToken(jwt, authorities, jwt.getSubject());
    }
}
