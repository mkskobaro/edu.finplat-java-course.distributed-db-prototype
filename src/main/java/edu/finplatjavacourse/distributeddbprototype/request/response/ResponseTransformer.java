package edu.finplatjavacourse.distributeddbprototype.request.response;


import edu.finplatjavacourse.distributeddbprototype.request.executor.Response;


public interface ResponseTransformer {

    String transform(Response response);

    static String badRequest() {
        return "BAD REQUEST";
    }

    static String internal() {
        return "INTERNAL ERROR";
    }
}
