package ba.ibu.edu.web_engineering_project.core.exceptions.account;

import org.springframework.security.authentication.DisabledException;

public class EmailNotConfirmedException extends DisabledException {
    public EmailNotConfirmedException(String msg) {
        super(msg);
    }

    public EmailNotConfirmedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
