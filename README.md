Student API Controller
This repository contains a RESTful API implemented in Java using Spring Boot for managing student information. The API provides endpoints for CRUD operations (Create, Read, Update, Delete) along with additional functionalities such as PATCH, HEAD, and OPTIONS requests.

Endpoints
GET /api/students: Retrieves a list of all students.
GET /api/students/{studentID}: Retrieves details of a specific student by ID.
POST /api/students: Adds a new student. Requires a JSON payload with student details.
PUT /api/students/{studentId}: Updates an existing student's details. Requires a JSON payload with updated student information.
DELETE /api/students/{studentId}: Deletes a student by ID.
PATCH /api/students/{studentId}: Partially updates a student's details based on provided fields in the request body.
HEAD /api/students/{studentId}: Checks for the existence of a student by ID without fetching the full resource.
OPTIONS /api/students/{studentId}: Retrieves the HTTP methods supported for a student resource.

Error Handling
The API returns appropriate HTTP status codes and messages, including 404 Not Found for resources that do not exist.
Input validation is handled using Jakarta Bean Validation (@Valid annotation) for request bodies.

Technologies Used
Spring Framework: For dependency injection and MVC architecture.
Jakarta Bean Validation: For validating input data.
Postman: For unit testing.

Usage
To use this API, ensure you have Java and Maven installed. Clone the repository, build the project using Maven, and deploy it to your preferred application server.



HERE'S HOW YOU CAN CREATE YOUR OWN API THAT IS JUST LIKE THIS : 

Set up the Spring Boot project:

Create a new Spring Boot project using the Spring Initializer.
Choose dependencies such as Spring Web, Spring Data JPA, Spring Validation, and a database driver (e.g., MySQL driver).

Create the Entity class:
Create a Student class that represents the student data.
Add fields such as id, firstName, lastName, email, age, grade, etc.
Annotate the class with JPA annotations (@Entity, @Table, @Id, @GeneratedValue, etc.) to map it to a database table.
Add validation annotations, such as @NotBlank, @Email, @Min, and @Max, to ensure data integrity.
Create the Repository Interface:

Create a StudentRepository interface that extends JpaRepository<Student, Integer> or any other appropriate data type for the id field.
This interface will provide the basic CRUD (Create, Read, Update, Delete) operations for the Student entity class.
Implement the Student Service:

Create a StudentService class and annotate it with @Service.
Inject the StudentRepository into the service class.
Implement the necessary methods for CRUD operations, such as getAllStudents(), getStudentById(int id), createStudent(Student student), updateStudent(int id, Student student), and deleteStudent(int id).
Create the Student Controller:

Create a StudentController class and annotate it with @RestController and @RequestMapping("/api/students").
Inject the StudentService into the controller class.
Implement the necessary REST API endpoints, such as:
`@GetMapping("/"): Get all students
`@GetMapping("/{id}"): Get a student by ID
`@PostMapping("/"): Create a new student
`@PutMapping("/{id}"): Update an existing student
`@DeleteMapping("/{id}"): Delete a student

Handle Exceptions:
Create a GlobalExceptionHandler class and annotate it with @RestControllerAdvice.
Implement exception handling methods to handle common exceptions, such as MethodArgumentNotValidException (for validation errors) and ResponseStatusException (for custom error handling).
Provide appropriate HTTP status codes and error responses.
Configure the Application Properties:

Update the application.properties file with the necessary database connection details (URL, username, password).
