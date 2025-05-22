package pe.com.sistradoc.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import pe.com.sistradoc.services.ReportService;
import java.util.Map;
import java.io.ByteArrayOutputStream;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;


import javax.sql.DataSource;
import java.sql.Connection;

import java.util.HashMap;

@Service
public class ReportServiceImp implements ReportService {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public byte[] getReportTramInProgressByDependencia()  throws Exception {
		//Lee el archivo
		InputStream reportStream = getClass().getResourceAsStream("/reports/TramitesInProgressByDependencia.jrxml");
		
		// Compilar el diseño
	    JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
	    
   	    // Llenar el reporte
	    try (Connection conn = dataSource.getConnection()) {
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), conn);

	        // Exportar a PDF
	        return JasperExportManager.exportReportToPdf(jasperPrint);
	    } catch (Exception e) {
			 return null;
		}
	}
	
	@Override
	public byte[] getReportTramInProgressByTipoTramite()  throws Exception {
		//Lee el archivo
		InputStream reportStream = getClass().getResourceAsStream("/reports/TramitesInProgressByTipoTramite.jrxml");
		
		// Compilar el diseño
	    JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
	    
   	    // Llenar el reporte
	    try (Connection conn = dataSource.getConnection()) {
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), conn);

	        // Exportar a PDF
	        return JasperExportManager.exportReportToPdf(jasperPrint);
	    } catch (Exception e) {
			 return null;
		}
	}

}
