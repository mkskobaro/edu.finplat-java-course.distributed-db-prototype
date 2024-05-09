package edu.finplatjavacourse.distributeddbprototype.request.exception;


public class StatementProcessingException extends RequestHandlingException {
    public StatementProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatementProcessingException(String message) {
        super(message);
    }
}
