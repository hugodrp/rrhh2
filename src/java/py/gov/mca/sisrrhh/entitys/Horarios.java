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
import py.gov.mca.sisrrhh.entitys.FichaCalendario;
import py.gov.mca.sisrrhh.entitys.Funcionarios;

@Entity
@Table(name = "horarios")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "Horarios.findAll", query = "SELECT h FROM Horarios h")
    , @NamedQuery(name = "Horarios.findByHorario", query = "SELECT h FROM Horarios h WHERE h.horario = :horario")
    , @NamedQuery(name = "Horarios.findByHoraEntrada", query = "SELECT h FROM Horarios h WHERE h.horaEntrada = :horaEntrada")
    , @NamedQuery(name = "Horarios.findByHoraSalida", query = "SELECT h FROM Horarios h WHERE h.horaSalida = :horaSalida")
    , @NamedQuery(name = "Horarios.findByDescripcion", query = "SELECT h FROM Horarios h WHERE h.descripcion = :descripcion")
    , @NamedQuery(name = "Horarios.findByCantidadHoras", query = "SELECT h FROM Horarios h WHERE h.cantidadHoras = :cantidadHoras")
    , @NamedQuery(name = "Horarios.findByCantidadMinutos", query = "SELECT h FROM Horarios h WHERE h.cantidadMinutos = :cantidadMinutos")})
public class Horarios
        implements Serializable {

    private static final long serialVersionUID = 1;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "horario")
    private String horario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "hora_entrada")
    private String horaEntrada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "hora_salida")
    private String horaSalida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad_horas")
    private Integer cantidadHoras;
    @Column(name = "cantidad_minutos")
    private Integer cantidadMinutos;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "horarioAsignado")
    private List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosList;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "horarioNormal")
    private List<Funcionarios> funcionariosList;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "horarioAsignado")
    private List<FichaCalendario> fichaCalendarioList;

    public Horarios() {
    }

    public Horarios(String horario) {
        this.horario = horario;
    }

    public Horarios(String horario, String horaEntrada, String horaSalida, String descripcion) {
        this.horario = horario;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHoraEntrada() {
        return this.horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return this.horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadHoras() {
        return this.cantidadHoras;
    }

    public void setCantidadHoras(Integer cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public Integer getCantidadMinutos() {
        return this.cantidadMinutos;
    }

    public void setCantidadMinutos(Integer cantidadMinutos) {
        this.cantidadMinutos = cantidadMinutos;
    }

    @XmlTransient
    public List<AsignacionExcepcionesHorariosDiarios> getAsignacionExcepcionesHorariosDiariosList() {
        return this.asignacionExcepcionesHorariosDiariosList;
    }

    public void setAsignacionExcepcionesHorariosDiariosList(List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosList) {
        this.asignacionExcepcionesHorariosDiariosList = asignacionExcepcionesHorariosDiariosList;
    }

    @XmlTransient
    public List<Funcionarios> getFuncionariosList() {
        return this.funcionariosList;
    }

    public void setFuncionariosList(List<Funcionarios> funcionariosList) {
        this.funcionariosList = funcionariosList;
    }

    @XmlTransient
    public List<FichaCalendario> getFichaCalendarioList() {
        return this.fichaCalendarioList;
    }

    public void setFichaCalendarioList(List<FichaCalendario> fichaCalendarioList) {
        this.fichaCalendarioList = fichaCalendarioList;
    }

    public int hashCode() {
        int hash = 0;
        return hash += this.horario != null ? this.horario.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Horarios)) {
            return false;
        }
        Horarios other = (Horarios) object;
        if (this.horario == null && other.horario != null || this.horario != null && !this.horario.equals(other.horario)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "py.gov.mca.sisrrhh.entitys.Horarios[ horario=" + this.horario + " ]";
    }
}
