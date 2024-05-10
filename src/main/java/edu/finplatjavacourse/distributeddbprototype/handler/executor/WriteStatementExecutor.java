package edu.finplatjavacourse.distributeddbprototype.handler.executor;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.WriteStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;


public class WriteStatementExecutor extends StatementExecutor {
    // TODO: DataWriter field

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
        // TODO: return DataWriter.doSomething((WriteStatement) statement)
        return Response.simpleResponse(false);
    }
}
