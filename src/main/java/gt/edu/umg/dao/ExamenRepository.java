package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.Examen;

public interface ExamenRepository extends IGenericRepository<Examen, Long> {
    List<Examen> findByConsultaId(Long consultaId);
}
