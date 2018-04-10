package com.edutech.javaee.s11.e01.resource;

import com.edutech.javaee.s11.e01.dao.GenericDao;
import com.edutech.javaee.s11.e01.dao.RolDao;
import com.edutech.javaee.s11.e01.model.Rol;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author nahum
 */
@Stateless
@Path("/roles")
public class RolEndpoint {

    final RolDao rolDao;
    
    @Inject
    GenericDao<Rol> otroDao;

    public RolEndpoint() {
        this.rolDao = null;
        this.otroDao = null;
    }

    @Inject
    public RolEndpoint(RolDao rolDao) {
        this.rolDao = rolDao;
    }
    
    /*@Inject
    public RolEndpoint(RolDao rolDao, GenericDao<Rol> otroDao) {
        this.rolDao = rolDao;
        this.otroDao = otroDao;
    }*/
        
    @GET
    @Produces({"application/json"})
    public List<Rol> findAll() {
        Rol rol = new Rol(1, "Admin", "Administrador del sistema");
        otroDao.save(rol);
        return this.rolDao.findAll();
    }

}
