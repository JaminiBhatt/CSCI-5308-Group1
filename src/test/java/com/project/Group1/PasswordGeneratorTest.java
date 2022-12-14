package com.project.Group1;

import com.project.Group1.Implementation.PasswordGenerator;
import com.project.Group1.Implementation.ValidateUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class PasswordGeneratorTest {

    @Test
    void testExecute()
    {
        Assertions.assertNotNull(PasswordGenerator.getInstance().generatePassword());
    }



}
