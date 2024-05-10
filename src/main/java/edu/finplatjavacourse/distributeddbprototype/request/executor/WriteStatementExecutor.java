package edu.finplatjavacourse.distributeddbprototype.request.executor;


import edu.finplatjavacourse.distributeddbprototype.request.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.request.parsing.impl.WriteStatement;
import edu.finplatjavacourse.distributeddbprototype.request.response.Response;


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
        // TODO: return DataWriter.doSomething(statement)
        return Response.simpleResponse(false);
    }
}
