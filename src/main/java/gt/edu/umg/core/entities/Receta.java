package gt.edu.umg.core.entities;

import java.time.LocalDateTime;
import gt.edu.umg.core.entities.enums.EstadoReceta;
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
@Table(name = "recetas")
public class Receta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "consulta_id", nullable = false)
	private Consulta consulta;
	@Column(nullable = false)
	private LocalDateTime fechaEmision;
	@Column(columnDefinition = "TEXT")
	private String indicacionesGenerales;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private EstadoReceta estado = EstadoReceta.EMITIDA;
	@Column(columnDefinition = "TEXT")
	private String observaciones;

	public Receta() {
		super();
	}

	public Receta(Long id, Consulta consulta, LocalDateTime fechaEmision, String indicacionesGenerales, EstadoReceta estado, String observaciones) {
		super();
		this.id = id;
		this.consulta = consulta;
		this.fechaEmision = fechaEmision;
		this.indicacionesGenerales = indicacionesGenerales;
		this.estado = estado;
		this.observaciones = observaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public LocalDateTime getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDateTime fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getIndicacionesGenerales() {
		return indicacionesGenerales;
	}

	public void setIndicacionesGenerales(String indicacionesGenerales) {
		this.indicacionesGenerales = indicacionesGenerales;
	}

	public EstadoReceta getEstado() {
		return estado;
	}

	public void setEstado(EstadoReceta estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
