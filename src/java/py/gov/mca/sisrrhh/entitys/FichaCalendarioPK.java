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
public class FichaCalendarioPK
        implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_calendario")
    @Temporal(value = TemporalType.DATE)
    private Date fechaCalendario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_documento_funcionario")
    private String numeroDocumentoFuncionario;

    public FichaCalendarioPK() {
    }

    public FichaCalendarioPK(Date fechaCalendario, String numeroDocumentoFuncionario) {
        this.fechaCalendario = fechaCalendario;
        this.numeroDocumentoFuncionario = numeroDocumentoFuncionario;
    }

    public Date getFechaCalendario() {
        return this.fechaCalendario;
    }

    public void setFechaCalendario(Date fechaCalendario) {
        this.fechaCalendario = fechaCalendario;
    }

    public String getNumeroDocumentoFuncionario() {
        return this.numeroDocumentoFuncionario;
    }

    public void setNumeroDocumentoFuncionario(String numeroDocumentoFuncionario) {
        this.numeroDocumentoFuncionario = numeroDocumentoFuncionario;
    }

    public int hashCode() {
        int hash = 0;
        hash += this.fechaCalendario != null ? this.fechaCalendario.hashCode() : 0;
        return hash += this.numeroDocumentoFuncionario != null ? this.numeroDocumentoFuncionario.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof FichaCalendarioPK)) {
            return false;
        }
        FichaCalendarioPK other = (FichaCalendarioPK) object;
        if (this.fechaCalendario == null && other.fechaCalendario != null || this.fechaCalendario != null && !this.fechaCalendario.equals(other.fechaCalendario)) {
            return false;
        }
        if (this.numeroDocumentoFuncionario == null && other.numeroDocumentoFuncionario != null || this.numeroDocumentoFuncionario != null && !this.numeroDocumentoFuncionario.equals(other.numeroDocumentoFuncionario)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.FichaCalendarioPK[ fechaCalendario=" + this.fechaCalendario + ", numeroDocumentoFuncionario=" + this.numeroDocumentoFuncionario + " ]";
    }
}
