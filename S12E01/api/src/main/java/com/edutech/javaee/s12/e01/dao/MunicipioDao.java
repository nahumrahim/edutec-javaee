package com.edutech.javaee.s12.e01.dao;

import com.edutech.javaee.s12.e01.model.Municipio;

/**
 *
 * @author nahum
 */
public class MunicipioDao extends GenericDao<Municipio> {

    public MunicipioDao() {
        super(Municipio.class);
    }
    
    @Override
    public Municipio save (Municipio entity) {
        // realizar validacion extra para municipio        
        this.em.persist(entity);
        return entity;
    }
    
}
