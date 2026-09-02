package com.example.aopintroductiondemo.repository;

import org.springframework.stereotype.Repository;

import com.example.aopintroductiondemo.dto.Student;

@Repository
public class StudentRepository {
    public void save(Student student) {
        System.out.println("Student saved");

    }
}
