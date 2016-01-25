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
    public void handleCassadraSessionException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cassandra session problem");
    }

    @ExceptionHandler(FieldNotFoundException.class)
    public void handleFieldsNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), "No such fields");
    }

    @ExceptionHandler(FieldsParserException.class)
    public void handleFieldsParserException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Cant parse fields");
    }

    @ExceptionHandler(ModsNotFoundException.class)
    public void handleModsNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), "No such mods");
    }

    @ExceptionHandler(StructNotFoundException.class)
    public void handleStructNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), "No such struct");
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
