package gt.edu.umg.core.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultas")
public class Consulta extends BaseAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cita_id", nullable = false, unique = true)
	private Cita cita;
	@Column(nullable = false)
	private LocalDateTime fechaHora;
	@Column(nullable = false, length = 255)
	private String motivoConsulta;
	@Column(columnDefinition = "TEXT")
	private String sintomas;
	@Column(columnDefinition = "TEXT")
	private String examenFisico;
	@Column(columnDefinition = "TEXT")
	private String observaciones;
	@Column(columnDefinition = "TEXT")
	private String planGeneral;

	public Consulta() {
		super();
	}

	public Consulta(Long id, Cita cita, LocalDateTime fechaHora, String motivoConsulta, String sintomas, String examenFisico, String observaciones, String planGeneral) {
		super();
		this.id = id;
		this.cita = cita;
		this.fechaHora = fechaHora;
		this.motivoConsulta = motivoConsulta;
		this.sintomas = sintomas;
		this.examenFisico = examenFisico;
		this.observaciones = observaciones;
		this.planGeneral = planGeneral;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getExamenFisico() {
		return examenFisico;
	}

	public void setExamenFisico(String examenFisico) {
		this.examenFisico = examenFisico;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPlanGeneral() {
		return planGeneral;
	}

	public void setPlanGeneral(String planGeneral) {
		this.planGeneral = planGeneral;
	}
}
