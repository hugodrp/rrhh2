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
import py.gov.mca.sisrrhh.entitys.UbicacionesFisicas;

@StaticMetamodel(value = UbicacionesFisicas.class)
public class UbicacionesFisicas_ {

    public static volatile SingularAttribute<UbicacionesFisicas, Integer> codigo;
    public static volatile SingularAttribute<UbicacionesFisicas, String> nombre;
    public static volatile ListAttribute<UbicacionesFisicas, Funcionarios> funcionariosList;
}
