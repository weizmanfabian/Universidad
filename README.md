# Universidad

Proyecto desarrollado con **Java 17**, **Docker Compose**, **PostgreSQL** y **React.js**.



https://github.com/user-attachments/assets/dc576a87-cdec-4aff-8943-e03e10f2e730



## Requisitos Previos

Asegúrese de tener instalados los siguientes elementos antes de continuar:

- **Java 17**
- **Docker Desktop**
- **Maven**
- **Git**
- Un IDE como **IntelliJ IDEA** o su editor de preferencia.
- Un gestor de bases de datos, como **DBeaver** (opcional para inspeccionar las bases de datos).

## Pasos para Ejecutar la Aplicación

### 1. Clonar el Repositorio

Ejecute el siguiente comando para clonar el repositorio:

```bash
git clone https://github.com/weizmanfabian/Universidad.git
```

### 2. Navegar al Directorio del Proyecto

Acceda al directorio raíz del proyecto:

```bash
cd Universidad
```

### 3. Configurar el Backend

#### 3.1. Acceder al Directorio del Backend

```bash
cd UniversidadBack
```

#### 3.2. Levantar los Contenedores con Docker Compose

Ejecute el siguiente comando para iniciar los contenedores de la base de datos:

```bash
docker-compose up
```
**Nota:** Al finalizar si desea remover el contenedor ejecute en este mismo directorio:
```bash
docker-compose down
```

#### 3.3. Conexión a la Base de Datos (Opcional)

Si desea conectarse a la base de datos creada, use las siguientes credenciales en su gestor de base de datos preferido:

- **Host:** `localhost`
- **Puerto:** `5436`
- **Usuario:** `weizman`
- **Contraseña:** `YourStrong#Passw0rd`
- **Base de datos:** `universidad`

### 4. Iniciar el Proyecto Backend

Abra el proyecto en **IntelliJ IDEA** u otro IDE compatible, y ejecute la aplicación.

#### 4.1. Importar pruebas de Postman (Opcional)
 - Abrir Postman e importar `TestUniversidad.postman_collection` que se encuentra en directorio `./Universidad` y ejecutar pruebas de la api.
 - El puerto de la api es 8086.
 - la url de la api es `http://localhost:8086/universidad`

### 5. Configurar el Frontend

#### 5.1. Acceder al Directorio del Frontend

Desde el directorio raíz del proyecto, navegue al directorio del frontend:

```bash
cd universidad-front
```

#### 5.2. Instalar Dependencias

Ejecute el siguiente comando para instalar las dependencias necesarias:

```bash
npm install
```

#### 5.3. Iniciar el Proyecto Frontend

Levante la aplicación frontend con el siguiente comando:

```bash
npm start
```

### 6. Acceso a la Aplicación

Una vez levantados ambos proyectos, podrá acceder a la aplicación a través de su navegador web. Asegúrese de que tanto el backend como el frontend están ejecutándose correctamente.

---

**Nota:** Si experimenta problemas, verifique los logs de Docker y de su entorno de desarrollo para diagnosticar posibles errores.
