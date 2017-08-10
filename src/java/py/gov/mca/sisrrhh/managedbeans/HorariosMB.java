/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.ejb.EJB
 *  javax.enterprise.context.SessionScoped
 *  javax.faces.application.FacesMessage
 *  javax.faces.application.FacesMessage$Severity
 *  javax.faces.context.FacesContext
 *  javax.inject.Named
 *  javax.servlet.http.Part
 *  py.gov.mca.sisrrhh.entitys.Horarios
 *  py.gov.mca.sisrrhh.sessionbeans.HorariosSB
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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import py.gov.mca.sisrrhh.entitys.Horarios;
import py.gov.mca.sisrrhh.sessionbeans.HorariosSB;

@Named(value = "horariosMB")
@SessionScoped
public class HorariosMB
        implements Serializable {

    @EJB
    private HorariosSB ejHorariosSB;
    private Part file;
    private String nombreArchivo;
    private int cantidadRegistros;
    private boolean mostrarBotonSubirBd;
    private boolean activarBotonSubirBd;
    private List<Horarios> listaHorarios;
    private float progress;

    @PostConstruct
    public void init() {
    }

    public String prepararFormCargaDedeArchivoHorarios() {
        this.cantidadRegistros = 0;
        this.setNombreArchivo("Ning\u00fan archivo seleccionado");
        this.setMostrarBotonSubirBd(false);
        this.setActivarBotonSubirBd(true);
        return "/administracion/admin_form_carga_horarios_desde_archivo";
    }

    public void leerArchivoHorarios() throws ParseException {
        block11:
        {
            this.setCantidadRegistros(0);
            this.setNombreArchivo("Ning\u00fan archivo seleccionado");
            try {
                String disposition = this.getFile().getHeader("content-disposition");
                this.setNombreArchivo(disposition.replaceFirst("(?i)^.*filename=\"([^\"]+)\".*$", "$1"));
                String tipoArchivo = this.getFile().getContentType();
                if (this.getNombreArchivo().length() == 12) {
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
                        if (nombreArchivoCorto.toString().equals("horarios")) {
                            BufferedReader bf = null;
                            try {
                                bf = new BufferedReader(new InputStreamReader(this.getFile().getInputStream()));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            String line = null;
                            try {
                                this.setListaHorarios(new ArrayList<Horarios>());
                                while ((line = bf.readLine()) != null) {
                                    StringTokenizer tokens = new StringTokenizer(line, ";");
                                    while (tokens.hasMoreTokens()) {
                                        String sHorario = tokens.nextToken();
                                        String horaSalida = sHorario.substring(4, 6);
                                        String minSalida = sHorario.substring(6, 8);
                                        String horaEntrada = sHorario.substring(0, 2);
                                        String minEntrada = sHorario.substring(2, 4);
                                        Horarios horario = new Horarios();
                                        horario.setHorario(sHorario);
                                        horario.setHoraEntrada(horaEntrada + ":" + minEntrada);
                                        horario.setHoraSalida(horaSalida + ":" + minSalida);
                                        horario.setDescripcion(horario.getHoraEntrada() + " a " + horario.getHoraSalida());
                                        horario.setCantidadHoras(Integer.valueOf(0));
                                        horario.setCantidadMinutos(Integer.valueOf(0));
                                        System.out.println("Horario: " + horario.getHorario() + " - " + horario.getHoraEntrada() + " - " + horario.getHoraSalida() + " - " + horario.getDescripcion());
                                        this.getListaHorarios().add(horario);
                                    }
                                }
                                this.cantidadRegistros = this.getListaHorarios().size();
                                System.out.println("Can " + this.cantidadRegistros);
                                this.setMostrarBotonSubirBd(true);
                                this.setActivarBotonSubirBd(false);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break block11;
                        }
                        this.setMostrarBotonSubirBd(false);
                        this.setActivarBotonSubirBd(true);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre archivo no valido", ""));
                        break block11;
                    }
                    this.setMostrarBotonSubirBd(false);
                    this.setActivarBotonSubirBd(true);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tipo archivo no valido", ""));
                    break block11;
                }
                this.setMostrarBotonSubirBd(false);
                this.setActivarBotonSubirBd(true);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Longitud de archivo no valida", ""));
            } catch (IOException e) {
                this.setMostrarBotonSubirBd(false);
                this.setActivarBotonSubirBd(true);
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "IOException", e.getMessage()));
            }
        }
    }

    public void guardarDesdeArchivoHorarios() {
        if (this.getListaHorarios() != null) {
            this.activarBotonSubirBd = true;
            this.setProgress(this.getListaHorarios().size() / 2 * 100 / this.getListaHorarios().size());
            int i = 0;
            for (Horarios horario : this.getListaHorarios()) {
                this.ejHorariosSB.udtade(horario);
                if (++i % 1000 == 0) {
                    this.ejHorariosSB.flushAndClear();
                }
                this.setProgress(i * 100 / this.getListaHorarios().size());
            }
            this.ejHorariosSB.flushAndClear();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione otro archivo", ""));
        }
    }

    public void onComplete() {
        this.setProgress(0.0f);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros guardados " + this.getCantidadRegistros(), ""));
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

    public List<Horarios> getListaHorarios() {
        return this.listaHorarios;
    }

    public void setListaHorarios(List<Horarios> listaHorarios) {
        this.listaHorarios = listaHorarios;
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

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
