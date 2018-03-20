package com.edutech.javaee.s09.e01.service;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author nahum
 */
public class RestClient {

   public List<String> getCardAuthorization(String idCard) {
        Client client = ClientBuilder.newClient();
        List<String> resourceList = new ArrayList<>();
        try {
            resourceList = client.target("http://localhost:8081/tarjeta/api/validate/" + idCard)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<String>>() {
                    });   
        }
        catch(Exception e) {
            resourceList.add("Error de comunicacion");
        }
        return resourceList;
   }
    
}
