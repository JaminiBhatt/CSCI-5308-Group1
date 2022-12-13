package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectsCommand extends Command {
    public ProjectsCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("call get_projects()");
            List<String> projects = new ArrayList<>();
            while (rs.next()) {
                projects.add(rs.getString(2));
            }
            con.commit();
            con.close();
            return projects;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
