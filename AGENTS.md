## Project Shape
- Single-module Maven Spring Boot 4.1 API (`pom.xml`), Java 21, package root `gt.edu.umg`.
- Main entrypoint: `src/main/java/gt/edu/umg/ApiDemoApplication.java`.
- Current implemented HTTP surface is `PacienteController` at `/api/v1/pacientes`; OpenAPI UI comes from `springdoc-openapi-starter-webmvc-ui`.
- `DOCS/MODELO_ENTIDADES.md` is a proposed clinic domain model, not fully implemented yet; use it as guidance when adding future entities.

## Commands
- From `apirest-demo/`, use the Maven wrapper: `./mvnw.cmd test` on Windows, `./mvnw test` elsewhere.
- Run locally with `./mvnw.cmd spring-boot:run`.
- Focus tests with Surefire syntax, e.g. `./mvnw.cmd -Dtest=ApiDemoApplicationTests test` or `./mvnw.cmd -Dtest=ApiDemoApplicationTests#contextLoads test`.

## Runtime Defaults
- `application.properties` uses in-memory H2: `jdbc:h2:mem:clinicadb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`, username `sa`, empty password.
- H2 console is enabled at `/h2-console`; schema is managed by Hibernate with `spring.jpa.hibernate.ddl-auto=update`.
- No external database, migrations, Docker, CI workflow, formatter, or lint config is present in the repo.

## Code Conventions That Matter
- MapStruct is wired via Maven annotation processors; keep mapper interfaces under Spring component model (`@Mapper(componentModel = "spring")`) when adding mappers.
- Repository methods are Spanish wrappers from `IGenericRepository` (`guardar`, `obtenerTodos`, `obtenerPorId`, `eliminarPorId`) over Spring Data JPA; existing services call those wrappers instead of direct `save/findAll/findById/deleteById`.
- Repositories that need dynamic filters should also extend `JpaSpecificationExecutor`, as `PacienteRepository` does for `buscarConFiltro`.
- DTO packages currently use capitalized segments: `core.entities.Dtos.Request` and `core.entities.Dtos.Response`; match existing imports unless doing an intentional package cleanup.
- `PacienteService` currently lives in `service.impl` alongside `PacienteServiceImpl`; preserve that package unless intentionally reorganizing services.
