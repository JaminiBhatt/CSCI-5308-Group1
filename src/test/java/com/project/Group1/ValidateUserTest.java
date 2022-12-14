package com.project.Group1;

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

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class ValidateUserTest {

    @Autowired
    Environment env;

        @Test
        void testEncodePasswordExecute() throws UnsupportedEncodingException, SQLException, ClassNotFoundException
        {
        Assertions.assertEquals("V2VsY29tZUAxMjM=", ValidateUser.getInstance().encodePassword("Welcome@123"));
        }

       @Test
       void testDecodePasswordExecute() throws UnsupportedEncodingException, SQLException, ClassNotFoundException
       {
        Assertions.assertEquals("Welcome@123", ValidateUser.getInstance().decodeData("V2VsY29tZUAxMjM="));
       }

      @Test
      void testAunthenticateExecute() throws UnsupportedEncodingException, SQLException, ClassNotFoundException
      {
          Assertions.assertNotNull(ValidateUser.getInstance().authenticateUser("Welcome", "Akash", env));
      }



}
