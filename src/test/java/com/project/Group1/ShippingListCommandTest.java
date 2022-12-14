package com.project.Group1;


import com.project.Group1.Bean.IRDataList;
import com.project.Group1.Bean.ShippingListItem;
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
public class ShippingListCommandTest {

    @Autowired
    Environment env;

    @Test
    void testExecute() {
        String[] args = new String[1];
        args[0] = "900";
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getSippingListCommand(db, args, env);
        List<ShippingListItem> items = (List) cmd.execute();
        Assertions.assertNotNull(items);
        Assertions.assertTrue(items.size() > 0, "No shipping list items for that PO number");
    }

    @Test
    void testExecuteFailureWrongPO() {
        String[] args = new String[1];
        args[0] = "0";
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getSippingListCommand(db, args, env);
        List<ShippingListItem> items = (List) cmd.execute();
        Assertions.assertNotNull(items);
        Assertions.assertTrue(items.size() == 0);
    }

    @Test
    void testExecuteFailureNoArgs() {
        String[] args = {""};
        IDatabase db = Database.getInstance();
        CommandFactory factory = new CommandFactory();
        Command cmd = factory.getSippingListCommand(db, args, env);
        List<ShippingListItem> items = (List) cmd.execute();
        Assertions.assertNotNull(items);
        Assertions.assertTrue(items.size() == 0);
    }

}
