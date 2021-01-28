

import java.sql.*;
import javax.swing.JOptionPane;

//Static class for connecting to database
public class DBconnection {
    private static final String databaseName = "user";

    //most used static method, returns connection
    public static Connection connectToDatabase(){

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin", "root","admin");
            System.out.println("Connected to MySQL");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("create database if not exists user");
            stmt.executeUpdate("USE user");
            System.out.println("Database created");
            PreparedStatement create = con.prepareStatement("create table if not exists users( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,fname VARCHAR(30),lname VARCHAR(30),uname VARCHAR(30),email VARCHAR(90),pass VARCHAR(60))"); //
            create.executeUpdate(); 
           
            
        }catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return con;
    }
    
    private static void deleteDatabase()  {
        try {
            Connection con = connectToDatabase();
            Statement stmt = con.createStatement();
            String sql = "DROP DATABASE " + databaseName;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }
   


}
