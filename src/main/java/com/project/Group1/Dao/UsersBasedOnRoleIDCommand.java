package com.project.Group1.Dao;

import com.project.Group1.Bean.User;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersBasedOnRoleIDCommand extends Command {
    public UsersBasedOnRoleIDCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String roleID = args[0];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_Users_Based_On_Role_ID(?)");
            ps.setInt(1, Integer.parseInt(roleID));
            ResultSet rs = ps.executeQuery();
            List<User> listUser = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("User_Name"));
                user.setEmail(rs.getString("Email"));
                user.setRoleId(rs.getInt("Role_ID"));
                listUser.add(user);
            }
            con.commit();
            con.close();
            return listUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
