package com.edutech.javaee.s05.e01.resources;

import com.edutech.javaee.s05.e01.annotations.CacheControl;
import com.edutech.javaee.s05.e01.dao.RolDao;
import com.edutech.javaee.s05.e01.dao.UsuarioDao;
import com.edutech.javaee.s05.e01.dto.ErrorMessageDto;
import com.edutech.javaee.s05.e01.dto.UsuarioDto;
import com.edutech.javaee.s05.e01.model.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.RollbackException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

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
    public Response create(Usuario entity) {
        Usuario usuario = new Usuario(
                entity.getCodigo(), 
                entity.getEmail(), 
                entity.getNombre(), 
                entity.getTelefono(),
                this.rolDao.find(entity.getRol().getId())
            );
        this.usuarioDao.save(usuario);
        return Response.ok(usuario).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(Usuario entity) throws RollbackException {
        Usuario usuario = this.usuarioDao.edit(entity);
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
    
    @GET
    @Path("foto/{idUsuario}")
    @CacheControl("max-age=3600")
    public Response downloadFoto(@PathParam("idUsuario") Integer id ) throws IOException {
        if (id == null)
            return Response.status(Response.Status.NOT_FOUND)
                    .entity( new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        
        Usuario usuario = usuarioDao.find(id);
        
        if (usuario == null)
            return Response.status(Response.Status.NOT_FOUND)
                    .entity( new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
    
        byte[] contenido = new byte[10];
        String mimeType = "";
        
        return Response.ok(contenido, mimeType).build();
    }

	@POST
	@Path("/upload/{idUsuario}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@Context HttpServletRequest request, 
            @Context SecurityContext context, 
            @PathParam("idUsuario") Integer idUsuario) {
		try {
			ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator fileIterator = upload.getItemIterator(request);

            Usuario usuario = this.usuarioDao.find(idUsuario);
			while (fileIterator.hasNext()) {
				FileItemStream item = fileIterator.next();

				usuario.setNombreArchivo(item.getName());
				usuario.setMimeType(item.getContentType());

				InputStream uploadedInputStream = item.openStream();
				byte[] fileData = IOUtils.toByteArray(uploadedInputStream);

				/*try {
					dto = this.fileController.upload(dto, fileData);
					dtos.add(dto);
				} catch (SaveException ex) {
					return Response.serverError().build();
				}*/
			}
		} catch (IOException | FileUploadException ex) {
			return Response.serverError().build();
		}
		
		return Response.ok().build();
	}
    

}
