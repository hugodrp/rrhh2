/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.Column
 *  javax.persistence.EmbeddedId
 *  javax.persistence.Entity
 *  javax.persistence.NamedQueries
 *  javax.persistence.NamedQuery
 *  javax.persistence.Table
 *  javax.persistence.Temporal
 *  javax.persistence.TemporalType
 *  javax.validation.constraints.Size
 *  py.gov.mca.sisrrhh.entitys.MarcacionPK
 */
package py.gov.mca.sisrrhh.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import py.gov.mca.sisrrhh.entitys.MarcacionPK;

@Entity
@Table(name = "marcacion")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "Marcacion.findAll", query = "SELECT m FROM Marcacion m")
    , @NamedQuery(name = "Marcacion.findByCedula", query = "SELECT m FROM Marcacion m WHERE m.marcacionPK.cedula = :cedula")
    , @NamedQuery(name = "Marcacion.findByFechaHoraMarcacion", query = "SELECT m FROM Marcacion m WHERE m.marcacionPK.fechaHoraMarcacion = :fechaHoraMarcacion")
    , @NamedQuery(name = "Marcacion.findByFechaMarcacionChar", query = "SELECT m FROM Marcacion m WHERE m.fechaMarcacionChar = :fechaMarcacionChar")
    , @NamedQuery(name = "Marcacion.findByHoraMarcacionChar", query = "SELECT m FROM Marcacion m WHERE m.horaMarcacionChar = :horaMarcacionChar")
    , @NamedQuery(name = "Marcacion.findByDiaMarcacion", query = "SELECT m FROM Marcacion m WHERE m.diaMarcacion = :diaMarcacion")
    , @NamedQuery(name = "Marcacion.findByTipoMacacion", query = "SELECT m FROM Marcacion m WHERE m.marcacionPK.tipoMacacion = :tipoMacacion")
    , @NamedQuery(name = "Marcacion.findByFormaMarcacion", query = "SELECT m FROM Marcacion m WHERE m.formaMarcacion = :formaMarcacion")
    , @NamedQuery(name = "Marcacion.findByNombreArchivo", query = "SELECT m FROM Marcacion m WHERE m.nombreArchivo = :nombreArchivo")})
public class Marcacion
        implements Serializable {

    private static final long serialVersionUID = 1;
    @EmbeddedId
    protected MarcacionPK marcacionPK;
    @Column(name = "fecha_marcacion_char")
    @Temporal(value = TemporalType.DATE)
    private Date fechaMarcacionChar;
    @Column(name = "hora_marcacion_char")
    @Temporal(value = TemporalType.TIME)
    private Date horaMarcacionChar;
    @Size(max = 20)
    @Column(name = "dia_marcacion")
    private String diaMarcacion;
    @Size(max = 20)
    @Column(name = "forma_marcacion")
    private String formaMarcacion;
    @Size(max = 45)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    public Marcacion() {
    }

    public Marcacion(MarcacionPK marcacionPK) {
        this.marcacionPK = marcacionPK;
    }

    public Marcacion(String cedula, Date fechaHoraMarcacion, String tipoMacacion) {
        this.marcacionPK = new MarcacionPK(cedula, fechaHoraMarcacion, tipoMacacion);
    }

    public MarcacionPK getMarcacionPK() {
        return this.marcacionPK;
    }

    public void setMarcacionPK(MarcacionPK marcacionPK) {
        this.marcacionPK = marcacionPK;
    }

    public Date getFechaMarcacionChar() {
        return this.fechaMarcacionChar;
    }

    public void setFechaMarcacionChar(Date fechaMarcacionChar) {
        this.fechaMarcacionChar = fechaMarcacionChar;
    }

    public Date getHoraMarcacionChar() {
        return this.horaMarcacionChar;
    }

    public void setHoraMarcacionChar(Date horaMarcacionChar) {
        this.horaMarcacionChar = horaMarcacionChar;
    }

    public String getDiaMarcacion() {
        return this.diaMarcacion;
    }

    public void setDiaMarcacion(String diaMarcacion) {
        this.diaMarcacion = diaMarcacion;
    }

    public String getFormaMarcacion() {
        return this.formaMarcacion;
    }

    public void setFormaMarcacion(String formaMarcacion) {
        this.formaMarcacion = formaMarcacion;
    }

    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int hashCode() {
        int hash = 0;
        return hash += this.marcacionPK != null ? this.marcacionPK.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Marcacion)) {
            return false;
        }
        Marcacion other = (Marcacion) object;
        if (this.marcacionPK == null && other.marcacionPK != null || this.marcacionPK != null && !this.marcacionPK.equals((Object) other.marcacionPK)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.Marcacion[ marcacionPK=" + (Object) this.marcacionPK + " ]";
    }
}
