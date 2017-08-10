/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.Basic
 *  javax.persistence.Column
 *  javax.persistence.Entity
 *  javax.persistence.Id
 *  javax.persistence.NamedQueries
 *  javax.persistence.NamedQuery
 *  javax.persistence.Table
 *  javax.validation.constraints.NotNull
 *  javax.validation.constraints.Size
 */
package py.gov.mca.sisrrhh.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "meses")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "Meses.findAll", query = "SELECT m FROM Meses m")
    , @NamedQuery(name = "Meses.findByCodigo", query = "SELECT m FROM Meses m WHERE m.codigo = :codigo")
    , @NamedQuery(name = "Meses.findByDescripcion", query = "SELECT m FROM Meses m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Meses.findByNumeroMes", query = "SELECT m FROM Meses m WHERE m.numeroMes = :numeroMes")})
public class Meses
        implements Serializable {

    private static final long serialVersionUID = 1;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_mes")
    private int numeroMes;

    public Meses() {
    }

    public Meses(String codigo) {
        this.codigo = codigo;
    }

    public Meses(String codigo, String descripcion, int numeroMes) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.numeroMes = numeroMes;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroMes() {
        return this.numeroMes;
    }

    public void setNumeroMes(int numeroMes) {
        this.numeroMes = numeroMes;
    }

    public int hashCode() {
        int hash = 0;
        return hash += this.codigo != null ? this.codigo.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Meses)) {
            return false;
        }
        Meses other = (Meses) object;
        if (this.codigo == null && other.codigo != null || this.codigo != null && !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.Meses[ codigo=" + this.codigo + " ]";
    }
}
