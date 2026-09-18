package com.ravikishan.jpaRelationships.service;

import com.ravikishan.jpaRelationships.model.Student;
import com.ravikishan.jpaRelationships.repository.StudentRepository;

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student){
        
    }
    public Student getStudent(long id){
        return new Student();
    }
    public void updateStudent(Student student, Long id){

    }

    public void deleteStudent(long id){
        
    }

    
}
