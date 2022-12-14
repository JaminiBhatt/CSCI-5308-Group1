package com.project.Group1;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.Database.Database;
import com.project.Group1.Database.IDatabase;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class VendorIDCommandTest {

    @Autowired
    Environment env;

    @Test
    void testExecute() {
        String[] args = new String[1];
        args[0] = "1";
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getVendorIDCommand(db, args, env);
        int id = (int) cmd.execute();
        Assertions.assertTrue(id == 1);
    }

    @Test
    void testExecuteFailureWrongID() {
        String[] args = new String[1];
        args[0] = "3";
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getVendorIDCommand(db, args, env);
        int id = (int) cmd.execute();
        Assertions.assertTrue(id == -1);
    }
}
