package gt.edu.umg.core.entities;

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
@Table(name = "historiales_medicos")
public class HistorialMedico extends BaseAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "paciente_id", nullable = false, unique = true)
	private Paciente paciente;
	@Column(length = 10)
	private String tipoSangre;
	@Column(columnDefinition = "TEXT")
	private String alergias;
	@Column(columnDefinition = "TEXT")
	private String enfermedadesCronicas;
	@Column(columnDefinition = "TEXT")
	private String antecedentesPersonales;
	@Column(columnDefinition = "TEXT")
	private String antecedentesFamiliares;
	@Column(columnDefinition = "TEXT")
	private String cirugiasPrevias;
	@Column(columnDefinition = "TEXT")
	private String medicamentosHabituales;
	@Column(columnDefinition = "TEXT")
	private String observaciones;

	public HistorialMedico() {
		super();
	}

	public HistorialMedico(Long id, Paciente paciente, String tipoSangre, String alergias, String enfermedadesCronicas, String antecedentesPersonales, String antecedentesFamiliares, String cirugiasPrevias, String medicamentosHabituales, String observaciones) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.tipoSangre = tipoSangre;
		this.alergias = alergias;
		this.enfermedadesCronicas = enfermedadesCronicas;
		this.antecedentesPersonales = antecedentesPersonales;
		this.antecedentesFamiliares = antecedentesFamiliares;
		this.cirugiasPrevias = cirugiasPrevias;
		this.medicamentosHabituales = medicamentosHabituales;
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

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getEnfermedadesCronicas() {
		return enfermedadesCronicas;
	}

	public void setEnfermedadesCronicas(String enfermedadesCronicas) {
		this.enfermedadesCronicas = enfermedadesCronicas;
	}

	public String getAntecedentesPersonales() {
		return antecedentesPersonales;
	}

	public void setAntecedentesPersonales(String antecedentesPersonales) {
		this.antecedentesPersonales = antecedentesPersonales;
	}

	public String getAntecedentesFamiliares() {
		return antecedentesFamiliares;
	}

	public void setAntecedentesFamiliares(String antecedentesFamiliares) {
		this.antecedentesFamiliares = antecedentesFamiliares;
	}

	public String getCirugiasPrevias() {
		return cirugiasPrevias;
	}

	public void setCirugiasPrevias(String cirugiasPrevias) {
		this.cirugiasPrevias = cirugiasPrevias;
	}

	public String getMedicamentosHabituales() {
		return medicamentosHabituales;
	}

	public void setMedicamentosHabituales(String medicamentosHabituales) {
		this.medicamentosHabituales = medicamentosHabituales;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
