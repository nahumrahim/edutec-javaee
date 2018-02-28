package com.edutech.javaee.s05.e01.resources;

import com.edutech.javaee.s05.e01.annotations.CacheControl;
import com.edutech.javaee.s05.e01.dao.RolDao;
import com.edutech.javaee.s05.e01.dao.UsuarioDao;
import com.edutech.javaee.s05.e01.dto.ErrorMessageDto;
import com.edutech.javaee.s05.e01.model.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
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

/**
 *
 * @author nahum
 */
@Stateless
@Path("/usuarios")
public class UsuarioEndpoint {

    final UsuarioDao usuarioDao;
    final RolDao rolDao;

    private static final String FILE_PATH = "c:\\home\\upload\\";

    public UsuarioEndpoint() {
        this.usuarioDao = null;
        this.rolDao = null;
    }

    @Inject
    public UsuarioEndpoint(UsuarioDao usuarioDao, RolDao rolDao) {
        this.usuarioDao = usuarioDao;
        this.rolDao = rolDao;
    }

    // save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
		String uploadedFileLocation) throws IOException {
        OutputStream out;
        int read;
        byte[] bytes = new byte[1024];
        out = new FileOutputStream(new File(uploadedFileLocation));
        while ((read = uploadedInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
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
        if (usuario == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }
        
        //SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy", Locale.CANADA_FRENCH);
        if (usuario.getFechaNacimiento() != null) {
            usuario.setFechaNacimientoConFormato( df.format(usuario.getFechaNacimiento()) );
            System.out.println( usuario.getFechaNacimientoConFormato() );
        }
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
                this.rolDao.find(entity.getRol().getId()),
                entity.getFechaNacimiento()
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
    public Response downloadFoto(@PathParam("idUsuario") Integer id )  {
        if (id == null)
            return Response.status(Response.Status.NOT_FOUND)
                    .entity( new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        
        Usuario usuario = usuarioDao.find(id);
        
        if (usuario == null || usuario.getMimeType() == null)
            return Response.status(Response.Status.NOT_FOUND)
                    .entity( new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
    
        File file = new File(FILE_PATH + usuario.getNombreArchivo());
        Response.ResponseBuilder rb = Response.ok( file );
        //rb.type(usuario.getMimeType());
        rb.header("Content-disposition", "inline; filename=" + usuario.getNombreArchivo());
        return rb.build();
    }

	@POST
	@Path("foto/{idUsuario}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFoto(@Context HttpServletRequest request, 
            @Context SecurityContext context, 
            @PathParam("idUsuario") Integer idUsuario) {
		try {
            Usuario usuario = this.usuarioDao.find(idUsuario);

            ServletFileUpload upload = new ServletFileUpload();
			FileItemIterator fileIterator = upload.getItemIterator(request);
			while (fileIterator.hasNext()) {
				FileItemStream item = fileIterator.next();

				if (item.getContentType() != null) {
                    usuario.setNombreArchivo(item.getName());
                    usuario.setMimeType(item.getContentType());
                    this.writeToFile(item.openStream(), FILE_PATH + item.getName());
                }
			}
            this.usuarioDao.edit(usuario);
		} catch (IOException | FileUploadException ex) {
			//return Response.serverError().build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorMessageDto(true, 500, "Hubo un error al cargar el archivo")).build();
		}
		
		return Response.ok(new ErrorMessageDto(true, 200, "Archivo subido con éxito")).build();
	}
    

}
