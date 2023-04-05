package  report;

import net.sf.jasperreports.engine.*;
import  utils.PropertiesManager;

import javax.swing.JOptionPane;
import net.sf.jasperreports.export.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Collection;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.sql.Connection;

public class ReportManager
{
    private List<LinkedHashMap<String, Object>> dataList;
    private Connection conn;
    private String templatePath;
    private String fullPathOutput;
    private ExportFormat exportType;
    private Map<String, Object> parameters;
    private JasperPrint jasperPrint;

    public ReportManager() {
        this.dataList = null;
        this.conn = null;
        this.templatePath = null;
        this.fullPathOutput = null;
        this.exportType = null;
        this.parameters = null;
        this.jasperPrint = null;
    }

    public ReportManager(final String templatePath, final String fullPathOutput, final ExportFormat exportType, final Connection conn, final Map<String, Object> parameters) {
        this.dataList = null;
        this.conn = null;
        this.templatePath = null;
        this.fullPathOutput = null;
        this.exportType = null;
        this.parameters = null;
        this.jasperPrint = null;
        this.templatePath = templatePath;
        this.fullPathOutput = fullPathOutput;
        this.exportType = exportType;
        this.conn = conn;
        this.parameters = parameters;
    }

    public ReportManager(final String templatePath, final String fullPathOutput, final ExportFormat exportType, final List<LinkedHashMap<String, Object>> dataList, final Map<String, Object> parameters) {
        this.dataList = null;
        this.conn = null;
        this.templatePath = null;
        this.fullPathOutput = null;
        this.exportType = null;
        this.parameters = null;
        this.jasperPrint = null;
        this.templatePath = templatePath;
        this.fullPathOutput = fullPathOutput;
        this.exportType = exportType;
        this.dataList = dataList;
        this.parameters = parameters;
    }

    public String getTemplateName() {
        return this.templatePath;
    }

    public List<LinkedHashMap<String, Object>> getDateList() {
        return this.dataList;
    }

    public void setDateList(final List<LinkedHashMap<String, Object>> dateList) {
        this.dataList = dateList;
    }

    public Connection getConn() {
        return this.conn;
    }

    public void setTemplateName(final String templateName) {
        this.templatePath = templateName;
    }

    public String getFullPathOutput() {
        return this.fullPathOutput;
    }

    public void setFullPathOutput(final String fullPathPdfOutput) {
        this.fullPathOutput = fullPathPdfOutput;
    }

    public ExportFormat getExportType() {
        return this.exportType;
    }

    public void setExportType(final ExportFormat exportType) {
        this.exportType = exportType;
    }

    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    public void setParameters(final Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public void setConn(final Connection conn) {
        this.conn = conn;
    }

    public void run() throws JRException {
        final String jasperReport = JasperCompileManager.compileReportToFile(this.templatePath);
        if (this.conn == null) {
            final JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource((Collection)this.dataList);
            this.jasperPrint = JasperFillManager.fillReport(jasperReport, (Map)this.parameters, (JRDataSource)beanColDataSource);
        }
        else {
            this.jasperPrint = JasperFillManager.fillReport(jasperReport, (Map)this.parameters, this.conn);
        }
        switch (this.exportType) {
            case EXCEL: {
                try {
                    final Exporter xlsExporter = new Exporter() {
                        @Override
                        public void setExporterInput(ExporterInput exporterInput) {

                        }

                        @Override
                        public void setExporterOutput(ExporterOutput exporterOutput) {

                        }

                        @Override
                        public void setConfiguration(ReportExportConfiguration reportExportConfiguration) {

                        }

                        @Override
                        public void setConfiguration(ExporterConfiguration exporterConfiguration) {

                        }

                        @Override
                        public void setReportContext(ReportContext reportContext) {

                        }

                        @Override
                        public ReportContext getReportContext() {
                            return null;
                        }

                        @Override
                        public void exportReport() throws JRException {

                        }
                    };
                    xlsExporter.setExporterInput((ExporterInput)new SimpleExporterInput(this.jasperPrint));
                    xlsExporter.setExporterOutput((OutputStreamExporterOutput) new SimpleOutputStreamExporterOutput(this.fullPathOutput));
                    final SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
                    xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.valueOf(true));
                    xlsReportConfiguration.setDetectCellType(Boolean.valueOf(true));
                    xlsReportConfiguration.setWhitePageBackground(Boolean.valueOf(false));
                    xlsReportConfiguration.setIgnoreCellBackground(Boolean.valueOf(false));
                    xlsExporter.setConfiguration((XlsReportConfiguration) xlsReportConfiguration);
                    xlsExporter.exportReport();
                    JOptionPane.showMessageDialog(null, PropertiesManager.getInstanceOfPropertiesManager().getProperty("EXPORT_OK"));
                }
                catch (Exception var4) {
                    var4.printStackTrace();
                    JOptionPane.showMessageDialog(null, PropertiesManager.getInstanceOfPropertiesManager().getProperty("EXPORT_ERR"));
                }
                break;
            }
            case PDF: {
                JasperExportManager.exportReportToPdfFile(this.jasperPrint, this.fullPathOutput);
                break;
            }
            case HTML: {
                JasperExportManager.exportReportToHtmlFile(this.jasperPrint, this.fullPathOutput);
                break;
            }
            default: {
                JasperExportManager.exportReportToPdfFile(this.jasperPrint, this.fullPathOutput);
                break;
            }
        }
    }

    public enum ExportFormat
    {
        EXCEL,
        PDF,
        HTML;
    }
}
