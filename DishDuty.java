import java.util.*;
import java.util.Calendar;
import javax.mail.*;
import javax.mail.internet.*;

class SendEmail {
	
   public static void main(String args[]) {  
	   
	  int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	  int week = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
	  if (week == 5) {
		  week = 1;
	  }
	  
	  day--;
	  week--;
	  
	  String names[] = {"tapley.a", "hirn.j", "kachmar.m", "vaiarella.t"};
	  String duty[] = {names[(week % 4)], 
			  names[((week + 1) % 4)], names[((week + 1) % 4)],
			  names[((week + 2) % 4)], names[((week + 2) % 4)], 
			  names[((week + 3) % 4)], names[((week + 3) % 4)]};
	  
      String from = "dishduty320@gmail.com";
      String to = duty[day] + "@husky.neu.edu";
 
      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true"); 
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.smtp.port", 587);

      Session session = Session.getInstance(properties,
    		  new javax.mail.Authenticator() {
    	  protected PasswordAuthentication getPasswordAuthentication() {
    		  return new PasswordAuthentication(from, "dishdutydavb");
    	  }
      });

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         message.setSubject("Dish Duty Reminder!");
         message.setText("Don't forget to do the dishes tonight.");

         Transport.send(message);
         System.out.print("Done!");
      } 
      
      catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}