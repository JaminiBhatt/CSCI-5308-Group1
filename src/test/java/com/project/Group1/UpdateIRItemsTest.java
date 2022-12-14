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
public class UpdateIRItemsTest {


    @Autowired
    Environment env;

    @Test
    void testExecute() {
        String[] args = new String[7];
        args[0] = "1";
        args[1] = "123";
        args[2] = "table";
        args[3] = "20";
        args[4] = "10";
        args[5] = "2";
        args[6] = "2";
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getUpdateIRItems(db, args, env);
        int res = (int) cmd.execute();
        Assertions.assertTrue(res > 0);
    }
}
