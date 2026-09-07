package gt.edu.umg.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gt.edu.umg.core.PacienteSpecification;
import gt.edu.umg.core.entities.Paciente;
import gt.edu.umg.core.entities.Dtos.Request.PacienteFilter;
import gt.edu.umg.core.entities.Dtos.Response.PacienteResponseDto;
import gt.edu.umg.dao.PacienteRepository;
import gt.edu.umg.dto.PagedResponse;
import gt.edu.umg.dto.PaginationMetadata;
import gt.edu.umg.exception.BusinessRuleException;
import gt.edu.umg.exception.InvalidParameterException;
import gt.edu.umg.exception.ResourceNotFoundException;
import gt.edu.umg.mapper.PacienteMapper;

@Service
public class PacienteServiceImpl implements PacienteService {

    private static final List<Integer> ALLOWED_SIZES = List.of(50, 100, 200);

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper     pacienteMapper;

    public PacienteServiceImpl(PacienteRepository pacienteRepository,
                               PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper     = pacienteMapper;
    }

    @Override
    @Transactional
    public Paciente guardar(Paciente paciente) {
        if (paciente.getNumeroIdentificacion() != null
                && !paciente.getNumeroIdentificacion().isBlank()
                && pacienteRepository.existsByNumeroIdentificacion(
                        paciente.getNumeroIdentificacion())) {
            throw new BusinessRuleException(
                    "Ya existe un paciente con ese numero de identificacion");
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
        return incluirInactivos
                ? pacienteRepository.obtenerTodos()
                : pacienteRepository.findByActivoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente obtenerPorId(Long id) {
        return pacienteRepository.obtenerPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paciente no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public Paciente actualizar(Long id, Paciente paciente) {
        Paciente existente = obtenerPorId(id);
        if (paciente.getNumeroIdentificacion() != null
                && !paciente.getNumeroIdentificacion().isBlank()
                && pacienteRepository.existsByNumeroIdentificacionAndIdNot(
                        paciente.getNumeroIdentificacion(), id)) {
            throw new BusinessRuleException(
                    "Ya existe un paciente con ese numero de identificacion");
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
    @Override
    @Transactional(readOnly = true)
    public PagedResponse<PacienteResponseDto> buscarPaginado(PacienteFilter filter) {
        if (filter.getPage() < 0) {
            throw new InvalidParameterException(
                    "El numero de pagina debe ser mayor o igual a cero.");
        }
        if (!ALLOWED_SIZES.contains(filter.getSize())) {
            throw new InvalidParameterException(
                    "El tamano de pagina permitido es 50, 100 o 200.");
        }
        Specification<Paciente> spec = PacienteSpecification.buildSpec(filter);
        Sort sort = Sort.by(Sort.Direction.DESC, "creadoEn");
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(), sort);
        Page<Paciente> pageResult = pacienteRepository.findAll(spec, pageable);
        List<PacienteResponseDto> data = pageResult.getContent()
                .stream()
                .map(pacienteMapper::toDto)
                .toList();
        PaginationMetadata metadata = new PaginationMetadata(
                pageResult.getTotalElements(),   // totalRecords
                pageResult.getNumber(),          // page (zero-based)
                pageResult.getSize(),            // pageSize
                pageResult.getTotalPages(),      // totalPages
                pageResult.hasPrevious(),        // hasPreviousPage
                pageResult.hasNext()             // hasNextPage
        );

        return new PagedResponse<>(data, metadata);
    }
}
