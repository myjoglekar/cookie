package com.l2tmedia.cookie.utils;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.l2tmedia.cookie.dashboard.bean.DealerVisitBean;
import com.l2tmedia.cookie.dashboard.bean.DeviceTypeBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerPageBean;
import com.l2tmedia.cookie.dashboard.bean.VisitGeoReportBean;
import com.l2tmedia.cookie.report.bean.groups.DealerReferrerDomainGroup;
import com.l2tmedia.cookie.report.bean.groups.DealerReferrerTypeGroup;
import com.l2tmedia.cookie.report.bean.FrequencyReportBean;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;
import org.apache.log4j.Logger;

public class PieChartDemo {

    final static Logger logger = Logger.getLogger(PieChartDemo.class);

    public static class CustomRenderer extends BarRenderer {

        final static Logger logger = Logger.getLogger(CustomRenderer.class);

        /**
         * The colors.
         */
        private Paint[] colors;

        /**
         * Creates a new renderer.
         *
         * @param colors the colors.
         */
        public CustomRenderer(final Paint[] colors) {
            this.colors = colors;
        }

        /**
         * Returns the paint for an item. Overrides the default behaviour
         * inherited from AbstractSeriesRenderer.
         *
         * @param row the series.
         * @param column the category.
         *
         * @return The item color.
         */
        public Paint getItemPaint(final int row, final int column) {
            return this.colors[column % this.colors.length];
        }
    }

    public static class ChartDrawingSupplier extends DefaultDrawingSupplier {

        final static Logger logger = Logger.getLogger(PieChartDemo.class);

        public Paint[] paintSequence;
        public int paintIndex;
        public int fillPaintIndex;

        {
            paintSequence = new Paint[]{
                new Color(227, 26, 28),
                new Color(000, 102, 204),
                new Color(102, 051, 153),
                new Color(102, 51, 0),
                new Color(156, 136, 48),
                new Color(153, 204, 102),
                new Color(153, 51, 51),
                new Color(102, 51, 0),
                new Color(204, 153, 51),
                new Color(0, 51, 0)};
        }

        @Override
        public Paint getNextPaint() {
            Paint result
                    = paintSequence[paintIndex % paintSequence.length];
            paintIndex++;
            return result;
        }

        @Override
        public Paint getNextFillPaint() {
            Paint result
                    = paintSequence[fillPaintIndex % paintSequence.length];
            fillPaintIndex++;
            return result;
        }
    }

    public static class PageNumeration extends PdfPageEventHelper {

        final static Logger logger = Logger.getLogger(PageNumeration.class);

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(1);
            table.setTotalWidth(523);
            PdfPCell cell = new PdfPCell(new Phrase("Page Number " + writer.getPageNumber()));
            table.addCell(cell);
            table.writeSelectedRows(0, -1, 36, 64, writer.getDirectContent());
        }
    }

    public static class HeaderFooterTable extends PdfPageEventHelper {

        final static Logger logger = Logger.getLogger(HeaderFooterTable.class);

        protected PdfPTable footer;

        public HeaderFooterTable() {

        }

        public HeaderFooterTable(PdfPTable footer) {
            this.footer = footer;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                logger.debug("LOCATION PATH " + getClass().getProtectionDomain().getCodeSource().getLocation());
                Rectangle rectangle = new Rectangle(10, 900, 100, 850);
                Image img = Image.getInstance(PieChartDemo.class.getResource("") + "../../../../../../static/img/logos/digital1.png");
                img.scaleToFit(100, 100);
                img.setAbsolutePosition((rectangle.getLeft() + rectangle.getRight()) / 2 - 45, rectangle.getTop() - 50);
                img.setAlignment(Element.ALIGN_TOP);
                writer.getDirectContent().addImage(img);
                if (footer != null) {
                    footer.writeSelectedRows(0, -1, 36, 64, writer.getDirectContent());
                }
            } catch (BadElementException ex) {
                logger.error("Error writing to PDF", ex);
            } catch (IOException ex) {
                logger.error("IOException writing to PDF", ex);
            } catch (DocumentException ex) {
                logger.error("DocumentException writing to PDF", ex);
            }
        }
    }

    public static void writeChartToPDF(OutputStream outputStream, Map dataMap) {
        logger.debug("Beginning write chart to PDF");
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
            HeaderFooterTable event = new HeaderFooterTable();
            writer.setPageEvent(event);
            PageNumeration pevent = new PageNumeration();
            writer.setPageEvent(pevent);
            // Frequency chart by total uservisit
            document.newPage();
            if (mediaLastReferrer.size() > 0) {
                document.add(generatePieMediaReferrerChart(writer, "Last", mediaLastReferrer));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            if (frequencyData.size() > 0) {
                document.add(generateFrequencyBarChart(writer, frequencyData));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            if (urlFirstReferrer.size() > 0) {
                document.add(generatePieUrlReferrerChart(writer, "First", urlFirstReferrer));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            document.newPage();
            if (mediaFirstReferrer.size() > 0) {
                document.add(createMediaFirstReferrerTable(mediaFirstReferrer));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            if (mediaLastReferrer.size() > 0) {
                document.add(createMediaLastReferrerTable(mediaLastReferrer));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            if (assistReferrerMedia.size() > 0) {
                document.add(createMediaAssistsTable(assistReferrerMedia));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            if (urlFirstReferrer.size() > 0) {
                document.add(createUrlFirstReferrerTable(urlFirstReferrer));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            if (urlLastReferrer.size() > 0) {
                document.add(createUrlLastReferrerTable(urlLastReferrer));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            if (assistReferrerUrl.size() > 0) {

                document.add(createUrlAssistsTable(assistReferrerUrl));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }

            if (deviceType.size() > 0) {
                document.add(createDeviceTable(deviceType));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            List<ReferrerBean> referrerData = (List<ReferrerBean>) dataMap.get("byReferrer");
            if (referrerData.size() > 0) {
                document.add(createReferrerTable(referrerData));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            List<DealerVisitBean> vistsByDealer = (List<DealerVisitBean>) dataMap.get("dealerSummary");
            if (vistsByDealer.size() > 0) {
                document.add(createByDealerTable(vistsByDealer));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            if (vistsByDealer.size() > 0) {
                document.add(createLocationTable(locationPerformance));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("\n"));
                //document.newPage();
            }
            List<ReferrerPageBean> referrerPageData = (List<ReferrerPageBean>) dataMap.get("byReferrerPage");
            if (referrerPageData.size() > 0) {
                document.add(createReferrerPageTable(referrerPageData));
            }
        } catch (Exception e) {
            logger.error("Error writing chart to PDF", e);
        }
        
        document.close();
        logger.debug("Finished writing chart to PDF");
    }
    

    public static Image generatePieUrlReferrerChart(PdfWriter writer, String firstOrLast, List<Map> urlFirstReferrer) throws BadElementException {
        logger.debug("Generating Pie Url Referrer Chart");
        DefaultPieDataset dataSet = new DefaultPieDataset();
        List<String> legends = new ArrayList<>();

        for (Iterator<Map> iterator = urlFirstReferrer.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerDomainGroup dealerReferrerDomainGroup = (DealerReferrerDomainGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");
            dataSet.setValue(dealerReferrerDomainGroup.getDomainName(), count);
            legends.add(dealerReferrerDomainGroup.getDomainName());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                firstOrLast + " Referrer by Url", dataSet, true, false, false);

        Paint[] paintSequence = new Paint[]{
            new Color(116, 196, 198),
            new Color(34, 137, 149),
            new Color(90, 113, 122),
            new Color(61, 70, 77),
            new Color(241, 136, 60)
        };
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setDrawingSupplier(new ChartDrawingSupplier());
        plot.setBackgroundPaint(Color.white);
        int i = 0;
        for (Iterator<String> iterator = legends.iterator(); iterator.hasNext();) {
            if (i > 4) {
                i = 2;
            }
            String legend = iterator.next();
            plot.setSectionPaint(legend, paintSequence[i++]);
        }

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
        logger.debug("Completed generating Pie Url referrer chart");
        return img;
    }

    public static Image generatePieMediaReferrerChart(PdfWriter writer, String firstOrLast, List mediaFirstReferrer) throws BadElementException {
        logger.debug("Generating Pie Media Referrer Chart");

        DefaultPieDataset dataSet = new DefaultPieDataset();
        List<String> legends = new ArrayList<>();

        for (Iterator<Map> iterator = mediaFirstReferrer.iterator(); iterator.hasNext();) {
            Map referrerMap = iterator.next();
            DealerReferrerTypeGroup dealerReferrerTypeGroup = (DealerReferrerTypeGroup) referrerMap.get("referrer");
            Long count = (Long) referrerMap.get("count");

            dataSet.setValue(dealerReferrerTypeGroup.getReferrerType(), count);
            legends.add(dealerReferrerTypeGroup.getReferrerType());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Referrer by Media", dataSet, true, false, false);

        Paint[] paintSequence = new Paint[]{
            new Color(116, 196, 198),
            new Color(34, 137, 149),
            new Color(90, 113, 122),
            new Color(61, 70, 77),
            new Color(241, 136, 60)
        };
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setDrawingSupplier(new ChartDrawingSupplier());
        plot.setBackgroundPaint(Color.white);
        int i = 0;
        for (Iterator<String> iterator = legends.iterator(); iterator.hasNext();) {
            if (i > 4) {
                i = 2;
            }
            String legend = iterator.next();
            plot.setSectionPaint(legend, paintSequence[i++]);

        }
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
        logger.debug("Completing generating pie Media Referrer Chart");
        return img;
        //return templateBar;
    }

    public static Image generateFrequencyBarChart(PdfWriter writer, List<FrequencyReportBean> frequencyData) throws BadElementException {
        logger.debug("Generating frequency bar chart");

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();

        logger.debug("DATA FOUND ----> ");
        logger.debug(frequencyData);
        for (Iterator<FrequencyReportBean> iterator = frequencyData.iterator(); iterator.hasNext();) {
            FrequencyReportBean frequencyReportBean = iterator.next();
            dataSet.setValue(frequencyReportBean.getCount(), "No of Times", frequencyReportBean.getNoOfTimes());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Number of times user visit", "Count", "Number Of Visits",
                dataSet, PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        final CategoryItemRenderer renderer = new CustomRenderer(
                new Paint[]{new Color(116, 196, 198), new Color(116, 196, 198),
                    new Color(116, 196, 198), new Color(116, 196, 198),
                    new Color(116, 196, 198)
                });
//        renderer.setLabelGenerator(new StandardCategoryLabelGenerator());
        renderer.setItemLabelsVisible(true);
        final ItemLabelPosition p = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 45.0
        );
        renderer.setPositiveItemLabelPosition(p);
        plot.setRenderer(renderer);

        plot.setDrawingSupplier(new ChartDrawingSupplier());

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
        logger.debug("Completed generating frequency bar chart");
        return img;
    }

    public static PdfPTable createMediaFirstReferrerTable(List<Map> firstReferrer) throws DocumentException {
        logger.debug("Creating media first referrer table");

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
        logger.debug("Successfully created media first referrer table");
        return table;
    }

    public static PdfPTable createMediaLastReferrerTable(List lastReferrer) throws DocumentException {
        logger.debug("Creating media last referrer table");

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
        logger.debug("Successfully created media last referrer table");
        return table;
    }

    public static PdfPTable createUrlFirstReferrerTable(List<Map> firstReferrer) throws DocumentException {
        logger.debug("Creating Url first refererrer table");

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
        logger.debug("Successfully created url first referrer table");
        return table;
    }

    public static PdfPTable createUrlLastReferrerTable(List<Map> lastReferrer) throws DocumentException {
        logger.debug("Creating url last referrer table");

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
        logger.debug("Successfully created url last referrer table");
        return table;
    }

    public static PdfPTable createMediaAssistsTable(List<Map> assistMedia) throws DocumentException {
        logger.debug("Creating media assists table");

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
        logger.debug("Successfully created media assists table");
        return table;
    }

    public static PdfPTable createUrlAssistsTable(List<Map> assistReferrer) throws DocumentException {
        logger.debug("Creating Url Assists Table");

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
        logger.debug("Successfully created Url Assists table");
        return table;
    }

    public static PdfPTable createDeviceTable(List<DeviceTypeBean> deviceType) throws DocumentException {
        logger.debug("Creating device table for PDF");

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
        logger.debug("Successfully created device table for PDF.");
        return table;
    }

    public static PdfPTable createLocationTable(List<VisitGeoReportBean> deviceType) throws DocumentException {
        logger.debug("Creating location table for PDF");

        PdfPTable table = new PdfPTable(new float[]{2, 1, 1, 2});
        table.setWidthPercentage(95f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Location Perfomance"));
        cell.setHorizontalAlignment(1);
        cell.setColspan(4);
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
        logger.debug("Successfully created location table for PDF");
        return table;
    }

    public static PdfPTable createByDealerTable(List<DealerVisitBean> visitData) throws DocumentException {
        logger.debug("Creating by-dealer table for PDF");

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
        logger.debug("Successfully created by-dealer table for PDF");
        return table;
    }

    public static PdfPTable createReferrerTable(List<ReferrerBean> referrerData) throws DocumentException {
        logger.debug("Creating referrer table for PDF");

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
        logger.debug("Successfully created referrer table for PDF");
        return table;
    }

    public static PdfPTable createReferrerPageTable(List<ReferrerPageBean> referrerData) throws DocumentException {
        logger.debug("Creating referrer page table for PDF");
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
        logger.debug("Successfully created referrer page table for PDF");
        return table;
    }

}
