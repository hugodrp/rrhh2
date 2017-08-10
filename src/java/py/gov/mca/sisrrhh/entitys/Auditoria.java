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
 *  javax.persistence.Table
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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "auditoria")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "Auditoria.findAll", query = "SELECT a FROM Auditoria a")
    , @NamedQuery(name = "Auditoria.findByCodigo", query = "SELECT a FROM Auditoria a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "Auditoria.findByFecha", query = "SELECT a FROM Auditoria a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Auditoria.findByUsuario", query = "SELECT a FROM Auditoria a WHERE a.usuario = :usuario")
    , @NamedQuery(name = "Auditoria.findByRolUsuario", query = "SELECT a FROM Auditoria a WHERE a.rolUsuario = :rolUsuario")
    , @NamedQuery(name = "Auditoria.findByTipoMovimiento", query = "SELECT a FROM Auditoria a WHERE a.tipoMovimiento = :tipoMovimiento")
    , @NamedQuery(name = "Auditoria.findByDescripcion", query = "SELECT a FROM Auditoria a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Auditoria.findByNombreTabla", query = "SELECT a FROM Auditoria a WHERE a.nombreTabla = :nombreTabla")
    , @NamedQuery(name = "Auditoria.findByClaveTabla", query = "SELECT a FROM Auditoria a WHERE a.claveTabla = :claveTabla")
    , @NamedQuery(name = "Auditoria.findByAux3", query = "SELECT a FROM Auditoria a WHERE a.aux3 = :aux3")})
public class Auditoria
        implements Serializable {

    private static final long serialVersionUID = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rol_usuario")
    private String rolUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_tabla")
    private String nombreTabla;
    @Size(max = 45)
    @Column(name = "clave_tabla")
    private String claveTabla;
    @Size(max = 45)
    @Column(name = "aux_3")
    private String aux3;

    public Auditoria() {
    }

    public Auditoria(Integer codigo) {
        this.codigo = codigo;
    }

    public Auditoria(Integer codigo, Date fecha, String usuario, String rolUsuario, String tipoMovimiento, String descripcion, String nombreTabla) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.usuario = usuario;
        this.rolUsuario = rolUsuario;
        this.tipoMovimiento = tipoMovimiento;
        this.descripcion = descripcion;
        this.nombreTabla = nombreTabla;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRolUsuario() {
        return this.rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public String getTipoMovimiento() {
        return this.tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
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

    public String getClaveTabla() {
        return this.claveTabla;
    }

    public void setClaveTabla(String claveTabla) {
        this.claveTabla = claveTabla;
    }

    public String getAux3() {
        return this.aux3;
    }

    public void setAux3(String aux3) {
        this.aux3 = aux3;
    }

    public int hashCode() {
        int hash = 0;
        return hash += this.codigo != null ? this.codigo.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if (this.codigo == null && other.codigo != null || this.codigo != null && !this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.Auditoria[ codigo=" + this.codigo + " ]";
    }
}
