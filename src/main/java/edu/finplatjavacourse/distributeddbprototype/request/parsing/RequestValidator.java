package edu.finplatjavacourse.distributeddbprototype.request.parsing;


public interface RequestValidator {
    void validate(String request);

    boolean canParse(String request);
}
