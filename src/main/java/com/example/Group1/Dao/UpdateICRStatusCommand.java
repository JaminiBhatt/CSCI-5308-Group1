package com.example.Group1.Dao;

import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateICRStatusCommand extends Command {
    public UpdateICRStatusCommand(IDatabase receiver, String[] args, Environment env) {
        super(receiver, args, env);
    }

    @Override
    public Object execute() {
        IDatabase db = receiver;
        //String id = args[0];
        try {
            Connection con = db.getConnection(env.getProperty("CONNECTION_URL"), env.getProperty("DEVINT_USERNAME"), env.getProperty("DEVINT_PASSWORD"));
            PreparedStatement ps = con.prepareStatement("call get_ICR_Update()");
            ps.executeQuery();
            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}

