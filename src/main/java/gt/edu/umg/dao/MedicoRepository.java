package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.Medico;

public interface MedicoRepository extends IGenericRepository<Medico, Long> {
    boolean existsByNumeroColegiado(String numeroColegiado);
    boolean existsByCorreo(String correo);
    boolean existsByNumeroColegiadoAndIdNot(String numeroColegiado, Long id);
    boolean existsByCorreoAndIdNot(String correo, Long id);
    List<Medico> findByActivoTrue();
}
