package com.example.student.service.impl;

import com.example.student.entity.Student;
import com.example.student.exception.ResourceNotFoundException;
import com.example.student.exception.StudentAlreadyExistException;
import com.example.student.repository.StudentRepository;
import com.example.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private ResourceLoader resourceLoader ;

    @Autowired
    private StudentRepository studentRepository;
    public void createStudent(Student student) {
        if(studentRepository.findById(student.getId()).isPresent()) {
            throw new StudentAlreadyExistException("Student with same Id already exist");
        }
        LocalDateTime now = LocalDateTime.now();
        student.setCreatedAt(now);
        student.setCreatedBy("admin");
        studentRepository.save(student);

    }
    public List<Student> fetchStudents(){
        return studentRepository.findAll();
    }

    public void readFileFromResources(String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filename);
        InputStream inputStream = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //Skip the first line
        reader.readLine();
        List<Student> list= new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null ) {
            String[] row = line.split(",");
            Student student = createStudentObj(row);
            if(student!=null) {
                list.add(student);
            }
        }
        // Closing the BufferedReader
        reader.close();
        studentRepository.saveAll(list);

    }

    public void updateStudent(Student student){
        Student s = studentRepository.findById(student.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Student", "Student Id", String.valueOf(student.getId()))
        );
        LocalDateTime now = LocalDateTime.now();
        student.setUpdatedAt(now);
        student.setUpdatedBy("admin");
        studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        if(id !=null) {
            Student s = studentRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Student", "Student Id", String.valueOf(id))
            );
            studentRepository.deleteById(id);
        }
    }

    public Student fetchStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "id", String.valueOf(id))
        );

    }

    private Student createStudentObj(String[] row){
        if(row.length>=6) {
            Student student = new Student();
            student.setId(Long.parseLong(row[0]));
            student.setFirstName(row[1]);
            student.setLastName(row[2]);
            student.setLevel(row[3]);
            student.setGpa(Double.parseDouble(row[4]));
            student.setMajor(row[5]);
           return student;
        }
        return null;
    }


}
