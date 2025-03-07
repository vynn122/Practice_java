package dao;

import db.DatabaseConnection;
import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static void insert(Student student) {
        String sql = "INSERT INTO [dbo].[Students]  ([Name],[DOB],[Address])  VALUES(?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDob());
            stmt.setString(3, student.getAddress());
            stmt.executeUpdate();
            System.out.println("Student insert successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void update(Student student) {
        String sql = "UPDATE Students SET Name = ? ,DOB = ? ,Address = ? WHERE StudentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDob());
            stmt.setString(3, student.getAddress());
            stmt.setInt(4, student.getId());
            
            stmt.executeUpdate();
            System.out.println("Student update successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void delete(Student student) {
        String sql = "DELETE from Students WHERE StudentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, student.getId());
            stmt.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             
             ResultSet rs = stmt.executeQuery(sql))
            
        {
            while (rs.next()) {
                students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"), rs.getString("DOB"), rs.getString("Address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    
    public static List<Student> searchStudents(String q) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students Where [Name] collate SQL_Latin1_General_CP437_BIN2 like '%' + N'"+ q +"' + '%'  ";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             //stmt.setString(1, q);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {              
              students.add(new Student(rs.getInt("StudentID"), rs.getString("Name"), rs.getString("DOB"), rs.getString("Address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    
    /*
    public static List<Categories> getAllData() {
        List<Categories> categories = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categories.add(new Categories(rs.getInt("StudentID"), rs.getString("Name"), rs.getString("DOB")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
*/
}

