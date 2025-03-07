/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import db.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Categories;
import model.Users;


/**
 *
 * @author ratha
 */
public class UserDAO {
    public static List<Users> getAllData(){
        List<Users> user = new ArrayList();
        
        try(Connection con = DatabaseConnection.getConnection()){
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery("Select * From User_Tbl");
           while(rs.next()){
               user.add(new Users(rs.getInt("UserID"),rs.getString("UserName"),rs.getString("UserPassword")));
           }
        }catch(SQLException e){
            e.printStackTrace();
        }        
        return user;
    }       
    
    public static List<Users> searchData(String q){
        List<Users> user = new ArrayList();
        String sql = "Select * From User_Tbl Where UserName like '%' + ? + '%' ";
        try(Connection con = DatabaseConnection.getConnection()){
           PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1, q);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               user.add(new Users(rs.getInt("UserID"),rs.getString("UserName"),rs.getString("UserPassword")));
           
           }
        }catch(SQLException e){
            e.printStackTrace();
        }        
        return user;
    }
    
    public static boolean getLogin(String UserName, String UserPassword){
        boolean b = false;
        String sql = "Select * From User_Tbl Where UserName = ? and UserPassword = ? ";
        try(Connection con = DatabaseConnection.getConnection()){
           PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1, UserName);
           stmt.setString(2, UserPassword);
           ResultSet rs = stmt.executeQuery();           
           rs.next();
           b = true;
        }catch(SQLException e){
            b = false;
            e.printStackTrace();
        }        
        return b;
    }
    
    public static void insert(Users user) {
        String sql = "Insert into User_Tbl (UserName, UserPassword) Values(?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserPassword());
            stmt.executeUpdate();
            System.out.println("Insert successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void update(Users user) {
        String sql = "Update User_Tbl set " +
                    "	UserName = ? , " +
                    "	UserPassword = ? " +
                    "Where UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserPassword());
            stmt.setInt(3, user.getUserID());
            stmt.executeUpdate();
            System.out.println("Insert successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Users user) {
        String sql = "Delete From User_Tbl " +
                        "Where UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getUserID());
            stmt.executeUpdate();
            System.out.println("Delete successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
