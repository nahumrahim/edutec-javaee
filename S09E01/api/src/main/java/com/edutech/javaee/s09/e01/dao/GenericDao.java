package com.edutech.javaee.s09.e01.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nahum
 * @param <T>
 */
public class GenericDao<T> {
    
    @PersistenceContext(unitName = "primary")
    EntityManager em;
    
    public T save(T entity) {
        System.out.println("Voy creando el registro de tipo: " + entity.getClass().getSimpleName());
        System.out.println(entity.toString());
        System.out.println("El entity manager dice:");
        System.out.println(this.em.toString());
        return entity;
    }
}
