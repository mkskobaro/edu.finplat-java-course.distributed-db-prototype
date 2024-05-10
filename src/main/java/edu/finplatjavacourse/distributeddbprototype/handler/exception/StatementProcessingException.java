package edu.finplatjavacourse.distributeddbprototype.handler.exception;


public class StatementProcessingException extends RequestHandlingException {
    public StatementProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatementProcessingException(String message) {
        super(message);
    }
}
