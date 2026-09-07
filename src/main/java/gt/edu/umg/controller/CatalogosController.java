package gt.edu.umg.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.dao.ClinicaRepository;
import gt.edu.umg.dao.EspecialidadRepository;
import gt.edu.umg.dao.MedicamentoRepository;
import gt.edu.umg.dao.MedicoRepository;
import gt.edu.umg.dao.TipoExamenRepository;
import gt.edu.umg.dto.ClinicDtos.ClinicaRequest;
import gt.edu.umg.dto.ClinicDtos.ClinicaResponse;
import gt.edu.umg.dto.ClinicDtos.EspecialidadRequest;
import gt.edu.umg.dto.ClinicDtos.EspecialidadResponse;
import gt.edu.umg.dto.ClinicDtos.MedicamentoRequest;
import gt.edu.umg.dto.ClinicDtos.MedicamentoResponse;
import gt.edu.umg.dto.ClinicDtos.MedicoRequest;
import gt.edu.umg.dto.ClinicDtos.MedicoResponse;
import gt.edu.umg.dto.ClinicDtos.TipoExamenRequest;
import gt.edu.umg.dto.ClinicDtos.TipoExamenResponse;
import gt.edu.umg.mapper.ClinicMapper;
import gt.edu.umg.service.impl.ClinicDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class CatalogosController {
    private final ClinicDomainService service;
    private final ClinicMapper mapper;
    private final ClinicaRepository clinicaRepository;
    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final TipoExamenRepository tipoExamenRepository;

    public CatalogosController(ClinicDomainService service, ClinicMapper mapper, ClinicaRepository clinicaRepository,
                               MedicoRepository medicoRepository, EspecialidadRepository especialidadRepository,
                               MedicamentoRepository medicamentoRepository, TipoExamenRepository tipoExamenRepository) {
        this.service = service;
        this.mapper = mapper;
        this.clinicaRepository = clinicaRepository;
        this.medicoRepository = medicoRepository;
        this.especialidadRepository = especialidadRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.tipoExamenRepository = tipoExamenRepository;
    }

    // ── Clínicas ──────────────────────────────────────────────────────────────

    @PostMapping("/clinicas")
    @PreAuthorize("hasAuthority('CLINICA_CREATE')")
    public ResponseEntity<ClinicaResponse> crearClinica(@Valid @RequestBody ClinicaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toClinica(service.crearClinica(request)));
    }

    @GetMapping("/clinicas")
    @PreAuthorize("hasAuthority('CLINICA_READ')")
    public List<ClinicaResponse> listarClinicas(@RequestParam(defaultValue = "false") boolean incluirInactivos) {
        return (incluirInactivos ? clinicaRepository.findAll() : clinicaRepository.findByActivoTrue()).stream().map(mapper::toClinica).toList();
    }

    @GetMapping("/clinicas/{id}")
    @PreAuthorize("hasAuthority('CLINICA_READ')")
    public ClinicaResponse obtenerClinica(@PathVariable Long id) { return mapper.toClinica(service.clinica(id)); }

    @DeleteMapping("/clinicas/{id}")
    @PreAuthorize("hasAuthority('CLINICA_UPDATE')")
    public ResponseEntity<Void> desactivarClinica(@PathVariable Long id) { service.desactivarClinica(id); return ResponseEntity.noContent().build(); }

    @PutMapping("/clinicas/{id}")
    @PreAuthorize("hasAuthority('CLINICA_UPDATE')")
    public ClinicaResponse actualizarClinica(@PathVariable Long id, @Valid @RequestBody ClinicaRequest request) { return mapper.toClinica(service.actualizarClinica(id, request)); }

    @PutMapping("/clinicas/{id}/activar")
    @PreAuthorize("hasAuthority('CLINICA_UPDATE')")
    public ResponseEntity<Void> activarClinica(@PathVariable Long id) { service.activarClinica(id); return ResponseEntity.noContent().build(); }

    // ── Médicos ───────────────────────────────────────────────────────────────

    @PostMapping("/medicos")
    @PreAuthorize("hasAuthority('MEDICO_CREATE')")
    public ResponseEntity<MedicoResponse> crearMedico(@Valid @RequestBody MedicoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toMedico(service.crearMedico(request)));
    }

    @GetMapping("/medicos")
    @PreAuthorize("hasAuthority('MEDICO_READ')")
    public List<MedicoResponse> listarMedicos(@RequestParam(defaultValue = "false") boolean incluirInactivos) {
        return (incluirInactivos ? medicoRepository.findAll() : medicoRepository.findByActivoTrue()).stream().map(mapper::toMedico).toList();
    }

    @GetMapping("/medicos/{id}")
    @PreAuthorize("hasAuthority('MEDICO_READ')")
    public MedicoResponse obtenerMedico(@PathVariable Long id) { return mapper.toMedico(service.medico(id)); }

    @DeleteMapping("/medicos/{id}")
    @PreAuthorize("hasAuthority('MEDICO_UPDATE')")
    public ResponseEntity<Void> desactivarMedico(@PathVariable Long id) { service.desactivarMedico(id); return ResponseEntity.noContent().build(); }

    @PutMapping("/medicos/{id}")
    @PreAuthorize("hasAuthority('MEDICO_UPDATE')")
    public MedicoResponse actualizarMedico(@PathVariable Long id, @Valid @RequestBody MedicoRequest request) { return mapper.toMedico(service.actualizarMedico(id, request)); }

    @PutMapping("/medicos/{id}/activar")
    @PreAuthorize("hasAuthority('MEDICO_UPDATE')")
    public ResponseEntity<Void> activarMedico(@PathVariable Long id) { service.activarMedico(id); return ResponseEntity.noContent().build(); }

    // ── Especialidades ────────────────────────────────────────────────────────

    @PostMapping("/especialidades")
    @PreAuthorize("hasAuthority('ESPECIALIDAD_CREATE')")
    public ResponseEntity<EspecialidadResponse> crearEspecialidad(@Valid @RequestBody EspecialidadRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toEspecialidad(service.crearEspecialidad(request)));
    }

    @GetMapping("/especialidades")
    @PreAuthorize("hasAuthority('ESPECIALIDAD_READ')")
    public List<EspecialidadResponse> listarEspecialidades(@RequestParam(defaultValue = "false") boolean incluirInactivos) {
        return (incluirInactivos ? especialidadRepository.findAll() : especialidadRepository.findByActivoTrue()).stream().map(mapper::toEspecialidad).toList();
    }

    @GetMapping("/especialidades/{id}")
    @PreAuthorize("hasAuthority('ESPECIALIDAD_READ')")
    public EspecialidadResponse obtenerEspecialidad(@PathVariable Long id) { return mapper.toEspecialidad(service.especialidad(id)); }

    @DeleteMapping("/especialidades/{id}")
    @PreAuthorize("hasAuthority('ESPECIALIDAD_UPDATE')")
    public ResponseEntity<Void> desactivarEspecialidad(@PathVariable Long id) { service.desactivarEspecialidad(id); return ResponseEntity.noContent().build(); }

    @PutMapping("/especialidades/{id}")
    @PreAuthorize("hasAuthority('ESPECIALIDAD_UPDATE')")
    public EspecialidadResponse actualizarEspecialidad(@PathVariable Long id, @Valid @RequestBody EspecialidadRequest request) { return mapper.toEspecialidad(service.actualizarEspecialidad(id, request)); }

    @PutMapping("/especialidades/{id}/activar")
    @PreAuthorize("hasAuthority('ESPECIALIDAD_UPDATE')")
    public ResponseEntity<Void> activarEspecialidad(@PathVariable Long id) { service.activarEspecialidad(id); return ResponseEntity.noContent().build(); }

    // ── Medicamentos ──────────────────────────────────────────────────────────

    @PostMapping("/medicamentos")
    @PreAuthorize("hasAuthority('MEDICAMENTO_CREATE')")
    public ResponseEntity<MedicamentoResponse> crearMedicamento(@Valid @RequestBody MedicamentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toMedicamento(service.crearMedicamento(request)));
    }

    @GetMapping("/medicamentos")
    @PreAuthorize("hasAuthority('MEDICAMENTO_READ')")
    public List<MedicamentoResponse> listarMedicamentos(@RequestParam(defaultValue = "false") boolean incluirInactivos) {
        return (incluirInactivos ? medicamentoRepository.findAll() : medicamentoRepository.findByActivoTrue()).stream().map(mapper::toMedicamento).toList();
    }

    @GetMapping("/medicamentos/{id}")
    @PreAuthorize("hasAuthority('MEDICAMENTO_READ')")
    public MedicamentoResponse obtenerMedicamento(@PathVariable Long id) { return mapper.toMedicamento(service.medicamento(id)); }

    @DeleteMapping("/medicamentos/{id}")
    @PreAuthorize("hasAuthority('MEDICAMENTO_UPDATE')")
    public ResponseEntity<Void> desactivarMedicamento(@PathVariable Long id) { service.desactivarMedicamento(id); return ResponseEntity.noContent().build(); }

    @PutMapping("/medicamentos/{id}")
    @PreAuthorize("hasAuthority('MEDICAMENTO_UPDATE')")
    public MedicamentoResponse actualizarMedicamento(@PathVariable Long id, @Valid @RequestBody MedicamentoRequest request) { return mapper.toMedicamento(service.actualizarMedicamento(id, request)); }

    @PutMapping("/medicamentos/{id}/activar")
    @PreAuthorize("hasAuthority('MEDICAMENTO_UPDATE')")
    public ResponseEntity<Void> activarMedicamento(@PathVariable Long id) { service.activarMedicamento(id); return ResponseEntity.noContent().build(); }

    // ── Tipos de Examen ───────────────────────────────────────────────────────

    @PostMapping("/tipos-examen")
    @PreAuthorize("hasAuthority('EXAMEN_CREATE')")
    public ResponseEntity<TipoExamenResponse> crearTipoExamen(@Valid @RequestBody TipoExamenRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toTipoExamen(service.crearTipoExamen(request)));
    }

    @GetMapping("/tipos-examen")
    @PreAuthorize("hasAuthority('EXAMEN_READ')")
    public List<TipoExamenResponse> listarTiposExamen(@RequestParam(defaultValue = "false") boolean incluirInactivos) {
        return (incluirInactivos ? tipoExamenRepository.findAll() : tipoExamenRepository.findByActivoTrue()).stream().map(mapper::toTipoExamen).toList();
    }

    @GetMapping("/tipos-examen/{id}")
    @PreAuthorize("hasAuthority('EXAMEN_READ')")
    public TipoExamenResponse obtenerTipoExamen(@PathVariable Long id) { return mapper.toTipoExamen(service.tipoExamen(id)); }

    @PutMapping("/tipos-examen/{id}")
    @PreAuthorize("hasAuthority('EXAMEN_UPDATE')")
    public TipoExamenResponse actualizarTipoExamen(@PathVariable Long id, @Valid @RequestBody TipoExamenRequest request) { return mapper.toTipoExamen(service.actualizarTipoExamen(id, request)); }

    @DeleteMapping("/tipos-examen/{id}")
    @PreAuthorize("hasAuthority('EXAMEN_UPDATE')")
    public ResponseEntity<Void> desactivarTipoExamen(@PathVariable Long id) { service.desactivarTipoExamen(id); return ResponseEntity.noContent().build(); }

    @PutMapping("/tipos-examen/{id}/activar")
    @PreAuthorize("hasAuthority('EXAMEN_UPDATE')")
    public ResponseEntity<Void> activarTipoExamen(@PathVariable Long id) { service.activarTipoExamen(id); return ResponseEntity.noContent().build(); }
}
