/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.ListAttribute
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 *  py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK
 *  py.gov.mca.sisrrhh.entitys.Estados
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
 *  py.gov.mca.sisrrhh.entitys.HistorialCambiosAsignacionExcepcion
 *  py.gov.mca.sisrrhh.entitys.Horarios
 *  py.gov.mca.sisrrhh.entitys.TiposExcepciones
 */
package py.gov.mca.sisrrhh.entitys;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.HistorialCambiosAsignacionExcepcion;
import py.gov.mca.sisrrhh.entitys.Horarios;
import py.gov.mca.sisrrhh.entitys.TiposExcepciones;

@StaticMetamodel(value = AsignacionExcepcionesHorariosDiarios.class)
public class AsignacionExcepcionesHorariosDiarios_ {

    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiarios, Integer> mesAsignacion;
    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiarios, AsignacionExcepcionesHorariosDiariosPK> asignacionExcepcionesHorariosDiariosPK;
    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiarios, String> descripcionAsignacion;
    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiarios, Estados> estadoHorarioAsignado;
    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiarios, Funcionarios> funcionarios;
    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiarios, Integer> anioAsignacion;
    public static volatile ListAttribute<AsignacionExcepcionesHorariosDiarios, HistorialCambiosAsignacionExcepcion> historialCambiosAsignacionExcepcionList;
    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiarios, Horarios> horarioAsignado;
    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiarios, TiposExcepciones> tiposExcepciones;
}
