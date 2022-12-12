package com.project.Group1.Controller;

import com.project.Group1.Bean.User;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.CommandFactory.ICommandFactory;
import com.project.Group1.Dao.*;
import com.project.Group1.Database.Database;
import com.project.Group1.Implementation.PasswordGenerator;
import com.project.Group1.Implementation.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class AddRoleController {

    @Autowired
    Environment env;

    @RequestMapping(value = "/AddRole", method = RequestMethod.POST)
    public ModelAndView adminAddRole(@ModelAttribute User user, HttpSession httpSession) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("role") == null) {
            modelAndView.setViewName("ErrorPageInvalidUrl");
        } else {
            ICommandFactory factory = new CommandFactory();


            //get the Role ID
            String[] args = new String[1];
            args[0] = user.getRole();
            Command getRoleID = factory.getRoleIDCommand(Database.getInstance(), args, env);
            int RoleID = (int) getRoleID.execute();

            String Password = ValidateUser.getInstance().encodePassword(PasswordGenerator.getInstance().generatePassword());

            //add User Role
            String[] args1 = new String[4];
            args1[0] = user.getUsername();
            args1[1] = Password;
            args1[2] = String.valueOf(RoleID);
            args1[3] = user.getEmail();
            Command addUserRole = factory.addUserRoleCommand(Database.getInstance(), args1, env);
            addUserRole.execute();

            modelAndView.addObject("view", "AddRole");
            modelAndView.setViewName("AdminScreen");
        }
        return modelAndView;

    }


}
