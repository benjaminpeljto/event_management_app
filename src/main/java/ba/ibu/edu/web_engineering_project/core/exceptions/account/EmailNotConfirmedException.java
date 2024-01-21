package ba.ibu.edu.web_engineering_project.core.exceptions.account;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EmailNotConfirmedException extends DisabledException {
    public EmailNotConfirmedException(String msg) {
        super(msg);
    }

    public EmailNotConfirmedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
