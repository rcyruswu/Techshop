import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportGenerator {
    
    public static void generateReport() throws Exception {
        // Load the report design file from resources
        InputStream reportStream = ReportGenerator.class.getResourceAsStream("/report.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(reportStream);
        
        // Compile the report design to a JasperReport object
        net.sf.jasperreports.engine.JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        
        // Create a data source for the report
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getReportData());
        
        // Set the report parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "Example Report");
        
        // Fill the report with data and parameters
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        
        // Export the report to a PDF file
        JasperExportManager.exportReportToPdfFile(jasperPrint, "report.pdf");
    }
    
    private static List<ReportItem> getReportData() {
        List<ReportItem> data = new ArrayList<>();
        data.add(new ReportItem("Item 1", 10.0));
        data.add(new ReportItem("Item 2", 20.0));
        data.add(new ReportItem("Item 3", 30.0));
        return data;
    }
    
    public static class ReportItem {
        private final String name;
        private final Double value;
        
        public ReportItem(String name, Double value) {
            this.name = name;
            this.value = value;
        }

        /**
         *
         * @param title
         * @param description
         * @param imagePath
         */
        public ReportItem(String title, String description, String imagePath) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        public String getName() {
            return name;
        }
        
        public Double getValue() {
            return value;
        }
    }

    private ReportGenerator() {
    }
      

}
