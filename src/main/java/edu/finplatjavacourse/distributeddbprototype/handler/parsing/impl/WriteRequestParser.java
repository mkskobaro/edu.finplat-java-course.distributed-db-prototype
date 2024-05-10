package edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestParser;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;


public class WriteRequestParser extends RequestParser {
    private final WriteRequestValidator requestValidator = new WriteRequestValidator();
    private final WriteRequestToStatementTransformer requestTransformer = new WriteRequestToStatementTransformer();

    public WriteRequestParser(RequestParser next) {
        super(next);
    }

    public WriteRequestParser() {
        super(null);
    }

    @Override
    protected void validate(String request) {
        requestValidator.validate(request);
    }

    @Override
    protected boolean canProcess(String request) {
        return requestValidator.canParse(request);
    }

    @Override
    protected Statement transform(String request) {
        return requestTransformer.transform(request);
    }
}
