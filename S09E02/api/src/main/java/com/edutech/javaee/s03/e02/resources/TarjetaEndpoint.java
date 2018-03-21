package com.edutech.javaee.s03.e02.resources;

import com.edutech.javaee.s03.e02.model.Tarjeta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nahum
 */
@Stateless
@Path("/operaciones")
public class TarjetaEndpoint {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    @GET
    @Produces({"application/json"})
    public List<Tarjeta> findAll() {
        List<Tarjeta> tarjetas = em.createQuery("SELECT u FROM Tarjeta u ", Tarjeta.class)
                .getResultList();        
        return tarjetas;
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        Tarjeta tarjeta = this.em.find(Tarjeta.class, id);
        
        if (tarjeta == null)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")                    
                    .build();
        
        return Response.ok(tarjeta, MediaType.APPLICATION_JSON).build();
    }
        
    @GET
    @Path("validate/{number}")
    public Response validate(@PathParam("number") Long id) {
        return Response.ok().build();
    }
}
