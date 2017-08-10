/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.FechasEspeciales
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.FechasEspeciales;

@Stateless
public class FechasEspecialesSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public FechasEspeciales findByFechaEspecial(Date fechaEspecial) {
        Query q = this.em.createNamedQuery("FechasEspeciales.findByFechaEspecial");
        q.setParameter("fechaEspecial", (Object) fechaEspecial);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (FechasEspeciales) q.getResultList().get(0);
    }

    public void create(FechasEspeciales fechasEspeciales) {
        this.em.persist((Object) fechasEspeciales);
    }

    public void udtade(FechasEspeciales fechasEspeciales) {
        this.em.merge((Object) fechasEspeciales);
    }

    public void flushAndClear() {
        this.em.flush();
        this.em.clear();
    }
}
