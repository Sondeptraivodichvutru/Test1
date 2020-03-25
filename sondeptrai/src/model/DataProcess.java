/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Asus
 */
public class DataProcess {
    public Connection getConnection(){
        
        Connection  conn = null;
        
        try {
            //nap trinh dieu khien.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //khai bao chuoi ket noi.
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=testJava";
            String user = "sa";
            String pass = "soncudon";
            try {
                conn = DriverManager.getConnection(url, user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    public boolean addProduct(Product p) {
        String sql = "INSERT INTO tblProduct VALUES(? , ?, ?, ?)";
        int result = 0;
        try {
            PreparedStatement pet = getConnection().prepareStatement(sql);
            //set tham so.
            pet.setString(1, p.getId());
            pet.setString(2, p.getName());
            pet.setFloat(3, p.getPrice());
            pet.setInt(4, p.getQuantity());
            result = pet.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result>0;
    }
    public ArrayList<Product> getData(){
        ArrayList<Product> list = new ArrayList<Product>();
        String sql = "SELECT * FROM tblProduct";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql); 
            while(rs.next())
            {
                Product p = new Product();
                p.setId(rs.getString(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getFloat(3));
                p.setQuantity(rs.getInt(4));
                list.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    
    }
    public boolean deleteProduct(String id){
        String sql = "DELETE FROM tblProduct WHERE _id=?";
        int result = 0;
        try {
            PreparedStatement pet = getConnection().prepareStatement(sql);
            pet.setString(1, id);
            result = pet.executeUpdate();
            pet.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result>0;
    }
    public boolean updateProduct(String id, String name, float price, int quantity){
        String sql ="UPDATE tblProduct SET _name = ?, _price = ?, _quantity=? WHERE _id=?";
        int result = 0;
        try {
            PreparedStatement pet = getConnection().prepareStatement(sql);
            pet.setString(4, id);
            pet.setString(1, name);
            pet.setFloat(2, price);
            pet.setInt(3, quantity);
            result = pet.executeUpdate();
            pet.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result > 0;
    }
}
