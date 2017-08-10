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
@Table(name = "dias")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "Dias.findAll", query = "SELECT d FROM Dias d")
    , @NamedQuery(name = "Dias.findByCodigo", query = "SELECT d FROM Dias d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "Dias.findByDescripcion", query = "SELECT d FROM Dias d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "Dias.findByNumeroDia", query = "SELECT d FROM Dias d WHERE d.numeroDia = :numeroDia")})
public class Dias
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
    @Column(name = "numero_dia")
    private int numeroDia;

    public Dias() {
    }

    public Dias(String codigo) {
        this.codigo = codigo;
    }

    public Dias(String codigo, String descripcion, int numeroDia) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.numeroDia = numeroDia;
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

    public int getNumeroDia() {
        return this.numeroDia;
    }

    public void setNumeroDia(int numeroDia) {
        this.numeroDia = numeroDia;
    }

    public int hashCode() {
        int hash = 0;
        return hash += this.codigo != null ? this.codigo.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Dias)) {
            return false;
        }
        Dias other = (Dias) object;
        if (this.codigo == null && other.codigo != null || this.codigo != null && !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.Dias[ codigo=" + this.codigo + " ]";
    }
}
