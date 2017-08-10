/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.Basic
 *  javax.persistence.Column
 *  javax.persistence.Entity
 *  javax.persistence.GeneratedValue
 *  javax.persistence.GenerationType
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ubicaciones_fisicas")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "UbicacionesFisicas.findAll", query = "SELECT u FROM UbicacionesFisicas u")
    , @NamedQuery(name = "UbicacionesFisicas.findByCodigo", query = "SELECT u FROM UbicacionesFisicas u WHERE u.codigo = :codigo")
    , @NamedQuery(name = "UbicacionesFisicas.findByNombre", query = "SELECT u FROM UbicacionesFisicas u WHERE u.nombre = :nombre")})
public class UbicacionesFisicas
        implements Serializable {

    private static final long serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "ubicacionesFisicasCodigo")
    private List<Funcionarios> funcionariosList;

    public UbicacionesFisicas() {
    }

    public UbicacionesFisicas(Integer codigo) {
        this.codigo = codigo;
    }

    public UbicacionesFisicas(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return hash += this.codigo != null ? this.codigo.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof UbicacionesFisicas)) {
            return false;
        }
        UbicacionesFisicas other = (UbicacionesFisicas) object;
        if (this.codigo == null && other.codigo != null || this.codigo != null && !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.UbicacionesFisicas[ codigo=" + this.codigo + " ]";
    }
}
