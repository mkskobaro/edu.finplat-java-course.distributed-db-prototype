package edu.finplatjavacourse.distributeddbprototype.request.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.request.exception.ValidatingException;
import edu.finplatjavacourse.distributeddbprototype.request.parsing.RequestValidator;


public class WriteRequestValidator implements RequestValidator {
    @Override
    public void validate(String request) {
        if (!request.matches("PUT [-+]?\\d+, \".+\", [-+]?\\d+")) {
            throw new ValidatingException("Bad request. Should be: PUT LONG, \"STRING\", LONG. Given: " + request);
        }
    }

    @Override
    public boolean canParse(String request) {
        return request.startsWith("PUT ");
    }
}
