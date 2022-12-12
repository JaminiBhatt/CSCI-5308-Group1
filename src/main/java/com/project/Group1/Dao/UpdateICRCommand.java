package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateICRCommand extends Command {
    public UpdateICRCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement stmt = con.prepareStatement("call insert_ICR(?,?,?,?,?,?,?)");
            stmt.setInt(1, Integer.parseInt(args[0]));
            stmt.setInt(2, Integer.parseInt(args[1]));
            stmt.setString(3, args[2]);
            stmt.setString(4, args[3]);
            stmt.setString(5, args[4]);
            stmt.setString(6, args[5]);
            stmt.setString(7, args[6]);

            ResultSet rs = stmt.executeQuery();
            int irID = -1;
            while (rs.next()) {
                irID = rs.getInt(1);
            }
            con.commit();
            con.close();
            return irID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
