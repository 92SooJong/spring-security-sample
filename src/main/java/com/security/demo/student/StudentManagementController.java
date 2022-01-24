package com.security.demo.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    public List<Student> getAllStudent(){
        return STUDENTS;
    }

    public void registerNewStudent(Student student){
        System.out.println("student = " + student);
    }

    public void deleteStudent(Integer studentId){
        System.out.println("studentId = " + studentId);
    }
    
    public void updateStudent(Integer studentId, Student student){
        System.out.println(String.format("%s %s",student, studentId));
    }

}
