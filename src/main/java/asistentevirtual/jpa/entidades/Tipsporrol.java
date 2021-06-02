/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AdminAlexisAlvarado
 */
@Entity
@Table(name = "TIPSPORROL", catalog = "", schema = "ADMINAV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipsporrol.findAll", query = "SELECT t FROM Tipsporrol t"),
    @NamedQuery(name = "Tipsporrol.findByPkTipsporrol", query = "SELECT t FROM Tipsporrol t WHERE t.pkTipsporrol = :pkTipsporrol")})
public class Tipsporrol implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TIPSPORROL", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkTipsporrol;
    @JoinColumn(name = "FK_ROL", referencedColumnName = "PK_ROL", nullable = false)
    @ManyToOne(optional = false)
    private Rol fkRol;
    @JoinColumn(name = "FK_TIPS", referencedColumnName = "PK_TIPS", nullable = false)
    @ManyToOne(optional = false)
    private Tips fkTips;

    public Tipsporrol() {
    }

    public Tipsporrol(BigDecimal pkTipsporrol) {
        this.pkTipsporrol = pkTipsporrol;
    }

    public BigDecimal getPkTipsporrol() {
        return pkTipsporrol;
    }

    public void setPkTipsporrol(BigDecimal pkTipsporrol) {
        this.pkTipsporrol = pkTipsporrol;
    }

    public Rol getFkRol() {
        return fkRol;
    }

    public void setFkRol(Rol fkRol) {
        this.fkRol = fkRol;
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
        hash += (pkTipsporrol != null ? pkTipsporrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipsporrol)) {
            return false;
        }
        Tipsporrol other = (Tipsporrol) object;
        if ((this.pkTipsporrol == null && other.pkTipsporrol != null) || (this.pkTipsporrol != null && !this.pkTipsporrol.equals(other.pkTipsporrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "asistentevirtual.jpa.entidades.Tipsporrol[ pkTipsporrol=" + pkTipsporrol + " ]";
    }
    
}
