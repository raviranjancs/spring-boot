package com.example.aopintroductiondemo.service;

import org.springframework.stereotype.Service;

import com.example.aopintroductiondemo.dto.Student;
import com.example.aopintroductiondemo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
//        try {
//            Thread.sleep(2000);
//        }
//        catch(Exception e) {}

        studentRepository.save(student);
    }
    
}
