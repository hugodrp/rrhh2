/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 *  py.gov.mca.sisrrhh.entitys.FichaCalendarioPK
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
 *  py.gov.mca.sisrrhh.entitys.Horarios
 *  py.gov.mca.sisrrhh.entitys.TiposExcepciones
 */
package py.gov.mca.sisrrhh.entitys;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.FichaCalendario;
import py.gov.mca.sisrrhh.entitys.FichaCalendarioPK;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.Horarios;
import py.gov.mca.sisrrhh.entitys.TiposExcepciones;

@StaticMetamodel(value = FichaCalendario.class)
public class FichaCalendario_ {

    public static volatile SingularAttribute<FichaCalendario, Integer> marcaCalculoHorasExtras;
    public static volatile SingularAttribute<FichaCalendario, TiposExcepciones> codigoExcepcion;
    public static volatile SingularAttribute<FichaCalendario, String> motivo;
    public static volatile SingularAttribute<FichaCalendario, String> motivoCambioHora;
    public static volatile SingularAttribute<FichaCalendario, Integer> porcentajeMulta;
    public static volatile SingularAttribute<FichaCalendario, Integer> diaCalendarioNumero;
    public static volatile SingularAttribute<FichaCalendario, Date> horaEntrada;
    public static volatile SingularAttribute<FichaCalendario, Boolean> diaTrabajado;
    public static volatile SingularAttribute<FichaCalendario, String> diaCalendarioLetra;
    public static volatile SingularAttribute<FichaCalendario, Boolean> diaComplementario;
    public static volatile SingularAttribute<FichaCalendario, String> horaExtraTiempo;
    public static volatile SingularAttribute<FichaCalendario, Integer> montoFijo;
    public static volatile SingularAttribute<FichaCalendario, Integer> anioCalendarioNumero;
    public static volatile SingularAttribute<FichaCalendario, Date> horaSalida;
    public static volatile SingularAttribute<FichaCalendario, Horarios> horarioAsignado;
    public static volatile SingularAttribute<FichaCalendario, Integer> marcaCalculoPorcentaje;
    public static volatile SingularAttribute<FichaCalendario, FichaCalendarioPK> fichaCalendarioPK;
    public static volatile SingularAttribute<FichaCalendario, Integer> horasExtra;
    public static volatile SingularAttribute<FichaCalendario, String> marcaBloqueo;
    public static volatile SingularAttribute<FichaCalendario, Integer> mesCalendarioNumero;
    public static volatile SingularAttribute<FichaCalendario, Date> horaNoDefinida;
    public static volatile SingularAttribute<FichaCalendario, Funcionarios> funcionarios;
    public static volatile SingularAttribute<FichaCalendario, Boolean> marcaFichaVerificada;
}
