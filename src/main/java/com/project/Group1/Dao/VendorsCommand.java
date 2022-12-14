package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendorsCommand extends Command {
    public VendorsCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        int projectId = Integer.parseInt(args[0]);
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            // getting vendor data
            PreparedStatement ps = con.prepareStatement("call get_vendor_data(?)");
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            List<String> vendors = new ArrayList<>();
            while (rs.next()) {
                vendors.add(rs.getString(2));
            }
            con.commit();
            con.commit();
            return vendors;
        } catch (Exception e) {
            return null;
        }
    }

}
