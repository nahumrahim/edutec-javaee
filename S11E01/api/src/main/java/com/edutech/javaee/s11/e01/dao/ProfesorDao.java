package com.edutech.javaee.s11.e01.dao;

import com.edutech.javaee.s11.e01.model.Profesor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nahum
 */
@Stateless
public class ProfesorDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public List<Profesor> findAll() {
        return this.em
                .createQuery("SELECT r FROM Profesor r LEFT JOIN FETCH r.cursos c LEFT JOIN FETCH c.ciclo LEFT JOIN FETCH c.salon s LEFT JOIN FETCH s.sede", Profesor.class)
                // Para las implementaciones basadas en Eclipse Link, utilizar Hints
                .setHint("eclipselink.left-join-fetch", "r.cursos.ciclo")
                .setHint("eclipselink.left-join-fetch", "r.cursos.salon.sede")
                .getResultList();            
    }
    public List<Profesor> findAllUnNivel() {
        return this.em
                .createQuery("SELECT r FROM Profesor r LEFT JOIN FETCH r.cursos c", Profesor.class)
                .getResultList();
    }

    public Profesor find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT r FROM Profesor r WHERE r.id = :id", Profesor.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }  

}
