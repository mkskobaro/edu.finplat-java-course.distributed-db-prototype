package edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestParser;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;


public class CheckRequestParser extends RequestParser {
    private final CheckRequestValidator requestValidator = new CheckRequestValidator();
    private final CheckRequestToStatementTransformer requestTransformer = new CheckRequestToStatementTransformer();

    public CheckRequestParser(RequestParser next) {
        super(next);
    }

    public CheckRequestParser() {
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
