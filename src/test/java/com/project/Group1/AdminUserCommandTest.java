package com.project.Group1;

import com.project.Group1.Bean.User;
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
public class AdminUserCommandTest {

    @Autowired
    Environment env;

    @Test
    void testExecute() {
        ICommandFactory factory = new CommandFactory();
        String[] args = new String[2];
        args[0] = String.valueOf(4);
        args[1] = "Rock";
        Command getUsers=factory.getAdminUserCommand(Database.getInstance(), args, env);
        User adminUser = (User) getUsers.execute();
        assertNotNull(adminUser.getUsername());
    }

    @Test
    void testExecuteWrongInput() {
        ICommandFactory factory = new CommandFactory();
        String[] args1 = new String[2];
        args1[0] = String.valueOf(4);
        args1[1] = "Akash";
        Command getUsers=factory.getAdminUserCommand(Database.getInstance(), args1, env);
        User adminUser = (User) getUsers.execute();
        assertNull(adminUser.getUsername());
    }


}
