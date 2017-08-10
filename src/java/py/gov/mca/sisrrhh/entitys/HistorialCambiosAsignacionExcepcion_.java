/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 *  py.gov.mca.sisrrhh.entitys.HistorialCambiosAsignacionExcepcionPK
 */
package py.gov.mca.sisrrhh.entitys;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios;
import py.gov.mca.sisrrhh.entitys.HistorialCambiosAsignacionExcepcion;
import py.gov.mca.sisrrhh.entitys.HistorialCambiosAsignacionExcepcionPK;

@StaticMetamodel(value = HistorialCambiosAsignacionExcepcion.class)
public class HistorialCambiosAsignacionExcepcion_ {

    public static volatile SingularAttribute<HistorialCambiosAsignacionExcepcion, String> descripcionCambio;
    public static volatile SingularAttribute<HistorialCambiosAsignacionExcepcion, String> fechaCambio;
    public static volatile SingularAttribute<HistorialCambiosAsignacionExcepcion, AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiarios;
    public static volatile SingularAttribute<HistorialCambiosAsignacionExcepcion, String> motivoCambio;
    public static volatile SingularAttribute<HistorialCambiosAsignacionExcepcion, HistorialCambiosAsignacionExcepcionPK> historialCambiosAsignacionExcepcionPK;
}
