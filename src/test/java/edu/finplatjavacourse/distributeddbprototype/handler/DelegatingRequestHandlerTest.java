package edu.finplatjavacourse.distributeddbprototype.handler;


import edu.finplatjavacourse.distributeddbprototype.handler.executor.ReadStatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.StatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.WriteStatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestParser;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.ReadRequestParser;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.impl.WriteRequestParser;
import edu.finplatjavacourse.distributeddbprototype.handler.response.AlreadyExistsResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.response.ReadResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.response.ResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.response.SimpleResponseTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


//@ExtendWith(MockitoExtension.class)
//class DelegatingRequestHandlerTest {
//    RequestHandler requestHandler = new DelegatingRequestHandler(
//            requestParserChain(),
//            statementExecutorChain(),
//            responseTransformerChain());
//
//    private static RequestParser requestParserChain() {
//        return new ReadRequestParser(
//                new WriteRequestParser());
//    }
//
//    private static StatementExecutor statementExecutorChain() {
//        return new ReadStatementExecutor(
//                new WriteStatementExecutor());
//    }
//
//    private static ResponseTransformer responseTransformerChain() {
//        return new ReadResponseTransformer(
//                new SimpleResponseTransformer(
//                        new AlreadyExistsResponseTransformer()));
//    }
//
//    @Test
//    void handle_GetRequestWithExistingSubstring_ReturnsHotelsWithThatSubstring() {
//        String result = requestHandler.handle("GET \"Hotel\"");
//
//        assertEquals("""
//                1, "Hotel 1", 1
//                2, "Hotel 2", 2
//                3, "Hotel 3", 3
//                """, result);
//    }
//
//    @Test
//    void handle_GetRequestWithNoSuchSubstring_ReturnsNoHotels() {
//        String result = requestHandler.handle("GET \"Hotel\"");
//
//        assertEquals("", result);
//    }
//
//    @Test
//    void handle_PutRequestWithWithUniqueId_ReturnsOk() {
//        String result = requestHandler.handle("PUT 101, \"Hotel 101\", 101");
//
//        assertEquals("OK", result);
//    }
//
//    @Test
//    void handle_PutRequestWithNotUniqueId_ReturnsFail() {
//        String result = requestHandler.handle("PUT 101, \"Hotel 101\", 101");
//
//        assertEquals("FAIL. CONSTRAINT CHECK FAILED. FIELD \"id\" IS NOT UNIQUE", result);
//    }
//
//    @Test
//    void handle_InvalidRequest_ReturnsBadRequest() {
//        String result = requestHandler.handle("SomethingWrong");
//
//        assertEquals("BAD REQUEST", result);
//    }
//}