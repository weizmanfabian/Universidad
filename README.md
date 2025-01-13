# Sistema de Gestión Universitaria

[![Java](https://img.shields.io/badge/Java-17-red.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.4-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-27.3.1-blue.svg)](https://www.docker.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16.1-336791.svg)](https://www.postgresql.org/)
[![React](https://img.shields.io/badge/React-18.3.1-61DAFB.svg)](https://reactjs.org/)

Sistema integral de gestión universitaria desarrollado con arquitectura de microservicios, implementando las mejores prácticas de desarrollo y patrones de diseño modernos.

https://github.com/user-attachments/assets/dc576a87-cdec-4aff-8943-e03e10f2e730

## Estructura del Proyecto

```
Universidad/
├── UniversidadBack/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   ├── dockerdocker-compose.yml
│   └── pom.xml
├── universidad-front/
│   ├── src/
│   ├── public/
│   └── package.json
└── README.md
```

### Backend
- **Java 17**: Aprovechando las últimas características del lenguaje como Records, Pattern Matching y Sealed Classes
- **Spring Boot 3.1.4**: Framework principal con soporte para:
  - Spring Data JPA para persistencia de datos
  - Spring Validation para validación de datos
- **PostgreSQL 16.1**: Sistema de gestión de base de datos robusto y escalable
- **Arquitectura de Microservicios**: Implementación de una arquitectura de microservicios, cada uno con su propia responsabilidad y comunicación a través de API REST
- **Swagger/OpenAPI**: Documentación automatizada de API REST
- **Docker compose**: Orquestación de contenedores para desarrollo y producción

### Frontend
- **React 18.3.1**: Biblioteca principal para la interfaz de usuario
- **Axios**: Cliente HTTP para comunicación con el backend

## Requisitos del Sistema

### Herramientas Necesarias
| Herramienta | Versión Mínima | Descripción |
|-------------|----------------|-------------|
| Java | 17.0 | JDK para desarrollo y ejecución |
| Docker Desktop | 27.3.1 | Containerización y orquestación |
| Maven | 3.9.7 | Gestión de dependencias y build |
| Git | 2.45.1 | Control de versiones |
| Node.js | 22.12.0 | Entorno de ejecución JavaScript |
| npm | 10.8.2 | Gestor de paquetes de Node.js |

### IDEs Recomendados
- **Backend**: IntelliJ IDEA 2023.3 o sus versiones posteriores
- **Frontend**: Visual Studio Code con extensiones para React y TypeScript
- **Base de Datos**: DBeaver 23.2.x o DataGrip

## Configuración del Entorno de Desarrollo

### 1. Clonar el Repositorio
```bash
git clone https://github.com/weizmanfabian/Universidad.git
cd Universidad
```

### 2. Configuración del Backend

#### 2.1. Configuración de la Base de Datos
Crear un contenedor de PostgreSQL con Docker y ejecutar
```bash
cd UniversidadBack
docker-compose up -d
```

Nota: Cuando termine de probar la aplicación y desee remover el contenedor, ejecutar
```bash
docker-compose down
```

El archivo `docker-compose.yml` incluye:
- PostgreSQL 16.1 con persistencia de datos
- Volúmenes para persistencia de datos
- Variables de entorno configuradas para desarrollo

#### 2.2. Credenciales de Base de Datos
```properties
Host: localhost
Puerto: 5436
Usuario: weizman
Contraseña: YourStrong#Passw0rd
Base de datos: universidad
```

#### 2.3. Ejecución del Backend
```bash
mvn clean install
mvn spring-boot:run
```

La aplicación estará disponible en:
- API REST: `http://localhost:8086/universidad`
- Swagger UI: http://localhost:8086/universidad/swagger-ui/index.html
- OpenAPI Docs: http://localhost:8086/universidad/v3/api-docs

#### 2.3.1. Importar pruebas de Postman (Opcional)
 - Abrir Postman e importar `TestUniversidad.postman_collection` que se encuentra en directorio `./Universidad` y ejecutar pruebas de la api.

### 3. Configuración del Frontend

```bash
cd ../universidad-front
npm install
npm start
```

La aplicación web estará disponible en http://localhost:3000




