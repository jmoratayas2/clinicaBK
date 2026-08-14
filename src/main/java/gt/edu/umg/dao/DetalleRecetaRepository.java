package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.DetalleReceta;

public interface DetalleRecetaRepository extends IGenericRepository<DetalleReceta, Long> {
    List<DetalleReceta> findByRecetaId(Long recetaId);
}
