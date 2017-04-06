/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.controller;

import com.l2tmedia.cookie.Constants;
import com.l2tmedia.cookie.admin.service.DashboardService;
import com.l2tmedia.cookie.dashboard.bean.DealerVisitBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerPageBean;
import com.l2tmedia.cookie.utils.DateUtils;
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
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("Requesting top Dealers by Visit: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getTopDealersByVisit(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "hourlyVisitChart/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List hourlyVisitChart(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("Requesting hourly visit chart: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.hourlyVisitChart(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "dashboardTickers/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getDashboardTickers(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("Requesting dashboard tickers: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getDashboardTickers(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "dashboardTickersYesterday/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getDashboardTickersYesterday(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getYesterday(request.getParameter("startDate"));
        Date endDate = DateUtils.getStartTodayDate(request.getParameter("endDate"));
        logger.debug("Requesting dashboard tickers yesterday: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getDashboardTickers(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byDeviceType/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByDeviceType(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("Requesting device type: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getByDeviceType(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byReferrerPage/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ReferrerPageBean> getByReferrerPage(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("Requesting actions by referrer: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getByReferrerPage(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byGeoReport/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByGeoReport(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("Requesting geo report: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getByGeoReport(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byBrowser/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByBrowser(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("Requesting by browser: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getByBrowser(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byOs/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByOs(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("Requesting actions by OS: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getByOs(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byReferrer/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<ReferrerBean> getByReferrer(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        logger.debug("Requesting actions by referrer: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getByReferrer(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byMonthlyForSixMonths/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByMonthlyForSixMonths(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        if (DateUtils.getDifferenceInMonths(startDate, endDate) < 6) {
            startDate = DateUtils.getSixMonthsBack(endDate);
        }
        logger.debug("Requesting six month breakdown: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getByMonthlyForSixMonths(startDate, endDate, dealerSiteId);
    }

    @RequestMapping(value = "byDailyForOneMonths/{dealerSiteId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List getByDailyForOneMonths(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerSiteId) {
        Date startDate = DateUtils.getStartDate(request.getParameter("startDate"));
        Date endDate = DateUtils.getEndDate(request.getParameter("endDate"));
        if (DateUtils.getDifferenceInMonths(startDate, endDate) < 1) {
            startDate = DateUtils.getOneMonthsBack(endDate);
        }
        logger.debug("Requesting one month breakdown by day: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);
        return dashboardService.getByDaily(startDate, endDate, dealerSiteId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.error(Constants.HTTP_ERROR, e);
    }
}
