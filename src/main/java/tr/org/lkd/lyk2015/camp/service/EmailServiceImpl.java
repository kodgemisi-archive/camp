package tr.org.lkd.lyk2015.camp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    private final String username;
    private final String from;
    private final String password;
    private final Properties props;

    @Autowired
    public EmailServiceImpl(Environment environment) {
        username = environment.getProperty("email.username");
        from = environment.getProperty("email.from");
        password = environment.getProperty("email.password");

        props = new Properties();
        props.put("mail.smtp.auth", environment.getProperty("email.smtp.auth"));
        props.put("mail.smtp.starttls.enable", environment.getProperty("email.smtp.starttls.enable"));
        props.put("mail.smtp.host", environment.getProperty("email.smtp.host"));
        props.put("mail.smtp.port", environment.getProperty("email.smtp.port"));
    }

    @Override
    public void sendEmail(String to, String subject, String content) {

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Done");
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
