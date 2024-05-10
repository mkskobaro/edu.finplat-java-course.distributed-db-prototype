package edu.finplatjavacourse.distributeddbprototype.handler.exception;


public class RequestHandlingException extends RuntimeException {
    public RequestHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestHandlingException(String message) {
        super(message);
    }
}
