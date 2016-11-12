/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.service;

import com.visumbu.wa.admin.dao.DashboardDao;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Service("dashboardService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DashboardService {

    @Autowired
    private DashboardDao dashboardDao;

    public List getTopDealersByVisit(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.getTopDealersByVisit(startDate, endDate, dealerSiteId);
    }
    public List hourlyVisitChart(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.hourlyVisitChart(startDate, endDate, dealerSiteId);
    }

    public List getDashboardTickers(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.getDashboardTickers(startDate, endDate, dealerSiteId);
    }

    public List getByDeviceType(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.getByDeviceType(startDate, endDate, dealerSiteId);
    }

    public List getByGeoReport(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.getByGeoReport(startDate, endDate, dealerSiteId);
    }

    public List getByBrowser(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.getByBrowser(startDate, endDate, dealerSiteId);
    }
    
    public List getByOs(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.getByOs(startDate, endDate, dealerSiteId);
    }
    
    public List getByReferrer(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.getByReferrer(startDate, endDate, dealerSiteId);
    }

    public List getByMonthlyForSixMonths(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.getByMonthly(startDate, endDate, dealerSiteId);
    }

    public List getByDaily(Date startDate, Date endDate, Integer dealerSiteId) {
        return dashboardDao.getByDaily(startDate, endDate, dealerSiteId);
    }
}
