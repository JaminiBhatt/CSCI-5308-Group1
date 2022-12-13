package com.example.Group1.Dao;

import com.project.Group1.Bean.ICRCallAssignment;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ICRCallAssignmentCommand extends Command {

    public ICRCallAssignmentCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

//    public void update() {
//        IDatabase db = receiver;
//        //String id = args[0];
//        try {
//            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
//            PreparedStatement ps = con.prepareStatement("call get_ICR_Update()");
//            ps.executeQuery();
//            con.commit();
//            con.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        int ID = Integer.parseInt(args[0]);
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_ICRItems_For_CallAssignment(?)");
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            List<ICRCallAssignment> ICR_Items = new ArrayList<>();
            while (rs.next()) {
                ID = rs.getInt("ID");
                int SLID = rs.getInt("SLID");
                int itemCode = rs.getInt("ItemCode");
                String itemDesc = rs.getString("ItemDesc");
                int itemQty = rs.getInt("ItemQty");
                int offeredQty = rs.getInt("OfferedQty");
                int ICRID = rs.getInt("ICRID");
                ICR_Items.add(new ICRCallAssignment(ID, SLID, itemCode, itemDesc, itemQty, offeredQty, ICRID));
            }
            con.commit();
            con.close();
            return ICR_Items;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

