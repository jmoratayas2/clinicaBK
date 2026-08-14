package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.TipoExamen;

public interface TipoExamenRepository extends IGenericRepository<TipoExamen, Long> {
    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdNot(String nombre, Long id);
    List<TipoExamen> findByActivoTrue();
}
