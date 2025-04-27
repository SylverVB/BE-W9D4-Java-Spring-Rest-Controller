# Spring Rest Controller - SampleController

This project demonstrates the creation of a simple REST controller using Spring Boot and Spring MVC. The `SampleController` defines several endpoints for handling sample data with basic HTTP request methods.

## Project Structure

- **src/main/java/com/app/Controller/SampleController.java**  
  Defines the REST endpoints for handling `Sample` data, including GET and POST methods.

- **src/main/java/com/app/Model/Sample.java**  
  Defines a simple model class to represent the data returned by the endpoints.

- **src/main/java/com/app/Application.java**  
  The Spring Boot application entry point with the main method to launch the application.

- **src/test/java/com/app/SampleControllerTest.java**  
  Contains unit tests for verifying the functionality of the `SampleController` endpoints.

## Endpoints

### 1. `GET /sample/`
Returns a default `Sample` object as a JSON response.

Example response:
```json
{
  "id": 1,
  "text": "sample text"
}
```

### 2. `GET /string/{text}`
Extracts a path variable and returns it as a string.

Example:  
Request: `GET /string/hello`  
Response: `"hello"`

### 3. `GET /long/{id}`
Extracts a path variable of type `long` and returns it.

Example:  
Request: `GET /long/1`  
Response: `1`

### 4. `POST /requestbody`
Extracts the request body, deserializes it into a `Sample` object, and returns the same object.

Example request body:
```json
{
  "id": 1,
  "text": "sample text"
}
```

Example response:
```json
{
  "id": 1,
  "text": "sample text"
}
```

## Project Setup

Clone the repository:

```bash
git clone https://github.com/SylverVB/BE-W9D4-Java-Spring-Rest-Controller.git
cd BE-W9D4-Java-Spring-Rest-Controller
```

Install dependencies and run the application:

```bash
./mvnw spring-boot:run
```

The application will be accessible at `localhost:9000`.

## Unit Tests

Unit tests are provided in the `src/test/java/com/app/SampleControllerTest.java` file and cover the following endpoints:

- `GET /long/{id}`
- `POST /requestbody`

Tests are executed using Spring's MockMvc and JUnit.

## Dependencies

The project uses the following dependencies:

- Spring Boot
- Spring Web
- JUnit
- Mockito