# JavaUser – Spring Boot 11 REST API

> **Versión:** 1.0.0   |   **Java:** 11   |   **Build tool:** Maven   |   **DB:** H2 in‑memory

---

## Índice
1. [Descripción](#descripción)
2. [Arquitectura y stack](#arquitectura-y-stack)
3. [Requisitos](#requisitos)
4. [Cómo levantar el proyecto](#cómo-levantar-el-proyecto)
5. [Variables y perfiles de configuración](#variables-y-perfiles-de-configuración)
6. [Documentación OpenAPI / Swagger](#documentación-openapi--swagger)
7. [Autenticación JWT](#autenticación-jwt)
8. [Estrategia de validación](#estrategia-de-validación)
9. [Licencia](#licencia)

---

## Descripción
**JavaUser** es una API REST que gestiona usuarios y sus teléfonos. Incluye:

* Registro, login y refresco de token JWT
* CRUD de usuarios (paginado y filtrado por activos)
* Validaciones personalizadas de e‑mail y contraseña por anotaciones
* Seguridad con Spring Security + JWT (stateless)
* CORS (permitiendo **http://localhost:4200** por defecto)
* Documentación OpenAPI lista en `/swagger-ui` (springdoc‑openapi)
* BD embebida **H2**, auto‑migrada con `ddl‑auto update`

---

## Arquitectura y stack
| Capa | Tecnologías |
|------|-------------|
| HTTP / Controllers | Spring MVC, Springdoc‑OpenAPI |
| Seguridad | Spring Security 5, JWT (jjwt 0.11) |
| Negocio | Servicios + DTOs + Mappers |
| Persistencia | Spring Data JPA, Hibernate 5, H2 |
| Validación | Hibernate Validator + anotaciones custom |
| Build | Maven 3.8+, Java 11 |

---
## Diagrama de la solución
![Diagrama de la solución](/readme/images/Diagrama Solucion JAVATEST.jpg)

---
## Requisitos
* **Java 11 JDK**
* **Maven 3.8+**

> No se requiere ninguna BD externa: H2 in‑memory se levanta automáticamente.

---

## Cómo levantar el proyecto
```bash
# clonar repositorio
$ git clone https://github.com/tu‑organizacion/javauser.git
$ cd javauser

# compilar y ejecutar
$ mvn spring-boot:run
```
La aplicación se inicia en **`http://localhost:8080`**.

### Ejecutar un jar empaquetado
```bash
$ mvn clean package -DskipTests
$ java -jar target/javauser-1.0.0.jar
```

---

## Variables y perfiles de configuración
Los valores por defecto están en `src/main/resources/application.yml`.

| Propiedad | Descripción |
|-----------|-------------|
| `jwt.secret` | **Clave HMAC‑SHA‑256 (≥ 256 bits)** para firmar tokens |
| `jwt.expiration-ms` | Validez del token en milisegundos (24 h por defecto) |
| `spring.datasource.*` | Configuración de H2 in‑memory |
| `validation.regex.*` | RegEx para e‑mail y password usados por los validadores custom |

---

## Documentación OpenAPI / Swagger
* UI interactiva: **`http://localhost:8080/swagger-ui/index.html`**
* JSON: `http://localhost:8080/v3/api-docs`

---

## Autenticación JWT
1. `POST /api/auth/login` → devuelve `token` (header: `Authorization: Bearer <token>`)
2. Todas las rutas (excepto las públicas) exigen el header con **Bearer token**.
3. Contraseñas se almacenan con **Bcrypt**.

---

## Estrategia de validación
* Anotaciones custom:
    * **`@ValidMail`** (regex configurable)
    * **`@ValidPassword`** (regex configurable)
* Combinadas con `@NotBlank`, `@Email`, etc. en los DTOs.
* Errores centralizados en `GlobalExceptionHandler` que devuelve `CustomResponse`.

---

## Licencia
MIT © 2025 Martín Ríos

