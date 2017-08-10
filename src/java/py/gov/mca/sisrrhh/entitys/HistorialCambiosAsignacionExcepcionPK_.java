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
import py.gov.mca.sisrrhh.entitys.HistorialCambiosAsignacionExcepcionPK;

@StaticMetamodel(value = HistorialCambiosAsignacionExcepcionPK.class)
public class HistorialCambiosAsignacionExcepcionPK_ {

    public static volatile SingularAttribute<HistorialCambiosAsignacionExcepcionPK, Integer> codigoHistorial;
    public static volatile SingularAttribute<HistorialCambiosAsignacionExcepcionPK, String> codigoExcepcion;
    public static volatile SingularAttribute<HistorialCambiosAsignacionExcepcionPK, String> numeroDocumentoFuncionario;
    public static volatile SingularAttribute<HistorialCambiosAsignacionExcepcionPK, Date> fechaAsignacion;
}
