package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InspectorCommand extends Command {
    public InspectorCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("call get_inspector_list()");
            List<String> inspectorList = new ArrayList<>();
            while (rs.next()) {
                inspectorList.add(rs.getString(1));
            }
            System.out.println(inspectorList);
            con.commit();
            con.close();
            return inspectorList;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}


