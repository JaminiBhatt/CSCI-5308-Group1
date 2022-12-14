package com.project.Group1;

import com.project.Group1.Implementation.PasswordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
