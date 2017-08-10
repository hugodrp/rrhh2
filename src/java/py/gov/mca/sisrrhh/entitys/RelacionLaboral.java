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
import py.gov.mca.sisrrhh.entitys.Funcionarios;

@Entity
@Table(name = "relacion_laboral")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "RelacionLaboral.findAll", query = "SELECT r FROM RelacionLaboral r")
    , @NamedQuery(name = "RelacionLaboral.findByRelacionLaboral", query = "SELECT r FROM RelacionLaboral r WHERE r.relacionLaboral = :relacionLaboral")
    , @NamedQuery(name = "RelacionLaboral.findByDescripcion", query = "SELECT r FROM RelacionLaboral r WHERE r.descripcion = :descripcion")})
public class RelacionLaboral
        implements Serializable {

    private static final long serialVersionUID = 1;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "relacion_laboral")
    private String relacionLaboral;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "relacionLaboral")
    private List<Funcionarios> funcionariosList;

    public RelacionLaboral() {
    }

    public RelacionLaboral(String relacionLaboral) {
        this.relacionLaboral = relacionLaboral;
    }

    public RelacionLaboral(String relacionLaboral, String descripcion) {
        this.relacionLaboral = relacionLaboral;
        this.descripcion = descripcion;
    }

    public String getRelacionLaboral() {
        return this.relacionLaboral;
    }

    public void setRelacionLaboral(String relacionLaboral) {
        this.relacionLaboral = relacionLaboral;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Funcionarios> getFuncionariosList() {
        return this.funcionariosList;
    }

    public void setFuncionariosList(List<Funcionarios> funcionariosList) {
        this.funcionariosList = funcionariosList;
    }

    public int hashCode() {
        int hash = 0;
        return hash += this.relacionLaboral != null ? this.relacionLaboral.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof RelacionLaboral)) {
            return false;
        }
        RelacionLaboral other = (RelacionLaboral) object;
        if (this.relacionLaboral == null && other.relacionLaboral != null || this.relacionLaboral != null && !this.relacionLaboral.equals(other.relacionLaboral)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.RelacionLaboral[ relacionLaboral=" + this.relacionLaboral + " ]";
    }
}
