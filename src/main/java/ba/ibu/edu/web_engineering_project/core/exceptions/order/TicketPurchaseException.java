package ba.ibu.edu.web_engineering_project.core.exceptions.order;

import ba.ibu.edu.web_engineering_project.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ba.ibu.edu.web_engineering_project.common.constants.HttpCodes.BAD_REQUEST;
import static ba.ibu.edu.web_engineering_project.common.constants.HttpCodes.CONFLICT;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TicketPurchaseException extends GeneralException {

    public TicketPurchaseException() {
        super(BAD_REQUEST);
    }
    public TicketPurchaseException(String message) {
        super(BAD_REQUEST, message);
    }
}
