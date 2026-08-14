package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.Receta;

public interface RecetaRepository extends IGenericRepository<Receta, Long> {
    List<Receta> findByConsultaId(Long consultaId);
}
