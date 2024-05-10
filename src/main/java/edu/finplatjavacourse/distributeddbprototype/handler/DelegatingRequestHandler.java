package edu.finplatjavacourse.distributeddbprototype.handler;


import edu.finplatjavacourse.distributeddbprototype.handler.exception.ParsingException;
import edu.finplatjavacourse.distributeddbprototype.handler.exception.ResponseTransformerException;
import edu.finplatjavacourse.distributeddbprototype.handler.exception.StatementProcessingException;
import edu.finplatjavacourse.distributeddbprototype.handler.response.Response;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.StatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestParser;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.ResponseTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class DelegatingRequestHandler implements RequestHandler {
    private final RequestParser requestParser;
    private final StatementExecutor statementExecutor;
    private final ResponseTransformer responseTransformer;

    @Override
    public String handle(String request) {
        try {
            Statement statement = requestParser.parse(request);
            Response response = statementExecutor.execute(statement);
            return responseTransformer.transform(response);
        } catch (ParsingException pe) {
            log.debug("Parsing failed.", pe);
            return ResponseTransformer.badRequest();
        } catch (StatementProcessingException spe) {
            log.error("Execution failed.", spe);
            return ResponseTransformer.internal();
        } catch (ResponseTransformerException rte) {
            log.error("Response construction failed.", rte);
            return ResponseTransformer.internal();
        } catch (Exception e) {
            log.error("Unexpected exception caught.", e);
            return ResponseTransformer.internal();
        }
    }
}
