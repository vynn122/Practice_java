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


/**
 *
 * @author ratha
 */
public class StaffDAO {
    public static List<Categories> getAllData(){
        List<Categories> category = new ArrayList();
        
        try(Connection con = DatabaseConnection.getConnection()){
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery("Select * From Staffs");
           while(rs.next()){
               category.add(new Categories(rs.getInt("CategoryID"),rs.getString("CategoryName"),rs.getString("CategoryDesc")));
           }
        }catch(SQLException e){
            e.printStackTrace();
        }        
        return category;
    }   
    
    
    public static List<Categories> searchData(String q){
        List<Categories> category = new ArrayList();
        String sql = "Select * From Categories Where CategoryName Collate SQL_Latin1_General_CP437_BIN2 like '%' + ?  + '%' ";
        try(Connection con = DatabaseConnection.getConnection()){
           PreparedStatement stmt = con.prepareStatement(sql);
           stmt.setString(1, q);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
               category.add(new Categories(rs.getInt("CategoryID"),rs.getString("CategoryName"),rs.getString("CategoryDesc")));
           
           }
        }catch(SQLException e){
            e.printStackTrace();
        }        
        return category;
    }
    
    public static void insert(Categories category) {
        String sql = "Insert into Categories (CategoryName, CategoryDesc) Values(?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getCategoryName());
            stmt.setString(2, category.getCategoryDesc());
            stmt.executeUpdate();
            System.out.println("Insert successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void update(Categories category) {
        String sql = "Update Categories set " +
                    "	CategoryName = ? , " +
                    "	CategoryDesc = ? " +
                    "Where CategoryID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getCategoryName());
            stmt.setString(2, category.getCategoryDesc());
            stmt.setInt(3, category.getCategoryID());
            stmt.executeUpdate();
            System.out.println("Insert successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Categories category) {
        String sql = "Delete From Categories " +
                        "Where CategoryID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, category.getCategoryID());
            stmt.executeUpdate();
            System.out.println("Delete successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
