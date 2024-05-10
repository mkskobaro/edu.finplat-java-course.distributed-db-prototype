package edu.finplatjavacourse.distributeddbprototype.request.response;


public class SimpleResponseTransformer extends ResponseTransformer {
    public SimpleResponseTransformer(ResponseTransformer next) {
        super(next);
    }

    public SimpleResponseTransformer() {
        super(null);
    }

    @Override
    protected String transform0(Response response) {
        return ((Response.SimpleResponse) response).ok() ? "OK" : "FAIL";
    }

    @Override
    protected boolean canTransform(Response response) {
        return response instanceof Response.SimpleResponse;
    }
}
