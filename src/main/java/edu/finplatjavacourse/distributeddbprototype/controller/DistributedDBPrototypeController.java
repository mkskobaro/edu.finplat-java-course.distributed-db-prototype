package edu.finplatjavacourse.distributeddbprototype.controller;

import edu.finplatjavacourse.distributeddbprototype.ApplicationRunner;
import edu.finplatjavacourse.distributeddbprototype.handler.DelegatingRequestHandler;
import edu.finplatjavacourse.distributeddbprototype.handler.RequestHandler;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.CheckStatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.ReadStatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.StatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.executor.WriteStatementExecutor;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.RequestParser;
import edu.finplatjavacourse.distributeddbprototype.handler.parsing.Statement;
import edu.finplatjavacourse.distributeddbprototype.handler.response.AlreadyExistsResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.response.ReadResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.response.ResponseTransformer;
import edu.finplatjavacourse.distributeddbprototype.handler.response.SimpleResponseTransformer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class DistributedDBPrototypeController {
    private final RequestHandler requestHandler;

    @Inject
    public DistributedDBPrototypeController(RequestHandlerSupplier requestHandlerSupplier) {
        this.requestHandler = requestHandlerSupplier.getRequestHandler();
    }

    @GET
    @Path("/{query}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response get(@PathParam(value = "query") String query){
        String result = requestHandler.handle(query);
        if (result.equals("BAD REQUEST") || result.startsWith("FAIL"))
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        if (result.equals("INTERNAL ERROR"))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        return Response.status(200).entity(result).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(String query){
        String result = requestHandler.handle(query);
        if (result.equals("BAD REQUEST") || result.startsWith("FAIL"))
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        if (result.equals("INTERNAL ERROR"))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        return Response.status(201).build();
    }
}
