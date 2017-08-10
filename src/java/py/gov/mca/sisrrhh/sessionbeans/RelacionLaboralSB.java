/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.RelacionLaboral
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.RelacionLaboral;

@Stateless
public class RelacionLaboralSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public RelacionLaboral findByRelacionLaboral(String relacionLaboral) {
        Query q = this.em.createNamedQuery("RelacionLaboral.findByRelacionLaboral");
        q.setParameter("relacionLaboral", (Object) relacionLaboral);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (RelacionLaboral) q.getResultList().get(0);
    }

    public List<RelacionLaboral> findAll() {
        Query q = this.em.createNamedQuery("RelacionLaboral.findAll");
        return q.getResultList();
    }

    public void create(RelacionLaboral relacionLaboral) {
        this.em.persist((Object) relacionLaboral);
    }

    public void udtade(RelacionLaboral relacionLaboral) {
        this.em.merge((Object) relacionLaboral);
    }

    public String udtadeMultiple(List<RelacionLaboral> relacionLaborales) {
        try {
            for (int i = 0; i < relacionLaborales.size(); ++i) {
                RelacionLaboral relacionLaboral = relacionLaborales.get(i);
                this.em.merge((Object) relacionLaboral);
                if (i % 1000 != 0) {
                    continue;
                }
                this.flushAndClear();
            }
            this.flushAndClear();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            this.flushAndClear();
            return "FAIL";
        }
    }

    public void flushAndClear() {
        this.em.flush();
        this.em.clear();
    }
}
