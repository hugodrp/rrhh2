/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.ListAttribute
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
 *  py.gov.mca.sisrrhh.entitys.Roles
 *  py.gov.mca.sisrrhh.entitys.VacacionesFuncionarios
 */
package py.gov.mca.sisrrhh.entitys;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.Roles;
import py.gov.mca.sisrrhh.entitys.VacacionesFuncionarios;

@StaticMetamodel(value = Estados.class)
public class Estados_ {

    public static volatile SingularAttribute<Estados, String> descripcion;
    public static volatile SingularAttribute<Estados, String> aux1;
    public static volatile ListAttribute<Estados, AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosList;
    public static volatile SingularAttribute<Estados, String> codigo;
    public static volatile SingularAttribute<Estados, String> aux2;
    public static volatile ListAttribute<Estados, Roles> rolesList;
    public static volatile ListAttribute<Estados, Funcionarios> funcionariosList;
    public static volatile ListAttribute<Estados, VacacionesFuncionarios> vacacionesFuncionariosList;
}
