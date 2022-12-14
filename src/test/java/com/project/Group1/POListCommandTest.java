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
public class POListCommandTest {

    @Autowired
    Environment env;

    @Test
    void testExecute() {
        String[] args = new String[2];
        args[0] = "1";
        args[1] = "1";
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getPOListCommand(db, args, env);
        List<String> result = (List) cmd.execute();
        Assertions.assertTrue(result.size() != 0);
        Assertions.assertEquals("900", result.get(0));
    }

    @Test
    void testExecuteWrongData() {
        String[] args = new String[2];
        args[0] = "3";
        args[1] = "3";
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getPOListCommand(db, args, env);
        List<String> result = (List) cmd.execute();
        Assertions.assertTrue(result.size() == 0);
    }
}
