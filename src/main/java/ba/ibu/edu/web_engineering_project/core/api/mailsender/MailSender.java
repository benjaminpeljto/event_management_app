package ba.ibu.edu.web_engineering_project.core.api.mailsender;

import ba.ibu.edu.web_engineering_project.core.model.Ticket;
import ba.ibu.edu.web_engineering_project.core.model.User;

import java.util.List;

public interface MailSender {
    public void send(User user, String message, String subject);
    public void sendQRTickets(String sendTo, List<Ticket> ticketList);
    public void sendEmailConfirmation(String sendTo, String firstName, String confirmationCode);
}
