package com.edutech.javaee.s07.e01.resources;

import com.edutech.javaee.s07.e01.dao.RolDao;
import com.edutech.javaee.s07.e01.model.Rol;
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

    public RolEndpoint() {
        this.rolDao = null;
    }

    @Inject
    public RolEndpoint(RolDao rolDao) {
        this.rolDao = rolDao;
    }
    
    @GET
    @Produces({"application/json"})
    public List<Rol> findAll() {
        return this.rolDao.findAll();
    }

}
