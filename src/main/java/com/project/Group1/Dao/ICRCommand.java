package com.project.Group1.Dao;

import com.project.Group1.Bean.ICRItems;
import com.project.Group1.Bean.ICRItemsList;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ICRCommand extends Command {
    public ICRCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_icr_items_from_shipping_list(?,?,?)");
            ps.setString(1, args[0]);
            ps.setString(2, args[1]);
            ps.setString(3, args[2]);


            ResultSet rs = ps.executeQuery();
            ICRItemsList list = new ICRItemsList();
            while (rs.next()) {
                int PONumber = Integer.parseInt(args[2]);
                int itemCode = rs.getInt("ItemCode");
                String itemDesc = rs.getString("ItemDesc");
                int itemQty = rs.getInt("ItemQty");
                int offeredQty = 0;
                list.addICRItem(new ICRItems(PONumber, itemCode, itemDesc, itemQty, offeredQty));
            }
            con.commit();
            con.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
