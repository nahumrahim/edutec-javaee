package com.edutech.javaee.s11.e01.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author nahum
 */
@Entity
@Table(name="DIVISION")
public class Division implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "divisionGen")
    @SequenceGenerator(name="divisionGen", sequenceName = "division_seq", initialValue = 10)
    private Integer id;
    
    private Integer dividendo;
    
    private Integer divisor;
    
    private String cociente;

    public Division() {
    }
    
    public Division(Integer dividendo, Integer divisor, String cociente) {
        this.dividendo = dividendo;
        this.divisor = divisor;
        this.cociente = cociente;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDividendo() {
        return dividendo;
    }

    public void setDividendo(Integer dividendo) {
        this.dividendo = dividendo;
    }

    public Integer getDivisor() {
        return divisor;
    }

    public void setDivisor(Integer divisor) {
        this.divisor = divisor;
    }

    public String getCociente() {
        return cociente;
    }

    public void setCociente(String cociente) {
        this.cociente = cociente;
    }    
    
}
