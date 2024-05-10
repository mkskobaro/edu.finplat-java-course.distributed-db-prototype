package edu.finplatjavacourse.distributeddbprototype.handler.executor;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.CheckStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;


public class CheckStatementExecutor extends StatementExecutor {
    // TODO: Checker field

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
        // TODO: return Checker.doSomething((CheckStatement) statement)
        return Response.simpleResponse(false);
    }
}
