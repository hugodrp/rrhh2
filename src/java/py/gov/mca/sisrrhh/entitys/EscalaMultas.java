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
 *  javax.validation.constraints.NotNull
 *  javax.validation.constraints.Size
 */
package py.gov.mca.sisrrhh.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "escala_multas")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "EscalaMultas.findAll", query = "SELECT e FROM EscalaMultas e")
    , @NamedQuery(name = "EscalaMultas.findById", query = "SELECT e FROM EscalaMultas e WHERE e.id = :id")
    , @NamedQuery(name = "EscalaMultas.findByDescripcion", query = "SELECT e FROM EscalaMultas e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "EscalaMultas.findByCantidadMinutosDesde", query = "SELECT e FROM EscalaMultas e WHERE e.cantidadMinutosDesde = :cantidadMinutosDesde")
    , @NamedQuery(name = "EscalaMultas.findByCantidadMinutosHasta", query = "SELECT e FROM EscalaMultas e WHERE e.cantidadMinutosHasta = :cantidadMinutosHasta")
    , @NamedQuery(name = "EscalaMultas.findByPorcentajeMulta", query = "SELECT e FROM EscalaMultas e WHERE e.porcentajeMulta = :porcentajeMulta")
    , @NamedQuery(name = "EscalaMultas.findByTipoEscala", query = "SELECT e FROM EscalaMultas e WHERE e.tipoEscala = :tipoEscala")})
public class EscalaMultas
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
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_minutos_desde")
    private int cantidadMinutosDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_minutos_hasta")
    private int cantidadMinutosHasta;
    @Column(name = "porcentaje_multa")
    private Double porcentajeMulta;
    @Size(max = 20)
    @Column(name = "tipo_escala")
    private String tipoEscala;

    public EscalaMultas() {
    }

    public EscalaMultas(Integer id) {
        this.id = id;
    }

    public EscalaMultas(Integer id, String descripcion, int cantidadMinutosDesde, int cantidadMinutosHasta) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidadMinutosDesde = cantidadMinutosDesde;
        this.cantidadMinutosHasta = cantidadMinutosHasta;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadMinutosDesde() {
        return this.cantidadMinutosDesde;
    }

    public void setCantidadMinutosDesde(int cantidadMinutosDesde) {
        this.cantidadMinutosDesde = cantidadMinutosDesde;
    }

    public int getCantidadMinutosHasta() {
        return this.cantidadMinutosHasta;
    }

    public void setCantidadMinutosHasta(int cantidadMinutosHasta) {
        this.cantidadMinutosHasta = cantidadMinutosHasta;
    }

    public Double getPorcentajeMulta() {
        return this.porcentajeMulta;
    }

    public void setPorcentajeMulta(Double porcentajeMulta) {
        this.porcentajeMulta = porcentajeMulta;
    }

    public String getTipoEscala() {
        return this.tipoEscala;
    }

    public void setTipoEscala(String tipoEscala) {
        this.tipoEscala = tipoEscala;
    }

    public int hashCode() {
        int hash = 0;
        return hash += this.id != null ? this.id.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof EscalaMultas)) {
            return false;
        }
        EscalaMultas other = (EscalaMultas) object;
        if (this.id == null && other.id != null || this.id != null && !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.EscalaMultas[ id=" + this.id + " ]";
    }
}
