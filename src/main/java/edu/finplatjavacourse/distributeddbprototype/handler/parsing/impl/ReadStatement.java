package edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;


public record ReadStatement(String searchOn) implements Statement {
}
