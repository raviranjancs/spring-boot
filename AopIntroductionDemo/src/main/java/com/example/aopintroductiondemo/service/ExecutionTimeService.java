package com.example.aopintroductiondemo.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.aopintroductiondemo.dto.Student;
@Component
@Primary
public class ExecutionTimeService implements StudentService{
    private LoggingDecorator loggingDecorator;

    public ExecutionTimeService(LoggingDecorator loggingDecorator) {
        this.loggingDecorator = loggingDecorator;
    }

    @Override
    public void createStudent(Student student) {
        long start = System.currentTimeMillis();

        loggingDecorator.createStudent(student);

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
