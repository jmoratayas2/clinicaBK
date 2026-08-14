package gt.edu.umg.service.impl;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import gt.edu.umg.core.entities.Paciente;

public interface PacienteService {
	Paciente guardar(Paciente paciente);
    List<Paciente> obtenerTodos();
    List<Paciente> obtenerTodos(boolean incluirInactivos);
    Paciente obtenerPorId(Long id);
    Paciente actualizar(Long id, Paciente paciente);
    void desactivar(Long id);
    void activar(Long id);
    
    // Método para consultas dinámicas (Queryable)
    List<Paciente> buscarConFiltro(Specification<Paciente> spec);
}
