/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.controller;

import com.visumbu.wa.admin.service.DashboardService;
import com.visumbu.wa.admin.service.ReportService;
import com.visumbu.wa.admin.service.VisitService;
import java.util.Date;
import java.util.HashMap;
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
 * @author jp
 */
@Controller
@RequestMapping("api")
public class ApiController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private DashboardService dashboardService;
    

    @RequestMapping(value = "v1/cookie", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List mapService(HttpServletRequest request, HttpServletResponse response) {
        Date startDate = com.visumbu.wa.utils.DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = com.visumbu.wa.utils.DateUtils.getEndDate(request.getParameter("endDate"));
        return reportService.getVisitLog(startDate, endDate);
    }
    
    @RequestMapping(value = "v1/cookie", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List mapServicePost(HttpServletRequest request, HttpServletResponse response) {
        Date startDate = com.visumbu.wa.utils.DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = com.visumbu.wa.utils.DateUtils.getEndDate(request.getParameter("endDate"));
        return reportService.getVisitLog(startDate, endDate);
    }
    
    @RequestMapping(value = "cookieData", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map downloadReport(HttpServletRequest request, HttpServletResponse response) {
        Integer dealerSiteId = 0;
        Date startDate = com.visumbu.wa.utils.DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = com.visumbu.wa.utils.DateUtils.getEndDate(request.getParameter("endDate"));

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

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
