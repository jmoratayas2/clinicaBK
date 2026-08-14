package gt.edu.umg.core.entities;

import java.time.LocalDateTime;
import gt.edu.umg.core.entities.enums.EstadoCita;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "citas")
public class Cita extends BaseAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "medico_id", nullable = false)
	private Medico medico;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "clinica_id", nullable = false)
	private Clinica clinica;
	@Column(nullable = false)
	private LocalDateTime fechaHoraInicio;
	@Column(nullable = false)
	private LocalDateTime fechaHoraFin;
	@Column(nullable = false, length = 255)
	private String motivo;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private EstadoCita estado = EstadoCita.PROGRAMADA;
	@Column(columnDefinition = "TEXT")
	private String observaciones;

	public Cita() {
		super();
	}

	public Cita(Long id, Paciente paciente, Medico medico, Clinica clinica, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String motivo, EstadoCita estado, String observaciones) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.clinica = clinica;
		this.fechaHoraInicio = fechaHoraInicio;
		this.fechaHoraFin = fechaHoraFin;
		this.motivo = motivo;
		this.estado = estado;
		this.observaciones = observaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public LocalDateTime getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public LocalDateTime getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public EstadoCita getEstado() {
		return estado;
	}

	public void setEstado(EstadoCita estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
