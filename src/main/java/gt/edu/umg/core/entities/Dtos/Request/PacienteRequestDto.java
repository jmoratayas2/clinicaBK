package gt.edu.umg.core.entities.Dtos.Request;

import java.time.LocalDate;

import gt.edu.umg.core.entities.enums.SexoBiologico;
import gt.edu.umg.core.entities.enums.TipoIdentificacion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record PacienteRequestDto(
		@NotBlank(message = "Los nombres son obligatorios")
	    @Size(min = 2, max = 80, message = "Los nombres deben tener entre 2 y 80 caracteres")
	    String nombres,

	    @NotBlank(message = "Los apellidos son obligatorios")
	    @Size(min = 2, max = 80, message = "Los apellidos deben tener entre 2 y 80 caracteres")
	    String apellidos,

	    TipoIdentificacion tipoIdentificacion,
	    @Size(max = 40, message = "El numero de identificacion no debe exceder 40 caracteres")
	    String numeroIdentificacion,

	    SexoBiologico sexoBiologico,

	    @Size(max = 20, message = "El telefono no debe exceder 20 caracteres")
	    String telefono,

	    @Email(message = "Debe ser una dirección de correo válida")
	    String correo,

	    @Size(max = 255, message = "La direccion no debe exceder 255 caracteres")
	    String direccion,

	    @Size(max = 120, message = "El contacto de emergencia no debe exceder 120 caracteres")
	    String contactoEmergencia,

	    @Size(max = 20, message = "El telefono de emergencia no debe exceder 20 caracteres")
	    String telefonoEmergencia,

	    @NotNull(message = "La fecha de nacimiento es obligatoria")
	    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
	    LocalDate fechaNacimiento
	
		) {}
