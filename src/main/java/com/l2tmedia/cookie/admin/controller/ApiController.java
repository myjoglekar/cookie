/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.controller;

import com.l2tmedia.cookie.admin.service.DashboardService;
import com.l2tmedia.cookie.admin.service.ReportService;
import com.l2tmedia.cookie.admin.service.VisitService;
import com.l2tmedia.cookie.bean.ReportPage;
import com.l2tmedia.cookie.controller.BaseController;
import com.l2tmedia.cookie.utils.DateUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ApiController extends BaseController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value = "v1/cookie", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Object mapService(HttpServletRequest request, HttpServletResponse response) {
        ReportPage page = getPage(request);
        if (page == null) {
            page = new ReportPage();
            page.setCount(50);
            page.setPageNo(1);
            page.setStart(1);
        }
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String expectedFormat = "MM/dd/yyyy";
        if (startDateStr != null) {
            if (!DateUtils.isValidDate(startDateStr, expectedFormat)) {
                System.out.println("Invalid Start Date");
                return new ResponseEntity<String>("Invalid Start Date - Expected Format: " + expectedFormat, HttpStatus.BAD_REQUEST);
            }
        }
        if (endDateStr != null) {
            if (!DateUtils.isValidDate(endDateStr, expectedFormat)) {
                System.out.println("Invalid End Date");
                return new ResponseEntity<String>("Invalid End Date - Expected Format: " + expectedFormat, HttpStatus.BAD_REQUEST);
            }
        }

        Date startDate = com.l2tmedia.cookie.utils.DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = com.l2tmedia.cookie.utils.DateUtils.getEndDate(request.getParameter("endDate"));
        Long timeDiff = DateUtils.dateDiffInSec(endDate, startDate);
        if (timeDiff <= 0) {
            System.out.println("End Date must be greater than Start Date");
            return new ResponseEntity<String>("End Date must be greater than Start Date", HttpStatus.BAD_REQUEST);
        }
        return reportService.getVisitLog(startDate, endDate, page);
    }

    @RequestMapping(value = "v1/cookie", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Map mapServicePost(HttpServletRequest request, HttpServletResponse response) {
        ReportPage page = getPage(request);
        if (page == null) {
            page = new ReportPage();
            page.setCount(50);
            page.setPageNo(1);
            page.setStart(1);
        }
        Date startDate = com.l2tmedia.cookie.utils.DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = com.l2tmedia.cookie.utils.DateUtils.getEndDate(request.getParameter("endDate"));
        return reportService.getVisitLog(startDate, endDate, page);
    }

    @RequestMapping(value = "cookieData", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map downloadReport(HttpServletRequest request, HttpServletResponse response) {
        Integer dealerSiteId = 0;
        Date startDate = com.l2tmedia.cookie.utils.DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = com.l2tmedia.cookie.utils.DateUtils.getEndDate(request.getParameter("endDate"));

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
