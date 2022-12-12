package com.project.Group1.Controller;

import com.project.Group1.Bean.ShippingListItem;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.Database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShippingListDashBoardController {

    @Autowired
    Environment env;

    @GetMapping("/shippinglist-dashboard")
    public ModelAndView getShippingListDashboard() {
        ModelAndView model = new ModelAndView("ShippingListDashboard");
        // getting projects
        String[] args = {};
        CommandFactory factory = new CommandFactory();
        Command getProjects = factory.getProjectsCommand(Database.getInstance(), args, env);
        List<String> projects = (List) getProjects.execute();
        model.addObject("projects", projects);

        return model;
    }

    @PostMapping("/shippinglist-dashboard")
    public ModelAndView getData(@RequestParam String project) {
        ModelAndView model = new ModelAndView();
        model.setViewName("ShippingListDashboard");

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
        args2[1] = String.valueOf(vendorId);
        Command getPOList = factory.getPOListCommand(Database.getInstance(), args2, env);
        List<String> po_list = (List) getPOList.execute();
        model.addObject("po_list", po_list);

        return model;
    }

    @PostMapping("/shippinglist-data")
    public ModelAndView getShippingListData(@RequestParam String project, @RequestParam String PO) {
        ModelAndView model = new ModelAndView("ShippingListDashboard");
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
        args2[1] = String.valueOf(vendorId);
        Command getPOList = factory.getPOListCommand(Database.getInstance(), args2, env);
        List<String> po_list = (List) getPOList.execute();
        model.addObject("po_list", po_list);


        // getting shippinglist
        String po = String.valueOf(po_list.get(0));
        String[] args4 = {po};
        Command getShippingListItems = factory.getSippingListCommand(Database.getInstance(), args4, env);
        List<ShippingListItem> items_list = (List) getShippingListItems.execute();
        model.addObject("items_list", items_list);

        return model;
    }

}