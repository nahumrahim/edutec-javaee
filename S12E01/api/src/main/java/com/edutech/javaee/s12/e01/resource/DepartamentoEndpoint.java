package com.edutech.javaee.s12.e01.resource;

import com.edutech.javaee.s12.e01.dao.DepartamentoDao;
import com.edutech.javaee.s12.e01.dto.ErrorMessageDto;
import com.edutech.javaee.s12.e01.model.Departamento;
import java.util.ArrayList;
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
@Path("/departamentos")
public class DepartamentoEndpoint {

    @Inject
    DepartamentoDao dao;

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Departamento departamento = this.dao.find(id);
        if (departamento == null)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        
        return Response.ok(departamento).build();    }
    
    @GET
    @Produces({"application/json"})
    public List<Departamento> findAll() {
        List<Departamento> deptosDto;
        deptosDto = new ArrayList<>();
        //deptosDto = this.dao.findAll();
        this.dao.findAll()
                .stream()
                .forEach((Departamento departamento) -> {
                    System.out.println( departamento.getNombre() );
                    deptosDto.add(
                        new Departamento (
                            departamento.getId(),
                            departamento.getCodigo(), 
                            departamento.getNombre()
                        )
                    ); 
                });
        return deptosDto;
    }

    
}
