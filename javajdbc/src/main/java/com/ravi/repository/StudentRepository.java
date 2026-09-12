package com.ravi.repository;

import java.sql.Statement;

import com.ravi.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepository {
    String url = "DATABASSE_URL";
    String username = "DATABASE_USERNAME";
    String password = "DATABASE_PASSWORD";

    public void createStudent(){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO students(name, email, age) VALUES ('Ravi','ravi@ravi.com',21)";
            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected == 1){
                System.out.println("Create operation sucessful");
            }
            else{
                System.out.println("connection faild!");
            }
            connection.close();
        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }

    public void updateStudent(){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            
            Statement statement = connection.createStatement();

            String sql = "UPDATE students SET name = 'Amit Bind', email = 'amit@amit.com', age = 23 WHERE id = 1";

            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected == 1){
                System.out.println("Update operation sucessful");
            }
            else{
                System.out.println("connection faild!");
            }
            connection.close();
        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }

    public void deleteStudent(){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            
            Statement statement = connection.createStatement();

            String sql = "DELETE FROM students WHERE id = 1";

            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected == 1){
                System.out.println("Delete operation sucessful");
            }
            else{
                System.out.println("connection faild!");
            }
            connection.close();
        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }

    public void getStudentById(){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM students WHERE id = 2";
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();

            Student student = mapRow(resultSet);

            System.out.println(student);

            connection.close();
        }
        catch(SQLException e){
            System.out.println("Database connection faild! ");
            e.printStackTrace();
        }
    }

    public void complateCRUD(){
         try{
            Connection connection = DriverManager.getConnection(url,username,password);
            
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM students WHERE id = 7";
            boolean result = statement.execute(sql);

            if(result){
                ResultSet resultSet = statement.getResultSet();
            }
            else{
                int rowAffected = statement.getUpdateCount();
            }
            connection.close();
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
