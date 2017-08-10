/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 */
package py.gov.mca.sisrrhh.entitys;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK;

@StaticMetamodel(value = AsignacionExcepcionesHorariosDiariosPK.class)
public class AsignacionExcepcionesHorariosDiariosPK_ {

    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiariosPK, String> codigoExcepcion;
    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiariosPK, String> numeroDocumentoFuncionario;
    public static volatile SingularAttribute<AsignacionExcepcionesHorariosDiariosPK, Date> fechaAsignacion;
}
