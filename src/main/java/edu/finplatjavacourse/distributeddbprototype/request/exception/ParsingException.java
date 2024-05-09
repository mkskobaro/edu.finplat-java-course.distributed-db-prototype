package edu.finplatjavacourse.distributeddbprototype.request.exception;


public class ParsingException extends RequestHandlingException {
    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingException(String message) {
        super(message);
    }
}
