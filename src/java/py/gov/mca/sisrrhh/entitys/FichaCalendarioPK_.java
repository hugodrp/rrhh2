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
import py.gov.mca.sisrrhh.entitys.FichaCalendarioPK;

@StaticMetamodel(value = FichaCalendarioPK.class)
public class FichaCalendarioPK_ {

    public static volatile SingularAttribute<FichaCalendarioPK, String> numeroDocumentoFuncionario;
    public static volatile SingularAttribute<FichaCalendarioPK, Date> fechaCalendario;
}
