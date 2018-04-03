package com.edutech.javaee.s09.e01.resource;

import com.edutech.javaee.s09.e01.dao.ProfesorDao;
import com.edutech.javaee.s09.e01.model.Curso;
import com.edutech.javaee.s09.e01.model.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 *
 * @author nahum
 */
@Stateless
@Path("/profesores")
public class ProfesorEndpoint {

    final ProfesorDao profesorDao;

    public ProfesorEndpoint() {
        this.profesorDao = null;
    }

    @Inject
    public ProfesorEndpoint(ProfesorDao profesorDao) {
        this.profesorDao = profesorDao;
    }
    
    @GET
    @Produces({"application/json"})
    public List<Profesor> findAll(@Context ContainerRequestContext rq) {
        //if (rq.getSecurityContext().isUserInRole("ADMIN"))
            return this.profesorDao.findAll();
        //else
        //    return new ArrayList<>();
    }

    @GET
    @Produces({"application/json"})
    @Path("fetch-un-nivel")
    public List<Profesor> fetchUnNivel() {
        List<Profesor> dtoList = new ArrayList<>();
        this.profesorDao.findAllUnNivel().stream().forEach((_profesor) -> {
            Profesor dto = new Profesor(
                _profesor.getId(),
                _profesor.getCarnet(),
                _profesor.getNombre(),
                _profesor.getDireccion()
            );
            dto.setCursos(new ArrayList<>());
            _profesor.getCursos().stream().forEach((_curso) -> {
               dto.getCursos().add(new Curso(
                   _curso.getId(),
                   _curso.getCodigo(),
                   _curso.getDireccion()
               ));
            });
            dtoList.add(dto);
        });
        return dtoList;
    }
}
