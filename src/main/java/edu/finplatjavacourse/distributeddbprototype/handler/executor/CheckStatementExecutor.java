package edu.finplatjavacourse.distributeddbprototype.handler.executor;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.CheckStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;
import edu.finplatjavacourse.distributeddbprototype.processing.WriteEngine;

import java.io.IOException;


public class CheckStatementExecutor extends StatementExecutor {

    public CheckStatementExecutor(StatementExecutor next) {
        super(next);
    }

    public CheckStatementExecutor() {
        super(null);
    }

    @Override
    protected boolean canProcess(Statement statement) {
        return statement instanceof CheckStatement;
    }

    @Override
    protected Response process(Statement statement) {
        try {
            return WriteEngine.getInstance().checkPutKey((CheckStatement) statement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
