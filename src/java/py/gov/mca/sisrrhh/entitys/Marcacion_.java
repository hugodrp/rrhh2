/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 *  py.gov.mca.sisrrhh.entitys.MarcacionPK
 */
package py.gov.mca.sisrrhh.entitys;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.Marcacion;
import py.gov.mca.sisrrhh.entitys.MarcacionPK;

@StaticMetamodel(value = Marcacion.class)
public class Marcacion_ {

    public static volatile SingularAttribute<Marcacion, String> nombreArchivo;
    public static volatile SingularAttribute<Marcacion, Date> fechaMarcacionChar;
    public static volatile SingularAttribute<Marcacion, String> formaMarcacion;
    public static volatile SingularAttribute<Marcacion, MarcacionPK> marcacionPK;
    public static volatile SingularAttribute<Marcacion, String> diaMarcacion;
    public static volatile SingularAttribute<Marcacion, Date> horaMarcacionChar;
}
