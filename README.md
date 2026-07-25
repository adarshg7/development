**Leave Management System**

A backend Leave Management System built using Spring Boot, Spring Data JPA, and MySQL. The application provides REST APIs to manage employees, departments, skills, projects, leave types, and leave requests using a clean layered architecture.

**Features**
Employee Management (CRUD)
Department Management (CRUD)
Skill Management (CRUD)
Project Management (CRUD)
Leave Type Management (CRUD)
Leave Request Management (CRUD)
Employee–Department Relationship (Many-to-One)
Employee–Skill Relationship (Many-to-Many)
Employee–Project Relationship (Many-to-Many)
Leave Request–Employee Relationship (Many-to-One)
Leave Request–Leave Type Relationship (Many-to-One)
DTO-based request and response handling
Mapper layer for Entity ↔ DTO conversion
Repository pattern using Spring Data JPA
RESTful APIs
MySQL database integration

**Tech Stack**
Java 21+ (or your installed version)
Spring Boot
Spring Data JPA (Hibernate)
MySQL
Maven
Lombok
REST API

**Project Structure**
```text
src
└── main
    ├── java
    │   └── com.employee.leaveManagement
    │       ├── controller
    │       ├── service
    │       │   └── impl
    │       ├── repository
    │       ├── entity
    │       ├── dto
    │       │   ├── employee
    │       │   ├── department
    │       │   ├── project
    │       │   ├── skill
    │       │   ├── leaveRequest
    │       │   └── leaveType
    │       ├── mapper
    │       └── LeaveManagementApplication.java
    └── resources
        └── application.properties
```
        
**Database Schema**

The project contains the following entities:

Employee
Department
Skill
Project
Leave Type
Leave Request
Relationships
```text
Department
      │
      │ One Department
      ▼
Employee
      │
      ├───────────────┐
      │               │
Many Skills      Many Projects
      │               │
      ▼               ▼
    Skill         Project

Employee
     │
     │ One Employee
     ▼
Leave Request
     ▲
     │
One Leave Type
     │
Leave Type
```

**REST APIs**
Employee
Method	Endpoint	Description
POST	/employees	Create Employee
GET	/employees	Get All Employees
GET	/employees/{id}	Get Employee By Id
PUT	/employees/{id}	Update Employee
DELETE	/employees/{id}	Delete Employee
Department
Method	Endpoint
POST	/departments
GET	/departments
GET	/departments/{id}
PUT	/departments/{id}
DELETE	/departments/{id}
Skill
Method	Endpoint
POST	/skills
GET	/skills
GET	/skills/{id}
PUT	/skills/{id}
DELETE	/skills/{id}
Project
Method	Endpoint
POST	/projects
GET	/projects
GET	/projects/{id}
PUT	/projects/{id}
DELETE	/projects/{id}
Leave Type
Method	Endpoint
POST	/leave-types
GET	/leave-types
GET	/leave-types/{id}
PUT	/leave-types/{id}
DELETE	/leave-types/{id}
Leave Request
Method	Endpoint
POST	/leave-requests
GET	/leave-requests
GET	/leave-requests/{id}
PUT	/leave-requests/{id}
DELETE	/leave-requests/{id}

**Architecture**

The project follows a layered architecture:

Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL Database

DTOs and Mappers are used to separate API models from database entities.

**Concepts Demonstrated**
Spring Boot
Spring Data JPA
Hibernate ORM
REST API Development
Layered Architecture
DTO Pattern
Mapper Pattern
Dependency Injection
JPA Relationships
CRUD Operations
MySQL Integration
Maven Project Structure

