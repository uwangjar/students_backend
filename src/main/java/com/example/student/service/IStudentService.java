package com.example.student.service;

import com.example.student.entity.Student;

import java.io.IOException;
import java.util.List;

public interface IStudentService {
    public void createStudent(Student student);
    public List<Student> fetchStudents();
    public void readFileFromResources(String filename) throws IOException;
    public Student fetchStudentById(Long id);
    public void deleteStudent(Long id);
    public void updateStudent(Student student);
}
