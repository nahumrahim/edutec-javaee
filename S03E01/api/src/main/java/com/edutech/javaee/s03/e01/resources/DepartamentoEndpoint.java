package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dto.UsuarioDto;
import com.edutech.javaee.s03.e01.model.Rol;
import com.edutech.javaee.s03.e01.model.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.RollbackException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
@Path("/usuarios")
public class DepartamentoEndpoint {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    @GET
    @Produces({"application/json"})
    public List<Usuario> findAll() {
        List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u JOIN FETCH u.rol", Usuario.class)
                .getResultList();        
        return usuarios;
    }

}
