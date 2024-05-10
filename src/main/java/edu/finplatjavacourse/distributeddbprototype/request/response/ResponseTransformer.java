package edu.finplatjavacourse.distributeddbprototype.request.response;


import edu.finplatjavacourse.distributeddbprototype.request.exception.ResponseTransformerException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class ResponseTransformer {
    private final ResponseTransformer next;

    public final String transform(Response response) {
        if (!canTransform(response)) {
            if (next == null) {
                throw new ResponseTransformerException("Can't transform response. No such transformer. Response: " + response);
            }
            return next.transform(response);
        }

        try {
            return transform0(response);
        } catch (Exception e) {
            throw new ResponseTransformerException("Unexpected while transforming response", e);
        }
    }

    protected abstract String transform0(Response response);

    protected abstract boolean canTransform(Response response);

    public static String badRequest() {
        return "BAD REQUEST";
    }

    public static String internal() {
        return "INTERNAL ERROR";
    }
}
