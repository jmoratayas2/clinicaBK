## Project Shape
- Single-module Maven Spring Boot 4.1 API (`pom.xml`), Java 21, package root `gt.edu.umg`.
- Main entrypoint: `src/main/java/gt/edu/umg/ApiDemoApplication.java`.
- The clinic domain from `DOCS/MODELO_ENTIDADES.md` is implemented as JPA entities plus REST controllers under `/api/v1`.
- OpenAPI UI comes from `springdoc-openapi-starter-webmvc-ui`.

## Commands
- From `apirest-demo/`, use the Maven wrapper: `./mvnw.cmd test` on Windows, `./mvnw test` elsewhere.
- Run locally with `./mvnw.cmd spring-boot:run`.
- Focus tests with Surefire syntax, e.g. `./mvnw.cmd -Dtest=ApiDemoApplicationTests test` or `./mvnw.cmd -Dtest=ApiDemoApplicationTests#contextLoads test`.

## Runtime Defaults
- `application.properties` connects directly to local MySQL: `jdbc:mysql://localhost:3306/Clínica`, user `root`.
- Schema is managed by Hibernate with `spring.jpa.hibernate.ddl-auto=update`; no Flyway/Liquibase migration config is present.
- No Docker, CI workflow, formatter, or lint config is present in the repo.

## Code Conventions That Matter
- Lombok and MapStruct are not used; entities have explicit no-arg constructors plus getters/setters so Eclipse/STS works without annotation processing.
- Mappers are manual Spring `@Component` classes under `mapper/`.
- Repository methods are Spanish wrappers from `IGenericRepository` (`guardar`, `obtenerTodos`, `obtenerPorId`, `eliminarPorId`) over Spring Data JPA; existing services call those wrappers instead of direct `save/findAll/findById/deleteById`.
- Repositories that need dynamic filters should also extend `JpaSpecificationExecutor`, as `PacienteRepository` does for `buscarConFiltro`.
- DTO packages currently use capitalized segments: `core.entities.Dtos.Request` and `core.entities.Dtos.Response`; match existing imports unless doing an intentional package cleanup.
- `PacienteService` currently lives in `service.impl` alongside `PacienteServiceImpl`; preserve that package unless intentionally reorganizing services.
- Controllers must return DTOs, not JPA entities; shared DTOs for the clinic model are nested records in `dto/ClinicDtos.java`.
- Delete endpoints for active catalogs/patients perform logical deactivation by setting `activo=false`; clinical records are not physically deleted.
