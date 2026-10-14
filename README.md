# Student CRUD REST API

A Student Management REST API developed using **Java, Spring Boot, Spring Data JPA, and PostgreSQL**. This project implements CRUD (Create, Read, Update, Delete) operations to manage student records and provides hands-on experience with REST API development and database integration.

## 🚀 Features

- Create a new student record.
- Retrieve a student by ID.
- Retrieve all student records.
- Update existing student details.
- Delete a student record.
- Integrate PostgreSQL using Spring Data JPA.
- Test REST API endpoints using Postman.

## 🛠️ Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **API Testing:** Postman
- **IDE:** IntelliJ IDEA

## 📂 Project Structure

```text
student/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/practice/student/
│   │   │       ├── controller/
│   │   │       │   └── StudentController.java
│   │   │       ├── entity/
│   │   │       │   └── Student.java
│   │   │       ├── repository/
│   │   │       │   └── StudentRepository.java
│   │   │       ├── service/
│   │   │       │   └── StudentService.java
│   │   │       └── StudentApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## ⚙️ Prerequisites

Ensure the following are installed:

- Java JDK
- PostgreSQL
- Maven (optional if using the Maven Wrapper)
- Postman for API testing

## 🔧 Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/ronit-khupase/Student-CRUD-Practice.git
```

### 2. Navigate to the Project Directory

```bash
cd Student-CRUD-Practice
```

### 3. Configure PostgreSQL

Create a database in PostgreSQL and configure the database connection in `src/main/resources/application.properties`.

Example configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Replace the database name, username, and password with your actual PostgreSQL configuration.

**Security note:** Do not commit database passwords or other sensitive credentials to GitHub.

### 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively, on Windows, use the Maven Wrapper:

```bash
.\mvnw.cmd spring-boot:run
```

The application will run at:

`http://localhost:8080`

## 📡 REST API Endpoints

**Base URL:** `http://localhost:8080/api/students`

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/create` | Create a new student |
| GET | `/get/{id}` | Retrieve a student by ID |
| GET | `/get` | Retrieve all students |
| PUT | `/update/{id}` | Update student details |
| DELETE | `/delete/{id}` | Delete a student by ID |

### 1. Create a Student

**Method:** `POST`

**Endpoint:** `/api/students/create`

Request body:

```json
{
  "name": "Ronit Khupase",
  "email": "ronit@example.com",
  "age": 23,
  "subject": "Spring Boot"
}
```

### 2. Get a Student by ID

**Method:** `GET`

**Endpoint:** `/api/students/get/1`

Retrieves the student whose ID is `1`.

### 3. Get All Students

**Method:** `GET`

**Endpoint:** `/api/students/get`

Returns a list of all student records.

### 4. Update a Student

**Method:** `PUT`

**Endpoint:** `/api/students/update/1`

Request body:

```json
{
  "name": "Ronit Khupase",
  "email": "ronit.updated@example.com",
  "age": 23,
  "subject": "Spring Framework"
}
```

### 5. Delete a Student

**Method:** `DELETE`

**Endpoint:** `/api/students/delete/1`

Deletes the student whose ID is `1`.

## 🧪 API Testing

All REST API endpoints were tested using **Postman**.

The tests covered creating, retrieving, updating, and deleting student records, along with verifying database changes in PostgreSQL.

## 📚 Key Learnings

Through this project, I gained practical experience with:

- Developing REST APIs using Spring Boot.
- Understanding the Controller–Service–Repository architecture.
- Implementing CRUD operations using Spring Data JPA.
- Connecting Spring Boot with PostgreSQL.
- Understanding Inversion of Control (IoC) and Dependency Injection (DI).
- Working with Spring-managed beans.
- Testing REST endpoints using Postman.
- Debugging application and database integration issues.

## 🎯 Project Objective

This project was developed to strengthen my Java backend development skills and gain practical experience with the Spring ecosystem.

It represents my first independent hands-on project exploring Spring Boot REST APIs and PostgreSQL integration.

## 👨‍💻 Author

**Ronit Khupase**

GitHub: [ronit-khupase](https://github.com/ronit-khupase)

## 📌 Future Improvements

- Add input validation using Jakarta Bean Validation.
- Implement centralized exception handling.
- Improve HTTP status codes and API responses.
- Add unit and integration tests.
- Implement authentication and authorization using Spring Security.
