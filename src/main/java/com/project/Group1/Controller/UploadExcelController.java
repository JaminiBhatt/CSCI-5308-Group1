package com.project.Group1.Controller;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.Database.Database;
import com.project.Group1.Implementation.ExcelToListConverter;
import com.project.Group1.Implementation.UploadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


@Controller
public class UploadExcelController {

    @Autowired
    Environment env;

    @PostMapping("/uploadFile")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String project, @RequestParam String PO, @RequestParam String vendor, HttpSession httpSession) throws SQLException, ClassNotFoundException {
        {
            ModelAndView modelAndView = new ModelAndView();
            if (httpSession.getAttribute("role") == null) {
                modelAndView.setViewName("ErrorPageInvalidUrl");
            } else {
                CommandFactory factory = new CommandFactory();
                if (ExcelToListConverter.getInstance().checkExcelFormat(file)) {

                    // getting vendorID from vendorName
                    String[] args = new String[1];
                    args[0] = vendor;
                    Command getVendorID = factory.getVendorIdCommand(Database.getInstance(), args, env);
                    int vendorID = (int) getVendorID.execute();

                    // getting projectID from projectName
                    String[] args1 = new String[1];
                    args1[0] = project;
                    Command getProjectId = factory.getProjectIDCommand(Database.getInstance(), args1, env);
                    int projectId = (int) getProjectId.execute();

                    //getting ShippingListID
                    String[] args2 = new String[3];
                    args2[0] = String.valueOf(projectId);
                    args2[1] = String.valueOf(vendorID);
                    args2[2] = PO;
                    Command getShippingListID = factory.getShippingListID(Database.getInstance(), args2, env);
                    int shippingListID = (int) getShippingListID.execute();

                    UploadExcel.getInstance().save(file, env, projectId, vendorID, shippingListID, httpSession.getAttribute("username").toString());

                }

                String[] args = {};
                Command getProjects = factory.getProjectsCommand(Database.getInstance(), args, env);
                List<String> projects = (List) getProjects.execute();
                modelAndView.addObject("projects", projects);
                modelAndView.setViewName("UploadSpl");
            }
            return modelAndView;
        }
    }

}

