package gt.edu.umg.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.edu.umg.dao.HistorialMedicoRepository;
import gt.edu.umg.dto.ClinicDtos.HistorialMedicoRequest;
import gt.edu.umg.dto.ClinicDtos.HistorialMedicoResponse;
import gt.edu.umg.mapper.ClinicMapper;
import gt.edu.umg.service.impl.ClinicDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class HistorialMedicoController {
    private final ClinicDomainService service;
    private final ClinicMapper mapper;
    private final HistorialMedicoRepository historialMedicoRepository;

    public HistorialMedicoController(ClinicDomainService service, ClinicMapper mapper,
                                     HistorialMedicoRepository historialMedicoRepository) {
        this.service = service;
        this.mapper = mapper;
        this.historialMedicoRepository = historialMedicoRepository;
    }

    @GetMapping("/pacientes/{pacienteId}/historial")
    public HistorialMedicoResponse obtener(@PathVariable Long pacienteId) {
        return mapper.toHistorial(service.historial(pacienteId));
    }

    @PutMapping("/pacientes/{pacienteId}/historial")
    public HistorialMedicoResponse guardar(@PathVariable Long pacienteId, @Valid @RequestBody HistorialMedicoRequest request) {
        return mapper.toHistorial(service.guardarHistorial(pacienteId, request));
    }

    @GetMapping("/historiales-medicos")
    public List<HistorialMedicoResponse> listar() {
        return historialMedicoRepository.findAll().stream().map(mapper::toHistorial).toList();
    }

    @GetMapping("/historiales-medicos/{id}")
    public HistorialMedicoResponse obtenerPorId(@PathVariable Long id) {
        return mapper.toHistorial(service.historialPorId(id));
    }
}
