package com.project.Group1.Controller;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.CommandFactory.ICommandFactory;
import com.project.Group1.Database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UploadSplController {

    @Autowired
    Environment env;

    @GetMapping("/UploadSPL")
    public ModelAndView getShippingListDashboard(HttpSession httpSession) {
        ModelAndView model = new ModelAndView();
        if (httpSession.getAttribute("role") == null) {
            model.setViewName("Error");
        } else {
            model.setViewName("UploadSpl");
            // getting projects
            String[] args = {};
            CommandFactory factory = new CommandFactory();
            Command getProjects = factory.getProjectsCommand(Database.getInstance(), args, env);
            List<String> projects = (List) getProjects.execute();
            model.addObject("projects", projects);
        }

        return model;
    }

    @PostMapping("/UploadSPL")
    public ModelAndView demo(@RequestParam String project, HttpSession httpSession) {
        ModelAndView model = new ModelAndView();
        if (httpSession.getAttribute("role") == null) {
            model.setViewName("ErrorPageInvalidUrl");
        } else {
            model.setViewName("UploadSpl");

            ICommandFactory factory = new CommandFactory();

            // adding selected projected
            String selectedProject = project;
            model.addObject("selectedProject", selectedProject);

            // getting projects
            String[] args = {};
            Command getProjects = factory.getProjectsCommand(Database.getInstance(), args, env);
            List<String> projects = (List) getProjects.execute();
            model.addObject("projects", projects);

            // getting data for selected project (id)
            String[] args1 = new String[1];
            args1[0] = selectedProject;

            Command getProjectId = factory.getProjectIDCommand(Database.getInstance(), args1, env);
            int projectId = (int) getProjectId.execute();
            args1[0] = String.valueOf(projectId);

            Command getVendors = factory.getVendorsCommand(Database.getInstance(), args1, env);
            List<String> vendors = (List) getVendors.execute();
            model.addObject("vendors", vendors);

            // getting po_list
            args1[0] = String.valueOf(projectId);
            Command getVendorID = factory.getVendorIDCommand(Database.getInstance(), args1, env);
            int vendorId = (int) getVendorID.execute();

            String[] args2 = new String[2];
            args2[0] = String.valueOf(projectId);
            args2[1] = String.valueOf(projectId);
            Command getPOList = factory.getPOListCommand(Database.getInstance(), args2, env);
            List<String> po_list = (List) getPOList.execute();
            model.addObject("po_list", po_list);
        }
        return model;
    }
}
