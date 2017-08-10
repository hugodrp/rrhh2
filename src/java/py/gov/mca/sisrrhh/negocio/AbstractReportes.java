/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.faces.context.FacesContext
 *  javax.servlet.ServletOutputStream
 *  javax.servlet.http.HttpServletResponse
 *  net.sf.jasperreports.engine.JRDataSource
 *  net.sf.jasperreports.engine.JRException
 *  net.sf.jasperreports.engine.JRExporterParameter
 *  net.sf.jasperreports.engine.JasperExportManager
 *  net.sf.jasperreports.engine.JasperFillManager
 *  net.sf.jasperreports.engine.JasperPrint
 *  net.sf.jasperreports.engine.JasperReport
 *  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
 *  net.sf.jasperreports.engine.export.JRCsvExporter
 *  net.sf.jasperreports.engine.export.JRCsvExporterParameter
 *  net.sf.jasperreports.engine.util.JRLoader
 */
package py.gov.mca.sisrrhh.negocio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public abstract class AbstractReportes<T> {

    private Class<T> reporteClass;

    public AbstractReportes(Class<T> reporteClass) {
        this.reporteClass = reporteClass;
    }

    public JasperPrint exportarPDF(Map<String, Object> parametros, List<T> beanListDataSource, String nombreReporte) throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beanListDataSource);
        JasperReport jasper = (JasperReport) JRLoader.loadObject((InputStream) this.getClass().getClassLoader().getResourceAsStream("py/gov/mca/sisrrhh/reportes/" + nombreReporte + ".jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport((JasperReport) jasper, parametros, (JRDataSource) beanCollectionDataSource);
        return jasperPrint;
    }

    public void exportarGenerico(String tipoReporte, String nombreArchivoSalida, HttpServletResponse response, Map<String, Object> parametros, List<T> beanListDataSource, String nombreReporte) throws IOException, JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beanListDataSource);
        JasperReport jasper = (JasperReport) JRLoader.loadObject((InputStream) this.getClass().getClassLoader().getResourceAsStream("py/gov/mca/sisrrhh/reportes/" + nombreReporte + ".jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport((JasperReport) jasper, parametros, (JRDataSource) beanCollectionDataSource);
        ServletOutputStream ouputStream = response.getOutputStream();
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        if ("pdf".equalsIgnoreCase(tipoReporte)) {
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "attachment; filename=" + nombreArchivoSalida + ".pdf");
            JasperExportManager.exportReportToPdfStream((JasperPrint) jasperPrint, (OutputStream) ouputStream);
        } else if ("csv".equalsIgnoreCase(tipoReporte)) {
            response.setContentType("text/csv");
            response.addHeader("Content-disposition", "attachment; filename=" + nombreArchivoSalida + ".csv");
            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, (Object) jasperPrint);
            exporter.setParameter((JRExporterParameter) JRCsvExporterParameter.FIELD_DELIMITER, (Object) ";");
            StringBuffer buffer = new StringBuffer();
            exporter.setParameter(JRCsvExporterParameter.OUTPUT_STRING_BUFFER, (Object) buffer);
            exporter.exportReport();
            response.getOutputStream().write(buffer.toString().getBytes());
            response.flushBuffer();
        }
        ouputStream.flush();
        ouputStream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
}
