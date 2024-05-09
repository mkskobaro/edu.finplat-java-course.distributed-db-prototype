package edu.finplatjavacourse.distributeddbprototype.request.parsing;


public interface RequestToStatementTransformer {

    Statement transform(String request);

}
