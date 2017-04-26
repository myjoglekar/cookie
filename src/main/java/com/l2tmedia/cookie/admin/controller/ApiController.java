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
import com.l2tmedia.cookie.utils.DateValidator;
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
import org.apache.log4j.Logger;

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

    final static Logger logger = Logger.getLogger(ApiController.class);

    @RequestMapping(value = "v1/cookie", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Object mapService(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Cookie to MAP service called: startDate=" + request.getParameter("startDate") + ", endDate=" + request.getParameter("endDate"));
        
        ReportPage page = getPage(request);
        if (page == null) {
            page = new ReportPage();
            page.setCount(50);
            page.setPageNo(1);
            page.setStart(1);
        }
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        
        DateValidator validator = new DateValidator(startDateStr, endDateStr);
        if (validator.validateHasErrors()) {
            List<String> errors = validator.getErrorMessages();
            logger.error("Date validation returned errors: " + errors);
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        } else {
            return reportService.getVisitLog(DateUtils.getStartDate(startDateStr), DateUtils.getEndDate(endDateStr), page);
        }
    }

    @RequestMapping(value = "v1/conversionData", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Object conversionData(HttpServletRequest request, HttpServletResponse response) {
        String startDateStr = request.getParameter("date");
        Date date = DateUtils.getStartDate(startDateStr);
        return reportService.getConversionData(date);
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
        logger.debug("calling function to Download Report in ApiController class where startDate="+startDate+"and endDate="+endDate);
        return dataMap;

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.error(Constants.HTTP_ERROR, e);
    }
}
