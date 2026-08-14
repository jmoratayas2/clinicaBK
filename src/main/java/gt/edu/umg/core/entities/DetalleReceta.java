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

@Entity
@Table(name = "detalles_receta")
public class DetalleReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;
    @Column(nullable = false, length = 120)
    private String dosis;
    @Column(nullable = false, length = 120)
    private String frecuencia;
    @Column(nullable = false, length = 120)
    private String duracion;
    @Column(nullable = false, length = 80)
    private String viaAdministracion;
    @Column(length = 80)
    private String cantidad;
    @Column(columnDefinition = "TEXT")
    private String indicaciones;
    public Long getId() {
        return this.id;
    }
    public Receta getReceta() {
        return this.receta;
    }
    public Medicamento getMedicamento() {
        return this.medicamento;
    }
    public String getDosis() {
        return this.dosis;
    }
    public String getFrecuencia() {
        return this.frecuencia;
    }
    public String getDuracion() {
        return this.duracion;
    }
    public String getViaAdministracion() {
        return this.viaAdministracion;
    }
    public String getCantidad() {
        return this.cantidad;
    }
    public String getIndicaciones() {
        return this.indicaciones;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setReceta(final Receta receta) {
        this.receta = receta;
    }
    public void setMedicamento(final Medicamento medicamento) {
        this.medicamento = medicamento;
    }
    public void setDosis(final String dosis) {
        this.dosis = dosis;
    }
    public void setFrecuencia(final String frecuencia) {
        this.frecuencia = frecuencia;
    }
    public void setDuracion(final String duracion) {
        this.duracion = duracion;
    }
    public void setViaAdministracion(final String viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }
    public void setCantidad(final String cantidad) {
        this.cantidad = cantidad;
    }
    public void setIndicaciones(final String indicaciones) {
        this.indicaciones = indicaciones;
    }
}
