package com.edutech.javaee.s03.e02.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author nahum
 */
@Entity
@Table(name="TARJETA")
public class Tarjeta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String numero;
    private String descripcion;

    public Tarjeta() {
    }

    public Tarjeta(Integer id, String numero, String descripcion) {
        this.id = id;
        this.numero = numero;
        this.descripcion = descripcion;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
       
}
