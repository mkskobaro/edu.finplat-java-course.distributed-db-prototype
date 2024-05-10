package edu.finplatjavacourse.distributeddbprototype.handler.exception;


public class ValidatingException extends ParsingException {
    public ValidatingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatingException(String message) {
        super(message);
    }
}
