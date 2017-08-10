/*
 * Decompiled with CFR 0_122.
 */
package py.gov.mca.sisrrhh.clasesaux;

public class MultaCsv {

    private String nroDoc;
    private Integer sueldoDiario;
    private Integer porcentaMultaTotal;
    private Integer totalMontoFijo;
    private Integer totalDiasTrabajados;
    private Integer totalDiasComplementarios;
    private Integer totalMulta;

    public MultaCsv() {
    }

    public MultaCsv(String nroDoc, Integer sueldoDiario, Integer porcentaMultaTotal, Integer totalMontoFijo, Integer totalDiasTrabajados, Integer totalDiasComplementarios, Integer totalMulta) {
        this.nroDoc = nroDoc;
        this.sueldoDiario = sueldoDiario;
        this.porcentaMultaTotal = porcentaMultaTotal;
        this.totalMontoFijo = totalMontoFijo;
        this.totalDiasTrabajados = totalDiasTrabajados;
        this.totalDiasComplementarios = totalDiasComplementarios;
        this.totalMulta = totalMulta;
    }

    public String getNroDoc() {
        return this.nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public Integer getPorcentaMultaTotal() {
        return this.porcentaMultaTotal;
    }

    public void setPorcentaMultaTotal(Integer porcentaMultaTotal) {
        this.porcentaMultaTotal = porcentaMultaTotal;
    }

    public Integer getTotalMulta() {
        return this.totalMulta;
    }

    public void setTotalMulta(Integer totalMulta) {
        this.totalMulta = totalMulta;
    }

    public Integer getSueldoDiario() {
        return this.sueldoDiario;
    }

    public void setSueldoDiario(Integer sueldoDiario) {
        this.sueldoDiario = sueldoDiario;
    }

    public Integer getTotalMontoFijo() {
        return this.totalMontoFijo;
    }

    public void setTotalMontoFijo(Integer totalMontoFijo) {
        this.totalMontoFijo = totalMontoFijo;
    }

    public Integer getTotalDiasTrabajados() {
        return this.totalDiasTrabajados;
    }

    public void setTotalDiasTrabajados(Integer totalDiasTrabajados) {
        this.totalDiasTrabajados = totalDiasTrabajados;
    }

    public Integer getTotalDiasComplementarios() {
        return this.totalDiasComplementarios;
    }

    public void setTotalDiasComplementarios(Integer totalDiasComplementarios) {
        this.totalDiasComplementarios = totalDiasComplementarios;
    }
}
