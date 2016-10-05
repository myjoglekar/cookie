/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.service;

import com.visumbu.wa.admin.dao.DealerDao;
import com.visumbu.wa.admin.dao.VisitDao;
import com.visumbu.wa.bean.VisitInputBean;
import com.visumbu.wa.model.ActionLog;
import com.visumbu.wa.model.VisitLog;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.DealerSite;
import com.visumbu.wa.model.UniqueVisit;
import com.visumbu.wa.model.UniqueVisitFingerprint;
import com.visumbu.wa.model.UniqueVisitSessionId;
import com.visumbu.wa.model.UniqueVisitVisitId;
import com.visumbu.wa.model.VisitPluginProperties;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Service("visitService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VisitService {

    @Autowired
    private VisitDao visitDao;
    @Autowired
    private DealerDao dealerDao;

    public Dealer read(Integer id) {
        return (Dealer) visitDao.read(Dealer.class, id);
    }

    public List<Dealer> read() {
        List<Dealer> dealer = visitDao.read(Dealer.class);
        return dealer;
    }

    public void newVisit() {

    }

    public ActionLog saveAction(VisitInputBean visitBean) {
        ActionLog actionLog = new ActionLog();
        BeanUtils.copyProperties(visitBean, actionLog);
        visitDao.create(actionLog);
        return actionLog;
    }

    public VisitLog saveLog(VisitInputBean visitBean) {
        VisitLog visitLog = new VisitLog();
        BeanUtils.copyProperties(visitBean, visitLog);
        visitDao.create(visitLog);
        updateDealerDetails(visitBean);
        UniqueVisit uniqueVisit = updateUniqueVisitDetails(visitLog);
        return visitLog;
    }

    private void updateDealerDetails(VisitInputBean visitBean) {
        Dealer dealer = dealerDao.findBySiteId(visitBean.getSiteId());
        DealerSite dealerSite = dealerDao.findDealerSite(dealer.getId(), visitBean.getDomainName());
        if (dealerSite == null) {
            dealerSite = new DealerSite();
            dealerSite.setDealerId(dealer);
            dealerSite.setSiteName(visitBean.getDomainName());
            dealerDao.create(dealerSite);
        }
        dealer.setLastSiteVisit(new Date());
        dealerDao.update(dealer);
    }

    public void saveVisitProperties(Properties supportedPlugins, VisitLog visitLog) {
        Enumeration e = supportedPlugins.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = supportedPlugins.getProperty(key);
            VisitPluginProperties properties = new VisitPluginProperties();
            properties.setVisitLogId(visitLog);
            properties.setPropertyName(key);
            properties.setPropertyValue(value);
            dealerDao.create(properties);
        }
    }

    public UniqueVisit updateUniqueVisitDetails(VisitLog visitLog) {
        String fingerPrint = visitLog.getFingerprint();
        String visitId = visitLog.getVisitId();
        String sessionId = visitLog.getSessionId();
        UniqueVisit uniqueVisit = null;
        UniqueVisit uniqueVisitFp = visitDao.getUniqueIdByFingerPrint(fingerPrint);
        UniqueVisit uniqueVisitVi = visitDao.getUniqueIdByVisitId(visitId);
        UniqueVisit uniqueVisitSi = visitDao.getUniqueIdBySessionId(sessionId);
        uniqueVisit = uniqueVisitFp != null ? uniqueVisitFp : (uniqueVisitVi != null ? uniqueVisitVi : (uniqueVisitSi != null ? uniqueVisitSi : null));
        if (uniqueVisit == null) {
            uniqueVisit = new UniqueVisit();
            uniqueVisit.setTotalVisits(1);
            uniqueVisit = (UniqueVisit) visitDao.create(uniqueVisit);
        } else {
            uniqueVisit.setTotalVisits(uniqueVisit.getTotalVisits() + 1);
            uniqueVisit = (UniqueVisit) visitDao.update(uniqueVisit);
        }
        if (uniqueVisitFp == null) {
            UniqueVisitFingerprint uniqueVisitFingerprint = new UniqueVisitFingerprint();
            uniqueVisitFingerprint.setFingerprint(fingerPrint);
            uniqueVisitFingerprint.setUniqueVisitId(uniqueVisit);
            visitDao.create(uniqueVisitFingerprint);
        }
        if (uniqueVisitSi == null) {
            UniqueVisitSessionId uniqueVisitSessionId = new UniqueVisitSessionId();
            uniqueVisitSessionId.setSessionId(sessionId);
            uniqueVisitSessionId.setUniqueVisitId(uniqueVisit);
            visitDao.create(uniqueVisitSessionId);
        }
        if (uniqueVisitVi == null) {
            UniqueVisitVisitId uniqueVisitVisitId = new UniqueVisitVisitId();
            uniqueVisitVisitId.setVisitId(visitId);
            uniqueVisitVisitId.setUniqueVisitId(uniqueVisit);
            visitDao.create(uniqueVisitVisitId);
        }
        return uniqueVisit;
    }

}
