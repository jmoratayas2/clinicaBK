package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.Tratamiento;

public interface TratamientoRepository extends IGenericRepository<Tratamiento, Long> {
    List<Tratamiento> findByConsultaId(Long consultaId);
}
