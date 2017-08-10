/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios
 *  py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK;

@Stateless
public class AsignacionExcepcionesHorariosDiariosSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public List<AsignacionExcepcionesHorariosDiarios> findAll() {
        Query q = this.em.createNamedQuery("AsignacionExcepcionesHorariosDiarios.findAll");
        return q.getResultList();
    }

    public AsignacionExcepcionesHorariosDiarios findByFechaAsignacionNumeroDocumentoFuncionario(Date fechaAsignacion, String numeroDocumentoFuncionario, String codigoExcepcion, String estadoHorarioAsignado) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM AsignacionExcepcionesHorariosDiarios e ");
        jpql.append("WHERE e.asignacionExcepcionesHorariosDiariosPK.fechaAsignacion = :fechaAsignacion ");
        jpql.append("AND e.asignacionExcepcionesHorariosDiariosPK.numeroDocumentoFuncionario = :numeroDocumentoFuncionario ");
        jpql.append("AND e.asignacionExcepcionesHorariosDiariosPK.codigoExcepcion = :codigoExcepcion ");
        jpql.append("AND e.estadoHorarioAsignado.codigo = :estadoHorarioAsignado ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("fechaAsignacion", (Object) fechaAsignacion);
        q.setParameter("numeroDocumentoFuncionario", (Object) numeroDocumentoFuncionario);
        q.setParameter("codigoExcepcion", (Object) codigoExcepcion);
        q.setParameter("estadoHorarioAsignado", (Object) estadoHorarioAsignado);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (AsignacionExcepcionesHorariosDiarios) q.getResultList().get(0);
    }

    public List<AsignacionExcepcionesHorariosDiarios> findByCedula(String cedula) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM AsignacionExcepcionesHorariosDiarios e ");
        jpql.append("WHERE e.asignacionExcepcionesHorariosDiariosPK.numeroDocumentoFuncionario = :cedula ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("cedula", (Object) cedula);
        return q.getResultList();
    }

    public List<AsignacionExcepcionesHorariosDiarios> findByCedulaMesAnio(String cedula, Integer mes, Integer anio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM AsignacionExcepcionesHorariosDiarios e ");
        jpql.append("WHERE e.asignacionExcepcionesHorariosDiariosPK.numeroDocumentoFuncionario = :cedula ");
        jpql.append("AND e.mesAsignacion = :mes ");
        jpql.append("AND e.anioAsignacion = :anio ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("cedula", (Object) cedula);
        q.setParameter("mes", (Object) mes);
        q.setParameter("anio", (Object) anio);
        return q.getResultList();
    }

    public List<AsignacionExcepcionesHorariosDiarios> findByCedulaMesAnioTipo(String cedula, Integer mes, Integer anio, String tipo) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM AsignacionExcepcionesHorariosDiarios e ");
        jpql.append("WHERE e.asignacionExcepcionesHorariosDiariosPK.numeroDocumentoFuncionario = :cedula ");
        jpql.append("AND e.mesAsignacion = :mes ");
        jpql.append("AND e.anioAsignacion = :anio ");
        jpql.append("AND e.tiposExcepciones.codigoExcepcion = :tipo ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("cedula", (Object) cedula);
        q.setParameter("mes", (Object) mes);
        q.setParameter("anio", (Object) anio);
        q.setParameter("tipo", (Object) tipo);
        return q.getResultList();
    }

    public List<AsignacionExcepcionesHorariosDiarios> findByCedulaFechaDesdeFechaHasta(String cedula, Date fechaDesde, Date fechaHasta) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM AsignacionExcepcionesHorariosDiarios e ");
        jpql.append("WHERE e.asignacionExcepcionesHorariosDiariosPK.numeroDocumentoFuncionario = :cedula ");
        jpql.append("AND CAST( e.asignacionExcepcionesHorariosDiariosPK.fechaAsignacion AS DATE)  BETWEEN :paramFechaDesde AND :paramFechaHasta ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("cedula", (Object) cedula);
        q.setParameter("paramFechaDesde", (Object) fechaDesde);
        q.setParameter("paramFechaHasta", (Object) fechaHasta);
        return q.getResultList();
    }

    public void create(AsignacionExcepcionesHorariosDiarios asignacionExcepcionesHorariosDiarios) {
        this.em.persist((Object) asignacionExcepcionesHorariosDiarios);
    }

    public void delete(AsignacionExcepcionesHorariosDiarios asignacionExcepcionesHorariosDiarios) {
        AsignacionExcepcionesHorariosDiarios asignacionExcepcionesHorariosDiariosAux = (AsignacionExcepcionesHorariosDiarios) this.em.find(AsignacionExcepcionesHorariosDiarios.class, (Object) asignacionExcepcionesHorariosDiarios.getAsignacionExcepcionesHorariosDiariosPK());
        this.em.remove((Object) asignacionExcepcionesHorariosDiariosAux);
    }

    public void udtade(AsignacionExcepcionesHorariosDiarios asignacionExcepcionesHorariosDiarios) {
        this.em.merge((Object) asignacionExcepcionesHorariosDiarios);
    }

    public void flushAndClear() {
        this.em.flush();
        this.em.clear();
    }
}
