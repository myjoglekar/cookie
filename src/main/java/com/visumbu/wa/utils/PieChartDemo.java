package com.visumbu.wa.utils;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.visumbu.wa.dashboard.bean.DealerVisitBean;
import com.visumbu.wa.dashboard.bean.DeviceTypeBean;
import com.visumbu.wa.dashboard.bean.ReferrerBean;
import com.visumbu.wa.dashboard.bean.ReferrerPageBean;
import com.visumbu.wa.dashboard.bean.VisitGeoReportBean;
import com.visumbu.wa.report.bean.groups.DealerReferrerDomainGroup;
import com.visumbu.wa.report.bean.groups.DealerReferrerTypeGroup;
import com.visumbu.wa.report1.bean.FrequencyReportBean;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
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

    public static class PageNumeration extends PdfPageEventHelper {

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            //            ColumnText ct = new ColumnText(writer.getDirectContent());
//            ct.setSimpleColumn(new Rectangle(36, 832, 559, 810));
//            for (Element e : header) {
//                ct.addElement(e);
//            }
            PdfPTable table = new PdfPTable(2);

            table.setTotalWidth(523);
            PdfPCell cell = new PdfPCell(new Phrase("This is a test document" + writer.getPageNumber()));
            cell.setBackgroundColor(BaseColor.ORANGE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("This is a copyright notice"));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            table.writeSelectedRows(0, -1, 36, 64, writer.getDirectContent());

        }
    }

    public static class HeaderFooterTable extends PdfPageEventHelper {

        protected PdfPTable footer;

        public HeaderFooterTable(PdfPTable footer) {
            this.footer = footer;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                //            ColumnText ct = new ColumnText(writer.getDirectContent());
//            ct.setSimpleColumn(new Rectangle(36, 832, 559, 810));
//            for (Element e : header) {
//                ct.addElement(e);
//            }
                System.out.println("LOCATION PATH " + getClass().getProtectionDomain().getCodeSource().getLocation());
                Rectangle rectangle = new Rectangle(10, 900, 100, 850);
                Image img = Image.getInstance(PieChartDemo.class.getResource("") + "../../../../../../static/img/logos/digital1.png");
                img.scaleToFit(100, 100);
                img.setAbsolutePosition((rectangle.getLeft() + rectangle.getRight()) / 2 - 45, rectangle.getTop() - 50);
                img.setAlignment(Element.ALIGN_TOP);
                writer.getDirectContent().addImage(img);

                footer.writeSelectedRows(0, -1, 36, 64, writer.getDirectContent());
            } catch (BadElementException ex) {
                Logger.getLogger(PieChartDemo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PieChartDemo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(PieChartDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        System.out.println("LOCATION PATH " + PieChartDemo.class.getProtectionDomain().getCodeSource().getLocation());
        List<FrequencyReportBean> frequencyData = (List<FrequencyReportBean>) dataMap.get("byFrequency");
        List<DeviceTypeBean> deviceType = (List<DeviceTypeBean>) dataMap.get("deviceType");
        List<VisitGeoReportBean> locationPerformance = (List<VisitGeoReportBean>) dataMap.get("locationPerformance");

        Map referrerDomainAssistUrl = (Map) dataMap.get("assistReferrerUrl");
        Map referrerDomainAssistMedia = (Map) dataMap.get("assistReferrerMedia");

        List assistReferrerUrl = (List) referrerDomainAssistUrl.get("assistReferrer");
        List assistReferrerMedia = (List) referrerDomainAssistMedia.get("assistReferrer");

        /**
         * Url first Last referrer
         */
        Map extremeReferrerDomain = (Map) dataMap.get("extremeReferrerDomain");
        List urlFirstReferrer = (List) extremeReferrerDomain.get("firstReferrer");
        List urlLastReferrer = (List) extremeReferrerDomain.get("lastReferrer");

        /**
         * Type Last referrer
         */
        Map extremeReferrerType = (Map) dataMap.get("extremeReferrerType");

        List mediaFirstReferrer = (List) extremeReferrerType.get("firstReferrer");
        List mediaLastReferrer = (List) extremeReferrerType.get("lastReferrer");

        PdfWriter writer = null;

        Document document = new Document(PageSize.A4, 36, 36, 72, 72);

        try {
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            PdfPTable table = new PdfPTable(2);

            table.setTotalWidth(523);
            PdfPCell cell = new PdfPCell(new Phrase("This is a test document"));
            cell.setBackgroundColor(BaseColor.ORANGE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("This is a copyright notice"));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
            HeaderFooterTable event = new HeaderFooterTable(table);
            writer.setPageEvent(event);
            PageNumeration pevent = new PageNumeration();
            writer.setPageEvent(pevent);
            // Frequency chart by total uservisit
            document.newPage();
            document.add(generatePieMediaReferrerChart(writer, "Last", mediaLastReferrer));

            document.newPage();

            document.add(generateFrequencyBarChart(writer, frequencyData));
            document.newPage();

            document.add(generatePieUrlReferrerChart(writer, "First", urlFirstReferrer));
            document.newPage();

            document.add(createMediaFirstReferrerTable(mediaFirstReferrer));
            document.newPage();
            document.add(createMediaLastReferrerTable(mediaLastReferrer));
            document.newPage();

            document.add(createMediaAssistsTable(assistReferrerUrl));
            document.newPage();
            document.add(createUrlFirstReferrerTable(urlFirstReferrer));
            document.newPage();
            document.add(createUrlLastReferrerTable(urlLastReferrer));
            document.newPage();

            document.add(createUrlAssistsTable(assistReferrerUrl));
            document.newPage();

            document.add(createDeviceTable(deviceType));
            document.newPage();

            List<ReferrerBean> referrerData = (List<ReferrerBean>) dataMap.get("byReferrer");
            document.add(createReferrerTable(referrerData));
            document.newPage();

            List<DealerVisitBean> vistsByDealer = (List<DealerVisitBean>) dataMap.get("dealerSummary");
            document.add(createByDealerTable(vistsByDealer));

            document.newPage();
            document.add(createLocationTable(locationPerformance));

            List<ReferrerPageBean> referrerPageData = (List<ReferrerPageBean>) dataMap.get("byReferrerPage");
            document.add(createReferrerPageTable(referrerPageData));

            /*
             document.newPage();
            
             document.add(generatePieMediaReferrerChart(writer, "First", mediaFirstReferrer));
             document.newPage();
             document.add(generatePieMediaReferrerChart(writer, "Last", mediaLastReferrer));
             document.newPage();
             */
            // Table
//            for (int i = 0; i < 50; i++) {
//                document.add(new Paragraph("Hello World!"));
//            }
//            
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    public static Image generatePieUrlReferrerChart(PdfWriter writer, String firstOrLast, List<Map> urlFirstReferrer) throws BadElementException {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Iterator<Map> iterator = urlFirstReferrer.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerDomainGroup dealerReferrerDomainGroup = (DealerReferrerDomainGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");
            dataSet.setValue(dealerReferrerDomainGroup.getDomainName(), count);
        }

        JFreeChart chart = ChartFactory.createPieChart(
                firstOrLast + " Referrer by Url", dataSet, true, false, false);
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate templateBar = contentByte.createTemplate(500, 300);
        Graphics2D graphics2dBar = templateBar.createGraphics(500, 300,
                new DefaultFontMapper());
        Rectangle2D rectangle2dBar = new Rectangle2D.Double(0, 0, 500,
                300);

        chart.draw(graphics2dBar, rectangle2dBar);

        graphics2dBar.dispose();
        //contentByte.addTemplate(templateBar, 30, 30);
        Image img = Image.getInstance(templateBar);
        return img;
    }

    public static Image generatePieMediaReferrerChart(PdfWriter writer, String firstOrLast, List mediaFirstReferrer) throws BadElementException {
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (Iterator<Map> iterator = mediaFirstReferrer.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerTypeGroup dealerReferrerTypeGroup = (DealerReferrerTypeGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");

            dataSet.setValue(dealerReferrerTypeGroup.getReferrerType(), count);
        }

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
        // contentByte.addTemplate(templateBar, 30, 30);
        Image img = Image.getInstance(templateBar);
        return img;
        //return templateBar;
    }

    public static Image generateFrequencyBarChart(PdfWriter writer, List<FrequencyReportBean> frequencyData) throws BadElementException {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        System.out.println("DATA FOUND ----> ");
        System.out.println(frequencyData);
        for (Iterator<FrequencyReportBean> iterator = frequencyData.iterator(); iterator.hasNext();) {
            FrequencyReportBean frequencyReportBean = iterator.next();
            dataSet.setValue(frequencyReportBean.getCount(), "No of Times", frequencyReportBean.getNoOfTimes());
        }
//        
//        dataSet.setValue(791, "No of Times", "1");
//        dataSet.setValue(978, "No of Times", "2");
//        dataSet.setValue(1262, "No of Times", "3");
//        dataSet.setValue(1650, "No of Times", "4");
//        dataSet.setValue(2519, "No of Times", "5 or more");

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

        // contentByte.addTemplate(templatePie, 30, 30);
        Image img = Image.getInstance(templatePie);
        return img;
    }

    public static PdfPTable createMediaFirstReferrerTable(List<Map> firstReferrer) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Media First Referrer"));
        cell.setHorizontalAlignment(1);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Domain Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Count"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        for (Iterator<Map> iterator = firstReferrer.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerTypeGroup dealerReferrerDomainGroup = (DealerReferrerTypeGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");
            table.addCell(dealerReferrerDomainGroup.getDomainName());
            table.addCell(dealerReferrerDomainGroup.getReferrerType());
            table.addCell(count + "");
        }

//        table.addCell("row 1; cell 1");
//        table.addCell("row 1; cell 2");
//        table.addCell("row 1; cell 2");
//        table.addCell("row 2; cell 1");
//        table.addCell("row 2; cell 2");
//        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createMediaLastReferrerTable(List lastReferrer) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Media Last Referrer"));
        cell.setHorizontalAlignment(1);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Domain Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Count"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        for (Iterator<Map> iterator = lastReferrer.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerTypeGroup dealerReferrerDomainGroup = (DealerReferrerTypeGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");
            table.addCell(dealerReferrerDomainGroup.getDomainName());
            table.addCell(dealerReferrerDomainGroup.getReferrerType());
            table.addCell(count + "");
        }
        return table;
    }

    public static PdfPTable createUrlFirstReferrerTable(List<Map> firstReferrer) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("URL First Referrer"));
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
        for (Iterator<Map> iterator = firstReferrer.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerDomainGroup dealerReferrerDomainGroup = (DealerReferrerDomainGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");
            table.addCell(dealerReferrerDomainGroup.getDomainName());
            table.addCell(dealerReferrerDomainGroup.getReferrerDomain());
            table.addCell(count + "");
        }
        return table;
    }

    public static PdfPTable createUrlLastReferrerTable(List<Map> lastReferrer) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("URL Last Referrer"));
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
        for (Iterator<Map> iterator = lastReferrer.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerDomainGroup dealerReferrerDomainGroup = (DealerReferrerDomainGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");
            table.addCell(dealerReferrerDomainGroup.getDomainName());
            table.addCell(dealerReferrerDomainGroup.getReferrerDomain());
            table.addCell(count + "");
        }
        return table;
    }

    public static PdfPTable createMediaAssistsTable(List<Map> assistMedia) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Media Assist Referrer"));
        cell.setHorizontalAlignment(1);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Domain Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Count"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        for (Iterator<Map> iterator = assistMedia.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerTypeGroup dealerReferrerDomainGroup = (DealerReferrerTypeGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");
            table.addCell(dealerReferrerDomainGroup.getDomainName());
            table.addCell(dealerReferrerDomainGroup.getReferrerType());
            table.addCell(count + "");
        }
        return table;
    }

    public static PdfPTable createUrlAssistsTable(List<Map> assistReferrer) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("URL Assist Referrer"));
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
        for (Iterator<Map> iterator = assistReferrer.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerDomainGroup dealerReferrerDomainGroup = (DealerReferrerDomainGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");
            table.addCell(dealerReferrerDomainGroup.getDomainName());
            table.addCell(dealerReferrerDomainGroup.getDomainName());
            table.addCell(count + "");
        }
//        table.addCell("row 1; cell 1");
//        table.addCell("row 1; cell 2");
//        table.addCell("row 1; cell 2");
//        table.addCell("row 2; cell 1");
//        table.addCell("row 2; cell 2");
//        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createDeviceTable(List<DeviceTypeBean> deviceType) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Device Perfomance"));
        cell.setHorizontalAlignment(1);
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
        for (Iterator<DeviceTypeBean> iterator = deviceType.iterator(); iterator.hasNext();) {
            DeviceTypeBean deviceTypeBean = iterator.next();
            table.addCell(deviceTypeBean.getDeviceType());
            table.addCell(deviceTypeBean.getVisitCount() + "");
            table.addCell(deviceTypeBean.getUniqueUserCount() + "");
        }
//        table.addCell("row 1; cell 1");
//        table.addCell("row 1; cell 2");
//        table.addCell("row 1; cell 2");
//        table.addCell("row 2; cell 1");
//        table.addCell("row 2; cell 2");
//        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createLocationTable(List<VisitGeoReportBean> deviceType) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Location Perfomance"));
        cell.setHorizontalAlignment(1);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Dealer Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("City"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Unique Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        for (Iterator<VisitGeoReportBean> iterator = deviceType.iterator(); iterator.hasNext();) {
            VisitGeoReportBean geoReportBean = iterator.next();
            table.addCell(geoReportBean.getDealerName());
            table.addCell(geoReportBean.getCity());
            table.addCell(geoReportBean.getVisitCount() + "");
            table.addCell(geoReportBean.getUniqueUserCount() + "");
        }
        return table;
    }

    public static PdfPTable createByDealerTable(List<DealerVisitBean> visitData) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Dealer Summary"));
        cell.setHorizontalAlignment(1);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Dealer Name"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Unique Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        for (Iterator<DealerVisitBean> iterator = visitData.iterator(); iterator.hasNext();) {
            DealerVisitBean visitBean = iterator.next();
            table.addCell(visitBean.getDealerName());
            table.addCell(visitBean.getTotalSiteVisit() + "");
            table.addCell(visitBean.getUniqueUserCount() + "");
        }
        return table;
    }

    public static PdfPTable createReferrerTable(List<ReferrerBean> referrerData) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Referrer Domain"));
        cell.setHorizontalAlignment(1);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer Sites"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Unique Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        for (Iterator<ReferrerBean> iterator = referrerData.iterator(); iterator.hasNext();) {
            ReferrerBean referrerBean = iterator.next();
            table.addCell(referrerBean.getReferrer());
            table.addCell(referrerBean.getVisitCount() + "");
            table.addCell(referrerBean.getUniqueUserCount() + "");
        }
        return table;
    }

    public static PdfPTable createReferrerPageTable(List<ReferrerPageBean> referrerData) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{2, 1, 1});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("By Referrer Table"));
        cell.setHorizontalAlignment(1);
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Referrer Sites"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Unique Visits"));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        for (Iterator<ReferrerPageBean> iterator = referrerData.iterator(); iterator.hasNext();) {
            ReferrerPageBean referrerBean = iterator.next();
            table.addCell(referrerBean.getReferrer());
            table.addCell(referrerBean.getVisitCount() + "");
            table.addCell(referrerBean.getUniqueUserCount() + "");
        }
        return table;
    }

}
