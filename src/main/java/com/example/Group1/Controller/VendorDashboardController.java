package com.example.Group1.Controller;

import com.example.Group1.Bean.VendorDashboardItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class VendorDashboardController {

    @Autowired
    Environment env;

    @GetMapping("/VendorDashboard")
    public ModelAndView getVendorDashboardDashboard() {
        ModelAndView model = new ModelAndView("VendorDashboard");
        // getting projects
        String[] args = {};
        CommandFactory factory = new CommandFactory();
        Command getProjects = factory.getProjectsCommand(Database.getInstance(), args, env);
        List<String> projects = (List) getProjects.execute();
        model.addObject("projects", projects);

        return model;
    }

    @PostMapping("/VendorDashboard")
    public ModelAndView vendorPOListForSelectedProject(@RequestParam String project) {
        ModelAndView model = new ModelAndView();
        model.setViewName("VendorDashboard");

        CommandFactory factory = new CommandFactory();

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

        return model;
    }

    @PostMapping("/vendor-data")
    public ModelAndView getVendorDashboardData(@RequestParam String project, @RequestParam String PO) {
        ModelAndView model = new ModelAndView("VendorDashboard");
        CommandFactory factory = new CommandFactory();

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


        // getting ICR
        String po = String.valueOf(po_list.get(0));
        String[] args4 = new String[3];
        args4[0] = String.valueOf(projectId);
        args4[1] = String.valueOf(vendorId);
        args4[2] = String.valueOf(po);
        //String[] args4 = {po};
        Command getVendorDashboardItems = factory.getICRVendorDashboardCommand(Database.getInstance(), args4, env);
        List<VendorDashboardItems> ICR_items_list = (List) getVendorDashboardItems.execute();
        model.addObject("ICR_items_list", ICR_items_list);
        return model;
    }


}

