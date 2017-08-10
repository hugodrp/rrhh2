/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 */
package py.gov.mca.sisrrhh.entitys;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.Meses;

@StaticMetamodel(value = Meses.class)
public class Meses_ {

    public static volatile SingularAttribute<Meses, String> descripcion;
    public static volatile SingularAttribute<Meses, String> codigo;
    public static volatile SingularAttribute<Meses, Integer> numeroMes;
}
