package edu.finplatjavacourse.distributeddbprototype.handler.executor;


import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.ReadStatement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;
import edu.finplatjavacourse.distributeddbprototype.processing.searchservice.SearchService;


public class ReadStatementExecutor extends StatementExecutor {

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
        return SearchService.getInstance().getStringFromCacheOrSearchSubstring(((ReadStatement) statement));
    }
}
