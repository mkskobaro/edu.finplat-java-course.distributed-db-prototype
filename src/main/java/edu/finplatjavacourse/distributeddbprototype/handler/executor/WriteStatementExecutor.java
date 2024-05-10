package edu.finplatjavacourse.distributeddbprototype.handler.executor;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.WriteStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;
import edu.finplatjavacourse.distributeddbprototype.processing.WriteEngine;

import java.io.IOException;


public class WriteStatementExecutor extends StatementExecutor {
    public WriteStatementExecutor(StatementExecutor next) {
        super(next);
    }

    public WriteStatementExecutor() {
        super(null);
    }

    @Override
    protected boolean canProcess(Statement statement) {
        return statement instanceof WriteStatement;
    }

    @Override
    protected Response process(Statement statement) {
        // TODO: concurrent write
        // TODO: if cannot construct WriteEngine -> illegal state -> log, shutdown
        try {
            return WriteEngine.getInstance().write((WriteStatement) statement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
