<p align="center">
 <img src="https://github.com/magnusgbjerke/tcs-backend/blob/main/hanger.svg" width="170"/>
</p>

<h3 align="center">The Clothing Store</h3>

<p align="center">
    <i>Offering stylish and trendy fashion for all.</i>
    <br />
<br />
    <a href="#Introduction"><strong>Introduction</strong></a> ·
    <a href="#Features"><strong>Features</strong></a> ·
    <a href="#Technologies"><strong>Technologies</strong></a> ·
    <a href="#Getting-Started"><strong>Getting Started</strong></a>
</p>


## Introduction
<!--- Short description --->
The Clothing Store is an online clothing store created as a part of a school project. This is the backend component.

## Technologies

- **Backend**: Spring Boot with Maven and Java
- **API Docs**: Swagger UI and OpenAPI v3
- **DB**: PostgreSQL

## Getting Started

- Requires JDK 21.
- Requires a PostgreSQL server running on port 5432 with username: postgres and password: postgrespassword.
- Requires a database named "tcs" on the PostgreSQL server.
- Requires a keycloak server running on port 8081 and import of the realm-export.json. Or you can build the keycloak-server with Docker:

  ```bash
  
  cd tcs-backend
  
  docker build -t keycloak -f keycloak.Dockerfile .
  
  docker run --name keycloak -d -p 8081:8081 keycloak
  
  ```

- Run OnlinestoreApplication

The app should now be running on localhost:8080.

Swagger UI --> [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

OpenAPI v3 --> [localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs).
