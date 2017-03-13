/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.service;

import com.l2tmedia.cookie.admin.dao.DealerDao;
import com.l2tmedia.cookie.admin.dao.VisitDao;
import com.l2tmedia.cookie.bean.VisitInputBean;
import com.l2tmedia.cookie.model.ActionLog;
import com.l2tmedia.cookie.model.Conversion;
import com.l2tmedia.cookie.model.VisitLog;
import com.l2tmedia.cookie.model.Dealer;
import com.l2tmedia.cookie.model.DealerSite;
import com.l2tmedia.cookie.model.UniqueVisit;
import com.l2tmedia.cookie.model.UniqueVisitFingerprint;
import com.l2tmedia.cookie.model.UniqueVisitSessionId;
import com.l2tmedia.cookie.model.UniqueVisitVisitId;
import com.l2tmedia.cookie.model.VisitPluginProperties;
import com.l2tmedia.cookie.utils.WaUtils;
import com.l2tmedia.cookie.utils.DateUtils;
import java.io.StringReader;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.json.JsonObject;
import javax.json.JsonValue;
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

    public Conversion saveConversion(VisitInputBean visitBean, Dealer dealer) {
        System.out.println("New Conversion for dealer " + dealer.getDealerName());
        Conversion conversion = new Conversion();
        BeanUtils.copyProperties(visitBean, conversion);
        Boolean isValid = isValidConversion(conversion);
        System.out.println("Is it a valid conversion -> " + isValid);
        if (isValid) {
            conversion.setDealerId(dealer);
            Date sessionVisitTime = getSessionVisitTime(visitBean);
            if (sessionVisitTime == null) {
                sessionVisitTime = new Date();
            }
            System.out.println("Session visit time " + sessionVisitTime);
            Date firstVisitTime = getFirstVisitTime(visitBean);
            if (firstVisitTime == null) {
                firstVisitTime = sessionVisitTime;
            }

            System.out.println("First visit time " + sessionVisitTime);
            Long durationToConvert = DateUtils.timeDiff(new Date(), firstVisitTime);

            System.out.println("Total duration to convert " + durationToConvert);

            Long duration = DateUtils.timeDiff(new Date(), sessionVisitTime);
            System.out.println("Current Session Duration " + duration);
            conversion.setDuration(duration);
            conversion.setDurationToConvert(durationToConvert);
            conversion.setFirstVisitTime(getFirstVisitTime(visitBean));
            conversion.setSessionVisitTime(sessionVisitTime);
            System.out.println("Saving new conversion " + conversion);

            visitDao.create(conversion);
            return conversion;
        }
        return null;
    }

    public ActionLog saveAction(VisitInputBean visitBean, Dealer dealer) {
        ActionLog actionLog = new ActionLog();
        BeanUtils.copyProperties(visitBean, actionLog);
        // Dealer dealer = updateDealerDetails(visitBean);
        actionLog.setDealerId(dealer);
        visitDao.create(actionLog);
        return actionLog;
    }

    public VisitLog saveLog(VisitInputBean visitBean, Dealer dealer) {
        VisitLog visitLog = new VisitLog();
        BeanUtils.copyProperties(visitBean, visitLog);
        //Dealer dealer = updateDealerDetails(visitBean);
        visitLog.setDealerId(dealer);
        visitDao.create(visitLog);
        //UniqueVisit uniqueVisit = updateUniqueVisitDetails(visitLog);
        return visitLog;
    }

    public Dealer updateDealerDetails(VisitInputBean visitBean) {
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
        return dealer;
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

    public String getReferrerUrl(String visitId, Integer visitCount) {
        return visitDao.getReferrerUrl(visitId, visitCount);
    }

    private Boolean isValidConversion(Conversion conversion) {
        String formData = conversion.getFormData();
        javax.json.JsonReader jr
                = javax.json.Json.createReader(new StringReader(formData));
        javax.json.JsonObject formObject = jr.readObject();
        for (Map.Entry<String, JsonValue> entrySet : formObject.entrySet()) {
            if(entrySet.getKey().toLowerCase().contains("price")) {
                continue;
            }
            JsonValue value = entrySet.getValue();
            String dataValue = value.toString().replaceAll("\"", "");
            System.out.println("Checking valid email or phone for " + dataValue);
            if (WaUtils.isEmailValid(dataValue) || WaUtils.validatePhoneNumber(dataValue)) {
                System.out.println("Validation Successful -> " + dataValue);
                return true;
            }
        }
        System.out.println("Not a valid converstion " + conversion);
        return false;
    }

    private Date getFirstVisitTime(VisitInputBean visitBean) {
        return visitDao.getFirstVisitTime(visitBean.getVisitId());
    }

    private Date getSessionVisitTime(VisitInputBean visitBean) {
        return visitDao.getSessionVisitTime(visitBean.getVisitId(), visitBean.getVisitCount());
    }
}
