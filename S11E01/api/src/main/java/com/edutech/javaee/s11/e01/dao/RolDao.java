package com.edutech.javaee.s11.e01.dao;

import com.edutech.javaee.s11.e01.model.Rol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nahum
 */
public class RolDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public List<Rol> findAll() {
        return this.em
                .createQuery("SELECT r FROM Rol r ", Rol.class)
                .getResultList();            
    }

    public Rol find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT r FROM Rol r WHERE r.id = :id", Rol.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }  
}
