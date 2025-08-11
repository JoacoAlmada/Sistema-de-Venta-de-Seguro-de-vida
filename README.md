# 🏢 Sistema de Gestión de Clientes - Ficticia S.A.

##  Problema a Resolver

**Ficticia S.A.** está incursionando en la venta de seguros de vida y necesita conocer mejor a sus clientes actuales. La empresa requiere un sistema que permita registrar y gestionar información detallada de personas para evaluar riesgos y ofrecer seguros personalizados.

###  Requerimientos del Cliente

La empresa necesitaba capturar los siguientes datos de sus clientes:
- **Información básica**: Nombre completo, identificación, edad, género
- **Estado**: Activo o inactivo en el sistema
- **Información médica y de riesgo**:
  - ¿Maneja vehículos?
  - ¿Usa lentes?
  - ¿Es diabético?
  - ¿Padece alguna otra enfermedad? ¿Cuál?
- **Funcionalidad**: Sistema CRUD completo (Alta, Baja, Modificación)

###  Especificaciones Técnicas Solicitadas
- ✅ Frontend web responsive
- ✅ Backend en Java (alternativa a C#) con arquitectura en tres capas
- ✅ Validaciones de negocio robustas
- ✅ Base de datos SQL
- ✅ Sistema de autenticación seguro

---

##  Solución Implementada

### 🏗Arquitectura del Sistema

He desarrollado una solución full-stack que cumple con todos los requerimientos:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│    Frontend     │    │     Backend     │    │   Base de       │
│   Angular 20    │◄──►│   Spring Boot   │◄──►│     Datos       │
│   Bootstrap 5   │    │    Java 17      │    │   SQL Server    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

###  Tecnologías Utilizadas

#### Frontend
- **Angular 20**: Framework moderno para aplicaciones web
- **Bootstrap 5.3**: Para interfaces responsive y componentes UI
- **TypeScript**: Tipado estático y mejor desarrollo
- **JWT Decode**: Manejo seguro de tokens de autenticación

#### Backend (Arquitectura en 3 Capas)
- **Java 17 + Spring Boot 3.1.3**: Framework robusto y moderno
- **Capa de Presentación**: Controllers REST con validaciones
- **Capa de Negocio**: Services con lógica empresarial
- **Capa de Datos**: Repositories con Spring Data JPA
- **Spring Security + JWT**: Autenticación y autorización segura
- **Swagger/OpenAPI**: Documentación automática de API

#### Base de Datos
- **H2**: Para desarrollo y pruebas
- **SQL Server**: Para producción
- **JPA/Hibernate**: ORM para gestión de datos

---

##  Funcionalidades Implementadas

### Gestión de Usuarios
- **Registro completo** de clientes con todos los datos solicitados
- **Listado paginado** de usuarios registrados
- **Actualización** de información existente
- **Eliminación** de registros (baja lógica)
- **Búsqueda y filtrado** por diferentes criterios

###  Sistema de Autenticación
- **Login seguro** con JWT
- **Roles de usuario**: ADMIN y USER
- **Protección de rutas** tanto en frontend como backend
- **Sesión persistente** con renovación automática de tokens

###  Validaciones de Negocio
- **Campos obligatorios**: Validación de datos esenciales
- **Formato de identificación**: Validación de cédulas/documentos
- **Rango de edad**: Validación de edades lógicas (18-120 años)
- **Formato de email**: Validación de correos electrónicos
- **Validaciones médicas**: Coherencia en datos de salud

###  Gestión de Seguros
- **Tipos de seguro**: Básico, Estándar, Premium
- **Asignación automática** basada en perfil de riesgo
- **Evaluación de factores de riesgo** (edad, condiciones médicas, etc.)

---

##  Cómo Ejecutar el Proyecto

### Prerrequisitos
- Java 17+
- Node.js 18+
- Maven 3.8+
- SQL Server 

### 1️ Backend
```bash
cd Backend
mvn clean install
mvn spring-boot:run
```
**API disponible en:** `http://localhost:8080`

### 2 Frontend
```bash
cd Frontend
npm install
npm start
```
**Aplicación disponible en:** `http://localhost:4200`

### 3️ Documentación de API
**Swagger UI:** `http://localhost:8080/swagger-ui/index.html`

---


### Características Adicionales Implementadas
- **Diseño responsive** - Funciona en móviles y tablets
-  **API REST bien documentada** - Swagger integrado
-  **Testing completo** - JUnit y Mockito para backend
-  **UI/UX moderna** - Interfaz intuitiva con Bootstrap
-  **Seguridad robusta** - JWT + Spring Security
-  **Gestión de roles** - Sistema de permisos
-  **Escalabilidad** - Arquitectura preparada para crecer

---

##  Casos de Uso Resueltos

1. **Registro de nuevos clientes** con validación completa
2. **Consulta de información** de clientes existentes
3. **Actualización de datos médicos** para re-evaluación de riesgos
4. **Gestión de estados** (activar/desactivar clientes)
5. **Administración segura** con diferentes niveles de acceso
6. **Reportes y análisis** de la base de clientes

---

##  Resultados Obtenidos

Esta solución permite a **Ficticia S.A.**:
-  **Conocer mejor a sus clientes** con información detallada y estructurada
-  **Evaluar riesgos** de manera sistemática para seguros de vida
- **Ofrecer seguros personalizados** basados en perfiles de riesgo
- **Mantener seguridad** en el manejo de información sensible
- **Escalar el negocio** con una base tecnológica sólida

---

##  Soporte y Documentación

- **Documentación técnica**: README específicos en `/Backend` y `/Frontend`
- **API Documentation**: Swagger UI integrado
- **Testing**: Suites de pruebas automatizadas incluidas
