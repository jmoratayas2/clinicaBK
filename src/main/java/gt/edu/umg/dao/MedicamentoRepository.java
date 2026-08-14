package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.Medicamento;

public interface MedicamentoRepository extends IGenericRepository<Medicamento, Long> {
    List<Medicamento> findByActivoTrue();
}
