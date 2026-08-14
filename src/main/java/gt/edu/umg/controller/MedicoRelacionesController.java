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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.dao.MedicoClinicaRepository;
import gt.edu.umg.dao.MedicoEspecialidadRepository;
import gt.edu.umg.dto.ClinicDtos.MedicoClinicaRequest;
import gt.edu.umg.dto.ClinicDtos.MedicoClinicaResponse;
import gt.edu.umg.dto.ClinicDtos.MedicoEspecialidadRequest;
import gt.edu.umg.dto.ClinicDtos.MedicoEspecialidadResponse;
import gt.edu.umg.mapper.ClinicMapper;
import gt.edu.umg.service.impl.ClinicDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/medicos")
public class MedicoRelacionesController {
    private final ClinicDomainService service;
    private final ClinicMapper mapper;
    private final MedicoClinicaRepository medicoClinicaRepository;
    private final MedicoEspecialidadRepository medicoEspecialidadRepository;

    public MedicoRelacionesController(ClinicDomainService service, ClinicMapper mapper,
                                      MedicoClinicaRepository medicoClinicaRepository,
                                      MedicoEspecialidadRepository medicoEspecialidadRepository) {
        this.service = service;
        this.mapper = mapper;
        this.medicoClinicaRepository = medicoClinicaRepository;
        this.medicoEspecialidadRepository = medicoEspecialidadRepository;
    }

    @PostMapping("/{medicoId}/clinicas")
    public ResponseEntity<MedicoClinicaResponse> asignarClinica(@PathVariable Long medicoId,
                                                                @Valid @RequestBody MedicoClinicaRequest request) {
        MedicoClinicaRequest normalizado = new MedicoClinicaRequest(medicoId, request.clinicaId(), request.consultorio(), request.fechaIngreso());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toMedicoClinica(service.asignarMedicoClinica(normalizado)));
    }

    @GetMapping("/{medicoId}/clinicas")
    public List<MedicoClinicaResponse> listarClinicas(@PathVariable Long medicoId,
                                                      @RequestParam(defaultValue = "false") boolean incluirInactivos) {
        return (incluirInactivos ? medicoClinicaRepository.findByMedicoId(medicoId) : medicoClinicaRepository.findByMedicoIdAndActivoTrue(medicoId))
                .stream().map(mapper::toMedicoClinica).toList();
    }

    @GetMapping("/clinicas-relaciones")
    public List<MedicoClinicaResponse> listarTodasClinicas(@RequestParam(defaultValue = "false") boolean incluirInactivos) {
        return (incluirInactivos ? medicoClinicaRepository.findAll() : medicoClinicaRepository.findByActivoTrue())
                .stream().map(mapper::toMedicoClinica).toList();
    }

    @GetMapping("/clinicas-relaciones/{id}")
    public MedicoClinicaResponse obtenerMedicoClinica(@PathVariable Long id) {
        return mapper.toMedicoClinica(service.medicoClinica(id));
    }

    @PutMapping("/clinicas-relaciones/{id}")
    public MedicoClinicaResponse actualizarMedicoClinica(@PathVariable Long id, @Valid @RequestBody MedicoClinicaRequest request) {
        return mapper.toMedicoClinica(service.actualizarMedicoClinica(id, request));
    }

    @DeleteMapping("/clinicas-relaciones/{id}")
    public ResponseEntity<Void> desactivarMedicoClinica(@PathVariable Long id) {
        service.desactivarMedicoClinica(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clinicas-relaciones/{id}/activar")
    public ResponseEntity<Void> activarMedicoClinica(@PathVariable Long id) {
        service.activarMedicoClinica(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{medicoId}/especialidades")
    public ResponseEntity<MedicoEspecialidadResponse> asignarEspecialidad(@PathVariable Long medicoId,
                                                                          @Valid @RequestBody MedicoEspecialidadRequest request) {
        MedicoEspecialidadRequest normalizado = new MedicoEspecialidadRequest(medicoId, request.especialidadId(), request.principal(), request.numeroCertificacion());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toMedicoEspecialidad(service.asignarMedicoEspecialidad(normalizado)));
    }

    @GetMapping("/{medicoId}/especialidades")
    public List<MedicoEspecialidadResponse> listarEspecialidades(@PathVariable Long medicoId,
                                                                @RequestParam(defaultValue = "false") boolean incluirInactivos) {
        return (incluirInactivos ? medicoEspecialidadRepository.findByMedicoId(medicoId) : medicoEspecialidadRepository.findByMedicoIdAndActivoTrue(medicoId))
                .stream().map(mapper::toMedicoEspecialidad).toList();
    }

    @GetMapping("/especialidades-relaciones")
    public List<MedicoEspecialidadResponse> listarTodasEspecialidades(@RequestParam(defaultValue = "false") boolean incluirInactivos) {
        return (incluirInactivos ? medicoEspecialidadRepository.findAll() : medicoEspecialidadRepository.findByActivoTrue())
                .stream().map(mapper::toMedicoEspecialidad).toList();
    }

    @GetMapping("/especialidades-relaciones/{id}")
    public MedicoEspecialidadResponse obtenerMedicoEspecialidad(@PathVariable Long id) {
        return mapper.toMedicoEspecialidad(service.medicoEspecialidad(id));
    }

    @PutMapping("/especialidades-relaciones/{id}")
    public MedicoEspecialidadResponse actualizarMedicoEspecialidad(@PathVariable Long id, @Valid @RequestBody MedicoEspecialidadRequest request) {
        return mapper.toMedicoEspecialidad(service.actualizarMedicoEspecialidad(id, request));
    }

    @DeleteMapping("/especialidades-relaciones/{id}")
    public ResponseEntity<Void> desactivarMedicoEspecialidad(@PathVariable Long id) {
        service.desactivarMedicoEspecialidad(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/especialidades-relaciones/{id}/activar")
    public ResponseEntity<Void> activarMedicoEspecialidad(@PathVariable Long id) {
        service.activarMedicoEspecialidad(id);
        return ResponseEntity.noContent().build();
    }
}
