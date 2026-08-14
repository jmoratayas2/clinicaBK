package gt.edu.umg.core.entities;

import java.time.LocalDateTime;
import gt.edu.umg.core.entities.enums.EstadoExamen;
import gt.edu.umg.core.entities.enums.PrioridadExamen;
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
@Table(name = "examenes")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_examen_id", nullable = false)
    private TipoExamen tipoExamen;
    @Column(nullable = false)
    private LocalDateTime fechaSolicitud;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PrioridadExamen prioridad;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private EstadoExamen estado = EstadoExamen.SOLICITADO;
    @Column(columnDefinition = "TEXT")
    private String indicaciones;
    public Long getId() {
        return this.id;
    }
    public Consulta getConsulta() {
        return this.consulta;
    }
    public TipoExamen getTipoExamen() {
        return this.tipoExamen;
    }
    public LocalDateTime getFechaSolicitud() {
        return this.fechaSolicitud;
    }
    public PrioridadExamen getPrioridad() {
        return this.prioridad;
    }
    public EstadoExamen getEstado() {
        return this.estado;
    }
    public String getIndicaciones() {
        return this.indicaciones;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setConsulta(final Consulta consulta) {
        this.consulta = consulta;
    }
    public void setTipoExamen(final TipoExamen tipoExamen) {
        this.tipoExamen = tipoExamen;
    }
    public void setFechaSolicitud(final LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    public void setPrioridad(final PrioridadExamen prioridad) {
        this.prioridad = prioridad;
    }
    public void setEstado(final EstadoExamen estado) {
        this.estado = estado;
    }
    public void setIndicaciones(final String indicaciones) {
        this.indicaciones = indicaciones;
    }
}
