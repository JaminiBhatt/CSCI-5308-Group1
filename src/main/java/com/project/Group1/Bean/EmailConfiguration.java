package com.project.Group1.Bean;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
public class EmailConfiguration {
    final String username = "anu.mounisha@gmail.com";
    final String password = "szhwiqsgpedelciu";
    public Session session;
    public EmailConfiguration() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }
}
