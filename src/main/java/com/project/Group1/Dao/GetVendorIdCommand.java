package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetVendorIdCommand extends Command {
    public GetVendorIdCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String vendorName = args[0];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_Vendor_ID(?)");
            ps.setString(1, vendorName);
            ResultSet rs = ps.executeQuery();
            int vendorID = 0;
            while (rs.next()) {
                vendorID = rs.getInt(1);
            }
            con.commit();
            con.close();
            return vendorID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
