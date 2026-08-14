package gt.edu.umg.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import gt.edu.umg.core.entities.Receta;
import gt.edu.umg.core.entities.ResultadoExamen;
import gt.edu.umg.core.entities.TipoExamen;
import gt.edu.umg.core.entities.Tratamiento;
import gt.edu.umg.core.entities.enums.EstadoCita;
import gt.edu.umg.core.entities.enums.EstadoExamen;
import gt.edu.umg.core.entities.enums.EstadoReceta;
import gt.edu.umg.dao.CitaRepository;
import gt.edu.umg.dao.ClinicaRepository;
import gt.edu.umg.dao.ConsultaRepository;
import gt.edu.umg.dao.DetalleRecetaRepository;
import gt.edu.umg.dao.DiagnosticoRepository;
import gt.edu.umg.dao.EspecialidadRepository;
import gt.edu.umg.dao.ExamenRepository;
import gt.edu.umg.dao.HistorialMedicoRepository;
import gt.edu.umg.dao.MedicamentoRepository;
import gt.edu.umg.dao.MedicoClinicaRepository;
import gt.edu.umg.dao.MedicoEspecialidadRepository;
import gt.edu.umg.dao.MedicoRepository;
import gt.edu.umg.dao.PacienteRepository;
import gt.edu.umg.dao.RecetaRepository;
import gt.edu.umg.dao.ResultadoExamenRepository;
import gt.edu.umg.dao.TipoExamenRepository;
import gt.edu.umg.dao.TratamientoRepository;
import gt.edu.umg.dto.ClinicDtos.CitaRequest;
import gt.edu.umg.dto.ClinicDtos.ClinicaRequest;
import gt.edu.umg.dto.ClinicDtos.ConsultaRequest;
import gt.edu.umg.dto.ClinicDtos.DetalleRecetaRequest;
import gt.edu.umg.dto.ClinicDtos.DiagnosticoRequest;
import gt.edu.umg.dto.ClinicDtos.EspecialidadRequest;
import gt.edu.umg.dto.ClinicDtos.ExamenRequest;
import gt.edu.umg.dto.ClinicDtos.HistorialMedicoRequest;
import gt.edu.umg.dto.ClinicDtos.MedicamentoRequest;
import gt.edu.umg.dto.ClinicDtos.MedicoClinicaRequest;
import gt.edu.umg.dto.ClinicDtos.MedicoEspecialidadRequest;
import gt.edu.umg.dto.ClinicDtos.MedicoRequest;
import gt.edu.umg.dto.ClinicDtos.RecetaRequest;
import gt.edu.umg.dto.ClinicDtos.ResultadoExamenRequest;
import gt.edu.umg.dto.ClinicDtos.TipoExamenRequest;
import gt.edu.umg.dto.ClinicDtos.TratamientoRequest;
import gt.edu.umg.exception.BusinessRuleException;
import gt.edu.umg.exception.ResourceNotFoundException;

@Service
public class ClinicDomainService {
    private final PacienteRepository pacienteRepository;
    private final ClinicaRepository clinicaRepository;
    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;
    private final MedicoClinicaRepository medicoClinicaRepository;
    private final MedicoEspecialidadRepository medicoEspecialidadRepository;
    private final HistorialMedicoRepository historialMedicoRepository;
    private final CitaRepository citaRepository;
    private final ConsultaRepository consultaRepository;
    private final DiagnosticoRepository diagnosticoRepository;
    private final TratamientoRepository tratamientoRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final RecetaRepository recetaRepository;
    private final DetalleRecetaRepository detalleRecetaRepository;
    private final TipoExamenRepository tipoExamenRepository;
    private final ExamenRepository examenRepository;
    private final ResultadoExamenRepository resultadoExamenRepository;

    public ClinicDomainService(PacienteRepository pacienteRepository, ClinicaRepository clinicaRepository,
                               MedicoRepository medicoRepository, EspecialidadRepository especialidadRepository,
                               MedicoClinicaRepository medicoClinicaRepository,
                               MedicoEspecialidadRepository medicoEspecialidadRepository,
                               HistorialMedicoRepository historialMedicoRepository, CitaRepository citaRepository,
                               ConsultaRepository consultaRepository, DiagnosticoRepository diagnosticoRepository,
                               TratamientoRepository tratamientoRepository, MedicamentoRepository medicamentoRepository,
                               RecetaRepository recetaRepository, DetalleRecetaRepository detalleRecetaRepository,
                               TipoExamenRepository tipoExamenRepository, ExamenRepository examenRepository,
                               ResultadoExamenRepository resultadoExamenRepository) {
        this.pacienteRepository = pacienteRepository;
        this.clinicaRepository = clinicaRepository;
        this.medicoRepository = medicoRepository;
        this.especialidadRepository = especialidadRepository;
        this.medicoClinicaRepository = medicoClinicaRepository;
        this.medicoEspecialidadRepository = medicoEspecialidadRepository;
        this.historialMedicoRepository = historialMedicoRepository;
        this.citaRepository = citaRepository;
        this.consultaRepository = consultaRepository;
        this.diagnosticoRepository = diagnosticoRepository;
        this.tratamientoRepository = tratamientoRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.recetaRepository = recetaRepository;
        this.detalleRecetaRepository = detalleRecetaRepository;
        this.tipoExamenRepository = tipoExamenRepository;
        this.examenRepository = examenRepository;
        this.resultadoExamenRepository = resultadoExamenRepository;
    }

    @Transactional
    public Clinica crearClinica(ClinicaRequest request) {
        Clinica c = new Clinica();
        c.setNombre(request.nombre());
        c.setDireccion(request.direccion());
        c.setTelefono(request.telefono());
        c.setCorreo(request.correo());
        c.setHorarioAtencion(request.horarioAtencion());
        return clinicaRepository.guardar(c);
    }

    @Transactional
    public void desactivarClinica(Long id) {
        clinica(id).setActivo(false);
    }

    @Transactional
    public void activarClinica(Long id) {
        clinica(id).setActivo(true);
    }

    @Transactional
    public Clinica actualizarClinica(Long id, ClinicaRequest request) {
        Clinica c = clinica(id);
        c.setNombre(request.nombre());
        c.setDireccion(request.direccion());
        c.setTelefono(request.telefono());
        c.setCorreo(request.correo());
        c.setHorarioAtencion(request.horarioAtencion());
        return c;
    }

    @Transactional
    public Medico crearMedico(MedicoRequest request) {
        if (medicoRepository.existsByNumeroColegiado(request.numeroColegiado())) throw new BusinessRuleException("Ya existe un medico con ese numero colegiado");
        if (request.correo() != null && medicoRepository.existsByCorreo(request.correo())) throw new BusinessRuleException("Ya existe un medico con ese correo");
        Medico m = new Medico();
        m.setNombres(request.nombres());
        m.setApellidos(request.apellidos());
        m.setNumeroColegiado(request.numeroColegiado());
        m.setTelefono(request.telefono());
        m.setCorreo(request.correo());
        return medicoRepository.guardar(m);
    }

    @Transactional
    public void desactivarMedico(Long id) {
        medico(id).setActivo(false);
    }

    @Transactional
    public void activarMedico(Long id) {
        medico(id).setActivo(true);
    }

    @Transactional
    public Medico actualizarMedico(Long id, MedicoRequest request) {
        if (medicoRepository.existsByNumeroColegiadoAndIdNot(request.numeroColegiado(), id)) throw new BusinessRuleException("Ya existe un medico con ese numero colegiado");
        if (request.correo() != null && medicoRepository.existsByCorreoAndIdNot(request.correo(), id)) throw new BusinessRuleException("Ya existe un medico con ese correo");
        Medico m = medico(id);
        m.setNombres(request.nombres());
        m.setApellidos(request.apellidos());
        m.setNumeroColegiado(request.numeroColegiado());
        m.setTelefono(request.telefono());
        m.setCorreo(request.correo());
        return m;
    }

    @Transactional
    public Especialidad crearEspecialidad(EspecialidadRequest request) {
        if (especialidadRepository.existsByNombre(request.nombre())) throw new BusinessRuleException("Ya existe una especialidad con ese nombre");
        Especialidad e = new Especialidad();
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        return especialidadRepository.guardar(e);
    }

    @Transactional
    public void desactivarEspecialidad(Long id) {
        especialidad(id).setActivo(false);
    }

    @Transactional
    public void activarEspecialidad(Long id) {
        especialidad(id).setActivo(true);
    }

    @Transactional
    public Especialidad actualizarEspecialidad(Long id, EspecialidadRequest request) {
        if (especialidadRepository.existsByNombreAndIdNot(request.nombre(), id)) throw new BusinessRuleException("Ya existe una especialidad con ese nombre");
        Especialidad e = especialidad(id);
        e.setNombre(request.nombre());
        e.setDescripcion(request.descripcion());
        return e;
    }

    @Transactional
    public MedicoClinica asignarMedicoClinica(MedicoClinicaRequest request) {
        if (medicoClinicaRepository.existsByMedicoIdAndClinicaId(request.medicoId(), request.clinicaId())) throw new BusinessRuleException("El medico ya esta asociado a esa clinica");
        MedicoClinica mc = new MedicoClinica();
        mc.setMedico(medicoActivo(request.medicoId()));
        mc.setClinica(clinicaActiva(request.clinicaId()));
        mc.setConsultorio(request.consultorio());
        mc.setFechaIngreso(request.fechaIngreso());
        return medicoClinicaRepository.guardar(mc);
    }

    @Transactional
    public MedicoClinica actualizarMedicoClinica(Long id, MedicoClinicaRequest request) {
        MedicoClinica mc = medicoClinica(id);
        if (!mc.getClinica().getId().equals(request.clinicaId()) || !mc.getMedico().getId().equals(request.medicoId())) {
            if (medicoClinicaRepository.existsByMedicoIdAndClinicaId(request.medicoId(), request.clinicaId())) throw new BusinessRuleException("El medico ya esta asociado a esa clinica");
            mc.setMedico(medicoActivo(request.medicoId()));
            mc.setClinica(clinicaActiva(request.clinicaId()));
        }
        mc.setConsultorio(request.consultorio());
        mc.setFechaIngreso(request.fechaIngreso());
        return mc;
    }

    @Transactional
    public void desactivarMedicoClinica(Long id) { medicoClinica(id).setActivo(false); }

    @Transactional
    public void activarMedicoClinica(Long id) { medicoClinica(id).setActivo(true); }

    @Transactional
    public MedicoEspecialidad asignarMedicoEspecialidad(MedicoEspecialidadRequest request) {
        if (medicoEspecialidadRepository.existsByMedicoIdAndEspecialidadId(request.medicoId(), request.especialidadId())) throw new BusinessRuleException("El medico ya esta asociado a esa especialidad");
        boolean principal = Boolean.TRUE.equals(request.principal());
        if (principal && medicoEspecialidadRepository.existsByMedicoIdAndPrincipalTrueAndActivoTrue(request.medicoId())) throw new BusinessRuleException("El medico ya tiene una especialidad principal activa");
        MedicoEspecialidad me = new MedicoEspecialidad();
        me.setMedico(medicoActivo(request.medicoId()));
        me.setEspecialidad(especialidadActiva(request.especialidadId()));
        me.setPrincipal(principal);
        me.setNumeroCertificacion(request.numeroCertificacion());
        return medicoEspecialidadRepository.guardar(me);
    }

    @Transactional
    public MedicoEspecialidad actualizarMedicoEspecialidad(Long id, MedicoEspecialidadRequest request) {
        MedicoEspecialidad me = medicoEspecialidad(id);
        boolean principal = Boolean.TRUE.equals(request.principal());
        if (principal && medicoEspecialidadRepository.existsByMedicoIdAndPrincipalTrueAndActivoTrueAndIdNot(request.medicoId(), id)) throw new BusinessRuleException("El medico ya tiene una especialidad principal activa");
        if (!me.getMedico().getId().equals(request.medicoId()) || !me.getEspecialidad().getId().equals(request.especialidadId())) {
            if (medicoEspecialidadRepository.existsByMedicoIdAndEspecialidadId(request.medicoId(), request.especialidadId())) throw new BusinessRuleException("El medico ya esta asociado a esa especialidad");
            me.setMedico(medicoActivo(request.medicoId()));
            me.setEspecialidad(especialidadActiva(request.especialidadId()));
        }
        me.setPrincipal(principal);
        me.setNumeroCertificacion(request.numeroCertificacion());
        return me;
    }

    @Transactional
    public void desactivarMedicoEspecialidad(Long id) { medicoEspecialidad(id).setActivo(false); }

    @Transactional
    public void activarMedicoEspecialidad(Long id) { medicoEspecialidad(id).setActivo(true); }

    @Transactional
    public HistorialMedico guardarHistorial(Long pacienteId, HistorialMedicoRequest request) {
        HistorialMedico h = historialMedicoRepository.findByPacienteId(pacienteId).orElseGet(() -> {
            HistorialMedico nuevo = new HistorialMedico();
            nuevo.setPaciente(paciente(pacienteId));
            return nuevo;
        });
        h.setTipoSangre(request.tipoSangre());
        h.setAlergias(request.alergias());
        h.setEnfermedadesCronicas(request.enfermedadesCronicas());
        h.setAntecedentesPersonales(request.antecedentesPersonales());
        h.setAntecedentesFamiliares(request.antecedentesFamiliares());
        h.setCirugiasPrevias(request.cirugiasPrevias());
        h.setMedicamentosHabituales(request.medicamentosHabituales());
        h.setObservaciones(request.observaciones());
        return historialMedicoRepository.guardar(h);
    }

    @Transactional
    public Cita crearCita(CitaRequest request) {
        if (!request.fechaHoraFin().isAfter(request.fechaHoraInicio())) throw new BusinessRuleException("La fecha final debe ser posterior a la fecha inicial");
        if (!medicoClinicaRepository.existsByMedicoIdAndClinicaIdAndActivoTrue(request.medicoId(), request.clinicaId())) throw new BusinessRuleException("El medico no esta activo en la clinica seleccionada");
        if (citaRepository.existsSolapamiento(request.medicoId(), request.fechaHoraInicio(), request.fechaHoraFin())) throw new BusinessRuleException("Ya existe una cita solapada para el medico");
        Cita c = new Cita();
        c.setPaciente(pacienteActivo(request.pacienteId()));
        c.setMedico(medicoActivo(request.medicoId()));
        c.setClinica(clinicaActiva(request.clinicaId()));
        c.setFechaHoraInicio(request.fechaHoraInicio());
        c.setFechaHoraFin(request.fechaHoraFin());
        c.setMotivo(request.motivo());
        c.setObservaciones(request.observaciones());
        return citaRepository.guardar(c);
    }

    @Transactional
    public Cita cambiarEstadoCita(Long id, EstadoCita estado) {
        Cita c = cita(id);
        c.setEstado(estado);
        return c;
    }

    @Transactional
    public Cita actualizarCita(Long id, CitaRequest request) {
        if (!request.fechaHoraFin().isAfter(request.fechaHoraInicio())) throw new BusinessRuleException("La fecha final debe ser posterior a la fecha inicial");
        if (!medicoClinicaRepository.existsByMedicoIdAndClinicaIdAndActivoTrue(request.medicoId(), request.clinicaId())) throw new BusinessRuleException("El medico no esta activo en la clinica seleccionada");
        if (citaRepository.existsSolapamientoExcluyendo(id, request.medicoId(), request.fechaHoraInicio(), request.fechaHoraFin())) throw new BusinessRuleException("Ya existe una cita solapada para el medico");
        Cita c = cita(id);
        c.setPaciente(pacienteActivo(request.pacienteId()));
        c.setMedico(medicoActivo(request.medicoId()));
        c.setClinica(clinicaActiva(request.clinicaId()));
        c.setFechaHoraInicio(request.fechaHoraInicio());
        c.setFechaHoraFin(request.fechaHoraFin());
        c.setMotivo(request.motivo());
        c.setObservaciones(request.observaciones());
        return c;
    }

    @Transactional
    public Consulta crearConsulta(ConsultaRequest request) {
        Cita cita = cita(request.citaId());
        if (cita.getEstado() == EstadoCita.CANCELADA) throw new BusinessRuleException("Una cita cancelada no puede generar consulta");
        if (consultaRepository.existsByCitaId(request.citaId())) throw new BusinessRuleException("La cita ya tiene una consulta");
        if (cita.getEstado() != EstadoCita.ATENDIDA) cita.setEstado(EstadoCita.ATENDIDA);
        Consulta c = new Consulta();
        c.setCita(cita);
        c.setFechaHora(request.fechaHora());
        c.setMotivoConsulta(request.motivoConsulta());
        c.setSintomas(request.sintomas());
        c.setExamenFisico(request.examenFisico());
        c.setObservaciones(request.observaciones());
        c.setPlanGeneral(request.planGeneral());
        return consultaRepository.guardar(c);
    }

    @Transactional
    public Consulta actualizarConsulta(Long id, ConsultaRequest request) {
        Consulta c = consulta(id);
        c.setFechaHora(request.fechaHora());
        c.setMotivoConsulta(request.motivoConsulta());
        c.setSintomas(request.sintomas());
        c.setExamenFisico(request.examenFisico());
        c.setObservaciones(request.observaciones());
        c.setPlanGeneral(request.planGeneral());
        return c;
    }

    @Transactional
    public Diagnostico crearDiagnostico(Long consultaId, DiagnosticoRequest request) {
        boolean principal = Boolean.TRUE.equals(request.principal());
        if (principal && diagnosticoRepository.existsByConsultaIdAndPrincipalTrue(consultaId)) throw new BusinessRuleException("La consulta ya tiene un diagnostico principal");
        Diagnostico d = new Diagnostico();
        d.setConsulta(consulta(consultaId));
        d.setCodigoCie10(request.codigoCie10());
        d.setDescripcion(request.descripcion());
        d.setTipo(request.tipo());
        d.setPrincipal(principal);
        d.setObservaciones(request.observaciones());
        return diagnosticoRepository.guardar(d);
    }

    @Transactional
    public Diagnostico actualizarDiagnostico(Long id, DiagnosticoRequest request) {
        Diagnostico d = diagnostico(id);
        boolean principal = Boolean.TRUE.equals(request.principal());
        if (principal && !Boolean.TRUE.equals(d.getPrincipal()) && diagnosticoRepository.existsByConsultaIdAndPrincipalTrue(d.getConsulta().getId())) throw new BusinessRuleException("La consulta ya tiene un diagnostico principal");
        d.setCodigoCie10(request.codigoCie10());
        d.setDescripcion(request.descripcion());
        d.setTipo(request.tipo());
        d.setPrincipal(principal);
        d.setObservaciones(request.observaciones());
        return d;
    }

    @Transactional
    public Tratamiento crearTratamiento(Long consultaId, TratamientoRequest request) {
        if (request.fechaFin() != null && request.fechaFin().isBefore(request.fechaInicio())) throw new BusinessRuleException("La fecha fin del tratamiento no puede ser anterior a la fecha inicio");
        Tratamiento t = new Tratamiento();
        Consulta consulta = consulta(consultaId);
        t.setConsulta(consulta);
        if (request.diagnosticoId() != null) {
            Diagnostico diagnostico = diagnostico(request.diagnosticoId());
            if (!diagnostico.getConsulta().getId().equals(consultaId)) throw new BusinessRuleException("El diagnostico no pertenece a la consulta");
            t.setDiagnostico(diagnostico);
        }
        t.setDescripcion(request.descripcion());
        t.setIndicaciones(request.indicaciones());
        t.setFechaInicio(request.fechaInicio());
        t.setFechaFin(request.fechaFin());
        t.setEstado(request.estado());
        t.setObservaciones(request.observaciones());
        return tratamientoRepository.guardar(t);
    }

    @Transactional
    public Tratamiento actualizarTratamiento(Long id, TratamientoRequest request) {
        if (request.fechaFin() != null && request.fechaFin().isBefore(request.fechaInicio())) throw new BusinessRuleException("La fecha fin del tratamiento no puede ser anterior a la fecha inicio");
        Tratamiento t = tratamiento(id);
        if (request.diagnosticoId() == null) {
            t.setDiagnostico(null);
        } else {
            Diagnostico d = diagnostico(request.diagnosticoId());
            if (!d.getConsulta().getId().equals(t.getConsulta().getId())) throw new BusinessRuleException("El diagnostico no pertenece a la consulta");
            t.setDiagnostico(d);
        }
        t.setDescripcion(request.descripcion());
        t.setIndicaciones(request.indicaciones());
        t.setFechaInicio(request.fechaInicio());
        t.setFechaFin(request.fechaFin());
        t.setEstado(request.estado());
        t.setObservaciones(request.observaciones());
        return t;
    }

    @Transactional
    public Medicamento crearMedicamento(MedicamentoRequest request) {
        Medicamento m = new Medicamento();
        m.setNombreGenerico(request.nombreGenerico());
        m.setNombreComercial(request.nombreComercial());
        m.setPresentacion(request.presentacion());
        m.setConcentracion(request.concentracion());
        return medicamentoRepository.guardar(m);
    }

    @Transactional
    public void desactivarMedicamento(Long id) {
        medicamento(id).setActivo(false);
    }

    @Transactional
    public void activarMedicamento(Long id) { medicamento(id).setActivo(true); }

    @Transactional
    public Medicamento actualizarMedicamento(Long id, MedicamentoRequest request) {
        Medicamento m = medicamento(id);
        m.setNombreGenerico(request.nombreGenerico());
        m.setNombreComercial(request.nombreComercial());
        m.setPresentacion(request.presentacion());
        m.setConcentracion(request.concentracion());
        return m;
    }

    @Transactional
    public Receta crearReceta(Long consultaId, RecetaRequest request) {
        Receta r = new Receta();
        r.setConsulta(consulta(consultaId));
        r.setFechaEmision(LocalDateTime.now());
        r.setIndicacionesGenerales(request.indicacionesGenerales());
        r.setObservaciones(request.observaciones());
        r.setEstado(EstadoReceta.EMITIDA);
        Receta guardada = recetaRepository.guardar(r);
        for (DetalleRecetaRequest detalle : request.detalles()) {
            Medicamento medicamento = medicamentoActivo(detalle.medicamentoId());
            DetalleReceta d = new DetalleReceta();
            d.setReceta(guardada);
            d.setMedicamento(medicamento);
            d.setDosis(detalle.dosis());
            d.setFrecuencia(detalle.frecuencia());
            d.setDuracion(detalle.duracion());
            d.setViaAdministracion(detalle.viaAdministracion());
            d.setCantidad(detalle.cantidad());
            d.setIndicaciones(detalle.indicaciones());
            detalleRecetaRepository.guardar(d);
        }
        return guardada;
    }

    @Transactional
    public Receta anularReceta(Long id) {
        Receta r = receta(id);
        r.setEstado(EstadoReceta.ANULADA);
        return r;
    }

    @Transactional
    public TipoExamen crearTipoExamen(TipoExamenRequest request) {
        if (tipoExamenRepository.existsByNombre(request.nombre())) throw new BusinessRuleException("Ya existe un tipo de examen con ese nombre");
        TipoExamen t = new TipoExamen();
        t.setNombre(request.nombre());
        t.setCategoria(request.categoria());
        t.setDescripcion(request.descripcion());
        return tipoExamenRepository.guardar(t);
    }

    @Transactional
    public TipoExamen actualizarTipoExamen(Long id, TipoExamenRequest request) {
        if (tipoExamenRepository.existsByNombreAndIdNot(request.nombre(), id)) throw new BusinessRuleException("Ya existe un tipo de examen con ese nombre");
        TipoExamen t = tipoExamen(id);
        t.setNombre(request.nombre());
        t.setCategoria(request.categoria());
        t.setDescripcion(request.descripcion());
        return t;
    }

    @Transactional
    public void desactivarTipoExamen(Long id) { tipoExamen(id).setActivo(false); }

    @Transactional
    public void activarTipoExamen(Long id) { tipoExamen(id).setActivo(true); }

    @Transactional
    public Examen crearExamen(Long consultaId, ExamenRequest request) {
        TipoExamen tipo = tipoExamen(request.tipoExamenId());
        if (!Boolean.TRUE.equals(tipo.getActivo())) throw new BusinessRuleException("El tipo de examen esta inactivo");
        Examen e = new Examen();
        e.setConsulta(consulta(consultaId));
        e.setTipoExamen(tipo);
        e.setFechaSolicitud(LocalDateTime.now());
        e.setPrioridad(request.prioridad());
        e.setIndicaciones(request.indicaciones());
        return examenRepository.guardar(e);
    }

    @Transactional
    public Examen cambiarEstadoExamen(Long id, EstadoExamen estado) {
        Examen e = examen(id);
        if (e.getEstado() == EstadoExamen.RESULTADO_DISPONIBLE && estado == EstadoExamen.CANCELADO) throw new BusinessRuleException("No se puede cancelar un examen con resultado disponible");
        e.setEstado(estado);
        return e;
    }

    @Transactional
    public ResultadoExamen registrarResultado(Long examenId, ResultadoExamenRequest request) {
        Examen examen = examen(examenId);
        if (examen.getEstado() == EstadoExamen.CANCELADO) throw new BusinessRuleException("No se puede registrar resultado para un examen cancelado");
        if (resultadoExamenRepository.existsByExamenId(examenId)) throw new BusinessRuleException("El examen ya tiene resultado");
        ResultadoExamen r = new ResultadoExamen();
        r.setExamen(examen);
        r.setFechaResultado(request.fechaResultado());
        r.setResultado(request.resultado());
        r.setConclusion(request.conclusion());
        r.setArchivoUrl(request.archivoUrl());
        r.setObservaciones(request.observaciones());
        examen.setEstado(EstadoExamen.RESULTADO_DISPONIBLE);
        return resultadoExamenRepository.guardar(r);
    }

    @Transactional
    public ResultadoExamen actualizarResultado(Long id, ResultadoExamenRequest request) {
        ResultadoExamen r = resultado(id);
        r.setFechaResultado(request.fechaResultado());
        r.setResultado(request.resultado());
        r.setConclusion(request.conclusion());
        r.setArchivoUrl(request.archivoUrl());
        r.setObservaciones(request.observaciones());
        return r;
    }

    @Transactional
    public Examen cancelarExamen(Long id) {
        Examen examen = examen(id);
        if (examen.getEstado() == EstadoExamen.RESULTADO_DISPONIBLE) throw new BusinessRuleException("No se puede cancelar un examen con resultado disponible");
        examen.setEstado(EstadoExamen.CANCELADO);
        return examen;
    }

    public Clinica clinica(Long id) { return clinicaRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Clinica no encontrada: " + id)); }
    public Medico medico(Long id) { return medicoRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado: " + id)); }
    public Especialidad especialidad(Long id) { return especialidadRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada: " + id)); }
    public Cita cita(Long id) { return citaRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada: " + id)); }
    public Consulta consulta(Long id) { return consultaRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada: " + id)); }
    public Diagnostico diagnostico(Long id) { return diagnosticoRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Diagnostico no encontrado: " + id)); }
    public Tratamiento tratamiento(Long id) { return tratamientoRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Tratamiento no encontrado: " + id)); }
    public Medicamento medicamento(Long id) { return medicamentoRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Medicamento no encontrado: " + id)); }
    public Receta receta(Long id) { return recetaRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Receta no encontrada: " + id)); }
    public TipoExamen tipoExamen(Long id) { return tipoExamenRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Tipo de examen no encontrado: " + id)); }
    public Examen examen(Long id) { return examenRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Examen no encontrado: " + id)); }
    public ResultadoExamen resultado(Long id) { return resultadoExamenRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Resultado de examen no encontrado: " + id)); }
    public ResultadoExamen resultadoPorExamen(Long examenId) { return resultadoExamenRepository.findByExamenId(examenId).orElseThrow(() -> new ResourceNotFoundException("Resultado no encontrado para examen: " + examenId)); }
    public MedicoClinica medicoClinica(Long id) { return medicoClinicaRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Relacion medico-clinica no encontrada: " + id)); }
    public MedicoEspecialidad medicoEspecialidad(Long id) { return medicoEspecialidadRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Relacion medico-especialidad no encontrada: " + id)); }

    public List<DetalleReceta> detallesReceta(Long recetaId) { return detalleRecetaRepository.findByRecetaId(recetaId); }
    public DetalleReceta detalleReceta(Long id) { return detalleRecetaRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Detalle de receta no encontrado: " + id)); }
    public HistorialMedico historial(Long pacienteId) { return historialMedicoRepository.findByPacienteId(pacienteId).orElseThrow(() -> new ResourceNotFoundException("Historial no encontrado para paciente: " + pacienteId)); }
    public HistorialMedico historialPorId(Long id) { return historialMedicoRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Historial no encontrado: " + id)); }

    private gt.edu.umg.core.entities.Paciente paciente(Long id) { return pacienteRepository.obtenerPorId(id).orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado: " + id)); }
    private gt.edu.umg.core.entities.Paciente pacienteActivo(Long id) { gt.edu.umg.core.entities.Paciente p = paciente(id); if (!Boolean.TRUE.equals(p.getActivo())) throw new BusinessRuleException("El paciente esta inactivo"); return p; }
    private Medico medicoActivo(Long id) { Medico m = medico(id); if (!Boolean.TRUE.equals(m.getActivo())) throw new BusinessRuleException("El medico esta inactivo"); return m; }
    private Clinica clinicaActiva(Long id) { Clinica c = clinica(id); if (!Boolean.TRUE.equals(c.getActivo())) throw new BusinessRuleException("La clinica esta inactiva"); return c; }
    private Especialidad especialidadActiva(Long id) { Especialidad e = especialidad(id); if (!Boolean.TRUE.equals(e.getActivo())) throw new BusinessRuleException("La especialidad esta inactiva"); return e; }
    private Medicamento medicamentoActivo(Long id) { Medicamento m = medicamento(id); if (!Boolean.TRUE.equals(m.getActivo())) throw new BusinessRuleException("El medicamento esta inactivo"); return m; }
}
