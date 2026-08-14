package gt.edu.umg.core.entities;

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
@Table(name = "medicos_especialidades", uniqueConstraints = @UniqueConstraint(columnNames = {"medico_id", "especialidad_id"}))
public class MedicoEspecialidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "medico_id", nullable = false)
	private Medico medico;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "especialidad_id", nullable = false)
	private Especialidad especialidad;
	@Column(nullable = false)
	private Boolean principal = false;
	@Column(length = 80)
	private String numeroCertificacion;
	@Column(nullable = false)
	private Boolean activo = true;

	public MedicoEspecialidad() {
		super();
	}

	public MedicoEspecialidad(Long id, Medico medico, Especialidad especialidad, Boolean principal, String numeroCertificacion, Boolean activo) {
		super();
		this.id = id;
		this.medico = medico;
		this.especialidad = especialidad;
		this.principal = principal;
		this.numeroCertificacion = numeroCertificacion;
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

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Boolean getPrincipal() {
		return principal;
	}

	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}

	public String getNumeroCertificacion() {
		return numeroCertificacion;
	}

	public void setNumeroCertificacion(String numeroCertificacion) {
		this.numeroCertificacion = numeroCertificacion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
