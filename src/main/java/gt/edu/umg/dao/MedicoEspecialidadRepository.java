package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.MedicoEspecialidad;

public interface MedicoEspecialidadRepository extends IGenericRepository<MedicoEspecialidad, Long> {
    boolean existsByMedicoIdAndEspecialidadId(Long medicoId, Long especialidadId);
    boolean existsByMedicoIdAndPrincipalTrueAndActivoTrue(Long medicoId);
    boolean existsByMedicoIdAndPrincipalTrueAndActivoTrueAndIdNot(Long medicoId, Long id);
    List<MedicoEspecialidad> findByMedicoId(Long medicoId);
    List<MedicoEspecialidad> findByActivoTrue();
    List<MedicoEspecialidad> findByMedicoIdAndActivoTrue(Long medicoId);
}
