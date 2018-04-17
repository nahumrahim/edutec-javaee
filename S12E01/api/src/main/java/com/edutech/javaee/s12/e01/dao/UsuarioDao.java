package com.edutech.javaee.s12.e01.dao;

import com.edutech.javaee.s12.e01.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nahum
 */
public class UsuarioDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public UsuarioDao() {
    }

    public UsuarioDao(EntityManager em) {
        this.em = em;
    }
    
    public Usuario find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Usuario u JOIN FETCH u.rol WHERE u.id = :parametro", Usuario.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }    
    
    public List<Usuario> findAll() {
        return this.em
                .createQuery("SELECT u FROM Usuario u JOIN FETCH u.rol", Usuario.class)
                .getResultList();            
    }

    public Usuario save(Usuario entity) {
        this.em.persist(entity);
        return entity;
    }
    
    public Usuario edit(Usuario entity) {
        Usuario usuario = this.find(entity.getId());
        if (usuario != null) {
            usuario.setCodigo(entity.getCodigo());
            usuario.setEmail(entity.getEmail());
            usuario.setNombre(entity.getNombre());
            usuario.setPassword(entity.getPassword());
            usuario.setTelefono(entity.getTelefono());        
            this.em.merge(usuario);
        }
        return usuario;
    }
    
    public Usuario remove(Integer id) {
        Usuario usuario = this.find(id);
        this.em.remove(usuario);
        return usuario;
    }

    public Usuario findByNameAndPassword(String codigo, String password) {
        try {
            return this.em.createQuery("Select u from Usuario u WHERE u.codigo = :codigo and u.password = :password", Usuario.class)
                    .setParameter("codigo", codigo)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public Usuario findByName(String codigo) {
        try {
            return this.em.createQuery("Select u from Usuario u JOIN FETCH u.rol JOIN FETCH u.rol WHERE u.codigo = :codigo", Usuario.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}
