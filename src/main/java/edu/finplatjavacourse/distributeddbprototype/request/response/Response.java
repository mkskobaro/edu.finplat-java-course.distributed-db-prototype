package edu.finplatjavacourse.distributeddbprototype.request.response;


public interface Response {
    static Response simpleResponse() {
        return new SimpleResponse(true);
    }

    static Response simpleResponse(boolean isOk) {
        return new SimpleResponse(isOk);
    }

    static Response idAlreadyExistsResponse() {
        return new AlreadyExistsResponse("id");
    }

    /* Simple responses */

    record SimpleResponse(boolean ok) implements Response {
    }

    record AlreadyExistsResponse(String fieldName) implements Response {
    }
}

