package com.project.Group1.Implementation;
import com.project.Group1.Bean.EmailConfiguration;
import com.project.Group1.Bean.EmailDetails;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailInspector {
    public void updateInspector(String inspectorName, String emailID) {
        EmailConfiguration emailConfiguration = new EmailConfiguration();
        try {
            EmailDetails em = new EmailDetails();
            Message message = new MimeMessage(emailConfiguration.session);
            message.setFrom(new InternetAddress("anu.mounisha@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailID)
            );
            message.setSubject("Call Assignment "+inspectorName);
            message.setText(em.CallAssignmentEmailDetails());
            Transport.send(message);

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
