package edu.finplatjavacourse.distributeddbprototype.handler.parsing;


import edu.finplatjavacourse.distributeddbprototype.handler.exception.ParsingException;
import edu.finplatjavacourse.distributeddbprototype.handler.exception.ValidatingException;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.*;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class RequestParser {
    private final RequestParser next;

    public static DelegatingRequestParser checkRequestParser(RequestParser next) {
        return new DelegatingRequestParser(next, new CheckRequestValidator(), new CheckRequestToStatementTransformer());
    }

    public static DelegatingRequestParser readRequestParser(RequestParser next) {
        return new DelegatingRequestParser(next, new ReadRequestValidator(), new ReadRequestToStatementTransformer());
    }

    public static DelegatingRequestParser writeRequestParser(RequestParser next) {
        return new DelegatingRequestParser(next, new WriteRequestValidator(), new WriteRequestToStatementTransformer());
    }

    public final Statement parse(String request) {
        if (!canProcess(request)) {
            if (next == null) {
                throw new ParsingException("Can't parse request. No such parser. Request: " + request);
            }
            return next.parse(request);
        }

        try {
            validate(request);
            return transform(request);
        } catch (ValidatingException | IllegalArgumentException e) {
            throw new ParsingException("Can't parse request", e);
        } catch (Exception e) {
            throw new ParsingException("Unexpected while parsing", e);
        }
    }

    protected abstract void validate(String request);

    protected abstract boolean canProcess(String request);

    protected abstract Statement transform(String request);

}
