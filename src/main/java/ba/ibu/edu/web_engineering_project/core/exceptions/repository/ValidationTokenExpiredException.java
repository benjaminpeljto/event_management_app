package ba.ibu.edu.web_engineering_project.core.exceptions.repository;

import ba.ibu.edu.web_engineering_project.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ba.ibu.edu.web_engineering_project.common.constants.HttpCodes.BAD_REQUEST;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationTokenExpiredException extends GeneralException {
    public ValidationTokenExpiredException(String message) {
        super(BAD_REQUEST, message);
    }
    public ValidationTokenExpiredException() {
        super(BAD_REQUEST);
    }

}
