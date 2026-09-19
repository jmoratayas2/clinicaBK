# Backend Clínico (`clinicaBK`)

Microservicio backend principal de lógica de negocio para la gestión médica y hospitalaria. Desarrollado con **Spring Boot**, **Java 21**, **Spring Data JPA**, **PostgreSQL** y validación de tokens JWT mediante **clave pública RSA**.

---

## 📋 Requisitos

- JDK 21.
- PostgreSQL 14+ en puerto 5433 (`clinicas_db`).
- Maven Wrapper (`mvnw.cmd` o `./mvnw`).

---

## 🗄️ Base de Datos

```properties
server.port=8080
spring.datasource.url=jdbc:postgresql://localhost:5433/clinicas_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

---

## 🔒 Seguridad e Integración JWT

`clinicaBK` actúa como un **OAuth2 Resource Server**:
- **Validación descentralizada:** Valida la firma de los tokens JWT emitidos por `security-clinic` empleando únicamente la **clave pública RSA** almacenada en `src/main/resources/keys/public.pem`.
- **Claims validados:** Emisor (`iss=clinicas-security`), audiencia (`aud=clinicas-backend`), expiración y authorities asignadas al usuario.
- **Autorización por permisos:** Control fino en endpoints mediante `@PreAuthorize("hasAuthority('PACIENTE_READ')")`, etc.

---

## 📄 Paginación Real y Filtros Dinámicos en BD

El módulo de Pacientes (`/api/v1/pacientes`) cuenta con **paginación real en base de datos**:
- **`PacienteFilter`**: DTO con parámetros de consulta (`nombres`, `apellidos`, `numeroIdentificacion`, `activo`, `page`, `size`).
- **`PacienteSpecification`**: Construcción dinámica de predicados JPA (`Specification<Paciente>`).
- **`Pageable`**: La base de datos ejecuta `LIMIT` y `OFFSET` exactos, evitando transferencia innecesaria de registros a memoria.
- **`PagedResponse<T>`**: Envía al cliente la página solicitada junto con metadatos de paginación (`totalPages`, `totalElements`, `pageNumber`, `pageSize`).

---

## 🚀 Compilación y Ejecución

```bash
# Compilación
./mvnw.cmd clean compile

# Ejecución local
./mvnw.cmd spring-boot:run
```
