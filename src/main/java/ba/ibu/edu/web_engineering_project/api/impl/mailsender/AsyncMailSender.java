package ba.ibu.edu.web_engineering_project.api.impl.mailsender;

import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.model.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AsyncMailSender {

    private final GmailSMTPSender mailSender;

    public AsyncMailSender(GmailSMTPSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendQRTicketsAsync(String sendTo, List<Ticket> tickets){
        mailSender.sendQRTickets(sendTo, tickets);
    }

    @Async
    public void sendAsync(User user, String message, String subject){
        mailSender.send(user, message, subject);
    }

    @Async
    public void sendEmailConfirmationAsync(String sendTo, String firstName, String confirmationCode){
        mailSender.sendEmailConfirmation(sendTo, firstName, confirmationCode);
    }
}
