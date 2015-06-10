package no.nb.microservices.catalogmetadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author ronnymikalsen
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Field")
public class FieldNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public FieldNotFoundException(String message) {
        super(message);
    }

}
