package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class POListCommand extends Command {
    public POListCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String projectId = args[0];
        String vendorId = args[1];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_po_number(?,?)");
            ps.setString(1, String.valueOf(projectId));
            ps.setString(2, String.valueOf(vendorId));

            ResultSet rs = ps.executeQuery();
            List<String> po_list = new ArrayList<>();
            while (rs.next()) {
                po_list.add(rs.getString(1));
            }
            con.commit();
            con.close();
            return po_list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
