/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.Stateless
 *  javax.persistence.EntityExistsException
 *  javax.persistence.EntityManager
 *  javax.persistence.PersistenceContext
 *  javax.persistence.PersistenceException
 *  javax.persistence.Query
 *  py.gov.mca.sisrrhh.entitys.Auditoria
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
 */
package py.gov.mca.sisrrhh.sessionbeans;

import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import py.gov.mca.sisrrhh.entitys.Auditoria;
import py.gov.mca.sisrrhh.entitys.Funcionarios;

@Stateless
public class FuncionariosSB {

    @PersistenceContext(unitName = "sisrrhhPU")
    private EntityManager em;

    public Funcionarios findByUsuario(String usuario) {
        Query q = this.em.createNamedQuery("Funcionarios.findByUsuario");
        q.setParameter("usuario", (Object) usuario);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (Funcionarios) q.getResultList().get(0);
    }

    public Funcionarios findByNumeroDocumento(String numeroDocumento) {
        Query q = this.em.createNamedQuery("Funcionarios.findByNumeroDocumento");
        q.setParameter("numeroDocumento", (Object) numeroDocumento);
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return (Funcionarios) q.getResultList().get(0);
    }

    public List<Funcionarios> findAll() {
        Query q = this.em.createNamedQuery("Funcionarios.findAll");
        if (q.getResultList().isEmpty()) {
            return null;
        }
        return q.getResultList();
    }

    public List<Funcionarios> findAll2() {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e.numero_documento, ");
        jpql.append("e.nombres, ");
        jpql.append("e.apellidos, ");
        jpql.append("e.fecha_ingreso, ");
        jpql.append("e.estado_funcionario, ");
        jpql.append("e.relacion_laboral, ");
        jpql.append("e.sueldo_diario, ");
        jpql.append("e.horario_normal ");
        jpql.append("FROM funcionarios e ");
        jpql.append("ORDER BY e.numero_documento");
        Query q = this.em.createNativeQuery(jpql.toString(), Funcionarios.class);
        List funcionarios = q.getResultList();
        if (funcionarios.isEmpty()) {
            return null;
        }
        return funcionarios;
    }

    public List<Funcionarios> findByRangoCedula(String cedulaInicio, String cedulaFin) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM Funcionarios e ");
        jpql.append("WHERE e.numeroDocumento BETWEEN :cedulaInicio AND :cedulaFin ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("cedulaInicio", (Object) cedulaInicio);
        q.setParameter("cedulaFin", (Object) cedulaFin);
        return q.getResultList();
    }

    public List<Funcionarios> findByEstado(String estado) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM Funcionarios e ");
        jpql.append("WHERE e.estadoFuncionario.codigo = :estado ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("estado", (Object) estado);
        return q.getResultList();
    }

    public List<Funcionarios> findByEstadoRelacionLaboral(String estado, String relacionLaboral) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM Funcionarios e ");
        jpql.append("WHERE e.estadoFuncionario.codigo = :estado ");
        jpql.append("AND e.relacionLaboral.relacionLaboral = :relacionLaboral ");
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("estado", (Object) estado);
        return q.getResultList();
    }

    public List<Funcionarios> findByEstadoRelacionLaboralFiltro(String estado, String relacionLaboral) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT e ");
        jpql.append("FROM Funcionarios e ");
        jpql.append("WHERE e.estadoFuncionario.codigo = :estado ");
        if (relacionLaboral.equals("JOR")) {
            jpql.append("AND e.relacionLaboral.relacionLaboral = :relacionLaboral ");
        } else {
            relacionLaboral = "JOR";
            jpql.append("AND e.relacionLaboral.relacionLaboral != :relacionLaboral ");
        }
        Query q = this.em.createQuery(jpql.toString());
        q.setParameter("estado", (Object) estado);
        q.setParameter("relacionLaboral", (Object) relacionLaboral);
        return q.getResultList();
    }

    public void create(Funcionarios funcionario) {
        this.em.persist((Object) funcionario);
    }

    public String guardarFuncionarioMantenimiento(Funcionarios funcionario, String modo, String campos, String usuario, String rol) {
        return this.guardar(funcionario, modo, campos, usuario, rol);
    }

    public void update(Funcionarios funcionario) {
        this.em.merge((Object) funcionario);
    }

    private String guardar(Funcionarios funcionario, String modo, String campos, String usuario, String rol) {
        boolean ban = false;
        String resultado = "NO";
        try {
            if (modo.equals("Update")) {
                ((Funcionarios) this.em.merge((Object) funcionario)).getNumeroDocumento();
            } else {
                Funcionarios fu = (Funcionarios) this.em.find(Funcionarios.class, (Object) funcionario.getNumeroDocumento());
                if (fu == null) {
                    this.em.persist((Object) funcionario);
                } else {
                    ban = true;
                }
            }
            if (!ban) {
                String descriAudit = "Fecha: " + new Date() + ", " + modo + " funcionario: " + funcionario.getNumeroDocumento() + ", " + campos;
                this.guardarAuditoria(new Date(), usuario, rol, modo, "funcionarios", funcionario.getNumeroDocumento(), descriAudit);
                resultado = "OK";
            }
        } catch (EntityExistsException e) {
            System.out.println("EntityExistsException2: " + e.getMessage());
        } catch (PersistenceException e) {
            System.out.println("PersistenceException2: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception2: " + e.getMessage());
        }
        return resultado;
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
}
