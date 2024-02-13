package ba.ibu.edu.web_engineering_project.core.exceptions.account;

import ba.ibu.edu.web_engineering_project.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ba.ibu.edu.web_engineering_project.common.constants.HttpCodes.FORBIDDEN;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class CustomBadCredentialsException extends GeneralException {
    public CustomBadCredentialsException() {
        super(FORBIDDEN);
    }
    public CustomBadCredentialsException(String message) {
        super(FORBIDDEN, message);
    }
}
