package edu.finplatjavacourse.distributeddbprototype.handler.executor;


import edu.finplatjavacourse.distributeddbprototype.handler.exception.ExecutionException;
import edu.finplatjavacourse.distributeddbprototype.handler.exception.StatementProcessingException;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class StatementExecutor {
    private final StatementExecutor next;

    public final Response execute(Statement statement) {
        if (!canProcess(statement)) {
            if (next == null) {
                throw new StatementProcessingException("Can't process statement. No such executor. Statement: " + statement);
            }
            return next.execute(statement);
        }

        try {
            return process(statement);
        } catch (ExecutionException e) {
            throw new StatementProcessingException("Execution failed", e);
        } catch (Exception e) {
            throw new StatementProcessingException("Unexpected while execution", e);
        }
    }

    protected abstract boolean canProcess(Statement statement);

    protected abstract Response process(Statement statement);
}
