package edu.finplatjavacourse.distributeddbprototype.controller;

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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class RequestHandlerSupplier {
    private final RequestHandler requestHandler;

    public RequestHandler getRequestHandler() {
        return requestHandler;
    }

    public RequestHandlerSupplier() {
        RequestParser requestParser = RequestParser.readRequestParser(
                RequestParser.checkRequestParser(
                        RequestParser.writeRequestParser(null)
                )
        );
        StatementExecutor statementExecutor = new ReadStatementExecutor(new CheckStatementExecutor(new WriteStatementExecutor()));
        ResponseTransformer responseTransformer =  new ReadResponseTransformer(new SimpleResponseTransformer(new AlreadyExistsResponseTransformer()));

        this.requestHandler = new DelegatingRequestHandler(
                requestParser,
                statementExecutor,
                responseTransformer);
    }
}
