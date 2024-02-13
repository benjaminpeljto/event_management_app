package ba.ibu.edu.web_engineering_project.core.exceptions.event;

import ba.ibu.edu.web_engineering_project.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ba.ibu.edu.web_engineering_project.common.constants.HttpCodes.BAD_REQUEST;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEventCategoryException extends GeneralException {
    public InvalidEventCategoryException() {
        super(BAD_REQUEST);
    }
    public InvalidEventCategoryException(String message) {
        super(BAD_REQUEST, message);
    }
}
