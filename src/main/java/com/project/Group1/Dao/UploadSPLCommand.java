package com.project.Group1.Dao;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.Database.IDatabase;
import org.springframework.core.env.Environment;

import java.sql.Connection;

public class UploadSPLCommand extends Command {

    public UploadSPLCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        // query to execute upload shipping list data
        IDatabase db = receiver;
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));

            // with the connection make the call to the procedure.
            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
