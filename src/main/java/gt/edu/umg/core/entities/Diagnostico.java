package gt.edu.umg.core.entities;

import gt.edu.umg.core.entities.enums.TipoDiagnostico;
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
@Table(name = "diagnosticos")
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;
    @Column(length = 20)
    private String codigoCie10;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoDiagnostico tipo;
    @Column(nullable = false)
    private Boolean principal = false;
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    public Long getId() {
        return this.id;
    }
    public Consulta getConsulta() {
        return this.consulta;
    }
    public String getCodigoCie10() {
        return this.codigoCie10;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public TipoDiagnostico getTipo() {
        return this.tipo;
    }
    public Boolean getPrincipal() {
        return this.principal;
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
    public void setCodigoCie10(final String codigoCie10) {
        this.codigoCie10 = codigoCie10;
    }
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    public void setTipo(final TipoDiagnostico tipo) {
        this.tipo = tipo;
    }
    public void setPrincipal(final Boolean principal) {
        this.principal = principal;
    }
    public void setObservaciones(final String observaciones) {
        this.observaciones = observaciones;
    }
}
