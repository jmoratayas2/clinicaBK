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

import gt.edu.umg.core.entities.Paciente;
import gt.edu.umg.core.entities.Dtos.Request.PacienteRequestDto;
import gt.edu.umg.core.entities.Dtos.Response.PacienteResponseDto;
import gt.edu.umg.mapper.PacienteMapper;
import gt.edu.umg.service.impl.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pacientes")
@Tag(name = "Pacientes", description = "Endpoints para la gestión de pacientes")
public class PacienteController {

	private final PacienteService pacienteService;
	private final PacienteMapper pacienteMapper;
	
	
	public PacienteController(PacienteService pacienteService, PacienteMapper pacienteMapper) {
		this.pacienteService = pacienteService;
		this.pacienteMapper = pacienteMapper;
	}
	
	@PostMapping
    public ResponseEntity<PacienteResponseDto> crear(@Valid @RequestBody PacienteRequestDto request) {
        Paciente entidad = pacienteMapper.toEntity(request);
        Paciente guardado = pacienteService.guardar(entidad);
        return new ResponseEntity<>(pacienteMapper.toDto(guardado), HttpStatus.CREATED);
    }
	
	@GetMapping
    @Operation(summary = "Obtener lista de todos los pacientes")
    public ResponseEntity<List<PacienteResponseDto>> obtenerTodos(@RequestParam(defaultValue = "false") boolean incluirInactivos) {
        List<PacienteResponseDto> respuesta = pacienteService.obtenerTodos(incluirInactivos)
                .stream()
                .map(pacienteMapper::toDto)
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un paciente por su ID")
    public ResponseEntity<PacienteResponseDto> obtenerPorId(@PathVariable Long id) {
        Paciente paciente = pacienteService.obtenerPorId(id);
        return ResponseEntity.ok(pacienteMapper.toDto(paciente));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un paciente")
    public ResponseEntity<PacienteResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequestDto request) {
        Paciente entidad = pacienteMapper.toEntity(request);
        return ResponseEntity.ok(pacienteMapper.toDto(pacienteService.actualizar(id, entidad)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Desactivar un paciente por su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pacienteService.desactivar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/activar")
    @Operation(summary = "Reactivar un paciente por su ID")
    public ResponseEntity<Void> activar(@PathVariable Long id) {
        pacienteService.activar(id);
        return ResponseEntity.noContent().build();
    }
	
}
