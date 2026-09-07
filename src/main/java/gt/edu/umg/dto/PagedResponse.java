package gt.edu.umg.dto;

import java.util.List;

/**
 * Respuesta genérica para endpoints paginados.
 *
 * Estructura JSON devuelta:
 * {
 *   "data": [ ... ],
 *   "metadata": {
 *     "totalRecords": 750,
 *     "page": 0,
 *     "pageSize": 50,
 *     "totalPages": 15,
 *     "hasPreviousPage": false,
 *     "hasNextPage": true
 *   }
 * }
 *
 * Reutilizable en futuros módulos: Médicos, Citas, Especialidades, etc.
 */
public record PagedResponse<T>(
    List<T> data,
    PaginationMetadata metadata
) {}
