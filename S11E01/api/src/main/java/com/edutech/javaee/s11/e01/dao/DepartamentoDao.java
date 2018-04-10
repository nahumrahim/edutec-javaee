package com.edutech.javaee.s11.e01.dao;

import com.edutech.javaee.s11.e01.model.Departamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nahum
 */
public class DepartamentoDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Departamento find(Integer id) {
        try {
            return this.em
                    .createNamedQuery("Departamento.findById", Departamento.class)
                    .setParameter("idDepartamento", id)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }    
    public List<Departamento> findAll() {
        return this.em.createNamedQuery("Departamento.findAll").getResultList();
    }

}
