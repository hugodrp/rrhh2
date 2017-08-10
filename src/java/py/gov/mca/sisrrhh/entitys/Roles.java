/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.Basic
 *  javax.persistence.CascadeType
 *  javax.persistence.Column
 *  javax.persistence.Entity
 *  javax.persistence.Id
 *  javax.persistence.JoinColumn
 *  javax.persistence.ManyToOne
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.Funcionarios;

@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findByCodigo", query = "SELECT r FROM Roles r WHERE r.codigo = :codigo")
    , @NamedQuery(name = "Roles.findByNombre", query = "SELECT r FROM Roles r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "Roles.findByPaginaInicio", query = "SELECT r FROM Roles r WHERE r.paginaInicio = :paginaInicio")})
public class Roles
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
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 200)
    @Column(name = "pagina_inicio")
    private String paginaInicio;
    @JoinColumn(name = "estado_rol", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Estados estadoRol;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "rolUsuario")
    private List<Funcionarios> funcionariosList;

    public Roles() {
    }

    public Roles(String codigo) {
        this.codigo = codigo;
    }

    public Roles(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaginaInicio() {
        return this.paginaInicio;
    }

    public void setPaginaInicio(String paginaInicio) {
        this.paginaInicio = paginaInicio;
    }

    public Estados getEstadoRol() {
        return this.estadoRol;
    }

    public void setEstadoRol(Estados estadoRol) {
        this.estadoRol = estadoRol;
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
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if (this.codigo == null && other.codigo != null || this.codigo != null && !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.Roles[ codigo=" + this.codigo + " ]";
    }
}
