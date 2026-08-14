package gt.edu.umg.core.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "medicos_clinicas", uniqueConstraints = @UniqueConstraint(columnNames = {"medico_id", "clinica_id"}))
public class MedicoClinica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "medico_id", nullable = false)
	private Medico medico;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "clinica_id", nullable = false)
	private Clinica clinica;
	@Column(length = 80)
	private String consultorio;
	private LocalDate fechaIngreso;
	@Column(nullable = false)
	private Boolean activo = true;

	public MedicoClinica() {
		super();
	}

	public MedicoClinica(Long id, Medico medico, Clinica clinica, String consultorio, LocalDate fechaIngreso, Boolean activo) {
		super();
		this.id = id;
		this.medico = medico;
		this.clinica = clinica;
		this.consultorio = consultorio;
		this.fechaIngreso = fechaIngreso;
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(String consultorio) {
		this.consultorio = consultorio;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
