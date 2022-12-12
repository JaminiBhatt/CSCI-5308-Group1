package com.project.Group1.Implementation;

import com.project.Group1.Bean.User;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.CommandFactory.ICommandFactory;
import com.project.Group1.Database.Database;
import org.springframework.core.env.Environment;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Base64;

public class ValidateUser {

    private static ValidateUser instance_ValidateUser = null;

    private ValidateUser() {

    }

    public User authenticateUser(String userPassword, String userName, Environment env) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        ICommandFactory factory = new CommandFactory();
        // getting projects
        String[] args = new String[1];
        args[0] = userName;
        Command getUserDetails = factory.getUserDetailsCommand(Database.getInstance(), args, env);
        User userDetails = (User) getUserDetails.execute();
        String dbPassword = null;
        if (userDetails.getPassword() != null) {
            dbPassword = decodeData(userDetails.getPassword());
        }
        if (dbPassword != null && dbPassword.equalsIgnoreCase(userPassword)) {
            userDetails.setIsValidUser(true);
        } else {
            userDetails.setIsValidUser(false);
        }

        return userDetails;

    }

    public String decodeData(String dbPassword) throws UnsupportedEncodingException {
        byte[] base64decodedBytes = Base64.getDecoder().decode(dbPassword);
        return new String(base64decodedBytes, "utf-8");
    }

    public String encodePassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(password.getBytes());

    }

    public static ValidateUser getInstance() {
        if (instance_ValidateUser == null) {
            instance_ValidateUser = new ValidateUser();
        }
        return instance_ValidateUser;
    }


}
