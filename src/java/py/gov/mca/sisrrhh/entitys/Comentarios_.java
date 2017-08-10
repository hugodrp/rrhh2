/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.ListAttribute
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
 */
package py.gov.mca.sisrrhh.entitys;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.Comentarios;
import py.gov.mca.sisrrhh.entitys.Funcionarios;

@StaticMetamodel(value = Comentarios.class)
public class Comentarios_ {

    public static volatile SingularAttribute<Comentarios, String> descripcion;
    public static volatile SingularAttribute<Comentarios, String> nombreTabla;
    public static volatile SingularAttribute<Comentarios, String> pkTabla;
    public static volatile SingularAttribute<Comentarios, String> movimiento;
    public static volatile SingularAttribute<Comentarios, Integer> id;
    public static volatile ListAttribute<Comentarios, Funcionarios> funcionariosList;
}
