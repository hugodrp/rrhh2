/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.Query
 *  org.apache.commons.lang.StringUtils
 *  py.gov.mca.sisrrhh.entitys.Auditoria
 *  py.gov.mca.sisrrhh.entitys.FichaCalendario
 *  py.gov.mca.sisrrhh.entitys.FichaCalendarioPK
 *  py.gov.mca.sisrrhh.entitys.Horarios
 *  py.gov.mca.sisrrhh.entitys.TiposExcepciones
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import py.gov.mca.sisrrhh.entitys.Auditoria;
import py.gov.mca.sisrrhh.entitys.FichaCalendario;
import py.gov.mca.sisrrhh.entitys.FichaCalendarioPK;
import py.gov.mca.sisrrhh.entitys.Horarios;
import py.gov.mca.sisrrhh.entitys.TiposExcepciones;

@Stateless
public class FichaCalendarioSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public List<FichaCalendario> findByCedulaMesAnio(String cedula, Integer mes, Integer anio) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM FichaCalendario e ");
        jpql.append("WHERE e.fichaCalendarioPK.numeroDocumentoFuncionario = :cedula ");
        jpql.append("AND e.mesCalendarioNumero = :mes ");
        jpql.append("AND e.anioCalendarioNumero = :anio ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("cedula", (Object) cedula);
        q.setParameter("mes", (Object) mes);
        q.setParameter("anio", (Object) anio);
        return q.getResultList();
    }

    public String findTest() {
        System.err.println("EEEEEEEEEEEEEEE");
        String retorno = "Test ";
        Query q = this.em.createNativeQuery("SELECT a.fecha_calendario, a.numero_documento_funcionario, a.dia_calendario_letra, a.dia_calendario_numero FROM ficha_calendario a", FichaCalendario.class);
        List fichaCalendarios = q.getResultList();
        for (FichaCalendario a : fichaCalendarios) {
            System.out.println("FichaCalendario " + a.getDiaCalendarioLetra() + " " + a.getDiaCalendarioNumero());
            retorno = "Test Retorno";
        }
        return retorno;
    }

    public List<FichaCalendario> findByCedulaFechaDesdeFechaHasta(String cedula, Date fechaDesde, Date fechaHasta) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM FichaCalendario e ");
        jpql.append("WHERE e.fichaCalendarioPK.numeroDocumentoFuncionario = :cedula ");
        jpql.append("AND CAST( e.fichaCalendarioPK.fechaCalendario AS DATE)  BETWEEN :paramFechaDesde AND :paramFechaHasta ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("cedula", (Object) cedula);
        q.setParameter("paramFechaDesde", (Object) fechaDesde);
        q.setParameter("paramFechaHasta", (Object) fechaHasta);
        return q.getResultList();
    }

    public List<FichaCalendario> findByCedulaFechaDesdeFechaHastaConNull(String cedula, Date fechaDesde, Date fechaHasta) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM FichaCalendario e ");
        jpql.append("WHERE e.fichaCalendarioPK.numeroDocumentoFuncionario = :cedula ");
        jpql.append("AND CAST( e.fichaCalendarioPK.fechaCalendario AS DATE)  BETWEEN :paramFechaDesde AND :paramFechaHasta ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("cedula", (Object) cedula);
        q.setParameter("paramFechaDesde", (Object) fechaDesde);
        q.setParameter("paramFechaHasta", (Object) fechaHasta);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return q.getResultList();
    }

    public FichaCalendario findByFechaCalendarioNumeroDocumentoFuncionario(Date fechaCalendario, String numeroDocumentoFuncionario) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM FichaCalendario e ");
        jpql.append("WHERE e.fichaCalendarioPK.fechaCalendario = :fechaCalendario ");
        jpql.append("AND e.fichaCalendarioPK.numeroDocumentoFuncionario = :numeroDocumentoFuncionario");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("fechaCalendario", (Object) fechaCalendario);
        q.setParameter("numeroDocumentoFuncionario", (Object) numeroDocumentoFuncionario);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (FichaCalendario) q.getResultList().get(0);
    }

    public List<FichaCalendario> findByFechaCalendarioNumeroDocumentoFuncionarioLista(List<FichaCalendario> lista) {
        ArrayList<FichaCalendario> listaAux = new ArrayList<FichaCalendario>();
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM FichaCalendario e ");
        jpql.append("WHERE e.fichaCalendarioPK.fechaCalendario = :fechaCalendario ");
        jpql.append("AND e.fichaCalendarioPK.numeroDocumentoFuncionario = :numeroDocumentoFuncionario");
        for (int i = 0; i < lista.size(); ++i) {
            Query q = this.em.createQuery(jpql.toString());
            q.setParameter("fechaCalendario", (Object) lista.get(i).getFichaCalendarioPK().getFechaCalendario());
            q.setParameter("numeroDocumentoFuncionario", (Object) lista.get(i).getFichaCalendarioPK().getNumeroDocumentoFuncionario());
            if (q.getResultList().isEmpty()) {
                System.out.println("NUEVA FICHA: " + lista.get(i).getFichaCalendarioPK().getNumeroDocumentoFuncionario());
                listaAux.add(lista.get(i));
                continue;
            }
            System.out.println("FICHA YA EXISTE: " + lista.get(i).getFichaCalendarioPK().getNumeroDocumentoFuncionario());
        }
        return listaAux;
    }

    public List<FichaCalendario> findAll() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM FichaCalendario e ");
        jpql.append("WHERE e.mesCalendarioNumero = 6 ");
        Query q = this.em.createQuery(jpql.toString());
        return q.getResultList();
    }

    public List<FichaCalendario> listar() {
        Query q = this.em.createNamedQuery("FichaCalendario.findAll");
        return q.getResultList();
    }

    public void create(FichaCalendario fichaCalendario) {
        this.em.persist((Object) fichaCalendario);
    }

    public void udtade(FichaCalendario fichaCalendario) {
        TiposExcepciones te = new TiposExcepciones();
        te.setCodigoExcepcion("VACI");
        if (fichaCalendario.getCodigoExcepcion() != null && !StringUtils.isBlank((String) fichaCalendario.getCodigoExcepcion().getCodigoExcepcion())) {
            te = (TiposExcepciones) this.em.getReference(TiposExcepciones.class, (Object) fichaCalendario.getCodigoExcepcion().getCodigoExcepcion());
        }
        fichaCalendario.setCodigoExcepcion(te);
        Horarios h = (Horarios) this.em.getReference(Horarios.class, (Object) fichaCalendario.getHorarioAsignado().getHorario());
        fichaCalendario.setHorarioAsignado(h);
        this.em.merge((Object) fichaCalendario);
        this.flushAndClear();
    }

    public String udtadeMultiple(List<FichaCalendario> fichasCalendarios) {
        try {
            for (int i = 0; i < fichasCalendarios.size(); ++i) {
                FichaCalendario fichaCalendario = fichasCalendarios.get(i);
                this.em.merge((Object) fichaCalendario);
                if (i % 10000 != 0) {
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

    private void guardarAuditoria(Date fechaAudit, String usuarioAudit, String rolAudit, String tipoMovimientoAudit, String nombreTablaAudit, String campoClaveAudit, String descriAudit) {
        Auditoria auditoria = new Auditoria();
        auditoria.setFecha(fechaAudit);
        auditoria.setUsuario(usuarioAudit);
        auditoria.setRolUsuario(rolAudit);
        auditoria.setTipoMovimiento(tipoMovimientoAudit);
        auditoria.setNombreTabla(nombreTablaAudit);
        auditoria.setClaveTabla(campoClaveAudit);
        auditoria.setDescripcion(descriAudit);
        this.em.persist((Object) auditoria);
        this.flushAndClear();
    }

    public void flushAndClear() {
        this.em.flush();
        this.em.clear();
    }

    private void imprimir(FichaCalendario fichaCalendario) {
        System.out.println("NumeroDocumentoFuncionario: " + fichaCalendario.getFichaCalendarioPK().getNumeroDocumentoFuncionario());
        System.out.println("FechaCalendario: " + fichaCalendario.getFichaCalendarioPK().getFechaCalendario());
        if (fichaCalendario.getCodigoExcepcion() != null) {
            System.out.println("CodigoExcepcion: " + fichaCalendario.getCodigoExcepcion().getCodigoExcepcion());
            System.out.println("DescripcionExcepcion: " + fichaCalendario.getCodigoExcepcion().getDescripcionExcepcion());
        }
        System.out.println("Horario Codigo: " + fichaCalendario.getHorarioAsignado().getHorario());
        System.out.println("Horario Descripcion: " + fichaCalendario.getHorarioAsignado().getDescripcion());
    }
}
