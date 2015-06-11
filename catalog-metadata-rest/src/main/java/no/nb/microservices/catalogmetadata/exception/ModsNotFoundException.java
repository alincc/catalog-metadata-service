package no.nb.microservices.catalogmetadata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Mods not found")
public class ModsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ModsNotFoundException(String message) {
        super(message);
    }
}
