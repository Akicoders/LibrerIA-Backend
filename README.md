
# LibrerIA API

Es un proyecto centralizado en la parte backend del proyecto LibrerIA, en donde tiene lo esencial y lo importante para que tenga logica y sea dinamico la aplicacion.


Aquí tienes los controladores en formato de referencia API, sin renderizar como Markdown:

```
## API Reference

### GeneroController

#### Get all Generos

```http
  GET /api/generos
```

| Parameter | Type     | Description                  |
| :-------- | :------- | :--------------------------- |
| None      | None     | Get all Generos from database |

#### Add Genero

```http
  POST /api/genero/agregar
```

| Parameter | Type     | Description                    |
| :-------- | :------- | :----------------------------- |
| `genero`  | object   | **Required**. Genero to add    |

#### Update Genero

```http
  POST /api/genero/actualizar
```

| Parameter | Type     | Description                    |
| :-------- | :------- | :----------------------------- |
| `genero`  | object   | **Required**. Genero to update |

#### Check if Genero exists

```http
  GET /api/genero/existe/{id}
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| `id`      | integer  | **Required**. ID of Genero        |

#### Get Genero by ID

```http
  GET /api/genero/{id}
```

| Parameter | Type     | Description                    |
| :-------- | :------- | :----------------------------- |
| `id`      | integer  | **Required**. ID of Genero      |

#### Count all Generos

```http
  GET /api/generos/todos
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| None      | None     | Count of all Generos in database |

#### Delete Genero by ID

```http
  DELETE /api/genero/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | integer  | **Required**. ID of Genero to delete |

#### Delete Genero

```http
  DELETE /api/genero/eliminar
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `genero`  | object   | **Required**. Genero to delete    |

---

### AutorController

#### Get all Autores

```http
  GET /api/autores
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| None      | None     | Get all Autores from database     |

#### Add Autor

```http
  POST /api/autor/agregar
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| `autor`   | object   | **Required**. Autor to add       |

#### Update Autor

```http
  POST /api/autor/actualizar
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| `autor`   | object   | **Required**. Autor to update    |

#### Check if Autor exists

```http
  GET /api/autor/existe/{id}
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| `id`      | integer  | **Required**. ID of Autor        |

#### Get Autor by ID

```http
  GET /api/autor/{id}
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| `id`      | integer  | **Required**. ID of Autor        |

#### Count all Autores

```http
  GET /api/autores/todos
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| None      | None     | Count of all Autores in database  |

#### Delete Autor by ID

```http
  DELETE /api/autor/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | integer  | **Required**. ID of Autor to delete|

#### Delete Autor

```http
  DELETE /api/autor/eliminar
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `autor`   | object   | **Required**. Autor to delete     |

---

### AdministradorController

#### Get all Administradores

```http
  GET /api/administradores
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| None      | None     | Get all Administradores from database|

#### Add Administrador

```http
  POST /api/administrador/agregar
```

| Parameter   | Type     | Description                              |
| :---------- | :------- | :--------------------------------------- |
| `administrador` | object   | **Required**. Administrador to add    |

#### Update Administrador

```http
  POST /api/administrador/actualizar
```

| Parameter   | Type     | Description                              |
| :---------- | :------- | :--------------------------------------- |
| `administrador` | object   | **Required**. Administrador to update  |

#### Check if Administrador exists

```http
  GET /api/administrador/existe/{id}
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| `id`      | integer  | **Required**. ID of Administrador |

#### Get Administrador by ID

```http
  GET /api/administrador/{id}
```

| Parameter | Type     | Description                      |
| :-------- | :------- | :-------------------------------- |
| `id`      | integer  | **Required**. ID of Administrador |

#### Count all Administradores

```http
  GET /api/administradores/todos
```

| Parameter | Type     | Description                          |
| :-------- | :------- | :----------------------------------- |
| None      | None     | Count of all Administradores in database|

#### Delete Administrador by ID

```http
  DELETE /api/administrador/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | integer  | **Required**. ID of Administrador  |

#### Delete Administrador

```http
  DELETE /api/administrador/eliminar
```

| Parameter   | Type     | Description                       |
| :---------- | :------- | :-------------------------------- |
| `administrador` | object   | **Required**. Administrador to delete |

---

### LibroController

#### Get all Libros

```http
  GET /api/libros
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| None      | None     | Get all Libros from database        |

#### Add Libro

```http
  POST /api/libro/agregar
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `libro`   | object   | **Required**. Libro to add         |

#### Update Libro

```http
  POST /api/libro/actualizar
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `libro`   | object   | **Required**. Libro to update      |

#### Check if Libro exists

```http
  GET /api/libro/existe/{id}
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `id`      | integer  | **Required**. ID of Libro to check  |

#### Get Libro by ID

```http
  GET /api/libro/{id}
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `id`      | integer  | **Required**. ID of Libro           |

#### Count all Libros

```http
  GET /api/libros/todos
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| None      | None     | Count of all Libros in database     |

#### Delete Libro by ID

```http
  DELETE /api/libro/{id}
```

| Parameter | Type     | Description                        |
| :-------- | :------- | :--------------------------------- |
| `id`      | integer  | **Required**. ID of Libro to delete|

#### Delete Libro

```http
  DELETE /api/libro/eliminar
```

| Parameter | Type     | Description                        |
| :-------- | :------- | :--------------------------------- |
| `libro`   | object   | **Required**. Libro to delete     |

---

### CategoriaController

#### Get all Categorias

```http
  GET /api/categorias
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| None      | None     | Get all Categorias from database    |

#### Add Categoria

```http
  POST /api/categoria/agregar
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `categoria` | object   | **Required**. Categoria to add    |

#### Update Categoria

```http
  POST /api/categoria/actualizar
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `categoria`

 | object   | **Required**. Categoria to update  |

#### Check if Categoria exists

```http
  GET /api/categoria/existe/{id}
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `id`      | integer  | **Required**. ID of Categoria       |

#### Get Categoria by ID

```http
  GET /api/categoria/{id}
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `id`      | integer  | **Required**. ID of Categoria       |

#### Count all Categorias

```http
  GET /api/categorias/todos
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| None      | None     | Count of all Categorias in database |

#### Delete Categoria by ID

```http
  DELETE /api/categoria/{id}
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `id`      | integer  | **Required**. ID of Categoria       |

#### Delete Categoria

```http
  DELETE /api/categoria/eliminar
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| `categoria` | object   | **Required**. Categoria to delete |

---

### EditorialController

#### Get all Editoriales

```http
  GET /api/editoriales
```

| Parameter | Type     | Description                         |
| :-------- | :------- | :---------------------------------- |
| None      | None     | Get all Editoriales from database   |

#### Add Editorial

```http
  POST /api/editorial/agregar
```

| Parameter  | Type     | Description                             |
| :--------- | :------- | :-------------------------------------- |
| `editorial`| object   | **Required**. Editorial to add         |

#### Update Editorial

```http
  POST /api/editorial/actualizar
```

| Parameter  | Type     | Description                             |
| :--------- | :------- | :-------------------------------------- |
| `editorial`| object   | **Required**. Editorial to update      |

#### Check if Editorial exists

```http
  GET /api/editorial/existe/{id}
```

| Parameter | Type     | Description                           |
| :-------- | :------- | :------------------------------------ |
| `id`      | integer  | **Required**. ID of Editorial         |

#### Get Editorial by ID

```http
  GET /api/editorial/{id}
```

| Parameter | Type     | Description                           |
| :-------- | :------- | :------------------------------------ |
| `id`      | integer  | **Required**. ID of Editorial         |

#### Count all Editoriales

```http
  GET /api/editoriales/todos
```

| Parameter | Type     | Description                           |
| :-------- | :------- | :------------------------------------ |
| None      | None     | Count of all Editoriales in database  |

#### Delete Editorial by ID

```http
  DELETE /api/editorial/{id}
```

| Parameter | Type     | Description                           |
| :-------- | :------- | :------------------------------------ |
| `id`      | integer  | **Required**. ID of Editorial to delete|

#### Delete Editorial

```http
  DELETE /api/editorial/eliminar
```

| Parameter  | Type     | Description                             |
| :--------- | :------- | :-------------------------------------- |
| `editorial`| object   | **Required**. Editorial to delete      |

```
```
## Tech Stack

**Server:** Java, Spring Security, Hibernate, Spring Boot

## Top Libraries

Lombok 

Google Guava

Apache Commons
```

```
## Screenshots

![image](https://github.com/user-attachments/assets/4db4b054-5710-4af0-a5ae-1604909eeb94)

```


