package edu.finplatjavacourse.distributeddbprototype.handler.parsing;


public interface RequestValidator {
    void validate(String request);

    boolean canParse(String request);
}
