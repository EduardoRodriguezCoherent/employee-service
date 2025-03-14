# Employee Service

Microservice part of Gym Management System, this service contains endpoints to register new employees, assign roles 
(TRAINER, STAFF, ADMIN), and assign expertise based on the club availability. 

## Running Locally

### **Prerequisites**
To run the application locally, ensure you have the following:

- **MySQL** – Installed and running.
- **Java 17+** – Ensure you have Java Development Kit (JDK) installed.
- **Maven** – To build and run the project.
- **Docker (optional)** – If you prefer running MySQL in a container.
- **Discovery Service** – This microservice relies on a service discovery component. Ensure it is running before starting this service.
- **Gym Club Service** – This microservice relies on a gym club service, since it is used to validate the expertise are,
a.k.a. facility in the **gym-club-service**, the employee service call synchronously the gym club service to do so.

## Setup Instructions

### 1. Start the Discovery Service
Before running this service, you need to start the Discovery Service to enable service registration and discovery.

```sh
cd path/to/discovery-service
mvn spring-boot:run
```

### 2. Start the Gym Club Service
After running this service, you need to start the Gym club Service since this one call it to validate expertise area.
Do not call this service directly, the logic is handled in **orchestrator-service**.

```sh
cd path/to/gym-club-service
mvn spring-boot:run
```

### 3. Configure Application Properties
Make sure the application’s database connection is correctly configured. Open src/main/resources/application.properties (or application.yml) and ensure 
the following configuration:

```properties
# Database Configuration (H2 for development)
spring.datasource.url=jdbc:h2:mem:gymemployee
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Run SQL scripts automatically
spring.datasource.initialization-mode=always
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:init-db-schema.sql
spring.sql.init.data-locations=classpath:populate-dev.sql
```

This microservice uses H2 in-memory database, so when it starts, automatically initializes the db and populate it using 
the **init-db-schema.sql** and **populate-dev.sql** files in the resources package.

### 4. Start the Employee Service
Once the Discovery Service is up and running, navigate to the directory of the employee microservice:

```bash
cd path/to/employee-service
```
Run the following command to build and start the application:

```bash
mvn spring-boot:run
```
This will start the Employee Service, which should now be available on http://localhost:8082 (or any configured port).

### **Employee Service API Endpoints**

- **GET** `/api/employees`  
  Retrieve a list of all employees available.
- **GET** `/api/employees/{id}`  
  Get detailed information of a specific employee by its unique ID.
- **POST** `/api/employees`  
  Creates a new employee.
- **POST** `/api/employees/{id}/roles`  
  Adds roles to an employee.
- **DELETE** `/api/employees/{id}/roles`  
  Removes roles to an employee.
- **POST** `/api/employees/{employeeId}/assign-expertise`  
  Validates expertise area to and the assigns it to teh TRAINER, to check if the area is valid, this service calls
the **gym-club-service** synchronously.

### **Troubleshooting**
1. Ensure that the Discovery Service is running before starting the Employee service.
2. Ensure that the Gym Club Service is running before calling this microservice.
3. H2 database does not support a huge amount of calls, neither running for too much time, restarting this microservice 
if needed.