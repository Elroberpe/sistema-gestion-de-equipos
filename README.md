# Sistema de Gestión de Equipos

Aplicación de escritorio desarrollada en Java para la gestión integral de un inventario de equipos. Permite el control de préstamos, devoluciones, usuarios y solicitantes, facilitando la administración con una interfaz gráfica intuitiva y la persistencia de datos en una base de datos relacional.

## Arquitectura

El proyecto sigue una arquitectura **MVC** (Modelo-Vista-Controlador), lo que facilita el mantenimiento y escalabilidad de la aplicación, separando la lógica de negocio de la interfaz gráfica.

Además, hace uso de los siguientes patrones de diseño para el acceso y manejo de datos:
* **DAO (Data Access Object)**: Para encapsular el acceso a la base de datos.
* **DTO (Data Transfer Object)**: Para transportar datos entre las distintas capas (Modelo y Vista).
* **Singleton**: Para el manejo eficiente de la conexión a la base de datos (clase `Conexion`).

## Tecnologías y Herramientas

* **Lenguaje**: Java
* **Interfaz Gráfica**: Java Swing
* **Base de Datos**: Microsoft SQL Server
* **Conectividad DB**: JDBC (Java Database Connectivity)

## Características Principales

* **Login de Seguridad**: Acceso restringido con autenticación de usuarios.
* **Gestión de Equipos**: Registro, edición y listado de los equipos disponibles.
* **Préstamos y Devoluciones**: Control detallado de qué equipo fue prestado, a quién y en qué fecha.
* **Gestión de Solicitantes y Usuarios**: Administración de las personas involucradas en los préstamos y los operadores del sistema.
* **Reportes**: Generación de reportes operativos (disponibilidad en el módulo `PanelReportes`).

## Requisitos y Configuración

1. **Base de Datos**: 
   * Motor: SQL Server (localhost:1433 por defecto).
   * Nombre de la BD: `BDGestionEquipos`
   * Asegúrate de configurar en tu SQL Server un usuario con credenciales por defecto (o cámbialas en la clase `Conexion.java`):
     * **Usuario**: `sa`
     * **Contraseña**: `123456`

2. **Librerías / Dependencias**:
   * Requiere añadir al classpath el driver JDBC de Microsoft SQL Server (`mssql-jdbc`). Este suele estar presente en la carpeta `/lib` del proyecto o deberás descargarlo de forma externa.

3. **Ejecución**:
   * Abre el proyecto en tu IDE preferido (Eclipse, IntelliJ, etc.).
   * Compila y ejecuta la clase que contiene el punto de entrada (por lo general en el paquete de Vistas/Controladores principales o la clase de inicio `FrmLogin.java` / `PaneInicio.java`).

## Licencia
El código de este proyecto es provisto tal cual y se encuentra bajo los términos acordados con su autor original.
