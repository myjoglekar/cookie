/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.service;

import com.l2tmedia.cookie.admin.dao.DashboardDao;
import com.l2tmedia.cookie.dashboard.bean.DealerVisitBean;
import com.l2tmedia.cookie.dashboard.bean.DeviceTypeBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerPageBean;
import com.l2tmedia.cookie.dashboard.bean.VisitGeoReportBean;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author netphenix
 */
@Service("dashboardService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DashboardService {

    @Autowired
    private DashboardDao dashboardDao;

    private Integer maxCount = 5;

    final static Logger logger = Logger.getLogger(DashboardService.class);

    public void setMaxCount(Integer maxCount) {
        logger.debug("Start function of Set Max Count in DashboardService class");
        dashboardDao.setMaxCount(maxCount);
        this.maxCount = maxCount;
        logger.debug("End  function of Set Max Count in DashboardService class");
    }

    public List<DealerVisitBean> getTopDealersByVisit(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Top Dealer By Visit in DashboardService class");
        logger.debug("End  function of Top Dealer By visit in DashboardService class");
        return dashboardDao.getTopDealersByVisit(startDate, endDate, dealerSiteId);
    }

    public List hourlyVisitChart(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Hourly visit chart in DashboardService class");
        logger.debug("End  function of Hourly visit chart in DashboardService class");
        return dashboardDao.hourlyVisitChart(startDate, endDate, dealerSiteId);
    }

    public List getDashboardTickers(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Dashboard Tickers in DashboardService class");
        logger.debug("End  function of Top Dashboard Tickers in DashboardService class");
        return dashboardDao.getDashboardTickers(startDate, endDate, dealerSiteId);
    }

    public List<DeviceTypeBean> getByDeviceType(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Get Device Type in DashboardService class");
        logger.debug("End  function of Get Device Type in DashboardService class");
        return dashboardDao.getByDeviceType(startDate, endDate, dealerSiteId);
    }

    public List<VisitGeoReportBean> getByGeoReport(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Get Geo Report in DashboardService class");
        logger.debug("End  function of Get Geo Report in DashboardService class");
        return dashboardDao.getByGeoReport(startDate, endDate, dealerSiteId);
    }

    public List getByBrowser(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Get By Browser in DashboardService class");
        logger.debug("End  function of Get By Browser in DashboardService class");
        return dashboardDao.getByBrowser(startDate, endDate, dealerSiteId);
    }

    public List getByOs(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Get By OS in DashboardService class");
        logger.debug("End  function of Get By OS in DashboardService class");
        return dashboardDao.getByOs(startDate, endDate, dealerSiteId);
    }

    public List<ReferrerBean> getByReferrer(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Get By Referrer in DashboardService class");
        logger.debug("End  function of Get By Referrer in DashboardService class");
        return dashboardDao.getByReferrer(startDate, endDate, dealerSiteId);
    }

    public List<ReferrerPageBean> getByReferrerPage(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Get By Referrer Page in DashboardService class");
        logger.debug("End  function of Get By Referrer Page in DashboardService class");
        return dashboardDao.getByReferrerPage(startDate, endDate, dealerSiteId);
    }

    public List getByMonthlyForSixMonths(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Get Monthly six months in DashboardService class");
        logger.debug("End  function of Get Monthly Six Months in DashboardService class");
        return dashboardDao.getByMonthly(startDate, endDate, dealerSiteId);
    }

    public List getByDaily(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Start function of Get Dily in DashboardService class");
        logger.debug("End  function of Get Daily in DashboardService class");
        return dashboardDao.getByDaily(startDate, endDate, dealerSiteId);
    }
}
