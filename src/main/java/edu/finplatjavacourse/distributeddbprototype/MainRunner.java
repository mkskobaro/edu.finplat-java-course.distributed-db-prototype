package edu.finplatjavacourse.distributeddbprototype;


import edu.finplatjavacourse.distributeddbprototype.handler.DelegatingRequestHandler;
import edu.finplatjavacourse.distributeddbprototype.handler.RequestHandler;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.CheckStatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.ReadStatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.StatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.WriteStatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestParser;
import edu.finplatjavacourse.distributeddbprototype.handler.response.AlreadyExistsResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.response.ReadResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.response.ResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.response.SimpleResponseTransformer;


public class MainRunner {
    public static void main(String[] args) {
        RequestHandler requestHandler = new DelegatingRequestHandler(
                requestParserChain(),
                statementExecutorChain(),
                responseTransformerChain());

        System.out.println("=======\"PUT 123, \\\"Hotel 123 \\\", 100\"=========");
        System.out.println(requestHandler.handle("PUT 123, \"Hotel 123 \", 100")); // should be valid
        System.out.println("=======\"PUT 321, \\\"Hotel 321 \\\", 100\"=========");
        System.out.println(requestHandler.handle("PUT 321, \"Hotel 321 \", 100")); // should be valid

        System.out.println("=======\"PUT 123, \\\"Hotel 123 \\\", 100\"=========");
        System.out.println(requestHandler.handle("PUT 123, \"Hotel 123 \", 100")); // should be invalid since such id already exists

        System.out.println("=======\"DFSFDSF\"=========");
        System.out.println(requestHandler.handle("DFSFDSF")); // should be invalid since bad validated
        System.out.println("=======\"PUT e234, \\\"Hotel 123 \\\", 100\"=========");
        System.out.println(requestHandler.handle("PUT e234, \"Hotel 123 \", 100")); // should be invalid since bad validated
        System.out.println("=======\"PUT 100, \\\"Hotel 123 \\\", dsafa54\"=========");
        System.out.println(requestHandler.handle("PUT 100, \"Hotel 123 \", dsafa54")); // should be invalid since bad validated

        System.out.println("=======\"GET \\\"Hotel\\\"\"=========");
        System.out.println(requestHandler.handle("GET \"Hotel\"")); // should be valid
        System.out.println("=======\"GET adsfasdfasdf\"=========");
        System.out.println(requestHandler.handle("GET adsfasdfasdf")); // should be invalid

        System.out.println("=======\"CHECK PUT 321, \\\"Hotel 321\\\", 100\"=========");
        System.out.println(requestHandler.handle("CHECK PUT 321, \"Hotel 321\", 100")); // should be valid
        System.out.println("=======\"CHECK GET adsfasdfasdf\"=========");
        System.out.println(requestHandler.handle("CHECK GET adsfasdfasdf")); // should be invalid
        System.out.println("=======\"CHECK PUT adsfasdfasdf\"=========");
        System.out.println(requestHandler.handle("CHECK PUT adsfasdfasdf")); // should be invalid
    }

    private static RequestParser requestParserChain() {
        return RequestParser.readRequestParser(
                RequestParser.checkRequestParser(
                        RequestParser.writeRequestParser(null)
                )
        );
    }

    private static StatementExecutor statementExecutorChain() {
        return new ReadStatementExecutor(new CheckStatementExecutor(new WriteStatementExecutor()));
    }

    private static ResponseTransformer responseTransformerChain() {
        return new ReadResponseTransformer(new SimpleResponseTransformer(new AlreadyExistsResponseTransformer()));
    }
}
