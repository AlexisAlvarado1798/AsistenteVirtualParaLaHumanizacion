/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AdminAlexisAlvarado
 */
@Entity
@Table(name = "USUARIO", catalog = "", schema = "ADMINAV", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NOMBREUSUARIO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByPkUsuario", query = "SELECT u FROM Usuario u WHERE u.pkUsuario = :pkUsuario"),
    @NamedQuery(name = "Usuario.findByNombreusuario", query = "SELECT u FROM Usuario u WHERE u.nombreusuario = :nombreusuario and u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_USUARIO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBREUSUARIO", nullable = false, length = 200)
    private String nombreusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CONTRASENA", nullable = false, length = 200)
    private String contrasena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuario")
    private List<Estadousuario> estadousuarioList;
    @JoinColumn(name = "FK_PERSONA", referencedColumnName = "PK_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona fkPersona;
    @JoinColumn(name = "FK_ROL", referencedColumnName = "PK_ROL")
    @ManyToOne
    private Rol fkRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuario")
    private List<Accionpermitidaporusuario> accionpermitidaporusuarioList;

    public Usuario() {
    }

    public Usuario(BigDecimal pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    public Usuario(BigDecimal pkUsuario, String nombreusuario, String contrasena) {
        this.pkUsuario = pkUsuario;
        this.nombreusuario = nombreusuario;
        this.contrasena = contrasena;
    }

    public BigDecimal getPkUsuario() {
        return pkUsuario;
    }

    public void setPkUsuario(BigDecimal pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @XmlTransient
    public List<Estadousuario> getEstadousuarioList() {
        return estadousuarioList;
    }

    public void setEstadousuarioList(List<Estadousuario> estadousuarioList) {
        this.estadousuarioList = estadousuarioList;
    }

    public Persona getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(Persona fkPersona) {
        this.fkPersona = fkPersona;
    }

    public Rol getFkRol() {
        return fkRol;
    }

    public void setFkRol(Rol fkRol) {
        this.fkRol = fkRol;
    }

    @XmlTransient
    public List<Accionpermitidaporusuario> getAccionpermitidaporusuarioList() {
        return accionpermitidaporusuarioList;
    }

    public void setAccionpermitidaporusuarioList(List<Accionpermitidaporusuario> accionpermitidaporusuarioList) {
        this.accionpermitidaporusuarioList = accionpermitidaporusuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUsuario != null ? pkUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.pkUsuario == null && other.pkUsuario != null) || (this.pkUsuario != null && !this.pkUsuario.equals(other.pkUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "asistentevirtual.jpa.entidades.Usuario[ pkUsuario=" + pkUsuario + " ]";
    }
    
}
