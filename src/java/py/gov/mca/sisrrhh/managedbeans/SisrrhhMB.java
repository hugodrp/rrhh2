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
 *  javax.servlet.ServletOutputStream
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 *  javax.servlet.http.Part
 *  net.sf.jasperreports.engine.JRDataSource
 *  net.sf.jasperreports.engine.JRException
 *  net.sf.jasperreports.engine.JasperExportManager
 *  net.sf.jasperreports.engine.JasperFillManager
 *  net.sf.jasperreports.engine.JasperPrint
 *  net.sf.jasperreports.engine.JasperReport
 *  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
 *  net.sf.jasperreports.engine.util.JRLoader
 *  py.gov.mca.sisrrhh.clasesaux.MultaCsv
 *  py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios
 *  py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK
 *  py.gov.mca.sisrrhh.entitys.Auditoria
 *  py.gov.mca.sisrrhh.entitys.Estados
 *  py.gov.mca.sisrrhh.entitys.FechasEspeciales
 *  py.gov.mca.sisrrhh.entitys.FichaCalendario
 *  py.gov.mca.sisrrhh.entitys.FichaCalendarioPK
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
 *  py.gov.mca.sisrrhh.entitys.Horarios
 *  py.gov.mca.sisrrhh.entitys.Marcacion
 *  py.gov.mca.sisrrhh.entitys.MarcacionPK
 *  py.gov.mca.sisrrhh.entitys.Roles
 *  py.gov.mca.sisrrhh.entitys.VacacionesFuncionarios
 *  py.gov.mca.sisrrhh.entitys.VacacionesFuncionariosPK
 *  py.gov.mca.sisrrhh.negocio.ReportePorcentajeMultaCsv
 *  py.gov.mca.sisrrhh.negocio.ReportesFichaCalendario
 *  py.gov.mca.sisrrhh.sessionbeans.AsignacionExcepcionesHorariosDiariosSB
 *  py.gov.mca.sisrrhh.sessionbeans.AuditoriaSB
 *  py.gov.mca.sisrrhh.sessionbeans.FechasEspecialesSB
 *  py.gov.mca.sisrrhh.sessionbeans.FichaCalendarioSB
 *  py.gov.mca.sisrrhh.sessionbeans.FuncionariosSB
 *  py.gov.mca.sisrrhh.sessionbeans.MarcacionSB
 *  py.gov.mca.sisrrhh.sessionbeans.VacacionesFuncionariosSB
 *  py.gov.mca.sisrrhh.utiles.Converciones
 *  py.gov.mca.sisrrhh.utiles.MsgUtil
 */
package py.gov.mca.sisrrhh.managedbeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import py.gov.mca.sisrrhh.clasesaux.MultaCsv;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiarios;
import py.gov.mca.sisrrhh.entitys.AsignacionExcepcionesHorariosDiariosPK;
import py.gov.mca.sisrrhh.entitys.Auditoria;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.FechasEspeciales;
import py.gov.mca.sisrrhh.entitys.FichaCalendario;
import py.gov.mca.sisrrhh.entitys.FichaCalendarioPK;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.Horarios;
import py.gov.mca.sisrrhh.entitys.Marcacion;
import py.gov.mca.sisrrhh.entitys.MarcacionPK;
import py.gov.mca.sisrrhh.entitys.Roles;
import py.gov.mca.sisrrhh.entitys.VacacionesFuncionarios;
import py.gov.mca.sisrrhh.entitys.VacacionesFuncionariosPK;
import py.gov.mca.sisrrhh.negocio.ReportePorcentajeMultaCsv;
import py.gov.mca.sisrrhh.negocio.ReportesFichaCalendario;
import py.gov.mca.sisrrhh.sessionbeans.AsignacionExcepcionesHorariosDiariosSB;
import py.gov.mca.sisrrhh.sessionbeans.AuditoriaSB;
import py.gov.mca.sisrrhh.sessionbeans.FechasEspecialesSB;
import py.gov.mca.sisrrhh.sessionbeans.FichaCalendarioSB;
import py.gov.mca.sisrrhh.sessionbeans.FuncionariosSB;
import py.gov.mca.sisrrhh.sessionbeans.MarcacionSB;
import py.gov.mca.sisrrhh.sessionbeans.VacacionesFuncionariosSB;
import py.gov.mca.sisrrhh.utiles.Converciones;
import py.gov.mca.sisrrhh.utiles.MsgUtil;

@Named(value = "sisrrhhMB")
@SessionScoped
public class SisrrhhMB
        implements Serializable {

    @EJB
    private FuncionariosSB ejbFuncionariosSB;
    @EJB
    private MarcacionSB ejbMarcacionSB;
    @EJB
    private AuditoriaSB auditoriaSB;
    @EJB
    private AsignacionExcepcionesHorariosDiariosSB ejbAsignacionExcepcionesHorariosDiariosSB;
    @EJB
    private FichaCalendarioSB ejbFichaCalendarioSB;
    @EJB
    private VacacionesFuncionariosSB vacacionesFuncionariosSB;
    @EJB
    private FechasEspecialesSB ejbFechasEspecialesSB;
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private String telefono;
    private String cuentaCorriente;
    private String cedulaFuncionario;
    private String cedulaFuncionarioInicio;
    private String cedulaFuncionarioFin;
    private Date fechaDesde;
    private Date fechaHasta;
    private String loginUsuario;
    private String claveUsuario;
    private String contrasenaActual;
    private String contrasena1;
    private String contrasena2;
    private String linkExpediente;
    private List<Marcacion> listaMarcaciones;
    private List<Funcionarios> listaFuncionarios;
    private List<FichaCalendario> fichasCalendarios;
    private List<FichaCalendario> fichasCalendariosAux;
    private Funcionarios funcionarioUsuario;
    private Funcionarios funcionario;
    private Part file;
    private String nombreArchivo;
    private int cantidadRegistros;
    private int cantidadRegistrosError;
    private List<String> detallesErrores;
    private StringBuilder numeroReloj;
    private StringBuilder anio;
    private StringBuilder mes;
    private StringBuilder dia;
    private StringBuilder tipoFun;
    private String fechaArchivo;
    private String numeroRelojArchivo;
    private float progress;
    private final SimpleDateFormat dateFormatFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final SimpleDateFormat dateFormatFecha = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat dateFormatHora = new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat dateFormatDia = new SimpleDateFormat("EEEE", Locale.getDefault());
    private final SimpleDateFormat dateFormatMes = new SimpleDateFormat("MMMM", Locale.getDefault());
    private SimpleDateFormat dateFormatDiaNumero = new SimpleDateFormat("dd");
    private SimpleDateFormat dateFormatMesNumero = new SimpleDateFormat("MM");
    private SimpleDateFormat dateFormatAnioNumero = new SimpleDateFormat("yyyy");
    private LinkedHashMap<Integer, String> listaMeses = new LinkedHashMap();
    private boolean mostrarBotonSubirBd;
    private boolean activarBotonSubirBd;
    private boolean mostrarComponentesPantallaBuscarRangoFechaCedula;
    private Integer numeroMes;
    private Integer numeroAnio;
    private Integer porcentajeMulta;
    private Integer porcentajeMultaTotal;
    private Integer numeroFilaMarcacion;
    private Integer contadorMarcacion;
    private boolean mostrarComponentesVacaciones;
    private List<VacacionesFuncionarios> vacacionesFuncionarios;
    private Integer anioVacacion;
    private Integer diasCorrespondientesVacacion;
    private Integer diasRestantesVacacion;
    private Integer diasSolicitadoVacacion;
    private Integer diasRestantesTotalVacacion;
    private String cedulaFuncionarioVacaciones;
    private Funcionarios funcionarioVacaciones;
    private Date fechaDesdeFuncionarioTab1vacaciones;
    private Date fechaHastaFuncionarioTab1vacaciones;
    private List<Date> rangoDeFechaSeleccionado;
    private AsignacionExcepcionesHorariosDiarios asignacionExcepcionesHorariosDiariosVacaciones;
    private List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosVacacionesLista;
    private Integer totalDiasEntreFechasVacacion;
    private Integer totalDiasFeriadosVacacion;
    private Integer totalDiasDomingosVacacion;
    private Integer totalDiasYaAsignados;
    private HtmlDataTable dataTableVacaciones;

    public SisrrhhMB() {
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
    }

    @PostConstruct
    public void init() {
        this.recuperarUsuarioSession();
        this.loginUsuario = "";
        this.claveUsuario = "";
    }

    public String btnIngresar() {
        if (this.getLoginUsuario() != null) {
            if (this.getLoginUsuario().equals("") || this.getClaveUsuario().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los campos con (*) no pueden estar vacio.", ""));
                return "/index";
            }
            this.setFuncionarioUsuario(null);
            this.setFuncionarioUsuario(this.ejbFuncionariosSB.findByUsuario(this.getLoginUsuario().trim()));
            if (this.getFuncionarioUsuario() != null) {
                Converciones c = new Converciones();
                String contrasenaMD5 = c.getMD5(this.getClaveUsuario());
                if (contrasenaMD5 == null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo ingresar, intentelo de nuevo.", ""));
                    return "/index";
                }
                if (this.getFuncionarioUsuario().getContrasena().equals(this.getClaveUsuario()) && this.getFuncionarioUsuario().getEstadoFuncionario().getCodigo().equals("ACT")) {
                    HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("loginUsuario", (Object) this.getLoginUsuario());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido!", ""));
                    return "/admin_panel_principal";
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contrase\u00f1a no validos, intentelo de nuevo.", ""));
                return "/index";
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no exite, intentelo de nuevo.", ""));
            return "/index";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los campos con null.", ""));
        return "/index";
    }

    public String btnSalir() {
        this.funcionarioUsuario = null;
        this.funcionario = null;
        this.loginUsuario = null;
        this.claveUsuario = null;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/index";
    }

    public String prepararFormCargaDedeArchivoAudi() {
        this.cantidadRegistros = 0;
        this.cantidadRegistrosError = 0;
        this.setNombreArchivo("Ning\u00fan archivo seleccionado");
        this.setNumeroReloj(new StringBuilder());
        this.setAnio(new StringBuilder());
        this.setMes(new StringBuilder());
        this.setDia(new StringBuilder());
        this.setFechaArchivo("DD/MM/AAAA");
        this.setNumeroRelojArchivo("0");
        this.mostrarBotonSubirBd = false;
        this.activarBotonSubirBd = true;
        return "admin_form_carga_desde_archivo_audi";
    }

    public void leerArchivoAudi() {
        block25:
        {
            this.cantidadRegistros = 0;
            this.cantidadRegistrosError = 0;
            this.setNumeroReloj(new StringBuilder());
            this.setAnio(new StringBuilder());
            this.setMes(new StringBuilder());
            this.setDia(new StringBuilder());
            this.setNombreArchivo("Ning\u00fan archivo seleccionado");
            this.setFechaArchivo("DD/MM/AAAA");
            this.setNumeroRelojArchivo("0");
            try {
                String disposition = this.file.getHeader("content-disposition");
                this.setNombreArchivo(disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1"));
                String tipoArchivo = this.file.getContentType();
                if (this.getNombreArchivo().length() == 8) {
                    if (tipoArchivo.equals("text/csv")) {
                        StringBuilder nombreArchivoCorto = new StringBuilder();
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(0));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(1));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(2));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(3));
                        if (nombreArchivoCorto.toString().equals("audi")) {
                            try {
                                BufferedReader bf = new BufferedReader(new InputStreamReader(this.file.getInputStream()));
                                try {
                                    String line;
                                    this.listaMarcaciones = new ArrayList<Marcacion>();
                                    this.detallesErrores = new ArrayList<String>();
                                    int contadorLinea = 0;
                                    int contadorErrorLectura = 0;
                                    while ((line = bf.readLine()) != null) {
                                        if (contadorLinea > 0) {
                                            StringTokenizer tokens = new StringTokenizer(line, ";");
                                            while (tokens.hasMoreTokens()) {
                                                String fechaAudi = tokens.nextToken();
                                                String cedulaAudi = tokens.nextToken();
                                                String tipoAudi = tokens.nextToken();
                                                String valorAudi = tokens.nextToken();
                                                try {
                                                    Date fchAu = this.dateFormatFecha.parse(fechaAudi);
                                                    FichaCalendario fichaAux = this.ejbFichaCalendarioSB.findByFechaCalendarioNumeroDocumentoFuncionario(fchAu, cedulaAudi);
                                                    try {
                                                        fichaAux.setFichaCalendarioPK(new FichaCalendarioPK());
                                                        fichaAux.getFichaCalendarioPK().setFechaCalendario(this.dateFormatFecha.parse(fechaAudi));
                                                        fichaAux.getFichaCalendarioPK().setNumeroDocumentoFuncionario(cedulaAudi);
                                                        switch (tipoAudi) {
                                                            case "montoFijoMulta": {
                                                                fichaAux.setMontoFijo(Integer.valueOf(Integer.parseInt(valorAudi)));
                                                                break;
                                                            }
                                                            case "porcentajeMulta": {
                                                                fichaAux.setPorcentajeMulta(Integer.valueOf(Integer.parseInt(valorAudi)));
                                                                break;
                                                            }
                                                        }
                                                        System.out.println("fecha: " + fichaAux.getFichaCalendarioPK().getFechaCalendario());
                                                        System.out.println("cedula: " + fichaAux.getFichaCalendarioPK().getNumeroDocumentoFuncionario());
                                                        System.out.println("montoFijoMulta: " + fichaAux.getMontoFijo());
                                                        System.out.println("porcentajeMulta: " + fichaAux.getPorcentajeMulta());
                                                        this.ejbFichaCalendarioSB.udtade(fichaAux);
                                                    } catch (ParseException ex) {
                                                        ++contadorErrorLectura;
                                                        Logger.getLogger(SisrrhhMB.class.getName()).log(Level.SEVERE, null, ex);
                                                        String errorLec = "Cedula: " + fichaAux.getFichaCalendarioPK().getNumeroDocumentoFuncionario() + ", Fecha: " + fechaAudi;
                                                        this.detallesErrores.add(errorLec);
                                                        MsgUtil.msg((String) "No se puede convertir a fecha", (String) errorLec, (String) "ERROR");
                                                    }
                                                } catch (ParseException ex) {
                                                    Logger.getLogger(SisrrhhMB.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        }
                                        ++contadorLinea;
                                    }
                                    this.cantidadRegistros = this.listaMarcaciones.size() - contadorErrorLectura;
                                    this.cantidadRegistrosError = contadorErrorLectura;
                                    if (contadorErrorLectura == 0) {
                                        this.mostrarBotonSubirBd = true;
                                        this.activarBotonSubirBd = false;
                                    }
                                    break block25;
                                } catch (IOException e) {
                                    String errorLec = "IOException1: " + e.getMessage();
                                    this.detallesErrores.add(errorLec);
                                    MsgUtil.msg((String) "Error en archivo", (String) errorLec, (String) "ERROR");
                                }
                            } catch (FileNotFoundException e) {
                                String errorLec = "FileNotFoundException: " + e.getMessage();
                                this.detallesErrores.add(errorLec);
                                MsgUtil.msg((String) "Error en archivo", (String) errorLec, (String) "ERROR");
                            }
                            break block25;
                        }
                        this.mostrarBotonSubirBd = false;
                        this.activarBotonSubirBd = true;
                        MsgUtil.msg((String) "Nombre archivo no valido", (String) "", (String) "ERROR");
                        break block25;
                    }
                    this.mostrarBotonSubirBd = false;
                    this.activarBotonSubirBd = true;
                    MsgUtil.msg((String) "Tipo archivo no valido", (String) "Extenci\u00f3n debe ser .csv", (String) "ERROR");
                    break block25;
                }
                this.mostrarBotonSubirBd = false;
                this.activarBotonSubirBd = true;
                MsgUtil.msg((String) "Longitud del nombre de archivo no valido", (String) "Extenci\u00f3n debe ser 23 caracteres", (String) "ERROR");
            } catch (IOException e) {
                this.mostrarBotonSubirBd = false;
                this.activarBotonSubirBd = true;
                String errorLec = "IOException2: " + e.getMessage();
                this.detallesErrores.add(errorLec);
                MsgUtil.msg((String) "Error en archivo", (String) errorLec, (String) "ERROR");
            }
        }
    }

    public String prepararFormCargaDedeArchivoAsistencias() {
        this.cantidadRegistros = 0;
        this.cantidadRegistrosError = 0;
        this.setNombreArchivo("Ning\u00fan archivo seleccionado");
        this.setNumeroReloj(new StringBuilder());
        this.setAnio(new StringBuilder());
        this.setMes(new StringBuilder());
        this.setDia(new StringBuilder());
        this.setFechaArchivo("DD/MM/AAAA");
        this.setNumeroRelojArchivo("0");
        this.mostrarBotonSubirBd = false;
        this.activarBotonSubirBd = true;
        return "admin_form_carga_desde_archivo_asistencias";
    }

    public void leerArchivoAsistencias() {
        block15:
        {
            this.cantidadRegistros = 0;
            this.cantidadRegistrosError = 0;
            this.setNumeroReloj(new StringBuilder());
            this.setAnio(new StringBuilder());
            this.setMes(new StringBuilder());
            this.setDia(new StringBuilder());
            this.setNombreArchivo("Ning\u00fan archivo seleccionado");
            this.setFechaArchivo("DD/MM/AAAA");
            this.setNumeroRelojArchivo("0");
            try {
                String disposition = this.file.getHeader("content-disposition");
                this.setNombreArchivo(disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1"));
                String tipoArchivo = this.file.getContentType();
                if (this.getNombreArchivo().length() == 23) {
                    if (tipoArchivo.equals("text/csv")) {
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
                        if (nombreArchivoCorto.toString().equals("asistencia")) {
                            this.getNumeroReloj().append(this.getNombreArchivo().charAt(10));
                            this.getAnio().append(this.getNombreArchivo().charAt(11));
                            this.getAnio().append(this.getNombreArchivo().charAt(12));
                            this.getAnio().append(this.getNombreArchivo().charAt(13));
                            this.getAnio().append(this.getNombreArchivo().charAt(14));
                            this.getMes().append(this.getNombreArchivo().charAt(15));
                            this.getMes().append(this.getNombreArchivo().charAt(16));
                            this.getDia().append(this.getNombreArchivo().charAt(17));
                            this.getDia().append(this.getNombreArchivo().charAt(18));
                            this.setFechaArchivo(this.getDia() + "/" + this.getMes() + "/" + this.getAnio());
                            this.setNumeroRelojArchivo(this.getNumeroReloj().toString());
                            try {
                                BufferedReader bf = new BufferedReader(new InputStreamReader(this.file.getInputStream()));
                                try {
                                    String line;
                                    this.listaMarcaciones = new ArrayList<Marcacion>();
                                    this.detallesErrores = new ArrayList<String>();
                                    int contadorLinea = 0;
                                    int contadorErrorLectura = 0;
                                    while ((line = bf.readLine()) != null) {
                                        if (contadorLinea > 0) {
                                            StringTokenizer tokens = new StringTokenizer(line, ";");
                                            while (tokens.hasMoreTokens()) {
                                                String no = tokens.nextToken();
                                                String fechaHora = tokens.nextToken();
                                                String vericicaCod = tokens.nextToken();
                                                Marcacion marcacion = new Marcacion();
                                                try {
                                                    marcacion.setMarcacionPK(new MarcacionPK());
                                                    marcacion.getMarcacionPK().setCedula(no);
                                                    marcacion.getMarcacionPK().setFechaHoraMarcacion(this.dateFormatFechaHora.parse(fechaHora));
                                                    marcacion.getMarcacionPK().setTipoMacacion(vericicaCod.toUpperCase().trim());
                                                    marcacion.setFechaMarcacionChar(marcacion.getMarcacionPK().getFechaHoraMarcacion());
                                                    marcacion.setHoraMarcacionChar(marcacion.getMarcacionPK().getFechaHoraMarcacion());
                                                    marcacion.setDiaMarcacion(this.dateFormatDia.format(marcacion.getMarcacionPK().getFechaHoraMarcacion()).toUpperCase());
                                                    marcacion.setFormaMarcacion("");
                                                    marcacion.setNombreArchivo(this.getNombreArchivo());
                                                    this.listaMarcaciones.add(marcacion);
                                                } catch (ParseException ex) {
                                                    ++contadorErrorLectura;
                                                    Logger.getLogger(SisrrhhMB.class.getName()).log(Level.SEVERE, null, ex);
                                                    String errorLec = "Cedula: " + marcacion.getMarcacionPK().getCedula() + ", Fecha: " + fechaHora;
                                                    this.detallesErrores.add(errorLec);
                                                    MsgUtil.msg((String) "No se puede convertir a fecha", (String) errorLec, (String) "ERROR");
                                                }
                                            }
                                        }
                                        ++contadorLinea;
                                    }
                                    this.cantidadRegistros = this.listaMarcaciones.size() - contadorErrorLectura;
                                    this.cantidadRegistrosError = contadorErrorLectura;
                                    if (contadorErrorLectura == 0) {
                                        this.mostrarBotonSubirBd = true;
                                        this.activarBotonSubirBd = false;
                                    }
                                    break block15;
                                } catch (IOException e) {
                                    String errorLec = "IOException1: " + e.getMessage();
                                    this.detallesErrores.add(errorLec);
                                    MsgUtil.msg((String) "Error en archivo", (String) errorLec, (String) "ERROR");
                                }
                            } catch (FileNotFoundException e) {
                                String errorLec = "FileNotFoundException: " + e.getMessage();
                                this.detallesErrores.add(errorLec);
                                MsgUtil.msg((String) "Error en archivo", (String) errorLec, (String) "ERROR");
                            }
                            break block15;
                        }
                        this.mostrarBotonSubirBd = false;
                        this.activarBotonSubirBd = true;
                        MsgUtil.msg((String) "Nombre archivo no valido", (String) "", (String) "ERROR");
                        break block15;
                    }
                    this.mostrarBotonSubirBd = false;
                    this.activarBotonSubirBd = true;
                    MsgUtil.msg((String) "Tipo archivo no valido", (String) "Extenci\u00f3n debe ser .csv", (String) "ERROR");
                    break block15;
                }
                this.mostrarBotonSubirBd = false;
                this.activarBotonSubirBd = true;
                MsgUtil.msg((String) "Longitud del nombre de archivo no valido", (String) "Extenci\u00f3n debe ser 23 caracteres", (String) "ERROR");
            } catch (IOException e) {
                this.mostrarBotonSubirBd = false;
                this.activarBotonSubirBd = true;
                String errorLec = "IOException2: " + e.getMessage();
                this.detallesErrores.add(errorLec);
                MsgUtil.msg((String) "Error en archivo", (String) errorLec, (String) "ERROR");
            }
        }
    }

    public void guardarDesdeArchivoAsistencias() {
        if (this.listaMarcaciones != null) {
            this.activarBotonSubirBd = true;
            this.progress = this.listaMarcaciones.size() / 2 * 100 / this.listaMarcaciones.size();
            int i = 0;
            this.contadorMarcacion = 0;
            for (Marcacion mar : this.listaMarcaciones) {
                this.contadorMarcacion = this.contadorMarcacion + 1;
                this.ejbMarcacionSB.insert(mar);
                if (++i % 1000 == 0) {
                    this.ejbMarcacionSB.flushAndClear();
                }
                this.progress = i * 100 / this.listaMarcaciones.size();
            }
            this.ejbMarcacionSB.flushAndClear();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione otro archivo", ""));
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
        return this.dateFormatFecha.format(fecha);
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

    public String prepararFormBuscarPorCedulaFechaDesdeFechaHasta() {
        this.mostrarComponentesPantallaBuscarRangoFechaCedula = false;
        return "admin_form_reporte_rango_fecha_cedula";
    }

    public String prepararFormBuscarPorRangoCedulaFechaDesdeFechaHasta() {
        this.mostrarComponentesPantallaBuscarRangoFechaCedula = false;
        return "admin_form_reporte_rango_fecha_rango_cedula";
    }

    public void exportarPDFporRangoCedulaFechaDesdeFechaHasta() throws JRException, IOException {
        List listaFuncionariosAux = this.ejbFuncionariosSB.findByRangoCedula(this.cedulaFuncionarioInicio, this.cedulaFuncionarioFin);
        for (int i = 0; i < listaFuncionariosAux.size(); ++i) {
            ((Funcionarios) listaFuncionariosAux.get(i)).setFichaCalendarioList(this.getMarcacionesPorCedulaFechaDedeFechaHasta(((Funcionarios) listaFuncionariosAux.get(i)).getNumeroDocumento(), this.fechaDesde, this.fechaHasta));
        }
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String urlImagen = ((ServletContext) ctx.getContext()).getRealPath("/resources/images/escudo.gif");
        String urlImagen2 = ((ServletContext) ctx.getContext()).getRealPath("/resources/images/asu128_trans.png");
        parametros.put("urlImagen", urlImagen);
        parametros.put("urlImagen2", urlImagen2);
        parametros.put("nombreDependencia", "Direcci\u00f3n de Recursos Humanos");
        parametros.put("fechaDesde", this.getFechaDesde());
        parametros.put("fechaHasta", this.getFechaHasta());
        parametros.put("fechaGeneracion", new Date());
        parametros.put("cedulaFuncionario", this.getFuncionario().getNumeroDocumento());
        parametros.put("apellidoNombreFuncionario", this.getFuncionario().getApellidos() + ", " + this.getFuncionario().getNombres());
        parametros.put("nombreApellidoFuncionario", this.getFuncionario().getNombres() + " " + this.getFuncionario().getApellidos());
        parametros.put("totalRegistros", listaFuncionariosAux.size());
        parametros.put("usuarioGeneracion", this.funcionarioUsuario.getNombres() + " " + this.funcionarioUsuario.getApellidos());
        parametros.put("SUBREPORT_DIR", "py/gov/mca/sisrrhh/reportes/");
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource((Collection) listaFuncionariosAux);
        JasperReport jasper = (JasperReport) JRLoader.loadObject((InputStream) this.getClass().getClassLoader().getResourceAsStream("py/gov/mca/sisrrhh/reportes/ReporteMarcacionCedulaFechaDesdeFechaHasta.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport((JasperReport) jasper, parametros, (JRDataSource) beanCollectionDataSource);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=marcacion_" + this.getFuncionario().getNumeroDocumento() + "_" + this.dateFormatMes.format(this.getFechaDesde()) + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream((JasperPrint) jasperPrint, (OutputStream) stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void buscarPorCedulaFechaDesdeFechaHasta() {
        this.funcionario = this.getFuncionario(this.getCedulaFuncionario());
        this.fichasCalendarios = this.getMarcacionesPorCedulaFechaDedeFechaHasta(this.getCedulaFuncionario(), this.getFechaDesde(), this.getFechaHasta());
        this.mostrarComponentesPantallaBuscarRangoFechaCedula = true;
    }

    public void exportarPDFporCedulaFechaDesdeFechaHasta() throws JRException, IOException {
        System.out.println("exportarPDFporCedulaFechaDesdeFechaHasta()");
        ReportesFichaCalendario reporte = new ReportesFichaCalendario();
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String urlImagen = ((ServletContext) ctx.getContext()).getRealPath("/resources/images/escudo.gif");
        String urlImagen2 = ((ServletContext) ctx.getContext()).getRealPath("/resources/images/asu128_trans.png");
        parametros.put("urlImagen", urlImagen);
        parametros.put("urlImagen2", urlImagen2);
        parametros.put("nombreDependencia", "Direcci\u00f3n de Recursos Humanos");
        parametros.put("fechaDesde", this.getFechaDesde());
        parametros.put("fechaHasta", this.getFechaHasta());
        parametros.put("fechaGeneracion", new Date());
        parametros.put("cedulaFuncionario", this.getFuncionario().getNumeroDocumento());
        parametros.put("apellidoNombreFuncionario", this.getFuncionario().getApellidos() + ", " + this.getFuncionario().getNombres());
        parametros.put("nombreApellidoFuncionario", this.getFuncionario().getNombres() + " " + this.getFuncionario().getApellidos());
        parametros.put("totalRegistros", this.fichasCalendarios.size());
        parametros.put("usuarioGeneracion", this.funcionarioUsuario.getNombres() + " " + this.funcionarioUsuario.getApellidos());
        parametros.put("SUBREPORT_DIR", "py/gov/mca/sisrrhh/reportes/");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        reporte.exportarGenerico("pdf", "marcacion_" + this.getFuncionario().getNumeroDocumento() + "_" + this.dateFormatMes.format(this.getFechaDesde()), response, parametros, this.getFichasCalendarios(), "ReporteMarcacionCedulaFechaDesdeFechaHasta");
    }

    public void exportarPDFporCedulaMesAnio() throws JRException, IOException {
        System.out.println("exportarPDFporCedulaFechaDesdeFechaHasta()");
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
        String mesLetra = this.listaMeses.get(this.numeroMes);
        reporte.exportarGenerico("pdf", ("marcacion_" + this.getFuncionario().getNumeroDocumento() + "_" + mesLetra + "_" + this.numeroAnio).toLowerCase(), response, parametros, this.fichasCalendarios, "ReporteMarcacionCedulaMesAnio");
    }

    public void generarCsvPorcentajeMultasTotalesPorRangoDeFecha() throws IOException, JRException {
        ReportePorcentajeMultaCsv reporte = new ReportePorcentajeMultaCsv();
        ArrayList<MultaCsv> multas = new ArrayList<MultaCsv>();
        List listaFuncionariosActivos = this.ejbFuncionariosSB.findByEstado("ACT");
        for (int i = 0; i < listaFuncionariosActivos.size(); ++i) {
            Integer sueldoDiario = 0;
            Integer totalPorcentajeMulta = 0;
            Integer totalMontoFijo = 0;
            Integer totalDiasTrabajados = 0;
            Integer totalDiasComplementarios = 0;
            Integer totalMulta = 0;
            List fichasCalendariosAux2 = this.ejbFichaCalendarioSB.findByCedulaFechaDesdeFechaHasta(((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento(), this.fechaDesde, this.fechaHasta);
            for (int j = 0; j < fichasCalendariosAux2.size(); ++j) {
                if (((FichaCalendario) fichasCalendariosAux2.get(j)).getPorcentajeMulta() != null) {
                    totalPorcentajeMulta = totalPorcentajeMulta + ((FichaCalendario) fichasCalendariosAux2.get(j)).getPorcentajeMulta();
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
            if (((Funcionarios) listaFuncionariosActivos.get(i)).getSueldoDiario() != null) {
                sueldoDiario = ((Funcionarios) listaFuncionariosActivos.get(i)).getSueldoDiario().intValue();
                Double montoMultaDiaria = ((Funcionarios) listaFuncionariosActivos.get(i)).getSueldoDiario() * (double) totalPorcentajeMulta.intValue() / 100.0;
                montoMultaDiaria = Math.ceil(montoMultaDiaria);
                totalMulta = totalMulta + montoMultaDiaria.intValue() + totalMontoFijo;
            } else {
                System.out.println("Cedula: " + ((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento() + ", Sin salario diario ");
            }
            System.out.println("Cedula: " + ((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento() + ", Jornal: " + sueldoDiario + ", Total Pocentaje: " + totalPorcentajeMulta + "%, Total Monto Fijo: " + totalMontoFijo + ", Total Dias Trabajados: " + totalDiasTrabajados + ", Total Dias Complementarios: " + totalDiasComplementarios + ", Total Multa:  " + totalMulta);
            MultaCsv multa = new MultaCsv(((Funcionarios) listaFuncionariosActivos.get(i)).getNumeroDocumento(), sueldoDiario, totalPorcentajeMulta, totalMontoFijo, totalDiasTrabajados, totalDiasComplementarios, totalMulta);
            multas.add(multa);
        }
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        reporte.exportarGenerico("csv", "porcentaje_total_", response, null, multas, "ReportePorcentajeMultasEnCvs");
    }

    public void onComplete() {
        this.progress = 0.0f;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros guardados " + this.cantidadRegistros, ""));
    }

    public String guardarFichaPorMesAnioCedula() {
        for (int i = 0; i < this.getFichasCalendarios().size(); ++i) {
            this.getFichasCalendarios().get(i).setMarcaCalculoPorcentaje(Integer.valueOf(1));
            this.getFichasCalendarios().get(i).setMesCalendarioNumero(this.numeroMes + 1);
            this.getFichasCalendarios().get(i).setAnioCalendarioNumero(this.numeroAnio.intValue());
            String descriAudit = "Fecha: " + this.getFichasCalendarios().get(i).getFichaCalendarioPK().getFechaCalendario() + ", Monto Porcentaje: " + this.getFichasCalendarios().get(i).getPorcentajeMulta();
            this.actualizarFichaCalendario(this.getFichasCalendarios().get(i));
            this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Insert o Update", "ficha_calendario", this.getFichasCalendarios().get(i).getFichaCalendarioPK().getNumeroDocumentoFuncionario(), descriAudit);
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados", ""));
        return "admin_funcionario_ficha_general";
    }

    public void actualizarFichaCalendario(FichaCalendario fichaCalendario) {
        this.ejbFichaCalendarioSB.udtade(fichaCalendario);
    }

    public String prepararFormPorcentaje(Integer porcentaje, Integer numeroFila) {
        this.porcentajeMulta = porcentaje;
        this.numeroFilaMarcacion = numeroFila;
        return "admin_form_porcentaje";
    }

    public String guardarPorcentajeEnFila() {
        for (int i = 0; i < this.getFichasCalendarios().size(); ++i) {
            if (!this.numeroFilaMarcacion.equals(this.getFichasCalendarios().get(i).getDiaCalendarioNumero())) {
                continue;
            }
            this.getFichasCalendarios().get(i).setPorcentajeMulta(this.porcentajeMulta);
        }
        return "admin_funcionario_ficha_general";
    }

    public String prepararFormulariosVacaciones(String pagina) {
        this.funcionarioVacaciones = null;
        this.cedulaFuncionarioVacaciones = "";
        this.anioVacacion = 0;
        this.diasCorrespondientesVacacion = 0;
        this.diasRestantesVacacion = 0;
        this.diasSolicitadoVacacion = 0;
        this.diasRestantesTotalVacacion = 0;
        this.totalDiasEntreFechasVacacion = 0;
        this.totalDiasDomingosVacacion = 0;
        this.totalDiasFeriadosVacacion = 0;
        this.totalDiasYaAsignados = 0;
        this.mostrarComponentesVacaciones = false;
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        this.numeroMes = cal.get(2);
        this.numeroAnio = cal.get(1);
        this.dataTableVacaciones = new HtmlDataTable();
        String pag = "";
        switch (pagina) {
            case "form_pedido": {
                pag = "admin_funcionario_vacaciones_form_pedido";
                break;
            }
            case "form_modificacion": {
                pag = "admin_funcionario_vacaciones_form_modificacion";
                break;
            }
            case "form_anual": {
                pag = "admin_funcionario_vacaciones_form_anual";
            }
        }
        return pag;
    }

    public void buscarFuncionarioVacacionesPedido() {
        this.funcionarioVacaciones = this.getFuncionario(this.getCedulaFuncionarioVacaciones());
        this.diasRestantesTotalVacacion = 0;
        if (this.funcionarioVacaciones != null) {
            this.mostrarComponentesVacaciones = true;
            this.vacacionesFuncionarios = this.vacacionesFuncionariosSB.findByNumeroDocumentoEstadoVacacionConDiasRestantes(this.funcionarioVacaciones.getNumeroDocumento(), "ACT");
            this.calcularDiasRestantesTotalVacacion();
        } else {
            this.vacacionesFuncionarios = new ArrayList<VacacionesFuncionarios>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe funcionario", ""));
        }
    }

    public void guardarVacacionSolicitada() {
        if (this.diasSolicitadoVacacion > this.diasRestantesTotalVacacion) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Excede a la cantidad de dias disponibles", ""));
        } else {
            for (int i = 0; i < this.vacacionesFuncionarios.size(); ++i) {
                if (this.diasSolicitadoVacacion > this.vacacionesFuncionarios.get(i).getCantidadDiasRestante()) {
                    this.diasSolicitadoVacacion = this.diasSolicitadoVacacion - this.vacacionesFuncionarios.get(i).getCantidadDiasRestante();
                    this.vacacionesFuncionarios.get(i).setCantidadDiasRestante(Integer.valueOf(0));
                    continue;
                }
                if (this.diasSolicitadoVacacion < this.vacacionesFuncionarios.get(i).getCantidadDiasRestante()) {
                    this.vacacionesFuncionarios.get(i).setCantidadDiasRestante(Integer.valueOf(this.vacacionesFuncionarios.get(i).getCantidadDiasRestante() - this.diasSolicitadoVacacion));
                    this.diasSolicitadoVacacion = 0;
                    continue;
                }
                this.diasSolicitadoVacacion = 0;
                this.vacacionesFuncionarios.get(i).setCantidadDiasRestante(Integer.valueOf(0));
            }
            this.actualizarVacacionesFuncionarios();
            this.insertarAsignacionExcepcionesHorariosDiarios();
            this.diasRestantesTotalVacacion = 0;
            this.calcularDiasRestantesTotalVacacion();
        }
    }

    public void actualizarVacacionesFuncionarios() {
        for (int i = 0; i < this.vacacionesFuncionarios.size(); ++i) {
            this.vacacionesFuncionariosSB.udtade(this.vacacionesFuncionarios.get(i));
            String descriAudit = "Periodo: " + this.vacacionesFuncionarios.get(i).getVacacionesFuncionariosPK().getAnioVacacion() + ", Cedula: " + this.vacacionesFuncionarios.get(i).getVacacionesFuncionariosPK().getNumeroDocumento() + ", Dias correspondiente: " + this.vacacionesFuncionarios.get(i).getCantidadDiasCorrespondiente() + ", Dias restante: " + this.vacacionesFuncionarios.get(i).getCantidadDiasRestante();
            this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "vacaciones_funcionarios", this.vacacionesFuncionarios.get(i).getVacacionesFuncionariosPK().getNumeroDocumento(), descriAudit);
        }
    }

    public void insertarAsignacionExcepcionesHorariosDiarios() {
        for (int i = 0; i < this.rangoDeFechaSeleccionado.size(); ++i) {
            AsignacionExcepcionesHorariosDiarios asignacionExcepcionesHorariosDiariosAux = new AsignacionExcepcionesHorariosDiarios();
            asignacionExcepcionesHorariosDiariosAux.setAsignacionExcepcionesHorariosDiariosPK(new AsignacionExcepcionesHorariosDiariosPK());
            asignacionExcepcionesHorariosDiariosAux.setHorarioAsignado(new Horarios());
            asignacionExcepcionesHorariosDiariosAux.setEstadoHorarioAsignado(new Estados());
            asignacionExcepcionesHorariosDiariosAux.getAsignacionExcepcionesHorariosDiariosPK().setCodigoExcepcion("VACA");
            asignacionExcepcionesHorariosDiariosAux.getAsignacionExcepcionesHorariosDiariosPK().setFechaAsignacion(this.rangoDeFechaSeleccionado.get(i));
            asignacionExcepcionesHorariosDiariosAux.getAsignacionExcepcionesHorariosDiariosPK().setNumeroDocumentoFuncionario(this.funcionarioVacaciones.getNumeroDocumento());
            asignacionExcepcionesHorariosDiariosAux.setHorarioAsignado(this.funcionarioVacaciones.getHorarioNormal());
            asignacionExcepcionesHorariosDiariosAux.setMesAsignacion(Integer.parseInt(this.dateFormatMesNumero.format(this.rangoDeFechaSeleccionado.get(i))));
            asignacionExcepcionesHorariosDiariosAux.setAnioAsignacion(Integer.parseInt(this.dateFormatAnioNumero.format(this.rangoDeFechaSeleccionado.get(i))));
            asignacionExcepcionesHorariosDiariosAux.setDescripcionAsignacion("NRO FORMULARIO XX");
            asignacionExcepcionesHorariosDiariosAux.getEstadoHorarioAsignado().setCodigo("ACT");
            String descripcion = "FechaAsignacion: " + asignacionExcepcionesHorariosDiariosAux.getAsignacionExcepcionesHorariosDiariosPK().getFechaAsignacion() + ", Cedula: " + this.funcionarioVacaciones.getNumeroDocumento() + ", HorarioAsignado: " + (Object) this.funcionarioVacaciones.getHorarioNormal() + ", MesAsignacion: " + asignacionExcepcionesHorariosDiariosAux.getMesAsignacion() + ", AnioAsignacion: " + asignacionExcepcionesHorariosDiariosAux.getAnioAsignacion() + ", DescripcionAsignacion: " + asignacionExcepcionesHorariosDiariosAux.getDescripcionAsignacion();
            this.actualizarAsignacionExcepcionesHorariosDiarios(asignacionExcepcionesHorariosDiariosAux, new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "asignacion_excepciones_horarios_diarios", this.funcionarioVacaciones.getNumeroDocumento(), descripcion);
        }
    }

    public void actualizarAsignacionExcepcionesHorariosDiarios(AsignacionExcepcionesHorariosDiarios asignacionExcepcionesHorariosDiariosAux, Date fecha, String usuario, String usuarioRol, String tipoMovimiento, String nombreTabla, String claveTabla, String descripcion) {
        this.ejbAsignacionExcepcionesHorariosDiariosSB.udtade(asignacionExcepcionesHorariosDiariosAux);
        this.guardarAuditoria(fecha, usuario, usuarioRol, tipoMovimiento, nombreTabla, claveTabla, descripcion);
    }

    public void eliminarAsignacionExcepcionesHorariosDiarios(AsignacionExcepcionesHorariosDiarios asignacionExcepcionesHorariosDiariosAux, Date fecha, String usuario, String usuarioRol, String tipoMovimiento, String nombreTabla, String claveTabla, String descripcion) {
        this.ejbAsignacionExcepcionesHorariosDiariosSB.delete(asignacionExcepcionesHorariosDiariosAux);
        this.guardarAuditoria(fecha, usuario, usuarioRol, tipoMovimiento, nombreTabla, claveTabla, descripcion);
    }

    public void calcularDiasHabiles() {
        this.totalDiasEntreFechasVacacion = 0;
        this.totalDiasDomingosVacacion = 0;
        this.totalDiasFeriadosVacacion = 0;
        this.setTotalDiasYaAsignados(0);
        this.rangoDeFechaSeleccionado = new ArrayList<Date>();
        long diferenciaEn_ms = this.fechaHastaFuncionarioTab1vacaciones.getTime() - this.fechaDesdeFuncionarioTab1vacaciones.getTime();
        long dias = diferenciaEn_ms / 86400000;
        int i = 0;
        while ((long) i < dias + 1) {
            this.totalDiasEntreFechasVacacion = this.totalDiasEntreFechasVacacion + 1;
            Date fechaAux = SisrrhhMB.sumaDias(this.fechaDesdeFuncionarioTab1vacaciones, i);
            FechasEspeciales fechaEspecial = this.ejbFechasEspecialesSB.findByFechaEspecial(fechaAux);
            if (fechaEspecial == null) {
                String domingo = this.dateFormatDia.format(fechaAux).toUpperCase();
                if (!domingo.equals("DOMINGO")) {
                    this.asignacionExcepcionesHorariosDiariosVacaciones = this.ejbAsignacionExcepcionesHorariosDiariosSB.findByFechaAsignacionNumeroDocumentoFuncionario(fechaAux, this.funcionarioVacaciones.getNumeroDocumento(), "VACA", "ACT");
                    if (this.asignacionExcepcionesHorariosDiariosVacaciones == null) {
                        this.rangoDeFechaSeleccionado.add(fechaAux);
                    } else {
                        this.totalDiasYaAsignados = this.totalDiasYaAsignados + 1;
                    }
                } else {
                    this.totalDiasDomingosVacacion = this.totalDiasDomingosVacacion + 1;
                }
            } else {
                this.totalDiasFeriadosVacacion = this.totalDiasFeriadosVacacion + 1;
            }
            ++i;
        }
        this.diasSolicitadoVacacion = this.rangoDeFechaSeleccionado.size();
    }

    public static Date sumaDias(Date fecha, int dias) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.add(6, dias);
        return cal.getTime();
    }

    public void calcularDiasRestantesTotalVacacion() {
        for (int i = 0; i < this.vacacionesFuncionarios.size(); ++i) {
            this.diasRestantesTotalVacacion = this.diasRestantesTotalVacacion + this.vacacionesFuncionarios.get(i).getCantidadDiasRestante();
        }
    }

    public void guardarVacacionAnio() {
        VacacionesFuncionarios vacaAnio = new VacacionesFuncionarios();
        vacaAnio.setEstadoVacacion(new Estados());
        vacaAnio.getEstadoVacacion().setCodigo("ACT");
        vacaAnio.setCantidadDiasCorrespondiente(this.diasCorrespondientesVacacion);
        vacaAnio.setCantidadDiasRestante(this.diasRestantesVacacion);
        vacaAnio.setVacacionesFuncionariosPK(new VacacionesFuncionariosPK());
        vacaAnio.getVacacionesFuncionariosPK().setAnioVacacion(this.anioVacacion.intValue());
        vacaAnio.getVacacionesFuncionariosPK().setNumeroDocumento(this.funcionarioVacaciones.getNumeroDocumento());
        this.actualizarCantidadDiasAnual(vacaAnio);
    }

    public void actualizarCantidadDiasAnual(VacacionesFuncionarios vacaAnio) {
        this.vacacionesFuncionariosSB.udtade(vacaAnio);
        String descriAudit = "A\u00f1o: " + this.anioVacacion + ", Cedula: " + this.funcionarioVacaciones.getNumeroDocumento() + ", Dias Total: " + vacaAnio.getCantidadDiasCorrespondiente() + ", Dias Resto: " + vacaAnio.getCantidadDiasRestante() + ", ESTADO: " + vacaAnio.getEstadoVacacion().getCodigo();
        this.guardarAuditoria(new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Insert o Update", "vacaciones_funcionarios", this.funcionarioVacaciones.getNumeroDocumento(), descriAudit);
        this.vacacionesFuncionarios = this.vacacionesFuncionariosSB.findByNumeroDocumentoEstadoVacacion(this.funcionarioVacaciones.getNumeroDocumento(), "ACT");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados", ""));
    }

    public void buscarFuncionarioVacacionesAnual() {
        this.funcionarioVacaciones = this.getFuncionario(this.getCedulaFuncionarioVacaciones());
        if (this.funcionarioVacaciones != null) {
            this.mostrarComponentesVacaciones = true;
            this.vacacionesFuncionarios = this.vacacionesFuncionariosSB.findByNumeroDocumentoEstadoVacacion(this.funcionarioVacaciones.getNumeroDocumento(), "ACT");
        } else {
            this.vacacionesFuncionarios = new ArrayList<VacacionesFuncionarios>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe funcionario", ""));
        }
    }

    public void buscarFuncionarioVacacionesModificacion() {
        this.funcionarioVacaciones = this.getFuncionario(this.getCedulaFuncionarioVacaciones());
        if (this.funcionarioVacaciones != null) {
            this.dataTableVacaciones = new HtmlDataTable();
            this.mostrarComponentesVacaciones = true;
            this.asignacionExcepcionesHorariosDiariosVacacionesLista = this.ejbAsignacionExcepcionesHorariosDiariosSB.findByCedulaMesAnioTipo(this.funcionarioVacaciones.getNumeroDocumento(), Integer.valueOf(this.numeroMes + 1), this.numeroAnio, "VACA");
        } else {
            this.vacacionesFuncionarios = new ArrayList<VacacionesFuncionarios>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe funcionario", ""));
        }
    }

    public String modificarFechaVacacion() {
        this.asignacionExcepcionesHorariosDiariosVacaciones = new AsignacionExcepcionesHorariosDiarios();
        this.asignacionExcepcionesHorariosDiariosVacaciones = (AsignacionExcepcionesHorariosDiarios) this.getDataTableVacaciones().getRowData();
        this.vacacionesFuncionarios = this.vacacionesFuncionariosSB.findByNumeroDocumentoEstadoVacacion(this.funcionarioVacaciones.getNumeroDocumento(), "ACT");
        return "admin_funcionario_vacaciones_form_anular_fecha";
    }

    public String borrarFechaVacacion() {
        Date fechaAsignacion = this.asignacionExcepcionesHorariosDiariosVacaciones.getAsignacionExcepcionesHorariosDiariosPK().getFechaAsignacion();
        String numeroDocumento = this.funcionarioVacaciones.getNumeroDocumento();
        String codigoExcepcion = "VACA";
        String estadoHorarioAsignado = "ACT";
        AsignacionExcepcionesHorariosDiarios diaAsig = this.ejbAsignacionExcepcionesHorariosDiariosSB.findByFechaAsignacionNumeroDocumentoFuncionario(fechaAsignacion, numeroDocumento, codigoExcepcion, estadoHorarioAsignado);
        AsignacionExcepcionesHorariosDiarios diaAsigBorrar = this.ejbAsignacionExcepcionesHorariosDiariosSB.findByFechaAsignacionNumeroDocumentoFuncionario(fechaAsignacion, numeroDocumento, codigoExcepcion, estadoHorarioAsignado);
        diaAsig.getAsignacionExcepcionesHorariosDiariosPK().setCodigoExcepcion("NULL");
        diaAsig.setHorarioAsignado(this.funcionarioVacaciones.getHorarioNormal());
        diaAsig.setEstadoHorarioAsignado(new Estados("DES"));
        String descripcion = "Eliminacion de tipo de excepcion y horario asignado para fecha: " + diaAsig.getAsignacionExcepcionesHorariosDiariosPK().getFechaAsignacion();
        this.actualizarAsignacionExcepcionesHorariosDiarios(diaAsig, new Date(), this.funcionarioUsuario.getUsuario(), this.funcionarioUsuario.getRolUsuario().getNombre(), "Update", "asignacion_excepciones_horarios_diarios", diaAsig.getAsignacionExcepcionesHorariosDiariosPK().getNumeroDocumentoFuncionario(), descripcion);
        this.ejbAsignacionExcepcionesHorariosDiariosSB.delete(diaAsigBorrar);
        System.out.println("Fecha seleccionada: " + this.asignacionExcepcionesHorariosDiariosVacaciones.getAsignacionExcepcionesHorariosDiariosPK().getFechaAsignacion());
        System.out.println("Fecha asig: " + diaAsig.getAsignacionExcepcionesHorariosDiariosPK().getFechaAsignacion());
        for (int i = 0; i < this.vacacionesFuncionarios.size(); ++i) {
            if (this.vacacionesFuncionarios.get(i).getCantidadDiasCorrespondiente() <= this.vacacionesFuncionarios.get(i).getCantidadDiasRestante()) {
                continue;
            }
            this.diasSolicitadoVacacion = 1 + this.vacacionesFuncionarios.get(i).getCantidadDiasRestante();
            this.vacacionesFuncionarios.get(i).setCantidadDiasRestante(this.diasSolicitadoVacacion);
            this.actualizarCantidadDiasAnual(this.vacacionesFuncionarios.get(i));
            break;
        }
        this.dataTableVacaciones = new HtmlDataTable();
        this.asignacionExcepcionesHorariosDiariosVacacionesLista = this.ejbAsignacionExcepcionesHorariosDiariosSB.findByCedulaMesAnioTipo(this.funcionarioVacaciones.getNumeroDocumento(), Integer.valueOf(this.numeroMes + 1), this.numeroAnio, "VACA");
        return "admin_funcionario_vacaciones_form_modificacion";
    }

    public Integer cantidadDiasDelMes(Integer anio, Integer mes) {
        GregorianCalendar cal = new GregorianCalendar(anio, mes, 1);
        int days = cal.getActualMaximum(5);
        return days;
    }

    public Funcionarios getFuncionario(String cedula) {
        Funcionarios funcionarioRetorno = this.ejbFuncionariosSB.findByNumeroDocumento(cedula);
        return funcionarioRetorno;
    }

    public List<FichaCalendario> getMarcacionesPorCedulaFechaDedeFechaHasta(String cedula, Date fechaDesde, Date fechaHasta) {
        List marcaciones = this.ejbMarcacionSB.findByCedulaFechaDesdeFechaHasta(cedula, fechaDesde, fechaHasta);
        ArrayList<FichaCalendario> fichaCalendarioRetorno = new ArrayList<FichaCalendario>();
        String control = "";
        for (int i = 0; i < marcaciones.size(); ++i) {
            System.out.println("ENTROOOOO" + ((Marcacion) marcaciones.get(i)).getFechaMarcacionChar());
            if (!control.equals(((Marcacion) marcaciones.get(i)).getFechaMarcacionChar().toString())) {
                try {
                    Date fechaInicial = this.dateFormatFecha.parse(this.dateFormatFecha.format(((Marcacion) marcaciones.get(i)).getMarcacionPK().getFechaHoraMarcacion()));
                    ArrayList marcacionesAux = new ArrayList();
                    for (int j = 0; j < marcaciones.size(); ++j) {
                        if (!fechaInicial.equals(this.dateFormatFecha.parse(this.dateFormatFecha.format(((Marcacion) marcaciones.get(j)).getMarcacionPK().getFechaHoraMarcacion())))) {
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

    public Funcionarios recuperarUsuarioSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.funcionarioUsuario = (Funcionarios) session.getAttribute("funcionarioUsuario");
        return this.funcionarioUsuario;
    }

    public List<AsignacionExcepcionesHorariosDiarios> getAsignacionesHorariosPorFuncionario(String cedula, Integer mes, Integer anio) {
        List asignacionExcepcionesHorariosDiariosRetorno = this.ejbAsignacionExcepcionesHorariosDiariosSB.findByCedulaMesAnio(cedula, mes, anio);
        return asignacionExcepcionesHorariosDiariosRetorno;
    }

    public List<FichaCalendario> getFichaCalendarioNom(String cedula, Integer mes, Integer anio) {
        List fichaCalendarioRetorno = this.ejbFichaCalendarioSB.findByCedulaMesAnio(cedula, mes, anio);
        return fichaCalendarioRetorno;
    }

    public String test() {
        String r = "test";
        r = this.ejbFichaCalendarioSB.findTest();
        System.out.println(r);
        return r;
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuentaCorriente() {
        return this.cuentaCorriente;
    }

    public void setCuentaCorriente(String cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public String getLoginUsuario() {
        return this.loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getClaveUsuario() {
        return this.claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getContrasenaActual() {
        return this.contrasenaActual;
    }

    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    public String getContrasena1() {
        return this.contrasena1;
    }

    public void setContrasena1(String contrasena1) {
        this.contrasena1 = contrasena1;
    }

    public String getContrasena2() {
        return this.contrasena2;
    }

    public void setContrasena2(String contrasena2) {
        this.contrasena2 = contrasena2;
    }

    public String getLinkExpediente() {
        return this.linkExpediente;
    }

    public void setLinkExpediente(String linkExpediente) {
        this.linkExpediente = linkExpediente;
    }

    public Funcionarios getFuncionarioUsuario() {
        return this.funcionarioUsuario;
    }

    public void setFuncionarioUsuario(Funcionarios funcionarioUsuario) {
        this.funcionarioUsuario = funcionarioUsuario;
    }

    public Part getFile() {
        return this.file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public boolean isMostrarBotonSubirBd() {
        return this.mostrarBotonSubirBd;
    }

    public void setMostrarBotonSubirBd(boolean mostrarBotonSubirBd) {
        this.mostrarBotonSubirBd = mostrarBotonSubirBd;
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

    public StringBuilder getAnio() {
        return this.anio;
    }

    public void setAnio(StringBuilder anio) {
        this.anio = anio;
    }

    public StringBuilder getNumeroReloj() {
        return this.numeroReloj;
    }

    public void setNumeroReloj(StringBuilder numeroReloj) {
        this.numeroReloj = numeroReloj;
    }

    public StringBuilder getMes() {
        return this.mes;
    }

    public void setMes(StringBuilder mes) {
        this.mes = mes;
    }

    public StringBuilder getDia() {
        return this.dia;
    }

    public void setDia(StringBuilder dia) {
        this.dia = dia;
    }

    public String getFechaArchivo() {
        return this.fechaArchivo;
    }

    public void setFechaArchivo(String fechaArchivo) {
        this.fechaArchivo = fechaArchivo;
    }

    public String getNumeroRelojArchivo() {
        return this.numeroRelojArchivo;
    }

    public void setNumeroRelojArchivo(String numeroRelojArchivo) {
        this.numeroRelojArchivo = numeroRelojArchivo;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public boolean isActivarBotonSubirBd() {
        return this.activarBotonSubirBd;
    }

    public void setActivarBotonSubirBd(boolean activarBotonSubirBd) {
        this.activarBotonSubirBd = activarBotonSubirBd;
    }

    public String getCedulaFuncionario() {
        return this.cedulaFuncionario;
    }

    public void setCedulaFuncionario(String cedulaFuncionario) {
        this.cedulaFuncionario = cedulaFuncionario;
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

    public List<Funcionarios> getListaFuncionarios() {
        return this.listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionarios> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public Funcionarios getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    public boolean isMostrarComponentesPantallaBuscarRangoFechaCedula() {
        return this.mostrarComponentesPantallaBuscarRangoFechaCedula;
    }

    public void setMostrarComponentesPantallaBuscarRangoFechaCedula(boolean mostrarComponentesPantallaBuscarRangoFechaCedula) {
        this.mostrarComponentesPantallaBuscarRangoFechaCedula = mostrarComponentesPantallaBuscarRangoFechaCedula;
    }

    public LinkedHashMap<Integer, String> getListaMeses() {
        return this.listaMeses;
    }

    public void setListaMeses(LinkedHashMap<Integer, String> listaMeses) {
        this.listaMeses = listaMeses;
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

    public Integer getPorcentajeMulta() {
        return this.porcentajeMulta;
    }

    public void setPorcentajeMulta(Integer porcentajeMulta) {
        this.porcentajeMulta = porcentajeMulta;
    }

    public Integer getNumeroFilaMarcacion() {
        return this.numeroFilaMarcacion;
    }

    public void setNumeroFilaMarcacion(Integer numeroFilaMarcacion) {
        this.numeroFilaMarcacion = numeroFilaMarcacion;
    }

    public Integer getPorcentajeMultaTotal() {
        return this.porcentajeMultaTotal;
    }

    public void setPorcentajeMultaTotal(Integer porcentajeMultaTotal) {
        this.porcentajeMultaTotal = porcentajeMultaTotal;
    }

    public List<FichaCalendario> getFichasCalendarios() {
        return this.fichasCalendarios;
    }

    public void setFichasCalendarios(List<FichaCalendario> fichasCalendarios) {
        this.fichasCalendarios = fichasCalendarios;
    }

    public String getCedulaFuncionarioInicio() {
        return this.cedulaFuncionarioInicio;
    }

    public void setCedulaFuncionarioInicio(String cedulaFuncionarioInicio) {
        this.cedulaFuncionarioInicio = cedulaFuncionarioInicio;
    }

    public String getCedulaFuncionarioFin() {
        return this.cedulaFuncionarioFin;
    }

    public void setCedulaFuncionarioFin(String cedulaFuncionarioFin) {
        this.cedulaFuncionarioFin = cedulaFuncionarioFin;
    }

    public StringBuilder getTipoFun() {
        return this.tipoFun;
    }

    public void setTipoFun(StringBuilder tipoFun) {
        this.tipoFun = tipoFun;
    }

    public boolean isMostrarComponentesVacaciones() {
        return this.mostrarComponentesVacaciones;
    }

    public void setMostrarComponentesVacaciones(boolean mostrarComponentesVacaciones) {
        this.mostrarComponentesVacaciones = mostrarComponentesVacaciones;
    }

    public List<VacacionesFuncionarios> getVacacionesFuncionarios() {
        return this.vacacionesFuncionarios;
    }

    public void setVacacionesFuncionarios(List<VacacionesFuncionarios> vacacionesFuncionarios) {
        this.vacacionesFuncionarios = vacacionesFuncionarios;
    }

    public Integer getAnioVacacion() {
        return this.anioVacacion;
    }

    public void setAnioVacacion(Integer anioVacacion) {
        this.anioVacacion = anioVacacion;
    }

    public Integer getDiasCorrespondientesVacacion() {
        return this.diasCorrespondientesVacacion;
    }

    public void setDiasCorrespondientesVacacion(Integer diasCorrespondientesVacacion) {
        this.diasCorrespondientesVacacion = diasCorrespondientesVacacion;
    }

    public Integer getDiasRestantesVacacion() {
        return this.diasRestantesVacacion;
    }

    public void setDiasRestantesVacacion(Integer diasRestantesVacacion) {
        this.diasRestantesVacacion = diasRestantesVacacion;
    }

    public Integer getDiasSolicitadoVacacion() {
        return this.diasSolicitadoVacacion;
    }

    public void setDiasSolicitadoVacacion(Integer diasSolicitadoVacacion) {
        this.diasSolicitadoVacacion = diasSolicitadoVacacion;
    }

    public Integer getDiasRestantesTotalVacacion() {
        return this.diasRestantesTotalVacacion;
    }

    public void setDiasRestantesTotalVacacion(Integer diasRestantesTotalVacacion) {
        this.diasRestantesTotalVacacion = diasRestantesTotalVacacion;
    }

    public Date getFechaDesdeFuncionarioTab1vacaciones() {
        return this.fechaDesdeFuncionarioTab1vacaciones;
    }

    public void setFechaDesdeFuncionarioTab1vacaciones(Date fechaDesdeFuncionarioTab1vacaciones) {
        this.fechaDesdeFuncionarioTab1vacaciones = fechaDesdeFuncionarioTab1vacaciones;
    }

    public Date getFechaHastaFuncionarioTab1vacaciones() {
        return this.fechaHastaFuncionarioTab1vacaciones;
    }

    public void setFechaHastaFuncionarioTab1vacaciones(Date fechaHastaFuncionarioTab1vacaciones) {
        this.fechaHastaFuncionarioTab1vacaciones = fechaHastaFuncionarioTab1vacaciones;
    }

    public List<Date> getRangoDeFechaSeleccionado() {
        return this.rangoDeFechaSeleccionado;
    }

    public void setRangoDeFechaSeleccionado(List<Date> rangoDeFechaSeleccionado) {
        this.rangoDeFechaSeleccionado = rangoDeFechaSeleccionado;
    }

    public Integer getTotalDiasEntreFechasVacacion() {
        return this.totalDiasEntreFechasVacacion;
    }

    public void setTotalDiasEntreFechasVacacion(Integer totalDiasEntreFechasVacacion) {
        this.totalDiasEntreFechasVacacion = totalDiasEntreFechasVacacion;
    }

    public Integer getTotalDiasFeriadosVacacion() {
        return this.totalDiasFeriadosVacacion;
    }

    public void setTotalDiasFeriadosVacacion(Integer totalDiasFeriadosVacacion) {
        this.totalDiasFeriadosVacacion = totalDiasFeriadosVacacion;
    }

    public Integer getTotalDiasDomingosVacacion() {
        return this.totalDiasDomingosVacacion;
    }

    public void setTotalDiasDomingosVacacion(Integer totalDiasDomingosVacacion) {
        this.totalDiasDomingosVacacion = totalDiasDomingosVacacion;
    }

    public SimpleDateFormat getDateFormatDiaNumero() {
        return this.dateFormatDiaNumero;
    }

    public void setDateFormatDiaNumero(SimpleDateFormat dateFormatDiaNumero) {
        this.dateFormatDiaNumero = dateFormatDiaNumero;
    }

    public SimpleDateFormat getDateFormatMesNumero() {
        return this.dateFormatMesNumero;
    }

    public void setDateFormatMesNumero(SimpleDateFormat dateFormatMesNumero) {
        this.dateFormatMesNumero = dateFormatMesNumero;
    }

    public SimpleDateFormat getDateFormatAnioNumero() {
        return this.dateFormatAnioNumero;
    }

    public void setDateFormatAnioNumero(SimpleDateFormat dateFormatAnioNumero) {
        this.dateFormatAnioNumero = dateFormatAnioNumero;
    }

    public AsignacionExcepcionesHorariosDiarios getAsignacionExcepcionesHorariosDiariosVacaciones() {
        return this.asignacionExcepcionesHorariosDiariosVacaciones;
    }

    public void setAsignacionExcepcionesHorariosDiariosTab1Vacaciones(AsignacionExcepcionesHorariosDiarios asignacionExcepcionesHorariosDiariosVacaciones) {
        this.asignacionExcepcionesHorariosDiariosVacaciones = asignacionExcepcionesHorariosDiariosVacaciones;
    }

    public Integer getTotalDiasYaAsignados() {
        return this.totalDiasYaAsignados;
    }

    public void setTotalDiasYaAsignados(Integer totalDiasYaAsignados) {
        this.totalDiasYaAsignados = totalDiasYaAsignados;
    }

    public String getCedulaFuncionarioVacaciones() {
        return this.cedulaFuncionarioVacaciones;
    }

    public void setCedulaFuncionarioVacaciones(String cedulaFuncionarioVacaciones) {
        this.cedulaFuncionarioVacaciones = cedulaFuncionarioVacaciones;
    }

    public Funcionarios getFuncionarioVacaciones() {
        return this.funcionarioVacaciones;
    }

    public void setFuncionarioVacaciones(Funcionarios funcionarioVacaciones) {
        this.funcionarioVacaciones = funcionarioVacaciones;
    }

    public List<AsignacionExcepcionesHorariosDiarios> getAsignacionExcepcionesHorariosDiariosVacacionesLista() {
        return this.asignacionExcepcionesHorariosDiariosVacacionesLista;
    }

    public void setAsignacionExcepcionesHorariosDiariosVacacionesLista(List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosVacacionesLista) {
        this.asignacionExcepcionesHorariosDiariosVacacionesLista = asignacionExcepcionesHorariosDiariosVacacionesLista;
    }

    public HtmlDataTable getDataTableVacaciones() {
        return this.dataTableVacaciones;
    }

    public void setDataTableVacaciones(HtmlDataTable dataTableVacaciones) {
        this.dataTableVacaciones = dataTableVacaciones;
    }

    public Integer getContadorMarcacion() {
        return this.contadorMarcacion;
    }

    public void setContadorMarcacion(Integer contadorMarcacion) {
        this.contadorMarcacion = contadorMarcacion;
    }

    public int getCantidadRegistrosError() {
        return this.cantidadRegistrosError;
    }

    public void setCantidadRegistrosError(int cantidadRegistrosError) {
        this.cantidadRegistrosError = cantidadRegistrosError;
    }

    public List<String> getDetallesErrores() {
        return this.detallesErrores;
    }

    public void setDetallesErrores(List<String> detallesErrores) {
        this.detallesErrores = detallesErrores;
    }
}
