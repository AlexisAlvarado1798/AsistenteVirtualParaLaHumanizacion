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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AdminAlexisAlvarado
 */
@Entity
@Table(name = "TIPS", catalog = "", schema = "ADMINAV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tips.findAll", query = "SELECT t FROM Tips t"),
    @NamedQuery(name = "Tips.findByPkTips", query = "SELECT t FROM Tips t WHERE t.pkTips = :pkTips"),
    @NamedQuery(name = "Tips.findByCodigo", query = "SELECT t FROM Tips t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Tips.findByDescripcion", query = "SELECT t FROM Tips t WHERE t.descripcion = :descripcion")})
public class Tips implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TIPS", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkTips;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CODIGO", nullable = false, length = 100)
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTips")
    private List<Tipsporrol> tipsporrolList;
    @OneToMany(mappedBy = "fkTips")
    private List<Tips> tipsList;
    @JoinColumn(name = "FK_TIPS", referencedColumnName = "PK_TIPS")
    @ManyToOne
    private Tips fkTips;

    public Tips() {
    }

    public Tips(BigDecimal pkTips) {
        this.pkTips = pkTips;
    }

    public Tips(BigDecimal pkTips, String codigo, String descripcion) {
        this.pkTips = pkTips;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public BigDecimal getPkTips() {
        return pkTips;
    }

    public void setPkTips(BigDecimal pkTips) {
        this.pkTips = pkTips;
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

    @XmlTransient
    public List<Tips> getTipsList() {
        return tipsList;
    }

    public void setTipsList(List<Tips> tipsList) {
        this.tipsList = tipsList;
    }

    public Tips getFkTips() {
        return fkTips;
    }

    public void setFkTips(Tips fkTips) {
        this.fkTips = fkTips;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkTips != null ? pkTips.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tips)) {
            return false;
        }
        Tips other = (Tips) object;
        if ((this.pkTips == null && other.pkTips != null) || (this.pkTips != null && !this.pkTips.equals(other.pkTips))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "asistentevirtual.jpa.entidades.Tips[ pkTips=" + pkTips + " ]";
    }
    
}
