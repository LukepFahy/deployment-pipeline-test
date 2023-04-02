

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import io.github.cdimascio.dotenv.Dotenv;

public class SendEmailSSL {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                      .directory("user.dir")
                      .filename("credentials")
                      .load();

        final String username = System.getenv("EMAIL_ID");
        final String password = System.getenv("EMAIL_PASSWORD");

        //SMTP settings
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
        //Describes email contents
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(username)
            );
            message.setSubject("Subject Test");
            message.setText("Body Test");

            Transport.send(message);

            System.out.println("Email sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}