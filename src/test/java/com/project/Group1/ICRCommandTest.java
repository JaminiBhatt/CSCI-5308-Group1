package com.project.Group1;

import com.project.Group1.CommandFactory.Command;
import com.project.Group1.CommandFactory.CommandFactory;
import com.project.Group1.CommandFactory.ICommandFactory;
import com.project.Group1.Database.Database;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class ICRCommandTest {

    @Autowired
    Environment env;

    @Test
    void testExecute() {
        ICommandFactory factory = new CommandFactory();
        String[] args = new String[3];
        args[0] = "1";
        args[1] = "1";
        args[2] = "900";
        Command getICRItems = factory.getICRItemsCommand(Database.getInstance(), args, env);
        assertNotNull(getICRItems.execute());
    }

    @Test
    void testExecuteWrongInput() {
        ICommandFactory factory = new CommandFactory();
        String[] args = new String[2];
        args[0] = "1";
        args[1] = "1";
        Command getICRItems = factory.getICRItemsCommand(Database.getInstance(), args, env);
        assertNull(getICRItems.execute());
    }


}

