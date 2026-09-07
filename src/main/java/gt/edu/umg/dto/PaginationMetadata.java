package gt.edu.umg.dto;

/**
 * Metadatos de paginación que acompañan a cada respuesta paginada.
 *
 * Construido desde los métodos de Spring Data Page<T>:
 *   totalRecords   ← page.getTotalElements()
 *   page           ← page.getNumber()          (zero-based)
 *   pageSize       ← page.getSize()
 *   totalPages     ← page.getTotalPages()
 *   hasPreviousPage ← page.hasPrevious()
 *   hasNextPage     ← page.hasNext()
 */
public record PaginationMetadata(
    long totalRecords,
    int page,
    int pageSize,
    int totalPages,
    boolean hasPreviousPage,
    boolean hasNextPage
) {}
