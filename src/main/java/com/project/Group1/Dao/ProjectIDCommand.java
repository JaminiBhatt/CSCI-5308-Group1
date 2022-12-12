package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProjectIDCommand extends Command {
    public ProjectIDCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        String selectedProject = args[0];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_selected_project(?)");
            ps.setString(1, selectedProject);
            ResultSet rs = ps.executeQuery();
            int projectId = 0;
            while (rs.next()) {
                projectId = rs.getInt(1);
            }
            con.commit();
            con.close();
            return projectId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
