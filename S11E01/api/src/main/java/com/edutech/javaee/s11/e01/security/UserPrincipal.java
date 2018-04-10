package com.edutech.javaee.s11.e01.security;

import com.edutech.javaee.s11.e01.model.Usuario;
import java.security.Principal;
import javax.security.auth.Subject;

/**
 *
 * @author nahum
 */
public class UserPrincipal implements Principal {
    Usuario usuario;
    
    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
