package gt.edu.umg.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import gt.edu.umg.core.entities.enums.EstadoCita;
import gt.edu.umg.core.entities.enums.EstadoExamen;
import gt.edu.umg.core.entities.enums.EstadoReceta;
import gt.edu.umg.core.entities.enums.EstadoTratamiento;
import gt.edu.umg.core.entities.enums.PrioridadExamen;
import gt.edu.umg.core.entities.enums.TipoDiagnostico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public final class ClinicDtos {
    private ClinicDtos() {
    }

    public record ClinicaRequest(@NotBlank String nombre, @NotBlank String direccion, @NotBlank String telefono,
                                 String correo, String horarioAtencion) {}
    public record ClinicaResponse(Long id, String nombre, String direccion, String telefono, String correo,
                                  String horarioAtencion, Boolean activo, LocalDateTime creadoEn, LocalDateTime actualizadoEn) {}

    public record MedicoRequest(@NotBlank String nombres, @NotBlank String apellidos, @NotBlank String numeroColegiado,
                                String telefono, String correo) {}
    public record MedicoResponse(Long id, String nombres, String apellidos, String numeroColegiado, String telefono,
                                 String correo, Boolean activo, LocalDateTime creadoEn, LocalDateTime actualizadoEn) {}

    public record EspecialidadRequest(@NotBlank String nombre, String descripcion) {}
    public record EspecialidadResponse(Long id, String nombre, String descripcion, Boolean activo) {}

    public record MedicoClinicaRequest(Long medicoId, @NotNull Long clinicaId, String consultorio,
                                       LocalDate fechaIngreso) {}
    public record MedicoClinicaResponse(Long id, Long medicoId, String medico, Long clinicaId, String clinica,
                                        String consultorio, LocalDate fechaIngreso, Boolean activo) {}

    public record MedicoEspecialidadRequest(Long medicoId, @NotNull Long especialidadId, Boolean principal,
                                            String numeroCertificacion) {}
    public record MedicoEspecialidadResponse(Long id, Long medicoId, String medico, Long especialidadId,
                                             String especialidad, Boolean principal, String numeroCertificacion,
                                             Boolean activo) {}

    public record HistorialMedicoRequest(String tipoSangre, String alergias, String enfermedadesCronicas,
                                         String antecedentesPersonales, String antecedentesFamiliares,
                                         String cirugiasPrevias, String medicamentosHabituales, String observaciones) {}
    public record HistorialMedicoResponse(Long id, Long pacienteId, String tipoSangre, String alergias,
                                          String enfermedadesCronicas, String antecedentesPersonales,
                                          String antecedentesFamiliares, String cirugiasPrevias,
                                          String medicamentosHabituales, String observaciones,
                                          LocalDateTime actualizadoEn) {}

    public record CitaRequest(@NotNull Long pacienteId, @NotNull Long medicoId, @NotNull Long clinicaId,
                              @NotNull LocalDateTime fechaHoraInicio, @NotNull LocalDateTime fechaHoraFin,
                              @NotBlank String motivo, String observaciones) {}
    public record CitaEstadoRequest(@NotNull EstadoCita estado) {}
    public record CitaResponse(Long id, Long pacienteId, String paciente, Long medicoId, String medico,
                               Long clinicaId, String clinica, LocalDateTime fechaHoraInicio,
                               LocalDateTime fechaHoraFin, String motivo, EstadoCita estado, String observaciones) {}

    public record ConsultaRequest(Long citaId, @NotNull LocalDateTime fechaHora,
                                  @NotBlank String motivoConsulta, String sintomas, String examenFisico,
                                  String observaciones, String planGeneral) {}
    public record ConsultaResponse(Long id, Long citaId, LocalDateTime fechaHora, String motivoConsulta,
                                   String sintomas, String examenFisico, String observaciones, String planGeneral) {}

    public record DiagnosticoRequest(@NotBlank String descripcion, String codigoCie10, @NotNull TipoDiagnostico tipo,
                                     Boolean principal, String observaciones) {}
    public record DiagnosticoResponse(Long id, Long consultaId, String codigoCie10, String descripcion,
                                      TipoDiagnostico tipo, Boolean principal, String observaciones) {}

    public record TratamientoRequest(Long diagnosticoId, @NotBlank String descripcion, @NotBlank String indicaciones,
                                     @NotNull LocalDate fechaInicio, LocalDate fechaFin,
                                     @NotNull EstadoTratamiento estado, String observaciones) {}
    public record TratamientoResponse(Long id, Long consultaId, Long diagnosticoId, String descripcion,
                                      String indicaciones, LocalDate fechaInicio, LocalDate fechaFin,
                                      EstadoTratamiento estado, String observaciones) {}

    public record MedicamentoRequest(@NotBlank String nombreGenerico, String nombreComercial,
                                     @NotBlank String presentacion, @NotBlank String concentracion) {}
    public record MedicamentoResponse(Long id, String nombreGenerico, String nombreComercial, String presentacion,
                                      String concentracion, Boolean activo) {}

    public record DetalleRecetaRequest(@NotNull Long medicamentoId, @NotBlank String dosis, @NotBlank String frecuencia,
                                       @NotBlank String duracion, @NotBlank String viaAdministracion,
                                       String cantidad, String indicaciones) {}
    public record RecetaRequest(String indicacionesGenerales, String observaciones,
                                @Valid @NotEmpty List<DetalleRecetaRequest> detalles) {}
    public record DetalleRecetaResponse(Long id, Long medicamentoId, String medicamento, String dosis,
                                        String frecuencia, String duracion, String viaAdministracion,
                                        String cantidad, String indicaciones) {}
    public record RecetaResponse(Long id, Long consultaId, LocalDateTime fechaEmision,
                                 String indicacionesGenerales, EstadoReceta estado, String observaciones,
                                 List<DetalleRecetaResponse> detalles) {}

    public record TipoExamenRequest(@NotBlank String nombre, @NotBlank String categoria, String descripcion) {}
    public record TipoExamenResponse(Long id, String nombre, String categoria, String descripcion, Boolean activo) {}

    public record ExamenRequest(@NotNull Long tipoExamenId, @NotNull PrioridadExamen prioridad, String indicaciones) {}
    public record ExamenEstadoRequest(@NotNull EstadoExamen estado) {}
    public record ExamenResponse(Long id, Long consultaId, Long tipoExamenId, String tipoExamen,
                                 LocalDateTime fechaSolicitud, PrioridadExamen prioridad, EstadoExamen estado,
                                 String indicaciones) {}

    public record ResultadoExamenRequest(@NotNull LocalDateTime fechaResultado, @NotBlank String resultado,
                                         @NotBlank String conclusion, String archivoUrl, String observaciones) {}
    public record ResultadoExamenResponse(Long id, Long examenId, LocalDateTime fechaResultado, String resultado,
                                          String conclusion, String archivoUrl, String observaciones) {}
}
