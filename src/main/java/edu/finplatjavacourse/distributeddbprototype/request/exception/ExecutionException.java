package edu.finplatjavacourse.distributeddbprototype.request.exception;


public class ExecutionException extends StatementProcessingException {
    public ExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutionException(String message) {
        super(message);
    }
}
