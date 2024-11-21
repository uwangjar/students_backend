package com.example.student.controller;

import com.example.student.dto.ResponseDto;
import com.example.student.entity.Student;
import com.example.student.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class StudentController {
    private IStudentService iStudentService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/student")
    public ResponseEntity<ResponseDto> createStudent(@RequestBody Student student) {
        iStudentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Studen created successfully"));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/student")
    public ResponseEntity<List<Student>> fetchStudents() {
        List<Student> students = iStudentService.fetchMovies();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> fetchStudentDetails(@PathVariable Long id){
        Student student = iStudentService.fetchStudentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/student")
    public ResponseEntity<ResponseDto> updateStudent(@RequestBody Student student) {
            iStudentService.updateStudent(student);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200", "Student updated successfully"));

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/student/{id}")
    public ResponseEntity<ResponseDto> deleteStudent(@PathVariable Long id) {
            iStudentService.deleteStudent(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200", "Student deleted sucessfully"));

    }
}
