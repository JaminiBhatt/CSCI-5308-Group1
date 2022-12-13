package com.example.Group1.Dao;

import com.project.Group1.Bean.VendorDashboardItems;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ICRVendorDashboardCommand extends Command {

    public ICRVendorDashboardCommand(IDatabase db, String[] args, Environment env) {
        super(db, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        int pid = Integer.parseInt(args[0]);
        int vid = Integer.parseInt(args[1]);
        int PO = Integer.parseInt(args[2]);
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_ICR_VendorDashboard(?,?,?)");
            ps.setInt(1, pid);
            ps.setInt(2, vid);
            ps.setInt(3, PO);
            ResultSet rs = ps.executeQuery();

            List<VendorDashboardItems> ICR_items_list = new ArrayList<>();
            while (rs.next()) {
                int PONumber = PO;
                int ID = rs.getInt("ID");
                String projectName = rs.getString("projectName");
                String vendorName = rs.getString("vendorName");
                int ICRNumber = rs.getInt("ICRNumber");
                int statusID = rs.getInt("statusID");
                String CreatedOn = rs.getString("CreatedOn");
                String CreatedBy = rs.getString("CreatedBy");
                ICR_items_list.add(new VendorDashboardItems(projectName, vendorName, PONumber,
                        ICRNumber, statusID, CreatedOn, CreatedBy, ID));
                System.out.println("ICR" + ID);
            }
            con.commit();
            con.close();
            return ICR_items_list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
