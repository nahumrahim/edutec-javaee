package com.edutech.javaee.s04.e01.dao;

import com.edutech.javaee.s04.e01.dto.UsuarioDto;
import com.edutech.javaee.s04.e01.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nahum
 */
//@Stateless
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
    
    public Usuario edit(UsuarioDto entity) {
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
}
