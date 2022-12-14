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

import java.sql.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class UpdateIRCommandTest {
    @Autowired
    Environment env;

    @Test
    void testExecute() {
        String[] args = new String[6];
        args[0] = "2";
        args[1] = "2";
        args[2] = "800";
        args[3] = "no remarks";
        args[4] = new Date(2022, 10, 20).toString();
        args[5] = new Date(2022, 11, 20).toString();
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getUpdateIR(db, args, env);
        int irID = (Integer) cmd.execute();
        Assertions.assertTrue(-1 != irID);
    }
}
