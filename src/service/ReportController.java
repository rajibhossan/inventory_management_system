package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.ReportRecipt;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReportController {

    public String createPDF(List<ReportRecipt> reciptList, String reportName) {

        File f = new File("sendRecipt.pdf");
        File f2 = new File(reportName);

        try {

            InputStream stream = new FileInputStream(f2);
            JasperDesign jasperDesign = JRXmlLoader.load(stream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(reciptList);
            Map<String, Object> parameters = new HashMap();
            parameters.put("ds", ds);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
            OutputStream out = new FileOutputStream(new File(f.getAbsoluteFile().toString()));
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return f.getAbsoluteFile().toString();

    }

    public void createPDF(List<ReportRecipt> reciptList) {

        File f = new File("recipt" + reciptList.get(0).getPhone() + ".pdf");
        File f2 = new File("recipt.jrxml");

        try {

            InputStream stream = new FileInputStream(f2);
            JasperDesign jasperDesign = JRXmlLoader.load(stream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(reciptList);
            Map<String, Object> parameters = new HashMap();
            parameters.put("ds", ds);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
//            OutputStream out = new FileOutputStream(new File(f.getAbsoluteFile().toString()));
//            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

}
