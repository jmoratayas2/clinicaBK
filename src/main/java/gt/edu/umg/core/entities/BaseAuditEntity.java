package gt.edu.umg.core.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class BaseAuditEntity {
    @Column(nullable = false, updatable = false)
    private LocalDateTime creadoEn;
    @Column(nullable = false)
    private LocalDateTime actualizadoEn;

    @PrePersist
    void prePersist() {
        LocalDateTime ahora = LocalDateTime.now();
        creadoEn = ahora;
        actualizadoEn = ahora;
    }

    @PreUpdate
    void preUpdate() {
        actualizadoEn = LocalDateTime.now();
    }
    public LocalDateTime getCreadoEn() {
        return this.creadoEn;
    }
    public LocalDateTime getActualizadoEn() {
        return this.actualizadoEn;
    }
    public void setCreadoEn(final LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
    public void setActualizadoEn(final LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}
