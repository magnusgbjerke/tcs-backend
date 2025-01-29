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
The Clothing Store is an online clothing store created as a part of a school project. It is scheduled to be completed by Spring 2025. This is the backend component.

## Technologies

- **Backend**: Spring Boot(Maven, Java)
- **API Docs**: Swagger UI and OpenAPI v3
- **DB**: PostgreSQL

## Getting Started
Run locally or with Docker.

### Prerequisites
- Docker Desktop installed.

### Run with Docker
1. Clone the repository:
   
   ```bash
   git clone https://github.com/magnusgbjerke/tcs-backend.git
   ```

2. Run Docker Compose Up
   
    ```bash
    cd tcs-backend
    docker compose up -d
   ```
   
   The app should now be running on localhost:8080.

   Swagger UI --> [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

   OpenAPI v3 --> [localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs).

   PgAdmin(Web-version) --> [localhost:80](http://localhost:80).
