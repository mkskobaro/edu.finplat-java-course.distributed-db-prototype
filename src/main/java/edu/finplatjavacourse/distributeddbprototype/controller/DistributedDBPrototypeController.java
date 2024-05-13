package edu.finplatjavacourse.distributeddbprototype.controller;

import edu.finplatjavacourse.distributeddbprototype.handler.RequestHandler;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Singleton
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
        if (result.startsWith("BAD REQUEST") || result.startsWith("FAIL"))
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        if (result.startsWith("INTERNAL ERROR"))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        return Response.status(200).entity(result).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(String query){
        String result = requestHandler.handle(query);
        if (result.startsWith("BAD REQUEST") || result.startsWith("FAIL"))
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        if (result.startsWith("INTERNAL ERROR"))
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        return Response.status(201).build();
    }
}
