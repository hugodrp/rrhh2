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
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
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
@Table(name = "comentarios")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "Comentarios.findAll", query = "SELECT c FROM Comentarios c")
    , @NamedQuery(name = "Comentarios.findById", query = "SELECT c FROM Comentarios c WHERE c.id = :id")
    , @NamedQuery(name = "Comentarios.findByMovimiento", query = "SELECT c FROM Comentarios c WHERE c.movimiento = :movimiento")
    , @NamedQuery(name = "Comentarios.findByDescripcion", query = "SELECT c FROM Comentarios c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Comentarios.findByNombreTabla", query = "SELECT c FROM Comentarios c WHERE c.nombreTabla = :nombreTabla")
    , @NamedQuery(name = "Comentarios.findByPkTabla", query = "SELECT c FROM Comentarios c WHERE c.pkTabla = :pkTabla")})
public class Comentarios
        implements Serializable {

    private static final long serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "movimiento")
    private String movimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_tabla")
    private String nombreTabla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pk_tabla")
    private String pkTabla;
    @OneToMany(mappedBy = "comentariosId")
    private List<Funcionarios> funcionariosList;

    public Comentarios() {
    }

    public Comentarios(Integer id) {
        this.id = id;
    }

    public Comentarios(Integer id, String movimiento, String descripcion, String nombreTabla, String pkTabla) {
        this.id = id;
        this.movimiento = movimiento;
        this.descripcion = descripcion;
        this.nombreTabla = nombreTabla;
        this.pkTabla = pkTabla;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovimiento() {
        return this.movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreTabla() {
        return this.nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getPkTabla() {
        return this.pkTabla;
    }

    public void setPkTabla(String pkTabla) {
        this.pkTabla = pkTabla;
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
        return hash += this.id != null ? this.id.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Comentarios)) {
            return false;
        }
        Comentarios other = (Comentarios) object;
        if (this.id == null && other.id != null || this.id != null && !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.Comentarios[ id=" + this.id + " ]";
    }
}
