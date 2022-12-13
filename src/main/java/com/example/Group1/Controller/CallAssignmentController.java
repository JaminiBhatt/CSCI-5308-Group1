package com.example.Group1.Controller;

import com.example.Group1.Bean.Event;
import com.example.Group1.Dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CallAssignmentController {
    @Autowired
    Environment env;
    IDatabase db = Database.getInstance();

    @PostMapping("/CallAssignment")
    public ModelAndView ICRsubmit(@RequestParam(required = false) String ICRID) {
        ModelAndView model = new ModelAndView("ICRCallAssignment");
        System.out.println("id" + ICRID);
        String[] args = new String[1];
        args[0] = ICRID;
        CommandFactory factory = new CommandFactory();
        Command getItems = factory.getICRCallAssignmentCommand(db, args, env);
        List<String> itemsList = (List) getItems.execute();
        model.addObject("itemsList", itemsList);
        Command inspector = factory.getInspectorCommand(db, args, env);
        List<String> inspectorList = (List<String>) inspector.execute();
        //System.out.println(inspectorList);
        model.addObject("inspectorList", inspectorList);
        //ICRCallAssignmentCommand icrCallAssignmentCommand = new ICRCallAssignmentCommand(db, args, env);
        // icrCallAssignmentCommand.update();
        return model;
    }

    @PostMapping("/Call_Assignment")
    public ModelAndView ICRsubmitted(@RequestParam(required = false) String inspectorName) {
        ModelAndView model = new ModelAndView("ICRCallAssignment");
        //model.setViewName("ICRCallAssignment");
        List<String> inspectorList1 = new ArrayList<>();
        inspectorList1.add(inspectorName);
        NotificationServiceSubject notificationServiceSubject = new NotificationServiceSubject();
        notificationServiceSubject.subscribe(Event.CALL_Assignment, inspectorList1);
        ICommandFactory iCommandFactory = new CommandFactory();

        String[] args = new String[2];
        args[0] = inspectorName;
        //args[1] = ICRID;
        System.out.println("inspector" + inspectorName);
        Command getEmail = iCommandFactory.getInspectorEmailCommand(Database.getInstance(), args, env);
        String emailId = (String) getEmail.execute();
        String[] args1 = new String[1];
        args1[0] = emailId;
        InspectorEmailCommand emailListener = new InspectorEmailCommand(db, args1, env);
        emailListener.updateInspector(inspectorName, emailId);
        return model;
    }
}
