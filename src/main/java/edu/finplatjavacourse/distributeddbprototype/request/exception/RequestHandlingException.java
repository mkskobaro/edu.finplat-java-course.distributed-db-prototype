package edu.finplatjavacourse.distributeddbprototype.request.exception;


public class RequestHandlingException extends RuntimeException {
    public RequestHandlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestHandlingException(String message) {
        super(message);
    }
}
