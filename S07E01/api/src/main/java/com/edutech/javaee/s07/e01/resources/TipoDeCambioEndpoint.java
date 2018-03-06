package com.edutech.javaee.s07.e01.resources;

import com.banguat.ws.tipocambio.InfoVariable;
import com.banguat.ws.tipocambio.TipoCambio;
import com.banguat.ws.tipocambio.TipoCambioSoap;
import com.banguat.ws.tipocambio.VarDolar;
import com.edutech.javaee.s07.e01.dto.ErrorMessageDto;
import java.util.List;
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

    @GET
    @Produces({"application/json"})
    public Response tipoDeCambioBanguat() {
        List<VarDolar> cambioDolares = null;
        try {
            TipoCambio tp = new TipoCambio();
            TipoCambioSoap port = tp.getTipoCambioSoap12();
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
        return Response.ok(json.build()).build();
    }
    
}
