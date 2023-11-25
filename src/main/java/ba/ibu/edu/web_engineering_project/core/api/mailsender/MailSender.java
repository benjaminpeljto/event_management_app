package ba.ibu.edu.web_engineering_project.core.api.mailsender;

import ba.ibu.edu.web_engineering_project.core.model.User;

public interface MailSender {
    public String send(User user, String message, String subject);
}
