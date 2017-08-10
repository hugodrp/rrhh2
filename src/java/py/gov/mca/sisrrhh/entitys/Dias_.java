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
import py.gov.mca.sisrrhh.entitys.Dias;

@StaticMetamodel(value = Dias.class)
public class Dias_ {

    public static volatile SingularAttribute<Dias, String> descripcion;
    public static volatile SingularAttribute<Dias, String> codigo;
    public static volatile SingularAttribute<Dias, Integer> numeroDia;
}
