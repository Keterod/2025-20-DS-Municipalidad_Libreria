# 📚 Sistema de Gestión de Biblioteca Municipal

Sistema web desarrollado como proyecto académico para la gestión de una biblioteca municipal.

El proyecto busca centralizar la administración de usuarios, libros, préstamos y demás procesos relacionados con la gestión bibliotecaria, facilitando el acceso y control de la información.

---

## 📌 Descripción

El Sistema de Gestión de Biblioteca Municipal permite gestionar de manera organizada la información relacionada con los recursos bibliográficos y los usuarios de la biblioteca.

El proyecto fue desarrollado como parte de un proyecto académico de Ingeniería de Sistemas e Informática, aplicando conceptos de análisis de requerimientos, diseño de software, arquitectura MVC y gestión de bases de datos.

---

## 🎯 Objetivos

- Digitalizar la gestión de información de la biblioteca.
- Facilitar la administración del catálogo bibliográfico.
- Gestionar usuarios y sus roles.
- Administrar préstamos y devoluciones.
- Mantener la información centralizada en una base de datos.
- Aplicar buenas prácticas de desarrollo de software.

---

## ⚙️ Funcionalidades

### 👤 Gestión de usuarios

- Registro de usuarios.
- Actualización de información.
- Gestión de roles.
- Control de acceso según el rol.

### 📚 Gestión bibliográfica

- Registro de libros.
- Actualización de información.
- Consulta del catálogo.
- Gestión de disponibilidad.

### 📖 Gestión de préstamos

- Registro de préstamos.
- Control de devoluciones.
- Consulta del estado de los préstamos.

### 🔐 Seguridad

- Autenticación de usuarios.
- Gestión de roles.
- Almacenamiento de contraseñas mediante hash.

---

## 🛠️ Tecnologías

### Backend
- Java
- Spring Boot
- MVC

### Base de datos
- Microsoft SQL Server
- JDBC

### Diseño y documentación
- UML
- PlantUML

### Herramientas
- Git
- GitHub
- Maven

> Las tecnologías indicadas corresponden a las utilizadas durante el desarrollo académico del proyecto.

---

## 🏗️ Arquitectura

El sistema utiliza una arquitectura basada en el patrón **MVC (Model-View-Controller)**.

```text
┌─────────────────────┐
│        Vista        │
│       (View)        │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│    Controlador      │
│    (Controller)     │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│      Modelo         │
│      (Model)        │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│     SQL Server      │
│      Database       │
└─────────────────────┘
