# SpringAcidChallenge

### Levantar WebApp

ejecutar comando en el directorio del repo

```bash
mvnw spring-boot:run
```

### acceder al WebApp

acceder al siguiente link: http://localhost:8080/


### descripcion de los servicios

* subir imagen
POST : http://localhost:8080/users
BODY(application/json) :
{
  "userName": String,
  "image": String  
}

Descripcion:

userName :  nombre de usuario 
image: imagen codificada en base64

solo usuario "usuario1"  esta autorizado para subir imagen


* obtener imagen
get : http://localhost:8080/users

Descripcion

devuelve una imagen




### correr pruebas de los servicios

ejecutar comando en el directorio del repo

```bash
mvnw test
```

