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
public class AsignacionExcepcionesHorariosDiariosPK
        implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo_excepcion")
    private String codigoExcepcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_asignacion")
    @Temporal(value = TemporalType.DATE)
    private Date fechaAsignacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_documento_funcionario")
    private String numeroDocumentoFuncionario;

    public AsignacionExcepcionesHorariosDiariosPK() {
    }

    public AsignacionExcepcionesHorariosDiariosPK(String codigoExcepcion, Date fechaAsignacion, String numeroDocumentoFuncionario) {
        this.codigoExcepcion = codigoExcepcion;
        this.fechaAsignacion = fechaAsignacion;
        this.numeroDocumentoFuncionario = numeroDocumentoFuncionario;
    }

    public String getCodigoExcepcion() {
        return this.codigoExcepcion;
    }

    public void setCodigoExcepcion(String codigoExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
    }

    public Date getFechaAsignacion() {
        return this.fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getNumeroDocumentoFuncionario() {
        return this.numeroDocumentoFuncionario;
    }

    public void setNumeroDocumentoFuncionario(String numeroDocumentoFuncionario) {
        this.numeroDocumentoFuncionario = numeroDocumentoFuncionario;
    }

    public int hashCode() {
        int hash = 0;
        hash += this.codigoExcepcion != null ? this.codigoExcepcion.hashCode() : 0;
        hash += this.fechaAsignacion != null ? this.fechaAsignacion.hashCode() : 0;
        return hash += this.numeroDocumentoFuncionario != null ? this.numeroDocumentoFuncionario.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof AsignacionExcepcionesHorariosDiariosPK)) {
            return false;
        }
        AsignacionExcepcionesHorariosDiariosPK other = (AsignacionExcepcionesHorariosDiariosPK) object;
        if (this.codigoExcepcion == null && other.codigoExcepcion != null || this.codigoExcepcion != null && !this.codigoExcepcion.equals(other.codigoExcepcion)) {
            return false;
        }
        if (this.fechaAsignacion == null && other.fechaAsignacion != null || this.fechaAsignacion != null && !this.fechaAsignacion.equals(other.fechaAsignacion)) {
            return false;
        }
        if (this.numeroDocumentoFuncionario == null && other.numeroDocumentoFuncionario != null || this.numeroDocumentoFuncionario != null && !this.numeroDocumentoFuncionario.equals(other.numeroDocumentoFuncionario)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK[ codigoExcepcion=" + this.codigoExcepcion + ", fechaAsignacion=" + this.fechaAsignacion + ", numeroDocumentoFuncionario=" + this.numeroDocumentoFuncionario + " ]";
    }
}
