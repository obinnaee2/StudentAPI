HOW TO CREATE ANY API (USING USER API AS A CASE STUDY)

1. ) Set up the Spring Boot project:

Create a new Spring Boot project using the Spring Initializer 
Choose dependencies which area : Spring Web, Spring Data JPA, and Spring Validation and mySql driver .

2.) Create the  Entity class :

Create a User class that represents the user data.
Add all important fields, such as id, firstName, lastName, email, password, etc.
Annotate the class with JPA annotations (@Entity, @Table, @Id, @GeneratedValue, etc.) to map it to a database table.
Add validation annotations, such as @NotBlank, @Email, and @Size, to ensure data integrity.

3.) Create the Repository Interface :

Create UserRepository interface that extends JpaRepository<User, Integer> or any other appropriate data type for the id field.
This interface will provide the basic CRUD (Create, Read, Update, Delete) operations for the entity class which you defined in step 1.

4.)Implement the User Service:

Create a UserService class and annotate it with @Service.
Inject the UserRepository into the service class.
Implement the necessary methods for CRUD operations, such as getAllUsers(), getUserById(int id), createUser(User user), updateUser(int id, User user), and deleteUser(int id).

4.) Create the User Controller:

Create a UserController class and annotate it with @RestController and @RequestMapping("/api/users").
Inject the UserService into the controller class.
Implement the necessary REST API endpoints, such as:
@GetMapping("/"): Get all users
@GetMapping("/{id}"): Get a user by ID
@PostMapping("/"): Create a new user
@PutMapping("/{id}"): Update an existing user
@DeleteMapping("/{id}"): Delete a user

5.) Handle Exceptions:

Create a GlobalExceptionHandler class and annotate it with @RestControllerAdvice.
Implement exception handling methods to handle common exceptions, such as MethodArgumentNotValidException (for validation errors) and ResponseStatusException (for custom error handling).
Provide appropriate HTTP status codes and error responses.
Configure the Application Properties:

6.) Update the application.properties file with the necessary database connection details (URL, username, password).
