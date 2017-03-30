/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.controller;

import com.l2tmedia.cookie.admin.service.DashboardService;
import com.l2tmedia.cookie.admin.service.DealerService;
import com.l2tmedia.cookie.dashboard.bean.DealerVisitBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerPageBean;
import com.l2tmedia.cookie.utils.DateUtils;
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
import org.apache.log4j.Logger;

/**
 *
 * @author netphenix
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    final static Logger logger = Logger.getLogger(DashboardService.class);

    @RequestMapping(value = "topDealersByVisit/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<DealerVisitBean> topDealersByVisit(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Top Dealers Visit in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("End  function of Top Dealers Visit  in DashboardController class");
        return dashboardService.getTopDealersByVisit(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "hourlyVisitChart/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List hourlyVisitChart(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Hourly Visit Chart in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("End  function of Hourly Visit Chart  in DashboardController class");
        return dashboardService.hourlyVisitChart(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "dashboardTickers/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getDashboardTickers(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Dashboard Tickers in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("End  function of Dashboard Tickers  in DashboardController class");
        return dashboardService.getDashboardTickers(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "dashboardTickersYesterday/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getDashboardTickersYesterday(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Get Dashboard Tickers Yesterday in DashboardController class");
        Date startDate = DateUtils.getYesterday(request.getParameter("startDate"));
        Date endDate = DateUtils.getStartTodayDate(request.getParameter("endDate"));
        logger.debug("End  function of Top Get Dashboard Tickers  in DashboardController class");
        return dashboardService.getDashboardTickers(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byDeviceType/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByDeviceType(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Get Device Type in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("End  function of Get Device type  in DashboardController class");
        return dashboardService.getByDeviceType(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byReferrerPage/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ReferrerPageBean> getByReferrerPage(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Get Referrer Page in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("End  function of Referrer Page  in DashboardController class");
        return dashboardService.getByReferrerPage(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byGeoReport/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByGeoReport(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Get Geo Report in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("End  function of Get Geo Report  in DashboardController class");
        return dashboardService.getByGeoReport(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byBrowser/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByBrowser(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Get Browser in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("End  function of GEt Browser  in DashboardController class");
        return dashboardService.getByBrowser(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byOs/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByOs(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Get By OS in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("End  function of Get By OS  in DashboardController class");
        return dashboardService.getByOs(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byReferrer/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ReferrerBean> getByReferrer(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        return dashboardService.getByReferrer(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byMonthlyForSixMonths/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByMonthlyForSixMonths(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Get Monthly for six months in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        if (DateUtils.getDifferenceInMonths(startDate, endDate) < 6) {
            startDate = DateUtils.getSixMonthsBack(endDate);
        }
        logger.debug("End  function of Get Monthly for six months in DashboardController class");
        return dashboardService.getByMonthlyForSixMonths(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byDailyForOneMonths/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByDailyForOneMonths(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        logger.debug("Start function of Get Daily for one month Visit in DashboardController class");
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        if (DateUtils.getDifferenceInMonths(startDate, endDate) < 1) {
            startDate = DateUtils.getOneMonthsBack(endDate);
        }
        logger.debug("End  function of Get Daily for one month in DashboardController class");
        return dashboardService.getByDaily(startDate, endDate, dealerSiteId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
