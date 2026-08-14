package gt.edu.umg.dao;

import java.util.Optional;

import gt.edu.umg.core.entities.HistorialMedico;

public interface HistorialMedicoRepository extends IGenericRepository<HistorialMedico, Long> {
    Optional<HistorialMedico> findByPacienteId(Long pacienteId);
    boolean existsByPacienteId(Long pacienteId);
}
