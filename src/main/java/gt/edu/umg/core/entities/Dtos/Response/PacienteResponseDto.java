package gt.edu.umg.core.entities.Dtos.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import gt.edu.umg.core.entities.enums.SexoBiologico;
import gt.edu.umg.core.entities.enums.TipoIdentificacion;

public record PacienteResponseDto(
		Long id,
	    String nombres,
	    String apellidos,
	    TipoIdentificacion tipoIdentificacion,
	    String numeroIdentificacion,
	    SexoBiologico sexoBiologico,
	    String telefono,
	    String correo,
	    String direccion,
	    String contactoEmergencia,
	    String telefonoEmergencia,
	    LocalDate fechaNacimiento,
	    Integer edad,
	    Boolean activo,
	    LocalDateTime creadoEn,
	    LocalDateTime actualizadoEn
		) {}
