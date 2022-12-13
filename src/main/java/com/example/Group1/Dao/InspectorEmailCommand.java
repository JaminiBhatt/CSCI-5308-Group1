package com.example.Group1.Dao;

import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InspectorEmailCommand extends Command {
    public InspectorEmailCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
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

}


