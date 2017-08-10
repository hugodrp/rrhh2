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
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios;
import py.gov.mca.sisrrhh.entitys.FichaCalendario;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.Horarios;

@StaticMetamodel(value = Horarios.class)
public class Horarios_ {

    public static volatile SingularAttribute<Horarios, String> descripcion;
    public static volatile ListAttribute<Horarios, AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosList;
    public static volatile SingularAttribute<Horarios, Integer> cantidadHoras;
    public static volatile SingularAttribute<Horarios, Integer> cantidadMinutos;
    public static volatile SingularAttribute<Horarios, String> horario;
    public static volatile SingularAttribute<Horarios, String> horaEntrada;
    public static volatile ListAttribute<Horarios, FichaCalendario> fichaCalendarioList;
    public static volatile SingularAttribute<Horarios, String> horaSalida;
    public static volatile ListAttribute<Horarios, Funcionarios> funcionariosList;
}
