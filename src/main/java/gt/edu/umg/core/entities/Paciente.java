package gt.edu.umg.core.entities;

import java.time.LocalDate;
import gt.edu.umg.core.entities.enums.SexoBiologico;
import gt.edu.umg.core.entities.enums.TipoIdentificacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente extends BaseAuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 80)
	private String nombres;
	@Column(nullable = false, length = 80)
	private String apellidos;
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private TipoIdentificacion tipoIdentificacion;
	@Column(unique = true, length = 40)
	private String numeroIdentificacion;
	@Column(nullable = false)
	private LocalDate fechaNacimiento;
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private SexoBiologico sexoBiologico;
	@Column(length = 20)
	private String telefono;
	@Column(length = 120)
	private String correo;
	@Column(length = 255)
	private String direccion;
	@Column(length = 120)
	private String contactoEmergencia;
	@Column(length = 20)
	private String telefonoEmergencia;
	@Column(nullable = false)
	private Boolean activo = true;

	public Paciente() {
		super();
	}

	public Paciente(Long id, String nombres, String apellidos, TipoIdentificacion tipoIdentificacion, String numeroIdentificacion, LocalDate fechaNacimiento, SexoBiologico sexoBiologico, String telefono, String correo, String direccion, String contactoEmergencia, String telefonoEmergencia, Boolean activo) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipoIdentificacion = tipoIdentificacion;
		this.numeroIdentificacion = numeroIdentificacion;
		this.fechaNacimiento = fechaNacimiento;
		this.sexoBiologico = sexoBiologico;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
		this.contactoEmergencia = contactoEmergencia;
		this.telefonoEmergencia = telefonoEmergencia;
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public SexoBiologico getSexoBiologico() {
		return sexoBiologico;
	}

	public void setSexoBiologico(SexoBiologico sexoBiologico) {
		this.sexoBiologico = sexoBiologico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getContactoEmergencia() {
		return contactoEmergencia;
	}

	public void setContactoEmergencia(String contactoEmergencia) {
		this.contactoEmergencia = contactoEmergencia;
	}

	public String getTelefonoEmergencia() {
		return telefonoEmergencia;
	}

	public void setTelefonoEmergencia(String telefonoEmergencia) {
		this.telefonoEmergencia = telefonoEmergencia;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
