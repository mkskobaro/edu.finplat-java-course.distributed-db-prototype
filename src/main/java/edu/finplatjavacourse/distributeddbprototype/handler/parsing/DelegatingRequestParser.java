package edu.finplatjavacourse.distributeddbprototype.handler.parsing;


public class DelegatingRequestParser extends RequestParser {
    private final RequestValidator requestValidator;
    private final RequestToStatementTransformer requestTransformer;


    public DelegatingRequestParser(RequestParser next,
                                   RequestValidator validator,
                                   RequestToStatementTransformer transformer) {
        super(next);
        this.requestValidator = validator;
        this.requestTransformer = transformer;
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
