package edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestToStatementTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;


public class WriteRequestToStatementTransformer implements RequestToStatementTransformer {
    @Override
    public Statement transform(String request) {
        try {
            String[] tokens = request.substring(4).split(", ");
            return new WriteStatement(Long.parseLong(tokens[0]),
                    tokens[1].substring(1, tokens[1].length() - 1),
                    Long.parseLong(tokens[2]));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Bad formatted request. Request: " + request, e);
        }
    }
}
