/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJB
 *  javax.enterprise.context.SessionScoped
 *  javax.faces.application.FacesMessage
 *  javax.faces.application.FacesMessage$Severity
 *  javax.faces.context.ExternalContext
 *  javax.faces.context.FacesContext
 *  javax.inject.Named
 *  javax.servlet.http.HttpSession
 *  javax.servlet.http.Part
 *  py.gov.mca.sisrrhh.entitys.Auditoria
 *  py.gov.mca.sisrrhh.entitys.Comentarios
 *  py.gov.mca.sisrrhh.entitys.Estados
 *  py.gov.mca.sisrrhh.entitys.Funcionarios
 *  py.gov.mca.sisrrhh.entitys.Horarios
 *  py.gov.mca.sisrrhh.entitys.RelacionLaboral
 *  py.gov.mca.sisrrhh.entitys.Roles
 *  py.gov.mca.sisrrhh.sessionbeans.AuditoriaSB
 *  py.gov.mca.sisrrhh.sessionbeans.EstadosSB
 *  py.gov.mca.sisrrhh.sessionbeans.FuncionariosSB
 *  py.gov.mca.sisrrhh.sessionbeans.HorariosSB
 *  py.gov.mca.sisrrhh.sessionbeans.RelacionLaboralSB
 *  py.gov.mca.sisrrhh.utiles.MsgUtil
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
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import py.gov.mca.sisrrhh.entitys.Auditoria;
import py.gov.mca.sisrrhh.entitys.Comentarios;
import py.gov.mca.sisrrhh.entitys.Estados;
import py.gov.mca.sisrrhh.entitys.Funcionarios;
import py.gov.mca.sisrrhh.entitys.Horarios;
import py.gov.mca.sisrrhh.entitys.RelacionLaboral;
import py.gov.mca.sisrrhh.entitys.Roles;
import py.gov.mca.sisrrhh.sessionbeans.AuditoriaSB;
import py.gov.mca.sisrrhh.sessionbeans.EstadosSB;
import py.gov.mca.sisrrhh.sessionbeans.FuncionariosSB;
import py.gov.mca.sisrrhh.sessionbeans.HorariosSB;
import py.gov.mca.sisrrhh.sessionbeans.RelacionLaboralSB;
import py.gov.mca.sisrrhh.utiles.MsgUtil;

@Named(value = "funcionariosMB")
@SessionScoped
public class FuncionariosMB
        implements Serializable {

    @EJB
    private FuncionariosSB ejbFuncionariosSB;
    @EJB
    private HorariosSB ejHorariosSB;
    @EJB
    private EstadosSB ejbEstadosSB;
    @EJB
    private AuditoriaSB auditoriaSB;
    @EJB
    private RelacionLaboralSB ejbRelacionLaboralSB;
    private final SimpleDateFormat dateFormatFecha = new SimpleDateFormat("dd/MM/yyyy");
    private Part file;
    private String nombreArchivo;
    private int cantidadRegistros;
    private StringBuilder anio;
    private StringBuilder mes;
    private StringBuilder dia;
    private StringBuilder tipoFun;
    private String fechaArchivo;
    private boolean mostrarBotonSubirBd;
    private boolean activarBotonSubirBd;
    private Funcionarios funcionarioUsuario;
    private List<Funcionarios> listaFuncionarios;
    private List<Funcionarios> listaFuncionariosFiltro;
    private float progress;
    private Funcionarios funcionario;
    private Funcionarios slcFuncionario;
    private List<Estados> estados;
    private List<Horarios> horarios;
    private Horarios horario;
    private List<RelacionLaboral> relacionLaborales;
    private RelacionLaboral relacionLaboral;
    private boolean boolNombreApellido;
    private boolean boolRelacionLaboral;
    private boolean boolHorario;
    private boolean boolJornal;
    private boolean boolFechaIngreso;
    private boolean boolEstado;

    @PostConstruct
    public void init() {
        this.funcionario = new Funcionarios();
        this.recuperarUsuarioSession();
    }

    public String prepararFormCargaDedeArchivoHorariosFuncionarios() {
        this.setCantidadRegistros(0);
        this.setNombreArchivo("Ning\u00fan archivo seleccionado");
        this.setMostrarBotonSubirBd(false);
        this.setActivarBotonSubirBd(true);
        return "/admin_form_carga_horarios_fun_desde_archivo";
    }

    public void leerArchivoHorariosFuncionarios() throws ParseException {
        block13:
        {
            this.setCantidadRegistros(0);
            this.setNombreArchivo("Ning\u00fan archivo seleccionado");
            try {
                String disposition = this.getFile().getHeader("content-disposition");
                this.setNombreArchivo(disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1"));
                String tipoArchivo = this.getFile().getContentType();
                System.out.println("Long " + this.getNombreArchivo().length());
                if (this.getNombreArchivo().length() == 21) {
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
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(10));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(11));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(12));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(13));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(14));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(15));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(16));
                        if (nombreArchivoCorto.toString().equals("funcionarios_hora")) {
                            BufferedReader bf = null;
                            try {
                                bf = new BufferedReader(new InputStreamReader(this.getFile().getInputStream()));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            String line = null;
                            try {
                                this.setListaFuncionarios(new ArrayList<Funcionarios>());
                                while ((line = bf.readLine()) != null) {
                                    StringTokenizer tokens = new StringTokenizer(line, ";");
                                    while (tokens.hasMoreTokens()) {
                                        String numeroDocumento = tokens.nextToken();
                                        String horario = tokens.nextToken();
                                        Funcionarios funcionarioEncontrado = this.ejbFuncionariosSB.findByNumeroDocumento(numeroDocumento);
                                        if (funcionarioEncontrado != null) {
                                            Horarios horarioEncontrado = this.ejHorariosSB.findByHorario(horario);
                                            if (horarioEncontrado == null) {
                                                horarioEncontrado = new Horarios();
                                                horarioEncontrado.setHorario("00000000");
                                                System.out.println("Cedula: " + numeroDocumento + ", horario no definido: " + horario);
                                            }
                                            funcionarioEncontrado.setHorarioNormal(horarioEncontrado);
                                            this.getListaFuncionarios().add(funcionarioEncontrado);
                                            continue;
                                        }
                                        System.out.println("Cedula no existe: " + numeroDocumento);
                                    }
                                }
                                this.setCantidadRegistros(this.getListaFuncionarios().size());
                                this.setMostrarBotonSubirBd(true);
                                this.setActivarBotonSubirBd(false);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break block13;
                        }
                        this.setMostrarBotonSubirBd(false);
                        this.setActivarBotonSubirBd(true);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre archivo no valido", ""));
                        break block13;
                    }
                    this.setMostrarBotonSubirBd(false);
                    this.setActivarBotonSubirBd(true);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo archivo no valido", ""));
                    break block13;
                }
                this.setMostrarBotonSubirBd(false);
                this.setActivarBotonSubirBd(true);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Longitud de archivo no valida", ""));
            } catch (IOException e) {
                this.setMostrarBotonSubirBd(false);
                this.setActivarBotonSubirBd(true);
                e.printStackTrace();
            }
        }
    }

    public void guardarDesdeArchivoHorariosFuncionarios() {
        if (this.getListaFuncionarios() != null) {
            this.setActivarBotonSubirBd(true);
            int i = 0;
            for (Funcionarios funcionarioAux : this.getListaFuncionarios()) {
                this.ejbFuncionariosSB.update(funcionarioAux);
                if (++i % 1000 == 0) {
                    this.ejbFuncionariosSB.flushAndClear();
                }
                this.setProgress(i * 100 / this.getListaFuncionarios().size());
            }
            this.ejbFuncionariosSB.flushAndClear();
            String descriAudit = "Nombre de archivo: " + this.getNombreArchivo() + ", Cantidad de registros: " + this.getListaFuncionarios().size();
            this.setListaFuncionarios(null);
            this.setActivarBotonSubirBd(false);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione otro archivo", ""));
        }
    }

    public String prepararFormCargaDedeArchivoFuncionarios() {
        this.setCantidadRegistros(0);
        this.setNombreArchivo("Ning\u00fan archivo seleccionado");
        this.setMostrarBotonSubirBd(false);
        this.setActivarBotonSubirBd(true);
        return "/administracion/admin_form_carga_desde_archivo_funcionarios";
    }

    public void leerArchivoFuncionarios() throws ParseException {
        block13:
        {
            this.setCantidadRegistros(0);
            this.setAnio(new StringBuilder());
            this.setMes(new StringBuilder());
            this.setDia(new StringBuilder());
            this.setTipoFun(new StringBuilder());
            this.setNombreArchivo("Ning\u00fan archivo seleccionado");
            this.setFechaArchivo("DD/MM/AAAA");
            this.setNombreArchivo("Ning\u00fan archivo seleccionado");
            try {
                String disposition = this.file.getHeader("content-disposition");
                this.setNombreArchivo(disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1"));
                String tipoArchivo = this.file.getContentType();
                if (this.getNombreArchivo().length() == 27) {
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
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(10));
                        nombreArchivoCorto.append(this.getNombreArchivo().charAt(11));
                        if (nombreArchivoCorto.toString().equals("funcionarios")) {
                            this.getAnio().append(this.getNombreArchivo().charAt(12));
                            this.getAnio().append(this.getNombreArchivo().charAt(13));
                            this.getAnio().append(this.getNombreArchivo().charAt(14));
                            this.getAnio().append(this.getNombreArchivo().charAt(15));
                            this.getMes().append(this.getNombreArchivo().charAt(16));
                            this.getMes().append(this.getNombreArchivo().charAt(17));
                            this.getDia().append(this.getNombreArchivo().charAt(18));
                            this.getDia().append(this.getNombreArchivo().charAt(19));
                            this.getTipoFun().append(this.getNombreArchivo().charAt(20));
                            this.getTipoFun().append(this.getNombreArchivo().charAt(21));
                            this.getTipoFun().append(this.getNombreArchivo().charAt(22));
                            this.setFechaArchivo(this.getDia() + "/" + this.getMes() + "/" + this.getAnio());
                            BufferedReader bf = null;
                            try {
                                bf = new BufferedReader(new InputStreamReader(this.file.getInputStream()));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            String line = null;
                            try {
                                this.listaFuncionarios = new ArrayList<Funcionarios>();
                                while ((line = bf.readLine()) != null) {
                                    StringTokenizer tokens = new StringTokenizer(line, ";");
                                    while (tokens.hasMoreTokens()) {
                                        Funcionarios funcionarioEncontrado;
                                        String numeroDocumento = tokens.nextToken();
                                        String apellidos = tokens.nextToken();
                                        String nombres = tokens.nextToken();
                                        String relacionLaboral = tokens.nextToken();
                                        String sueldoDiario = tokens.nextToken();
                                        if (sueldoDiario.equals("NOMB")) {
                                            System.out.println("CI " + numeroDocumento);
                                        }
                                        if ((funcionarioEncontrado = this.ejbFuncionariosSB.findByNumeroDocumento(numeroDocumento)) == null) {
                                            funcionarioEncontrado = new Funcionarios();
                                            funcionarioEncontrado.setRelacionLaboral(new RelacionLaboral());
                                            funcionarioEncontrado.setHorarioNormal(new Horarios());
                                            funcionarioEncontrado.setEstadoFuncionario(new Estados());
                                            funcionarioEncontrado.setRolUsuario(new Roles());
                                            funcionarioEncontrado.setNumeroDocumento(numeroDocumento.trim());
                                            funcionarioEncontrado.setApellidos(apellidos.trim());
                                            funcionarioEncontrado.setNombres(nombres.trim());
                                            funcionarioEncontrado.getRelacionLaboral().setRelacionLaboral(relacionLaboral);
                                            funcionarioEncontrado.getHorarioNormal().setHorario("00000000");
                                            funcionarioEncontrado.getEstadoFuncionario().setCodigo("ACT");
                                            funcionarioEncontrado.getRolUsuario().setCodigo("FUN");
                                        }
                                        funcionarioEncontrado.setSueldoDiario(Double.valueOf(Double.parseDouble(sueldoDiario)));
                                        this.listaFuncionarios.add(funcionarioEncontrado);
                                    }
                                }
                                this.cantidadRegistros = this.listaFuncionarios.size();
                                this.setMostrarBotonSubirBd(true);
                                this.setActivarBotonSubirBd(false);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break block13;
                        }
                        this.setMostrarBotonSubirBd(false);
                        this.setActivarBotonSubirBd(true);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre archivo no valido", ""));
                        break block13;
                    }
                    this.setMostrarBotonSubirBd(false);
                    this.setActivarBotonSubirBd(true);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo archivo no valido", ""));
                    break block13;
                }
                this.setMostrarBotonSubirBd(false);
                this.setActivarBotonSubirBd(true);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Longitud de archivo no valida", ""));
            } catch (IOException e) {
                this.setMostrarBotonSubirBd(false);
                this.setActivarBotonSubirBd(true);
                e.printStackTrace();
            }
        }
    }

    public void guardarDesdeArchivoFuncionarios() {
        if (this.listaFuncionarios != null) {
            this.activarBotonSubirBd = true;
            int i = 0;
            for (Funcionarios funcionarioAux : this.listaFuncionarios) {
                this.ejbFuncionariosSB.update(funcionarioAux);
                if (++i % 1000 == 0) {
                    this.ejbFuncionariosSB.flushAndClear();
                }
                this.progress = i * 100 / this.listaFuncionarios.size();
            }
            this.ejbFuncionariosSB.flushAndClear();
            String descriAudit = "Nombre de archivo: " + this.getNombreArchivo() + ", Cantidad de registros: " + this.listaFuncionarios.size();
            this.guardarAuditoria(new Date(), this.getFuncionarioUsuario().getUsuario(), this.getFuncionarioUsuario().getRolUsuario().getNombre(), "Insert o Update", "funcionarios", "", descriAudit);
            this.listaFuncionarios = null;
            this.activarBotonSubirBd = false;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione otro archivo", ""));
        }
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

    public Funcionarios recuperarUsuarioSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.setFuncionarioUsuario((Funcionarios) session.getAttribute("funcionarioUsuario"));
        return this.getFuncionarioUsuario();
    }

    public void onComplete() {
        this.setProgress(0.0f);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros guardados " + this.getCantidadRegistros(), ""));
    }

    public String prepararMostrarListaFuncionarios() {
        this.listaFuncionarios = this.ejbFuncionariosSB.findAll2();
        return "admin_funcionario_lista";
    }

    public String modificarFuncionario(String tipo) {
        this.boolNombreApellido = true;
        this.boolRelacionLaboral = true;
        this.boolHorario = true;
        this.boolJornal = true;
        this.boolFechaIngreso = true;
        this.boolEstado = true;
        if (tipo.endsWith("nombreApellido")) {
            this.boolNombreApellido = false;
        }
        if (tipo.endsWith("relacionLaboral")) {
            this.boolRelacionLaboral = false;
        }
        if (tipo.endsWith("horario")) {
            this.boolHorario = false;
        }
        if (tipo.endsWith("jornal")) {
            this.boolJornal = false;
        }
        if (tipo.endsWith("fechaIngreso")) {
            this.boolFechaIngreso = false;
        }
        if (tipo.endsWith("estado")) {
            this.boolEstado = false;
        }
        this.funcionario = this.ejbFuncionariosSB.findByNumeroDocumento(this.slcFuncionario.getNumeroDocumento());
        if (this.funcionario.getComentariosId() == null) {
            this.funcionario.setComentariosId(new Comentarios());
        }
        System.out.println("boolHorario: " + this.boolHorario);
        System.out.println("funcionario " + this.funcionario.getNombres());
        return "admin_funcionario_from_modificacion";
    }

    public String nuevoFuncionario() {
        this.funcionario = new Funcionarios();
        this.funcionario.setRelacionLaboral(new RelacionLaboral());
        this.funcionario.setHorarioNormal(new Horarios());
        this.funcionario.setEstadoFuncionario(new Estados());
        this.funcionario.setRolUsuario(new Roles("FUN"));
        this.funcionario.setComentariosId(new Comentarios());
        return "admin_funcionario_from_alta";
    }

    public String cancelarFomularios() {
        return this.redireccion("CANCELAR", "");
    }

    public String guardarFomularios(String modo) {
        String campos = this.camposParaAuditoria(modo);
        return this.redireccion(modo, campos);
    }

    public String redireccion(String modo, String campos) {
        String retorno = "admin_funcionario_lista";
        if (!modo.equals("CANCELAR")) {
            System.out.println("MODO " + modo);
            System.out.println("CAMPOS " + campos);
            if (!campos.equals("")) {
                String resultado = this.ejbFuncionariosSB.guardarFuncionarioMantenimiento(this.funcionario, modo, campos, this.recuperarUsuarioSession().getUsuario(), this.recuperarUsuarioSession().getRolUsuario().getNombre());
                if (resultado.equals("OK")) {
                    this.listaFuncionarios = this.ejbFuncionariosSB.findAll2();
                } else {
                    MsgUtil.msg((String) "No se pudo guardar", (String) "Cedula ya existe", (String) "ERROR");
                    retorno = "";
                }
            }
        }
        return retorno;
    }

    private String camposParaAuditoria(String modo) {
        String campos = "";
        switch (modo) {
            case "Update": {
                if (!this.funcionario.getNombres().equals(this.slcFuncionario.getNombres())) {
                    campos = "Nombre anterior: ";
                    campos = campos + this.slcFuncionario.getNombres();
                    campos = campos + ", Nombre actual: ";
                    campos = campos + this.funcionario.getNombres();
                }
                if (!this.funcionario.getApellidos().equals(this.slcFuncionario.getApellidos())) {
                    if (!campos.equals("")) {
                        campos = campos + ", ";
                    }
                    campos = campos + "Apellido anterior: ";
                    campos = campos + this.slcFuncionario.getApellidos();
                    campos = campos + ", Apellido actual: ";
                    campos = campos + this.funcionario.getApellidos();
                }
                if (this.funcionario.getFechaIngreso() != null && !this.funcionario.getFechaIngreso().equals(this.slcFuncionario.getFechaIngreso())) {
                    if (!campos.equals("")) {
                        campos = campos + ", ";
                    }
                    campos = campos + "Fecha Ingreso anterior: ";
                    campos = campos + this.formatearFecha(this.slcFuncionario.getFechaIngreso());
                    campos = campos + ", Fecha Ingreso actual: ";
                    campos = campos + this.formatearFecha(this.funcionario.getFechaIngreso());
                }
                if (!this.funcionario.getSueldoDiario().equals(this.slcFuncionario.getSueldoDiario())) {
                    if (!campos.equals("")) {
                        campos = campos + ", ";
                    }
                    campos = campos + "Jornal anterior: ";
                    campos = campos + this.slcFuncionario.getSueldoDiario();
                    campos = campos + ", Jornal actual: ";
                    campos = campos + this.funcionario.getSueldoDiario();
                }
                if (!this.funcionario.getRelacionLaboral().equals((Object) this.slcFuncionario.getRelacionLaboral())) {
                    if (!campos.equals("")) {
                        campos = campos + ", ";
                    }
                    campos = campos + "Relacion Laboral anterior: ";
                    campos = campos + this.slcFuncionario.getRelacionLaboral().getDescripcion();
                    campos = campos + ", Relacion Laboral actual: ";
                    campos = campos + this.funcionario.getRelacionLaboral().getDescripcion();
                }
                if (!this.funcionario.getHorarioNormal().equals((Object) this.slcFuncionario.getHorarioNormal())) {
                    if (!campos.equals("")) {
                        campos = campos + ", ";
                    }
                    campos = campos + "Horario anterior: ";
                    campos = campos + this.slcFuncionario.getHorarioNormal().getDescripcion();
                    campos = campos + ", Horario actual: ";
                    campos = campos + this.funcionario.getHorarioNormal().getDescripcion();
                }
                if (!this.funcionario.getEstadoFuncionario().equals((Object) this.slcFuncionario.getEstadoFuncionario())) {
                    if (!campos.equals("")) {
                        campos = campos + ", ";
                    }
                    campos = campos + "Estado anterior: ";
                    campos = campos + this.slcFuncionario.getEstadoFuncionario().getDescripcion();
                    campos = campos + ", Estado Laboral actual: ";
                    campos = campos + this.funcionario.getEstadoFuncionario().getDescripcion();
                }
                if (this.funcionario.getComentariosId() == null || this.funcionario.getComentariosId().getDescripcion().equals(this.slcFuncionario.getComentariosId().getDescripcion())) {
                    break;
                }
                if (!campos.equals("")) {
                    campos = campos + ", ";
                }
                campos = campos + "Comentario anterior: ";
                campos = campos + this.slcFuncionario.getComentariosId().getDescripcion();
                campos = campos + ", Comentario Laboral actual: ";
                campos = campos + this.funcionario.getComentariosId().getDescripcion();
                break;
            }
            case "Insert": {
                campos = "Nombre: ";
                campos = campos + this.funcionario.getNombres();
                if (!campos.equals("")) {
                    campos = campos + ", ";
                }
                campos = campos + "Apellido: ";
                if (!(campos = campos + this.funcionario.getApellidos()).equals("")) {
                    campos = campos + ", ";
                }
                campos = campos + "Fecha Ingreso actual: ";
                if (!(campos = campos + this.formatearFecha(this.funcionario.getFechaIngreso())).equals("")) {
                    campos = campos + ", ";
                }
                campos = campos + "Jornal: ";
                if (!(campos = campos + this.funcionario.getSueldoDiario()).equals("")) {
                    campos = campos + ", ";
                }
                campos = campos + "Relacion Laboral: ";
                if (!(campos = campos + this.funcionario.getRelacionLaboral().getDescripcion()).equals("")) {
                    campos = campos + ", ";
                }
                campos = campos + "Horario: ";
                if (!(campos = campos + this.funcionario.getHorarioNormal().getDescripcion()).equals("")) {
                    campos = campos + ", ";
                }
                campos = campos + "Estado: ";
                campos = campos + this.funcionario.getEstadoFuncionario().getDescripcion();
                break;
            }
            default: {
                campos = "";
            }
        }
        return campos;
    }

    public String formatearFecha(Date fecha) {
        if (fecha == null) {
            return "";
        }
        return this.getDateFormatFecha().format(fecha);
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

    public boolean isMostrarBotonSubirBd() {
        return this.mostrarBotonSubirBd;
    }

    public void setMostrarBotonSubirBd(boolean mostrarBotonSubirBd) {
        this.mostrarBotonSubirBd = mostrarBotonSubirBd;
    }

    public boolean isActivarBotonSubirBd() {
        return this.activarBotonSubirBd;
    }

    public void setActivarBotonSubirBd(boolean activarBotonSubirBd) {
        this.activarBotonSubirBd = activarBotonSubirBd;
    }

    public List<Funcionarios> getListaFuncionarios() {
        System.out.println("Tam: " + this.listaFuncionarios.size());
        return this.listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionarios> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public StringBuilder getAnio() {
        return this.anio;
    }

    public void setAnio(StringBuilder anio) {
        this.anio = anio;
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

    public StringBuilder getTipoFun() {
        return this.tipoFun;
    }

    public void setTipoFun(StringBuilder tipoFun) {
        this.tipoFun = tipoFun;
    }

    public String getFechaArchivo() {
        return this.fechaArchivo;
    }

    public void setFechaArchivo(String fechaArchivo) {
        this.fechaArchivo = fechaArchivo;
    }

    public Funcionarios getFuncionarioUsuario() {
        return this.funcionarioUsuario;
    }

    public void setFuncionarioUsuario(Funcionarios funcionarioUsuario) {
        this.funcionarioUsuario = funcionarioUsuario;
    }

    public SimpleDateFormat getDateFormatFecha() {
        return this.dateFormatFecha;
    }

    public Funcionarios getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    public List<Estados> getEstados() {
        this.estados = this.ejbEstadosSB.findAll();
        return this.estados;
    }

    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }

    public List<Horarios> getHorarios() {
        this.horarios = this.ejHorariosSB.findAll();
        return this.horarios;
    }

    public void setHorarios(List<Horarios> horarios) {
        this.horarios = horarios;
    }

    public List<RelacionLaboral> getRelacionLaborales() {
        this.relacionLaborales = this.ejbRelacionLaboralSB.findAll();
        return this.relacionLaborales;
    }

    public void setRelacionLaborales(List<RelacionLaboral> relacionLaborales) {
        this.relacionLaborales = relacionLaborales;
    }

    public RelacionLaboral getRelacionLaboral() {
        return this.relacionLaboral;
    }

    public void setRelacionLaboral(RelacionLaboral relacionLaboral) {
        this.relacionLaboral = relacionLaboral;
    }

    public Horarios getHorario() {
        return this.horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    public List<Funcionarios> getListaFuncionariosFiltro() {
        return this.listaFuncionariosFiltro;
    }

    public void setListaFuncionariosFiltro(List<Funcionarios> listaFuncionariosFiltro) {
        this.listaFuncionariosFiltro = listaFuncionariosFiltro;
    }

    public Funcionarios getSlcFuncionario() {
        return this.slcFuncionario;
    }

    public void setSlcFuncionario(Funcionarios slcFuncionario) {
        this.slcFuncionario = slcFuncionario;
    }

    public boolean isBoolNombreApellido() {
        return this.boolNombreApellido;
    }

    public void setBoolNombreApellido(boolean boolNombreApellido) {
        this.boolNombreApellido = boolNombreApellido;
    }

    public boolean isBoolRelacionLaboral() {
        return this.boolRelacionLaboral;
    }

    public void setBoolRelacionLaboral(boolean boolRelacionLaboral) {
        this.boolRelacionLaboral = boolRelacionLaboral;
    }

    public boolean isBoolHorario() {
        return this.boolHorario;
    }

    public void setBoolHorario(boolean boolHorario) {
        this.boolHorario = boolHorario;
    }

    public boolean isBoolJornal() {
        return this.boolJornal;
    }

    public void setBoolJornal(boolean boolJornal) {
        this.boolJornal = boolJornal;
    }

    public boolean isBoolFechaIngreso() {
        return this.boolFechaIngreso;
    }

    public void setBoolFechaIngreso(boolean boolFechaIngreso) {
        this.boolFechaIngreso = boolFechaIngreso;
    }

    public boolean isBoolEstado() {
        return this.boolEstado;
    }

    public void setBoolEstado(boolean boolEstado) {
        this.boolEstado = boolEstado;
    }
}
