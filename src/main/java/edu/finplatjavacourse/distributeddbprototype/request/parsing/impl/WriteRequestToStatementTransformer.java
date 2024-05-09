package edu.finplatjavacourse.distributeddbprototype.request.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.request.parsing.RequestToStatementTransformer;
import edu.finplatjavacourse.distributeddbprototype.request.parsing.Statement;


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
