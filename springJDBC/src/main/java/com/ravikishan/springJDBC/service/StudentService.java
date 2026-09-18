package com.ravikishan.springJDBC.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ravikishan.springJDBC.model.Student;
import com.ravikishan.springJDBC.repository.StudentRepository;

@Service 
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student){
        studentRepository.createStudent(student);
    }

    public void updateStudent(Student student, Long id){
        studentRepository.updateStudent(student,id);
    }
    public void deleteStudent(Long id){
        studentRepository.deleteStudent(id);
    }
    public Student getStudentById(Long id){
        return studentRepository.getStudentById(id);
    }
    public List<Student> getStudent(){
        return studentRepository.getStudent();
    }
    
    
}
