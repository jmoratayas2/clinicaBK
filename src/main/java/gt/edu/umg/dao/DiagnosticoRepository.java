package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.Diagnostico;

public interface DiagnosticoRepository extends IGenericRepository<Diagnostico, Long> {
    boolean existsByConsultaIdAndPrincipalTrue(Long consultaId);
    List<Diagnostico> findByConsultaId(Long consultaId);
}
