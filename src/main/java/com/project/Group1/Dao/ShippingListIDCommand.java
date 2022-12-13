package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShippingListIDCommand extends Command {

    public ShippingListIDCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String projectID = args[0];
        String vendorID = args[1];
        String poNumber = args[2];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_ShippingList_ID(?,?,?)");
            ps.setString(1, projectID);
            ps.setString(2, vendorID);
            ps.setString(3, poNumber);
            ResultSet rs = ps.executeQuery();
            int shippingListID = 0;
            while (rs.next()) {
                shippingListID = rs.getInt(1);
            }
            con.commit();
            con.close();
            return shippingListID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
