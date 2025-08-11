# 📄 Ficticia S.A – Sistema de Gestion de Usuarios y Seguros de Vida

## 📝 Descripción

Este proyecto es una solucion integral para **Ficticia S.A.**, que permite la administración de clientes de seguros de vida, gestionando su informacion personal, estado, condiciones medicas y el tipo de seguro contratado.

La aplicacion está compuesta por:
- **Backend** en **Java 17 + Spring Boot**, encargado de exponer una API REST para las operaciones de negocio.
- **Frontend** en **Angular 20 + Bootstrap**, que consume la API y presenta una interfaz web responsive.

---

## 📂 Estructura del Proyecto

```
/Backend
├── src/
│   ├── java/
│      ├── configs/    
│      ├── controller/   
│      ├── db/  
│      ├── dto.component/     
│      ├── models/ 
│      ├── repository/ 
│      └──  service/ 
├── test/
│   ├── java/  
│      ├── controller/   
│      ├── dto.component/     
│      ├── models/ 
│      └──  service/  
├── pom.xml
└── README.md

/Frontend
├── src/
│   ├── app/
│   │   ├── features/    
│   │   ├── interfaces/   
│   │   ├── routes/       
│   │   └── service/      
│   ├── assets/
│   ├── index.html
│   ├── main.ts
│   └── styles.css
├── package.json
└── angular.json
```

---

## 🚀 Tecnologías

### Backend
- **Java 17**
- **Spring Boot 3.1.3**
- **Spring Data JPA**
- **Spring Validation**
- **Spring Security** con JWT
- **ModelMapper**
- **Swagger/OpenAPI** (`springdoc-openapi`)
- **JUnit & Mockito** para pruebas unitarias
- **H2** (en desarrollo) y **SQL Server** (produccion)

### Frontend
- **Angular 20**
- **Bootstrap 5.3**
- **ng-bootstrap**
- **jwt-decode** para gestión de autenticacion
- **RxJS**
- **TypeScript**

---

## 📌 Funcionalidades Principales

- CRUD completo de usuarios.
- Gestion de tipos de seguros: **Basico**, **Estandar** y **Premium**.
- Campos medicos opcionales: manejo de vehículos, uso de lentes, condiciones como diabetes u otras enfermedades.
- Autenticación y autorización vía **JWT**.
- Interfaz responsive gracias a **Bootstrap**.
- Documentación de API accesible con Swagger.

---

## ⚙️ Instalación y Ejecución

### 🔹 Backend

1. Clonar el repositorio:
   ```bash
   git clone <repo-url>
   cd Backend
   ```

2. Compilar e iniciar:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. API disponible en:
   ```
   http://localhost:8080
   ```

### 🔹 Frontend

1. Ir a la carpeta:
   ```bash
   cd Frontend
   ```

2. Instalar dependencias:
   ```bash
   npm install
   ```

3. Levantar el servidor:
   ```bash
   npm start
   ```

4. Aplicación disponible en:
   ```
   http://localhost:4200
   ```

---

## 📖 Documentación de la API

Con el backend corriendo, accede a Swagger:
```
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 Testing

### Backend
```bash
mvn test
```
Incluye pruebas con JUnit 5 y Mockito para controladores, servicios y validaciones.

---

## 📂 Modelo de Datos (Backend)

### Usuario
- `id_usuario` (Integer)
- `identificacion` (String)
- `nombre_completo` (String)
- `contrasenia` (String)
- `edad` (Integer)
- `seguro` (Enum: Basico, Estandar, Premium)
- `email` (String)
- `telefono` (String)
- `genero` (Enum: Masculino, Femenino, Otro)
- `estado` (Enum: Activo, Inactivo)
- `maneja` (Boolean)
- `usa_lentes` (Boolean)
- `diabetico` (Boolean)
- `otra_enfermedad` (Boolean)
- `cual_enfermedad` (String)
- `cuenta` (Relación con entidad Cuenta por identificacion)

### Cuenta
- `identificacion` (String, PK)
- `contrasenia` (String)
- `rol` (Enum: Administrador, Usuario)

---

## 📌 Endpoints Principales (Backend)

### Usuarios
| Método | Endpoint | Descripción                    |
|--------|----------|--------------------------------|
| GET    | `/api/v1/usuario` | Lista todos los usuarios       |
| POST   | `/api/v1/usuario/filtro` | Obtiene usuarios por filtros   |
| POST   | `/api/v1/create/usuario` | Crea un nuevo usuario          |
| PUT    | `/api/v1/update/{id}` | Actualiza un usuario existente |
| DELETE | `/api/v1/delete/{id}` | Elimina un usuario             |

### Autenticación
| Método | Endpoint              | Descripción |
|--------|-----------------------|-------------|
| POST | `api/v1/cuenta/login` | Inicia sesión (JWT) |

---

## 🖥️ Interfaz de Usuario (Frontend)

El frontend está dividido en:

- **Features**: componentes y paginas (registro, login, listado de usuarios, edicion).
- **Services**: manejo de llamadas HTTP hacia la API.
- **Routes**: configuracion de rutas de Angular.
- **Interfaces**: tipado de datos que se reciben/envían al backend.

Uso de Bootstrap para maquetación y estilos, junto con ng-bootstrap para componentes dinamicos.

---


## 📄 Licencia

Este proyecto se desarrolla con fines tecnicos y demostrativos.
