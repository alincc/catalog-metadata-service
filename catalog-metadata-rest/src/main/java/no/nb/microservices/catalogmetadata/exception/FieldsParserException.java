package no.nb.microservices.catalogmetadata.exception;

public class FieldsParserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FieldsParserException(String message, Throwable cause) {
        super(message, cause);
    }

}
