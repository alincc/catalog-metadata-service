package no.nb.microservices.catalogmetadata.exception;

public class CassandraSessionException extends RuntimeException {

    public CassandraSessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
