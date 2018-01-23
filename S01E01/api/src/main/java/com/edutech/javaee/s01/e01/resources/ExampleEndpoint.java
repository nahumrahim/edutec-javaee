package com.edutech.javaee.s01.e01.resources;

import java.util.Arrays;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author nahum
 */
@Path("/")
public class ExampleEndpoint {

    @GET
    public String welcome() {
        return "Hello World!";
    }
    
    @GET
    @Path("string-list")
    //@Produces({"application/json"})
    public Response getNamesAsString() {
        String names[] = { "Java", "Scala", "Kotlins" };
        return Response.ok( Arrays.asList(names) ).build();
    }
    
}
