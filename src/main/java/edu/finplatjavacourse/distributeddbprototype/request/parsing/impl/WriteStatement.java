package edu.finplatjavacourse.distributeddbprototype.request.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.request.parsing.Statement;


public record WriteStatement(long id, String name, long price) implements Statement {
}
