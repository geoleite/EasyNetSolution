    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.easyportal.relatorio;


import br.com.easynet.database.DataSet;
import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.jb.BeanBase;

import br.com.easynet.jb.EasyDownloadJB;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author geoleite
 */
public class RelatorioPortal extends EasySecurityJB {

    private String dtInicio;
    private String dtFim;
    private String status;
    private DataSet ds;
    protected JRDataSource jRDataSource;
    private Map parameters = new HashMap();
    protected ByteArrayOutputStream baos = new ByteArrayOutputStream();
    protected JasperReport jasperReport;
    protected JasperPrint jasperPrint;
    protected JasperExportManager jasperExportManager;
    protected JRXlsExporter jRXlsExporter;
    protected ByteArrayInputStream bais;
    protected EasyDownloadJB easyDownloadJB;
    protected InputStream is;
    protected URL url;
    private String fileName;
    private String tipo;
    protected SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    public void print(String nameRel, String tituloRel) throws Exception {
        parameters.put("titulo", tituloRel);
        parameters.put("pathLogo", application.getRealPath("images/logomarca.jpg"));
        try {
            if (tipo.equalsIgnoreCase("HTML")) {
                baos = new ByteArrayOutputStream();
                JasperFillManager.fillReportToStream(is, baos, getParameters(), jRDataSource);
                bais = new ByteArrayInputStream(baos.toByteArray());
                setFileName(nameRel);
                getRequest().setAttribute(easyDownloadJB.FILE_NAME, getFileName());
                getRequest().setAttribute(easyDownloadJB.DATA, baos.toByteArray());
                getPage().forward("/portal/easydownload.jsp");

            } else if (tipo.equalsIgnoreCase("PDF")) {
                baos = new ByteArrayOutputStream();                
                JasperFillManager.fillReportToStream(is, baos, getParameters(), jRDataSource);
                bais = new ByteArrayInputStream(baos.toByteArray());
                baos = new ByteArrayOutputStream();
                jasperExportManager.exportReportToPdfStream(bais, baos);

                setFileName(nameRel + ".pdf");
                getRequest().setAttribute(easyDownloadJB.CONTENT_TYPE, "application/easypdf");
                getRequest().setAttribute(easyDownloadJB.FILE_NAME, getFileName());
                getRequest().setAttribute(easyDownloadJB.DATA, baos.toByteArray());
                getPage().forward("/portal/easydownload.jsp");

            } else if (tipo.equalsIgnoreCase("XLS")) {
                jasperReport = (JasperReport) JRLoader.loadObject(url);
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jRDataSource);
                JRXlsExporter xls = new JRXlsExporter();
                baos = new ByteArrayOutputStream();
                xls.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                xls.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
                xls.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                xls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                xls.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET, Integer.decode("65000"));
                xls.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                xls.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                xls.exportReport();

                String filename = nameRel + ".xls";
                getRequest().setAttribute(EasyDownloadJB.CONTENT_TYPE, "application/xls");
                getRequest().setAttribute(EasyDownloadJB.FILE_NAME, filename);
                getRequest().setAttribute(EasyDownloadJB.DATA, baos.toByteArray());
                getPage().forward("/portal/easydownload.jsp");
            }

        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * @return the ds
     */
    public DataSet getDs() {
        return ds;
    }

    /**
     * @param ds the ds to set
     */
    public void setDs(DataSet ds) {
        this.ds = ds;
    }

    /**
     * @return the parameters
     */
    public Map getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the dtInicio
     */
    public String getDtInicio() {
        return dtInicio;
    }

    /**
     * @param dtInicio the dtInicio to set
     */
    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    /**
     * @return the dtFim
     */
    public String getDtFim() {
        return dtFim;
    }

    /**
     * @param dtFim the dtFim to set
     */
    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
