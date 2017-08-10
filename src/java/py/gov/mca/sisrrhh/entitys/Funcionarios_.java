/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.persistence.metamodel.ListAttribute
 *  javax.persistence.metamodel.SingularAttribute
 *  javax.persistence.metamodel.StaticMetamodel
 *  py.gov.mca.sisrrhh.entitys.Horarios
 *  py.gov.mca.sisrrhh.entitys.RelacionLaboral
 *  py.gov.mca.sisrrhh.entitys.Roles
 *  py.gov.mca.sisrrhh.entitys.UbicacionesFisicas
 *  py.gov.mca.sisrrhh.entitys.VacacionesFuncionarios
 */
package py.gov.mca.sisrrhh.entitys;

import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios;
import py.gov.mca.sisrrhh.entitys.Comentarios;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.FichaCalendario;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.Horarios;
import py.gov.mca.sisrrhh.entitys.RelacionLaboral;
import py.gov.mca.sisrrhh.entitys.Roles;
import py.gov.mca.sisrrhh.entitys.UbicacionesFisicas;
import py.gov.mca.sisrrhh.entitys.VacacionesFuncionarios;

@StaticMetamodel(value = Funcionarios.class)
public class Funcionarios_ {

    public static volatile SingularAttribute<Funcionarios, String> apellidos;
    public static volatile SingularAttribute<Funcionarios, UbicacionesFisicas> ubicacionesFisicasCodigo;
    public static volatile SingularAttribute<Funcionarios, Double> sueldoDiario;
    public static volatile ListAttribute<Funcionarios, FichaCalendario> fichaCalendarioList;
    public static volatile SingularAttribute<Funcionarios, String> direccion;
    public static volatile SingularAttribute<Funcionarios, Horarios> horarioNormal;
    public static volatile SingularAttribute<Funcionarios, Estados> estadoFuncionario;
    public static volatile SingularAttribute<Funcionarios, RelacionLaboral> relacionLaboral;
    public static volatile SingularAttribute<Funcionarios, String> nombres;
    public static volatile ListAttribute<Funcionarios, AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosList;
    public static volatile SingularAttribute<Funcionarios, Date> fechaIngreso;
    public static volatile SingularAttribute<Funcionarios, Comentarios> comentariosId;
    public static volatile SingularAttribute<Funcionarios, String> correo2;
    public static volatile SingularAttribute<Funcionarios, Roles> rolUsuario;
    public static volatile SingularAttribute<Funcionarios, String> correo1;
    public static volatile SingularAttribute<Funcionarios, String> telefono1;
    public static volatile SingularAttribute<Funcionarios, String> usuario;
    public static volatile SingularAttribute<Funcionarios, String> contrasena;
    public static volatile SingularAttribute<Funcionarios, String> telefono2;
    public static volatile SingularAttribute<Funcionarios, String> numeroDocumento;
    public static volatile ListAttribute<Funcionarios, VacacionesFuncionarios> vacacionesFuncionariosList;
}
