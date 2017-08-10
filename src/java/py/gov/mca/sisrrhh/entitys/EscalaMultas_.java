/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 */
package py.gov.mca.sisrrhh.entitys;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.EscalaMultas;

@StaticMetamodel(value = EscalaMultas.class)
public class EscalaMultas_ {

    public static volatile SingularAttribute<EscalaMultas, String> descripcion;
    public static volatile SingularAttribute<EscalaMultas, Double> porcentajeMulta;
    public static volatile SingularAttribute<EscalaMultas, String> tipoEscala;
    public static volatile SingularAttribute<EscalaMultas, Integer> cantidadMinutosDesde;
    public static volatile SingularAttribute<EscalaMultas, Integer> cantidadMinutosHasta;
    public static volatile SingularAttribute<EscalaMultas, Integer> id;
}
