/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.EscalaMultas
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.io.PrintStream;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.EscalaMultas;

@Stateless
public class EscalaMultasSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public EscalaMultas findByHorario(Integer id) {
        Query q = this.em.createNamedQuery("EscalaMultas.findById");
        q.setParameter("id", (Object) id);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (EscalaMultas) q.getResultList().get(0);
    }

    public List<EscalaMultas> findAll() {
        Query q = this.em.createNamedQuery("EscalaMultas.findAll");
        return q.getResultList();
    }

    public List<EscalaMultas> findByTipoEscala(String tipoEscala) {
        Query q = this.em.createNamedQuery("EscalaMultas.findByTipoEscala");
        q.setParameter("tipoEscala", (Object) tipoEscala);
        return q.getResultList();
    }

    public void create(EscalaMultas escalaMultas) {
        this.em.persist((Object) escalaMultas);
    }

    public void udtade(EscalaMultas escalaMultas) {
        this.em.merge((Object) escalaMultas);
    }

    public String udtadeMultiple(List<EscalaMultas> escalaMultas) {
        try {
            for (int i = 0; i < escalaMultas.size(); ++i) {
                EscalaMultas escalaMulta = escalaMultas.get(i);
                System.out.println("ENTRO ANTES DE CLEAR " + i);
                this.em.merge((Object) escalaMulta);
                if (i % 1000 == 0) {
                    this.flushAndClear();
                    System.out.println("ENTRO EN CLEAR " + i);
                }
                System.out.println("Cnt : " + (i + 1) + " - RESTO: " + i % 10000);
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
