/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.Auditoria
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.Auditoria;

@Stateless
public class AuditoriaSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public List<Auditoria> findByCodigo(String codigo) {
        Query q = this.em.createNamedQuery("Auditoria.findByCodigo");
        q.setParameter("codigo", (Object) codigo);
        return q.getResultList();
    }

    public void create(Auditoria auditoria) {
        this.em.persist((Object) auditoria);
    }

    public void udtade(Auditoria auditoria) {
        this.em.merge((Object) auditoria);
    }

    public void flushAndClear() {
        this.em.flush();
        this.em.clear();
    }
}
