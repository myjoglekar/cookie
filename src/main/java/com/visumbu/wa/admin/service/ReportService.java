/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.service;

import com.visumbu.wa.admin.dao.ReportDao;
import com.visumbu.wa.bean.ReportPage;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Service("reportService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    public Map getVisitDetailedList(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        return reportDao.getVisitDetailedList(startDate, endDate, page, dealerSiteId);
    }
    
    public List getTimeOnSiteReport(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId){
        return reportDao.getTimeOnSiteReport(startDate, endDate, page, dealerSiteId);
    }

}
