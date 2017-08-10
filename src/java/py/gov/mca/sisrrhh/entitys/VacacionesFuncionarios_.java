/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 *  py.gov.mca.sisrrhh.entitys.VacacionesFuncionariosPK
 */
package py.gov.mca.sisrrhh.entitys;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.VacacionesFuncionarios;
import py.gov.mca.sisrrhh.entitys.VacacionesFuncionariosPK;

@StaticMetamodel(value = VacacionesFuncionarios.class)
public class VacacionesFuncionarios_ {

    public static volatile SingularAttribute<VacacionesFuncionarios, VacacionesFuncionariosPK> vacacionesFuncionariosPK;
    public static volatile SingularAttribute<VacacionesFuncionarios, Estados> estadoVacacion;
    public static volatile SingularAttribute<VacacionesFuncionarios, Integer> cantidadDiasRestante;
    public static volatile SingularAttribute<VacacionesFuncionarios, Funcionarios> funcionarios;
    public static volatile SingularAttribute<VacacionesFuncionarios, Integer> cantidadDiasCorrespondiente;
}
