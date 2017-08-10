/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.Marcacion
 *  py.gov.mca.sisrrhh.entitys.MarcacionPK
 *  py.gov.mca.sisrrhh.utiles.MsgUtil
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.Marcacion;
import py.gov.mca.sisrrhh.entitys.MarcacionPK;
import py.gov.mca.sisrrhh.utiles.MsgUtil;

@Stateless
public class MarcacionSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public List<Marcacion> findByCedula(String cedula) {
        Query q = this.em.createNamedQuery("Marcacion.findByCedula");
        q.setParameter("cedula", (Object) cedula);
        return q.getResultList();
    }

    public List<Marcacion> findByCedulaFechaDesdeFechaHasta(String cedula, Date fechaDesde, Date fechaHasta) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM Marcacion e ");
        jpql.append("WHERE e.marcacionPK.cedula = :cedula ");
        jpql.append("AND CAST(e.fechaMarcacionChar AS DATE)  BETWEEN :paramFechaDesde AND :paramFechaHasta ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("cedula", (Object) cedula);
        q.setParameter("paramFechaDesde", (Object) fechaDesde);
        q.setParameter("paramFechaHasta", (Object) fechaHasta);
        return q.getResultList();
    }

    public List<Marcacion> findAll() {
        Query q = this.em.createNamedQuery("Marcacion.findAll");
        return q.getResultList();
    }

    public void create(Marcacion marcacion) {
        this.em.persist((Object) marcacion);
    }

    public void udtade(Marcacion marcacion) {
        String err = "MARCACION " + marcacion.getMarcacionPK().getCedula() + " " + marcacion.getMarcacionPK().getTipoMacacion() + " " + marcacion.getMarcacionPK().getFechaHoraMarcacion();
        System.out.println(err);
        try {
            this.em.merge((Object) marcacion);
        } catch (Exception e) {
            Logger.getLogger(MarcacionSB.class.getName()).log(Level.SEVERE, null, e);
            MsgUtil.msg((String) "Error en transacci\u00f3n", (String) (err + " " + e.getMessage()), (String) "ERROR");
        }
    }

    public void insert(Marcacion marcacion) {
        String err = "MARCACION ";
        Marcacion mar = (Marcacion) this.em.find(Marcacion.class, (Object) marcacion.getMarcacionPK());
        if (mar == null) {
            err = err + "persist " + marcacion.getMarcacionPK().getCedula() + " " + marcacion.getMarcacionPK().getTipoMacacion() + " " + marcacion.getMarcacionPK().getFechaHoraMarcacion();
            this.em.persist((Object) marcacion);
        } else {
            err = err + "merge " + marcacion.getMarcacionPK().getCedula() + " " + marcacion.getMarcacionPK().getTipoMacacion() + " " + marcacion.getMarcacionPK().getFechaHoraMarcacion();
            this.em.merge((Object) marcacion);
        }
        System.out.println(err);
    }

    public String udtadeMultiple(List<Marcacion> marcaciones) {
        try {
            for (int i = 0; i < marcaciones.size(); ++i) {
                Marcacion marcacion = marcaciones.get(i);
                this.em.merge((Object) marcacion);
                if (i % 10000 == 0) {
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
