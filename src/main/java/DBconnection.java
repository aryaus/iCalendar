import java.sql.*;

public class DBconnection {
    private static final String databaseName = "user";
/**
 * 
 * most used static method, this method connect java application to MySQL database, and creates table users and events if tables do not exist in database
 * @return get connection
 */

    public static Connection connectToDatabase(){

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin", "root","");
            System.out.println("Connected to MySQL");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("create database if not exists user");
            stmt.executeUpdate("USE user");
            System.out.println("Database created");
            PreparedStatement create = con.prepareStatement("create table if not exists users( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,fname VARCHAR(30),lname VARCHAR(30),uname VARCHAR(30),email VARCHAR(90),pass VARCHAR(60))"); //
            create.executeUpdate();
            PreparedStatement createEvent = con.prepareStatement("create table if not exists events( ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,eventName VARCHAR(100), eventDate VARCHAR(100),eventTime VARCHAR(100),duration VARCHAR(100),"
                    + "location VARCHAR(100),participants VARCHAR(100),priority VARCHAR(100),reminder VARCHAR(100),userID int, FOREIGN KEY (userID) REFERENCES users (ID))"); 
            createEvent.executeUpdate(); 
           
            
        }catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return con;
    }
    /**
     * this method deletes the created database 
     */
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
