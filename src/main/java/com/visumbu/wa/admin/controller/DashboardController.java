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

    @RequestMapping(value = "topDealersByVisit", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List topDealersByVisit(HttpServletRequest request, HttpServletResponse response) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getTopDealersByVisit(startDate, endDate);
    }

    @RequestMapping(value = "hourlyVisitChart", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List hourlyVisitChart(HttpServletRequest request, HttpServletResponse response) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.hourlyVisitChart(startDate, endDate);
    }

    @RequestMapping(value = "dashboardTickers", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getDashboardTickers(HttpServletRequest request, HttpServletResponse response) {
        Date startDate = DateUtils.getStartTodayDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getDashboardTickers(startDate, endDate);
    }

    @RequestMapping(value = "dashboardTickersYesterday", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getDashboardTickersYesterday(HttpServletRequest request, HttpServletResponse response) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getStartTodayDate(request.getParameter("endDate"));
        return dashboardService.getDashboardTickers(startDate, endDate);
    }

    @RequestMapping(value = "byDeviceType", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByDeviceType(HttpServletRequest request, HttpServletResponse response) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getByDeviceType(startDate, endDate);
    }

    @RequestMapping(value = "byLocation", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByLocation(HttpServletRequest request, HttpServletResponse response) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getByLocation(startDate, endDate);
    }
    @RequestMapping(value = "byBrowser", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByBrowser(HttpServletRequest request, HttpServletResponse response) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getByBrowser(startDate, endDate);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
