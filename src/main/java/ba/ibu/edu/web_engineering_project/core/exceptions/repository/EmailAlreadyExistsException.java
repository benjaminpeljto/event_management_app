package ba.ibu.edu.web_engineering_project.core.exceptions.repository;

import ba.ibu.edu.web_engineering_project.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ba.ibu.edu.web_engineering_project.common.constants.HttpCodes.CONFLICT;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends GeneralException {

    public EmailAlreadyExistsException() {
        super(CONFLICT);
    }

    public EmailAlreadyExistsException(String message) {
        super(CONFLICT, message);
    }
}
