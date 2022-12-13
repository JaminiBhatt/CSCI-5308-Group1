package com.project.Group1.Controller;

import com.project.Group1.Bean.IRDataList;
import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.Database.Database;
import com.project.Group1.Implementation.PDFGeneratorUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class IRDashboardController {
    @Autowired
    Environment env;

    @GetMapping("/ir-dashboard")
    public ModelAndView getShippingListDashboard() {
        ModelAndView model = new ModelAndView("IRDashboard");
        // getting projects
        String[] args = {};
        CommandFactory factory = new CommandFactory();
        Command getProjects = factory.getProjectsCommand(Database.getInstance(), args, env);
        List<String> projects = (List) getProjects.execute();
        model.addObject("projects", projects);

        IRDataList irDataList = new IRDataList();
        model.addObject("irDataList", irDataList);

        return model;
    }

    @PostMapping("/ir-dashboard")
    public ModelAndView demo(@RequestParam String project) {
        ModelAndView model = new ModelAndView();
        model.setViewName("IRDashboard");

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

        IRDataList irDataList = new IRDataList();
        model.addObject("irDataList", irDataList);

        return model;
    }

    @PostMapping("/ir-dashboard-data")
    public ModelAndView getIRData(@RequestParam String project) {
        ModelAndView model = new ModelAndView();
        model.setViewName("IRDashboard");

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
//        System.out.println(irDataList);
//        for (IRData irData : irDataList.getIrDataList()) {
//            System.out.println(irData.toString());
//        }
        model.addObject("irDataList", irDataList);
        return model;
    }

    @PostMapping(
            value = "/export-pdf",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public void IRReport(HttpServletResponse response, @ModelAttribute IRDataList irDataList) throws IOException {


        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String fileType = "attachment; filename=inspection_report_" + dateFormat.format(new Date()) + ".pdf";
        response.setHeader("Content-Disposition", fileType);

        PDFGeneratorUtility.IRReport(response, irDataList);


    }

}
