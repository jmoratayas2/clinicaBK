package gt.edu.umg.dao;

import java.util.Optional;

import gt.edu.umg.core.entities.Consulta;

public interface ConsultaRepository extends IGenericRepository<Consulta, Long> {
    boolean existsByCitaId(Long citaId);
    Optional<Consulta> findByCitaId(Long citaId);
}
