# Spring PoC AI Module

Una API de ejemplo desarrollada con Spring Boot que demuestra una arquitectura por capas con patrones genéricos y generación automática de código CRUD.

## Tecnologías utilizadas

- **Java 21** - Lenguaje de programación
- **Spring Boot 3.5.2** - Framework principal
- **PostgreSQL** - Base de datos
- **Liquibase** - Migraciones de base de datos
- **SpringDoc OpenAPI** - Documentación automática de API
- **Gradle** - Gestión de dependencias y construcción
- **Docker & Docker Compose** - Containerización
- **PMD** - Análisis estático de código

## Requisitos previos

- Java 21+
- PostgreSQL
- Docker (opcional)
- [Task](https://github.com/go-task/task) - Para comandos automatizados (opcional)
- [Bun](https://bun.sh/) - Para generadores de código (opcional)

## Configuración inicial

### 1. Clonar el repositorio
```bash
git clone https://github.com/joalvarezdev/spring-poc-ai-module
cd spring-poc-ai-module
```

### 2. Configurar variables de entorno
Crear un archivo `.env` en la raíz del proyecto:
```bash
DATABASE_URL=jdbc:postgresql://localhost:5432/spring-poc-ai-module
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=postgres
CONTEXT_PATH=/
```

### 3. Configurar base de datos PostgreSQL
```bash
# Usando Docker
docker run --name postgres-simple-api -e POSTGRES_DB=simple-example-api -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:15
```
o
```bash
# Usando Docker Compose
docker compose up -d
```

## Cómo ejecutar

### Usando Gradle directamente
```bash
./gradlew bootRun
```

### Usando Task (recomendado)
```bash
# Construir la aplicación
task build

# Construir imagen Docker
task docker-build APP_NAME=spring-poc-ai-module

# Generar código CRUD
task entity
```

La aplicación estará disponible en: http://localhost:8090

## Generación de código

Este proyecto incluye un sistema de generadores de código usando Plop.js que permite crear componentes CRUD completos.

### Plantillas disponibles:
- **Controller** - Controladores REST
- **Service** - Servicios de negocio
- **DAO** - Objetos de acceso a datos
- **DTO** - Objetos de transferencia de datos
- **Domain** - Entidades de dominio
- **Repository** - Repositorios JPA
- **Mapper** - Mapeadores entre capas

### Uso:
```bash
task entity
# Seguir las instrucciones interactivas para generar el código
```

## Estructura del proyecto

```
src/main/java/com/joalvarez/spring-poc-ai-module/
├── config/           # Configuraciones de Spring
├── controller/       # Controladores REST
│   └── general/     # Controladores base y genéricos
├── data/            # Capa de datos
│   ├── dao/         # Data Access Objects
│   ├── dto/         # Data Transfer Objects
│   ├── mapper/      # Mapeadores entre capas
│   └── repository/  # Repositorios JPA
├── exception/       # Manejo de excepciones
├── service/         # Servicios de negocio
│   └── general/    # Servicios base y genéricos
└── shared/         # Utilidades compartidas
```

### Patrones arquitectónicos:
- **Arquitectura por capas** (Controller → Service → Repository)
- **Clases genéricas** para operaciones CRUD comunes
- **Clases base** para funcionalidad compartida
- **DTOs** para transferencia de datos
- **Mappers** para conversión entre capas

## Documentación de API

### Swagger UI
Una vez que la aplicación esté ejecutándose, la documentación interactiva estará disponible en:
- **Swagger UI**: http://localhost:8090/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8090/v3/api-docs

### Endpoints principales
- `GET /actuator/health` - Health check
- Endpoints adicionales según las entidades generadas

## Base de datos

### Configuración
- **Driver**: PostgreSQL
- **Puerto por defecto**: 5432
- **Base de datos**: simple-example-api

### Migraciones con Liquibase
```bash
# Las migraciones están en: src/main/resources/database/
# Configuración en: liquibase-changelog.yml
```

**Nota**: Liquibase está deshabilitado por defecto (`enabled: false`). Para habilitar migraciones automáticas, cambiar a `enabled: true` en `application.yml`.

## Docker

### Construcción manual
```bash
# Construir JAR
./gradlew clean build

# Construir imagen
docker build -t joalvarezdev/simple-example-api:latest --build-arg ARTIFACT_NAME=simple-example-api.jar .
```

### Usando Task
```bash
# Construir imagen
task docker-build APP_NAME=simple-example-api

# Construir y subir al registry
task docker-push APP_NAME=simple-example-api
```

### Docker Compose
```bash
docker-compose up -d
```

## Calidad de código

### PMD (análisis estático)
```bash
./gradlew pmdMain pmdTest
```

Las reglas de PMD están configuradas en `linters/pmd-ruleset.xml`.

## Testing

### Ejecutar tests
```bash
./gradlew test
```
## Configuración adicional

### Variables de configuración importantes:
- `server.port` - Puerto del servidor (por defecto: 8090)
- `spring.datasource.*` - Configuración de base de datos
- `springdoc.swagger-ui.*` - Configuración de Swagger

## Desarrollo

### Comandos útiles
```bash
# Instalar dependencias de Node (para generadores)
task node

# Limpiar y construir
./gradlew clean build

# Ejecutar con perfil específico
./gradlew bootRun --args='--spring.profiles.active=dev'

# Ver nombre del proyecto
./gradlew printProjectName
```