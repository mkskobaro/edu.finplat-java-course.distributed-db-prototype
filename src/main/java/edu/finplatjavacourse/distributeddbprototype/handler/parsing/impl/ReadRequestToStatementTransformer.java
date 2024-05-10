package edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestToStatementTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;


public class ReadRequestToStatementTransformer implements RequestToStatementTransformer {
    @Override
    public Statement transform(String request) {
        return new ReadStatement(request.substring(4));
    }
}
