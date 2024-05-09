package edu.finplatjavacourse.distributeddbprototype.request.executor;


public interface Response {
    static Response simpleResponse() {
        return new SimpleResponse(true);
    }

    static Response simpleResponse(boolean isOk) {
        return new SimpleResponse(isOk);
    }

    record SimpleResponse(boolean ok) implements Response {
    }
}

