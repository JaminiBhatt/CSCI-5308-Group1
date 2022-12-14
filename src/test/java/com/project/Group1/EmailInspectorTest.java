package com.project.Group1;

import com.project.Group1.Bean.EmailConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class EmailInspectorTest {
    @Test
    public void NotifyInspectorThroughMailFailureTest() {
        assertThrows(MessagingException.class, () -> {
            Session session = null;
            MimeMessage message = new MimeMessage(session);
            message.setSubject(null);
            Transport.send(message);
        });
    }
    @Test
    public void NotifyInspectorThroughMailTest() {
        EmailConfiguration emailConfiguration = new EmailConfiguration();
        assertThrows(MessagingException.class, () -> {
            Session session = emailConfiguration.session;
            MimeMessage message = new MimeMessage(session);
            Transport.send(message);
        });
    }
}
