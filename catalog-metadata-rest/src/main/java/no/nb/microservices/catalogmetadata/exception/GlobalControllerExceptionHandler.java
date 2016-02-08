package no.nb.microservices.catalogmetadata.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(CassandraSessionException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cassandra session problem")
    public void handleCassadraSessionException(HttpServletResponse response) throws IOException {
    }

    @ExceptionHandler(FieldNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such fields")
    public void handleFieldsNotFound(HttpServletResponse response) throws IOException {
    }

    @ExceptionHandler(FieldsParserException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cant parse fields")
    public void handleFieldsParserException(HttpServletResponse response) throws IOException {
    }

    @ExceptionHandler(ModsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such mod")
    public void handleModsNotFound(HttpServletResponse response) throws IOException {
    }

    @ExceptionHandler(StructNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such struct")
    public void handleStructNotFound(HttpServletResponse response) throws IOException {
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "It looks like we have a internal error in our application. The error have been logged and will be looked at by our development team")
    public void defaultHandler(HttpServletRequest req, Exception e) {
        LOGGER.error("" +
                "Got an unexcepted exception.\n" +
                "Request URI: " + req.getRequestURI() + "\n" +
                "Auth Type: " + req.getAuthType() + "\n" +
                "Context Path: " + req.getContextPath() + "\n" +
                "Path Info: " + req.getPathInfo() + "\n" +
                "Query String: " + req.getQueryString() + "\n" +
                "Remote User: " + req.getRemoteUser() + "\n" +
                "Method: " + req.getMethod() + "\n" +
                "Username: " + ((req.getUserPrincipal()  != null) ? req.getUserPrincipal().getName() : "Anonymous") + "\n"
                , e);
    }

}
