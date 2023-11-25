package ba.ibu.edu.web_engineering_project.api.impl.mailsender;

import ba.ibu.edu.web_engineering_project.core.api.mailsender.MailSender;
import ba.ibu.edu.web_engineering_project.core.model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class GmailSMTPSender implements MailSender {

    private final String fromEmailGmail;
    private final JavaMailSender javaMailSender;
    public GmailSMTPSender(String fromEmailGmail, JavaMailSender javaMailSender) {
        this.fromEmailGmail = fromEmailGmail;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String send(User user, String message, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage
                .setFrom(fromEmailGmail);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setText(message);
        simpleMailMessage.setSubject(subject);

        javaMailSender.send(simpleMailMessage);
        return "Mail sent successfully";
    }
}
