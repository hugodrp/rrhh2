/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 *  py.gov.mca.sisrrhh.entitys.TiposExcepciones
 */
package py.gov.mca.sisrrhh.entitys;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.FechasEspeciales;
import py.gov.mca.sisrrhh.entitys.TiposExcepciones;

@StaticMetamodel(value = FechasEspeciales.class)
public class FechasEspeciales_ {

    public static volatile SingularAttribute<FechasEspeciales, String> aux1;
    public static volatile SingularAttribute<FechasEspeciales, TiposExcepciones> codigoExcepcion;
    public static volatile SingularAttribute<FechasEspeciales, String> aux2;
    public static volatile SingularAttribute<FechasEspeciales, String> fechasEspecialescol;
    public static volatile SingularAttribute<FechasEspeciales, Date> fechaEspecial;
    public static volatile SingularAttribute<FechasEspeciales, String> descripcionFechaEspecial;
}
