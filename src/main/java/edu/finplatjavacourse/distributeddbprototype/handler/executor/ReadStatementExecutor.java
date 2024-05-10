package edu.finplatjavacourse.distributeddbprototype.handler.executor;


import edu.finplatjavacourse.distributeddbprototype.entity.Hotel;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.ReadStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;
import edu.finplatjavacourse.distributeddbprototype.handler.response.ReadResponse;


public class ReadStatementExecutor extends StatementExecutor {
    // TODO: DataReader field

    public ReadStatementExecutor(StatementExecutor next) {
        super(next);
    }

    public ReadStatementExecutor() {
        super(null);
    }

    @Override
    protected boolean canProcess(Statement statement) {
        return statement instanceof ReadStatement;
    }

    @Override
    protected Response process(Statement statement) {
        // TODO: return dataReader.doSomething((ReadStatement) statement)
        return ReadResponse.builder()
                .hotel(new Hotel(1L, "Hotel 1", 1L))
                .hotel(new Hotel(2L, "Hotel 2", 2L))
                .hotel(new Hotel(3L, "Hotel 3", 3L))
                .build();
    }
}
