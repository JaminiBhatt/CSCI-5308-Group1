package com.project.Group1.Controller;

import com.project.Group1.Bean.IRData;
import com.project.Group1.Bean.IRDataList;
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
public class IRFormController {

    @Autowired
    Environment env;

    @GetMapping("/ir-form")
    public ModelAndView getIRForm() {
        ModelAndView model = new ModelAndView("IRForm");
        // getting projects
        String[] args = {};
        CommandFactory factory = new CommandFactory();
        Command getProjects = factory.getProjectsCommand(Database.getInstance(), args, env);
        List<String> projects = (List) getProjects.execute();

        IRDataList irDataList = new IRDataList();
        model.addObject("irDataList", irDataList);
        model.addObject("projects", projects);

        return model;
    }

    @PostMapping("/ir-form")
    public ModelAndView getData(@RequestParam String project) {
        ModelAndView model = new ModelAndView();
        model.setViewName("IRForm");

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

        IRDataList irDataList = new IRDataList();
        model.addObject("irDataList", irDataList);

        return model;
    }

    @PostMapping("/ir-data")
    public ModelAndView getIRData(@RequestParam String project) {
        ModelAndView model = new ModelAndView();
        model.setViewName("IRForm");

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

        // getting ICR ID
        String[] args3 = new String[3];
        args3[0] = String.valueOf(projectId);
        args3[1] = String.valueOf(vendorId);
        args3[2] = String.valueOf(po_list.get(0));

        Command getICRID = factory.getICRIDCommand(Database.getInstance(), args3, env);
        int icrId = (int) getICRID.execute();

        // getting IR Data Items from ICR items
        String[] args4 = {String.valueOf(icrId)};
        Command getIRData = factory.getIRDataCommand(Database.getInstance(), args4, env);

        IRDataList irDataList = (IRDataList) getIRData.execute();
        model.addObject("irDataList", irDataList);
        return model;
    }

    @PostMapping("/ir-submit-data")
    public ModelAndView submitIRData(@ModelAttribute IRDataList irDataList, @RequestParam String project, @RequestParam String vendor, @RequestParam String PO, @RequestParam String remark, @RequestParam Date proposedFrom, @RequestParam Date proposedTo) {

        ModelAndView model = getIRForm();

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

        String[] args2 = new String[6];
        args2[0] = String.valueOf(projectId);
        args2[1] = String.valueOf(vendorId);
        args2[2] = String.valueOf(PO);
        args2[3] = String.valueOf(remark);
        args2[4] = String.valueOf(proposedFrom);
        args2[5] = String.valueOf(proposedTo);

        Command updateIR = factory.getUpdateIR(Database.getInstance(), args2, env);

        int irID = (int) updateIR.execute();

        for (IRData irData : irDataList.getIrDataList()) {
            String[] args3 = new String[7];
            args3[0] = String.valueOf(irID);
            args3[1] = String.valueOf(irData.itemCode);
            args3[2] = String.valueOf(irData.itemDesc);
            args3[3] = String.valueOf(irData.itemQty);
            args3[4] = String.valueOf(irData.assignedQty);
            args3[5] = String.valueOf(irData.acceptedQty);
            args3[6] = String.valueOf(irData.rejectedQty);
            Command getIRItems = factory.getUpdateIRItems(Database.getInstance(), args3, env);
            getIRItems.execute();
        }

        return model;
    }
}
