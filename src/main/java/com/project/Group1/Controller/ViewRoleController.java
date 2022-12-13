package com.project.Group1.Controller;

import com.project.Group1.Bean.User;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.CommandFactory.ICommandFactory;
import com.project.Group1.Database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Controller
public class ViewRoleController {

    @Autowired
    Environment env;

    @RequestMapping(value = "/viewRole", method = RequestMethod.POST)
    public ModelAndView validateTheUser(@ModelAttribute User user, HttpSession httpSession) throws SQLException, ClassNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        ICommandFactory factory = new CommandFactory();

        if (httpSession.getAttribute("role") == null) {
            modelAndView.setViewName("ErrorPageInvalidUrl");
        } else {
            //get the Role ID
            String[] args = new String[1];
            args[0] = user.getRole();
            Command getRoleID = factory.getRoleIDCommand(Database.getInstance(), args, env);
            int RoleID = (int) getRoleID.execute();

            //fetchUser based on roleID
            String[] args1 = new String[1];
            args1[0] = String.valueOf(RoleID);
            Command getUsers = factory.getUsersBasedOnRoleIDCommand(Database.getInstance(), args1, env);
            List<User> listUser = (List<User>) getUsers.execute();

            modelAndView.addObject("view", "ViewRole");
            modelAndView.addObject("userList", listUser);
            modelAndView.setViewName("AdminScreen");
        }
        return modelAndView;

    }
}
