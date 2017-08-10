/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  py.gov.mca.sisrrhh.entitys.FichaCalendario
 */
package py.gov.mca.sisrrhh.clasesaux;

import java.util.Date;
import java.util.List;
import py.gov.mca.sisrrhh.entitys.FichaCalendario;

public class FuncionarioMarcacion {

    private String nroDoc;
    private String nombres;
    private String apellidos;
    private String nombresApellidos;
    private String apellidosNombres;
    private String tipoFuncionario;
    private Date fechaDesde;
    private Date fechaHasta;
    private List<FichaCalendario> listaMarcacion;

    public FuncionarioMarcacion() {
    }

    public FuncionarioMarcacion(String nroDoc, String nombres, String apellidos, String tipoFuncionario, Date fechaDesde, Date fechaHasta, List<FichaCalendario> listaMarcacion) {
        this.nroDoc = nroDoc;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoFuncionario = tipoFuncionario;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.listaMarcacion = listaMarcacion;
        this.nombresApellidos = nombres + " " + apellidos;
        this.apellidosNombres = apellidos + ", " + nombres;
    }

    public String getNroDoc() {
        return this.nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoFuncionario() {
        return this.tipoFuncionario;
    }

    public void setTipoFuncionario(String tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
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

    public List<FichaCalendario> getListaMarcacion() {
        return this.listaMarcacion;
    }

    public void setListaMarcacion(List<FichaCalendario> listaMarcacion) {
        this.listaMarcacion = listaMarcacion;
    }

    public String getNombresApellidos() {
        return this.nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getApellidosNombres() {
        return this.apellidosNombres;
    }

    public void setApellidosNombres(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
    }
}
