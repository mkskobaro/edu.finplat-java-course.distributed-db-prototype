package edu.finplatjavacourse.distributeddbprototype.request.exception;


public class ValidatingException extends ParsingException {
    public ValidatingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatingException(String message) {
        super(message);
    }
}
