package com.ravi.repository;

import java.util.ArrayList;
import java.util.List;

import com.ravi.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepository {

    String url = "Database_URL";
    String username = "neondb_owner";
    String password = "Database_Password";

    public void createStudent(Student student){

        String sql = """
                        INSERT INTO students(name, email, age) VALUES (?, ?, ?)""";

        try(
             Connection connection = DriverManager.getConnection(url,username,password);
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ){
           preparedStatement.setString(1, student.getName());
           preparedStatement.setString(2, student.getEmail());
           preparedStatement.setInt(3, student.getAge());
            
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected == 1){
                System.out.println("Create operation sucessful");
            }
            else{
                System.out.println("No student was created.");
            }
        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student, Long id){

        String sql = """
                        UPDATE students 
                        SET name = ?, 
                        email = ?, 
                        age = ?
                        WHERE id = ?""";
        try(
            Connection connection = DriverManager.getConnection(url,username,password);
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setInt(3,student.getAge());
            preparedStatement.setLong(4,id);

            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected == 1){
                System.out.println("Update operation sucessful");
            }
            else{
                System.out.println("no student was updated.");
            }
        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long id){
        String sql = """
                DELETE FROM students WHERE id = ?
                """;
        try(
             Connection connection = DriverManager.getConnection(url,username,password);
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
           
            preparedStatement.setLong(1,id);
            

            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected == 1){
                System.out.println("Delete operation sucessful");
            }
            else{
                System.out.println("No student was deleted.");
            }
            
        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }

    public void getStudentById(Long id){
         String sql = """
         SELECT * FROM students WHERE id = ?
         """;
        try(
            Connection connection = DriverManager.getConnection(url,username,password);
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ){
            
            preparedStatement.setLong(1,id);
           
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                 if(resultSet.next()){
                    Student student = mapRow(resultSet);
                    System.out.println(student);
                 }
                 else{
                    System.out.println("No student was read");
                 }

            }
            catch(SQLException e){
                e.printStackTrace();
            }

        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }

    public void getStudent(){
         String sql = """
         SELECT * FROM students
         """;
        try(
            Connection connection = DriverManager.getConnection(url,username,password);
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
           
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<Student> studentList = new ArrayList<>(); 
                 while(resultSet.next()){
                    Student student = mapRow(resultSet);
                    studentList.add(student);
                    System.out.println(student);
                 }

            }
            catch(SQLException e){
                e.printStackTrace();
            }

        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }


    public void complateCRUD(){

         String sql = """
                        SELECT * FROM students WHERE id = ?
                 
                     """;
            // String sql = """
            //              INSERT INTO students(name, email, age) VALUES (?,?,?)
            //              """;
         try(
            Connection connection = DriverManager.getConnection(url,username,password);
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ){
                // it for read student
            preparedStatement.setLong(1,4);

            // it for create student
            // preparedStatement.setString(1,"Ravikishan");
            // preparedStatement.setString(2,"ravi@kishan.com");
            // preparedStatement.setInt(3,17);

           
            boolean result = preparedStatement.execute();

            if(result){
                try(ResultSet resultSet = preparedStatement.getResultSet()){
                    if(resultSet.next()){
                    Student student = mapRow(resultSet);
                    System.out.println(student);
                    }
                }
                
            }
            else{
                int rowAffected = preparedStatement.getUpdateCount();

                if(rowAffected == 1){
                System.out.println("Operation sucessful");
                }
                else{
                System.out.println("No student operation happend.");
                }
            }
        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }

    public Student mapRow(ResultSet resultSet) throws SQLException{
        Student student = new Student();

        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));


        return student;
    }
}
