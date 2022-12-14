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
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
    @RunWith(SpringJUnit4ClassRunner.class)
    @TestPropertySource("classpath:application-test.properties")
    public class GetVendorIdCommandTest {

        @Autowired
        Environment env;

        @Test
        void testExecute() {
            ICommandFactory factory = new CommandFactory();
            String[] args1 = new String[1];
            args1[0] = "Digiflux solutions";
            Command getVendorID=factory.getVendorIdCommand(Database.getInstance(), args1, env);
            assertTrue((int) getVendorID.execute()>0);
        }

    @Test
    void testExecuteWrongInput() {
        ICommandFactory factory = new CommandFactory();
        String[] args1 = new String[1];
        args1[0] = "1";
        Command getVendorID=factory.getVendorIdCommand(Database.getInstance(), args1, env);
        assertTrue((int) getVendorID.execute()==0);
    }

    }


