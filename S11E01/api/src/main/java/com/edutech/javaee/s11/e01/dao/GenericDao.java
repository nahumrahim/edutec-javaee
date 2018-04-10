package com.edutech.javaee.s11.e01.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author nahum
 * @param <T>
 */
public class GenericDao<T> {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    private Class<T> entityClass;

    public GenericDao() {
        System.out.println("Constructor vacio");
        entityClass = null;

        //this.entityClass = (Class) ((ParameterizedType) getClass()).getActualTypeArguments()[0];     
        System.out.println("La clase del dao es: ");
        System.out.println( getClass() );
    }

    public GenericDao(Class<T> entityClass) {
        System.out.println("Constructor con parametro");
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        System.out.println("Voy creando el registro de tipo: " + entity.getClass().getSimpleName());
        //System.out.println(entity.toString());
        //System.out.println("El entity manager dice:" + this.em.toString());
        this.em.persist(entity);
        return entity;
    }

    public List<T> findAll(Class<T> entityClass) {
        this.entityClass = entityClass;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> rootEntry = cq.from(this.entityClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        return em.createQuery(all).getResultList();
    }

}
