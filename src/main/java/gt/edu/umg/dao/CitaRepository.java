package gt.edu.umg.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gt.edu.umg.core.entities.Cita;

public interface CitaRepository extends IGenericRepository<Cita, Long> {
    @Query("""
            select count(c) > 0
            from Cita c
            where c.medico.id = :medicoId
              and c.estado not in (gt.edu.umg.core.entities.enums.EstadoCita.CANCELADA, gt.edu.umg.core.entities.enums.EstadoCita.NO_ASISTIO)
              and c.fechaHoraInicio < :fin
              and c.fechaHoraFin > :inicio
            """)
    boolean existsSolapamiento(@Param("medicoId") Long medicoId,
                               @Param("inicio") LocalDateTime inicio,
                               @Param("fin") LocalDateTime fin);

    @Query("""
            select count(c) > 0
            from Cita c
            where c.id <> :citaId
              and c.medico.id = :medicoId
              and c.estado not in (gt.edu.umg.core.entities.enums.EstadoCita.CANCELADA, gt.edu.umg.core.entities.enums.EstadoCita.NO_ASISTIO)
              and c.fechaHoraInicio < :fin
              and c.fechaHoraFin > :inicio
            """)
    boolean existsSolapamientoExcluyendo(@Param("citaId") Long citaId,
                                         @Param("medicoId") Long medicoId,
                                         @Param("inicio") LocalDateTime inicio,
                                         @Param("fin") LocalDateTime fin);
}
