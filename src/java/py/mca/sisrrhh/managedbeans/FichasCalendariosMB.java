package py.mca.sisrrhh.managedbeans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

@Named("fichasCalendariosMB")
@SessionScoped
public class FichasCalendariosMB
  implements Serializable
{
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
  private LinkedHashMap<Integer, String> listaMeses;
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
  
  public FichasCalendariosMB()
  {
    listaMeses = new LinkedHashMap();
    listaMeses.put(Integer.valueOf(0), "Enero");
    listaMeses.put(Integer.valueOf(1), "Febrero");
    listaMeses.put(Integer.valueOf(2), "Marzo");
    listaMeses.put(Integer.valueOf(3), "Abril");
    listaMeses.put(Integer.valueOf(4), "Mayo");
    listaMeses.put(Integer.valueOf(5), "Junio");
    listaMeses.put(Integer.valueOf(6), "Julio");
    listaMeses.put(Integer.valueOf(7), "Agosto");
    listaMeses.put(Integer.valueOf(8), "Septiembre");
    listaMeses.put(Integer.valueOf(9), "Octubre");
    listaMeses.put(Integer.valueOf(10), "Noviembre");
    listaMeses.put(Integer.valueOf(11), "Diciembre");
    
    listaCiclos = new LinkedHashMap();
    listaCiclos.put(Integer.valueOf(0), "Enero - Febrero");
    listaCiclos.put(Integer.valueOf(1), "Febrero - Marzo");
    listaCiclos.put(Integer.valueOf(2), "Marzo - Abril");
    listaCiclos.put(Integer.valueOf(3), "Abril - Mayo");
    listaCiclos.put(Integer.valueOf(4), "Mayo - Junio");
    listaCiclos.put(Integer.valueOf(5), "Junio - Julio");
    listaCiclos.put(Integer.valueOf(6), "Julio - Agosto");
    listaCiclos.put(Integer.valueOf(7), "Agosto - Septiembre");
    listaCiclos.put(Integer.valueOf(8), "Septiembre - Octubre");
    listaCiclos.put(Integer.valueOf(9), "Octubre - Noviembre");
    listaCiclos.put(Integer.valueOf(10), "Noviembre - Diciembre");
    listaCiclos.put(Integer.valueOf(11), "Diciembre - Enero");
    
    listaTipoMarcacion = new LinkedHashMap();
    listaTipoMarcacion.put(Integer.valueOf(1), "Entrada");
    listaTipoMarcacion.put(Integer.valueOf(2), "Salida");
    
    listaSiNo = new LinkedHashMap();
    listaSiNo.put(Integer.valueOf(1), "SI");
    listaSiNo.put(Integer.valueOf(2), "NO");
  }
  
  @PostConstruct
  public void init()
  {
    recuperarUsuarioSession();
    prepararFormFichaGeneralMensualNom();
    prepararFormFichaGeneralMensualJor();
  }
  
  public String prepararUpdateBloqueo()
  {
    return "/administracion/admin_form_update_bloqueo";
  }
  
  public void updateBloqueo() {
    fichasCalendarios = ejbFichaCalendarioSB.findAll();
    for (int i = 0; i < fichasCalendarios.size(); i++) {
      ((FichaCalendario)fichasCalendarios.get(i)).setMarcaBloqueo("NO");
    }
    
    guardarFichasCalendarios();
  }
  
  public String prepararFormCrearFichasCalendariosNom() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    setNumeroMes(Integer.valueOf(Integer.valueOf(cal.get(2)).intValue() - 1));
    setNumeroAnio(Integer.valueOf(cal.get(1)));
    setTipoGeneracion("NOM");
    return "/administracion/admin_funcionario_ficha_general_form_creacion_nom";
  }
  
  public void crearFichasCalendariosNom() throws ParseException {
    int diasDelMes = cantidadDiasDelMes(getNumeroAnio(), getNumeroMes()).intValue();
    List<Funcionarios> listaFuncionariosActivos = ejbFuncionariosSB.findByEstadoRelacionLaboralFiltro("ACT", getTipoGeneracion());
    
    Integer mesInt = Integer.valueOf(getNumeroMes().intValue() + 1);
    

    String mesDesdeString = mesInt + "";
    if (mesInt.intValue() < 10) {
      mesDesdeString = "0" + mesInt;
    }
    
    Date fechaDesdeAux = getDateFormatFecha().parse("1/" + mesDesdeString + "/" + getNumeroAnio());
    Date fechaHastaAux = getDateFormatFecha().parse(diasDelMes + "/" + mesDesdeString + "/" + getNumeroAnio());
    setFichasCalendarios(new ArrayList());
    
    for (int i = 0; i < listaFuncionariosActivos.size(); i++) {
      List<FichaCalendario> fichasCalendariosAux = getMarcacionesPorCedulaFechaDedeFechaHasta(((Funcionarios)listaFuncionariosActivos.get(i)).getNumeroDocumento(), fechaDesdeAux, fechaHastaAux);
      

      for (int j = 0; j < diasDelMes; j++) {
        Integer diaInt = Integer.valueOf(j + 1);
        String diaString = diaInt + "";
        if (diaInt.intValue() < 10) {
          diaString = "0" + diaInt;
        }
        String mesString = mesInt + "";
        if (mesInt.intValue() < 10) {
          mesString = "0" + mesInt;
        }
        String fechaSeleccionada = diaString + "/" + mesString + "/" + getNumeroAnio();
        FichaCalendario fichaCalendario = new FichaCalendario();
        fichaCalendario.setFichaCalendarioPK(new FichaCalendarioPK());
        fichaCalendario.getFichaCalendarioPK().setFechaCalendario(getDateFormatFecha().parse(fechaSeleccionada));
        fichaCalendario.getFichaCalendarioPK().setNumeroDocumentoFuncionario(((Funcionarios)listaFuncionariosActivos.get(i)).getNumeroDocumento());
        fichaCalendario.setMesCalendarioNumero(getNumeroMes().intValue() + 1);
        fichaCalendario.setAnioCalendarioNumero(getNumeroAnio().intValue());
        fichaCalendario.setDiaCalendarioNumero(diaInt.intValue());
        fichaCalendario.setDiaCalendarioLetra(dateFormatDia.format(getDateFormatFecha().parse(fechaSeleccionada)).toUpperCase());
        fichaCalendario.setHorarioAsignado(((Funcionarios)listaFuncionariosActivos.get(i)).getHorarioNormal());
        if (fichasCalendariosAux.isEmpty()) {
          fichaCalendario.setHoraEntrada(null);
          fichaCalendario.setHoraSalida(null);
          fichaCalendario.setHoraNoDefinida(null);
        } else {
          for (int k = 0; k < fichasCalendariosAux.size(); k++) {
            String fecha1 = getDateFormatFecha().format(fichaCalendario.getFichaCalendarioPK().getFechaCalendario());
            String fecha2 = getDateFormatFecha().format(((FichaCalendario)fichasCalendariosAux.get(k)).getFichaCalendarioPK().getFechaCalendario());
            if (fecha1.equals(fecha2)) {
              fichaCalendario.setHoraEntrada(((FichaCalendario)fichasCalendariosAux.get(k)).getHoraEntrada());
              fichaCalendario.setHoraSalida(((FichaCalendario)fichasCalendariosAux.get(k)).getHoraSalida());
              fichaCalendario.setHoraNoDefinida(((FichaCalendario)fichasCalendariosAux.get(k)).getHoraNoDefinida());
              break;
            }
          }
        }
        
        fichaCalendario.setPorcentajeMulta(Integer.valueOf(0));
        fichaCalendario.setMontoFijo(Integer.valueOf(0));
        fichaCalendario.setMarcaBloqueo("NO");
        getFichasCalendarios().add(fichaCalendario);
      }
    }
    guardarFichasCalendarios();
  }
  
  public String prepararFormCrearFichasCalendariosJor() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    setNumeroCiclo(Integer.valueOf(Integer.valueOf(cal.get(2)).intValue() - 1));
    setNumeroAnio(Integer.valueOf(cal.get(1)));
    setTipoGeneracion("JOR");
    return "/administracion/admin_funcionario_ficha_general_form_creacion_jor";
  }
  
  public void crearFichasCalendariosJor() throws ParseException {
    List<Funcionarios> listaFuncionariosActivos = ejbFuncionariosSB.findByEstadoRelacionLaboralFiltro("ACT", getTipoGeneracion());
    
    Integer mesIni = Integer.valueOf(getNumeroCiclo().intValue() + 1);
    

    String mesDesdeString = mesIni + "";
    if (mesIni.intValue() < 10) {
      mesDesdeString = "0" + mesIni;
    }
    
    Integer mesHas = Integer.valueOf(mesIni.intValue() + 1);
    String mesHastaString = mesHas.intValue() + 1 + "";
    if (mesHas.intValue() < 10) {
      mesHastaString = "0" + mesHas;
    }
    
    Integer anioIni = getNumeroAnio();
    
    Integer anioFin = getNumeroAnio();
    
    if (mesIni.equals(Integer.valueOf(12))) {
      mesHastaString = "01";
      anioFin = Integer.valueOf(anioFin.intValue() + 1);
    }
    
    Date fechaDesdeAux = getDateFormatFecha().parse("11/" + mesDesdeString + "/" + anioIni);
    Date fechaHastaAux = getDateFormatFecha().parse("10/" + mesHastaString + "/" + anioFin);
    
    setFichasCalendarios(new ArrayList());
    
    List<Date> calendarioJor = new ArrayList();
    
    Calendar cal = new GregorianCalendar();
    cal.setLenient(false);
    
    Date fechaIni = fechaDesdeAux;
    Date fechaFin = fechaHastaAux;
    
    int ban = 0;
    while (ban == 0) {
      if (fechaIni.after(fechaFin)) {
        ban = 1;
      } else {
        calendarioJor.add(fechaIni);
        cal.setTime(fechaIni);
        cal.add(5, 1);
        fechaIni = calendarToDate(cal);
      }
    }
    
    for (int i = 0; i < listaFuncionariosActivos.size(); i++) {
      List<FichaCalendario> fichasCalendariosAux = getMarcacionesPorCedulaFechaDedeFechaHasta(((Funcionarios)listaFuncionariosActivos.get(i)).getNumeroDocumento(), fechaDesdeAux, fechaHastaAux);
      

      for (int j = 0; j < calendarioJor.size(); j++) {
        FichaCalendario fichaCalendario = new FichaCalendario();
        fichaCalendario.setFichaCalendarioPK(new FichaCalendarioPK());
        fichaCalendario.getFichaCalendarioPK().setFechaCalendario((Date)calendarioJor.get(j));
        fichaCalendario.getFichaCalendarioPK().setNumeroDocumentoFuncionario(((Funcionarios)listaFuncionariosActivos.get(i)).getNumeroDocumento());
        fichaCalendario.setMesCalendarioNumero(Integer.parseInt(dateFormatMesNumero.format((Date)calendarioJor.get(j))));
        fichaCalendario.setAnioCalendarioNumero(Integer.parseInt(dateFormatAnioNumero.format((Date)calendarioJor.get(j))));
        fichaCalendario.setDiaCalendarioNumero(Integer.parseInt(dateFormatDiaNumero.format((Date)calendarioJor.get(j))));
        fichaCalendario.setDiaCalendarioLetra(dateFormatDia.format((Date)calendarioJor.get(j)).toUpperCase());
        fichaCalendario.setHorarioAsignado(((Funcionarios)listaFuncionariosActivos.get(i)).getHorarioNormal());
        if (fichasCalendariosAux.isEmpty()) {
          fichaCalendario.setHoraEntrada(null);
          fichaCalendario.setHoraSalida(null);
          fichaCalendario.setHoraNoDefinida(null);
        } else {
          for (int k = 0; k < fichasCalendariosAux.size(); k++) {
            String fecha1 = getDateFormatFecha().format(fichaCalendario.getFichaCalendarioPK().getFechaCalendario());
            String fecha2 = getDateFormatFecha().format(((FichaCalendario)fichasCalendariosAux.get(k)).getFichaCalendarioPK().getFechaCalendario());
            if (fecha1.equals(fecha2)) {
              fichaCalendario.setHoraEntrada(((FichaCalendario)fichasCalendariosAux.get(k)).getHoraEntrada());
              fichaCalendario.setHoraSalida(((FichaCalendario)fichasCalendariosAux.get(k)).getHoraSalida());
              fichaCalendario.setHoraNoDefinida(((FichaCalendario)fichasCalendariosAux.get(k)).getHoraNoDefinida());
              if ((((FichaCalendario)fichasCalendariosAux.get(k)).getHoraEntrada() != null) || (((FichaCalendario)fichasCalendariosAux.get(k)).getHoraSalida() != null) || (((FichaCalendario)fichasCalendariosAux.get(k)).getHoraNoDefinida() != null)) {
                fichaCalendario.setDiaTrabajado(Boolean.valueOf(true));
              }
              fichaCalendario.setDiaComplementario(Boolean.valueOf(false));
              break;
            }
          }
        }
        
        fichaCalendario.setPorcentajeMulta(Integer.valueOf(0));
        fichaCalendario.setMontoFijo(Integer.valueOf(0));
        
        fichaCalendario.setMarcaBloqueo("NO");
        getFichasCalendarios().add(fichaCalendario);
      }
    }
    guardarFichasCalendarios();
  }
  
  public void guardarFichasCalendarios() {
    if (getFichasCalendarios() != null)
    {


      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se generaron fichas nueva", ""));
      

      if (ejbFichaCalendarioSB.udtadeMultiple(getFichasCalendarios()).equals("OK"))
      {
        String descriAudit = "Fecha: " + new Date() + ", Total Registros insertados: " + getFichasCalendarios().size();
        guardarAuditoria(new Date(), getFuncionarioUsuario().getUsuario(), getFuncionarioUsuario().getRolUsuario().getNombre(), "Insert", "ficha_calendario", "", descriAudit);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se generaron fichas nueva", "Total: " + getFichasCalendarios().size()));
      }
    }
    else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocurrio un error al generar las fichas", ""));
    }
    fichasCalendarios = null;
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
    auditoriaSB.create(auditoria);
  }
  
  public List<FichaCalendario> getMarcacionesPorCedulaFechaDedeFechaHasta(String cedula, Date fechaDesde, Date fechaHasta)
  {
    List<Marcacion> marcaciones = ejbMarcacionSB.findByCedulaFechaDesdeFechaHasta(cedula, fechaDesde, fechaHasta);
    

    List<FichaCalendario> fichaCalendarioRetorno = new ArrayList();
    
    String control = "";
    
    for (int i = 0; i < marcaciones.size(); i++) {
      if (!control.equals(((Marcacion)marcaciones.get(i)).getFechaMarcacionChar().toString())) {
        try
        {
          Date fechaInicial = getDateFormatFecha().parse(getDateFormatFecha().format(((Marcacion)marcaciones.get(i)).getMarcacionPK().getFechaHoraMarcacion()));
          List<Marcacion> marcacionesAux = new ArrayList();
          for (int j = 0; j < marcaciones.size(); j++) {
            if (fechaInicial.equals(getDateFormatFecha().parse(getDateFormatFecha().format(((Marcacion)marcaciones.get(j)).getMarcacionPK().getFechaHoraMarcacion())))) {
              marcacionesAux.add(marcaciones.get(j));
            }
          }
          

          Marcacion marcacionMenor = new Marcacion();
          Marcacion marcacionMayor = new Marcacion();
          Marcacion marcacionNoDefinida = new Marcacion();
          for (int k = 0; k < marcacionesAux.size(); k++) {
            Date fechaMayor = ((Marcacion)marcacionesAux.get(k)).getMarcacionPK().getFechaHoraMarcacion();
            Date fechaMenor = ((Marcacion)marcacionesAux.get(k)).getMarcacionPK().getFechaHoraMarcacion();
            for (int l = 0; l < marcacionesAux.size(); l++) {
              if (((Marcacion)marcacionesAux.get(l)).getMarcacionPK().getFechaHoraMarcacion().compareTo(fechaMayor) > 0) {
                marcacionMayor = (Marcacion)marcacionesAux.get(l);
                fechaMayor = ((Marcacion)marcacionesAux.get(l)).getMarcacionPK().getFechaHoraMarcacion();
              } else if (((Marcacion)marcacionesAux.get(l)).getMarcacionPK().getFechaHoraMarcacion().compareTo(fechaMenor) < 0) {
                marcacionMenor = (Marcacion)marcacionesAux.get(l);
                fechaMenor = ((Marcacion)marcacionesAux.get(l)).getMarcacionPK().getFechaHoraMarcacion();
              } else {
                marcacionNoDefinida = (Marcacion)marcacionesAux.get(l);
              }
            }
          }
          FichaCalendario fichaCalendario = new FichaCalendario();
          
          fichaCalendario.setDiaCalendarioLetra(((Marcacion)marcacionesAux.get(0)).getDiaMarcacion());
          
          fichaCalendario.setFichaCalendarioPK(new FichaCalendarioPK());
          fichaCalendario.getFichaCalendarioPK().setFechaCalendario(((Marcacion)marcacionesAux.get(0)).getMarcacionPK().getFechaHoraMarcacion());
          fichaCalendario.getFichaCalendarioPK().setNumeroDocumentoFuncionario(((Marcacion)marcacionesAux.get(0)).getMarcacionPK().getCedula());
          
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
      control = ((Marcacion)marcaciones.get(i)).getFechaMarcacionChar().toString();
    }
    return fichaCalendarioRetorno;
  }
  
  public String prepararFormFichaGeneralMensualNom() {
    dataTableFichasCalendarios = new HtmlDataTable();
    mostrarComponentesPantallaBuscarRangoFechaCedula = false;
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    numeroMes = Integer.valueOf(cal.get(2) - 1);
    numeroAnio = Integer.valueOf(cal.get(1));
    porcentajeMultaTotal = Integer.valueOf(0);
    return "admin_funcionario_ficha_general_nom";
  }
  
  public String moverHorarioMarcacionFichaGeneral() {
    fichaCalendario = new FichaCalendario();
    fichaCalendario = ((FichaCalendario)getDataTableFichasCalendarios().getRowData());
    return "admin_funcionario_mover_tipo_marcacion_ficha_general";
  }
  
  public String guardarMoverHorarioMarcacionFichaGeneral() {
    String retorno = "";
    if (numeroTipoMarcacion != null) { String horarioMovido;
      String horarioMovido;
      if (numeroTipoMarcacion.intValue() == 1) {
        fichaCalendario.setHoraEntrada(fichaCalendario.getHoraNoDefinida());
        horarioMovido = "HoraEntrada: " + fichaCalendario.getHoraEntrada();
      } else {
        fichaCalendario.setHoraSalida(fichaCalendario.getHoraNoDefinida());
        horarioMovido = "HoraSalida: " + fichaCalendario.getHoraSalida();
      }
      fichaCalendario.setHoraNoDefinida(null);
      ejbFichaCalendarioSB.udtade(fichaCalendario);
      String descriAudit = "Fecha: " + fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + horarioMovido;
      
      guardarAuditoria(new Date(), funcionarioUsuario
        .getUsuario(), funcionarioUsuario
        .getRolUsuario().getNombre(), "Update", "ficha_calendario", fichaCalendario
        

        .getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
      
      dataTableFichasCalendarios = new HtmlDataTable();
      numeroTipoMarcacion = Integer.valueOf(0);
      retorno = redireccion();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un tipo de marcación", ""));
    }
    return retorno;
  }
  
  public String cambiarHorarioAsignadoFichaGeneral() {
    fichaCalendario = new FichaCalendario();
    fichaCalendario = ((FichaCalendario)getDataTableFichasCalendarios().getRowData());
    return "admin_funcionario_cambiar_horario_asignado_ficha_general";
  }
  
  public String guardarCambiarHorarioAsignadoFichaGeneral() {
    String retorno = "";
    if (!StringUtils.isBlank(fichaCalendario.getMotivoCambioHora())) {
      ejbFichaCalendarioSB.udtade(fichaCalendario);
      
      String descriAudit = "Fecha: " + fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + fichaCalendario.getMotivoCambioHora();
      guardarAuditoria(new Date(), funcionarioUsuario
        .getUsuario(), funcionarioUsuario
        .getRolUsuario().getNombre(), "Update", "ficha_calendario", fichaCalendario
        

        .getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
      
      retorno = redireccion();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo Comentario no puede estar vacio. ", ""));
    }
    return retorno;
  }
  
  public String agregarMotivoFichaGeneral() {
    fichaCalendario = new FichaCalendario();
    fichaCalendario = ((FichaCalendario)getDataTableFichasCalendarios().getRowData());
    if (fichaCalendario.getCodigoExcepcion() == null) {
      fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
    }
    return "admin_funcionario_agregar_motivo_ficha_general";
  }
  
  public String guardarAgregarMotivoFichaGeneral() {
    String retorno = "";
    if (!StringUtils.isBlank(fichaCalendario.getMotivo()))
    {
      String descriAudit = "Fecha: " + fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + fichaCalendario.getMotivo();
      guardarAuditoria(new Date(), funcionarioUsuario
        .getUsuario(), funcionarioUsuario
        .getRolUsuario().getNombre(), "Update", "ficha_calendario", fichaCalendario
        

        .getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
      
      ejbFichaCalendarioSB.udtade(fichaCalendario);
      retorno = redireccion();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo Comentario no puede estar vacio. ", ""));
    }
    return retorno;
  }
  
  public String agregarProcentajeMultaFichaGeneral() {
    fichaCalendario = new FichaCalendario();
    porcentajeMulta = null;
    fichaCalendario = ((FichaCalendario)getDataTableFichasCalendarios().getRowData());
    if (fichaCalendario.getCodigoExcepcion() == null) {
      fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
    }
    return "admin_funcionario_agregar_porcentaje_multa_ficha_general";
  }
  
  public String guardarAgregarProcentajeMultaFichaGeneral() {
    String retorno = "";
    if ((porcentajeMulta != null) && (porcentajeMulta.intValue() > -1) && (porcentajeMulta.intValue() < 101)) {
      fichaCalendario.setPorcentajeMulta(porcentajeMulta);
      
      String descriAudit = "Fecha: " + fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", PorcentajeMulta: " + fichaCalendario.getPorcentajeMulta();
      guardarAuditoria(new Date(), funcionarioUsuario
        .getUsuario(), funcionarioUsuario
        .getRolUsuario().getNombre(), "Update", "ficha_calendario", fichaCalendario
        

        .getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
      
      ejbFichaCalendarioSB.udtade(fichaCalendario);
      retorno = redireccion();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor no valido para Cambiar Pocentaje Actual. ", "Debe estar entre 0 y 100"));
    }
    calcutarTotalPorcentaje();
    return retorno;
  }
  
  public String agregarMontoFijoMultaFichaGeneral() {
    fichaCalendario = new FichaCalendario();
    montoFijo = null;
    fichaCalendario = ((FichaCalendario)getDataTableFichasCalendarios().getRowData());
    if (fichaCalendario.getCodigoExcepcion() == null) {
      fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
    }
    return "admin_funcionario_agregar_monto_fijo_multa_ficha_general";
  }
  
  public String guardarAgregarMontoFijoMultaFichaGeneral() {
    String retorno = "";
    if ((montoFijo != null) && (montoFijo.intValue() > -1)) {
      fichaCalendario.setMontoFijo(montoFijo);
      
      String descriAudit = "Fecha: " + fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", MontoFijoMulta: " + fichaCalendario.getMontoFijo();
      guardarAuditoria(new Date(), funcionarioUsuario
        .getUsuario(), funcionarioUsuario
        .getRolUsuario().getNombre(), "Update", "ficha_calendario", fichaCalendario
        

        .getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
      
      ejbFichaCalendarioSB.udtade(fichaCalendario);
      retorno = redireccion();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor no valido para Cambiar Monto Fijo Actual. ", "No puede estar vacio"));
    }
    calcutarTotalPorcentaje();
    return retorno;
  }
  
  public String diaTrabajadoFichaGeneral() {
    diasBoolean = null;
    fichaCalendario = new FichaCalendario();
    montoFijo = null;
    fichaCalendario = ((FichaCalendario)getDataTableFichasCalendarios().getRowData());
    if (fichaCalendario.getCodigoExcepcion() == null) {
      fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
    }
    return "admin_funcionario_dia_trabajado_marcacion_ficha_general";
  }
  
  public String guardarDiaTrabajadoMarcacionFichaGeneral() {
    String retorno = "";
    if (diasBoolean != null) {
      String diaTrabajado = "Dia Trabajado: ";
      if (diasBoolean.intValue() == 1) {
        fichaCalendario.setDiaTrabajado(Boolean.valueOf(true));
        diaTrabajado = diaTrabajado + "SI";
      } else {
        fichaCalendario.setDiaTrabajado(Boolean.valueOf(false));
        diaTrabajado = diaTrabajado + "NO";
      }
      ejbFichaCalendarioSB.udtade(fichaCalendario);
      String descriAudit = "Fecha: " + fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + diaTrabajado;
      
      guardarAuditoria(new Date(), funcionarioUsuario
        .getUsuario(), funcionarioUsuario
        .getRolUsuario().getNombre(), "Update", "ficha_calendario", fichaCalendario
        

        .getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
      
      diasBoolean = Integer.valueOf(0);
      retorno = redireccion();
      calcutarDiasTrabajados();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar valor para Cambio de estado", ""));
    }
    return retorno;
  }
  
  public String diaComplementarioFichaGeneral() {
    diasBoolean = null;
    fichaCalendario = new FichaCalendario();
    montoFijo = null;
    fichaCalendario = ((FichaCalendario)getDataTableFichasCalendarios().getRowData());
    if (fichaCalendario.getCodigoExcepcion() == null) {
      fichaCalendario.setCodigoExcepcion(new TiposExcepciones());
    }
    return "admin_funcionario_dia_complementario_marcacion_ficha_general";
  }
  
  public String guardarDiaComplementarioMarcacionFichaGeneral() {
    String retorno = "";
    if (diasBoolean != null) {
      String diaComplementario = "Dia Complementario: ";
      if (diasBoolean.intValue() == 1) {
        fichaCalendario.setDiaComplementario(Boolean.valueOf(true));
        diaComplementario = diaComplementario + "SI";
      } else {
        fichaCalendario.setDiaComplementario(Boolean.valueOf(false));
        diaComplementario = diaComplementario + "NO";
      }
      ejbFichaCalendarioSB.udtade(fichaCalendario);
      String descriAudit = "Fecha: " + fichaCalendario.getFichaCalendarioPK().getFechaCalendario() + ", " + diaComplementario;
      
      guardarAuditoria(new Date(), funcionarioUsuario
        .getUsuario(), funcionarioUsuario
        .getRolUsuario().getNombre(), "Update", "ficha_calendario", fichaCalendario
        

        .getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "-" + fichaCalendario.getFichaCalendarioPK().getFechaCalendario(), descriAudit);
      
      diasBoolean = Integer.valueOf(0);
      retorno = redireccion();
      calcutarDiasComplementarios();
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar valor para Cambio de estado", ""));
    }
    return retorno;
  }
  
  public String cancelarModificarFichaGeneral() {
    fichaCalendario = null;
    return redireccion();
  }
  
  public String redireccion() {
    dataTableFichasCalendarios = new HtmlDataTable();
    String retorno;
    List<FichaCalendario> listAux;
    String retorno; if (funcionario.getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
      List<FichaCalendario> listAux = getFichaCalendarioJor(getCedulaFuncionario(), fechaDesde, fechaHasta);
      

      retorno = "admin_funcionario_ficha_general_jor";
    } else {
      listAux = getFichaCalendarioNom(getCedulaFuncionario(), Integer.valueOf(numeroMes.intValue() + 1), numeroAnio);
      

      retorno = "admin_funcionario_ficha_general_nom";
    }
    for (int i = 0; i < listAux.size(); i++) {
      if ((((FichaCalendario)listAux.get(i)).getPorcentajeMulta() != null) && (((FichaCalendario)listAux.get(i)).getPorcentajeMulta().intValue() > 0))
      {
        ((FichaCalendario)listAux.get(i)).setPorcentajeMulta(((FichaCalendario)fichasCalendarios.get(i)).getPorcentajeMulta());
      }
      if ((((FichaCalendario)listAux.get(i)).getMontoFijo() != null) && (((FichaCalendario)listAux.get(i)).getMontoFijo().intValue() > 0))
      {
        ((FichaCalendario)listAux.get(i)).setMontoFijo(((FichaCalendario)fichasCalendarios.get(i)).getMontoFijo());
      }
    }
    fichasCalendarios.clear();
    fichasCalendarios = listAux;
    return retorno;
  }
  
  public void buscarFichaPorMesAnioCedulaNom() throws ParseException {
    int dias = cantidadDiasDelMes(numeroAnio, numeroMes).intValue();
    setFuncionario(getFuncionario(getCedulaFuncionario()));
    

    if (getFuncionario() == null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el funcionario", ""));
      prepararFormFichaGeneralMensualNom();
    } else if (getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El funcionario es Jornalero", ""));
      prepararFormFichaGeneralMensualNom();
    }
    else {
      mostrarComponentesPantallaBuscarRangoFechaCedula = true;
      Integer mesInt = Integer.valueOf(numeroMes.intValue() + 1);
      

      fichasCalendarios = getFichaCalendarioNom(getCedulaFuncionario(), mesInt, numeroAnio);
      activarComponentes = false;
      for (int k = 0; k < fichasCalendarios.size(); k++) {
        if (((FichaCalendario)fichasCalendarios.get(k)).getMarcaBloqueo().equals("SI")) {
          activarComponentes = true;
        }
      }
      
      fichaVerificada = "NO";
      if ((((FichaCalendario)fichasCalendarios.get(0)).getMarcaFichaVerificada() != null) && (((FichaCalendario)fichasCalendarios.get(0)).getMarcaFichaVerificada().booleanValue())) {
        fichaVerificada = "SI";
      }
      















































      List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiarios = getAsignacionesHorariosPorFuncionarioNom(getCedulaFuncionario(), mesInt, numeroAnio);
      if (!asignacionExcepcionesHorariosDiarios.isEmpty()) {
        for (int i = 0; i < getFichasCalendarios().size(); i++) {
          for (int j = 0; j < asignacionExcepcionesHorariosDiarios.size(); j++) {
            if (((AsignacionExcepcionesHorariosDiarios)asignacionExcepcionesHorariosDiarios.get(j)).getEstadoHorarioAsignado().getCodigo().equals("ACT")) {
              Date fechaCal = ((FichaCalendario)getFichasCalendarios().get(i)).getFichaCalendarioPK().getFechaCalendario();
              
              if (fechaCal.equals(((AsignacionExcepcionesHorariosDiarios)asignacionExcepcionesHorariosDiarios.get(j)).getAsignacionExcepcionesHorariosDiariosPK().getFechaAsignacion())) {
                ((FichaCalendario)getFichasCalendarios().get(i)).setHorarioAsignado(((AsignacionExcepcionesHorariosDiarios)asignacionExcepcionesHorariosDiarios.get(j)).getHorarioAsignado());
                ((FichaCalendario)getFichasCalendarios().get(i)).setMotivo(((AsignacionExcepcionesHorariosDiarios)asignacionExcepcionesHorariosDiarios.get(j)).getTiposExcepciones().getDescripcionExcepcion());
              }
            } else {
              ((FichaCalendario)getFichasCalendarios().get(i)).setHorarioAsignado(getFuncionario().getHorarioNormal());
              ((FichaCalendario)getFichasCalendarios().get(i)).setMotivo("");
            }
          }
        }
      }
      


      calcutarTotalPorcentaje();
      calcutarMotoFijoTotal();
      calcutarDiasTrabajados();
      calcutarDiasComplementarios();
    }
  }
  
  public String prepararFormFichaGeneralMensualJor()
  {
    dataTableFichasCalendarios = new HtmlDataTable();
    setMostrarComponentesPantallaBuscarRangoFechaCedula(false);
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    numeroCiclo = Integer.valueOf(cal.get(2) - 1);
    numeroAnio = Integer.valueOf(cal.get(1));
    setPorcentajeMultaTotal(Integer.valueOf(0));
    return "admin_funcionario_ficha_general_jor";
  }
  
  public void buscarFichaPorPeriodoCedulaJor() throws ParseException {
    setFuncionario(getFuncionario(getCedulaFuncionario()));
    if (getFuncionario() == null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existe el funcionario", ""));
      prepararFormFichaGeneralMensualJor();
    } else if (!getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El funcionario no es Jornalero", ""));
      prepararFormFichaGeneralMensualJor();
    }
    else {
      mostrarComponentesPantallaBuscarRangoFechaCedula = true;
      
      calcularPeriodo("JOR");
      
      fichasCalendarios = getFichaCalendarioJor(getCedulaFuncionario(), fechaDesde, fechaHasta);
      activarComponentes = false;
      for (int k = 0; k < fichasCalendarios.size(); k++) {
        if (((FichaCalendario)fichasCalendarios.get(k)).getMarcaBloqueo().equals("SI")) {
          activarComponentes = true;
        }
      }
      
      fichaVerificada = "NO";
      if ((((FichaCalendario)fichasCalendarios.get(0)).getMarcaFichaVerificada() != null) && (((FichaCalendario)fichasCalendarios.get(0)).getMarcaFichaVerificada().booleanValue())) {
        fichaVerificada = "SI";
      }
      
      List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiarios = getAsignacionesHorariosPorFuncionarioJor(getCedulaFuncionario(), fechaDesde, fechaHasta);
      if (!asignacionExcepcionesHorariosDiarios.isEmpty()) {
        for (int i = 0; i < getFichasCalendarios().size(); i++) {
          for (int j = 0; j < asignacionExcepcionesHorariosDiarios.size(); j++) {
            if (((AsignacionExcepcionesHorariosDiarios)asignacionExcepcionesHorariosDiarios.get(j)).getEstadoHorarioAsignado().getCodigo().equals("ACT")) {
              Date fechaCal = ((FichaCalendario)getFichasCalendarios().get(i)).getFichaCalendarioPK().getFechaCalendario();
              if (fechaCal.equals(((AsignacionExcepcionesHorariosDiarios)asignacionExcepcionesHorariosDiarios.get(j)).getAsignacionExcepcionesHorariosDiariosPK().getFechaAsignacion())) {
                ((FichaCalendario)getFichasCalendarios().get(i)).setHorarioAsignado(((AsignacionExcepcionesHorariosDiarios)asignacionExcepcionesHorariosDiarios.get(j)).getHorarioAsignado());
                ((FichaCalendario)getFichasCalendarios().get(i)).setMotivo(((AsignacionExcepcionesHorariosDiarios)asignacionExcepcionesHorariosDiarios.get(j)).getTiposExcepciones().getDescripcionExcepcion());
              }
            } else {
              ((FichaCalendario)getFichasCalendarios().get(i)).setHorarioAsignado(getFuncionario().getHorarioNormal());
              ((FichaCalendario)getFichasCalendarios().get(i)).setMotivo("");
            }
          }
        }
      }
      
      calcutarTotalPorcentaje();
      calcutarMotoFijoTotal();
      calcutarDiasTrabajados();
      calcutarDiasComplementarios();
    }
  }
  
  public void calcularHorasExtras() {
    System.out.println("Hora Extra");
    System.out.println("Cedula|Apellidos, Nombres|Fecha|Horario Asignado|Hora Entrada|Hora Salida|Horas Extra");
    for (int i = 0; i < getFichasCalendarios().size(); i++)
    {





      if ((!StringUtils.isBlank(String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getAnioCalendarioNumero()))) && (!String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getAnioCalendarioNumero()).isEmpty())) {
        String anioAux = String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getAnioCalendarioNumero());
        if ((!StringUtils.isBlank(String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getMesCalendarioNumero()))) && (!String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getMesCalendarioNumero()).isEmpty())) {
          String mesAux = String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getMesCalendarioNumero());
          if ((!StringUtils.isBlank(String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getDiaCalendarioNumero()))) && (!String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getDiaCalendarioNumero()).isEmpty())) {
            String diaAux = String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getDiaCalendarioNumero());
            
            if ((!StringUtils.isBlank(((FichaCalendario)getFichasCalendarios().get(i)).getHorarioAsignado().getHoraSalida())) && (!((FichaCalendario)getFichasCalendarios().get(i)).getHorarioAsignado().getHoraSalida().isEmpty()) && 
              (!((FichaCalendario)getFichasCalendarios().get(i)).getHorarioAsignado().getHorario().equals("00000000"))) {
              String horaInicio = ((FichaCalendario)getFichasCalendarios().get(i)).getHorarioAsignado().getHoraSalida();
              if (((FichaCalendario)getFichasCalendarios().get(i)).getHoraSalida() != null) {
                String horaFin = dateFormatHora.format(((FichaCalendario)getFichasCalendarios().get(i)).getHoraSalida());
                FechaHora f = new FechaHora();
                f.calcularHorasMinutosMismoDia(anioAux, mesAux, diaAux, horaInicio, horaFin);
                long hor = f.getMinutos() / 60L;
                long rest = f.getMinutos() % 60L;
                if (f.getMinutos() >= 0.0D)
                {



                  String h = hor + "";
                  if (hor < 10L) {
                    h = "0" + hor;
                  }
                  String m = rest + "";
                  if (rest < 10L) {
                    m = "0" + rest;
                  }
                  
                  String result = ((FichaCalendario)getFichasCalendarios().get(i)).getFichaCalendarioPK().getNumeroDocumentoFuncionario() + "|";
                  result = result + ((FichaCalendario)getFichasCalendarios().get(i)).getFuncionarios().getApellidos() + ", " + ((FichaCalendario)getFichasCalendarios().get(i)).getFuncionarios().getNombres() + "|";
                  result = result + getDateFormatFecha().format(((FichaCalendario)getFichasCalendarios().get(i)).getFichaCalendarioPK().getFechaCalendario()) + "|";
                  result = result + ((FichaCalendario)getFichasCalendarios().get(i)).getHorarioAsignado().getDescripcion() + "|";
                  result = result + formatearFechaToHora(((FichaCalendario)getFichasCalendarios().get(i)).getHoraEntrada()) + "|";
                  result = result + formatearFechaToHora(((FichaCalendario)getFichasCalendarios().get(i)).getHoraSalida()) + "|";
                  result = result + h + ":" + m;
                  System.out.println(result);
                  ((FichaCalendario)getFichasCalendarios().get(i)).setHoraExtraTiempo(h + ":" + m);
                  ejbFichaCalendarioSB.udtade((FichaCalendario)getFichasCalendarios().get(i));
                }
              }
            }
          }
        }
      }
    }
    

    calcutarTotalPorcentaje();
    calcutarMotoFijoTotal();
  }
  
  public void calcularMultas() {
    List<EscalaMultas> listaEscalaMultas = escalaMultasSB.findByTipoEscala("PORCENTAJE");
    for (int i = 0; i < getFichasCalendarios().size(); i++)
    {





      if ((!StringUtils.isBlank(String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getAnioCalendarioNumero()))) && (!String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getAnioCalendarioNumero()).isEmpty())) {
        String anioAux = String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getAnioCalendarioNumero());
        if ((!StringUtils.isBlank(String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getMesCalendarioNumero()))) && (!String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getMesCalendarioNumero()).isEmpty())) {
          String mesAux = String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getMesCalendarioNumero());
          if ((!StringUtils.isBlank(String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getDiaCalendarioNumero()))) && (!String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getDiaCalendarioNumero()).isEmpty())) {
            String diaAux = String.valueOf(((FichaCalendario)getFichasCalendarios().get(i)).getDiaCalendarioNumero());
            
            if ((!StringUtils.isBlank(((FichaCalendario)getFichasCalendarios().get(i)).getHorarioAsignado().getHoraEntrada())) && (!((FichaCalendario)getFichasCalendarios().get(i)).getHorarioAsignado().getHoraEntrada().isEmpty()) && 
              (!((FichaCalendario)getFichasCalendarios().get(i)).getHorarioAsignado().getHorario().equals("00000000"))) {
              String horaInicio = ((FichaCalendario)getFichasCalendarios().get(i)).getHorarioAsignado().getHoraEntrada();
              if (((FichaCalendario)getFichasCalendarios().get(i)).getHoraEntrada() != null) {
                String horaFin = dateFormatHora.format(((FichaCalendario)getFichasCalendarios().get(i)).getHoraEntrada());
                FechaHora f = new FechaHora();
                f.calcularHorasMinutosMismoDia(anioAux, mesAux, diaAux, horaInicio, horaFin);
                long hor = f.getMinutos() / 60L;
                long rest = f.getMinutos() % 60L;
                if (f.getMinutos() >= 0.0D)
                {



                  String h = "";
                  String m = "";
                  Integer por = Integer.valueOf(0);
                  
                  for (int j = 0; j < listaEscalaMultas.size(); j++) {
                    if (f.getMinutos() >= ((EscalaMultas)listaEscalaMultas.get(j)).getCantidadMinutosDesde()) {
                      h = hor + "";
                      m = rest + "";
                      por = Integer.valueOf(((EscalaMultas)listaEscalaMultas.get(j)).getPorcentajeMulta().intValue());
                    }
                    
                    if ((f.getMinutos() >= ((EscalaMultas)listaEscalaMultas.get(j)).getCantidadMinutosDesde()) && (f.getMinutos() <= ((EscalaMultas)listaEscalaMultas.get(j)).getCantidadMinutosHasta())) {
                      h = hor + "";
                      m = rest + "";
                      por = Integer.valueOf(((EscalaMultas)listaEscalaMultas.get(j)).getPorcentajeMulta().intValue());
                    }
                  }
                  

                  if (por.intValue() > 0) {
                    System.out.println("LLEGADA TARDIA");
                    System.out.println("Fecha: " + getDateFormatFecha().format(((FichaCalendario)getFichasCalendarios().get(i)).getFichaCalendarioPK().getFechaCalendario()));
                    System.out.println("Horas: " + h + " Minutos: " + m);
                    
                    System.out.println("PORCENTAJE: " + por);
                    System.out.println("------");
                    ((FichaCalendario)getFichasCalendarios().get(i)).setPorcentajeMulta(por);
                  }
                }
              }
            }
          }
        }
      }
    }
    



    calcutarTotalPorcentaje();
    calcutarMotoFijoTotal();
  }
  
  public Integer cantidadDiasDelMes(Integer anio, Integer mes) {
    Calendar cal = new GregorianCalendar(anio.intValue(), mes.intValue(), 1);
    int days = cal.getActualMaximum(5);
    return Integer.valueOf(days);
  }
  
  public long cantidadDiasDelPeriodo(Date fechaIni, Date fechaFin) {
    long milSecsPorDia = 86400000L;
    long diferencia = (fechaFin.getTime() - fechaIni.getTime()) / milSecsPorDia;
    return diferencia;
  }
  
  private Calendar dateToCalendar(Date date)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }
  
  private Date calendarToDate(Calendar calendar)
  {
    return calendar.getTime();
  }
  




  private void calcularPeriodo(String tipoFuncionario)
    throws ParseException
  {
    Integer anioIni = getNumeroAnio();
    Integer anioFin = getNumeroAnio();
    String diaIni;
    String diaHas; String mesDesdeString; String mesHastaString; if (tipoFuncionario.equals("JOR")) {
      String diaIni = "11";
      String diaHas = "10";
      Integer mesIni = Integer.valueOf(getNumeroCiclo().intValue() + 1);
      Integer mesHas = Integer.valueOf(mesIni.intValue() + 1);
      
      String mesDesdeString = mesIni + "";
      if (mesIni.intValue() < 10) {
        mesDesdeString = "0" + mesIni;
      }
      
      String mesHastaString = mesHas + "";
      if (mesHas.intValue() < 10) {
        mesHastaString = "0" + mesHas;
      }
      
      if (mesIni.equals(Integer.valueOf(12))) {
        mesHastaString = "01";
        anioFin = Integer.valueOf(anioFin.intValue() + 1);
      }
    } else {
      diaIni = "01";
      diaHas = cantidadDiasDelMes(numeroAnio, numeroMes) + "";
      Integer mesIni = Integer.valueOf(getNumeroMes().intValue() + 1);
      Integer mesHas = mesIni;
      
      mesDesdeString = mesIni + "";
      if (mesIni.intValue() < 10) {
        mesDesdeString = "0" + mesIni;
      }
      
      mesHastaString = mesHas + "";
      if (mesHas.intValue() < 10) {
        mesHastaString = "0" + mesHas;
      }
    }
    
    fechaDesde = getDateFormatFecha().parse(diaIni + "/" + mesDesdeString + "/" + anioIni);
    fechaHasta = getDateFormatFecha().parse(diaHas + "/" + mesHastaString + "/" + anioFin);
  }
  
  public Funcionarios recuperarUsuarioSession() {
    HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    setFuncionarioUsuario((Funcionarios)session.getAttribute("funcionarioUsuario"));
    return getFuncionarioUsuario();
  }
  
  public Funcionarios getFuncionario(String cedula) {
    Funcionarios funcionarioRetorno = ejbFuncionariosSB.findByNumeroDocumento(cedula);
    return funcionarioRetorno;
  }
  
  public List<FichaCalendario> getFichaCalendarioNom(String cedula, Integer mes, Integer anio) {
    List<FichaCalendario> fichaCalendarioRetorno = ejbFichaCalendarioSB.findByCedulaMesAnio(cedula, mes, anio);
    return fichaCalendarioRetorno;
  }
  
  public List<FichaCalendario> getFichaCalendarioJor(String cedula, Date fechaIni, Date fechaFin) {
    List<FichaCalendario> fichaCalendarioRetorno = ejbFichaCalendarioSB.findByCedulaFechaDesdeFechaHasta(cedula, fechaIni, fechaFin);
    return fichaCalendarioRetorno;
  }
  
  public List<AsignacionExcepcionesHorariosDiarios> getAsignacionesHorariosPorFuncionarioNom(String cedula, Integer mes, Integer anio) {
    List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosRetorno = ejbAsignacionExcepcionesHorariosDiariosSB.findByCedulaMesAnio(cedula, mes, anio);
    return asignacionExcepcionesHorariosDiariosRetorno;
  }
  
  public List<AsignacionExcepcionesHorariosDiarios> getAsignacionesHorariosPorFuncionarioJor(String cedula, Date fechaIni, Date fechaFin) {
    List<AsignacionExcepcionesHorariosDiarios> asignacionExcepcionesHorariosDiariosRetorno = ejbAsignacionExcepcionesHorariosDiariosSB.findByCedulaFechaDesdeFechaHasta(cedula, fechaIni, fechaFin);
    return asignacionExcepcionesHorariosDiariosRetorno;
  }
  
  public void calcutarTotalPorcentaje() {
    porcentajeMultaTotal = Integer.valueOf(0);
    if (fichasCalendarios != null) {
      for (int i = 0; i < fichasCalendarios.size(); i++) {
        if (((FichaCalendario)fichasCalendarios.get(i)).getPorcentajeMulta() != null) {
          porcentajeMultaTotal = Integer.valueOf(porcentajeMultaTotal.intValue() + ((FichaCalendario)fichasCalendarios.get(i)).getPorcentajeMulta().intValue());
        }
      }
    }
  }
  
  public void calcutarMotoFijoTotal() {
    montoFijoTotal = Integer.valueOf(0);
    if (fichasCalendarios != null) {
      for (int i = 0; i < fichasCalendarios.size(); i++) {
        if (((FichaCalendario)fichasCalendarios.get(i)).getMontoFijo() != null) {
          montoFijoTotal = Integer.valueOf(montoFijoTotal.intValue() + ((FichaCalendario)fichasCalendarios.get(i)).getMontoFijo().intValue());
        }
      }
    }
  }
  
  public void calcutarDiasTrabajados() {
    diasTrabajados = Integer.valueOf(0);
    if (fichasCalendarios != null) {
      for (int i = 0; i < fichasCalendarios.size(); i++) {
        if ((((FichaCalendario)fichasCalendarios.get(i)).getDiaTrabajado() != null) && 
          (((FichaCalendario)fichasCalendarios.get(i)).getDiaTrabajado().booleanValue())) {
          diasTrabajados = Integer.valueOf(diasTrabajados.intValue() + 1);
        }
      }
    }
  }
  
  public void calcutarDiasComplementarios()
  {
    diasComplementarios = Integer.valueOf(0);
    if (fichasCalendarios != null) {
      for (int i = 0; i < fichasCalendarios.size(); i++) {
        if ((((FichaCalendario)fichasCalendarios.get(i)).getDiaComplementario() != null) && 
          (((FichaCalendario)fichasCalendarios.get(i)).getDiaComplementario().booleanValue())) {
          diasComplementarios = Integer.valueOf(diasComplementarios.intValue() + 1);
        }
      }
    }
  }
  
  public String formatearFechaToHora(Date fecha)
  {
    if (fecha == null) {
      return "";
    }
    return dateFormatHora.format(fecha);
  }
  
  public String formatearFecha(Date fecha)
  {
    if (fecha == null) {
      return "";
    }
    return getDateFormatFecha().format(fecha);
  }
  
  public Integer validarFicha()
  {
    Integer banderaError = Integer.valueOf(0);
    for (int i = 0; i < getFichasCalendarios().size(); i++) {
      if (((FichaCalendario)getFichasCalendarios().get(i)).getHoraNoDefinida() != null) {
        banderaError = Integer.valueOf(1);
      }
    }
    return banderaError;
  }
  
  public void exportarPDFporCedulaMesAnio() throws JRException, IOException, ParseException {
    calcularHorasExtras();
    if (getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
      buscarFichaPorPeriodoCedulaJor();
    } else {
      buscarFichaPorMesAnioCedulaNom();
    }
    
    ReportesFichaCalendario reporte = new ReportesFichaCalendario();
    Map<String, Object> parametros = new HashMap();
    ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
    String urlImagen = ((ServletContext)ctx.getContext()).getRealPath("/resources/images/escudo.gif");
    String urlImagen2 = ((ServletContext)ctx.getContext()).getRealPath("/resources/images/asu128_trans.png");
    parametros.put("urlImagen", urlImagen);
    parametros.put("urlImagen2", urlImagen2);
    parametros.put("nombreDependencia", "Dirección de Recursos Humanos");
    parametros.put("mes", numeroMes);
    parametros.put("anio", numeroAnio);
    parametros.put("fechaGeneracion", new Date());
    parametros.put("cedulaFuncionario", getFuncionario().getNumeroDocumento());
    parametros.put("apellidoNombreFuncionario", getFuncionario().getApellidos() + ", " + getFuncionario().getNombres());
    parametros.put("nombreApellidoFuncionario", getFuncionario().getNombres() + " " + getFuncionario().getApellidos());
    parametros.put("totalRegistros", Integer.valueOf(fichasCalendarios.size()));
    parametros.put("usuarioGeneracion", funcionarioUsuario.getNombres() + " " + funcionarioUsuario.getApellidos());
    parametros.put("SUBREPORT_DIR", "py/gov/mca/sisrrhh/reportes/");
    HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
    String mesLetra;
    if (getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
      String mesLetra = (String)listaCiclos.get(numeroCiclo);
      mesLetra = mesLetra.replace(" ", "");
      mesLetra = mesLetra.replace("-", "_");
    } else {
      mesLetra = (String)listaMeses.get(numeroMes);
    }
    String repo = "ReporteMarcacionCedulaMesAnio";
    System.out.println("ROL: " + funcionarioUsuario.getRolUsuario().getCodigo());
    if (funcionarioUsuario.getRolUsuario().getCodigo().equals("ADM")) {
      repo = "ReporteMarcacionCedulaMesAnioHoraExtra";
    }
    
    reporte.exportarGenerico("pdf", ("marcacion_" + getFuncionario().getNumeroDocumento() + "_" + mesLetra + "_" + numeroAnio).toLowerCase(), response, parametros, fichasCalendarios, repo);
  }
  
  public void exportarCSVporCedula() throws IOException, JRException
  {
    ReportesFichaCalendario reporte = new ReportesFichaCalendario();
    Map<String, Object> parametros = new HashMap();
    
    parametros.put("fechaGeneracion", new Date());
    parametros.put("cedulaFuncionario", getFuncionario().getNumeroDocumento());
    parametros.put("apellidoNombreFuncionario", getFuncionario().getApellidos() + ", " + getFuncionario().getNombres());
    parametros.put("nombreApellidoFuncionario", getFuncionario().getNombres() + " " + getFuncionario().getApellidos());
    parametros.put("usuarioGeneracion", funcionarioUsuario.getNombres() + " " + funcionarioUsuario.getApellidos());
    HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
    
    String mesLetra;
    if (getFuncionario().getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
      String mesLetra = (String)listaCiclos.get(numeroCiclo);
      mesLetra = mesLetra.replace(" ", "");
      mesLetra = mesLetra.replace("-", "_");
    } else {
      mesLetra = (String)listaMeses.get(numeroMes);
    }
    reporte.exportarGenerico("csv", ("marcacion_" + getFuncionario().getNumeroDocumento() + "_" + mesLetra + "_" + numeroAnio).toLowerCase(), response, parametros, fichasCalendarios, "ReporteMarcacionCedula");
  }
  
  public String prepararFormReporteRangoFecha() {
    setCantidadRegistros(0);
    setNombreArchivo("Ningún archivo seleccionado");
    return "admin_funcionario_reporte_rango_fecha";
  }
  
  public void leerArchivoCedulas() throws ParseException {
    setCantidadRegistros(0);
    setNombreArchivo("Ningún archivo seleccionado");
    try {
      String disposition = getFile().getHeader("content-disposition");
      setNombreArchivo(disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1"));
      String tipoArchivo = getFile().getContentType();
      
      if (getNombreArchivo().length() == 15)
      {
        StringBuilder nombreArchivoCorto = new StringBuilder();
        nombreArchivoCorto.append(getNombreArchivo().charAt(0));
        nombreArchivoCorto.append(getNombreArchivo().charAt(1));
        nombreArchivoCorto.append(getNombreArchivo().charAt(2));
        nombreArchivoCorto.append(getNombreArchivo().charAt(3));
        nombreArchivoCorto.append(getNombreArchivo().charAt(4));
        nombreArchivoCorto.append(getNombreArchivo().charAt(5));
        nombreArchivoCorto.append(getNombreArchivo().charAt(6));
        nombreArchivoCorto.append(getNombreArchivo().charAt(7));
        nombreArchivoCorto.append(getNombreArchivo().charAt(8));
        nombreArchivoCorto.append(getNombreArchivo().charAt(9));
        nombreArchivoCorto.append(getNombreArchivo().charAt(10));
        if ((nombreArchivoCorto.toString().equals("cedulas_nom")) || (nombreArchivoCorto.toString().equals("cedulas_jor"))) {
          if (nombreArchivoCorto.toString().equals("cedulas_nom")) {
            tipoReporte = "NOMBRADOS, CONTRATRADOS, ETC.";
            tipoReporte2 = "nom";
          } else {
            tipoReporte = "JORNALEROS";
            tipoReporte2 = "jor";
          }
          
          BufferedReader bf = null;
          try {
            bf = new BufferedReader(new InputStreamReader(getFile().getInputStream()));
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          String line = null;
          try {
            cedulasDesdeArchivo = new ArrayList();
            int contadorLinea = 0;
            while ((line = bf.readLine()) != null) {
              StringTokenizer tokens = new StringTokenizer(line, ";");
              while (tokens.hasMoreTokens()) {
                cedulasDesdeArchivo.add(tokens.nextToken());
              }
            }
            setCantidadRegistros(cedulasDesdeArchivo.size());
          } catch (IOException e) {
            e.printStackTrace();
          }
        } else {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre archivo no valido", ""));
        }
        

      }
      else
      {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Longitud de archivo no valida", ""));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void exportarPDFporCedulaRangoFechas() throws JRException, IOException {
    ReportesFuncionarioMarcacion reporte = new ReportesFuncionarioMarcacion();
    
    List<FuncionarioMarcacion> listaParaReporte = new ArrayList();
    
    List<Funcionarios> funcionariosSegunArchivo = new ArrayList();
    for (int i = 0; i < cedulasDesdeArchivo.size(); i++) {
      Funcionarios func = ejbFuncionariosSB.findByNumeroDocumento(((String)cedulasDesdeArchivo.get(i)).trim());
      if (func != null) {
        funcionariosSegunArchivo.add(func);
      }
    }
    
    for (int i = 0; i < funcionariosSegunArchivo.size(); i++) {
      List<FichaCalendario> fichas = ejbFichaCalendarioSB.findByCedulaFechaDesdeFechaHastaConNull(((Funcionarios)funcionariosSegunArchivo.get(i)).getNumeroDocumento(), fechaDesde, fechaHasta);
      if (fichas != null)
      {



        FuncionarioMarcacion funMarAux = new FuncionarioMarcacion(((Funcionarios)funcionariosSegunArchivo.get(i)).getNumeroDocumento(), ((Funcionarios)funcionariosSegunArchivo.get(i)).getNombres(), ((Funcionarios)funcionariosSegunArchivo.get(i)).getApellidos(), ((Funcionarios)funcionariosSegunArchivo.get(i)).getRelacionLaboral().getDescripcion(), fechaDesde, fechaHasta, fichas);
        


        listaParaReporte.add(funMarAux);
      }
    }
    
    Map<String, Object> parametros = new HashMap();
    ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
    String urlImagen = ((ServletContext)ctx.getContext()).getRealPath("/resources/images/escudo.gif");
    String urlImagen2 = ((ServletContext)ctx.getContext()).getRealPath("/resources/images/asu128_trans.png");
    parametros.put("urlImagen", urlImagen);
    parametros.put("urlImagen2", urlImagen2);
    parametros.put("nombreDependencia", "Dirección de Recursos Humanos");
    parametros.put("mes", numeroMes);
    parametros.put("anio", numeroAnio);
    parametros.put("fechaGeneracion", new Date());
    parametros.put("totalRegistros", Integer.valueOf(listaParaReporte.size()));
    parametros.put("usuarioGeneracion", funcionarioUsuario.getNombres() + " " + funcionarioUsuario.getApellidos());
    parametros.put("SUBREPORT_DIR", "py/gov/mca/sisrrhh/reportes/");
    HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
    
    reporte.exportarGenerico("pdf", new StringBuilder().append("marcacion_rango_fecha_").append(dateFormatFechaGuionBajo.format(fechaDesde)).append("_").append(dateFormatFechaGuionBajo.format(fechaHasta)).toString().toLowerCase() + "_" + tipoReporte2, response, parametros, listaParaReporte, "ReporteMarcacionCedulaConSubReport");
  }
  
  public String guardarFichaPorCedula()
  {
    Integer banderaErrror = Integer.valueOf(0);
    for (int i = 0; i < getFichasCalendarios().size(); i++) {
      if (((FichaCalendario)getFichasCalendarios().get(i)).getHoraNoDefinida() != null)
        banderaErrror = Integer.valueOf(1);
    }
    String retorno;
    String retorno;
    if (banderaErrror.intValue() == 0)
    {

      for (int i = 0; i < getFichasCalendarios().size(); i++)
      {














        ((FichaCalendario)getFichasCalendarios().get(i)).setMarcaCalculoPorcentaje(Integer.valueOf(1));
        ((FichaCalendario)getFichasCalendarios().get(i)).setMarcaFichaVerificada(Boolean.valueOf(true));
        
        ((FichaCalendario)getFichasCalendarios().get(i)).setMesCalendarioNumero(Integer.parseInt(dateFormatMesNumero.format(((FichaCalendario)getFichasCalendarios().get(i)).getFichaCalendarioPK().getFechaCalendario())));
        ((FichaCalendario)getFichasCalendarios().get(i)).setAnioCalendarioNumero(Integer.parseInt(dateFormatAnioNumero.format(((FichaCalendario)getFichasCalendarios().get(i)).getFichaCalendarioPK().getFechaCalendario())));
        


        String descriAudit = "Fecha: " + ((FichaCalendario)getFichasCalendarios().get(i)).getFichaCalendarioPK().getFechaCalendario() + ", PorcentajeMulta: " + ((FichaCalendario)getFichasCalendarios().get(i)).getPorcentajeMulta();
        actualizarFichaCalendario((FichaCalendario)getFichasCalendarios().get(i));
        guardarAuditoria(new Date(), funcionarioUsuario.getUsuario(), funcionarioUsuario.getRolUsuario().getNombre(), "Insert o Update", "ficha_calendario", 
          ((FichaCalendario)getFichasCalendarios().get(i)).getFichaCalendarioPK().getNumeroDocumentoFuncionario(), descriAudit);
      }
      

      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados", ""));
      String retorno; if (funcionario.getRelacionLaboral().getRelacionLaboral().equals("JOR")) {
        retorno = prepararFormFichaGeneralMensualJor();
      } else {
        retorno = prepararFormFichaGeneralMensualNom();
      }
    } else {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen horarios no definidos", "La ficha no se puede guardar"));
      retorno = "";
    }
    return retorno;
  }
  
  public String prepararFormGenerarMultasNom() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    numeroMes = Integer.valueOf(cal.get(2) - 1);
    numeroAnio = Integer.valueOf(cal.get(1));
    return "admin_form_generar_multas_nom_csv";
  }
  
  public String prepararFormGenerarMultasJor() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    numeroCiclo = Integer.valueOf(cal.get(2) - 1);
    numeroAnio = Integer.valueOf(cal.get(1));
    return "admin_form_generar_multas_jor_csv";
  }
  
  public void generarMultasTotalesPorRangoDeFechaEnCsv(String tipoFuncionario) throws IOException, JRException, ParseException {
    calcularPeriodo(tipoFuncionario);
    ReportePorcentajeMultaCsv reporte = new ReportePorcentajeMultaCsv();
    List<MultaCsv> multas = new ArrayList();
    List<Funcionarios> listaFuncionariosActivos = ejbFuncionariosSB.findByEstadoRelacionLaboralFiltro("ACT", tipoFuncionario);
    for (int i = 0; i < listaFuncionariosActivos.size(); i++) {
      Integer sueldoDiario = Integer.valueOf(0);
      Integer totalPorcentajeMulta = Integer.valueOf(0);
      Integer totalMontoFijo = Integer.valueOf(0);
      Integer totalDiasTrabajados = Integer.valueOf(0);
      Integer totalDiasComplementarios = Integer.valueOf(0);
      Integer totalMulta = Integer.valueOf(0);
      fichasCalendarios = ejbFichaCalendarioSB.findByCedulaFechaDesdeFechaHasta(((Funcionarios)listaFuncionariosActivos.get(i)).getNumeroDocumento(), fechaDesde, fechaHasta);
      for (int j = 0; j < fichasCalendarios.size(); j++) {
        if (((FichaCalendario)fichasCalendarios.get(j)).getPorcentajeMulta() != null) {
          totalPorcentajeMulta = Integer.valueOf(totalPorcentajeMulta.intValue() + ((FichaCalendario)fichasCalendarios.get(j)).getPorcentajeMulta().intValue());
        }
        
        if (((FichaCalendario)fichasCalendarios.get(j)).getMontoFijo() != null) {
          totalMontoFijo = Integer.valueOf(totalMontoFijo.intValue() + ((FichaCalendario)fichasCalendarios.get(j)).getMontoFijo().intValue());
        }
        
        if ((((FichaCalendario)fichasCalendarios.get(j)).getDiaTrabajado() != null) && 
          (((FichaCalendario)fichasCalendarios.get(j)).getDiaTrabajado().booleanValue())) {
          totalDiasTrabajados = Integer.valueOf(totalDiasTrabajados.intValue() + 1);
        }
        

        if ((((FichaCalendario)fichasCalendarios.get(j)).getDiaComplementario() != null) && 
          (((FichaCalendario)fichasCalendarios.get(j)).getDiaComplementario().booleanValue())) {
          totalDiasComplementarios = Integer.valueOf(totalDiasComplementarios.intValue() + 1);
        }
        

        ((FichaCalendario)fichasCalendarios.get(j)).setMarcaBloqueo("SI");
      }
      if (((Funcionarios)listaFuncionariosActivos.get(i)).getSueldoDiario() != null) {
        sueldoDiario = Integer.valueOf(((Funcionarios)listaFuncionariosActivos.get(i)).getSueldoDiario().intValue());
        Double montoMultaDiaria = Double.valueOf(((Funcionarios)listaFuncionariosActivos.get(i)).getSueldoDiario().doubleValue() * totalPorcentajeMulta.intValue() / 100.0D);
        montoMultaDiaria = Double.valueOf(Math.ceil(montoMultaDiaria.doubleValue()));
        totalMulta = Integer.valueOf(totalMulta.intValue() + montoMultaDiaria.intValue() + totalMontoFijo.intValue());
        







        MultaCsv multa = new MultaCsv(((Funcionarios)listaFuncionariosActivos.get(i)).getNumeroDocumento(), sueldoDiario, totalPorcentajeMulta, totalMontoFijo, totalDiasTrabajados, totalDiasComplementarios, totalMulta);
        multas.add(multa);
        guardarFichasCalendarios();
      }
    }
    


    String nombreReporte = "ReporteMultasEnCvs";
    if (tipoFuncionario.equals("JOR")) {
      nombreReporte = "ReporteMultasEnCvsJor";
    }
    
    HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
    reporte.exportarGenerico("csv", ("total_multas_" + dateFormatFechaGuionBajo.format(fechaDesde) + "_" + dateFormatFechaGuionBajo.format(fechaHasta) + "_" + tipoFuncionario).toLowerCase(), response, null, multas, nombreReporte);
  }
  
  public void actualizarFichaCalendario(FichaCalendario fichaCalendario) {
    ejbFichaCalendarioSB.udtade(fichaCalendario);
  }
  



  public Integer getNumeroMes()
  {
    return numeroMes;
  }
  


  public void setNumeroMes(Integer numeroMes)
  {
    this.numeroMes = numeroMes;
  }
  


  public Integer getNumeroAnio()
  {
    return numeroAnio;
  }
  


  public void setNumeroAnio(Integer numeroAnio)
  {
    this.numeroAnio = numeroAnio;
  }
  


  public List<FichaCalendario> getFichasCalendarios()
  {
    return fichasCalendarios;
  }
  


  public void setFichasCalendarios(List<FichaCalendario> fichasCalendarios)
  {
    this.fichasCalendarios = fichasCalendarios;
  }
  


  public Funcionarios getFuncionarioUsuario()
  {
    return funcionarioUsuario;
  }
  


  public void setFuncionarioUsuario(Funcionarios funcionarioUsuario)
  {
    this.funcionarioUsuario = funcionarioUsuario;
  }
  


  public LinkedHashMap<Integer, String> getListaMeses()
  {
    return listaMeses;
  }
  


  public void setListaMeses(LinkedHashMap<Integer, String> listaMeses)
  {
    this.listaMeses = listaMeses;
  }
  


  public LinkedHashMap<Integer, String> getListaCiclos()
  {
    return listaCiclos;
  }
  


  public void setListaCiclos(LinkedHashMap<Integer, String> listaCiclos)
  {
    this.listaCiclos = listaCiclos;
  }
  


  public Integer getNumeroCiclo()
  {
    return numeroCiclo;
  }
  


  public void setNumeroCiclo(Integer numeroCiclo)
  {
    this.numeroCiclo = numeroCiclo;
  }
  


  public String getTipoGeneracion()
  {
    return tipoGeneracion;
  }
  


  public void setTipoGeneracion(String tipoGeneracion)
  {
    this.tipoGeneracion = tipoGeneracion;
  }
  


  public boolean isMostrarComponentesPantallaBuscarRangoFechaCedula()
  {
    return mostrarComponentesPantallaBuscarRangoFechaCedula;
  }
  



  public void setMostrarComponentesPantallaBuscarRangoFechaCedula(boolean mostrarComponentesPantallaBuscarRangoFechaCedula)
  {
    this.mostrarComponentesPantallaBuscarRangoFechaCedula = mostrarComponentesPantallaBuscarRangoFechaCedula;
  }
  


  public Integer getPorcentajeMulta()
  {
    return porcentajeMulta;
  }
  


  public void setPorcentajeMulta(Integer porcentajeMulta)
  {
    this.porcentajeMulta = porcentajeMulta;
  }
  


  public Integer getPorcentajeMultaTotal()
  {
    return porcentajeMultaTotal;
  }
  


  public void setPorcentajeMultaTotal(Integer porcentajeMultaTotal)
  {
    this.porcentajeMultaTotal = porcentajeMultaTotal;
  }
  


  public String getCedulaFuncionario()
  {
    return cedulaFuncionario;
  }
  


  public void setCedulaFuncionario(String cedulaFuncionario)
  {
    this.cedulaFuncionario = cedulaFuncionario;
  }
  


  public Funcionarios getFuncionario()
  {
    return funcionario;
  }
  


  public void setFuncionario(Funcionarios funcionario)
  {
    this.funcionario = funcionario;
  }
  


  public Date getFechaDesde()
  {
    return fechaDesde;
  }
  


  public void setFechaDesde(Date fechaDesde)
  {
    this.fechaDesde = fechaDesde;
  }
  


  public Date getFechaHasta()
  {
    return fechaHasta;
  }
  


  public void setFechaHasta(Date fechaHasta)
  {
    this.fechaHasta = fechaHasta;
  }
  


  public Part getFile()
  {
    return file;
  }
  


  public void setFile(Part file)
  {
    this.file = file;
  }
  


  public String getNombreArchivo()
  {
    return nombreArchivo;
  }
  


  public void setNombreArchivo(String nombreArchivo)
  {
    this.nombreArchivo = nombreArchivo;
  }
  


  public int getCantidadRegistros()
  {
    return cantidadRegistros;
  }
  


  public void setCantidadRegistros(int cantidadRegistros)
  {
    this.cantidadRegistros = cantidadRegistros;
  }
  


  public List<String> getCedulasDesdeArchivo()
  {
    return cedulasDesdeArchivo;
  }
  


  public void setCedulasDesdeArchivo(List<String> cedulasDesdeArchivo)
  {
    this.cedulasDesdeArchivo = cedulasDesdeArchivo;
  }
  


  public Integer getMontoFijo()
  {
    return montoFijo;
  }
  


  public void setMontoFijo(Integer montoFijo)
  {
    this.montoFijo = montoFijo;
  }
  


  public Integer getMontoFijoTotal()
  {
    return montoFijoTotal;
  }
  


  public void setMontoFijoTotal(Integer montoFijoTotal)
  {
    this.montoFijoTotal = montoFijoTotal;
  }
  


  public Integer getDiasTrabajados()
  {
    return diasTrabajados;
  }
  


  public void setDiasTrabajados(Integer diasTrabajados)
  {
    this.diasTrabajados = diasTrabajados;
  }
  


  public Integer getDiasComplementarios()
  {
    return diasComplementarios;
  }
  


  public void setDiasComplementarios(Integer diasComplementarios)
  {
    this.diasComplementarios = diasComplementarios;
  }
  


  public boolean isActivarComponentes()
  {
    return activarComponentes;
  }
  


  public void setActivarComponentes(boolean activarComponentes)
  {
    this.activarComponentes = activarComponentes;
  }
  


  public HtmlDataTable getDataTableFichasCalendarios()
  {
    return dataTableFichasCalendarios;
  }
  


  public void setDataTableFichasCalendarios(HtmlDataTable dataTableFichasCalendarios)
  {
    this.dataTableFichasCalendarios = dataTableFichasCalendarios;
  }
  


  public FichaCalendario getFichaCalendario()
  {
    return fichaCalendario;
  }
  


  public void setFichaCalendario(FichaCalendario fichaCalendario)
  {
    this.fichaCalendario = fichaCalendario;
  }
  


  public SimpleDateFormat getDateFormatFecha()
  {
    return dateFormatFecha;
  }
  


  public List<Horarios> getHorarios()
  {
    return this.horarios = horariosSB.findAll();
  }
  


  public void setHorarios(List<Horarios> horarios)
  {
    this.horarios = horarios;
  }
  


  public List<TiposExcepciones> getTiposExcepciones()
  {
    return this.tiposExcepciones = tiposExcepcionesSB.findAll();
  }
  


  public void setTiposExcepciones(List<TiposExcepciones> tiposExcepciones)
  {
    this.tiposExcepciones = tiposExcepciones;
  }
  


  public LinkedHashMap<Integer, String> getListaTipoMarcacion()
  {
    return listaTipoMarcacion;
  }
  


  public void setListaTipoMarcacion(LinkedHashMap<Integer, String> listaTipoMarcacion)
  {
    this.listaTipoMarcacion = listaTipoMarcacion;
  }
  


  public Integer getNumeroTipoMarcacion()
  {
    return numeroTipoMarcacion;
  }
  


  public void setNumeroTipoMarcacion(Integer numeroTipoMarcacion)
  {
    this.numeroTipoMarcacion = numeroTipoMarcacion;
  }
  


  public String getFichaVerificada()
  {
    return fichaVerificada;
  }
  


  public void setFichaVerificada(String fichaVerificada)
  {
    this.fichaVerificada = fichaVerificada;
  }
  


  public Integer getDiasBoolean()
  {
    return diasBoolean;
  }
  


  public void setDiasBoolean(Integer diasBoolean)
  {
    this.diasBoolean = diasBoolean;
  }
  


  public LinkedHashMap<Integer, String> getListaSiNo()
  {
    return listaSiNo;
  }
  


  public void setListaSiNo(LinkedHashMap<Integer, String> listaSiNo)
  {
    this.listaSiNo = listaSiNo;
  }
  


  public String getTipoReporte()
  {
    return tipoReporte;
  }
  


  public void setTipoReporte(String tipoReporte)
  {
    this.tipoReporte = tipoReporte;
  }
  


  public String getTipoReporte2()
  {
    return tipoReporte2;
  }
  


  public void setTipoReporte2(String tipoReporte2)
  {
    this.tipoReporte2 = tipoReporte2;
  }
}