/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.controller;

import com.visumbu.wa.admin.service.DashboardService;
import com.visumbu.wa.admin.service.DealerService;
import com.visumbu.wa.utils.DateUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

/**
 *
 * @author netphenix
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value = "topDealersByVisit/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List topDealersByVisit(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getTopDealersByVisit(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "hourlyVisitChart/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List hourlyVisitChart(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.hourlyVisitChart(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "dashboardTickers/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getDashboardTickers(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartTodayDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getDashboardTickers(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "dashboardTickersYesterday/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getDashboardTickersYesterday(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getYesterday(request.getParameter("startDate"));
        Date endDate = DateUtils.getStartTodayDate(request.getParameter("endDate"));
        return dashboardService.getDashboardTickers(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byDeviceType/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByDeviceType(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getByDeviceType(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byLocation/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByLocation(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getByLocation(startDate, endDate, dealerSiteId);
    }
    
    @RequestMapping(value = "byBrowser/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByBrowser(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getByBrowser(startDate, endDate, dealerSiteId);
    }
    
    
    @RequestMapping(value = "byOs/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByOs(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getByOs(startDate, endDate, dealerSiteId);
    }
    
    @RequestMapping(value = "byReferrer/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByReferrer(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getByReferrer(startDate, endDate, dealerSiteId);
    }
    
    @RequestMapping(value = "byMonthlyForSixMonths/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByMonthlyForSixMonths(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        if(DateUtils.getDifferenceInMonths(startDate, endDate) < 6) {
            startDate = DateUtils.getSixMonthsBack(endDate);
        }
        return dashboardService.getByMonthlyForSixMonths(startDate, endDate, dealerSiteId);
    }
    
    @RequestMapping(value = "byDailyForOneMonths/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByDailyForOneMonths(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        if(DateUtils.getDifferenceInMonths(startDate, endDate) < 1) {
            startDate = DateUtils.getOneMonthsBack(endDate);
        }
        return dashboardService.getByDaily(startDate, endDate, dealerSiteId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
