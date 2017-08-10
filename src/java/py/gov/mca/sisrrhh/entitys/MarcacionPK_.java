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
import py.gov.mca.sisrrhh.entitys.MarcacionPK;

@StaticMetamodel(value = MarcacionPK.class)
public class MarcacionPK_ {

    public static volatile SingularAttribute<MarcacionPK, String> cedula;
    public static volatile SingularAttribute<MarcacionPK, Date> fechaHoraMarcacion;
    public static volatile SingularAttribute<MarcacionPK, String> tipoMacacion;
}
