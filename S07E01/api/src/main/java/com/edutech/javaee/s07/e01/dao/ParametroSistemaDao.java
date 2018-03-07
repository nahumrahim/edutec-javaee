package com.edutech.javaee.s07.e01.dao;

import com.edutech.javaee.s07.e01.model.ParametroSistema;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nahum
 */
public class ParametroSistemaDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public ParametroSistemaDao() {
    }

    public ParametroSistemaDao(EntityManager em) {
        this.em = em;
    }
    
    public ParametroSistema find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT p FROM ParametroSistema p WHERE p.id = :parametro", ParametroSistema.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }    
    
    public List<ParametroSistema> findAll() {
        return this.em
                .createQuery("SELECT p FROM ParametroSistema p", ParametroSistema.class)
                .getResultList();            
    }

    public ParametroSistema save(ParametroSistema entity) {
        this.em.persist(entity);
        return entity;
    }
    
    public ParametroSistema remove(Integer id) {
        ParametroSistema usuario = this.find(id);
        this.em.remove(usuario);
        return usuario;
    }

}
