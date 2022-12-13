package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ICRIDCommand extends Command {

    public ICRIDCommand(IDatabase db, String[] args, Environment env) {
        super(db, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String ProjectID = args[0];
        String VendorID = args[1];
        String PONumber = args[2];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_ICRID(?,?,?)");
            ps.setString(1, ProjectID);
            ps.setString(2, VendorID);
            ps.setString(3, PONumber);

            ResultSet rs = ps.executeQuery();
            int ICRID = 0;
            while (rs.next()) {
                ICRID = rs.getInt(1);
            }
            con.commit();
            con.close();
            return ICRID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
