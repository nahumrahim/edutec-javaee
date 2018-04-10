package com.edutech.javaee.s11.e01.resource;

import com.edutech.javaee.s11.e01.dao.GenericDao;
import com.edutech.javaee.s11.e01.model.Division;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author nahum
 */
@Path("transaction")
@Stateless
public class TransactionTestEndpoint {
    
    GenericDao<Division> dao;

    public TransactionTestEndpoint() {
    }

    @Inject
    public TransactionTestEndpoint(GenericDao<Division> dao) {
        this.dao = dao;
    }

    
    @GET
    @Produces("application/json")
    public List<Division> findAll() {
        return dao.findAll(Division.class);
    }
    
    @GET
    @Path("error/{code}") 
    public Response getErrorCode(@PathParam ("code") Integer code) {
        
        if (code >= 400)
            throw new WebApplicationException("Error Http: " + code, code);
        
        return Response.status(code).build();
    }
    
    
    // Demo Javascript
    private void javascriptDivision(String foo) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        System.out.println(engine.eval(foo));
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void javaDivision(Integer dividendo, Integer divisor) throws ScriptException {
        Division div = new Division(dividendo, divisor, String.valueOf(dividendo/divisor));
        dao.save(div);
        System.out.println("El cociente es: " + div.getCociente());
    }    
    
    @GET
    @Path("test") 
    public Response test() {
        List<String> divisiones = new ArrayList<>();
        divisiones.add("4/8");
        divisiones.add("9/3");
        divisiones.add("7/0");
        divisiones.add("19/5");
        divisiones.stream().forEach((String division) -> { 
            try {
                javascriptDivision(division);
                String parts[] = division.split("/");
                javaDivision(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            } catch (ScriptException ex) {
                System.out.println(" -> Hubo un error al ejecutar la division: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println(" -> Hubo un error al ejecutar la division: " + ex.getMessage());
            }
        });
        return Response.ok("Finalizado").build();
    }
}
