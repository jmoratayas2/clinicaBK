package gt.edu.umg.mapper;

import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Component;

import gt.edu.umg.core.entities.Cita;
import gt.edu.umg.core.entities.Clinica;
import gt.edu.umg.core.entities.Consulta;
import gt.edu.umg.core.entities.DetalleReceta;
import gt.edu.umg.core.entities.Diagnostico;
import gt.edu.umg.core.entities.Especialidad;
import gt.edu.umg.core.entities.Examen;
import gt.edu.umg.core.entities.HistorialMedico;
import gt.edu.umg.core.entities.Medicamento;
import gt.edu.umg.core.entities.Medico;
import gt.edu.umg.core.entities.MedicoClinica;
import gt.edu.umg.core.entities.MedicoEspecialidad;
import gt.edu.umg.core.entities.Paciente;
import gt.edu.umg.core.entities.Receta;
import gt.edu.umg.core.entities.ResultadoExamen;
import gt.edu.umg.core.entities.TipoExamen;
import gt.edu.umg.core.entities.Tratamiento;
import gt.edu.umg.core.entities.Dtos.Response.PacienteResponseDto;
import gt.edu.umg.dto.ClinicDtos.CitaResponse;
import gt.edu.umg.dto.ClinicDtos.ClinicaResponse;
import gt.edu.umg.dto.ClinicDtos.ConsultaResponse;
import gt.edu.umg.dto.ClinicDtos.DetalleRecetaResponse;
import gt.edu.umg.dto.ClinicDtos.DiagnosticoResponse;
import gt.edu.umg.dto.ClinicDtos.EspecialidadResponse;
import gt.edu.umg.dto.ClinicDtos.ExamenResponse;
import gt.edu.umg.dto.ClinicDtos.HistorialMedicoResponse;
import gt.edu.umg.dto.ClinicDtos.MedicamentoResponse;
import gt.edu.umg.dto.ClinicDtos.MedicoClinicaResponse;
import gt.edu.umg.dto.ClinicDtos.MedicoEspecialidadResponse;
import gt.edu.umg.dto.ClinicDtos.MedicoResponse;
import gt.edu.umg.dto.ClinicDtos.RecetaResponse;
import gt.edu.umg.dto.ClinicDtos.ResultadoExamenResponse;
import gt.edu.umg.dto.ClinicDtos.TipoExamenResponse;
import gt.edu.umg.dto.ClinicDtos.TratamientoResponse;

@Component
public class ClinicMapper {
    public PacienteResponseDto toPaciente(Paciente p) {
        Integer edad = p.getFechaNacimiento() == null ? null : Period.between(p.getFechaNacimiento(), java.time.LocalDate.now()).getYears();
        return new PacienteResponseDto(p.getId(), p.getNombres(), p.getApellidos(), p.getTipoIdentificacion(),
                p.getNumeroIdentificacion(), p.getSexoBiologico(), p.getTelefono(), p.getCorreo(), p.getDireccion(),
                p.getContactoEmergencia(), p.getTelefonoEmergencia(), p.getFechaNacimiento(), edad, p.getActivo(),
                p.getCreadoEn(), p.getActualizadoEn());
    }

    public ClinicaResponse toClinica(Clinica c) {
        return new ClinicaResponse(c.getId(), c.getNombre(), c.getDireccion(), c.getTelefono(), c.getCorreo(),
                c.getHorarioAtencion(), c.getActivo(), c.getCreadoEn(), c.getActualizadoEn());
    }

    public MedicoResponse toMedico(Medico m) {
        return new MedicoResponse(m.getId(), m.getNombres(), m.getApellidos(), m.getNumeroColegiado(),
                m.getTelefono(), m.getCorreo(), m.getActivo(), m.getCreadoEn(), m.getActualizadoEn());
    }

    public EspecialidadResponse toEspecialidad(Especialidad e) {
        return new EspecialidadResponse(e.getId(), e.getNombre(), e.getDescripcion(), e.getActivo());
    }

    public MedicoClinicaResponse toMedicoClinica(MedicoClinica mc) {
        return new MedicoClinicaResponse(mc.getId(), mc.getMedico().getId(), nombre(mc.getMedico()),
                mc.getClinica().getId(), mc.getClinica().getNombre(), mc.getConsultorio(), mc.getFechaIngreso(),
                mc.getActivo());
    }

    public MedicoEspecialidadResponse toMedicoEspecialidad(MedicoEspecialidad me) {
        return new MedicoEspecialidadResponse(me.getId(), me.getMedico().getId(), nombre(me.getMedico()),
                me.getEspecialidad().getId(), me.getEspecialidad().getNombre(), me.getPrincipal(),
                me.getNumeroCertificacion(), me.getActivo());
    }

    public HistorialMedicoResponse toHistorial(HistorialMedico h) {
        return new HistorialMedicoResponse(h.getId(), h.getPaciente().getId(), h.getTipoSangre(), h.getAlergias(),
                h.getEnfermedadesCronicas(), h.getAntecedentesPersonales(), h.getAntecedentesFamiliares(),
                h.getCirugiasPrevias(), h.getMedicamentosHabituales(), h.getObservaciones(), h.getActualizadoEn());
    }

    public CitaResponse toCita(Cita c) {
        return new CitaResponse(c.getId(), c.getPaciente().getId(), nombre(c.getPaciente()), c.getMedico().getId(),
                nombre(c.getMedico()), c.getClinica().getId(), c.getClinica().getNombre(), c.getFechaHoraInicio(),
                c.getFechaHoraFin(), c.getMotivo(), c.getEstado(), c.getObservaciones());
    }

    public ConsultaResponse toConsulta(Consulta c) {
        return new ConsultaResponse(c.getId(), c.getCita().getId(), c.getFechaHora(), c.getMotivoConsulta(),
                c.getSintomas(), c.getExamenFisico(), c.getObservaciones(), c.getPlanGeneral());
    }

    public DiagnosticoResponse toDiagnostico(Diagnostico d) {
        return new DiagnosticoResponse(d.getId(), d.getConsulta().getId(), d.getCodigoCie10(), d.getDescripcion(),
                d.getTipo(), d.getPrincipal(), d.getObservaciones());
    }

    public TratamientoResponse toTratamiento(Tratamiento t) {
        return new TratamientoResponse(t.getId(), t.getConsulta().getId(),
                t.getDiagnostico() == null ? null : t.getDiagnostico().getId(), t.getDescripcion(), t.getIndicaciones(),
                t.getFechaInicio(), t.getFechaFin(), t.getEstado(), t.getObservaciones());
    }

    public MedicamentoResponse toMedicamento(Medicamento m) {
        return new MedicamentoResponse(m.getId(), m.getNombreGenerico(), m.getNombreComercial(), m.getPresentacion(),
                m.getConcentracion(), m.getActivo());
    }

    public DetalleRecetaResponse toDetalle(DetalleReceta d) {
        return new DetalleRecetaResponse(d.getId(), d.getMedicamento().getId(), d.getMedicamento().getNombreGenerico(),
                d.getDosis(), d.getFrecuencia(), d.getDuracion(), d.getViaAdministracion(), d.getCantidad(),
                d.getIndicaciones());
    }

    public RecetaResponse toReceta(Receta r, List<DetalleReceta> detalles) {
        return new RecetaResponse(r.getId(), r.getConsulta().getId(), r.getFechaEmision(), r.getIndicacionesGenerales(),
                r.getEstado(), r.getObservaciones(), detalles.stream().map(this::toDetalle).toList());
    }

    public TipoExamenResponse toTipoExamen(TipoExamen t) {
        return new TipoExamenResponse(t.getId(), t.getNombre(), t.getCategoria(), t.getDescripcion(), t.getActivo());
    }

    public ExamenResponse toExamen(Examen e) {
        return new ExamenResponse(e.getId(), e.getConsulta().getId(), e.getTipoExamen().getId(), e.getTipoExamen().getNombre(),
                e.getFechaSolicitud(), e.getPrioridad(), e.getEstado(), e.getIndicaciones());
    }

    public ResultadoExamenResponse toResultado(ResultadoExamen r) {
        return new ResultadoExamenResponse(r.getId(), r.getExamen().getId(), r.getFechaResultado(), r.getResultado(),
                r.getConclusion(), r.getArchivoUrl(), r.getObservaciones());
    }

    private String nombre(Paciente p) {
        return p.getNombres() + " " + p.getApellidos();
    }

    private String nombre(Medico m) {
        return m.getNombres() + " " + m.getApellidos();
    }
}
