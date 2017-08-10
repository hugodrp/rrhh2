/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.Basic
 *  javax.persistence.Column
 *  javax.persistence.Embeddable
 *  javax.persistence.Temporal
 *  javax.persistence.TemporalType
 *  javax.validation.constraints.NotNull
 *  javax.validation.constraints.Size
 */
package py.gov.mca.sisrrhh.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class MarcacionPK
        implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_marcacion")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fechaHoraMarcacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipo_macacion")
    private String tipoMacacion;

    public MarcacionPK() {
    }

    public MarcacionPK(String cedula, Date fechaHoraMarcacion, String tipoMacacion) {
        this.cedula = cedula;
        this.fechaHoraMarcacion = fechaHoraMarcacion;
        this.tipoMacacion = tipoMacacion;
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaHoraMarcacion() {
        return this.fechaHoraMarcacion;
    }

    public void setFechaHoraMarcacion(Date fechaHoraMarcacion) {
        this.fechaHoraMarcacion = fechaHoraMarcacion;
    }

    public String getTipoMacacion() {
        return this.tipoMacacion;
    }

    public void setTipoMacacion(String tipoMacacion) {
        this.tipoMacacion = tipoMacacion;
    }

    public int hashCode() {
        int hash = 0;
        hash += this.cedula != null ? this.cedula.hashCode() : 0;
        hash += this.fechaHoraMarcacion != null ? this.fechaHoraMarcacion.hashCode() : 0;
        return hash += this.tipoMacacion != null ? this.tipoMacacion.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof MarcacionPK)) {
            return false;
        }
        MarcacionPK other = (MarcacionPK) object;
        if (this.cedula == null && other.cedula != null || this.cedula != null && !this.cedula.equals(other.cedula)) {
            return false;
        }
        if (this.fechaHoraMarcacion == null && other.fechaHoraMarcacion != null || this.fechaHoraMarcacion != null && !this.fechaHoraMarcacion.equals(other.fechaHoraMarcacion)) {
            return false;
        }
        if (this.tipoMacacion == null && other.tipoMacacion != null || this.tipoMacacion != null && !this.tipoMacacion.equals(other.tipoMacacion)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.MarcacionPK[ cedula=" + this.cedula + ", fechaHoraMarcacion=" + this.fechaHoraMarcacion + ", tipoMacacion=" + this.tipoMacacion + " ]";
    }
}
