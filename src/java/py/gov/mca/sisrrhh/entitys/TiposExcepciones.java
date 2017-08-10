/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.Basic
 *  javax.persistence.CascadeType
 *  javax.persistence.Column
 *  javax.persistence.Entity
 *  javax.persistence.Id
 *  javax.persistence.NamedQueries
 *  javax.persistence.NamedQuery
 *  javax.persistence.OneToMany
 *  javax.persistence.Table
 *  javax.validation.constraints.NotNull
 *  javax.validation.constraints.Size
 */
package py.gov.mca.sisrrhh.entitys;

import java.io.Serializable;
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
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios;
import py.gov.mca.sisrrhh.entitys.FechasEspeciales;
import py.gov.mca.sisrrhh.entitys.FichaCalendario;

@Entity
@Table(name = "tipos_excepciones")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "TiposExcepciones.findAll", query = "SELECT t FROM TiposExcepciones t")
    , @NamedQuery(name = "TiposExcepciones.findByCodigoExcepcion", query = "SELECT t FROM TiposExcepciones t WHERE t.codigoExcepcion = :codigoExcepcion")
    , @NamedQuery(name = "TiposExcepciones.findByDescripcionExcepcion", query = "SELECT t FROM TiposExcepciones t WHERE t.descripcionExcepcion = :descripcionExcepcion")})
public class TiposExcepciones
        implements Serializable {

    private static final long serialVersionUID = 1;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo_excepcion")
    private String codigoExcepcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion_excepcion")
    private String descripcionExcepcion;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "tiposExcepciones")
    private List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosList;
    @OneToMany(mappedBy = "codigoExcepcion")
    private List<FichaCalendario> fichaCalendarioList;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "codigoExcepcion")
    private List<FechasEspeciales> fechasEspecialesList;

    public TiposExcepciones() {
    }

    public TiposExcepciones(String codigoExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
    }

    public TiposExcepciones(String codigoExcepcion, String descripcionExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
        this.descripcionExcepcion = descripcionExcepcion;
    }

    public String getCodigoExcepcion() {
        return this.codigoExcepcion;
    }

    public void setCodigoExcepcion(String codigoExcepcion) {
        this.codigoExcepcion = codigoExcepcion;
    }

    public String getDescripcionExcepcion() {
        return this.descripcionExcepcion;
    }

    public void setDescripcionExcepcion(String descripcionExcepcion) {
        this.descripcionExcepcion = descripcionExcepcion;
    }

    @XmlTransient
    public List<AsignacionExcepcionesHorariosDiarios> getAsignacionExcepcionesHorariosDiariosList() {
        return this.asignacionExcepcionesHorariosDiariosList;
    }

    public void setAsignacionExcepcionesHorariosDiariosList(List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosList) {
        this.asignacionExcepcionesHorariosDiariosList = asignacionExcepcionesHorariosDiariosList;
    }

    @XmlTransient
    public List<FichaCalendario> getFichaCalendarioList() {
        return this.fichaCalendarioList;
    }

    public void setFichaCalendarioList(List<FichaCalendario> fichaCalendarioList) {
        this.fichaCalendarioList = fichaCalendarioList;
    }

    @XmlTransient
    public List<FechasEspeciales> getFechasEspecialesList() {
        return this.fechasEspecialesList;
    }

    public void setFechasEspecialesList(List<FechasEspeciales> fechasEspecialesList) {
        this.fechasEspecialesList = fechasEspecialesList;
    }

    public int hashCode() {
        int hash = 0;
        return hash += this.codigoExcepcion != null ? this.codigoExcepcion.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof TiposExcepciones)) {
            return false;
        }
        TiposExcepciones other = (TiposExcepciones) object;
        if (this.codigoExcepcion == null && other.codigoExcepcion != null || this.codigoExcepcion != null && !this.codigoExcepcion.equals(other.codigoExcepcion)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.TiposExcepciones[ codigoExcepcion=" + this.codigoExcepcion + " ]";
    }
}
