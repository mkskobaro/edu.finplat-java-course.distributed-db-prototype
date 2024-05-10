package edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;


public record WriteStatement(long id, String name, long price) implements Statement {
}
