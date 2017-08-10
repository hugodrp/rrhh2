/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJB
 *  javax.enterprise.context.SessionScoped
 *  javax.faces.application.FacesMessage
 *  javax.faces.application.FacesMessage$Severity
 *  javax.faces.component.html.HtmlDataTable
 *  javax.faces.context.ExternalContext
 *  javax.faces.context.FacesContext
 *  javax.inject.Named
 *  javax.servlet.ServletContext
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 *  javax.servlet.http.Part
 *  net.sf.jasperreports.engine.JRException
 *  org.apache.commons.lang.StringUtils
 *  py.gov.mca.sisrrhh.clasesaux.FechaHora
 *  py.gov.mca.sisrrhh.clasesaux.FuncionarioMarcacion
 *  py.gov.mca.sisrrhh.clasesaux.MultaCsv
 *  py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios
 *  py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK
 *  py.gov.mca.sisrrhh.entitys.Auditoria
 *  py.gov.mca.sisrrhh.entitys.EscalaMultas
 *  py.gov.mca.sisrrhh.entitys.Estados
 *  py.gov.mca.sisrrhh.entitys.FichaCalendario
 *  py.gov.mca.sisrrhh.entitys.FichaCalendarioPK
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
 *  py.gov.mca.sisrrhh.entitys.Horarios
 *  py.gov.mca.sisrrhh.entitys.Marcacion
 *  py.gov.mca.sisrrhh.entitys.MarcacionPK
 *  py.gov.mca.sisrrhh.entitys.RelacionLaboral
 *  py.gov.mca.sisrrhh.entitys.Roles
 *  py.gov.mca.sisrrhh.entitys.TiposExcepciones
 *  py.gov.mca.sisrrhh.managedbeans.SisrrhhMB
 *  py.gov.mca.sisrrhh.negocio.ReportePorcentajeMultaCsv
 *  py.gov.mca.sisrrhh.negocio.ReportesFichaCalendario
 *  py.gov.mca.sisrrhh.negocio.ReportesFuncionarioMarcacion
 *  py.gov.mca.sisrrhh.sessionbeans.AsignacionExcepcionesHorariosDiariosSB
 *  py.gov.mca.sisrrhh.sessionbeans.AuditoriaSB
 *  py.gov.mca.sisrrhh.sessionbeans.EscalaMultasSB
 *  py.gov.mca.sisrrhh.sessionbeans.FichaCalendarioSB
 *  py.gov.mca.sisrrhh.sessionbeans.FuncionariosSB
 *  py.gov.mca.sisrrhh.sessionbeans.HorariosSB
 *  py.gov.mca.sisrrhh.sessionbeans.MarcacionSB
 *  py.gov.mca.sisrrhh.sessionbeans.TiposExcepcionesSB
 */
package py.gov.mca.sisrrhh.managedbeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang.StringUtils;
import py.gov.mca.sisrrhh.clasesaux.FechaHora;
import py.gov.mca.sisrrhh.clasesaux.FuncionarioMarcacion;
import py.gov.mca.sisrrhh.clasesaux.MultaCsv;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK;
import py.gov.mca.sisrrhh.entitys.Auditoria;
import py.gov.mca.sisrrhh.entitys.EscalaMultas;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.FichaCalendario;
import py.gov.mca.sisrrhh.entitys.FichaCalendarioPK;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.Horarios;
import py.gov.mca.sisrrhh.entitys.Marcacion;
import py.gov.mca.sisrrhh.entitys.MarcacionPK;
import py.gov.mca.sisrrhh.entitys.RelacionLaboral;
import py.gov.mca.sisrrhh.entitys.Roles;
import py.gov.mca.sisrrhh.entitys.TiposExcepciones;
import py.gov.mca.sisrrhh.managedbeans.SisrrhhMB;
import py.gov.mca.sisrrhh.negocio.ReportePorcentajeMultaCsv;
import py.gov.mca.sisrrhh.negocio.ReportesFichaCalendario;
import py.gov.mca.sisrrhh.negocio.ReportesFuncionarioMarcacion;
import py.gov.mca.sisrrhh.sessionbeans.AsignacionExcepcionesHorariosDiariosSB;
import py.gov.mca.sisrrhh.sessionbeans.AuditoriaSB;
import py.gov.mca.sisrrhh.sessionbeans.EscalaMultasSB;
import py.gov.mca.sisrrhh.sessionbeans.FichaCalendarioSB;
import py.gov.mca.sisrrhh.sessionbeans.FuncionariosSB;
import py.gov.mca.sisrrhh.sessionbeans.HorariosSB;
import py.gov.mca.sisrrhh.sessionbeans.MarcacionSB;
import py.gov.mca.sisrrhh.sessionbeans.TiposExcepcionesSB;

@Named(value = "fichasCalendariosMB")
@SessionScoped
public class FichasCalendariosMB
        implements Serializable {

    @EJB
    private FichaCalendarioSB ejbFichaCalendarioSB;
    @EJB
    private FuncionariosSB ejbFuncionariosSB;
    @EJB
    private MarcacionSB ejbMarcacionSB;
    @EJB
    private AuditoriaSB auditoriaSB;
    @EJB
    private AsignacionExcepcionesHorariosDiariosSB ejbAsignacionExcepcionesHorariosDiariosSB;
    @EJB
    private EscalaMultasSB escalaMultasSB;
    @EJB
    private HorariosSB horariosSB;
    @EJB
    private TiposExcepcionesSB tiposExcepcionesSB;
    private final SimpleDateFormat dateFormatFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final SimpleDateFormat dateFormatFecha = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat dateFormatFechaGuionBajo = new SimpleDateFormat("dd_MM_yyyy");
    private final SimpleDateFormat dateFormatHora = new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat dateFormatDia = new SimpleDateFormat("EEEE", Locale.getDefault());
    private final SimpleDateFormat dateFormatMes = new SimpleDateFormat("MMMM", Locale.getDefault());
    private SimpleDateFormat dateFormatDiaNumero = new SimpleDateFormat("dd");
    private SimpleDateFormat dateFormatMesNumero = new SimpleDateFormat("MM");
    private SimpleDateFormat dateFormatAnioNumero = new SimpleDateFormat("yyyy");
    private Integer numeroMes;
    private Integer numeroCiclo;
    private Integer numeroAnio;
    private Integer numeroTipoMarcacion;
    private Integer diasBoolean;
    private String tipoGeneracion;
    private List<FichaCalendario> fichasCalendarios;
    private Funcionarios funcionarioUsuario;
    private LinkedHashMap<Integer, String> listaMeses = new LinkedHashMap();
    private LinkedHashMap<Integer, String> listaCiclos;
    private LinkedHashMap<Integer, String> listaTipoMarcacion;
    private LinkedHashMap<Integer, String> listaSiNo;
    private boolean mostrarComponentesPantallaBuscarRangoFechaCedula;
    private boolean activarComponentes;
    private String fichaVerificada;
    private Integer porcentajeMulta;
    private Integer porcentajeMultaTotal;
    private Integer montoFijo;
    private Integer montoFijoTotal;
    private Integer diasTrabajados;
    private Integer diasComplementarios;
    private Funcionarios funcionario;
    private String cedulaFuncionario;
    private Date fechaDesde;
    private Date fechaHasta;
    private Part file;
    private String nombreArchivo;
    private String tipoReporte;
    private String tipoReporte2;
    private int cantidadRegistros;
    private List<String> cedulasDesdeArchivo;
    private HtmlDataTable dataTableFichasCalendarios;
    private FichaCalendario fichaCalendario;
    private List<Horarios> horarios;
    private List<TiposExcepciones> tiposExcepciones;

    public FichasCalendariosMB() {
        this.listaMeses.put(0, "Enero");
        this.listaMeses.put(1, "Febrero");
        this.listaMeses.put(2, "Marzo");
        this.listaMeses.put(3, "Abril");
        this.listaMeses.put(4, "Mayo");
        this.listaMeses.put(5, "Junio");
        this.listaMeses.put(6, "Julio");
        this.listaMeses.put(7, "Agosto");
        this.listaMeses.put(8, "Septiembre");
        this.listaMeses.put(9, "Octubre");
        this.listaMeses.put(10, "Noviembre");
        this.listaMeses.put(11, "Diciembre");
        this.listaCiclos = new LinkedHashMap();
        this.listaCiclos.put(0, "Enero - Febrero");
        this.listaCiclos.put(1, "Febrero - Marzo");
        this.listaCiclos.put(2, "Marzo - Abril");
        this.listaCiclos.put(3, "Abril - Mayo");
        this.listaCiclos.put(4, "Mayo - Junio");
        this.listaCiclos.put(5, "Junio - Julio");
        this.listaCiclos.put(6, "Julio - Agosto");
        this.listaCiclos.put(7, "Agosto - Septiembre");
        this.listaCiclos.put(8, "Septiembre - Octubre");
        this.listaCiclos.put(9, "Octubre - Noviembre");
        this.listaCiclos.put(10, "Noviembre - Diciembre");
        this.listaCiclos.put(11, "Diciembre - Enero");
        this.listaTipoMarcacion = new LinkedHashMap();
        this.listaTipoMarcacion.put(1, "Entrada");
        this.listaTipoMarcacion.put(2, "Salida");
        this.listaSiNo = new LinkedHashMap();
        this.listaSiNo.put(1, "SI");
        this.listaSiNo.put(2, "NO");
    }

    @PostConstruct
    public void init() {
        this.recuperarUsuarioSession();
        this.prepararFormFichaGeneralMensualNom();
        this.prepararFormFichaGeneralMensualJor();
    }

    public String prepararUpdateBloqueo() {
        return "/administracion/admin_form_update_bloqueo";
    }

    public void updateBloqueo() {
        this.fichasCalendarios = this.ejbFichaCalendarioSB.findAll();
        for (int i = 0; i < this.fichasCalendarios.size(); ++i) {
            this.fichasCalendarios.get(i).setMarcaBloqueo("NO");
        }
        this.guardarFichasCalendarios();
    }

    public String prepararFormCrearFichasCalendariosNom() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.setNumeroMes(Integer.valueOf(cal.get(2)) - 1);
        this.setNumeroAnio(cal.get(1));
        this.setTipoGeneracion("NOM");
        return "/administracion/admin_funcionario_ficha_general_form_creacion_nom";
    }

    public void crearFichasCalendariosNom() throws ParseException {
        int diasDelMes = this.cantidadDiasDelMes(this.getNumeroAnio(), this.getNumeroMes());
        List listaFuncionariosActivos = this.ejbFuncionariosSB.findByEstadoRelacionLaboralFiltro("ACT", this.getTipoGeneracion());
        Integer mesInt = this.getNumeroMes() + 1;
        String mesDesdeString = mesInt + "";
        if (mesInt < 10) {
            mesDesdeString = "0" + mesInt;
        }
        Date fechaDesdeAux = this.getDateFormatFecha().parse("1/" + mesDesdeString + "/" + this.getNumeroAnio());
        Date fechaHastaAux = this.getDateFormatFecha().parse("" + diasDelMes + "/" + mesDesdeString + "/" + this.getNumeroAnio());
        this.setFichasCalendarios(new ArrayList<FichaCalendario>());
        for (int i = 0; i < listaFuncionariosActivos.size(); ++i) {
            List<FichaCalendario> fichasCalendariosAux = this.getMarcacionesPorCedulaFechaDedeFechaHasta(((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento(), fechaDesdeAux, fechaHastaAux);
            for (int j = 0; j < diasDelMes; ++j) {
                Integer diaInt = j + 1;
                String diaString = diaInt + "";
                if (diaInt < 10) {
                    diaString = "0" + diaInt;
                }
                String mesString = mesInt + "";
                if (mesInt < 10) {
                    mesString = "0" + mesInt;
                }
                String fechaSeleccionada = diaString + "/" + mesString + "/" + this.getNumeroAnio();
                FichaCalendario fichaCalendario = new FichaCalendario();
                fichaCalendario.setFichaCalendarioPK(new FichaCalendarioPK());
                fichaCalendario.getFichaCalendarioPK().setFechaCalendario(this.getDateFormatFecha().parse(fechaSeleccionada));
                fichaCalendario.getFichaCalendarioPK().setNumeroDocumentoFuncionario(((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento());
                fichaCalendario.setMesCalendarioNumero(this.getNumeroMes() + 1);
                fichaCalendario.setAnioCalendarioNumero(this.getNumeroAnio().intValue());
                fichaCalendario.setDiaCalendarioNumero(diaInt.intValue());
                fichaCalendario.setDiaCalendarioLetra(this.dateFormatDia.format(this.getDateFormatFecha().parse(fechaSeleccionada)).toUpperCase());
                fichaCalendario.setHorarioAsignado(((Funcionarios) listaFuncionariosActivos.get(i)).getHorarioNormal());
                if (fichasCalendariosAux.isEmpty()) {
                    fichaCalendario.setHoraEntrada(null);
                    fichaCalendario.setHoraSalida(null);
                    fichaCalendario.setHoraNoDefinida(null);
                } else {
                    for (int k = 0; k < fichasCalendariosAux.size(); ++k) {
                        String fecha2;
                        String fecha1 = this.getDateFormatFecha().format(fichaCalendario.getFichaCalendarioPK().getFechaCalendario());
                        if (!fecha1.equals(fecha2 = this.getDateFormatFecha().format(fichasCalendariosAux.get(k).getFichaCalendarioPK().getFechaCalendario()))) {
                            continue;
                        }
                        fichaCalendario.setHoraEntrada(fichasCalendariosAux.get(k).getHoraEntrada());
                        fichaCalendario.setHoraSalida(fichasCalendariosAux.get(k).getHoraSalida());
                        fichaCalendario.setHoraNoDefinida(fichasCalendariosAux.get(k).getHoraNoDefinida());
                        break;
                    }
                }
                fichaCalendario.setPorcentajeMulta(Integer.valueOf(0));
                fichaCalendario.setMontoFijo(Integer.valueOf(0));
                fichaCalendario.setMarcaBloqueo("NO");
                this.getFichasCalendarios().add(fichaCalendario);
            }
        }
        this.guardarFichasCalendarios();
    }

    public String prepararFormCrearFichasCalendariosJor() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.setNumeroCiclo(Integer.valueOf(cal.get(2)) - 1);
        this.setNumeroAnio(cal.get(1));
        this.setTipoGeneracion("JOR");
        return "/administracion/admin_funcionario_ficha_general_form_creacion_jor";
    }

    public void crearFichasCalendariosJor() throws ParseException {
        List listaFuncionariosActivos = this.ejbFuncionariosSB.findByEstadoRelacionLaboralFiltro("ACT", this.getTipoGeneracion());
        Integer mesIni = this.getNumeroCiclo() + 1;
        String mesDesdeString = mesIni + "";
        if (mesIni < 10) {
            mesDesdeString = "0" + mesIni;
        }
        Integer mesHas = mesIni + 1;
        String mesHastaString = "" + (mesHas + 1) + "";
        if (mesHas < 10) {
            mesHastaString = "0" + mesHas;
        }
        Integer anioIni = this.getNumeroAnio();
        Integer anioFin = this.getNumeroAnio();
        if (mesIni.equals(12)) {
            mesHastaString = "01";
            anioFin = anioFin + 1;
        }
        Date fechaDesdeAux = this.getDateFormatFecha().parse("11/" + mesDesdeString + "/" + anioIni);
        Date fechaHastaAux = this.getDateFormatFecha().parse("10/" + mesHastaString + "/" + anioFin);
        this.setFichasCalendarios(new ArrayList<FichaCalendario>());
        ArrayList<Date> calendarioJor = new ArrayList<Date>();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setLenient(false);
        Date fechaIni = fechaDesdeAux;
        Date fechaFin = fechaHastaAux;
        boolean ban = false;
        while (!ban) {
            if (fechaIni.after(fechaFin)) {
                ban = true;
                continue;
            }
            calendarioJor.add(fechaIni);
            cal.setTime(fechaIni);
            cal.add(5, 1);
            fechaIni = this.calendarToDate(cal);
        }
        for (int i = 0; i < listaFuncionariosActivos.size(); ++i) {
            List<FichaCalendario> fichasCalendariosAux = this.getMarcacionesPorCedulaFechaDedeFechaHasta(((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento(), fechaDesdeAux, fechaHastaAux);
            for (int j = 0; j < calendarioJor.size(); ++j) {
                FichaCalendario fichaCalendario = new FichaCalendario();
                fichaCalendario.setFichaCalendarioPK(new FichaCalendarioPK());
                fichaCalendario.getFichaCalendarioPK().setFechaCalendario((Date) calendarioJor.get(j));
                fichaCalendario.getFichaCalendarioPK().setNumeroDocumentoFuncionario(((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento());
                fichaCalendario.setMesCalendarioNumero(Integer.parseInt(this.dateFormatMesNumero.format((Date) calendarioJor.get(j))));
                fichaCalendario.setAnioCalendarioNumero(Integer.parseInt(this.dateFormatAnioNumero.format((Date) calendarioJor.get(j))));
                fichaCalendario.setDiaCalendarioNumero(Integer.parseInt(this.dateFormatDiaNumero.format((Date) calendarioJor.get(j))));
                fichaCalendario.setDiaCalendarioLetra(this.dateFormatDia.format((Date) calendarioJor.get(j)).toUpperCase());
                fichaCalendario.setHorarioAsignado(((Funcionarios) listaFuncionariosActivos.get(i)).getHorarioNormal());
                if (fichasCalendariosAux.isEmpty()) {
                    fichaCalendario.setHoraEntrada(null);
                    fichaCalendario.setHoraSalida(null);
                    fichaCalendario.setHoraNoDefinida(null);
                } else {
                    for (int k = 0; k < fichasCalendariosAux.size(); ++k) {
                        String fecha2;
                        String fecha1 = this.getDateFormatFecha().format(fichaCalendario.getFichaCalendarioPK().getFechaCalendario());
                        if (!fecha1.equals(fecha2 = this.getDateFormatFecha().format(fichasCalendariosAux.get(k).getFichaCalendarioPK().getFechaCalendario()))) {
                            continue;
                        }
                        fichaCalendario.setHoraEntrada(fichasCalendariosAux.get(k).getHoraEntrada());
                        fichaCalendario.setHoraSalida(fichasCalendariosAux.get(k).getHoraSalida());
                        fichaCalendario.setHoraNoDefinida(fichasCalendariosAux.get(k).getHoraNoDefinida());
                        if (fichasCalendariosAux.get(k).getHoraEntrada() != null || fichasCalendariosAux.get(k).getHoraSalida() != null || fichasCalendariosAux.get(k).getHoraNoDefinida() != null) {
                            fichaCalendario.setDiaTrabajado(Boolean.valueOf(true));
                        }
                        fichaCalendario.setDiaComplementario(Boolean.valueOf(false));
                        break;
                    }
                }
                fichaCalendario.setPorcentajeMulta(Integer.valueOf(0));
                fichaCalendario.setMontoFijo(Integer.valueOf(0));
                fichaCalendario.setMarcaBloqueo("NO");
                this.getFichasCalendarios().add(fichaCalendario);
            }
        }
        this.guardarFichasCalendarios();
    }

    public void guardarFichasCalendarios() {
        if (this.getFichasCalendarios() != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se generaron fichas nueva", ""));
            if (this.ejbFichaCalendarioSB.udtadeMultiple(this.getFichasCalendarios()).equals("OK")) {
                String descriAudit = "Fecha: " + new Date() + ", Total Registros insertados: " + this.getFichasCalendarios().size();
                this.guardarAuditoria(new Date(), this.getFuncionarioUsuario().getUsuario(), this.getFuncionarioUsuario().getRolUsuario().getNombre(), "Insert", "ficha_calendario", "", descriAudit);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se generaron fichas nueva", "Total: " + this.getFichasCalendarios().size()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al generar las fichas", ""));
        }
        this.fichasCalendarios = null;
    }

    public void guardarAuditoria(Date fechaAudit, String usuarioAudit, String rolAudit, String tipoMovimientoAudit, String nombreTablaAudit, String campoClaveAudit, String descriAudit) {
        Auditoria auditoria = new Auditoria();
        auditoria.setFecha(fechaAudit);
        auditoria.setUsuario(usuarioAudit);
        auditoria.setRolUsuario(rolAudit);
        auditoria.setTipoMovimiento(tipoMovimientoAudit);
        auditoria.setNombreTabla(nombreTablaAudit);
        auditoria.setClaveTabla(campoClaveAudit);
        auditoria.setDescripcion(descriAudit);
        this.auditoriaSB.create(auditoria);
    }

    public List<FichaCalendario> getMarcacionesPorCedulaFechaDedeFechaHasta(String cedula, Date fechaDesde, Date fechaHasta) {
        List marcaciones = this.ejbMarcacionSB.findByCedulaFechaDesdeFechaHasta(cedula, fechaDesde, fechaHasta);
        ArrayList<FichaCalendario> fichaCalendarioRetorno = new ArrayList<FichaCalendario>();
        String control = "";
        for (int i = 0; i < marcaciones.size(); ++i) {
            if (!control.equals(((Marcacion) marcaciones.get(i)).getFechaMarcacionChar().toString())) {
                try {
                    Date fechaInicial = this.getDateFormatFecha().parse(this.getDateFormatFecha().format(((Marcacion) marcaciones.get(i)).getMarcacionPK().getFechaHoraMarcacion()));
                    ArrayList marcacionesAux = new ArrayList();
                    for (int j = 0; j < marcaciones.size(); ++j) {
                        if (!fechaInicial.equals(this.getDateFormatFecha().parse(this.getDateFormatFecha().format(((Marcacion) marcaciones.get(j)).getMarcacionPK().getFechaHoraMarcacion())))) {
                            continue;
                        }
                        marcacionesAux.add(marcaciones.get(j));
                    }
                    Marcacion marcacionMenor = new Marcacion();
                    Marcacion marcacionMayor = new Marcacion();
                    Marcacion marcacionNoDefinida = new Marcacion();
                    for (int k = 0; k < marcacionesAux.size(); ++k) {
                        Date fechaMayor = ((Marcacion) marcacionesAux.get(k)).getMarcacionPK().getFechaHoraMarcacion();
                        Date fechaMenor = ((Marcacion) marcacionesAux.get(k)).getMarcacionPK().getFechaHoraMarcacion();
                        for (int l = 0; l < marcacionesAux.size(); ++l) {
                            if (((Marcacion) marcacionesAux.get(l)).getMarcacionPK().getFechaHoraMarcacion().compareTo(fechaMayor) > 0) {
                                marcacionMayor = (Marcacion) marcacionesAux.get(l);
                                fechaMayor = ((Marcacion) marcacionesAux.get(l)).getMarcacionPK().getFechaHoraMarcacion();
                                continue;
                            }
                            if (((Marcacion) marcacionesAux.get(l)).getMarcacionPK().getFechaHoraMarcacion().compareTo(fechaMenor) < 0) {
                                marcacionMenor = (Marcacion) marcacionesAux.get(l);
                                fechaMenor = ((Marcacion) marcacionesAux.get(l)).getMarcacionPK().getFechaHoraMarcacion();
                                continue;
                            }
                            marcacionNoDefinida = (Marcacion) marcacionesAux.get(l);
                        }
                    }
                    FichaCalendario fichaCalendario = new FichaCalendario();
                    fichaCalendario.setDiaCalendarioLetra(((Marcacion) marcacionesAux.get(0)).getDiaMarcacion());
                    fichaCalendario.setFichaCalendarioPK(new FichaCalendarioPK());
                    fichaCalendario.getFichaCalendarioPK().setFechaCalendario(((Marcacion) marcacionesAux.get(0)).getMarcacionPK().getFechaHoraMarcacion());
                    fichaCalendario.getFichaCalendarioPK().setNumeroDocumentoFuncionario(((Marcacion) marcacionesAux.get(0)).getMarcacionPK().getCedula());
                    if (marcacionMenor.getHoraMarcacionChar() != null) {
                        fichaCalendario.setHoraEntrada(marcacionMenor.getHoraMarcacionChar());
                    } else {
                        fichaCalendario.setHoraEntrada(null);
                    }
                    if (marcacionMayor.getHoraMarcacionChar() != null) {
                        fichaCalendario.setHoraSalida(marcacionMayor.getHoraMarcacionChar());
                    } else {
                        fichaCalendario.setHoraSalida(null);
                    }
                    if (marcacionNoDefinida.getHoraMarcacionChar() != null) {
                        fichaCalendario.setHoraNoDefinida(marcacionNoDefinida.getHoraMarcacionChar());
                        if (marcacionMenor.getHoraMarcacionChar() != null) {
                            fichaCalendario.setHoraNoDefinida(null);
                        }
                        if (marcacionMayor.getHoraMarcacionChar() != null) {
                            fichaCalendario.setHoraNoDefinida(null);
                        }
                    } else {
                        fichaCalendario.setHoraNoDefinida(null);
                    }
                    fichaCalendarioRetorno.add(fichaCalendario);
                } catch (ParseException ex) {
                    Logger.getLogger(SisrrhhMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            control = ((Marcacion) marcaciones.get(i)).getFechaMarcacionChar().toString();
        }
        return fichaCalendarioRetorno;
    }

    public String prepararFormFichaGeneralMensualNom() {
        this.dataTableFichasCalendarios = new HtmlDataTable();
        this.mostrarComponentesPantallaBuscarRangoFechaCedula = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.numeroMes = cal.get(2) - 1;
        this.numeroAnio = cal.get(1);
        this.porcentajeMultaTotal = 0;
        return "admin_funcionario_ficha_general_nom";
    }

    public String moverHorarioMarcacionFichaGeneral() {
        this.fichaCalendario = new FichaCalendario();
        this.fichaCalendario = (FichaCalendario) this.getDataTableFichasCalendarios().getRowData();
        return "admin_funcionario_mover_tipo_marcacion_ficha_general";
    }

    public String guardarMoverHorarioMarcacionFichaGeneral() {
        String retorno = "";
        if (this.numeroTipoMarcacion != null) {
            String horarioMovido;
            if (this.numeroTipoMarcacion == 1) {
                this.fichaCalendario.setHoraEntrada(this.fichaCalendario.getHoraNoDefinida());
                horarioMovido = "HoraEntrada: " + this.fichaCalendario.getHoraEntrada();
            } else {
                this.fichaCalendario.setHoraSalida(this.fichaCalendario.getHoraNoDefinida());
                horarioMovido = "HoraSalida: " + this.fichaCalendario.getHoraSalida();
            }
            this.fichaCalendario.setHoraNoDefinida(null);
            this.ejbFichaCalendarioSB.udtade(this.fichaCalendario);
            String descriAudit = "Fecha: " + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + horarioMovido;
            this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "ficha_calendario", this.fichaCalendario.getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
            this.dataTableFichasCalendarios = new HtmlDataTable();
            this.numeroTipoMarcacion = 0;
            retorno = this.redireccion();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un tipo de marcaci\u00f3n", ""));
        }
        return retorno;
    }

    public String cambiarHorarioAsignadoFichaGeneral() {
        this.fichaCalendario = new FichaCalendario();
        this.fichaCalendario = (FichaCalendario) this.getDataTableFichasCalendarios().getRowData();
        return "admin_funcionario_cambiar_horario_asignado_ficha_general";
    }

    public String guardarCambiarHorarioAsignadoFichaGeneral() {
        String retorno = "";
        if (!StringUtils.isBlank((String) this.fichaCalendario.getMotivoCambioHora())) {
            this.ejbFichaCalendarioSB.udtade(this.fichaCalendario);
            String descriAudit = "Fecha: " + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + this.fichaCalendario.getMotivoCambioHora();
            this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "ficha_calendario", this.fichaCalendario.getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
            retorno = this.redireccion();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo Comentario no puede estar vacio. ", ""));
        }
        return retorno;
    }

    public String agregarMotivoFichaGeneral() {
        this.fichaCalendario = new FichaCalendario();
        this.fichaCalendario = (FichaCalendario) this.getDataTableFichasCalendarios().getRowData();
        if (this.fichaCalendario.getCodigoExcepcion() == null) {
            this.fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
        }
        return "admin_funcionario_agregar_motivo_ficha_general";
    }

    public String guardarAgregarMotivoFichaGeneral() {
        String retorno = "";
        if (!StringUtils.isBlank((String) this.fichaCalendario.getMotivo())) {
            String descriAudit = "Fecha: " + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + this.fichaCalendario.getMotivo();
            this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "ficha_calendario", this.fichaCalendario.getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
            this.ejbFichaCalendarioSB.udtade(this.fichaCalendario);
            retorno = this.redireccion();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo Comentario no puede estar vacio. ", ""));
        }
        return retorno;
    }

    public String agregarProcentajeMultaFichaGeneral() {
        this.fichaCalendario = new FichaCalendario();
        this.porcentajeMulta = null;
        this.fichaCalendario = (FichaCalendario) this.getDataTableFichasCalendarios().getRowData();
        if (this.fichaCalendario.getCodigoExcepcion() == null) {
            this.fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
        }
        return "admin_funcionario_agregar_porcentaje_multa_ficha_general";
    }

    public String guardarAgregarProcentajeMultaFichaGeneral() {
        String retorno = "";
        if (this.porcentajeMulta != null && this.porcentajeMulta > -1 && this.porcentajeMulta < 101) {
            this.fichaCalendario.setPorcentajeMulta(this.porcentajeMulta);
            String descriAudit = "Fecha: " + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", PorcentajeMulta: " + this.fichaCalendario.getPorcentajeMulta();
            this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "ficha_calendario", this.fichaCalendario.getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
            this.ejbFichaCalendarioSB.udtade(this.fichaCalendario);
            retorno = this.redireccion();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor no valido para Cambiar Pocentaje Actual. ", "Debe estar entre 0 y 100"));
        }
        this.calcutarTotalPorcentaje();
        return retorno;
    }

    public String agregarMontoFijoMultaFichaGeneral() {
        this.fichaCalendario = new FichaCalendario();
        this.montoFijo = null;
        this.fichaCalendario = (FichaCalendario) this.getDataTableFichasCalendarios().getRowData();
        if (this.fichaCalendario.getCodigoExcepcion() == null) {
            this.fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
        }
        return "admin_funcionario_agregar_monto_fijo_multa_ficha_general";
    }

    public String guardarAgregarMontoFijoMultaFichaGeneral() {
        String retorno = "";
        if (this.montoFijo != null && this.montoFijo > -1) {
            this.fichaCalendario.setMontoFijo(this.montoFijo);
            String descriAudit = "Fecha: " + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", MontoFijoMulta: " + this.fichaCalendario.getMontoFijo();
            this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "ficha_calendario", this.fichaCalendario.getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
            this.ejbFichaCalendarioSB.udtade(this.fichaCalendario);
            retorno = this.redireccion();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor no valido para Cambiar Monto Fijo Actual. ", "No puede estar vacio"));
        }
        this.calcutarTotalPorcentaje();
        return retorno;
    }

    public String diaTrabajadoFichaGeneral() {
        this.diasBoolean = null;
        this.fichaCalendario = new FichaCalendario();
        this.montoFijo = null;
        this.fichaCalendario = (FichaCalendario) this.getDataTableFichasCalendarios().getRowData();
        if (this.fichaCalendario.getCodigoExcepcion() == null) {
            this.fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
        }
        return "admin_funcionario_dia_trabajado_marcacion_ficha_general";
    }

    public String guardarDiaTrabajadoMarcacionFichaGeneral() {
        String retorno = "";
        if (this.diasBoolean != null) {
            String diaTrabajado = "Dia Trabajado: ";
            if (this.diasBoolean == 1) {
                this.fichaCalendario.setDiaTrabajado(Boolean.valueOf(true));
                diaTrabajado = diaTrabajado + "SI";
            } else {
                this.fichaCalendario.setDiaTrabajado(Boolean.valueOf(false));
                diaTrabajado = diaTrabajado + "NO";
            }
            this.ejbFichaCalendarioSB.udtade(this.fichaCalendario);
            String descriAudit = "Fecha: " + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + diaTrabajado;
            this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "ficha_calendario", this.fichaCalendario.getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
            this.diasBoolean = 0;
            retorno = this.redireccion();
            this.calcutarDiasTrabajados();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar valor para Cambio de estado", ""));
        }
        return retorno;
    }

    public String diaComplementarioFichaGeneral() {
        this.diasBoolean = null;
        this.fichaCalendario = new FichaCalendario();
        this.montoFijo = null;
        this.fichaCalendario = (FichaCalendario) this.getDataTableFichasCalendarios().getRowData();
        if (this.fichaCalendario.getCodigoExcepcion() == null) {
            this.fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
        }
        return "admin_funcionario_dia_complementario_marcacion_ficha_general";
    }

    public String guardarDiaComplementarioMarcacionFichaGeneral() {
        String retorno = "";
        if (this.diasBoolean != null) {
            String diaComplementario = "Dia Complementario: ";
            if (this.diasBoolean == 1) {
                this.fichaCalendario.setDiaComplementario(Boolean.valueOf(true));
                diaComplementario = diaComplementario + "SI";
            } else {
                this.fichaCalendario.setDiaComplementario(Boolean.valueOf(false));
                diaComplementario = diaComplementario + "NO";
            }
            this.ejbFichaCalendarioSB.udtade(this.fichaCalendario);
            String descriAudit = "Fecha: " + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + diaComplementario;
            this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "ficha_calendario", this.fichaCalendario.getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + this.fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
            this.diasBoolean = 0;
            retorno = this.redireccion();
            this.calcutarDiasComplementarios();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar valor para Cambio de estado", ""));
        }
        return retorno;
    }

    public String cancelarModificarFichaGeneral() {
        this.fichaCalendario = null;
        return this.redireccion();
    }

    public String redireccion() {
        List<FichaCalendario> listAux;
        String retorno;
        this.dataTableFichasCalendarios = new HtmlDataTable();
        if (this.funcionario.getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
            listAux = this.getFichaCalendarioJor(this.getCedulaFuncionario(), this.fechaDesde, this.fechaHasta);
            retorno = "admin_funcionario_ficha_general_jor";
        } else {
            listAux = this.getFichaCalendarioNom(this.getCedulaFuncionario(), this.numeroMes + 1, this.numeroAnio);
            retorno = "admin_funcionario_ficha_general_nom";
        }
        for (int i = 0; i < listAux.size(); ++i) {
            if (listAux.get(i).getPorcentajeMulta() != null && listAux.get(i).getPorcentajeMulta() > 0) {
                listAux.get(i).setPorcentajeMulta(this.fichasCalendarios.get(i).getPorcentajeMulta());
            }
            if (listAux.get(i).getMontoFijo() == null || listAux.get(i).getMontoFijo() <= 0) {
                continue;
            }
            listAux.get(i).setMontoFijo(this.fichasCalendarios.get(i).getMontoFijo());
        }
        this.fichasCalendarios.clear();
        this.fichasCalendarios = listAux;
        return retorno;
    }

    public void buscarFichaPorMesAnioCedulaNom() throws ParseException {
        int dias = this.cantidadDiasDelMes(this.numeroAnio, this.numeroMes);
        this.setFuncionario(this.getFuncionario(this.getCedulaFuncionario()));
        if (this.getFuncionario() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el funcionario", ""));
            this.prepararFormFichaGeneralMensualNom();
        } else if (this.getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El funcionario es Jornalero", ""));
            this.prepararFormFichaGeneralMensualNom();
        } else {
            List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiarios;
            this.mostrarComponentesPantallaBuscarRangoFechaCedula = true;
            Integer mesInt = this.numeroMes + 1;
            this.fichasCalendarios = this.getFichaCalendarioNom(this.getCedulaFuncionario(), mesInt, this.numeroAnio);
            this.activarComponentes = false;
            for (int k = 0; k < this.fichasCalendarios.size(); ++k) {
                if (!this.fichasCalendarios.get(k).getMarcaBloqueo().equals("SI")) {
                    continue;
                }
                this.activarComponentes = true;
            }
            this.fichaVerificada = "NO";
            if (this.fichasCalendarios.get(0).getMarcaFichaVerificada() != null && this.fichasCalendarios.get(0).getMarcaFichaVerificada().booleanValue()) {
                this.fichaVerificada = "SI";
            }
            if (!(asignacionExcepcionesHorariosDiarios = this.getAsignacionesHorariosPorFuncionarioNom(this.getCedulaFuncionario(), mesInt, this.numeroAnio)).isEmpty()) {
                for (int i = 0; i < this.getFichasCalendarios().size(); ++i) {
                    for (int j = 0; j < asignacionExcepcionesHorariosDiarios.size(); ++j) {
                        if (asignacionExcepcionesHorariosDiarios.get(j).getEstadoHorarioAsignado().getCodigo().equals("ACT")) {
                            Date fechaCal = this.getFichasCalendarios().get(i).getFichaCalendarioPK().getFechaCalendario();
                            if (!fechaCal.equals(asignacionExcepcionesHorariosDiarios.get(j).getAsignacionExcepcionesHorariosDiariosPK().getFechaAsignacion())) {
                                continue;
                            }
                            this.getFichasCalendarios().get(i).setHorarioAsignado(asignacionExcepcionesHorariosDiarios.get(j).getHorarioAsignado());
                            this.getFichasCalendarios().get(i).setMotivo(asignacionExcepcionesHorariosDiarios.get(j).getTiposExcepciones().getDescripcionExcepcion());
                            continue;
                        }
                        this.getFichasCalendarios().get(i).setHorarioAsignado(this.getFuncionario().getHorarioNormal());
                        this.getFichasCalendarios().get(i).setMotivo("");
                    }
                }
            }
            this.calcutarTotalPorcentaje();
            this.calcutarMotoFijoTotal();
            this.calcutarDiasTrabajados();
            this.calcutarDiasComplementarios();
        }
    }

    public String prepararFormFichaGeneralMensualJor() {
        this.dataTableFichasCalendarios = new HtmlDataTable();
        this.setMostrarComponentesPantallaBuscarRangoFechaCedula(false);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.numeroCiclo = cal.get(2) - 1;
        this.numeroAnio = cal.get(1);
        this.setPorcentajeMultaTotal(0);
        return "admin_funcionario_ficha_general_jor";
    }

    public void buscarFichaPorPeriodoCedulaJor() throws ParseException {
        this.setFuncionario(this.getFuncionario(this.getCedulaFuncionario()));
        if (this.getFuncionario() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el funcionario", ""));
            this.prepararFormFichaGeneralMensualJor();
        } else if (!this.getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El funcionario no es Jornalero", ""));
            this.prepararFormFichaGeneralMensualJor();
        } else {
            List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiarios;
            this.mostrarComponentesPantallaBuscarRangoFechaCedula = true;
            this.calcularPeriodo("JOR");
            this.fichasCalendarios = this.getFichaCalendarioJor(this.getCedulaFuncionario(), this.fechaDesde, this.fechaHasta);
            this.activarComponentes = false;
            for (int k = 0; k < this.fichasCalendarios.size(); ++k) {
                if (!this.fichasCalendarios.get(k).getMarcaBloqueo().equals("SI")) {
                    continue;
                }
                this.activarComponentes = true;
            }
            this.fichaVerificada = "NO";
            if (this.fichasCalendarios.get(0).getMarcaFichaVerificada() != null && this.fichasCalendarios.get(0).getMarcaFichaVerificada().booleanValue()) {
                this.fichaVerificada = "SI";
            }
            if (!(asignacionExcepcionesHorariosDiarios = this.getAsignacionesHorariosPorFuncionarioJor(this.getCedulaFuncionario(), this.fechaDesde, this.fechaHasta)).isEmpty()) {
                for (int i = 0; i < this.getFichasCalendarios().size(); ++i) {
                    for (int j = 0; j < asignacionExcepcionesHorariosDiarios.size(); ++j) {
                        if (asignacionExcepcionesHorariosDiarios.get(j).getEstadoHorarioAsignado().getCodigo().equals("ACT")) {
                            Date fechaCal = this.getFichasCalendarios().get(i).getFichaCalendarioPK().getFechaCalendario();
                            if (!fechaCal.equals(asignacionExcepcionesHorariosDiarios.get(j).getAsignacionExcepcionesHorariosDiariosPK().getFechaAsignacion())) {
                                continue;
                            }
                            this.getFichasCalendarios().get(i).setHorarioAsignado(asignacionExcepcionesHorariosDiarios.get(j).getHorarioAsignado());
                            this.getFichasCalendarios().get(i).setMotivo(asignacionExcepcionesHorariosDiarios.get(j).getTiposExcepciones().getDescripcionExcepcion());
                            continue;
                        }
                        this.getFichasCalendarios().get(i).setHorarioAsignado(this.getFuncionario().getHorarioNormal());
                        this.getFichasCalendarios().get(i).setMotivo("");
                    }
                }
            }
            this.calcutarTotalPorcentaje();
            this.calcutarMotoFijoTotal();
            this.calcutarDiasTrabajados();
            this.calcutarDiasComplementarios();
        }
    }

    public void calcularHorasExtras() {
        System.out.println("Hora Extra");
        System.out.println("Cedula|Apellidos, Nombres|Fecha|Horario Asignado|Hora Entrada|Hora Salida|Horas Extra");
        for (int i = 0; i < this.getFichasCalendarios().size(); ++i) {
            if (StringUtils.isBlank((String) String.valueOf(this.getFichasCalendarios().get(i).getAnioCalendarioNumero())) || String.valueOf(this.getFichasCalendarios().get(i).getAnioCalendarioNumero()).isEmpty()) {
                continue;
            }
            String anioAux = String.valueOf(this.getFichasCalendarios().get(i).getAnioCalendarioNumero());
            if (StringUtils.isBlank((String) String.valueOf(this.getFichasCalendarios().get(i).getMesCalendarioNumero())) || String.valueOf(this.getFichasCalendarios().get(i).getMesCalendarioNumero()).isEmpty()) {
                continue;
            }
            String mesAux = String.valueOf(this.getFichasCalendarios().get(i).getMesCalendarioNumero());
            if (StringUtils.isBlank((String) String.valueOf(this.getFichasCalendarios().get(i).getDiaCalendarioNumero())) || String.valueOf(this.getFichasCalendarios().get(i).getDiaCalendarioNumero()).isEmpty()) {
                continue;
            }
            String diaAux = String.valueOf(this.getFichasCalendarios().get(i).getDiaCalendarioNumero());
            if (StringUtils.isBlank((String) this.getFichasCalendarios().get(i).getHorarioAsignado().getHoraSalida()) || this.getFichasCalendarios().get(i).getHorarioAsignado().getHoraSalida().isEmpty() || this.getFichasCalendarios().get(i).getHorarioAsignado().getHorario().equals("00000000")) {
                continue;
            }
            String horaInicio = this.getFichasCalendarios().get(i).getHorarioAsignado().getHoraSalida();
            if (this.getFichasCalendarios().get(i).getHoraSalida() == null) {
                continue;
            }
            String horaFin = this.dateFormatHora.format(this.getFichasCalendarios().get(i).getHoraSalida());
            FechaHora f = new FechaHora();
            f.calcularHorasMinutosMismoDia(anioAux, mesAux, diaAux, horaInicio, horaFin);
            long hor = f.getMinutos() / 60;
            long rest = f.getMinutos() % 60;
            if ((double) f.getMinutos() < 0.0) {
                continue;
            }
            String h = "" + hor + "";
            if (hor < 10) {
                h = "0" + hor;
            }
            String m = "" + rest + "";
            if (rest < 10) {
                m = "0" + rest;
            }
            String result = this.getFichasCalendarios().get(i).getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "|";
            result = result + this.getFichasCalendarios().get(i).getFuncionarios().getApellidos() + ", " + this.getFichasCalendarios().get(i).getFuncionarios().getNombres() + "|";
            result = result + this.getDateFormatFecha().format(this.getFichasCalendarios().get(i).getFichaCalendarioPK().getFechaCalendario()) + "|";
            result = result + this.getFichasCalendarios().get(i).getHorarioAsignado().getDescripcion() + "|";
            result = result + this.formatearFechaToHora(this.getFichasCalendarios().get(i).getHoraEntrada()) + "|";
            result = result + this.formatearFechaToHora(this.getFichasCalendarios().get(i).getHoraSalida()) + "|";
            result = result + h + ":" + m;
            System.out.println(result);
            this.getFichasCalendarios().get(i).setHoraExtraTiempo(h + ":" + m);
            this.ejbFichaCalendarioSB.udtade(this.getFichasCalendarios().get(i));
        }
        this.calcutarTotalPorcentaje();
        this.calcutarMotoFijoTotal();
    }

    public void calcularMultas() {
        List listaEscalaMultas = this.escalaMultasSB.findByTipoEscala("PORCENTAJE");
        for (int i = 0; i < this.getFichasCalendarios().size(); ++i) {
            if (StringUtils.isBlank((String) String.valueOf(this.getFichasCalendarios().get(i).getAnioCalendarioNumero())) || String.valueOf(this.getFichasCalendarios().get(i).getAnioCalendarioNumero()).isEmpty()) {
                continue;
            }
            String anioAux = String.valueOf(this.getFichasCalendarios().get(i).getAnioCalendarioNumero());
            if (StringUtils.isBlank((String) String.valueOf(this.getFichasCalendarios().get(i).getMesCalendarioNumero())) || String.valueOf(this.getFichasCalendarios().get(i).getMesCalendarioNumero()).isEmpty()) {
                continue;
            }
            String mesAux = String.valueOf(this.getFichasCalendarios().get(i).getMesCalendarioNumero());
            if (StringUtils.isBlank((String) String.valueOf(this.getFichasCalendarios().get(i).getDiaCalendarioNumero())) || String.valueOf(this.getFichasCalendarios().get(i).getDiaCalendarioNumero()).isEmpty()) {
                continue;
            }
            String diaAux = String.valueOf(this.getFichasCalendarios().get(i).getDiaCalendarioNumero());
            if (StringUtils.isBlank((String) this.getFichasCalendarios().get(i).getHorarioAsignado().getHoraEntrada()) || this.getFichasCalendarios().get(i).getHorarioAsignado().getHoraEntrada().isEmpty() || this.getFichasCalendarios().get(i).getHorarioAsignado().getHorario().equals("00000000")) {
                continue;
            }
            String horaInicio = this.getFichasCalendarios().get(i).getHorarioAsignado().getHoraEntrada();
            if (this.getFichasCalendarios().get(i).getHoraEntrada() == null) {
                continue;
            }
            String horaFin = this.dateFormatHora.format(this.getFichasCalendarios().get(i).getHoraEntrada());
            FechaHora f = new FechaHora();
            f.calcularHorasMinutosMismoDia(anioAux, mesAux, diaAux, horaInicio, horaFin);
            long hor = f.getMinutos() / 60;
            long rest = f.getMinutos() % 60;
            if ((double) f.getMinutos() < 0.0) {
                continue;
            }
            String h = "";
            String m = "";
            Integer por = 0;
            for (int j = 0; j < listaEscalaMultas.size(); ++j) {
                if (f.getMinutos() >= (long) ((EscalaMultas) listaEscalaMultas.get(j)).getCantidadMinutosDesde()) {
                    h = "" + hor + "";
                    m = "" + rest + "";
                    por = ((EscalaMultas) listaEscalaMultas.get(j)).getPorcentajeMulta().intValue();
                }
                if (f.getMinutos() < (long) ((EscalaMultas) listaEscalaMultas.get(j)).getCantidadMinutosDesde() || f.getMinutos() > (long) ((EscalaMultas) listaEscalaMultas.get(j)).getCantidadMinutosHasta()) {
                    continue;
                }
                h = "" + hor + "";
                m = "" + rest + "";
                por = ((EscalaMultas) listaEscalaMultas.get(j)).getPorcentajeMulta().intValue();
            }
            if (por <= 0) {
                continue;
            }
            System.out.println("LLEGADA TARDIA");
            System.out.println("Fecha: " + this.getDateFormatFecha().format(this.getFichasCalendarios().get(i).getFichaCalendarioPK().getFechaCalendario()));
            System.out.println("Horas: " + h + " Minutos: " + m);
            System.out.println("PORCENTAJE: " + por);
            System.out.println("------");
            this.getFichasCalendarios().get(i).setPorcentajeMulta(por);
        }
        this.calcutarTotalPorcentaje();
        this.calcutarMotoFijoTotal();
    }

    public Integer cantidadDiasDelMes(Integer anio, Integer mes) {
        GregorianCalendar cal = new GregorianCalendar(anio, mes, 1);
        int days = cal.getActualMaximum(5);
        return days;
    }

    public long cantidadDiasDelPeriodo(Date fechaIni, Date fechaFin) {
        long milSecsPorDia = 86400000;
        long diferencia = (fechaFin.getTime() - fechaIni.getTime()) / milSecsPorDia;
        return diferencia;
    }

    private Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

    private void calcularPeriodo(String tipoFuncionario) throws ParseException {
        String mesHastaString;
        String mesDesdeString;
        String diaIni;
        String diaHas;
        Integer anioIni = this.getNumeroAnio();
        Integer anioFin = this.getNumeroAnio();
        if (tipoFuncionario.equals("JOR")) {
            diaIni = "11";
            diaHas = "10";
            Integer mesIni = this.getNumeroCiclo() + 1;
            Integer mesHas = mesIni + 1;
            mesDesdeString = mesIni + "";
            if (mesIni < 10) {
                mesDesdeString = "0" + mesIni;
            }
            mesHastaString = mesHas + "";
            if (mesHas < 10) {
                mesHastaString = "0" + mesHas;
            }
            if (mesIni.equals(12)) {
                mesHastaString = "01";
                anioFin = anioFin + 1;
            }
        } else {
            Integer mesIni;
            diaIni = "01";
            diaHas = this.cantidadDiasDelMes(this.numeroAnio, this.numeroMes) + "";
            Integer mesHas = mesIni = Integer.valueOf(this.getNumeroMes() + 1);
            mesDesdeString = mesIni + "";
            if (mesIni < 10) {
                mesDesdeString = "0" + mesIni;
            }
            mesHastaString = mesHas + "";
            if (mesHas < 10) {
                mesHastaString = "0" + mesHas;
            }
        }
        this.fechaDesde = this.getDateFormatFecha().parse(diaIni + "/" + mesDesdeString + "/" + anioIni);
        this.fechaHasta = this.getDateFormatFecha().parse(diaHas + "/" + mesHastaString + "/" + anioFin);
    }

    public Funcionarios recuperarUsuarioSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.setFuncionarioUsuario((Funcionarios) session.getAttribute("funcionarioUsuario"));
        return this.getFuncionarioUsuario();
    }

    public Funcionarios getFuncionario(String cedula) {
        Funcionarios funcionarioRetorno = this.ejbFuncionariosSB.findByNumeroDocumento(cedula);
        return funcionarioRetorno;
    }

    public List<FichaCalendario> getFichaCalendarioNom(String cedula, Integer mes, Integer anio) {
        List fichaCalendarioRetorno = this.ejbFichaCalendarioSB.findByCedulaMesAnio(cedula, mes, anio);
        return fichaCalendarioRetorno;
    }

    public List<FichaCalendario> getFichaCalendarioJor(String cedula, Date fechaIni, Date fechaFin) {
        List fichaCalendarioRetorno = this.ejbFichaCalendarioSB.findByCedulaFechaDesdeFechaHasta(cedula, fechaIni, fechaFin);
        return fichaCalendarioRetorno;
    }

    public List<AsignacionExcepcionesHorariosDiarios> getAsignacionesHorariosPorFuncionarioNom(String cedula, Integer mes, Integer anio) {
        List asignacionExcepcionesHorariosDiariosRetorno = this.ejbAsignacionExcepcionesHorariosDiariosSB.findByCedulaMesAnio(cedula, mes, anio);
        return asignacionExcepcionesHorariosDiariosRetorno;
    }

    public List<AsignacionExcepcionesHorariosDiarios> getAsignacionesHorariosPorFuncionarioJor(String cedula, Date fechaIni, Date fechaFin) {
        List asignacionExcepcionesHorariosDiariosRetorno = this.ejbAsignacionExcepcionesHorariosDiariosSB.findByCedulaFechaDesdeFechaHasta(cedula, fechaIni, fechaFin);
        return asignacionExcepcionesHorariosDiariosRetorno;
    }

    public void calcutarTotalPorcentaje() {
        this.porcentajeMultaTotal = 0;
        if (this.fichasCalendarios != null) {
            for (int i = 0; i < this.fichasCalendarios.size(); ++i) {
                if (this.fichasCalendarios.get(i).getPorcentajeMulta() == null) {
                    continue;
                }
                this.porcentajeMultaTotal = this.porcentajeMultaTotal + this.fichasCalendarios.get(i).getPorcentajeMulta();
            }
        }
    }

    public void calcutarMotoFijoTotal() {
        this.montoFijoTotal = 0;
        if (this.fichasCalendarios != null) {
            for (int i = 0; i < this.fichasCalendarios.size(); ++i) {
                if (this.fichasCalendarios.get(i).getMontoFijo() == null) {
                    continue;
                }
                this.montoFijoTotal = this.montoFijoTotal + this.fichasCalendarios.get(i).getMontoFijo();
            }
        }
    }

    public void calcutarDiasTrabajados() {
        this.diasTrabajados = 0;
        if (this.fichasCalendarios != null) {
            for (int i = 0; i < this.fichasCalendarios.size(); ++i) {
                if (this.fichasCalendarios.get(i).getDiaTrabajado() == null || !this.fichasCalendarios.get(i).getDiaTrabajado().booleanValue()) {
                    continue;
                }
                this.diasTrabajados = this.diasTrabajados + 1;
            }
        }
    }

    public void calcutarDiasComplementarios() {
        this.diasComplementarios = 0;
        if (this.fichasCalendarios != null) {
            for (int i = 0; i < this.fichasCalendarios.size(); ++i) {
                if (this.fichasCalendarios.get(i).getDiaComplementario() == null || !this.fichasCalendarios.get(i).getDiaComplementario().booleanValue()) {
                    continue;
                }
                this.diasComplementarios = this.diasComplementarios + 1;
            }
        }
    }

    public String formatearFechaToHora(Date fecha) {
        if (fecha == null) {
            return "";
        }
        return this.dateFormatHora.format(fecha);
    }

    public String formatearFecha(Date fecha) {
        if (fecha == null) {
            return "";
        }
        return this.getDateFormatFecha().format(fecha);
    }

    public Integer validarFicha() {
        Integer banderaError = 0;
        for (int i = 0; i < this.getFichasCalendarios().size(); ++i) {
            if (this.getFichasCalendarios().get(i).getHoraNoDefinida() == null) {
                continue;
            }
            banderaError = 1;
        }
        return banderaError;
    }

    public void exportarPDFporCedulaMesAnio() throws JRException, IOException, ParseException {
        String mesLetra;
        this.calcularHorasExtras();
        if (this.getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
            this.buscarFichaPorPeriodoCedulaJor();
        } else {
            this.buscarFichaPorMesAnioCedulaNom();
        }
        ReportesFichaCalendario reporte = new ReportesFichaCalendario();
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String urlImagen = ((ServletContext) ctx.getContext()).getRealPath("/resources/images/escudo.gif");
        String urlImagen2 = ((ServletContext) ctx.getContext()).getRealPath("/resources/images/asu128_trans.png");
        parametros.put("urlImagen", urlImagen);
        parametros.put("urlImagen2", urlImagen2);
        parametros.put("nombreDependencia", "Direcci\u00f3n de Recursos Humanos");
        parametros.put("mes", this.numeroMes);
        parametros.put("anio", this.numeroAnio);
        parametros.put("fechaGeneracion", new Date());
        parametros.put("cedulaFuncionario", this.getFuncionario().getNumeroDocumento());
        parametros.put("apellidoNombreFuncionario", this.getFuncionario().getApellidos() + ", " + this.getFuncionario().getNombres());
        parametros.put("nombreApellidoFuncionario", this.getFuncionario().getNombres() + " " + this.getFuncionario().getApellidos());
        parametros.put("totalRegistros", this.fichasCalendarios.size());
        parametros.put("usuarioGeneracion", this.funcionarioUsuario.getNombres() + " " + this.funcionarioUsuario.getApellidos());
        parametros.put("SUBREPORT_DIR", "py/gov/mca/sisrrhh/reportes/");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        if (this.getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
            mesLetra = this.listaCiclos.get(this.numeroCiclo);
            mesLetra = mesLetra.replace(" ", "");
            mesLetra = mesLetra.replace("-", "_");
        } else {
            mesLetra = this.listaMeses.get(this.numeroMes);
        }
        String repo = "ReporteMarcacionCedulaMesAnio";
        System.out.println("ROL: " + this.funcionarioUsuario.getRolUsuario().getCodigo());
        if (this.funcionarioUsuario.getRolUsuario().getCodigo().equals("ADM")) {
            repo = "ReporteMarcacionCedulaMesAnioHoraExtra";
        }
        reporte.exportarGenerico("pdf", ("marcacion_" + this.getFuncionario().getNumeroDocumento() + "_" + mesLetra + "_" + this.numeroAnio).toLowerCase(), response, parametros, this.fichasCalendarios, repo);
    }

    public void exportarCSVporCedula() throws IOException, JRException {
        String mesLetra;
        ReportesFichaCalendario reporte = new ReportesFichaCalendario();
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("fechaGeneracion", new Date());
        parametros.put("cedulaFuncionario", this.getFuncionario().getNumeroDocumento());
        parametros.put("apellidoNombreFuncionario", this.getFuncionario().getApellidos() + ", " + this.getFuncionario().getNombres());
        parametros.put("nombreApellidoFuncionario", this.getFuncionario().getNombres() + " " + this.getFuncionario().getApellidos());
        parametros.put("usuarioGeneracion", this.funcionarioUsuario.getNombres() + " " + this.funcionarioUsuario.getApellidos());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        if (this.getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
            mesLetra = this.listaCiclos.get(this.numeroCiclo);
            mesLetra = mesLetra.replace(" ", "");
            mesLetra = mesLetra.replace("-", "_");
        } else {
            mesLetra = this.listaMeses.get(this.numeroMes);
        }
        reporte.exportarGenerico("csv", ("marcacion_" + this.getFuncionario().getNumeroDocumento() + "_" + mesLetra + "_" + this.numeroAnio).toLowerCase(), response, parametros, this.fichasCalendarios, "ReporteMarcacionCedula");
    }

    public String prepararFormReporteRangoFecha() {
        this.setCantidadRegistros(0);
        this.setNombreArchivo("Ning\u00fan archivo seleccionado");
        return "admin_funcionario_reporte_rango_fecha";
    }

    public void leerArchivoCedulas() throws ParseException {
        block12:
        {
            this.setCantidadRegistros(0);
            this.setNombreArchivo("Ning\u00fan archivo seleccionado");
            try {
                String disposition = this.getFile().getHeader("content-disposition");
                this.setNombreArchivo(disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1"));
                String tipoArchivo = this.getFile().getContentType();
                if (this.getNombreArchivo().length() == 15) {
                    StringBuilder nombreArchivoCorto = new StringBuilder();
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(0));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(1));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(2));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(3));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(4));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(5));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(6));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(7));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(8));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(9));
                    nombreArchivoCorto.append(this.getNombreArchivo().charAt(10));
                    if (nombreArchivoCorto.toString().equals("cedulas_nom") || nombreArchivoCorto.toString().equals("cedulas_jor")) {
                        if (nombreArchivoCorto.toString().equals("cedulas_nom")) {
                            this.tipoReporte = "NOMBRADOS, CONTRATRADOS, ETC.";
                            this.tipoReporte2 = "nom";
                        } else {
                            this.tipoReporte = "JORNALEROS";
                            this.tipoReporte2 = "jor";
                        }
                        BufferedReader bf = null;
                        try {
                            bf = new BufferedReader(new InputStreamReader(this.getFile().getInputStream()));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        String line = null;
                        try {
                            this.cedulasDesdeArchivo = new ArrayList<String>();
                            boolean contadorLinea = false;
                            while ((line = bf.readLine()) != null) {
                                StringTokenizer tokens = new StringTokenizer(line, ";");
                                while (tokens.hasMoreTokens()) {
                                    this.cedulasDesdeArchivo.add(tokens.nextToken());
                                }
                            }
                            this.setCantidadRegistros(this.cedulasDesdeArchivo.size());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break block12;
                    }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre archivo no valido", ""));
                    break block12;
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Longitud de archivo no valida", ""));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exportarPDFporCedulaRangoFechas() throws JRException, IOException {
        int i;
        ReportesFuncionarioMarcacion reporte = new ReportesFuncionarioMarcacion();
        ArrayList<FuncionarioMarcacion> listaParaReporte = new ArrayList<FuncionarioMarcacion>();
        ArrayList<Funcionarios> funcionariosSegunArchivo = new ArrayList<Funcionarios>();
        for (i = 0; i < this.cedulasDesdeArchivo.size(); ++i) {
            Funcionarios func = this.ejbFuncionariosSB.findByNumeroDocumento(this.cedulasDesdeArchivo.get(i).trim());
            if (func == null) {
                continue;
            }
            funcionariosSegunArchivo.add(func);
        }
        for (i = 0; i < funcionariosSegunArchivo.size(); ++i) {
            List fichas = this.ejbFichaCalendarioSB.findByCedulaFechaDesdeFechaHastaConNull(((Funcionarios) funcionariosSegunArchivo.get(i)).getNumeroDocumento(), this.fechaDesde, this.fechaHasta);
            if (fichas == null) {
                continue;
            }
            FuncionarioMarcacion funMarAux = new FuncionarioMarcacion(((Funcionarios) funcionariosSegunArchivo.get(i)).getNumeroDocumento(), ((Funcionarios) funcionariosSegunArchivo.get(i)).getNombres(), ((Funcionarios) funcionariosSegunArchivo.get(i)).getApellidos(), ((Funcionarios) funcionariosSegunArchivo.get(i)).getRelacionLaboral().getDescripcion(), this.fechaDesde, this.fechaHasta, fichas);
            listaParaReporte.add(funMarAux);
        }
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String urlImagen = ((ServletContext) ctx.getContext()).getRealPath("/resources/images/escudo.gif");
        String urlImagen2 = ((ServletContext) ctx.getContext()).getRealPath("/resources/images/asu128_trans.png");
        parametros.put("urlImagen", urlImagen);
        parametros.put("urlImagen2", urlImagen2);
        parametros.put("nombreDependencia", "Direcci\u00f3n de Recursos Humanos");
        parametros.put("mes", this.numeroMes);
        parametros.put("anio", this.numeroAnio);
        parametros.put("fechaGeneracion", new Date());
        parametros.put("totalRegistros", listaParaReporte.size());
        parametros.put("usuarioGeneracion", this.funcionarioUsuario.getNombres() + " " + this.funcionarioUsuario.getApellidos());
        parametros.put("SUBREPORT_DIR", "py/gov/mca/sisrrhh/reportes/");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        reporte.exportarGenerico("pdf", new StringBuilder().append("marcacion_rango_fecha_").append(this.dateFormatFechaGuionBajo.format(this.fechaDesde)).append("_").append(this.dateFormatFechaGuionBajo.format(this.fechaHasta)).toString().toLowerCase() + "_" + this.tipoReporte2, response, parametros, listaParaReporte, "ReporteMarcacionCedulaConSubReport");
    }

    public String guardarFichaPorCedula() {
        String retorno;
        Integer banderaErrror = 0;
        for (int i = 0; i < this.getFichasCalendarios().size(); ++i) {
            if (this.getFichasCalendarios().get(i).getHoraNoDefinida() == null) {
                continue;
            }
            banderaErrror = 1;
        }
        if (banderaErrror == 0) {
            for (int i = 0; i < this.getFichasCalendarios().size(); ++i) {
                this.getFichasCalendarios().get(i).setMarcaCalculoPorcentaje(Integer.valueOf(1));
                this.getFichasCalendarios().get(i).setMarcaFichaVerificada(Boolean.valueOf(true));
                this.getFichasCalendarios().get(i).setMesCalendarioNumero(Integer.parseInt(this.dateFormatMesNumero.format(this.getFichasCalendarios().get(i).getFichaCalendarioPK().getFechaCalendario())));
                this.getFichasCalendarios().get(i).setAnioCalendarioNumero(Integer.parseInt(this.dateFormatAnioNumero.format(this.getFichasCalendarios().get(i).getFichaCalendarioPK().getFechaCalendario())));
                String descriAudit = "Fecha: " + this.getFichasCalendarios().get(i).getFichaCalendarioPK().getFechaCalendario() + ", PorcentajeMulta: " + this.getFichasCalendarios().get(i).getPorcentajeMulta();
                this.actualizarFichaCalendario(this.getFichasCalendarios().get(i));
                this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Insert o Update", "ficha_calendario", this.getFichasCalendarios().get(i).getFichaCalendarioPK().getNumeroDocumentoFuncionario(), descriAudit);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados", ""));
            retorno = this.funcionario.getRelacionLaboral().getRelacionLaboral().equals("JOR") ? this.prepararFormFichaGeneralMensualJor() : this.prepararFormFichaGeneralMensualNom();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen horarios no definidos", "La ficha no se puede guardar"));
            retorno = "";
        }
        return retorno;
    }

    public String prepararFormGenerarMultasNom() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.numeroMes = cal.get(2) - 1;
        this.numeroAnio = cal.get(1);
        return "admin_form_generar_multas_nom_csv";
    }

    public String prepararFormGenerarMultasJor() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.numeroCiclo = cal.get(2) - 1;
        this.numeroAnio = cal.get(1);
        return "admin_form_generar_multas_jor_csv";
    }

    public void generarMultasTotalesPorRangoDeFechaEnCsv(String tipoFuncionario) throws IOException, JRException, ParseException {
        this.calcularPeriodo(tipoFuncionario);
        ReportePorcentajeMultaCsv reporte = new ReportePorcentajeMultaCsv();
        ArrayList<MultaCsv> multas = new ArrayList<MultaCsv>();
        List listaFuncionariosActivos = this.ejbFuncionariosSB.findByEstadoRelacionLaboralFiltro("ACT", tipoFuncionario);
        for (int i = 0; i < listaFuncionariosActivos.size(); ++i) {
            Integer sueldoDiario = 0;
            Integer totalPorcentajeMulta = 0;
            Integer totalMontoFijo = 0;
            Integer totalDiasTrabajados = 0;
            Integer totalDiasComplementarios = 0;
            Integer totalMulta = 0;
            this.fichasCalendarios = this.ejbFichaCalendarioSB.findByCedulaFechaDesdeFechaHasta(((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento(), this.fechaDesde, this.fechaHasta);
            for (int j = 0; j < this.fichasCalendarios.size(); ++j) {
                if (this.fichasCalendarios.get(j).getPorcentajeMulta() != null) {
                    totalPorcentajeMulta = totalPorcentajeMulta + this.fichasCalendarios.get(j).getPorcentajeMulta();
                }
                if (this.fichasCalendarios.get(j).getMontoFijo() != null) {
                    totalMontoFijo = totalMontoFijo + this.fichasCalendarios.get(j).getMontoFijo();
                }
                if (this.fichasCalendarios.get(j).getDiaTrabajado() != null && this.fichasCalendarios.get(j).getDiaTrabajado().booleanValue()) {
                    totalDiasTrabajados = totalDiasTrabajados + 1;
                }
                if (this.fichasCalendarios.get(j).getDiaComplementario() != null && this.fichasCalendarios.get(j).getDiaComplementario().booleanValue()) {
                    totalDiasComplementarios = totalDiasComplementarios + 1;
                }
                this.fichasCalendarios.get(j).setMarcaBloqueo("SI");
            }
            if (((Funcionarios) listaFuncionariosActivos.get(i)).getSueldoDiario() == null) {
                continue;
            }
            sueldoDiario = ((Funcionarios) listaFuncionariosActivos.get(i)).getSueldoDiario().intValue();
            Double montoMultaDiaria = ((Funcionarios) listaFuncionariosActivos.get(i)).getSueldoDiario() * (double) totalPorcentajeMulta.intValue() / 100.0;
            montoMultaDiaria = Math.ceil(montoMultaDiaria);
            totalMulta = totalMulta + montoMultaDiaria.intValue() + totalMontoFijo;
            MultaCsv multa = new MultaCsv(((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento(), sueldoDiario, totalPorcentajeMulta, totalMontoFijo, totalDiasTrabajados, totalDiasComplementarios, totalMulta);
            multas.add(multa);
            this.guardarFichasCalendarios();
        }
        String nombreReporte = "ReporteMultasEnCvs";
        if (tipoFuncionario.equals("JOR")) {
            nombreReporte = "ReporteMultasEnCvsJor";
        }
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        reporte.exportarGenerico("csv", ("total_multas_" + this.dateFormatFechaGuionBajo.format(this.fechaDesde) + "_" + this.dateFormatFechaGuionBajo.format(this.fechaHasta) + "_" + tipoFuncionario).toLowerCase(), response, null, multas, nombreReporte);
    }

    public void actualizarFichaCalendario(FichaCalendario fichaCalendario) {
        this.ejbFichaCalendarioSB.udtade(fichaCalendario);
    }

    public Integer getNumeroMes() {
        return this.numeroMes;
    }

    public void setNumeroMes(Integer numeroMes) {
        this.numeroMes = numeroMes;
    }

    public Integer getNumeroAnio() {
        return this.numeroAnio;
    }

    public void setNumeroAnio(Integer numeroAnio) {
        this.numeroAnio = numeroAnio;
    }

    public List<FichaCalendario> getFichasCalendarios() {
        return this.fichasCalendarios;
    }

    public void setFichasCalendarios(List<FichaCalendario> fichasCalendarios) {
        this.fichasCalendarios = fichasCalendarios;
    }

    public Funcionarios getFuncionarioUsuario() {
        return this.funcionarioUsuario;
    }

    public void setFuncionarioUsuario(Funcionarios funcionarioUsuario) {
        this.funcionarioUsuario = funcionarioUsuario;
    }

    public LinkedHashMap<Integer, String> getListaMeses() {
        return this.listaMeses;
    }

    public void setListaMeses(LinkedHashMap<Integer, String> listaMeses) {
        this.listaMeses = listaMeses;
    }

    public LinkedHashMap<Integer, String> getListaCiclos() {
        return this.listaCiclos;
    }

    public void setListaCiclos(LinkedHashMap<Integer, String> listaCiclos) {
        this.listaCiclos = listaCiclos;
    }

    public Integer getNumeroCiclo() {
        return this.numeroCiclo;
    }

    public void setNumeroCiclo(Integer numeroCiclo) {
        this.numeroCiclo = numeroCiclo;
    }

    public String getTipoGeneracion() {
        return this.tipoGeneracion;
    }

    public void setTipoGeneracion(String tipoGeneracion) {
        this.tipoGeneracion = tipoGeneracion;
    }

    public boolean isMostrarComponentesPantallaBuscarRangoFechaCedula() {
        return this.mostrarComponentesPantallaBuscarRangoFechaCedula;
    }

    public void setMostrarComponentesPantallaBuscarRangoFechaCedula(boolean mostrarComponentesPantallaBuscarRangoFechaCedula) {
        this.mostrarComponentesPantallaBuscarRangoFechaCedula = mostrarComponentesPantallaBuscarRangoFechaCedula;
    }

    public Integer getPorcentajeMulta() {
        return this.porcentajeMulta;
    }

    public void setPorcentajeMulta(Integer porcentajeMulta) {
        this.porcentajeMulta = porcentajeMulta;
    }

    public Integer getPorcentajeMultaTotal() {
        return this.porcentajeMultaTotal;
    }

    public void setPorcentajeMultaTotal(Integer porcentajeMultaTotal) {
        this.porcentajeMultaTotal = porcentajeMultaTotal;
    }

    public String getCedulaFuncionario() {
        return this.cedulaFuncionario;
    }

    public void setCedulaFuncionario(String cedulaFuncionario) {
        this.cedulaFuncionario = cedulaFuncionario;
    }

    public Funcionarios getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Part getFile() {
        return this.file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int getCantidadRegistros() {
        return this.cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public List<String> getCedulasDesdeArchivo() {
        return this.cedulasDesdeArchivo;
    }

    public void setCedulasDesdeArchivo(List<String> cedulasDesdeArchivo) {
        this.cedulasDesdeArchivo = cedulasDesdeArchivo;
    }

    public Integer getMontoFijo() {
        return this.montoFijo;
    }

    public void setMontoFijo(Integer montoFijo) {
        this.montoFijo = montoFijo;
    }

    public Integer getMontoFijoTotal() {
        return this.montoFijoTotal;
    }

    public void setMontoFijoTotal(Integer montoFijoTotal) {
        this.montoFijoTotal = montoFijoTotal;
    }

    public Integer getDiasTrabajados() {
        return this.diasTrabajados;
    }

    public void setDiasTrabajados(Integer diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public Integer getDiasComplementarios() {
        return this.diasComplementarios;
    }

    public void setDiasComplementarios(Integer diasComplementarios) {
        this.diasComplementarios = diasComplementarios;
    }

    public boolean isActivarComponentes() {
        return this.activarComponentes;
    }

    public void setActivarComponentes(boolean activarComponentes) {
        this.activarComponentes = activarComponentes;
    }

    public HtmlDataTable getDataTableFichasCalendarios() {
        return this.dataTableFichasCalendarios;
    }

    public void setDataTableFichasCalendarios(HtmlDataTable dataTableFichasCalendarios) {
        this.dataTableFichasCalendarios = dataTableFichasCalendarios;
    }

    public FichaCalendario getFichaCalendario() {
        return this.fichaCalendario;
    }

    public void setFichaCalendario(FichaCalendario fichaCalendario) {
        this.fichaCalendario = fichaCalendario;
    }

    public SimpleDateFormat getDateFormatFecha() {
        return this.dateFormatFecha;
    }

    public List<Horarios> getHorarios() {
        this.horarios = this.horariosSB.findAll();
        return this.horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    public List<TiposExcepciones> getTiposExcepciones() {
        this.tiposExcepciones = this.tiposExcepcionesSB.findAll();
        return this.tiposExcepciones;
    }

    public void setTiposExcepciones(List<TiposExcepciones> tiposExcepciones) {
        this.tiposExcepciones = tiposExcepciones;
    }

    public LinkedHashMap<Integer, String> getListaTipoMarcacion() {
        return this.listaTipoMarcacion;
    }

    public void setListaTipoMarcacion(LinkedHashMap<Integer, String> listaTipoMarcacion) {
        this.listaTipoMarcacion = listaTipoMarcacion;
    }

    public Integer getNumeroTipoMarcacion() {
        return this.numeroTipoMarcacion;
    }

    public void setNumeroTipoMarcacion(Integer numeroTipoMarcacion) {
        this.numeroTipoMarcacion = numeroTipoMarcacion;
    }

    public String getFichaVerificada() {
        return this.fichaVerificada;
    }

    public void setFichaVerificada(String fichaVerificada) {
        this.fichaVerificada = fichaVerificada;
    }

    public Integer getDiasBoolean() {
        return this.diasBoolean;
    }

    public void setDiasBoolean(Integer diasBoolean) {
        this.diasBoolean = diasBoolean;
    }

    public LinkedHashMap<Integer, String> getListaSiNo() {
        return this.listaSiNo;
    }

    public void setListaSiNo(LinkedHashMap<Integer, String> listaSiNo) {
        this.listaSiNo = listaSiNo;
    }

    public String getTipoReporte() {
        return this.tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getTipoReporte2() {
        return this.tipoReporte2;
    }

    public void setTipoReporte2(String tipoReporte2) {
        this.tipoReporte2 = tipoReporte2;
    }
}
