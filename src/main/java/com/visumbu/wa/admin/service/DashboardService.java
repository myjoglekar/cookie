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

    public List getTopDealersByVisit(Date startDate, Date endDate) {
        return dashboardDao.getTopDealersByVisit(startDate, endDate);
    }
    public List hourlyVisitChart(Date startDate, Date endDate) {
        return dashboardDao.hourlyVisitChart(startDate, endDate);
    }

    public List getDashboardTickers(Date startDate, Date endDate) {
        return dashboardDao.getDashboardTickers(startDate, endDate);
    }

    public List getByDeviceType(Date startDate, Date endDate) {
        return dashboardDao.getByDeviceType(startDate, endDate);
    }

    public List getByLocation(Date startDate, Date endDate) {
        return dashboardDao.getByLocation(startDate, endDate);
    }

    public List getByBrowser(Date startDate, Date endDate) {
        return dashboardDao.getByBrowser(startDate, endDate);
    }
}
