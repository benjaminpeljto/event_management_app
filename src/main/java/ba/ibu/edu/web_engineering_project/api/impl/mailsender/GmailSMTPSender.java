package ba.ibu.edu.web_engineering_project.api.impl.mailsender;

import ba.ibu.edu.web_engineering_project.api.impl.mailsender.templatebuilder.EmailTemplateBuilder;
import ba.ibu.edu.web_engineering_project.core.api.mailsender.MailSender;
import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class GmailSMTPSender implements MailSender {

    private final String fromEmailGmail;
    private final JavaMailSender javaMailSender;
    private final String pathToTempQR;
    private final EmailTemplateBuilder builder;

    public GmailSMTPSender(String fromEmailGmail, JavaMailSender javaMailSender, String pathToTempQR, EmailTemplateBuilder builder) {
        this.fromEmailGmail = fromEmailGmail;
        this.javaMailSender = javaMailSender;
        this.pathToTempQR = pathToTempQR;
        this.builder = builder;
    }

    @Override
    public void send(User user, String message, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage
                .setFrom(fromEmailGmail);
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setText(builder.generateConfirmAccountTemplate(user.getFirstName(), user.getVerificationToken()));
        simpleMailMessage.setSubject(subject);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendQRTickets(String sendTo, List<Ticket> ticketList) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromEmailGmail);
            helper.setTo(sendTo);
            helper.setSubject("Successful purchase - Eventify");
            helper.setText("Thank you for purchasing tickets at Eventify!\n\n" +
                    "You can find your bought ticket's QR codes in the attachment of this email." +
                    "\nSee you at the event!" +
                    "\n\n Eventify Team.");

            for (Ticket ticket : ticketList) {
                String qrCodeFileName =
                        ticket.getEvent().getTitle() + "_" +
                                ticket.getTicketType().name() + "_" +
                                ticket.getId() + ".jpg";

                Path qrCodePath = Paths.get(pathToTempQR + File.separator + qrCodeFileName);
                helper.addAttachment(qrCodeFileName, qrCodePath.toFile());
            }

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @Override
    public void sendEmailConfirmation(String sendTo, String firstName, String confirmationCode) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromEmailGmail);
            helper.setTo(sendTo);
            helper.setSubject("Confirm your account - Eventify");
            helper.setText(builder.generateConfirmAccountTemplate(firstName, confirmationCode), true);
            javaMailSender.send(message);

        }catch (MessagingException e) {
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

}
