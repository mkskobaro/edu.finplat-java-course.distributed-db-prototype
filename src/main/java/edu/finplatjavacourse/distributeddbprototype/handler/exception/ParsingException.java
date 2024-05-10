package edu.finplatjavacourse.distributeddbprototype.handler.exception;


public class ParsingException extends RequestHandlingException {
    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingException(String message) {
        super(message);
    }
}
