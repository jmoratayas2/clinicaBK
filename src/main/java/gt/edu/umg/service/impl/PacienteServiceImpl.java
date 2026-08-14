package gt.edu.umg.service.impl;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gt.edu.umg.core.entities.Paciente;
import gt.edu.umg.dao.PacienteRepository;
import gt.edu.umg.exception.BusinessRuleException;
import gt.edu.umg.exception.ResourceNotFoundException;

@Service
public class PacienteServiceImpl implements PacienteService {

	private final PacienteRepository pacienteRepository;

	public PacienteServiceImpl(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}
	
	@Override
	@Transactional
	public Paciente guardar(Paciente paciente) {
		if (paciente.getNumeroIdentificacion() != null && !paciente.getNumeroIdentificacion().isBlank()
				&& pacienteRepository.existsByNumeroIdentificacion(paciente.getNumeroIdentificacion())) {
			throw new BusinessRuleException("Ya existe un paciente con ese numero de identificacion");
		}
		return pacienteRepository.guardar(paciente);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> obtenerTodos() {
		return pacienteRepository.obtenerTodos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> obtenerTodos(boolean incluirInactivos) {
		return incluirInactivos ? pacienteRepository.obtenerTodos() : pacienteRepository.findByActivoTrue();
	}

	@Override
	@Transactional(readOnly = true)
	public Paciente obtenerPorId(Long id) {
		return pacienteRepository.obtenerPorId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el ID: " + id));
	}

	@Override
	@Transactional
	public Paciente actualizar(Long id, Paciente paciente) {
		Paciente existente = obtenerPorId(id);
		if (paciente.getNumeroIdentificacion() != null && !paciente.getNumeroIdentificacion().isBlank()
				&& pacienteRepository.existsByNumeroIdentificacionAndIdNot(paciente.getNumeroIdentificacion(), id)) {
			throw new BusinessRuleException("Ya existe un paciente con ese numero de identificacion");
		}
		existente.setNombres(paciente.getNombres());
		existente.setApellidos(paciente.getApellidos());
		existente.setTipoIdentificacion(paciente.getTipoIdentificacion());
		existente.setNumeroIdentificacion(paciente.getNumeroIdentificacion());
		existente.setFechaNacimiento(paciente.getFechaNacimiento());
		existente.setSexoBiologico(paciente.getSexoBiologico());
		existente.setTelefono(paciente.getTelefono());
		existente.setCorreo(paciente.getCorreo());
		existente.setDireccion(paciente.getDireccion());
		existente.setContactoEmergencia(paciente.getContactoEmergencia());
		existente.setTelefonoEmergencia(paciente.getTelefonoEmergencia());
		return existente;
	}

	@Override
	@Transactional
	public void desactivar(Long id) {
		Paciente paciente = obtenerPorId(id);
		paciente.setActivo(false);
	}

	@Override
	@Transactional
	public void activar(Long id) {
		Paciente paciente = obtenerPorId(id);
		paciente.setActivo(true);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> buscarConFiltro(Specification<Paciente> spec) {
		return pacienteRepository.findAll(spec);
	}

}
