/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqldemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class MySQLdemo {

    public static Connection getConnection(){
        Connection conn = null;
        

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=testJava";
            try {
                conn = DriverManager.getConnection(url,"sa", "soncudon");
            } catch (SQLException ex) {
                Logger.getLogger(MySQLdemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {   
            Logger.getLogger(MySQLdemo.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return conn;
    }
    public static void main(String[] args) {
        String sql = "SELECT * FROM tblProduct";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("ProductID\tProductName\tPrice");
            while(rs.next())
            {
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLdemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
