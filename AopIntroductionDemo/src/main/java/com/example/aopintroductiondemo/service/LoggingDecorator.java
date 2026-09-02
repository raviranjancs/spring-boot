package com.example.aopintroductiondemo.service;

import org.springframework.stereotype.Component;

import com.example.aopintroductiondemo.dto.Student;
@Component
public class LoggingDecorator implements StudentService {

    private StudentServiceImpl studentServiceImpl;
    public LoggingDecorator(StudentServiceImpl studentServiceImpl){
        this.studentServiceImpl = studentServiceImpl;
    }
    @Override
    public void createStudent(Student student) {
         // Logging releated logic

        LoggingServiceUtil.logStart(
                "StudentServiceImpl", "createStudent");

        studentServiceImpl.createStudent(student);

        LoggingServiceUtil.logEnd(
                "StudentServiceImpl", "createStudent");
    }
    
}
