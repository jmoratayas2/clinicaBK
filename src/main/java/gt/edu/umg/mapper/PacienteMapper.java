package gt.edu.umg.mapper;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import gt.edu.umg.core.entities.Paciente;
import gt.edu.umg.core.entities.Dtos.Request.PacienteRequestDto;
import gt.edu.umg.core.entities.Dtos.Response.PacienteResponseDto;

@Component
public class PacienteMapper {

    public Paciente toEntity(PacienteRequestDto dto) {
        Paciente paciente = new Paciente();
        paciente.setNombres(dto.nombres());
        paciente.setApellidos(dto.apellidos());
        paciente.setTipoIdentificacion(dto.tipoIdentificacion());
        paciente.setNumeroIdentificacion(dto.numeroIdentificacion());
        paciente.setSexoBiologico(dto.sexoBiologico());
        paciente.setTelefono(dto.telefono());
        paciente.setCorreo(dto.correo());
        paciente.setDireccion(dto.direccion());
        paciente.setContactoEmergencia(dto.contactoEmergencia());
        paciente.setTelefonoEmergencia(dto.telefonoEmergencia());
        paciente.setFechaNacimiento(dto.fechaNacimiento());
        paciente.setActivo(true);
        return paciente;
    }

    public PacienteResponseDto toDto(Paciente entity) {
        return new PacienteResponseDto(
                entity.getId(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getTipoIdentificacion(),
                entity.getNumeroIdentificacion(),
                entity.getSexoBiologico(),
                entity.getTelefono(),
                entity.getCorreo(),
                entity.getDireccion(),
                entity.getContactoEmergencia(),
                entity.getTelefonoEmergencia(),
                entity.getFechaNacimiento(),
                calcularEdad(entity.getFechaNacimiento()),
                entity.getActivo(),
                entity.getCreadoEn(),
                entity.getActualizadoEn());
    }

    private Integer calcularEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return null;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
}
