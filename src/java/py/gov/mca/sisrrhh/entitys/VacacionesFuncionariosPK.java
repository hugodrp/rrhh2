/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.Basic
 *  javax.persistence.Column
 *  javax.persistence.Embeddable
 *  javax.validation.constraints.NotNull
 *  javax.validation.constraints.Size
 */
package py.gov.mca.sisrrhh.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class VacacionesFuncionariosPK
        implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "anio_vacacion")
    private int anioVacacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero_documento")
    private String numeroDocumento;

    public VacacionesFuncionariosPK() {
    }

    public VacacionesFuncionariosPK(int anioVacacion, String numeroDocumento) {
        this.anioVacacion = anioVacacion;
        this.numeroDocumento = numeroDocumento;
    }

    public int getAnioVacacion() {
        return this.anioVacacion;
    }

    public void setAnioVacacion(int anioVacacion) {
        this.anioVacacion = anioVacacion;
    }

    public String getNumeroDocumento() {
        return this.numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public int hashCode() {
        int hash = 0;
        hash += this.anioVacacion;
        return hash += this.numeroDocumento != null ? this.numeroDocumento.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof VacacionesFuncionariosPK)) {
            return false;
        }
        VacacionesFuncionariosPK other = (VacacionesFuncionariosPK) object;
        if (this.anioVacacion != other.anioVacacion) {
            return false;
        }
        if (this.numeroDocumento == null && other.numeroDocumento != null || this.numeroDocumento != null && !this.numeroDocumento.equals(other.numeroDocumento)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.VacacionesFuncionariosPK[ anioVacacion=" + this.anioVacacion + ", numeroDocumento=" + this.numeroDocumento + " ]";
    }
}
