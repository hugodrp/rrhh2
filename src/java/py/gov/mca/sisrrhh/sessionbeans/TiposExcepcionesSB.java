/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.TiposExcepciones
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.io.PrintStream;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.TiposExcepciones;

@Stateless
public class TiposExcepcionesSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public TiposExcepciones findByCodigoExcepcion(String codigo) {
        Query q = this.em.createNamedQuery("TiposExcepciones.findByCodigoExcepcion");
        q.setParameter("codigoExcepcion", (Object) codigo);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (TiposExcepciones) q.getResultList().get(0);
    }

    public List<TiposExcepciones> findAll() {
        Query q = this.em.createNamedQuery("TiposExcepciones.findAll");
        return q.getResultList();
    }

    public void create(TiposExcepciones horario) {
        this.em.persist((Object) horario);
    }

    public void udtade(TiposExcepciones tipoExcepcion) {
        this.em.merge((Object) tipoExcepcion);
    }

    public String udtadeMultiple(List<TiposExcepciones> tiposExcepciones) {
        try {
            for (int i = 0; i < tiposExcepciones.size(); ++i) {
                TiposExcepciones tipoExcepcion = tiposExcepciones.get(i);
                System.out.println("ENTRO ANTES DE CLEAR " + i);
                this.em.merge((Object) tipoExcepcion);
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
