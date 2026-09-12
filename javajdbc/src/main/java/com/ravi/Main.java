package com.ravi;

import com.ravi.repository.StudentRepository;

public class Main {
    public static void main(String[] args){
        StudentRepository studentRepository = new StudentRepository();
        // studentRepository.createStudent(new Student("Ravikishan","ravi@kishan.com",17));
        // studentRepository.updateStudent(new Student("Ankit Kumar","ankit@ankit.com",23),4L);
        // studentRepository.deleteStudent(6L);
        // studentRepository.getStudentById(6L);
        // studentRepository.getStudent();
        studentRepository.complateCRUD();
        
    }
}