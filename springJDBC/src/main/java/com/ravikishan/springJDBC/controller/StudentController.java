package com.ravikishan.springJDBC.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravikishan.springJDBC.model.Student;
import com.ravikishan.springJDBC.service.StudentService;



@RestController 
@RequestMapping ("/api/student/")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping 
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        studentService.createStudent(student);
        return ResponseEntity.ok("Done!");
    }
    @PutMapping ("/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody Student student, @PathVariable Long id){
        studentService.updateStudent(student,id);
        return ResponseEntity.ok("Done!");
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id ){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Done!");
    }
    @GetMapping ("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping 
    public ResponseEntity<List<Student>> getStudent(){
        return ResponseEntity.ok(studentService.getStudent());
    }
    
    
}
