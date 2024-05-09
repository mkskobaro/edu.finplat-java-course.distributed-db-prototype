package edu.finplatjavacourse.distributeddbprototype.request.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.request.parsing.RequestParser;
import edu.finplatjavacourse.distributeddbprototype.request.parsing.RequestToStatementTransformer;
import edu.finplatjavacourse.distributeddbprototype.request.parsing.Statement;


public class WriteRequestParser extends RequestParser {
    private final WriteRequestValidator requestValidator = new WriteRequestValidator();
    private final RequestToStatementTransformer requestTransformer = new WriteRequestToStatementTransformer();

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
