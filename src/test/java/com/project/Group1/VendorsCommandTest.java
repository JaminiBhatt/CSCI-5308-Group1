package com.project.Group1;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.Database.Database;
import com.project.Group1.Database.IDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")
public class VendorsCommandTest {
    @Autowired
    Environment env;

    @Test
    void testExecute() {
        String[] args = new String[1];
        args[0] = "1";
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getVendorsCommand(db, args, env);
        List<String> projects = (List) cmd.execute();
        Assertions.assertNotNull(projects);
        Assertions.assertTrue(projects.size() > 0);
    }

    @Test
    void testExecuteWrongId() {
        String[] args = new String[1];
        args[0] = "3";
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getVendorsCommand(db, args, env);
        List<String> projects = (List) cmd.execute();
        Assertions.assertNotNull(projects);
        Assertions.assertTrue(projects.size() == 0);
    }
}
