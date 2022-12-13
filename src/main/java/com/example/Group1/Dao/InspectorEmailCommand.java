package com.example.Group1.Dao;
import com.example.Group1.Bean.Configuration;
import org.springframework.core.env.Environment;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InspectorEmailCommand extends Command {
    public InspectorEmailCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        // List<String> emailList = new ArrayList<>();

        IDatabase db = receiver;
        //String x = args[0];
        String InspectorName = args[0];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_inspector_listEmail(?)");

            ps.setString(1, InspectorName);

            ResultSet rs = ps.executeQuery();
            String emailID = "";
            while (rs.next()) {
                emailID = rs.getString(1);
            }
            con.commit();
            con.close();
            return emailID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void updateInspector(String inspectorName, String emailID) {
        System.out.println("update inspector");
        Configuration configuration = new Configuration();
        //return args1[0];
        try {
            Message message = new MimeMessage(configuration.session);
            message.setFrom(new InternetAddress("anu.mounisha@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailID)
            );
            message.setSubject("Call Assignment ");
            message.setText("Hello Inspector " + inspectorName + ","
                    + "\n\n You have new call assignments");

            Transport.send(message);

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}


