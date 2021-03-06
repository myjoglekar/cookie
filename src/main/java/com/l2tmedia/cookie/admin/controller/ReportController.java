/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.controller;

import com.l2tmedia.cookie.Constants;
import com.l2tmedia.cookie.admin.service.DashboardService;
import com.l2tmedia.cookie.admin.service.ReportService;
import com.l2tmedia.cookie.bean.ReportPage;
import com.l2tmedia.cookie.controller.BaseController;
import com.l2tmedia.cookie.utils.DateUtils;
import com.l2tmedia.cookie.utils.PieChartDemo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.log4j.Logger;

/**
 *
 * @author netphenix
 */
@Controller
@RequestMapping("report")
public class ReportController extends BaseController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private DashboardService dashboardService;

    final static Logger logger = Logger.getLogger(ReportController.class);

    @RequestMapping(value = "visitDetails/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map topDealersByVisit(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        ReportPage page = getPage(request);
        logger.debug("Requesting top dealers by visit: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return reportService.getVisitDetailedList(startDate, endDate, page, dealerSiteId);
    }

    @RequestMapping(value = "timeOnSiteReports/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List timeOnSiteReport(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        ReportPage page = getPage(request);
        logger.debug("Requesting time on site report: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return reportService.getTimeOnSiteReport(startDate, endDate, page, dealerSiteId);
    }

    @RequestMapping(value = "byConversionFrequency/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByConversionFrequency(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        ReportPage page = getPage(request);
        logger.debug("Requesting by conversion frequency: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return reportService.getByConversionFrequency(startDate, endDate, page, dealerSiteId);
    }

    @RequestMapping(value = "byFrequency/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByFrequency(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        ReportPage page = getPage(request);
        logger.debug("Requesting by frequency: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return reportService.getByFrequency(startDate, endDate, page, dealerSiteId);
    }

    @RequestMapping(value = "formDataList/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map getFormDataList(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        ReportPage page = getPage(request);
        logger.debug("Requesting form data list: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return reportService.getFormDataList(startDate, endDate, page, dealerSiteId);
    }

    @RequestMapping(value = "visitDetailsList/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map getVisitDetailsList(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        String fingerprint = request.getParameter("fingerprint");
        String visitId = request.getParameter("visitId");
        String sessionId = request.getParameter("sessionId");
        ReportPage page = getPage(request);
        logger.debug("Requesting visit details list: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return reportService.getVisitDetailsList(startDate, endDate, page, dealerSiteId, fingerprint, sessionId, visitId);
    }

    @RequestMapping(value = "actionDetailsList/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getActionDetailsList(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        String fingerprint = request.getParameter("fingerprint");
        String visitId = request.getParameter("visitId");
        String sessionId = request.getParameter("sessionId");
        ReportPage page = getPage(request);
        logger.debug("Requesting action details list: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return reportService.getActionDetailsList(startDate, endDate, page, dealerSiteId, fingerprint, sessionId, visitId);
    }

    @RequestMapping(value = "referrerAssistSummary/{type}/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map getReferrerAssistSummary(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        if (type.equalsIgnoreCase("media")) {
            return reportService.getReferrerTypeAssistSummary(startDate, endDate, dealerSiteId);
        }
        if (type.equalsIgnoreCase("url")) {
            return reportService.getReferrerDomainAssistSummary(startDate, endDate, dealerSiteId);
        }
        logger.debug("Requesting referrer assist summary: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return null;
    }

    @RequestMapping(value = "extremeReferrerSummary/{type}/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map getExtremeReferrerSummary(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        if (type.equalsIgnoreCase("media")) {
            return reportService.getExtremeReferrerTypeSummary(startDate, endDate, dealerSiteId);
        }
        if (type.equalsIgnoreCase("url")) {
            return reportService.getExtremeReferrerDomainSummary(startDate, endDate, dealerSiteId);
        }
        logger.debug("Requesting extreme referrer summary: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return null;
    }

    @RequestMapping(value = "cookieData/service", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map downloadReport(HttpServletRequest request, HttpServletResponse response) {
        Integer dealerSiteId = 0;
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        
        logger.debug("Requesting report download: startDate=" + startDate + ", endDate=" + endDate);
        
        Map dataMap = new HashMap();
        dataMap.put("byFrequency", reportService.getByFrequency(startDate, endDate, null, dealerSiteId));
        dataMap.put("referrerDomainAssist", reportService.getReferrerDomainAssistSummary(startDate, endDate, dealerSiteId));
        dataMap.put("referrerTypeAssist", reportService.getReferrerTypeAssistSummary(startDate, endDate, dealerSiteId));

        dataMap.put("extremeReferrerDomain", reportService.getExtremeReferrerDomainSummary(startDate, endDate, dealerSiteId));
        dataMap.put("extremeReferrerType", reportService.getExtremeReferrerTypeSummary(startDate, endDate, dealerSiteId));

        dataMap.put("assistReferrerMedia", reportService.getReferrerTypeAssistSummary(startDate, endDate, dealerSiteId));
        dataMap.put("assistReferrerUrl", reportService.getReferrerDomainAssistSummary(startDate, endDate, dealerSiteId));
        dataMap.put("deviceType", dashboardService.getByDeviceType(startDate, endDate, dealerSiteId));
        dataMap.put("locationPerformance", dashboardService.getByGeoReport(startDate, endDate, dealerSiteId));

        dataMap.put("byReferrer", dashboardService.getByReferrer(startDate, endDate, dealerSiteId));
        dataMap.put("byReferrerPage", dashboardService.getByReferrerPage(startDate, endDate, dealerSiteId));
        dataMap.put("dealerSummary", dashboardService.getTopDealersByVisit(startDate, endDate, dealerSiteId));

        return dataMap;
    }

    @RequestMapping(value = "downloadReportPdf/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map downloadReport(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        
        logger.debug("Requesting PDF Report download: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        
        HttpSession session = request.getSession();
        try {
            String filename = "Report.pdf";
            response.setContentType("application/octet-stream");
            response.addHeader("content-disposition", "attachment; filename=\"" + filename + "\"");
            OutputStream out = response.getOutputStream();
            Map dataMap = new HashMap();
            dataMap.put("byFrequency", reportService.getByFrequency(startDate, endDate, null, dealerSiteId));
            dataMap.put("referrerDomainAssist", reportService.getReferrerDomainAssistSummary(startDate, endDate, dealerSiteId));
            dataMap.put("referrerTypeAssist", reportService.getReferrerTypeAssistSummary(startDate, endDate, dealerSiteId));

            dataMap.put("extremeReferrerDomain", reportService.getExtremeReferrerDomainSummary(startDate, endDate, dealerSiteId));
            dataMap.put("extremeReferrerType", reportService.getExtremeReferrerTypeSummary(startDate, endDate, dealerSiteId));

            dataMap.put("assistReferrerMedia", reportService.getReferrerTypeAssistSummary(startDate, endDate, dealerSiteId));
            dataMap.put("assistReferrerUrl", reportService.getReferrerDomainAssistSummary(startDate, endDate, dealerSiteId));
            dataMap.put("deviceType", dashboardService.getByDeviceType(startDate, endDate, dealerSiteId));
            dataMap.put("locationPerformance", dashboardService.getByGeoReport(startDate, endDate, dealerSiteId));

            dataMap.put("byReferrer", dashboardService.getByReferrer(startDate, endDate, dealerSiteId));
            dataMap.put("byReferrerPage", dashboardService.getByReferrerPage(startDate, endDate, dealerSiteId));
            dataMap.put("dealerSummary", dashboardService.getTopDealersByVisit(startDate, endDate, dealerSiteId));

            PieChartDemo.writeChartToPDF(out, dataMap);
            out.flush();
            out.close();

        } catch (FileNotFoundException ex) {
            logger.error("Error loading PDF Report, file not found.", ex);
        } catch (IOException ex) {
            logger.error("Error writing PDF report.", ex);
        }
        return null;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.error(Constants.HTTP_ERROR, e);
    }
}
