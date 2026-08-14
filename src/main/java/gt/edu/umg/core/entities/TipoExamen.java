package gt.edu.umg.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_examen")
public class TipoExamen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 120)
    private String nombre;
    @Column(nullable = false, length = 80)
    private String categoria;
    @Column(length = 255)
    private String descripcion;
    @Column(nullable = false)
    private Boolean activo = true;
    public Long getId() {
        return this.id;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getCategoria() {
        return this.categoria;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    public Boolean getActivo() {
        return this.activo;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }
    public void setCategoria(final String categoria) {
        this.categoria = categoria;
    }
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }
    public void setActivo(final Boolean activo) {
        this.activo = activo;
    }
}
