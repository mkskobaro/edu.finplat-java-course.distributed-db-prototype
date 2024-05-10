package edu.finplatjavacourse.distributeddbprototype.handler.parsing;


public interface RequestToStatementTransformer {

    Statement transform(String request);

}
