# LiterAlura-Challenge
El proyecto LiterAlura es una aplicación de consola desarrollada en Java con el propósito de gestionar un catálogo de libros. Esta aplicación permite a los usuarios interactuar mediante un menú textual, ofreciendo diversas funcionalidades relacionadas con la búsqueda y gestión de libros y autores.

## Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Estado del Proyecto](#estado-del-proyecto)
3. [Características de la Aplicación y Demostración](#características-de-la-aplicación-y-demostración)
4. [Acceso al Proyecto](#acceso-al-proyecto)
5. [Tecnologías Utilizadas](#tecnologías-utilizadas)

## Descripcion del Proyecto

El proyecto LiterAlura es un emocionante desafío de programación cuyo objetivo es construir un cátalogo de libros  interactivo. A lo largo de este proyecto, se han implementado una serie de funcionalidades que permiten a los usuarios buscar y gestionar información de libros y autores. El proceso incluye la realización de solicitudes a una API de libros, la manipulación de datos en formato JSON, el almacenamiento de estos datos en una base de datos y la presentación de los resultados de manera interactiva a través de una consola.


## Estado del Proyecto

El proyecto se desarrollo utilizando Java y el framework Spring Boot y se ha completado con éxito.


### Fases completadas:

1. **Consumo de la API**
    - Realización de solicitudes de la API para obtener datos de libros.

2. **Análisis de la Respuesta JSON**
    - Análisis y conversión de los datos JSON obtenidos a objetos Java. 

3. **Interación con el usuario**
    -  Implementación de un menú interactivo en consola con múltiples opciones para el usuario

4. **Consulta y Listado de Libros**
    - Búsqueda de libros por título y presentación de una lista de todos los libros buscados.

5. **Consulta y Listado de Autores**
    - Listado de autores y opción para consultar autores vivos en un determinado año

6. **Persistencia de Datos**
    - Inserción y consulta de datos en la base de datos utilizando Spring Data JPA y PostgresSQL.

7. **Estadísticas de Libros**
    - Inserciín y consulta de datos sobre la cantidad de libros en  determinados idiomas.

## Características de la Aplicación y Demostración

### 1. Busqueda de libros
La aplicación LiterAlura permite a los usuarios buscar libros por título a través de una integración con la API Gutendex. Utilizando un menú interactivo en consola, los usuarios pueden ingresar el título del libro deseado. La aplicación realizará una solicitud a la API y mostrará los resultados encontrados, incluyendo detalles como el título del libro, el nombre del autor, los idiomas disponibles y el número de descargas. Esta funcionalidad proporciona a los usuarios una forma eficiente de encontrar información específica sobre libros de interés.

<div align="center">

[![Video de Búsqueda de libros](https://img.youtube.com/vi/17JLRUQoTZA/0.jpg)](https://www.youtube.com/watch?v=17JLRUQoTZA)

</div>

### 2. Listado de libros registrados
LiterAlura mantiene un registro de todos los libros buscados por los usuarios a través de la API Gutendex. Los usuarios pueden acceder a un listado completo de libros consultados, que incluye detalles como el título del libro, el nombre del autor, los idiomas disponibles y el número de descargas. Esta función permite a los usuarios revisar fácilmente los libros previamente consultados y gestionar su catálogo personal de manera organizada.

<div align="center">

[![Video Listado de libros registrados](https://img.youtube.com/vi/3x3rWIOjjuM/0.jpg)](https://www.youtube.com/watch?v=3x3rWIOjjuM)

</div>



### 3. Listado de autores registrados
LiterAlura permite a los usuarios ver una lista completa de autores registrados en la base de datos. A través del menú textual, los usuarios pueden acceder a una lista detallada que incluye el nombre, año de nacimiento y año de fallecimiento de cada autor.

<div align="center">

[![Video Listado de autores registrados](https://img.youtube.com/vi/KjpnEZ2kxeM/0.jpg)](https://www.youtube.com/watch?v=KjpnEZ2kxeM)

</div>


### 4. Listado de autores vivos en un determinado año
LiterAlura permite a los usuarios consultar y listar autores vivos en un año específico, utilizando datos almacenados en la base de datos. Los usuarios ingresan el año deseado y la aplicación muestra una lista de autores cuyas fechas de nacimiento y fallecimiento indican que estaban vivos durante ese año. Esta función proporciona a los usuarios una manera eficiente de explorar y descubrir autores relevantes según el período histórico deseado.

<div align="center">

[![Video Listado por autores vivos en un año determinado](https://img.youtube.com/vi/WKUXLnGFnM8/0.jpg)](https://www.youtube.com/watch?v=WKUXLnGFnM8)

</div>


### 5. Listado de libros por idiomas
LiterAlura permite a los usuarios listar libros registrados en la base de datos según el idioma. A través de un menú textual, los usuarios pueden seleccionar el idioma deseado, y la aplicación mostrará todos los libros almacenados en ese idioma, proporcionando una herramienta útil para explorar la biblioteca según las preferencias lingüísticas del usuario.

<div align="center">

[![Video Listado por idiomas](https://img.youtube.com/vi/QDjqQOuY3uk/0.jpg)](https://www.youtube.com/watch?v=QDjqQOuY3uk)

</div>


## Acceso al Proyecto
Para acceder al proyecto, puedes clonar el repositorio desde GitHub usando el siguiente enlace:

```sh
git clone https://github.com/CristinaReyes99/LiterAlura-Challenge.git
```

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal para el desarrollo de la aplicación.
- **Postman:** Se utilizó para probar las solicitudes HTTP a la API de Gutendex.
- **Spring Boot**: Framework utilizado para la creación del proyecto y la gestión de dependencias.
- **Spring Data JPA**: Utilizado para la persistencia de datos en la base de datos.
- **PostgreSQL**: Sistema de gestión de bases de datos utilizado para almacenar la información.
- **JSON**: Formato de datos utilizado para la comunicación con la API.
- **API Gutendex**: Fuente de datos de libros para el proyecto.
- **GitHub**: Plataforma de control de versiones y colaboración en el proyecto.
- **Trello:** Se utilizó para planificar y seguir el progreso de las distintas fases de desarrollo.


## Autores
|[<img src="https://avatars.githubusercontent.com/u/156963931?v=4" width=115><br><sub>Cristina Reyes</sub>](https://github.com/CristinaReyes99)|
|:---:|
