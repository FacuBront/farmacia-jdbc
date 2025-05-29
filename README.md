# Proyecto Final - Gestión de Farmacia (Java + JDBC + H2)

Este proyecto es una aplicación de consola desarrollada en Java. Permite gestionar Clientes y Pedidos para una farmacia utilizando base de datos H2, JDBC, Log4j2 y patrón DAO.

---

## 🚀 Tecnologías utilizadas

- Java 17
- JDBC
- Base de datos H2 (modo archivo)
- Log4j2 (sistema de logging)
- Gradle
- JUnit 5 (para testing)

---

## 🧩 Estructura del Proyecto

```
farmacia-jdbc/
├── model/ → Clases de dominio (Cliente, Pedido)
├── dao/ → Interfaces DAO (generics y específicas)
├── dao/impl/ → Implementaciones de DAOs
├── util/ → Conexión a BD y creación de tablas
├── main/ → Menú principal y submenús
├── resources/ → log4j2.xml
├── logs/ → Salida de logs (app.log)
├── script_creacion.sql
└── build.gradle
```

---

## ⚙️ Cómo ejecutar el proyecto

### 1. Clonar el repositorio

- git clone https://github.com/FacuBront/farmacia-jdbc.git
- cd farmacia-jdbc
### 2. Abrir el proyecto en IntelliJ IDEA
- Usar Open y seleccionar la carpeta

- Esperar que Gradle sincronice

### 3. Ejecutar desde consola
- ./gradlew run
### 4. O desde IntelliJ
- Ir a src/main/java/main/Main.java

- Clic derecho → Run

---

## 🗄 Base de Datos
- Se usa H2 en modo archivo

- El archivo se crea automáticamente: /data/farmaciaDB.mv.db

- Las tablas se crean desde la clase DBInitializer

---

## 📊 Logging
- Configurado con Log4j2

- Muestra mensajes en consola y en logs/app.log

- Configuración en resources/log4j2.xml

---

## 📋 Script SQL de respaldo
- También se incluye script_creacion.sql con el esquema SQL para crear manualmente la base de datos.

---

## ✅ Funcionalidades
- CRUD completo de Clientes y Pedidos

- Validaciones básicas de entradas

- Búsqueda por ID y por Cliente

- Uso de clases genéricas

- Separación en capas (DAO, model, lógica)

- Logging y documentación

---

## ✍️ Autor
- Estudiante: Facundo Bront

- Profesor: Claudia Naveda
