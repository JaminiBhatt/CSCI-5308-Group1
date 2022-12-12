package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UploadExcelCommand extends Command {
    public UploadExcelCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call save_Shipping_List_Item (?,?,?,?,?,?,?,now(),?,now())");
            int records = 0;

            String sql = "SELECT COUNT(*) FROM ShippingListItems";
            PreparedStatement prest = con.prepareStatement(sql);
            ResultSet rs = prest.executeQuery();
            while (rs.next()) {
                records = rs.getInt(1);
            }

            ps.setInt(1, records + 1);
            ps.setInt(2, Integer.parseInt(args[0]));
            ps.setInt(3, Integer.parseInt(args[1]));
            ps.setString(4, args[2]);
            ps.setInt(5, Integer.parseInt(args[3]));
            ps.setInt(6, Integer.parseInt(args[4]));
            ps.setString(7, args[5]);
            ps.setString(8, args[5]);
            ps.execute();

            con.commit();
            con.close();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
