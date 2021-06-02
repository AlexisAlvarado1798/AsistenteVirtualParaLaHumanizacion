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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ESTADOCIVIL", catalog = "", schema = "ADMINAV", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadocivil.findAll", query = "SELECT e FROM Estadocivil e"),
    @NamedQuery(name = "Estadocivil.findByPkEstadocivil", query = "SELECT e FROM Estadocivil e WHERE e.pkEstadocivil = :pkEstadocivil"),
    @NamedQuery(name = "Estadocivil.findByCodigo", query = "SELECT e FROM Estadocivil e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "Estadocivil.findByDescripcion", query = "SELECT e FROM Estadocivil e WHERE e.descripcion = :descripcion")})
public class Estadocivil implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ESTADOCIVIL", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkEstadocivil;
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
    @OneToMany(mappedBy = "fkEstadocivil")
    private List<Persona> personaList;

    public Estadocivil() {
    }

    public Estadocivil(BigDecimal pkEstadocivil) {
        this.pkEstadocivil = pkEstadocivil;
    }

    public Estadocivil(BigDecimal pkEstadocivil, String codigo, String descripcion) {
        this.pkEstadocivil = pkEstadocivil;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public BigDecimal getPkEstadocivil() {
        return pkEstadocivil;
    }

    public void setPkEstadocivil(BigDecimal pkEstadocivil) {
        this.pkEstadocivil = pkEstadocivil;
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
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkEstadocivil != null ? pkEstadocivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadocivil)) {
            return false;
        }
        Estadocivil other = (Estadocivil) object;
        if ((this.pkEstadocivil == null && other.pkEstadocivil != null) || (this.pkEstadocivil != null && !this.pkEstadocivil.equals(other.pkEstadocivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
