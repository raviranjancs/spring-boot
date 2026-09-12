package com.ravi;

import com.ravi.repository.StudentRepository;

public class Main {
    public static void main(String[] args){
        StudentRepository studentRepository = new StudentRepository();
        // studentRepository.createStudent();
        // studentRepository.updateStudent();
        // studentRepository.deleteStudent();
        studentRepository.getStudentById();
        
    }
}