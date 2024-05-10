package edu.finplatjavacourse.distributeddbprototype.request.response;


public class AlreadyExistsResponseTransformer extends ResponseTransformer {

    public AlreadyExistsResponseTransformer(ResponseTransformer next) {
        super(next);
    }

    public AlreadyExistsResponseTransformer() {
        super(null);
    }

    @Override
    protected String transform0(Response response) {
        return "FAIL. CONSTRAINT CHECK FAILED. FIELD " + ((Response.AlreadyExistsResponse) response).fieldName() + " IS NOT UNIQUE";
    }

    @Override
    protected boolean canTransform(Response response) {
        return response instanceof Response.AlreadyExistsResponse;
    }
}
