package com.school.studentApi;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api")
@Validated
public class studentController {

    @Autowired
    private studentService studentService;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{studentsID}")
    public Optional<Student> getStudent(@PathVariable int studentsID){
        Optional<Student> theStudent = studentService.getStudentById(studentsID);
        if (theStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id - " + studentsID);
        }
        return theStudent;
    }
    @PostMapping("/students")
    public Student addStudent(@Valid @RequestBody Student student) {
        student.setId(0);
        return studentService.saveStudent(student);
    }
    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable int studentId, @Valid @RequestBody Student updatedStudent) {
        Optional<Student> existingStudent = studentService.getStudentById(studentId);
        if (existingStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id - " + studentId);
        }
        Student studentToUpdate = existingStudent.get();
        studentToUpdate.setFirstName(updatedStudent.getFirstName());
        studentToUpdate.setLastName(updatedStudent.getLastName());
        studentToUpdate.setEmail(updatedStudent.getEmail());
        studentToUpdate.setCourse(updatedStudent.getCourse());
        studentToUpdate.setAge(updatedStudent.getAge());
        return studentService.saveStudent(studentToUpdate);
    }
    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId) {
        Optional<Student> studentToDelete = studentService.getStudentById(studentId);
        if (studentToDelete.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id - " + studentId);
        }
        studentService.deleteStudent(studentId);
        return "Deleted student with id " + studentId;
    }
    @PatchMapping("/students/{studentId}")
    public Student patchStudent(@PathVariable int studentId, @RequestBody Map<String, Object> updates) {
        Optional<Student> existingStudent = studentService.getStudentById(studentId);
        if (existingStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id - " + studentId);
        }
        Student studentToUpdate = existingStudent.get();

        if (updates.containsKey("firstName")) {
            studentToUpdate.setFirstName((String) updates.get("firstName"));
        }
        if (updates.containsKey("lastName")) {
            studentToUpdate.setLastName((String) updates.get("lastName"));
        }
        if (updates.containsKey("email")) {
            studentToUpdate.setEmail((String) updates.get("email"));
        }
        if (updates.containsKey("course")) {
            studentToUpdate.setCourse((String) updates.get("course"));
        }
        if (updates.containsKey("age")) {
            studentToUpdate.setAge((Integer) updates.get("age"));
        }

        return studentService.saveStudent(studentToUpdate);
    }
    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headStudent(@PathVariable int studentId) {
        Optional<Student> existingStudent = studentService.getStudentById(studentId);
        if (existingStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setCacheControl("max-age=3600, must-revalidate");
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/students/{studentId}", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsStudent(@PathVariable int studentId) {
        Optional<Student> existingStudent = studentService.getStudentById(studentId);
        if (existingStudent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.HEAD);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.PUT);
        allowedMethods.add(HttpMethod.DELETE);
        allowedMethods.add(HttpMethod.OPTIONS);
        allowedMethods.add(HttpMethod.PATCH);

        headers.setAllow(allowedMethods);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}

