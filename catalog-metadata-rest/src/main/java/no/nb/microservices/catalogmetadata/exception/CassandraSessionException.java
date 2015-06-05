package no.nb.microservices.catalogmetadata.exception;

public class CassandraSessionException extends RuntimeException {

    public CassandraSessionException() {
    }

    public CassandraSessionException(String message) {
        super(message);
    }

    public CassandraSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CassandraSessionException(Throwable cause) {
        super(cause);
    }

    public CassandraSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
