package com.example.Group1.Implementation;
import com.example.Group1.Bean.Configuration;
import com.example.Group1.Bean.EmailDetails;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UpdateInspector {
    public void updateInspector(String inspectorName, String emailID) {
        System.out.println("update inspector");
        Configuration configuration = new Configuration();
        try {
            EmailDetails em = new EmailDetails();
            Message message = new MimeMessage(configuration.session);
            message.setFrom(new InternetAddress("anu.mounisha@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailID)
            );
            message.setSubject("Call Assignment ");
            message.setText(em.CallAssignmentEmailDetails());
            Transport.send(message);

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
