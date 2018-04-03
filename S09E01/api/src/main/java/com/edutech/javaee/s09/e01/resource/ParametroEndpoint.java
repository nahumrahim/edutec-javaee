package com.edutech.javaee.s09.e01.resource;

import com.edutech.javaee.s09.e01.dao.ParametroSistemaDao;
import com.edutech.javaee.s09.e01.dto.ErrorMessageDto;
import com.edutech.javaee.s09.e01.dto.ParametroDto;
import com.edutech.javaee.s09.e01.model.ParametroSistema;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author nahum
 */
//@Stateless
@Path("/parametros")
public class ParametroEndpoint {
    
    @Inject
    ParametroSistemaDao dao;

    @GET
    @Produces({"application/json"})
    @RolesAllowed("ADMIN")
    public List<ParametroSistema> findAll(@Context SecurityContext context) {
        System.out.println( context.getAuthenticationScheme());
        System.out.println( context.getUserPrincipal().getName() );
        System.out.println( context.isUserInRole("ADMIN") );
        System.out.println( context.isSecure() );
        return this.dao.findAll();
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        ParametroSistema parametro = this.dao.find(id);
        if (parametro == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }
        return Response.ok(parametro).build();
    }
    
    @POST
    @Produces({"application/json"})
    public Response pruebaPOST(ParametroDto dto) {
        System.out.println("Prueba de POST");
        return Response.ok("Parametro creado").build();
    }

}
