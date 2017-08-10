/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.Estados
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.Estados;

@Stateless
public class EstadosSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public Estados findByCodigo(String codigo) {
        Query q = this.em.createNamedQuery("Estados.findByCodigo");
        q.setParameter("codigo", (Object) codigo);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (Estados) q.getResultList().get(0);
    }

    public List<Estados> findAll() {
        Query q = this.em.createNamedQuery("Estados.findAll");
        return q.getResultList();
    }

    public void create(Estados estado) {
        this.em.persist((Object) estado);
    }

    public void udtade(Estados estado) {
        this.em.merge((Object) estado);
    }

    public String udtadeMultiple(List<Estados> estados) {
        try {
            for (int i = 0; i < estados.size(); ++i) {
                Estados estado = estados.get(i);
                this.em.merge((Object) estado);
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
