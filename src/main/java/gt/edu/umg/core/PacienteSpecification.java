package gt.edu.umg.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import gt.edu.umg.core.entities.Paciente;
import gt.edu.umg.core.entities.Dtos.Request.PacienteFilter;
import jakarta.persistence.criteria.Predicate;
public final class PacienteSpecification {

    private PacienteSpecification() {}
    public static Specification<Paciente> buildSpec(PacienteFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getNombres() != null && !filter.getNombres().isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.get("nombres")),
                    "%" + filter.getNombres().toLowerCase().trim() + "%"
                ));
            }
            if (filter.getApellidos() != null && !filter.getApellidos().isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.get("apellidos")),
                    "%" + filter.getApellidos().toLowerCase().trim() + "%"
                ));
            }
            if (filter.getNumeroIdentificacion() != null
                    && !filter.getNumeroIdentificacion().isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.get("numeroIdentificacion")),
                    "%" + filter.getNumeroIdentificacion().toLowerCase().trim() + "%"
                ));
            }
            if (filter.getActivo() != null) {
                predicates.add(cb.equal(root.get("activo"), filter.getActivo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
