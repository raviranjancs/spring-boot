package com.ravikishan.springJDBC.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ravikishan.springJDBC.model.Student;
@Repository 
public class StudentRepository {

    private JdbcTemplate jdbcTemplate;
    // private StudentRowMapper studentRowMapper = new StudentRowMapper();
    private RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);


    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createStudent(Student student){

        String sql = """
                        INSERT INTO students(name, email, age) VALUES (?, ?, ?)""";

        int rowAffected = jdbcTemplate.update(sql,student.getName(),student.getEmail(),student.getAge());
        if(rowAffected == 1){
            System.out.println("Create operation sucessful");
        }
        else{
            System.out.println("No student was created.");
        }
        
    }

    public void updateStudent(Student student, Long id){

        String sql = """
                        UPDATE students 
                        SET name = ?, 
                        email = ?, 
                        age = ?
                        WHERE id = ?""";
       
        int rowAffected = jdbcTemplate.update(sql,student.getName(),student.getEmail(),student.getAge(),id);
        if(rowAffected == 1){
            System.out.println("Update operation sucessful");
        }
        else{
            System.out.println("no student was updated.");
        }
        
    }

    public void deleteStudent(Long id){
        String sql = """
                DELETE FROM students WHERE id = ?
                """;

        int rowAffected = jdbcTemplate.update(sql,id);
        if(rowAffected == 1){
            System.out.println("Delete operation sucessful");
        }
        else{
            System.out.println("No student was deleted.");
        }
            
    }

    public Student getStudentById(Long id){
         String sql = """
         SELECT * FROM students WHERE id = ?
         """;
        return jdbcTemplate.queryForObject(sql, rowMapper,id);
        
    }

    public List<Student> getStudent(){
         String sql = """
         SELECT * FROM students
         """;
        
         List<Student> students = jdbcTemplate.query(sql, rowMapper);
         return students;

    }

}

