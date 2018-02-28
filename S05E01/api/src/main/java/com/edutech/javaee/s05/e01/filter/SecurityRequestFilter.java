package com.edutech.javaee.s05.e01.filter;

import com.edutech.javaee.s05.e01.dao.UsuarioDao;
import com.edutech.javaee.s05.e01.dto.ErrorMessageDto;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.status;

/**
 *
 * @author nahum
 */
@Provider
public class SecurityRequestFilter implements ContainerRequestFilter {
    
    @PersistenceContext(unitName = "primary")
    EntityManager em;

    @Inject
    UsuarioDao usuarioDao;
    
    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        System.out.println("JAXRS Filter dice: Estoy filtrando ;)");
        
        boolean debug = false;
        
        String[] nonSecurePaths = new String[] {
            "usuarios/login",
            "departamentos",
            "municipios"
        };
        
        System.out.println( em );
        System.out.println( usuarioDao );

        // Validar el token de seguridad
        
        // Responder con error cuando no se cumpla la validacion
        
        /*requestContext.abortWith(
                Response
                .status(401)
                .header("Content-Type", "application/json;charset=UTF-8")
                .entity(new ErrorMessageDto(
                    false,
                    401,
                    "No tiene autorizacion para realizar la peticion",
                    requestContext.getUriInfo().getPath()
                ))
                .build());*/  
    }

}
