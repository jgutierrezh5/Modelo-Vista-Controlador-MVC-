# Sistema MVC (versión Java)

Proyecto académico para demostrar la arquitectura Modelo-Vista-Controlador (MVC) y el uso de Bootstrap en una aplicación web. Es la versión en Java del proyecto original hecho en PHP.

## Módulos

- Marcas
- Productos
- Clientes

## Tecnologías utilizadas

- Java 17 o superior
- Jakarta Servlets y JSP (con JSTL)
- MySQL con JDBC
- Bootstrap 5
- Maven
- Apache Tomcat 10.1

## Estructura

```
SistemaMVC/
├── pom.xml
├── database/sistema_mvc.sql             Script para crear la base de datos
└── src/main/
    ├── java/
    │   ├── config/Database.java         Conexión a la base de datos
    │   ├── modelo/                      Clases de datos y acceso a la base (DAO)
    │   └── controlador/                 Servlets que reciben las peticiones
    ├── resources/db.properties          Usuario y contraseña de MySQL
    └── webapp/WEB-INF/
        ├── web.xml
        └── vista/                       Páginas JSP que ve el usuario
```

| PHP original                         | Java                                                  |
|--------------------------------------|-------------------------------------------------------|
| `index.php` (enrutador)              | Anotaciones `@WebServlet` en cada controlador         |
| `config/config.php`                  | `src/main/resources/db.properties`                    |
| `config/database.php`                | `config/Database.java`                                |
| `modelo/Marca.php`                   | `modelo/Marca.java` + `modelo/MarcaDAO.java`          |
| `controlador/MarcaController.php`    | `controlador/MarcaServlet.java`                       |
| `vista/marcas/index.php`             | `WEB-INF/vista/marcas/index.jsp`                      |

## Instalación

1. Crear la base de datos ejecutando `database/sistema_mvc.sql` en MySQL (por ejemplo, desde phpMyAdmin de XAMPP).
2. Revisar usuario y contraseña de MySQL en `src/main/resources/db.properties`. Por defecto usa `root` sin contraseña, como XAMPP.
3. Compilar el proyecto:

   ```
   mvn clean package
   ```

4. Copiar `target/SistemaMVC.war` a la carpeta `webapps` de Tomcat 10.1 e iniciar Tomcat.
5. Abrir en el navegador:

   `http://localhost:8080/SistemaMVC/`

También se puede abrir la carpeta como proyecto Maven en NetBeans, IntelliJ IDEA o Eclipse y ejecutarlo con un servidor Tomcat 10.1 configurado.

> Importante: se necesita Tomcat 10 o superior, porque el proyecto usa los paquetes `jakarta.*`. Tomcat 9 usa `javax.*` y no funcionará.

## Autor

Proyecto realizado para práctica académica de Programación.
Jorge Alejandro Gutierrez Hidalgo
