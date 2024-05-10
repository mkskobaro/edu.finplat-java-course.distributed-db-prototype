package edu.finplatjavacourse.distributeddbprototype.handler.exception;


public class ResponseTransformerException extends RequestHandlingException {
    public ResponseTransformerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseTransformerException(String message) {
        super(message);
    }
}
