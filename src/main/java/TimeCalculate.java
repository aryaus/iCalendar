
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * This class calculates the date on which the reminder must be set
 */
public class TimeCalculate {
    
    private static Date event_date;
    private static String kombo_reminder = "none";
    
    
   
    
    /**
     * calculates the date on which the reminder must be set
     * @throws ParseException catch the Exception as parse
     */
    public TimeCalculate() throws ParseException{
         int id = 1;       
        
        Connection connection = DBconnection.connectToDatabase();
        if(connection != null){
            try {
                
                  PreparedStatement pstmt = (PreparedStatement)
                  connection.prepareStatement("SELECT  eventDate,eventTime, reminder FROM events where UserID =  '" + id + "'");
                  ResultSet rs = pstmt.executeQuery();
                  rs.next();
                  String dat = rs.getString("eventDate");
                  String time = rs.getString("eventTime");
                  kombo_reminder = rs.getString("reminder");
                  Date date_time = new SimpleDateFormat("hh:mm aaa").parse(time);
                  Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dat);
                  event_date = copyTimeToDate(date, date_time); 
          
                connection.close();
            }catch (SQLException ex){
                Logger.getLogger(ICalendarFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("NO DATABASE CONNECTION!");
        }
        
        
        
           
    }
    
    /**
     * It duplicates a certain time and applies it to another wanted date
     * @param date receive a date
     * @param time time from an other date
     * @return return Return the calculation of day
     */
    public static Date copyTimeToDate(Date date, Date time) {
        
        Calendar t = Calendar.getInstance();
        t.setTime(time);
        
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, t.get(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, t.get(Calendar.MINUTE));
        c.set(Calendar.SECOND, t.get(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, t.get(Calendar.MILLISECOND));
        return c.getTime();
    }
    
    /**
     * It adds certain weeks to another wanted date
     * @param date receive a date
     * @param weeks receive certain weeks
     * @return Return the calculation of day
     */
    public static Date addWeeksToJavaDate(Date date, int weeks) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, weeks);
        return calendar.getTime();
    }

    /**
     * It adds certain days to another wanted date
     * @param date receive a date
     * @param days receive certain days
     * @return Return the calculation of day
     */
    public static Date adddaysToJavaDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
    
    /**
     * It adds certain hours to another wanted date
     * @param date receive a date
     * @param hours receive certain hours
     * @return Return the calculation of day
     */
    public static Date addHoursToJavaDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
    
    
    /**
     * It adds certain minutes to another wanted date
     * @param date receive a date
     * @param minutes receive certain minutes
     * @return Return the calculation of day
     */
    public static Date addMinutesToJavaDate(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
    
    /**
     * It returns how sooner the reminder should be set
     * @return Return the calculation of day
     */
    public static Date getReminderDate(){
        if(kombo_reminder.equals("10 minutes") ){
            event_date = addMinutesToJavaDate(event_date, -10);
             
        }
        
        else if(kombo_reminder.equals("1 hour")){
            event_date = addHoursToJavaDate(event_date, -1);
            
        }
        
        else if(kombo_reminder.equals("3 days")){
            event_date = adddaysToJavaDate(event_date, -3);
        }
        
        else if(kombo_reminder.equals("1 week")){
            event_date = addWeeksToJavaDate(event_date, -1);
            
        }
        return event_date;
    }
 
}
