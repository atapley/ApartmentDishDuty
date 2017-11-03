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
	  if (day == 0) {
			week--;
		}
	  day--;
	   if (day == -1) {
			day = 6;
		}
	  week--;
	   if (week == -1) {
			week = 3;
		}
	  
	   // Names commented out
	  String names[] = {****, ****, ****, ****};
	  String duty[] = {names[(week % 4)], 
			  names[((week + 1) % 4)], names[((week + 1) % 4)],
			  names[((week + 2) % 4)], names[((week + 2) % 4)], 
			  names[((week + 3) % 4)], names[((week + 3) % 4)]};
	  
	   //Emails commented out
      String from = ****;
      String to = duty[day] + ****;
 
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
    		  return new PasswordAuthentication(from, ****); //Password commented out
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
	   		//Weekly schedule sent on Mondays
		if (day == 1) {
			to = ****;

			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Weekly Dish Duty Schedule");
				message.setText("Monday: " + duty[1] + "\r" +
						"Tuesday: " + duty[2] + "\r" +
						"Wednesday: " + duty[3] + "\r" +
						"Thursday: " + duty[4] + "\r" +
						"Friday: " + duty[5] + "\r" +
						"Saturday: " + duty[6] + "\r" +
						"Sunday: " + duty[0] + "\r");

				Transport.send(message);
				System.out.print("Done!");
			} 

			catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
   }
}
