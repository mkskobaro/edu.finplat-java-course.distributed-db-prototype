package edu.finplatjavacourse.distributeddbprototype.request.response;


import edu.finplatjavacourse.distributeddbprototype.request.exception.ResponseTransformerException;
import edu.finplatjavacourse.distributeddbprototype.request.executor.Response;


public class SimpleResponseTransformer implements ResponseTransformer {
    @Override
    public String transform(Response response) {
        if(response instanceof Response.SimpleResponse simpleResponse)
            return simpleResponse.ok() ? "OK" : "FAIL";
        throw new ResponseTransformerException("Response should be instance of Response.SimpleResponse. Given: " + response.getClass().getName());
    }
}
