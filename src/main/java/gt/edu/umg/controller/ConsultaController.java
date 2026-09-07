package gt.edu.umg.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.dao.ConsultaRepository;
import gt.edu.umg.dao.DetalleRecetaRepository;
import gt.edu.umg.dao.DiagnosticoRepository;
import gt.edu.umg.dao.ExamenRepository;
import gt.edu.umg.dao.RecetaRepository;
import gt.edu.umg.dao.ResultadoExamenRepository;
import gt.edu.umg.dao.TratamientoRepository;
import gt.edu.umg.dto.ClinicDtos.DetalleRecetaResponse;
import gt.edu.umg.dto.ClinicDtos.ConsultaResponse;
import gt.edu.umg.dto.ClinicDtos.DiagnosticoRequest;
import gt.edu.umg.dto.ClinicDtos.DiagnosticoResponse;
import gt.edu.umg.dto.ClinicDtos.ExamenEstadoRequest;
import gt.edu.umg.dto.ClinicDtos.ExamenRequest;
import gt.edu.umg.dto.ClinicDtos.ExamenResponse;
import gt.edu.umg.dto.ClinicDtos.RecetaRequest;
import gt.edu.umg.dto.ClinicDtos.RecetaResponse;
import gt.edu.umg.dto.ClinicDtos.ResultadoExamenRequest;
import gt.edu.umg.dto.ClinicDtos.ResultadoExamenResponse;
import gt.edu.umg.dto.ClinicDtos.TratamientoRequest;
import gt.edu.umg.dto.ClinicDtos.TratamientoResponse;
import gt.edu.umg.mapper.ClinicMapper;
import gt.edu.umg.service.impl.ClinicDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ConsultaController {
    private final ClinicDomainService service;
    private final ClinicMapper mapper;
    private final ConsultaRepository consultaRepository;
    private final DiagnosticoRepository diagnosticoRepository;
    private final TratamientoRepository tratamientoRepository;
    private final RecetaRepository recetaRepository;
    private final DetalleRecetaRepository detalleRecetaRepository;
    private final ExamenRepository examenRepository;
    private final ResultadoExamenRepository resultadoExamenRepository;

    public ConsultaController(ClinicDomainService service, ClinicMapper mapper, ConsultaRepository consultaRepository,
                              DiagnosticoRepository diagnosticoRepository, TratamientoRepository tratamientoRepository,
                              RecetaRepository recetaRepository, DetalleRecetaRepository detalleRecetaRepository,
                              ExamenRepository examenRepository, ResultadoExamenRepository resultadoExamenRepository) {
        this.service = service;
        this.mapper = mapper;
        this.consultaRepository = consultaRepository;
        this.diagnosticoRepository = diagnosticoRepository;
        this.tratamientoRepository = tratamientoRepository;
        this.recetaRepository = recetaRepository;
        this.detalleRecetaRepository = detalleRecetaRepository;
        this.examenRepository = examenRepository;
        this.resultadoExamenRepository = resultadoExamenRepository;
    }

    // ── Consultas ─────────────────────────────────────────────────────────────

    @GetMapping("/consultas")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public List<ConsultaResponse> listarConsultas() { return consultaRepository.findAll().stream().map(mapper::toConsulta).toList(); }

    @GetMapping("/consultas/{id}")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public ConsultaResponse obtenerConsulta(@PathVariable Long id) { return mapper.toConsulta(service.consulta(id)); }

    @PutMapping("/consultas/{id}")
    @PreAuthorize("hasAuthority('CONSULTA_CREATE')")
    public ConsultaResponse actualizarConsulta(@PathVariable Long id, @Valid @RequestBody gt.edu.umg.dto.ClinicDtos.ConsultaRequest request) {
        return mapper.toConsulta(service.actualizarConsulta(id, request));
    }

    // ── Diagnósticos ──────────────────────────────────────────────────────────

    @PostMapping("/consultas/{consultaId}/diagnosticos")
    @PreAuthorize("hasAuthority('DIAGNOSTICO_CREATE')")
    public ResponseEntity<DiagnosticoResponse> crearDiagnostico(@PathVariable Long consultaId, @Valid @RequestBody DiagnosticoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDiagnostico(service.crearDiagnostico(consultaId, request)));
    }

    @GetMapping("/consultas/{consultaId}/diagnosticos")
    @PreAuthorize("hasAuthority('DIAGNOSTICO_READ')")
    public List<DiagnosticoResponse> listarDiagnosticos(@PathVariable Long consultaId) {
        return diagnosticoRepository.findByConsultaId(consultaId).stream().map(mapper::toDiagnostico).toList();
    }

    @GetMapping("/diagnosticos")
    @PreAuthorize("hasAuthority('DIAGNOSTICO_READ')")
    public List<DiagnosticoResponse> listarDiagnosticos() {
        return diagnosticoRepository.findAll().stream().map(mapper::toDiagnostico).toList();
    }

    @GetMapping("/diagnosticos/{id}")
    @PreAuthorize("hasAuthority('DIAGNOSTICO_READ')")
    public DiagnosticoResponse obtenerDiagnostico(@PathVariable Long id) { return mapper.toDiagnostico(service.diagnostico(id)); }

    @PutMapping("/diagnosticos/{id}")
    @PreAuthorize("hasAuthority('DIAGNOSTICO_CREATE')")
    public DiagnosticoResponse actualizarDiagnostico(@PathVariable Long id, @Valid @RequestBody DiagnosticoRequest request) {
        return mapper.toDiagnostico(service.actualizarDiagnostico(id, request));
    }

    // ── Tratamientos ──────────────────────────────────────────────────────────

    @PostMapping("/consultas/{consultaId}/tratamientos")
    @PreAuthorize("hasAuthority('CONSULTA_CREATE')")
    public ResponseEntity<TratamientoResponse> crearTratamiento(@PathVariable Long consultaId, @Valid @RequestBody TratamientoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toTratamiento(service.crearTratamiento(consultaId, request)));
    }

    @GetMapping("/consultas/{consultaId}/tratamientos")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public List<TratamientoResponse> listarTratamientos(@PathVariable Long consultaId) {
        return tratamientoRepository.findByConsultaId(consultaId).stream().map(mapper::toTratamiento).toList();
    }

    @GetMapping("/tratamientos")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public List<TratamientoResponse> listarTratamientos() {
        return tratamientoRepository.findAll().stream().map(mapper::toTratamiento).toList();
    }

    @GetMapping("/tratamientos/{id}")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public TratamientoResponse obtenerTratamiento(@PathVariable Long id) { return mapper.toTratamiento(service.tratamiento(id)); }

    @PutMapping("/tratamientos/{id}")
    @PreAuthorize("hasAuthority('CONSULTA_CREATE')")
    public TratamientoResponse actualizarTratamiento(@PathVariable Long id, @Valid @RequestBody TratamientoRequest request) {
        return mapper.toTratamiento(service.actualizarTratamiento(id, request));
    }

    // ── Recetas ───────────────────────────────────────────────────────────────

    @PostMapping("/consultas/{consultaId}/recetas")
    @PreAuthorize("hasAuthority('CONSULTA_CREATE')")
    public ResponseEntity<RecetaResponse> crearReceta(@PathVariable Long consultaId, @Valid @RequestBody RecetaRequest request) {
        var receta = service.crearReceta(consultaId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toReceta(receta, service.detallesReceta(receta.getId())));
    }

    @GetMapping("/consultas/{consultaId}/recetas")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public List<RecetaResponse> listarRecetas(@PathVariable Long consultaId) {
        return recetaRepository.findByConsultaId(consultaId).stream()
                .map(r -> mapper.toReceta(r, service.detallesReceta(r.getId())))
                .toList();
    }

    @GetMapping("/recetas")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public List<RecetaResponse> listarRecetas() {
        return recetaRepository.findAll().stream()
                .map(r -> mapper.toReceta(r, service.detallesReceta(r.getId())))
                .toList();
    }

    @GetMapping("/recetas/{id}")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public RecetaResponse obtenerReceta(@PathVariable Long id) {
        var receta = service.receta(id);
        return mapper.toReceta(receta, service.detallesReceta(receta.getId()));
    }

    @GetMapping("/recetas/{id}/detalles")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public List<DetalleRecetaResponse> listarDetallesReceta(@PathVariable Long id) {
        return service.detallesReceta(id).stream().map(mapper::toDetalle).toList();
    }

    @GetMapping("/detalles-receta")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public List<DetalleRecetaResponse> listarDetallesReceta() {
        return detalleRecetaRepository.findAll().stream().map(mapper::toDetalle).toList();
    }

    @GetMapping("/detalles-receta/{id}")
    @PreAuthorize("hasAuthority('CONSULTA_READ')")
    public DetalleRecetaResponse obtenerDetalleReceta(@PathVariable Long id) { return mapper.toDetalle(service.detalleReceta(id)); }

    @PutMapping("/recetas/{id}/anular")
    @PreAuthorize("hasAuthority('CONSULTA_CREATE')")
    public RecetaResponse anularReceta(@PathVariable Long id) {
        var receta = service.anularReceta(id);
        return mapper.toReceta(receta, service.detallesReceta(receta.getId()));
    }

    // ── Exámenes ──────────────────────────────────────────────────────────────

    @PostMapping("/consultas/{consultaId}/examenes")
    @PreAuthorize("hasAuthority('EXAMEN_CREATE')")
    public ResponseEntity<ExamenResponse> crearExamen(@PathVariable Long consultaId, @Valid @RequestBody ExamenRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toExamen(service.crearExamen(consultaId, request)));
    }

    @GetMapping("/consultas/{consultaId}/examenes")
    @PreAuthorize("hasAuthority('EXAMEN_READ')")
    public List<ExamenResponse> listarExamenes(@PathVariable Long consultaId) {
        return examenRepository.findByConsultaId(consultaId).stream().map(mapper::toExamen).toList();
    }

    @GetMapping("/examenes")
    @PreAuthorize("hasAuthority('EXAMEN_READ')")
    public List<ExamenResponse> listarExamenes() {
        return examenRepository.findAll().stream().map(mapper::toExamen).toList();
    }

    @GetMapping("/examenes/{id}")
    @PreAuthorize("hasAuthority('EXAMEN_READ')")
    public ExamenResponse obtenerExamen(@PathVariable Long id) { return mapper.toExamen(service.examen(id)); }

    @PutMapping("/examenes/{id}/estado")
    @PreAuthorize("hasAuthority('EXAMEN_UPDATE')")
    public ExamenResponse cambiarEstadoExamen(@PathVariable Long id, @Valid @RequestBody ExamenEstadoRequest request) {
        return mapper.toExamen(service.cambiarEstadoExamen(id, request.estado()));
    }

    @PutMapping("/examenes/{id}/cancelar")
    @PreAuthorize("hasAuthority('EXAMEN_UPDATE')")
    public ExamenResponse cancelarExamen(@PathVariable Long id) {
        return mapper.toExamen(service.cancelarExamen(id));
    }

    @PostMapping("/examenes/{id}/resultado")
    @PreAuthorize("hasAuthority('EXAMEN_CREATE')")
    public ResponseEntity<ResultadoExamenResponse> registrarResultado(@PathVariable Long id, @Valid @RequestBody ResultadoExamenRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResultado(service.registrarResultado(id, request)));
    }

    @GetMapping("/examenes/{id}/resultado")
    @PreAuthorize("hasAuthority('EXAMEN_READ')")
    public ResultadoExamenResponse obtenerResultadoPorExamen(@PathVariable Long id) { return mapper.toResultado(service.resultadoPorExamen(id)); }

    @GetMapping("/resultados-examen/{id}")
    @PreAuthorize("hasAuthority('EXAMEN_READ')")
    public ResultadoExamenResponse obtenerResultado(@PathVariable Long id) { return mapper.toResultado(service.resultado(id)); }

    @GetMapping("/resultados-examen")
    @PreAuthorize("hasAuthority('EXAMEN_READ')")
    public List<ResultadoExamenResponse> listarResultados() { return resultadoExamenRepository.findAll().stream().map(mapper::toResultado).toList(); }

    @PutMapping("/resultados-examen/{id}")
    @PreAuthorize("hasAuthority('EXAMEN_UPDATE')")
    public ResultadoExamenResponse actualizarResultado(@PathVariable Long id, @Valid @RequestBody ResultadoExamenRequest request) {
        return mapper.toResultado(service.actualizarResultado(id, request));
    }
}
