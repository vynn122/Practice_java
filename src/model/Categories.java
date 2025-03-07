package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ratha
 */
public class Categories {

    //Colums of table
    
    public Categories(int CategoryID, String CategoryName, String CategoryDesc) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.CategoryDesc = CategoryDesc;
        
    }
    
    int CategoryID;
    String CategoryName;
    String CategoryDesc;
    

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getCategoryDesc() {
        return CategoryDesc;
    }

    public void setCategoryDesc(String CategoryDesc) {
        this.CategoryDesc = CategoryDesc;
    }

}
