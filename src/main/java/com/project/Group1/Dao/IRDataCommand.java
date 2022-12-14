package com.project.Group1.Dao;

import com.project.Group1.Bean.IRData;
import com.project.Group1.Bean.IRDataList;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IRDataCommand extends Command {
    public IRDataCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String icrID = args[0];

        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_ICR_items_data(?)");
            ps.setString(1, icrID);

            ResultSet rs = ps.executeQuery();
            IRDataList irDataList = new IRDataList();
            while (rs.next()) {
                IRData item = new IRData();
                item.itemCode = rs.getInt(3);
                item.itemDesc = rs.getString(4);
                item.itemQty = rs.getInt(5);
                irDataList.addIRData(item);
            }
            con.commit();
            con.close();
            return irDataList;
        } catch (Exception e) {
            return null;
        }
    }
}
