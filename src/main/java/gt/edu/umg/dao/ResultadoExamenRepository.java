package gt.edu.umg.dao;

import java.util.Optional;

import gt.edu.umg.core.entities.ResultadoExamen;

public interface ResultadoExamenRepository extends IGenericRepository<ResultadoExamen, Long> {
    boolean existsByExamenId(Long examenId);
    Optional<ResultadoExamen> findByExamenId(Long examenId);
}
