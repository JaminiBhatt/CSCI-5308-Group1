package com.project.Group1.Dao;

import com.project.Group1.Bean.IRData;
import com.project.Group1.Bean.IRDataList;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ALLIRDataCommand extends Command {
    public ALLIRDataCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        int projectID = Integer.parseInt(args[0]);
        int vendorID = Integer.parseInt(args[1]);
        int PO = Integer.parseInt(args[2]);

        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_IR_items_data(?,?,?)");
            ps.setInt(1, projectID);
            ps.setInt(2, vendorID);
            ps.setInt(3, PO);

            ResultSet rs = ps.executeQuery();
            IRDataList irDataList = new IRDataList();
            while (rs.next()) {
                IRData item = new IRData();
                item.assignedQty = rs.getInt(4);
                item.acceptedQty = rs.getInt(5);
                item.rejectedQty = rs.getInt(6);
                item.itemCode = rs.getInt(7);
                item.itemDesc = rs.getString(8);
                item.itemQty = rs.getInt(9);
                irDataList.addIRData(item);
            }
            con.commit();
            con.close();
            return irDataList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
