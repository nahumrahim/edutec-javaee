package com.edutech.javaee.s09.e01.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author nahum
 */
@Entity
@Table(name="ASIGNACION_ESTUDIANTE")
@IdClass(AsignacionEstudiantePK.class)
public class AsignacionEstudiante implements Serializable {
    
    @Id
    @JoinColumn(name = "ID_ESTUDIANTE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudiante;

    @Id
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Curso curso;

    private Float zona;
    
    @Column(name="EXAMEN_FINAL")
    private Float examenFinal;

    @Column(name="NOTA_FINAL")
    private Float notaFinal;
    
    public AsignacionEstudiante() {
    }

    public AsignacionEstudiante(Integer id, Float zona, Float examenFinal, Float notaFinal, Estudiante estudiante, Curso curso) {
        this.zona = zona;
        this.examenFinal = examenFinal;
        this.notaFinal = notaFinal;
        this.estudiante = estudiante;
        this.curso = curso;
    }

    public Float getZona() {
        return zona;
    }

    public void setZona(Float zona) {
        this.zona = zona;
    }

    public Float getExamenFinal() {
        return examenFinal;
    }

    public void setExamenFinal(Float examenFinal) {
        this.examenFinal = examenFinal;
    }

    public Float getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Float notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
