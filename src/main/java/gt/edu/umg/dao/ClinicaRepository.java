package gt.edu.umg.dao;

import java.util.List;

import gt.edu.umg.core.entities.Clinica;

public interface ClinicaRepository extends IGenericRepository<Clinica, Long> {
    List<Clinica> findByActivoTrue();
}
