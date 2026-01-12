# 🎁 Amigo Secreto – My Secret Santa 🎅

Este proyecto nace de una tradición familiar.  
Mi hermana (desarrolladora Android) creó una aplicación móvil para gestionar nuestro *Amigo Invisible*, y ahora el objetivo es **evolucionar esa idea**.

Queremos pasar de una solución local a una **plataforma centralizada**.  
Este backend permitirá que la información sea consumida tanto por la aplicación móvil de mi hermana como por una futura versión web, permitiendo gestionar grupos, mantener el historial año tras año y dar flexibilidad total a cada sorteo.

Se trata de una **API REST** para la gestión del juego de *Amigo Secreto (Secret Santa)*, desarrollada con **Spring Boot** y siguiendo **principios de arquitectura limpia**.

---

## 📋 Descripción

Sistema completo para organizar sorteos de *Amigo Secreto*, permitiendo:

- Gestión de usuarios  
- Creación y administración de grupos  
- Configuración de sorteos personalizados  
- Ejecución de sorteos con algoritmo inteligente  
- Revelación controlada de asignaciones  

---

## 🛠️ Tecnologías

- Java 21  
- Spring Boot 3.4.1  
- Spring Data JPA  
- H2 Database (desarrollo y tests)  
- PostgreSQL (producción)  
- Maven  
- Lombok  
- JUnit 5 + Mockito + AssertJ  
- SpringDoc OpenAPI (Swagger)  

---

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura en capas**:

```
com.michellecabrerac.mysecretsanta/
├── domain/ # Entidades JPA
├── repository/ # Repositorios
├── service/ # Lógica de negocio (interfaces + implementaciones)
├── controller/ # Controladores REST
├── dto/ # Data Transfer Objects
├── exception/ # Excepciones personalizadas y manejo global
└── config/ # Configuración (OpenAPI, etc.)
```
---

## 📊 Modelo de Dominio

### Entidades principales

- **User** – Usuarios del sistema  
- **Group** – Grupos (familia, amigos, trabajo)  
- **GroupMember** – Pertenencia usuario–grupo (con roles)  
- **Draw** – Sorteos dentro de un grupo  
- **DrawParticipant** – Participación en sorteos  
- **DrawConfiguration** – Configuración de cada sorteo  
- **Assignment** – Resultados del sorteo (quién regala a quién)  

### Relaciones clave

- Un **User** puede pertenecer a varios **Groups** (a través de **GroupMember**)  
- Un **Group** puede tener varios **Draws**  
- Un **Draw** tiene múltiples **DrawParticipants** y una **DrawConfiguration**  
- Cada **Draw** ejecutado genera múltiples **Assignments**  

---

## 🚧 En desarrollo

- **UC-01:** Gestión de Usuarios  
- **UC-02:** Gestión de Grupos  
- **UC-03:** Gestión de Miembros del Grupo  
- **UC-04:** Crear y Configurar Sorteo  
- **UC-05:** Gestión de Participantes del Sorteo  
- **UC-06:** Transiciones de Estado del Sorteo  
- **UC-07:** Ejecución del Sorteo y Generación de Asignaciones  
- **UC-08:** Revelación de Asignaciones  

---

## 📐 Commit Convention

Este proyecto seguirá una convención de commits clara y explícita para mantener un historial **legible y trazable**.

---

### 📌 Formato
```
<TYPE>: <description>
```
---
### 🧩 Tipos de Commit

**UC-XX:** Funcionalidad de negocio
Relacionado con un caso de uso específico. Incluye implementación y tests.

**Ejemplos:**
```
UC-01: implement createUser method with business validations and tests
UC-01: implement getUserById with error handling
UC-01: complete user management use case
```
---

**Refactor:** Reestructuración de código
Cambios que mejoran el código sin alterar su comportamiento.

**Ejemplos:**
```
Refactor: standardize repository naming convention
Refactor: improve entity definitions
Refactor: extract validation logic to separate methods
````
---

**Fix:** Corrección de errores
Corrección de bugs o errores detectados.

**Ejemplos:**
```
Fix: resolve Mockito warnings
Fix: correct email validation logic
Fix: adjust POM configuration for Java 21 compatibility
```

---

**Docs:** Documentación
Cambios en documentación (README, Javadoc, comentarios).

**Ejemplos:**
```
Docs: add README with project overview
Docs: document API endpoints in OpenAPI
Docs: add commit convention guidelines
```

---

**Test:** Tests independientes
Cuando se añaden tests sin cambiar código de producción.

**Ejemplos:**
```
Test: add integration tests for user repository
Test: improve coverage for edge cases
```

---

### 📏 Reglas

- **Un commit = un cambio lógico**  
  Cada commit debe representar una única razón para el cambio.

- **Funcionalidad de negocio**  
  Los commits `UC-XX:` deben incluir los tests correspondientes.

- **Refactor ≠ funcionalidad**  
  Los commits de tipo `Refactor` no deben incluir nueva funcionalidad.

- **Descripción clara**  
  El mensaje debe ser comprensible sin necesidad de ver el diff.

- **Verbo en infinitivo**  
  Usa `add`, `implement`, `fix`, no `added`, `implemented`.

---
