package com.project.Group1.Dao;

import com.project.Group1.Bean.User;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDetailsCommand extends Command {

    public UserDetailsCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public User execute() {
        IDatabase db = receiver;
        String userName = args[0];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_User_Details(?)");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Rolename"));
                user.setUsername(rs.getString("User_Name"));
            }
            con.commit();
            con.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
