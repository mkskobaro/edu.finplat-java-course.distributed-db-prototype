package edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestParser;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;


public class ReadRequestParser extends RequestParser {
    private final ReadRequestValidator requestValidator = new ReadRequestValidator();
    private final ReadRequestToStatementTransformer requestTransformer = new ReadRequestToStatementTransformer();


    public ReadRequestParser(RequestParser next) {
        super(next);
    }

    public ReadRequestParser() {
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
