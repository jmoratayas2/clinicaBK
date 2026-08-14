package gt.edu.umg.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String nombreGenerico;
    @Column(length = 120)
    private String nombreComercial;
    @Column(nullable = false, length = 80)
    private String presentacion;
    @Column(nullable = false, length = 80)
    private String concentracion;
    @Column(nullable = false)
    private Boolean activo = true;
    public Long getId() {
        return this.id;
    }
    public String getNombreGenerico() {
        return this.nombreGenerico;
    }
    public String getNombreComercial() {
        return this.nombreComercial;
    }
    public String getPresentacion() {
        return this.presentacion;
    }
    public String getConcentracion() {
        return this.concentracion;
    }
    public Boolean getActivo() {
        return this.activo;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setNombreGenerico(final String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }
    public void setNombreComercial(final String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }
    public void setPresentacion(final String presentacion) {
        this.presentacion = presentacion;
    }
    public void setConcentracion(final String concentracion) {
        this.concentracion = concentracion;
    }
    public void setActivo(final Boolean activo) {
        this.activo = activo;
    }
}
