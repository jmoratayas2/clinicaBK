package gt.edu.umg.core.entities;

import java.time.LocalDate;
import gt.edu.umg.core.entities.enums.EstadoTratamiento;
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
@Table(name = "tratamientos")
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnostico_id")
    private Diagnostico diagnostico;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String indicaciones;
    @Column(nullable = false)
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoTratamiento estado;
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    public Long getId() {
        return this.id;
    }
    public Consulta getConsulta() {
        return this.consulta;
    }
    public Diagnostico getDiagnostico() {
        return this.diagnostico;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public String getIndicaciones() {
        return this.indicaciones;
    }
    public LocalDate getFechaInicio() {
        return this.fechaInicio;
    }
    public LocalDate getFechaFin() {
        return this.fechaFin;
    }
    public EstadoTratamiento getEstado() {
        return this.estado;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setConsulta(final Consulta consulta) {
        this.consulta = consulta;
    }
    public void setDiagnostico(final Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    public void setIndicaciones(final String indicaciones) {
        this.indicaciones = indicaciones;
    }
    public void setFechaInicio(final LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setFechaFin(final LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    public void setEstado(final EstadoTratamiento estado) {
        this.estado = estado;
    }
    public void setObservaciones(final String observaciones) {
        this.observaciones = observaciones;
    }
}
