package edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.handler.exception.ValidatingException;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestValidator;


public class ReadRequestValidator implements RequestValidator {
    @Override
    public void validate(String request) {
        if (!request.matches("GET \".+\"")) {
            throw new ValidatingException("Bad request. Should be: GET \"STRING\". Given: " + request);
        }
    }

    @Override
    public boolean canParse(String request) {
        return request.startsWith("GET ");
    }
}
