# Student CRUD REST API

A RESTful Student Management API built using **Java, Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL**. The application implements CRUD operations for managing student records using a layered architecture with DTOs, input validation, and database integration.

## Features

* Create, retrieve, update, and delete student records.
* Retrieve individual students by ID or fetch all students.
* **DTO Layer:** Separate request and response models from database entities.
* **Input Validation:** Validate incoming requests using Jakarta Bean Validation.
* **Database Integration:** Persist student records using Spring Data JPA and PostgreSQL.
* **Layered Architecture:** Separate controller, service, repository, DTO, and entity responsibilities.
* **API Testing:** Test REST endpoints using Postman.

## Technologies Used

| Technology              | Purpose                                    |
| ----------------------- | ------------------------------------------ |
| Java                    | Backend programming                        |
| Spring Boot             | Application development                    |
| Spring Web              | REST API development                       |
| Spring Data JPA         | Database access and persistence            |
| Hibernate               | Object-relational mapping                  |
| PostgreSQL              | Relational database                        |
| Jakarta Bean Validation | Request validation                         |
| Maven                   | Dependency management and build automation |
| Postman                 | API testing                                |
| IntelliJ IDEA           | Development environment                    |
| Git and GitHub          | Version control                            |

## Project Structure

```text
student/
├── src/
│   ├── main/
│   │   ├── java/com/practice/student/
│   │   │   ├── controller/
│   │   │   │   └── StudentController.java
│   │   │   ├── dto/
│   │   │   │   ├── StudentReqDto.java
│   │   │   │   └── StudentResDto.java
│   │   │   ├── entity/
│   │   │   │   └── Student.java
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java
│   │   │   ├── service/
│   │   │   │   └── StudentService.java
│   │   │   └── StudentApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

### Architecture

The application follows a layered architecture:

* **Controller:** Handles HTTP requests and responses.
* **DTO:** Defines request and response data structures.
* **Entity:** Represents student records in the database.
* **Service:** Contains business logic and coordinates operations.
* **Repository:** Performs database operations using Spring Data JPA.

**Request flow:**

```text
Client / Postman
       |
       v
Student Controller
       |
       v
DTO Validation
       |
       v
Student Service
       |
       v
Student Repository
       |
       v
PostgreSQL Database
```

## Prerequisites

Ensure the following are installed:

* Java JDK compatible with the project's Spring Boot version.
* PostgreSQL.
* Git.
* Postman (optional, for API testing).

Maven is optional if you use the included Maven Wrapper.

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/ronit-khupase/EduTrack.git
```

### 2. Navigate to the Project Directory

```bash
cd EduTrack
```

### 3. Create a PostgreSQL Database

Execute the following SQL command in PostgreSQL:

```sql
CREATE DATABASE student_db;
```

### 4. Configure Database Connection

Open `src/main/resources/application.properties` and configure the connection:

```properties
spring.application.name=student

spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/student_db}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Set the `DB_PASSWORD` environment variable to your PostgreSQL password before starting the application.

**Security:** Never commit database passwords or other sensitive credentials to GitHub.

The `spring.jpa.hibernate.ddl-auto=update` setting is suitable for development. Production applications should use a controlled database migration strategy.

### 5. Run the Application

**Windows:**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

Alternatively, if Maven is installed:

```bash
mvn spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

**API base URL:**

```text
http://localhost:8080/api/students
```

## API Endpoint Documentation

All endpoints use the base path `/api/students`.

| HTTP Method | Endpoint                    | Description                |
| ----------- | --------------------------- | -------------------------- |
| POST        | `/api/students/create`      | Create a student           |
| GET         | `/api/students/get/{id}`    | Retrieve a student by ID   |
| GET         | `/api/students/get`         | Retrieve all students      |
| PUT         | `/api/students/update/{id}` | Update an existing student |
| DELETE      | `/api/students/delete/{id}` | Delete a student by ID     |
| PATCH       | `/api/students/delete-soft/{id}`|Mark student as Deleted by ID  | 

### 1. Create a Student

Creates a new student record.

**Request:**

```http
POST /api/students/create
Content-Type: application/json
```

**Request body:**

```json
{
  "name": "Ronit Khupase",
  "email": "ronit@example.com",
  "age": 23,
  "subject": "Spring Boot"
}
```

The request must satisfy the validation constraints defined in `StudentReqDto`.

### 2. Get a Student by ID

Retrieves a student using their unique ID.

**Request:**

```http
GET /api/students/get/1
```

**Example response:**

```json
{
  "id": 1,
  "name": "Ronit Khupase",
  "email": "ronit@example.com",
  "age": 23,
  "subject": "Spring Boot"
}
```

The response fields depend on the implementation of `StudentResDto`.

### 3. Get All Students

Retrieves all student records.

**Request:**

```http
GET /api/students/get
```

**Example response:**

```json
[
  {
    "id": 1,
    "name": "Ronit Khupase",
    "email": "ronit@example.com",
    "age": 23,
    "subject": "Spring Boot"
  },
  {
    "id": 2,
    "name": "Amit Patil",
    "email": "amit@example.com",
    "age": 22,
    "subject": "Java"
  }
]
```

The example records are illustrative; actual results depend on the database contents.

### 4. Update a Student

Updates the details of an existing student.

**Request:**

```http
PUT /api/students/update/1
Content-Type: application/json
```

**Request body:**

```json
{
  "name": "Ronit Khupase",
  "email": "ronit.updated@example.com",
  "age": 23,
  "subject": "Spring Framework"
}
```

The request must satisfy the validation constraints defined in `StudentReqDto`.

### 5. Delete a Student

Deletes an existing student by ID.

**Request:**

```http
DELETE /api/students/delete/1
```

The response status and body depend on the controller implementation.

### 6. Soft-Delete a Student

Marks Deleted to an existing student by ID.

**Request:**

```http
PATCH /api/students/delete-soft/1
```

The response status and body depend on the controller implementation.

## Input Validation

The request DTO uses Jakarta Bean Validation to validate incoming data.

Common validation annotations include:

| Annotation  | Purpose                                         |
| ----------- | ----------------------------------------------- |
| `@NotBlank` | Rejects null, empty, or whitespace-only strings |
| `@Email`    | Validates email format                          |
| `@Min`      | Defines a minimum numeric value                 |
| `@Max`      | Defines a maximum numeric value                 |

The actual validation rules depend on the annotations configured in `StudentReqDto`.

In the controller, validation is typically activated using `@Valid`:

```java
@Valid @RequestBody StudentReqDto studentReqDto
```

Invalid requests can return HTTP `400 Bad Request` when validation and exception handling are configured appropriately.

## Testing

The API can be tested using Postman.

Recommended test cases:

| Test Case                           | Expected Behaviour                                 |
| ----------------------------------- | -------------------------------------------------- |
| Create a student with valid details | Student is created                                 |
| Submit an invalid email             | Validation error                                   |
| Submit an invalid age               | Validation error if age constraints are configured |
| Retrieve an existing student        | Student details are returned                       |
| Retrieve a nonexistent student      | Appropriate not-found response                     |
| Update an existing student          | Student details are updated                        |
| Delete an existing student          | Student is deleted                                 |
| Submit malformed JSON               | Request is rejected                                |

Verify database changes using PostgreSQL or pgAdmin.

## Key Learnings

* Developing RESTful APIs using Spring Boot.
* Implementing CRUD operations.
* Understanding Controller–Service–Repository architecture.
* Using DTOs to separate API contracts from database entities.
* Applying Jakarta Bean Validation to incoming requests.
* Integrating PostgreSQL with Spring Data JPA and Hibernate.
* Understanding Inversion of Control (IoC) and Dependency Injection (DI).
* Working with Spring-managed beans and repositories.
* Testing HTTP endpoints using Postman.
* Debugging application and database integration issues.

## Future Improvements

* Centralized exception handling using `@RestControllerAdvice`.
* Consistent API response formats and HTTP status codes.
* Unit testing using JUnit and Mockito.
* Integration testing for REST endpoints.
* Pagination and sorting for student records.
* Database migrations using Flyway or Liquibase.
* API documentation using OpenAPI and Swagger UI.
* Authentication and authorization using Spring Security.
* Docker support for simplified deployment.
* Continuous integration using GitHub Actions.

## Author

**Ronit Khupase**

GitHub: [ronit-khupase](https://github.com/ronit-khupase)

---

Developed as a hands-on project to strengthen Java backend development skills using Spring Boot, REST APIs, DTOs, validation, and PostgreSQL.
