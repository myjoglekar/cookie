/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.controller;

import com.visumbu.wa.admin.service.ReportService;
import com.visumbu.wa.admin.service.DealerService;
import com.visumbu.wa.bean.ReportPage;
import com.visumbu.wa.controller.BaseController;
import com.visumbu.wa.utils.DateUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author netphenix
 */
@Controller
@RequestMapping("report")
public class ReportController extends BaseController{

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "visitDetails/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map topDealersByVisit(HttpServletRequest request, HttpServletResponse response, Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        ReportPage page = getPage(request);
        return reportService.getVisitDetailedList(startDate, endDate, page, dealerSiteId);
    }

    @RequestMapping(value = "timeOnSiteReports/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List timeOnSiteReport(HttpServletRequest request, HttpServletResponse response, Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        ReportPage page = getPage(request);
        return reportService.getTimeOnSiteReport(startDate, endDate, page, dealerSiteId);
    }

    @RequestMapping(value = "byFrequency/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByFrequency(HttpServletRequest request, HttpServletResponse response, Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        ReportPage page = getPage(request);
        return reportService.getByFrequency(startDate, endDate, page, dealerSiteId);
    }

    @RequestMapping(value = "formDataList/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getFormDataList(HttpServletRequest request, HttpServletResponse response, Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        ReportPage page = getPage(request);
        return reportService.getFormDataList(startDate, endDate, page, dealerSiteId);
    }

    @RequestMapping(value = "visitDetailsList/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getVisitDetailsList(HttpServletRequest request, HttpServletResponse response, Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        String fingerprint = request.getParameter("fingerprint");
        String visitId = request.getParameter("visitId");
        String sessionId = request.getParameter("sessionId");
        ReportPage page = getPage(request);
        return reportService.getVisitDetailsList(startDate, endDate, page, dealerSiteId, fingerprint, sessionId, visitId);
    }

    @RequestMapping(value = "actionDetailsList/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getActionDetailsList(HttpServletRequest request, HttpServletResponse response, Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        String fingerprint = request.getParameter("fingerprint");
        String visitId = request.getParameter("visitId");
        String sessionId = request.getParameter("sessionId");
        ReportPage page = getPage(request);
        return reportService.getActionDetailsList(startDate, endDate, page, dealerSiteId, fingerprint, sessionId, visitId);
    }


    @RequestMapping(value = "referrerAssistSummary/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map getReferrerAssistSummary(HttpServletRequest request, HttpServletResponse response, Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return reportService.getReferrerAssistSummary(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "extremeReferrerSummary/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map getExtremeReferrerSummary(HttpServletRequest request, HttpServletResponse response, Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return reportService.getReferrerAssistSummary(startDate, endDate, dealerSiteId);
    }
    

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
