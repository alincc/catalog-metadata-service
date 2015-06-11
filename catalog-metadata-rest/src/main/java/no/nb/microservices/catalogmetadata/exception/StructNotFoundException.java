package no.nb.microservices.catalogmetadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Structure not found")
public class StructNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StructNotFoundException(String message) {
        super(message);
    }
}
