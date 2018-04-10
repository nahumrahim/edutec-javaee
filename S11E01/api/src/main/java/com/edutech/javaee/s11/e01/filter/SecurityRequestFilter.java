package com.edutech.javaee.s11.e01.filter;

import com.edutech.javaee.s11.e01.dao.UsuarioDao;
import com.edutech.javaee.s11.e01.dto.ErrorMessageDto;
import com.edutech.javaee.s11.e01.model.Usuario;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author nahum
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityRequestFilter implements ContainerRequestFilter, ContainerResponseFilter {
    
    //@PersistenceContext(unitName = "primary")
    //EntityManager em;
 
    @Context
    UriInfo uriInfo;
    
    @Inject
    UsuarioDao usuarioDao;
    
    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        System.out.println("JAXRS Filter dice: Estoy filtrando ;)");
        
        boolean isPublicRequest = false;
        boolean debug = true;
        //requestContext.getMethod()
        String[] publicPaths = new String[] {
            "usuarios/login",
            "departamentos",
            "municipios"
        };
        for (String path: publicPaths) {
            if (uriInfo.getAbsolutePath().toString().contains(path)) {
                isPublicRequest = true;
                break;
            }
        }
        
        if (isPublicRequest || debug)
            return;
            
        // Validar el token de seguridad
        String token = requestContext.getHeaderString("Authorization");
        String userName = extractUserFromToken(token);
        if (userName != null) {
            final Usuario user = usuarioDao.findByName(userName);
            if (user != null) {
                // Validar expiracion del token
                final SecurityContext securityContext = requestContext.getSecurityContext();
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return user::getNombre;
                    }
                    @Override
                    public boolean isUserInRole(String role) {
                        return user.getRol().getNombre().equals(role);
                    }
                    @Override
                    public boolean isSecure() {
                        return uriInfo.getAbsolutePath().toString().startsWith("https");
                    }
                    @Override
                    public String getAuthenticationScheme() {
                        return "Token-Based-Auth-Scheme";
                    }
                });
                return;
            }
        }
        
        aunthenticateError(requestContext);
    }
    
    private String extractUserFromToken(String token) {
        if (token == null)
            return null;
        
        String authParts[] = token.split(" ");
        if (authParts == null || authParts.length < 2 || !authParts[0].equals("Bearer"))
            return null;
        
        String tokenParts[] = authParts[1].split(":");
        if (tokenParts == null || tokenParts.length < 2)
            return null;
        
        return tokenParts[0];
    }
    
    private void aunthenticateError(ContainerRequestContext requestContext) {
        requestContext.abortWith(
            Response
            .status(401)
            .header("Content-Type", "application/json;charset=UTF-8")
            .entity(new ErrorMessageDto(
                false,
                401,
                "No tiene autorizacion para realizar la peticion",
                requestContext.getUriInfo().getPath()
            ))
            .build());
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // Do nothing
    }

}
