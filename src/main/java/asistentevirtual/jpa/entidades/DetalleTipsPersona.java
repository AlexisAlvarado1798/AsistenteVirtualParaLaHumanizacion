/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asistentevirtual.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AdminAlexisAlvarado
 */
@Entity
@Table(name = "DETALLE_TIPS_PERSONA", catalog = "", schema = "ADMINAV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleTipsPersona.findAll", query = "SELECT d FROM DetalleTipsPersona d"),
    @NamedQuery(name = "DetalleTipsPersona.findByPkDetalleTipsPersona", query = "SELECT d FROM DetalleTipsPersona d WHERE d.pkDetalleTipsPersona = :pkDetalleTipsPersona"),
    @NamedQuery(name = "DetalleTipsPersona.findByFecha", query = "SELECT d FROM DetalleTipsPersona d WHERE d.fecha = :fecha")})
public class DetalleTipsPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_DETALLE_TIPS_PERSONA", nullable = false, precision = 0, scale = -127)
    private BigDecimal pkDetalleTipsPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "FK_PERSONA", referencedColumnName = "PK_PERSONA")
    @ManyToOne
    private Persona fkPersona;
    @JoinColumn(name = "FK_TIPS", referencedColumnName = "PK_TIPS")
    @ManyToOne
    private Tips fkTips;

    public DetalleTipsPersona() {
    }

    public DetalleTipsPersona(BigDecimal pkDetalleTipsPersona) {
        this.pkDetalleTipsPersona = pkDetalleTipsPersona;
    }

    public DetalleTipsPersona(BigDecimal pkDetalleTipsPersona, Date fecha) {
        this.pkDetalleTipsPersona = pkDetalleTipsPersona;
        this.fecha = fecha;
    }

    public BigDecimal getPkDetalleTipsPersona() {
        return pkDetalleTipsPersona;
    }

    public void setPkDetalleTipsPersona(BigDecimal pkDetalleTipsPersona) {
        this.pkDetalleTipsPersona = pkDetalleTipsPersona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Persona getFkPersona() {
        return fkPersona;
    }

    public void setFkPersona(Persona fkPersona) {
        this.fkPersona = fkPersona;
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
        hash += (pkDetalleTipsPersona != null ? pkDetalleTipsPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleTipsPersona)) {
            return false;
        }
        DetalleTipsPersona other = (DetalleTipsPersona) object;
        if ((this.pkDetalleTipsPersona == null && other.pkDetalleTipsPersona != null) || (this.pkDetalleTipsPersona != null && !this.pkDetalleTipsPersona.equals(other.pkDetalleTipsPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "asistentevirtual.jpa.entidades.DetalleTipsPersona[ pkDetalleTipsPersona=" + pkDetalleTipsPersona + " ]";
    }
    
}
