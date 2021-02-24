
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public TimeCalculate() throws ParseException{
         int id = 1;       
        Connection connection = DBconnection.connectToDatabase();
        if(connection != null){
            try {
                  PreparedStatement pstmt = (PreparedStatement)
                  connection.prepareStatement("SELECT eventName, eventDate,eventTime, location, reminder FROM events where UserID =  '" + id + "'");
                  ResultSet rs = pstmt.executeQuery();
                  rs.next();
                  String dat = rs.getString("eventDate");
                  String time = rs.getString("eventTime");
                  TimeCalculate.kombo_reminder = rs.getString("reminder");
                  Date date_time = new SimpleDateFormat("hh:mm aaa").parse(time);
                  Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dat);
                  TimeCalculate.event_date = copyTimeToDate(date, date_time); 
          
                connection.close();
            }catch (SQLException ex){
                Logger.getLogger(ICalendarFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("NO DATABASE CONNECTION!");
        }
        
        
        if(kombo_reminder.equals("10 minutes") ){
            TimeCalculate.event_date = addMinutesToJavaDate(TimeCalculate.event_date, -10);
        }
        
        else if(kombo_reminder.equals("1 hour")){
            TimeCalculate.event_date = addHoursToJavaDate(TimeCalculate.event_date, -1);
        }
        
        else if(kombo_reminder.equals("3 days")){
            TimeCalculate.event_date = adddaysToJavaDate(TimeCalculate.event_date, -3);
        }
        
        else if(kombo_reminder.equals("1 week")){
            TimeCalculate.event_date = addWeeksToJavaDate(TimeCalculate.event_date, -1);
        }
           
    }
    
    /**
     * It duplicates a certain time and applies it to another wanted date
     * @param date
     * @param time
     * @return 
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
     * @param date
     * @param weeks
     * @return 
     */
    public static Date addWeeksToJavaDate(Date date, int weeks) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, weeks);
        return calendar.getTime();
    }

    /**
     * It adds certain days to another wanted date
     * @param date
     * @param days
     * @return 
     */
    public static Date adddaysToJavaDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
    
    /**
     * It adds certain hours to another wanted date
     * @param date
     * @param hours
     * @return 
     */
    public static Date addHoursToJavaDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
    
    
    /**
     * It adds certain minutes to another wanted date
     * @param date
     * @param minutes
     * @return 
     */
    public static Date addMinutesToJavaDate(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
    
    /**
     * It returns how sooner the reminder should be set
     * @return 
     */
    public Date getReminderDate(){
        return TimeCalculate.event_date;
    }
 
}
