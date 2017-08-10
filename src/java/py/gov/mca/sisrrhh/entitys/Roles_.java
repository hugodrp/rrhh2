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
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.Roles;

@StaticMetamodel(value = Roles.class)
public class Roles_ {

    public static volatile SingularAttribute<Roles, String> codigo;
    public static volatile SingularAttribute<Roles, String> paginaInicio;
    public static volatile SingularAttribute<Roles, String> nombre;
    public static volatile SingularAttribute<Roles, Estados> estadoRol;
    public static volatile ListAttribute<Roles, Funcionarios> funcionariosList;
}
