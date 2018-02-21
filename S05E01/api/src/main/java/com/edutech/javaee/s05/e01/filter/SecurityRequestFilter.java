package com.edutech.javaee.s05.e01.filter;

import com.edutech.javaee.s05.e01.dao.UsuarioDao;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    }

}
