/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ARYA
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.event.Event;
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
 * @author ARYA
 */
public class MailSender {
    
        public static String getData(String s) throws SQLException {
        String msg ="";
        Connection con = DBconnection.connectToDatabase();
        int id = ICalendarFrame.user_id;
        PreparedStatement pstmt = (PreparedStatement)
        con.prepareStatement("SELECT events.eventName, events.eventDate,events.eventTime, events.location, users.fname, users.lname FROM events, users where UserID =  '" + id + "'");
        ResultSet rs = pstmt.executeQuery();
        try {
            while (rs.next()) {
       
                msg= "Information! \nYou are going to have a(n) " + rs.getString("eventName") + ", on " + rs.getString("eventDate") + ",at "+rs.getString("eventTime")+ " in " +  rs.getString("location")+ ", with " +rs.getString("fname")+" " +rs.getString("lname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }     



    public static String getDataReminder(String s) throws SQLException {
        String msg ="";
        Connection con = DBconnection.connectToDatabase();
        int id = ICalendarFrame.user_id;
        PreparedStatement pstmt = (PreparedStatement)
        con.prepareStatement("SELECT eventName, eventDate,eventTime, location, reminder FROM events where UserID =  '" + id + "'");

        ResultSet rs = pstmt.executeQuery();
   
        try {
            while (rs.next()) {
                msg= "Reminder! \nYour appointment , " + rs.getString("eventName") + ", will take place on " + rs.getString("eventDate") + ", in " +  rs.getString("location")+ ", it will start in " +rs.getString("reminder")+" at " +rs.getString("eventTime")+" .";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
     
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
 
    public void reminder(){
        
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
               // sendMail();
            }
        };
        Timer t = new Timer();
        t.schedule(timer, 0, 0);
        
    }
    
    
    
}

