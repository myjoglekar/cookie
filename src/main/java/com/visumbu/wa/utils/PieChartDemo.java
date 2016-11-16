package com.visumbu.wa.utils;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.visumbu.wa.report1.bean.FrequencyReportBean;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartDemo {

    public static class HeaderFooterTable extends PdfPageEventHelper {

        protected PdfPTable footer;
        protected PdfPTable header;

        public HeaderFooterTable(PdfPTable footer, PdfPTable header) {
            this.footer = footer;
            this.header = header;
        }

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            header.writeSelectedRows(50, -1, 36, 564, writer.getDirectContent());
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            footer.writeSelectedRows(0, -1, 36, 64, writer.getDirectContent());
        }
    }

    public static void main(String[] args) {
        try {
            writeChartToPDF(new FileOutputStream("f://barchart.pdf"), null);
            //writeChartToPDF("f://piechart.pdf");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PieChartDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writeChartToPDF(OutputStream outputStream, Map dataMap) {
        List<FrequencyReportBean> frequencyData = (List<FrequencyReportBean>)dataMap.get("byFrequency");
        PdfWriter writer = null;

        Document document = new Document(PageSize.A4, 36, 36, 36, 72);

        try {
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(generateBarChart(writer, frequencyData));

            document.newPage();
            document.add(generatePieMediaReferrerChart(writer));
            document.newPage();
            document.add(generatePieUrlReferrerChart(writer));
            document.newPage();

            document.add(createMediaFirstReferrerTable());
            // Table
//            PdfPTable table = new PdfPTable(2);
//            
//            table.setTotalWidth(523);
//            PdfPCell cell = new PdfPCell(new Phrase("This is a test document"));
//            cell.setBackgroundColor(BaseColor.ORANGE);
//            table.addCell(cell);
//            cell = new PdfPCell(new Phrase("This is a copyright notice"));
//            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            table.addCell(cell);
//            HeaderFooterTable event = new HeaderFooterTable(table, table);
//            writer.setPageEvent(event);
//            
//            for (int i = 0; i < 50; i++) {
//                document.add(new Paragraph("Hello World!"));
//            }
//            
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    public static Image generatePieUrlReferrerChart(PdfWriter writer) throws BadElementException {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("China", 19.64);
        dataSet.setValue("India", 17.3);
        dataSet.setValue("United States", 4.54);
        dataSet.setValue("Indonesia", 3.4);
        dataSet.setValue("Brazil", 2.83);
        dataSet.setValue("Pakistan", 2.48);
        dataSet.setValue("Bangladesh", 2.38);

        JFreeChart chart = ChartFactory.createPieChart(
                "Referrer by Url", dataSet, true, false, false);
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate templateBar = contentByte.createTemplate(500, 300);
        Graphics2D graphics2dBar = templateBar.createGraphics(500, 300,
                new DefaultFontMapper());
        Rectangle2D rectangle2dBar = new Rectangle2D.Double(0, 0, 500,
                300);

        chart.draw(graphics2dBar, rectangle2dBar);

        graphics2dBar.dispose();
        contentByte.addTemplate(templateBar, 30, 30);
        Image img = Image.getInstance(templateBar);
        return img;
    }

    public static Image generatePieMediaReferrerChart(PdfWriter writer) throws BadElementException {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("China", 19.64);
        dataSet.setValue("India", 17.3);
        dataSet.setValue("United States", 4.54);
        dataSet.setValue("Indonesia", 3.4);
        dataSet.setValue("Brazil", 2.83);
        dataSet.setValue("Pakistan", 2.48);
        dataSet.setValue("Bangladesh", 2.38);

        JFreeChart chart = ChartFactory.createPieChart(
                "Referrer by Media", dataSet, true, false, false);
        PdfContentByte contentByte = writer.getDirectContent();

        PdfTemplate templateBar = contentByte.createTemplate(500, 300);
        Graphics2D graphics2dBar = templateBar.createGraphics(500, 300,
                new DefaultFontMapper());
        Rectangle2D rectangle2dBar = new Rectangle2D.Double(0, 0, 500,
                300);

        chart.draw(graphics2dBar, rectangle2dBar);

        graphics2dBar.dispose();
        contentByte.addTemplate(templateBar, 30, 30);
        Image img = Image.getInstance(templateBar);
        return img;
        //return templateBar;
    }

    public static Image generateBarChart(PdfWriter writer, List<FrequencyReportBean> frequencyData) throws BadElementException {
        
        
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.setValue(791, "No of Times", "1");
        dataSet.setValue(978, "No of Times", "2");
        dataSet.setValue(1262, "No of Times", "3");
        dataSet.setValue(1650, "No of Times", "4");
        dataSet.setValue(2519, "No of Times", "5 or more");

        JFreeChart chart = ChartFactory.createBarChart(
                "Number of times user visit", "Count", "Number Of Visits",
                dataSet, PlotOrientation.VERTICAL, false, true, false);

        PdfContentByte contentByte = writer.getDirectContent();

        PdfTemplate templatePie = contentByte.createTemplate(500, 300);
        Graphics2D graphics2dPie = templatePie.createGraphics(500, 300,
                new DefaultFontMapper());
        Rectangle2D rectangle2dPie = new Rectangle2D.Double(0, 0, 500,
                300);

        chart.draw(graphics2dPie, rectangle2dPie);

        graphics2dPie.dispose();

        contentByte.addTemplate(templatePie, 30, 30);
        Image img = Image.getInstance(templatePie);
        return img;
    }

    public static PdfPTable createMediaFirstReferrerTable() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{3, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Media First Referrer"));
        cell.setHorizontalAlignment(3);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Domain Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer Domain"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Count"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createMediaLastReferrerTable() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{3, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Media Last Referrer"));
        cell.setHorizontalAlignment(1);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Domain Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer Domain"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Count"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createMediaAssistsTable() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{3, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Media Last Referrer"));
        cell.setHorizontalAlignment(3);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Domain Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer Domain"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Count"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createUrlFirstReferrerTable() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{3, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("URL First Referrer"));
        cell.setHorizontalAlignment(3);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Domain Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer Domain"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Count"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createUrlLastReferrerTable() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{3, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("URL Last Referrer"));
        cell.setHorizontalAlignment(3);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Domain Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer Domain"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Count"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createUrlAssistsTable() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{3, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("URL Last Referrer"));
        cell.setHorizontalAlignment(3);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Domain Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer Domain"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Count"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createDeviceTable() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{3, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Device Perfomance"));
        cell.setHorizontalAlignment(3);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Device Type"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Unique Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        table.addCell("row 2; cell 2");
        return table;
    }

}
