package com.project.Group1.Controller;

import com.project.Group1.Bean.User;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.CommandFactory.ICommandFactory;
import com.project.Group1.Dao.*;
import com.project.Group1.Database.Database;
import com.project.Group1.Implementation.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    Environment env;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView loginScreen(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Login");
        user.setIsValidUser(true);
        modelAndView.addObject("isValidUser", user.getIsValidUser());
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView validateTheUser(@ModelAttribute User user, HttpSession httpSession) throws SQLException, ClassNotFoundException, UnsupportedEncodingException {
        ICommandFactory factory = new CommandFactory();
        ModelAndView modelAndView = new ModelAndView();

        //authenticate the user
        User userFromDB = ValidateUser.getInstance().authenticateUser(user.getPassword(), user.getUsername(), env);
        httpSession.setAttribute("username", userFromDB.getUsername());
        httpSession.setAttribute("password", userFromDB.getPassword());
        httpSession.setAttribute("role", userFromDB.getRole());


        modelAndView.addObject("isValidUser", userFromDB.getIsValidUser());
        if (userFromDB.getIsValidUser() && user.getRadioButton().equalsIgnoreCase("Others")) {
            String[] args = {};
            Command getProjects = factory.getProjectsCommand(Database.getInstance(), args, env);
            List<String> projects = (List) getProjects.execute();
            modelAndView.addObject("projects", projects);
            modelAndView.setViewName("UploadSpl");
        } else if (userFromDB.getIsValidUser() && user.getRadioButton().equalsIgnoreCase("Admin")) {
            //get the Role ID
            String[] args = new String[1];
            args[0] = "Admin";
            Command getRoleID = factory.getRoleIDCommand(Database.getInstance(), args, env);
            int RoleID = (int) getRoleID.execute();

            //check If Admin User or Not
            String[] args1 = new String[2];
            args1[0] = String.valueOf(RoleID);
            args1[1] = user.getUsername();
            Command getAdminUser = factory.getAdminUserCommand(Database.getInstance(), args1, env);
            User adminUser = (User) getAdminUser.execute();

            if (adminUser.getUsername() == null) {
                modelAndView.addObject("isCorrectAdmin", false);
                modelAndView.setViewName("Login");
            } else {
                modelAndView.addObject("isCorrectAdmin", true);
                modelAndView.addObject("view", "AddRole");
                modelAndView.setViewName("AdminScreen");
            }

        } else {
            modelAndView.setViewName("Login");

        }
        return modelAndView;
    }

}
