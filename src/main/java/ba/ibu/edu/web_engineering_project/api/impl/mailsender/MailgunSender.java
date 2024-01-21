package ba.ibu.edu.web_engineering_project.api.impl.mailsender;

import ba.ibu.edu.web_engineering_project.core.api.mailsender.MailSender;
import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//This implementation only works for sending emails to:
// benjaminpeljto00@gmail.com
// benjamin.peljto@stu.ibu.edu.ba
// benjamintere@windowslive.com
@Component
public class MailgunSender implements MailSender {

    private final RestTemplate restTemplate;
    private final String fromEmail;

    public MailgunSender(RestTemplate restTemplate, String fromEmail) {
        this.restTemplate = restTemplate;
        this.fromEmail = fromEmail;
    }

    @Override
    public void send(User user, String message, String subject) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", fromEmail);
        map.add("to", user.getEmail());

        map.add("subject", subject);
        map.add("text", message);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        restTemplate.postForEntity("/messages", request, String.class).getBody();
    }

    @Override
    public void sendQRTickets(String sendTo, List<Ticket> ticketList) {

    }

    @Override
    public void sendEmailConfirmation(String sendTo, String firstName, String confirmationCode) {

    }

    /*@Override
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
    }*/
}
