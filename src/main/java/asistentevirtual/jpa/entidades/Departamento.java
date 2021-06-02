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
@Table(name = "DEPARTAMENTO", catalog = "", schema = "ADMINAV", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODIGO"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
    @NamedQuery(name = "Departamento.findByPkDepartamento", query = "SELECT d FROM Departamento d WHERE d.pkDepartamento = :pkDepartamento"),
    @NamedQuery(name = "Departamento.findByCodigo", query = "SELECT d FROM Departamento d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "Departamento.findByNombre", query = "SELECT d FROM Departamento d WHERE d.nombre = :nombre")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_DEPARTAMENTO", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkDepartamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CODIGO", nullable = false, length = 20)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @OneToMany(mappedBy = "fkDepartamento")
    private List<Municipio> municipioList;
    @JoinColumn(name = "FK_PAIS", referencedColumnName = "PK_PAIS", nullable = false)
    @ManyToOne(optional = false)
    private Pais fkPais;

    public Departamento() {
    }

    public Departamento(BigDecimal pkDepartamento) {
        this.pkDepartamento = pkDepartamento;
    }

    public Departamento(BigDecimal pkDepartamento, String codigo, String nombre) {
        this.pkDepartamento = pkDepartamento;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public BigDecimal getPkDepartamento() {
        return pkDepartamento;
    }

    public void setPkDepartamento(BigDecimal pkDepartamento) {
        this.pkDepartamento = pkDepartamento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    public Pais getFkPais() {
        return fkPais;
    }

    public void setFkPais(Pais fkPais) {
        this.fkPais = fkPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkDepartamento != null ? pkDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.pkDepartamento == null && other.pkDepartamento != null) || (this.pkDepartamento != null && !this.pkDepartamento.equals(other.pkDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + "-" + nombre;
    }
    
}
