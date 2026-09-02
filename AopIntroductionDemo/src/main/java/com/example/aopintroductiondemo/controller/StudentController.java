package com.example.aopintroductiondemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aopintroductiondemo.dto.Student;
import com.example.aopintroductiondemo.service.StudentService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/students/")
public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService) {
    this.studentService = studentService;
   }  

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        studentService.createStudent(student);

        return ResponseEntity.ok("Done");


     }

    
}
