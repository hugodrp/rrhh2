/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.VacacionesFuncionarios
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.VacacionesFuncionarios;

@Stateless
public class VacacionesFuncionariosSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public List<VacacionesFuncionarios> findByNumeroDocumentoEstadoVacacion(String numeroDocumento, String estadoVacacion) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM VacacionesFuncionarios e ");
        jpql.append("WHERE e.vacacionesFuncionariosPK.numeroDocumento = :numeroDocumento ");
        jpql.append("AND e.estadoVacacion.codigo = :estadoVacacion ");
        jpql.append("ORDER BY e.vacacionesFuncionariosPK.anioVacacion DESC ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("numeroDocumento", (Object) numeroDocumento);
        q.setParameter("estadoVacacion", (Object) estadoVacacion);
        return q.getResultList();
    }

    public List<VacacionesFuncionarios> findByNumeroDocumentoEstadoVacacionConDiasRestantes(String numeroDocumento, String estadoVacacion) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM VacacionesFuncionarios e ");
        jpql.append("WHERE e.vacacionesFuncionariosPK.numeroDocumento = :numeroDocumento ");
        jpql.append("AND e.estadoVacacion.codigo = :estadoVacacion ");
        jpql.append("AND e.cantidadDiasRestante > 0 ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("numeroDocumento", (Object) numeroDocumento);
        q.setParameter("estadoVacacion", (Object) estadoVacacion);
        return q.getResultList();
    }

    public void create(VacacionesFuncionarios vacacionesFuncionarios) {
        this.em.persist((Object) vacacionesFuncionarios);
    }

    public void udtade(VacacionesFuncionarios vacacionesFuncionarios) {
        this.em.merge((Object) vacacionesFuncionarios);
    }

    public void flushAndClear() {
        this.em.flush();
        this.em.clear();
    }
}
