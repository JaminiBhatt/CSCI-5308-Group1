package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRoleCommand extends Command {
    public UserRoleCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String username = args[0];
        String password = args[1];
        String email = args[3];

        int records;
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            records = 0;
            PreparedStatement ps = con.prepareStatement("call AddUserRole (?,?,?,?,?,?)");
            PreparedStatement prest = con.prepareStatement("call get_User_Count()");
            ResultSet rs = prest.executeQuery();
            while (rs.next()) {
                records = rs.getInt(1);
            }

            ps.setInt(1, records + 1);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setInt(4, Integer.parseInt(args[2]));
            ps.setString(5, email);
            ps.setInt(6, 1);

            ps.executeUpdate();
            con.commit();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
