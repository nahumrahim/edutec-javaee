package com.edutech.javaee.s04.e01.dao;

import com.edutech.javaee.s04.e01.model.Departamento;
import com.edutech.javaee.s04.e01.model.Municipio;
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

    private void setChildren(Departamento entity) {
        entity.getMunicipios().stream().forEach( (Municipio municipio) -> {
            municipio.setDepartamento(entity);
        });    
    }
    
    public Departamento save(Departamento entity) {
        int conteo = this.em.createNamedQuery("Departamento.findByName", Long.class)
                .setParameter("nombre", entity.getNombre())
                .getSingleResult().intValue();
        if (conteo > 0)
            return null;
        
        this.setChildren(entity);
        
        this.em.persist(entity);
        return entity;
    }
    
    public Departamento edit(Departamento entity) {
        if (this.find(entity.getId()) != null) {
            this.setChildren(entity);
            this.em.merge(entity);
        }
        return entity;
    }

}
