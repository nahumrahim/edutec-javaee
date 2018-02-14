package com.edutech.javaee.s04.e01.resources;

import com.edutech.javaee.s04.e01.dao.DepartamentoDao;
import com.edutech.javaee.s04.e01.dao.RolDao;
import com.edutech.javaee.s04.e01.dao.UsuarioDao;
import com.edutech.javaee.s04.e01.dto.ErrorMessageDto;
import com.edutech.javaee.s04.e01.dto.UsuarioDto;
import com.edutech.javaee.s04.e01.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
public class UsuarioEndpoint {

    //@Inject
    final UsuarioDao usuarioDao;

    //@Inject
    final RolDao rolDao;

    public UsuarioEndpoint() {
        this.usuarioDao = null;
        this.rolDao = null;
    }

    @Inject
    public UsuarioEndpoint(UsuarioDao usuarioDao, RolDao rolDao) {
        this.usuarioDao = usuarioDao;
        this.rolDao = rolDao;
    }
    
    @GET
    @Produces({"application/json"})
    public List<Usuario> findAll() {
        return this.usuarioDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Usuario usuario = this.usuarioDao.find(id);
        
        if (usuario == null)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        
        return Response.ok(usuario, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(UsuarioDto dto) {
        Usuario usuario = new Usuario(
                dto.getCodigo(), 
                dto.getEmail(), 
                dto.getNombre(), 
                dto.getTelefono(),
                this.rolDao.find(dto.getIdRol())
            );
        this.usuarioDao.save(usuario);
        return Response.ok(usuario).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(UsuarioDto dto) throws RollbackException {
        Usuario usuario = this.usuarioDao.edit(dto);
        if (usuario == null)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        
        return Response.ok(usuario).build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Usuario usuario = this.usuarioDao.remove(id);

        if (usuario == null)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
            
        return Response.ok(usuario).build();
    }

}
