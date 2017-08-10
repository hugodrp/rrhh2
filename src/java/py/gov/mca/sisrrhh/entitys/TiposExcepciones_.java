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
import py.gov.mca.sisrrhh.entitys.FechasEspeciales;
import py.gov.mca.sisrrhh.entitys.FichaCalendario;
import py.gov.mca.sisrrhh.entitys.TiposExcepciones;

@StaticMetamodel(value = TiposExcepciones.class)
public class TiposExcepciones_ {

    public static volatile ListAttribute<TiposExcepciones, AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosList;
    public static volatile SingularAttribute<TiposExcepciones, String> codigoExcepcion;
    public static volatile ListAttribute<TiposExcepciones, FichaCalendario> fichaCalendarioList;
    public static volatile SingularAttribute<TiposExcepciones, String> descripcionExcepcion;
    public static volatile ListAttribute<TiposExcepciones, FechasEspeciales> fechasEspecialesList;
}
