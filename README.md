# SpringAcidChallenge

### Levantar WebApp

Ejecutar comando en el directorio del repo

```bash
mvnw spring-boot:run
```

### acceder al WebApp

Acceder al siguiente link: http://localhost:8080/


### Descripcion de los servicios

#### Subir imagen

```
POST : http://localhost:8080/users
BODY(application/json) :
{
  "userName": String,
  "image": String  
}
```
Descripcion:
```
userName :  nombre de usuario 
image: imagen codificada en base64

solo usuario "usuario1"  esta autorizado para subir imagen
```

#### Obtener imagen
```
GET : http://localhost:8080/users/1
```
Descripcion

Devuelve la imagen guardada




### correr pruebas de los servicios

Ejecutar comando en el directorio del repo

```bash
mvnw test
```

