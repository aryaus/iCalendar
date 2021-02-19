/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ARYA
 */
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
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
     
    public static void sendMail(String recepient){
        
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
    
    
    private static Message prepareMessage(Session session, String _mailAddress, String recepient){
       Message message = new MimeMessage(session); 
        try{
         
        message.setFrom(new InternetAddress(_mailAddress));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("Event");
        message.setText("This is an event");
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

