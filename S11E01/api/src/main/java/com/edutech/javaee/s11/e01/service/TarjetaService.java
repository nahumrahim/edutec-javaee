package com.edutech.javaee.s11.e01.service;

import com.edutech.javaee.s11.e01.dto.PagoTarjetaDto;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nahum
 */
public class TarjetaService {

    String tokenRemoto;
    
    public boolean remoteLogin() {
        return true;
    }
    
    public List<String> getCardAuthorization(String idCard) {
        Client client = ClientBuilder.newClient();
        List<String> informacion = new ArrayList<>();
        try {
            informacion = client.target("http://localhost:8081/tarjeta/api/consultas/validate/" + idCard)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<String>>() {
                    });   
        }
        catch(Exception e) {
            informacion.add("Error de comunicacion");
        }
        return informacion;
   }
   
   public void pagarTarjeta() {
        Client client = ClientBuilder.newClient();
        PagoTarjetaDto inputDto = new PagoTarjetaDto();
        inputDto.setNumeroTarjeta("2394824912");
        
        PagoTarjetaDto outputDto;
        try {
            Response response = client.target("http://localhost:8081/tarjeta/api/consultas/pagar")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(inputDto, MediaType.APPLICATION_JSON));
            outputDto = response.readEntity(PagoTarjetaDto.class);
            System.out.println(outputDto.getSaldo());
        }
        catch(Exception e) {
        }
       
   }
    
}
