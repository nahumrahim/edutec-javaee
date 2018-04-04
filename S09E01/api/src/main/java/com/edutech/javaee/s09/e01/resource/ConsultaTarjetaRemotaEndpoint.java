package com.edutech.javaee.s09.e01.resource;

import com.edutech.javaee.s09.e01.service.TarjetaService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author nahum
 */
@Stateless
@Path("consulta-tarjeta")
public class ConsultaTarjetaRemotaEndpoint {
    
    @Inject
    TarjetaService tarjetaService;

    @GET
    @Produces({"application/json"})
    @Path("{idCard}")
    public Response consulta(@PathParam("idCard") String idCard) {
        List<String> informacion = tarjetaService.getCardAuthorization(idCard);
        informacion.stream().forEach(info -> {
            System.out.println(info);
        });
        
        // pago
        tarjetaService.pagarTarjeta();
            
        return Response.ok().build();
    }
    
}
