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
        logger.debug("Calling function of Set Max Count in DashboardService class");
        dashboardDao.setMaxCount(maxCount);
        this.maxCount = maxCount;
      
    }

    public List<DealerVisitBean> getTopDealersByVisit(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getTopDealersByVisit for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        return dashboardDao.getTopDealersByVisit(startDate, endDate, dealerSiteId);
    }

    public List hourlyVisitChart(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function hourlyVisitChart for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        return dashboardDao.hourlyVisitChart(startDate, endDate, dealerSiteId);
    }

    public List getDashboardTickers(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getDashboardTickers for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        
        return dashboardDao.getDashboardTickers(startDate, endDate, dealerSiteId);
    }

    public List<DeviceTypeBean> getByDeviceType(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getByDeviceType for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        return dashboardDao.getByDeviceType(startDate, endDate, dealerSiteId);
    }

    public List<VisitGeoReportBean> getByGeoReport(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getByGeoReport for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        return dashboardDao.getByGeoReport(startDate, endDate, dealerSiteId);
    }

    public List getByBrowser(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getByBrowser for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        return dashboardDao.getByBrowser(startDate, endDate, dealerSiteId);
    }

    public List getByOs(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getByOs for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        return dashboardDao.getByOs(startDate, endDate, dealerSiteId);
    }

    public List<ReferrerBean> getByReferrer(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getByReferrer for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        return dashboardDao.getByReferrer(startDate, endDate, dealerSiteId);
    }

    public List<ReferrerPageBean> getByReferrerPage(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getByReferrerPage for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        return dashboardDao.getByReferrerPage(startDate, endDate, dealerSiteId);
    }

    public List getByMonthlyForSixMonths(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getByMonthlyForSixMonths for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);        
        return dashboardDao.getByMonthly(startDate, endDate, dealerSiteId);
    }

    public List getByDaily(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function getByDaily for a specific dealerSiteId in DashboardService class where date range between startDate="+startDate+"and endDate="+endDate);
        return dashboardDao.getByDaily(startDate, endDate, dealerSiteId);
    }
}
