 package com.edutech.javaee.s12.e01.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nahum
 */
@Entity
@Table(name="USUARIO")
@XmlRootElement
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioGen")
    @SequenceGenerator(name="usuarioGen", sequenceName = "usuario_seq", initialValue = 10)
    private Integer id;
    
    private String codigo;
    private String email;
    @Temporal(TemporalType.DATE) private Date fechaNacimiento;
    private String mimeType;
    private String nombre;
    private String nombreArchivo;
    private String password;
    @Lob private byte[] pic;
    private String telefono;
    @Transient private String fechaNacimientoConFormato;

    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol rol;

    public Usuario(Integer id, String codigo, String nombre, String telefono, String email) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = null;
    }

    public Usuario() {
    }

    public Usuario(String codigo, String email, String nombre, String telefono, Rol rol, Date fechaNacimiento) {
        this.codigo = codigo;
        this.email = email;
        this.nombre = nombre;
        this.password = null;
        this.telefono = telefono;
        this.rol = rol;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        this.fechaNacimientoConFormato = df.format(fechaNacimiento);
        System.out.println( this.fechaNacimientoConFormato );
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }    

    public String getFechaNacimientoConFormato() {
        return fechaNacimientoConFormato;
    }

    public void setFechaNacimientoConFormato(String fechaNacimientoConFormato) {
        this.fechaNacimientoConFormato = fechaNacimientoConFormato;
    }

    @XmlTransient
    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    
}
