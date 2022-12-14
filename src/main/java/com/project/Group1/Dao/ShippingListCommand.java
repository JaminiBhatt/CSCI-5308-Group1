package com.project.Group1.Dao;

import com.project.Group1.Bean.ShippingListItem;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShippingListCommand extends Command {
    public ShippingListCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String PO = args[0];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_shipping_list_items(?)");
            ps.setString(1, PO);
            ResultSet rs = ps.executeQuery();
            List<ShippingListItem> item_list = new ArrayList<>();
            while (rs.next()) {
                int PONumber = Integer.parseInt(PO);
                int itemCode = rs.getInt("ItemCode");
                String itemDesc = rs.getString("ItemDesc");
                int itemQty = rs.getInt("ItemQty");
                item_list.add(new ShippingListItem(PONumber, itemCode, itemDesc, itemQty));
            }
            con.commit();
            con.close();
            return item_list;

        } catch (Exception e) {
            return null;
        }
    }


}
