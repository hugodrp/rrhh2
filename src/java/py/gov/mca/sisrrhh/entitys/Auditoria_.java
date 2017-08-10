/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 */
package py.gov.mca.sisrrhh.entitys;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.Auditoria;

@StaticMetamodel(value = Auditoria.class)
public class Auditoria_ {

    public static volatile SingularAttribute<Auditoria, String> descripcion;
    public static volatile SingularAttribute<Auditoria, Date> fecha;
    public static volatile SingularAttribute<Auditoria, String> nombreTabla;
    public static volatile SingularAttribute<Auditoria, Integer> codigo;
    public static volatile SingularAttribute<Auditoria, String> aux3;
    public static volatile SingularAttribute<Auditoria, String> rolUsuario;
    public static volatile SingularAttribute<Auditoria, String> usuario;
    public static volatile SingularAttribute<Auditoria, String> tipoMovimiento;
    public static volatile SingularAttribute<Auditoria, String> claveTabla;
}
