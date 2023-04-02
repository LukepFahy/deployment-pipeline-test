import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static org.junit.Assert.*;

public class SendEmailSSLTest {

    @Test
    public void testSessionCreation() {
        // Test that session is created successfully
        Dotenv dotenv = Dotenv.configure()
                .directory("user.dir")
                .filename("credentials")
                .load();

        final String username = System.getenv("EMAIL_ID");
        final String password = System.getenv("EMAIL_PASSWORD");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        assertNotNull(session);
    }

    @Test
    public void testSendMessage() throws MessagingException {
        // Test that email is sent successfully
        Dotenv dotenv = Dotenv.configure()
                .directory("user.dir")
                .filename("credentials")
                .load();

        final String username = System.getenv("EMAIL_ID");
        final String password = System.getenv("EMAIL_PASSWORD");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(username)
        );
        message.setSubject("Subject Test");
        message.setText("Body Test");

        Transport.send(message);

        // Check that the email is sent successfully
        Folder folder = session.getFolder("Sent");
        folder.open(Folder.READ_ONLY);
        Message[] messages = folder.getMessages();

        boolean emailSent = false;
        for (Message msg : messages) {
            if (msg.getSubject().equals("Subject Test")) {
                emailSent = true;
                break;
            }
        }

        assertTrue(emailSent);
    }

    @Test(expected = MessagingException.class)
    public void testSendInvalidMessage() throws MessagingException {
        // Test that an invalid email is not sent
        Dotenv dotenv = Dotenv.configure()
                .directory("user.dir")
                .filename("credentials")
                .load();

        final String username = System.getenv("EMAIL_ID");
        final String password = System.getenv("EMAIL_PASSWORD");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPassword
