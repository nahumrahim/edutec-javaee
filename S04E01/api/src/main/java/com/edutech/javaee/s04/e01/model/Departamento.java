package com.edutech.javaee.s04.e01.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author nahum
 */
    @Entity
@Table(name="DEPARTAMENTO")
@NamedQueries({
    // Distinct
    //@NamedQuery(name="Departamento.findAll", query="Select DISTINCT d from Departamento d LEFT JOIN FETCH d.municipios"),
    @NamedQuery(name="Departamento.findAll", query="Select d from Departamento d"),
    //JOIN FETCH
    @NamedQuery(name="Departamento.findById", query="Select d from Departamento d LEFT JOIN FETCH d.municipios WHERE d.id = :idDepartamento"),

    @NamedQuery(name="Departamento.findByName", query="Select count(d) from Departamento d WHERE d.nombre = :nombre")
})
public class Departamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamentoGen")
    @SequenceGenerator(name="departamentoGen", sequenceName = "depto_seq", initialValue = 10)
    private Integer id;
    
    private String codigo;
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento", fetch = FetchType.LAZY, orphanRemoval = true)
    List<Municipio> municipios;
    
    public Departamento() {
    }

    public Departamento(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
    
}
