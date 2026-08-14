package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.MedicoClinica;

public interface MedicoClinicaRepository extends IGenericRepository<MedicoClinica, Long> {
    boolean existsByMedicoIdAndClinicaId(Long medicoId, Long clinicaId);
    boolean existsByMedicoIdAndClinicaIdAndActivoTrue(Long medicoId, Long clinicaId);
    List<MedicoClinica> findByMedicoId(Long medicoId);
    List<MedicoClinica> findByActivoTrue();
    List<MedicoClinica> findByMedicoIdAndActivoTrue(Long medicoId);
}
