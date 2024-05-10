package edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestToStatementTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;


public class CheckRequestToStatementTransformer implements RequestToStatementTransformer {
    @Override
    public Statement transform(String request) {
        try {
            String[] tokens = request.substring(10).split(", ");
            return new CheckStatement(Long.parseLong(tokens[0]));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Bad formatted request. Request: " + request, e);
        }
    }
}
