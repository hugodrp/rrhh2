/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.ListAttribute
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 */
package py.gov.mca.sisrrhh.entitys;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.RelacionLaboral;

@StaticMetamodel(value = RelacionLaboral.class)
public class RelacionLaboral_ {

    public static volatile SingularAttribute<RelacionLaboral, String> descripcion;
    public static volatile SingularAttribute<RelacionLaboral, String> relacionLaboral;
    public static volatile ListAttribute<RelacionLaboral, Funcionarios> funcionariosList;
}
