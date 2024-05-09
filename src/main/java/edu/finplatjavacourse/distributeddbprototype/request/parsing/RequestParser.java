package edu.finplatjavacourse.distributeddbprototype.request.parsing;


import edu.finplatjavacourse.distributeddbprototype.request.exception.ParsingException;
import edu.finplatjavacourse.distributeddbprototype.request.exception.ValidatingException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class RequestParser {
    private final RequestParser next;

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
