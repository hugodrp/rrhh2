/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.Horarios
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.io.PrintStream;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.Horarios;

@Stateless
public class HorariosSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public Horarios findByHorario(String horario) {
        Query q = this.em.createNamedQuery("Horarios.findByHorario");
        q.setParameter("horario", (Object) horario);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (Horarios) q.getResultList().get(0);
    }

    public List<Horarios> findAll() {
        Query q = this.em.createNamedQuery("Horarios.findAll");
        return q.getResultList();
    }

    public void create(Horarios horario) {
        this.em.persist((Object) horario);
    }

    public void udtade(Horarios horario) {
        System.out.println("ENTRO " + horario.getHorario());
        this.em.merge((Object) horario);
    }

    public String udtadeMultiple(List<Horarios> horarios) {
        try {
            for (int i = 0; i < horarios.size(); ++i) {
                Horarios horario = horarios.get(i);
                System.out.println("ENTRO ANTES DE CLEAR " + i);
                this.em.merge((Object) horario);
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
