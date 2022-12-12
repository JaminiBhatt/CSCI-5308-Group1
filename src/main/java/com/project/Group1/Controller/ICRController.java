package com.project.Group1.Controller;

import com.project.Group1.Bean.ICRItems;
import com.project.Group1.Bean.ICRItemsList;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.CommandFactory.ICommandFactory;
import com.project.Group1.Database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

@Controller
public class ICRController {

    @Autowired
    Environment env;

    @GetMapping("/icr")
    public ModelAndView getICRForm() {
        ModelAndView model = new ModelAndView("ICR");
        // getting projects
        String[] args = {};
        CommandFactory factory = new CommandFactory();
        Command getProjects = factory.getProjectsCommand(Database.getInstance(), args, env);
        List<String> projects = (List) getProjects.execute();

        ICRItemsList icrItemsList = new ICRItemsList();
        model.addObject("icrItemsList", icrItemsList);
        model.addObject("projects", projects);

        return model;
    }

    @PostMapping("/icr")
    public ModelAndView demo(@RequestParam String project) {
        ModelAndView model = new ModelAndView();
        model.setViewName("ICR");

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

        ICRItemsList icrItemsList = new ICRItemsList();
        model.addObject("icrItemsList", icrItemsList);

        return model;
    }

    @PostMapping("/icr-data")
    public ModelAndView getICRData(@RequestParam String project, @RequestParam String PO,@RequestParam String vendor) {
        ModelAndView model = new ModelAndView("ICR");
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


        // getting icr items

        String po = String.valueOf(po_list.get(0));
        String[] args4 = new String[3];
        args4[0] = String.valueOf(projectId);
        args4[1] = String.valueOf(vendorId);
        args4[2] = PO;
        Command getICRItems = factory.getICRItemsCommand(Database.getInstance(), args4, env);
//        List<ICRItems> items_list = (List) getICRItems.execute();
//        model.addObject("items_list", items_list);

        ICRItemsList icrItemsList = (ICRItemsList) getICRItems.execute();
        model.addObject("icrItemsList", icrItemsList);

        return model;
    }

    @PostMapping("/icr-submit-data")
    public ModelAndView submitICRData(@ModelAttribute ICRItemsList icrItemsList, @RequestParam String project, @RequestParam String vendor, @RequestParam String PO, @RequestParam String location, @RequestParam String remark, @RequestParam Date proposedFrom, @RequestParam Date proposedTo) {


        System.out.println(remark + " " + proposedFrom + " " + proposedTo);
        ModelAndView model = getICRForm();

        // get project id
        ICommandFactory factory = new CommandFactory();

        String[] args1 = new String[1];
        args1[0] = project;

        Command getProjectId = factory.getProjectIDCommand(Database.getInstance(), args1, env);
        int projectId = (int) getProjectId.execute();

        // get vendor id
        args1[0] = String.valueOf(projectId);
        Command getVendorID = factory.getVendorIDCommand(Database.getInstance(), args1, env);
        int vendorId = (int) getVendorID.execute();

        String[] args2 = new String[7];
        args2[0] = String.valueOf(projectId);
        args2[1] = String.valueOf(vendorId);
        args2[2] = String.valueOf(PO);
        args2[3] = String.valueOf(location);
        args2[4] = String.valueOf(remark);
        args2[5] = String.valueOf(proposedFrom);
        args2[6] = String.valueOf(proposedTo);


        Command updateICR = factory.getUpdateICR(Database.getInstance(), args2, env);
        int icrID = (int) updateICR.execute();

        for (ICRItems icrItems : icrItemsList.getIcrItemsList()) {
            String[] args3 = new String[5];
            args3[0] = String.valueOf(icrID);
            args3[1] = String.valueOf(icrItems.itemCode);
            args3[2] = String.valueOf(icrItems.itemDesc);
            args3[3] = String.valueOf(icrItems.itemQty);
            args3[4] = String.valueOf(icrItems.offeredQty);
            Command getICRItems = factory.getUpdateICRItems(Database.getInstance(), args3, env);
            getICRItems.execute();
        }


        return model;
    }



}