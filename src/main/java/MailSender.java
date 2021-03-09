/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
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

/**
 *
 * This class sends Emails to certain participants
 */
public class MailSender {
    /**
     * This method gets data from database and saves data as String 
     * Participant email Text
     * @param s get a String
     * @return  return string
     * @throws SQLException catch Exception of database
     */
        public static String getData(String s) throws SQLException {
        String msg ="";
        Connection con = DBconnection.connectToDatabase();
        int id = ICalendarFrame.user_id;
        PreparedStatement pstmt = (PreparedStatement)
        con.prepareStatement("SELECT events.eventName, events.eventDate,events.eventTime, events.location, users.fname, users.lname FROM events, users where UserID =  '" + id + "'");
        ResultSet rs = pstmt.executeQuery();
        try {
            while (rs.next()) {
       
                msg= "Information! \nYou are going to have a(n) " + rs.getString("eventName") + " on " + rs.getString("eventDate") + "at "+rs.getString("eventTime")+ " in " +  rs.getString("location")+ ", with " +rs.getString("fname")+" " +rs.getString("lname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }     

/**
 * This method gets data from database and saves data as String 
 * Reminder Text
 * @param s get a string
 * @return return a message
 * @throws SQLException catch Exception of database
 */

    public static String getDataReminder(String s) throws SQLException {
        String msg ="";
        Connection con = DBconnection.connectToDatabase();
        int id = ICalendarFrame.user_id;
        PreparedStatement pstmt = (PreparedStatement)
        con.prepareStatement("SELECT eventName, eventDate,eventTime, location, reminder FROM events where UserID =  '" + id + "'");

        ResultSet rs = pstmt.executeQuery();
        try {
            while (rs.next()) {
                msg= "Reminder! \nYour appointment , " + rs.getString("eventName") + ", will take place on " + rs.getString("eventDate") + " in " +  rs.getString("location")+ ", it will start in " +rs.getString("reminder")+" at " +rs.getString("eventTime")+" .";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
     /**
      * This method sends email to the participant that user chooses once he adds an event
      * @param recepient get a string
      * @throws SQLException    catch Exception of database
      */
    public static void sendMail(String recepient) throws SQLException{
        
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
          
            
            String _mailAddress = "icalendarjavaproject@gmail.com";
            String _pass = "Java2021!!";
            Session session;
            session = Session.getInstance(props, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(_mailAddress , _pass);
                }
            });
            Message message = prepareMessage(session , _mailAddress, recepient);
            
            Transport.send(message);
            System.out.println("Messege has been sent");
        } catch (MessagingException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    
    /**
     * This method prepares the message, that should be sent to the chosen participant
     * @param session      get session
     * @param _mailAddress get the Emailaddress
     * @param recepient    get recepient
     * @return             return message
     * @throws SQLException catch Exception of database
     */
    private static Message prepareMessage(Session session, String _mailAddress, String recepient) throws SQLException{
       Message message = new MimeMessage(session);
       String s="";
        try{
         
        message.setFrom(new InternetAddress(_mailAddress));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("Event");
        String msg = getData(s);
        message.setText(msg);
        } catch(MessagingException ex){
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
         
    }
    /**
     * This method sends an email as a reminder to the user 
     * @param recepient     get recepient
     * @throws SQLException catch Exception of database
     */
     public static void sendReminder(String recepient) throws SQLException{
        
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
          
            
            String _mailAddress = "icalendarjavaproject@gmail.com";
            String _pass = "Java2021!!";
            Session session;
            session = Session.getInstance(props, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(_mailAddress , _pass);
                }
            });
            Message message = prepareReminder(session , _mailAddress, recepient);
            
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    /**
     * This method prepares the reminder message, that should be sent to the user
     * @param session       get session
     * @param _mailAddress  get Emailaddress
     * @param recepient     get recepient
     * @return              return message
     * @throws SQLException 
     */
    
    private static Message prepareReminder(Session session, String _mailAddress, String recepient) throws SQLException{
       Message message = new MimeMessage(session);
       String s="";
        try{
         
        message.setFrom(new InternetAddress(_mailAddress));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("Reminder");
        String msg = getDataReminder(s);
        message.setText(msg);
        } catch(MessagingException ex){
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
         
    }
    
    /**
     * The reminder method sends a reminder to the participants.
     * the TimerTask will send the email and the Schedule receive the
     * date from another method
     */

    public static void reminder() throws ParseException{
        
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                try {
                   // 
                    sendReminder(Event.Email);
                } catch (SQLException ex) {
                    Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        };
        Timer t = new Timer();
        TimeCalculate t_event = new TimeCalculate();
        t.schedule(timer,t_event.getReminderDate());
        //t.schedule(timer, LocalDateTime.now(), 1);
       
        
    }
    
    
    
}

