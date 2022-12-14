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

import static org.junit.Assert.assertNotNull;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class VendorDashboardTest {
    @Autowired
    Environment env;
    @Test
    public void testExecute()
    {
        int projectId=1;
        int vendorId=1;
        int po=900;
        String[] args = new String[3];
        args[0] = String.valueOf(projectId);
        args[1] = String.valueOf(vendorId);
        args[2] = String.valueOf(po);
        ICommandFactory factory=new CommandFactory();
        Command getVendorDashboardItems = factory.getICRVendorDashboardCommand(Database.getInstance(), args, env);
        assertNotNull(getVendorDashboardItems.execute());
    }
}
