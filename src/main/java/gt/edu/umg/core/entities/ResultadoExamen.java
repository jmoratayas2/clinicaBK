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
@Table(name = "resultados_examen")
public class ResultadoExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "examen_id", nullable = false, unique = true)
    private Examen examen;
    @Column(nullable = false)
    private LocalDateTime fechaResultado;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String resultado;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String conclusion;
    @Column(length = 500)
    private String archivoUrl;
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    public Long getId() {
        return this.id;
    }
    public Examen getExamen() {
        return this.examen;
    }
    public LocalDateTime getFechaResultado() {
        return this.fechaResultado;
    }
    public String getResultado() {
        return this.resultado;
    }
    public String getConclusion() {
        return this.conclusion;
    }
    public String getArchivoUrl() {
        return this.archivoUrl;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setExamen(final Examen examen) {
        this.examen = examen;
    }
    public void setFechaResultado(final LocalDateTime fechaResultado) {
        this.fechaResultado = fechaResultado;
    }
    public void setResultado(final String resultado) {
        this.resultado = resultado;
    }
    public void setConclusion(final String conclusion) {
        this.conclusion = conclusion;
    }
    public void setArchivoUrl(final String archivoUrl) {
        this.archivoUrl = archivoUrl;
    }
    public void setObservaciones(final String observaciones) {
        this.observaciones = observaciones;
    }
}
