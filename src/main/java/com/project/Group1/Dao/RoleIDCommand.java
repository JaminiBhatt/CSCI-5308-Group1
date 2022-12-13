package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoleIDCommand extends Command {
    public RoleIDCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String roleName = args[0];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_Role_ID(?)");
            ps.setString(1, roleName);
            ResultSet rs = ps.executeQuery();
            int roleID = 0;
            while (rs.next()) {
                roleID = rs.getInt("ID");
            }
            con.commit();
            con.close();
            return roleID;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
