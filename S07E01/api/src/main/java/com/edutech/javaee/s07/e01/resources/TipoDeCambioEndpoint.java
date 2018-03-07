package com.edutech.javaee.s07.e01.resources;

import com.banguat.ws.tipocambio.InfoVariable;
import com.banguat.ws.tipocambio.TipoCambio;
import com.banguat.ws.tipocambio.TipoCambioSoap;
import com.banguat.ws.tipocambio.VarDolar;
import com.edutech.javaee.s07.e01.dao.ParametroSistemaDao;
import com.edutech.javaee.s07.e01.dto.ErrorMessageDto;
import java.net.URL;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author nahum
 */
@Path("tipo-de-cambio")
public class TipoDeCambioEndpoint {

    @Inject
    ParametroSistemaDao parametroDao;
    
    @GET
    @Produces({"application/json"})
    public Response tipoDeCambioBanguat() {
        List<VarDolar> cambioDolares = null;
        try {
            URL wsdlLocation = new URL( parametroDao.find(3).getValor() );
            TipoCambio tipoCambioService = new TipoCambio(wsdlLocation);
            
            TipoCambioSoap port = tipoCambioService.getTipoCambioSoap12();
            InfoVariable response = port.tipoCambioDia();
            cambioDolares = response.getCambioDolar().getVarDolar();
        } catch (Exception e) {
            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorMessageDto(false, 500, "Hubo un error al "
                            + " consultar el Banco de Guatemala"))
                    .build();
        }
        
        JsonObjectBuilder json = Json.createObjectBuilder();
        if (cambioDolares != null && !cambioDolares.isEmpty()) {
            json.add("fecha", cambioDolares.get(0).getFecha());
            json.add("referencia", cambioDolares.get(0).getReferencia());
        }
        //return Response.status(Response.Status.ACCEPTED).entity(json.build()).build();
        return Response.ok(json.build()).build();
    }
    
}
