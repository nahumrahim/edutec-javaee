package com.edutech.javaee.s02.e01.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nahum
 */
@XmlRootElement
public class ParametroDto {
    
    private Long id;
    private String nombre;
    private String valor;

    public ParametroDto() {
    }

    public ParametroDto(Long id, String nombre, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
