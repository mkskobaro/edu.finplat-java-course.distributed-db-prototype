package edu.finplatjavacourse.distributeddbprototype;


import edu.finplatjavacourse.distributeddbprototype.request.DelegatingRequestHandler;
import edu.finplatjavacourse.distributeddbprototype.request.RequestHandler;
import edu.finplatjavacourse.distributeddbprototype.request.executor.StatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.request.executor.WriteStatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.request.parsing.RequestParser;
import edu.finplatjavacourse.distributeddbprototype.request.parsing.impl.WriteRequestParser;
import edu.finplatjavacourse.distributeddbprototype.request.response.ResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.request.response.SimpleResponseTransformer;


public class MainRunner {
    public static void main(String[] args) {
        RequestHandler requestHandler = new DelegatingRequestHandler(
                requestParserChain(),
                statementExecutorChain(),
                responseTransformerChain());

        System.out.println(requestHandler.handle("PUT 123, \"Hotel 123 \", 100")); // should be valid
        System.out.println(requestHandler.handle("PUT 321, \"Hotel 321 \", 100")); // should be valid

        System.out.println(requestHandler.handle("PUT 123, \"Hotel 123 \", 100")); // should be invalid since such id already exists

        System.out.println(requestHandler.handle("DFSFDSF")); // should be invalid since bad validated
        System.out.println(requestHandler.handle("PUT e234, \"Hotel 123 \", 100")); // should be invalid since bad validated
        System.out.println(requestHandler.handle("PUT 100, \"Hotel 123 \", dsafa54")); // should be invalid since bad validated
    }

    private static RequestParser requestParserChain() {
        return new WriteRequestParser();
    }

    private static StatementExecutor statementExecutorChain() {
        return new WriteStatementExecutor();
    }

    private static ResponseTransformer responseTransformerChain() {
        return new SimpleResponseTransformer();
    }
}
