package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.Especialidad;

public interface EspecialidadRepository extends IGenericRepository<Especialidad, Long> {
    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdNot(String nombre, Long id);
    List<Especialidad> findByActivoTrue();
}
