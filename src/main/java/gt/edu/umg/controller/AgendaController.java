package gt.edu.umg.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.dao.CitaRepository;
import gt.edu.umg.dto.ClinicDtos.CitaEstadoRequest;
import gt.edu.umg.dto.ClinicDtos.CitaRequest;
import gt.edu.umg.dto.ClinicDtos.CitaResponse;
import gt.edu.umg.dto.ClinicDtos.ConsultaRequest;
import gt.edu.umg.dto.ClinicDtos.ConsultaResponse;
import gt.edu.umg.mapper.ClinicMapper;
import gt.edu.umg.service.impl.ClinicDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/citas")
public class AgendaController {
    private final ClinicDomainService service;
    private final ClinicMapper mapper;
    private final CitaRepository citaRepository;

    public AgendaController(ClinicDomainService service, ClinicMapper mapper, CitaRepository citaRepository) {
        this.service = service;
        this.mapper = mapper;
        this.citaRepository = citaRepository;
    }

    @PostMapping
    public ResponseEntity<CitaResponse> crear(@Valid @RequestBody CitaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toCita(service.crearCita(request)));
    }

    @GetMapping
    public List<CitaResponse> listar() { return citaRepository.findAll().stream().map(mapper::toCita).toList(); }

    @GetMapping("/{id}")
    public CitaResponse obtener(@PathVariable Long id) { return mapper.toCita(service.cita(id)); }

    @PutMapping("/{id}/estado")
    public CitaResponse cambiarEstado(@PathVariable Long id, @Valid @RequestBody CitaEstadoRequest request) {
        return mapper.toCita(service.cambiarEstadoCita(id, request.estado()));
    }

    @PutMapping("/{id}")
    public CitaResponse actualizar(@PathVariable Long id, @Valid @RequestBody CitaRequest request) {
        return mapper.toCita(service.actualizarCita(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cambiarEstadoCita(id, gt.edu.umg.core.entities.enums.EstadoCita.CANCELADA);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/consulta")
    public ResponseEntity<ConsultaResponse> crearConsulta(@PathVariable Long id, @Valid @RequestBody ConsultaRequest request) {
        ConsultaRequest normalizado = new ConsultaRequest(id, request.fechaHora(), request.motivoConsulta(), request.sintomas(), request.examenFisico(), request.observaciones(), request.planGeneral());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toConsulta(service.crearConsulta(normalizado)));
    }
}
