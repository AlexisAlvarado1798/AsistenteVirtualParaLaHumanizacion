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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AdminAlexisAlvarado
 */
@Entity
@Table(name = "ROL", catalog = "", schema = "ADMINAV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByPkRol", query = "SELECT r FROM Rol r WHERE r.pkRol = :pkRol"),
    @NamedQuery(name = "Rol.findByCodigo", query = "SELECT r FROM Rol r WHERE r.codigo = :codigo"),
    @NamedQuery(name = "Rol.findByDescripcion", query = "SELECT r FROM Rol r WHERE r.descripcion = :descripcion")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ROL", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkRol")
    private List<Tipsporrol> tipsporrolList;

    public Rol() {
    }

    public Rol(BigDecimal pkRol) {
        this.pkRol = pkRol;
    }

    public Rol(BigDecimal pkRol, String codigo, String descripcion) {
        this.pkRol = pkRol;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public BigDecimal getPkRol() {
        return pkRol;
    }

    public void setPkRol(BigDecimal pkRol) {
        this.pkRol = pkRol;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Tipsporrol> getTipsporrolList() {
        return tipsporrolList;
    }

    public void setTipsporrolList(List<Tipsporrol> tipsporrolList) {
        this.tipsporrolList = tipsporrolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkRol != null ? pkRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.pkRol == null && other.pkRol != null) || (this.pkRol != null && !this.pkRol.equals(other.pkRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "asistentevirtual.jpa.entidades.Rol[ pkRol=" + pkRol + " ]";
    }
    
}
