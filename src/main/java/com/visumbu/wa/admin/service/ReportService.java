/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.service;

import com.visumbu.wa.admin.dao.ReportDao;
import com.visumbu.wa.bean.ReportPage;
import com.visumbu.wa.model.ActionLog;
import com.visumbu.wa.model.VisitLog;
import com.visumbu.wa.report1.bean.SubmitReferrerBean;
import com.visumbu.wa.report.bean.groups.DealerReferrerDomainGroup;
import com.visumbu.wa.report.bean.groups.DealerReferrerTypeGroup;
import com.visumbu.wa.report1.bean.FrequencyReportBean;
import com.visumbu.wa.report1.bean.SubmitReferrerAssistBean;
import com.visumbu.wa.utils.WaUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    public List getTimeOnSiteReport(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        return reportDao.getTimeOnSiteReport(startDate, endDate, page, dealerSiteId);
    }

    public List<FrequencyReportBean> getByFrequency(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        return reportDao.getByFrequency(startDate, endDate, page, dealerSiteId);
    }

    public List getByConversionFrequency(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        return reportDao.getByConversionFrequency(startDate, endDate, page, dealerSiteId);
    }

    public Map getFormDataList(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        return reportDao.getFormDataList(startDate, endDate, page, dealerSiteId);
    }

    public Map getVisitDetailsList(Date startDate, Date endDate, ReportPage page,
            Integer dealerSiteId, String fingerprint, String sessionId, String visitId) {
        return reportDao.getVisitDetailsList(startDate, endDate, page, dealerSiteId, fingerprint, sessionId, visitId);
    }

    public List getActionDetailsList(Date startDate, Date endDate, ReportPage page,
            Integer dealerSiteId, String fingerprint, String sessionId, String visitId) {
        return reportDao.getActionDetailsList(startDate, endDate, page, dealerSiteId, fingerprint, sessionId, visitId);
    }

    public List<SubmitReferrerAssistBean> getAssistsSubmitReferrers(Date startDate, Date endDate, Integer dealerSiteId) {
        List<ActionLog> submitData = reportDao.getSubmitData(startDate, endDate, dealerSiteId);
        System.out.println("Referrer Assist Count -> " + submitData.size());
        List<SubmitReferrerAssistBean> referrerBeans = new ArrayList<>();
        for (Iterator<ActionLog> iterator = submitData.iterator(); iterator.hasNext();) {
            ActionLog submitClick = iterator.next();
            String fingerprint = submitClick.getFingerprint();
            String visitId = submitClick.getVisitId();
            String sessionId = submitClick.getSessionId();
            String domainName = WaUtils.getDomainName(submitClick.getUrl());
            Date conversionTime = submitClick.getActionTime();
            List<VisitLog> visitLogList = reportDao.getVisitLogReferrer(fingerprint, sessionId, visitId, domainName, startDate, conversionTime);
            if (visitLogList.size() > 0) {
                int count = 1;
                for (Iterator<VisitLog> iterator1 = visitLogList.iterator(); iterator1.hasNext();) {
                    VisitLog currentVisitLog = iterator1.next();
                    if (count == visitLogList.size()) {
                        continue;
                    }
                    SubmitReferrerAssistBean referrerBean = new SubmitReferrerAssistBean();
                    referrerBean.setAssistReferrerDomain(currentVisitLog.getReferrerDomain());
                    referrerBean.setAssistReferrerType(currentVisitLog.getReferrerType() == null ? WaUtils.getReferrerType(currentVisitLog.getReferrerUrl(), currentVisitLog.getDomainName()) : currentVisitLog.getReferrerType());
                    referrerBean.setAssistReferrerUrl(currentVisitLog.getReferrerUrl());
                    referrerBean.setDealerReferrerAssist(new DealerReferrerDomainGroup(currentVisitLog.getDomainName(), currentVisitLog.getReferrerDomain()));
                    referrerBean.setDealerReferrerTypeAssist(new DealerReferrerTypeGroup(currentVisitLog.getDomainName(), referrerBean.getAssistReferrerType()));
                    referrerBeans.add(referrerBean);
                }
            }
        }
        return referrerBeans;
    }

    public Map getReferrerDomainAssistSummary(Date startDate, Date endDate, Integer dealerSiteId) {
        List<SubmitReferrerAssistBean> submitReferrers = getAssistsSubmitReferrers(startDate, endDate, dealerSiteId);

        Map<DealerReferrerDomainGroup, Long> assistReferrerSummary = submitReferrers.stream().collect(
                Collectors.groupingBy(SubmitReferrerAssistBean::getDealerReferrerAssist, Collectors.counting()));

        System.out.println(assistReferrerSummary);
        List assistReferrerList = new ArrayList();
        for (Map.Entry<DealerReferrerDomainGroup, Long> entry : assistReferrerSummary.entrySet()) {
            Map assistReferrerMap = new HashMap();
            DealerReferrerDomainGroup key = entry.getKey();
            Long value = entry.getValue();
            assistReferrerMap.put("referrer", key);
            assistReferrerMap.put("count", value);
            assistReferrerList.add(assistReferrerMap);
        }

        Map returnMap = new HashMap();
        returnMap.put("assistReferrer", assistReferrerList);
        return returnMap;
    }

    public Map getReferrerTypeAssistSummary(Date startDate, Date endDate, Integer dealerSiteId) {
        List<SubmitReferrerAssistBean> submitReferrers = getAssistsSubmitReferrers(startDate, endDate, dealerSiteId);
        System.out.println(submitReferrers);
        Map<DealerReferrerTypeGroup, Long> assistReferrerSummary = submitReferrers.stream().collect(
                Collectors.groupingBy(SubmitReferrerAssistBean::getDealerReferrerTypeAssist, Collectors.counting()));

        System.out.println(assistReferrerSummary);
        List assistReferrerList = new ArrayList();
        for (Map.Entry<DealerReferrerTypeGroup, Long> entry : assistReferrerSummary.entrySet()) {
            Map assistReferrerMap = new HashMap();
            DealerReferrerTypeGroup key = entry.getKey();
            Long value = entry.getValue();
            assistReferrerMap.put("referrer", key);
            assistReferrerMap.put("count", value);
            assistReferrerList.add(assistReferrerMap);
        }

        Map returnMap = new HashMap();
        returnMap.put("assistReferrer", assistReferrerList);
        return returnMap;
    }

    public List<SubmitReferrerBean> getExtremeSubmitReferrers(Date startDate, Date endDate, Integer dealerSiteId) {
        List<ActionLog> submitData = reportDao.getSubmitData(startDate, endDate, dealerSiteId);
        System.out.println("Extreme Referrer Count -> " + submitData.size());
        List<SubmitReferrerBean> referrerBeans = new ArrayList<>();
        for (Iterator<ActionLog> iterator = submitData.iterator(); iterator.hasNext();) {
            SubmitReferrerBean referrerBean = new SubmitReferrerBean();
            ActionLog submitClick = iterator.next();
            String fingerprint = submitClick.getFingerprint();
            String visitId = submitClick.getVisitId();
            String sessionId = submitClick.getSessionId();
            String domainName = WaUtils.getDomainName(submitClick.getUrl());
            Date conversionTime = submitClick.getActionTime();
            List<VisitLog> visitLogList = reportDao.getVisitLogReferrer(fingerprint, sessionId, visitId, domainName, startDate, conversionTime);
            if (visitLogList.size() > 0) {
                /* First Visit Referrer */
                VisitLog firstVisitLog = visitLogList.get(0);
                referrerBean.setActionLog(submitClick);
                referrerBean.setFirstRefferTime(firstVisitLog.getVisitTime());
                referrerBean.setFirstReferrerDomain(firstVisitLog.getReferrerDomain());
                referrerBean.setFirstReferrerType(firstVisitLog.getReferrerType());
                referrerBean.setFirstReferrerUrl(firstVisitLog.getReferrerUrl());
                referrerBean.setFirstDealerReferrer(new DealerReferrerDomainGroup(firstVisitLog.getDomainName() == null ? "-" : firstVisitLog.getDomainName(),
                        firstVisitLog.getReferrerDomain() == null ? "-" : firstVisitLog.getReferrerDomain()));
                referrerBean.setFirstDealerReferrerType(new DealerReferrerTypeGroup(firstVisitLog.getDomainName() == null ? "-" : firstVisitLog.getDomainName(),
                        firstVisitLog.getReferrerType() == null ? "-" : firstVisitLog.getReferrerType()));
                /* Last Visit Referrer */
                VisitLog lastVisitLog = visitLogList.get(visitLogList.size() - 1);
                referrerBean.setLastRefferTime(lastVisitLog.getVisitTime());
                referrerBean.setLastReferrerDomain(lastVisitLog.getReferrerDomain());
                referrerBean.setLastReferrerType(lastVisitLog.getReferrerType());
                referrerBean.setLastReferrerUrl(lastVisitLog.getReferrerUrl());
                referrerBean.setLastDealerReferrer(new DealerReferrerDomainGroup(lastVisitLog.getDomainName() == null ? "-" : lastVisitLog.getDomainName(),
                        lastVisitLog.getReferrerDomain() == null ? "-" : lastVisitLog.getReferrerDomain()));
                referrerBean.setLastDealerReferrerType(new DealerReferrerTypeGroup(lastVisitLog.getDomainName() == null ? "-" : lastVisitLog.getDomainName(),
                        lastVisitLog.getReferrerType() == null ? "-" : lastVisitLog.getReferrerType()));
                referrerBeans.add(referrerBean);
            } else {
                visitLogList = reportDao.getVisitLog(fingerprint, sessionId, visitId, domainName, startDate, conversionTime);
                if(visitLogList == null || visitLogList.isEmpty()) {
                    continue;
                }
                VisitLog firstVisitLog = visitLogList.get(0);

                referrerBean.setActionLog(submitClick);
                referrerBean.setFirstRefferTime(firstVisitLog.getVisitTime());
                referrerBean.setFirstReferrerDomain(firstVisitLog.getReferrerDomain());
                referrerBean.setFirstReferrerType(firstVisitLog.getReferrerType());
                referrerBean.setFirstReferrerUrl(firstVisitLog.getReferrerUrl());
                referrerBean.setFirstDealerReferrer(new DealerReferrerDomainGroup(firstVisitLog.getDomainName() == null ? "-" : firstVisitLog.getDomainName(),
                        firstVisitLog.getReferrerDomain() == null ? "-" : firstVisitLog.getReferrerDomain()));
                referrerBean.setFirstDealerReferrerType(new DealerReferrerTypeGroup(firstVisitLog.getDomainName() == null ? "-" : firstVisitLog.getDomainName(),
                        firstVisitLog.getReferrerType() == null ? "-" : firstVisitLog.getReferrerType()));
                /* Last Visit Referrer */
                VisitLog lastVisitLog = visitLogList.get(visitLogList.size() - 1);
                referrerBean.setLastRefferTime(lastVisitLog.getVisitTime());
                referrerBean.setLastReferrerDomain(lastVisitLog.getReferrerDomain());
                referrerBean.setLastReferrerType(lastVisitLog.getReferrerType());
                referrerBean.setLastReferrerUrl(lastVisitLog.getReferrerUrl());
                referrerBean.setLastDealerReferrer(new DealerReferrerDomainGroup(lastVisitLog.getDomainName() == null ? "-" : lastVisitLog.getDomainName(),
                        lastVisitLog.getReferrerDomain() == null ? "-" : lastVisitLog.getReferrerDomain()));
                referrerBean.setLastDealerReferrerType(new DealerReferrerTypeGroup(lastVisitLog.getDomainName() == null ? "-" : lastVisitLog.getDomainName(),
                        lastVisitLog.getReferrerType() == null ? "-" : lastVisitLog.getReferrerType()));
                referrerBeans.add(referrerBean);
            }
        }
        return referrerBeans;
    }

    public Map getExtremeReferrerDomainSummary(Date startDate, Date endDate, Integer dealerSiteId) {
        List<SubmitReferrerBean> submitReferrers = getExtremeSubmitReferrers(startDate, endDate, dealerSiteId);
        Map<DealerReferrerDomainGroup, Long> firstReferrerSummary = submitReferrers.stream().collect(
                Collectors.groupingBy(SubmitReferrerBean::getFirstDealerReferrer, Collectors.counting()));

        List firstReferrerList = new ArrayList();
        for (Map.Entry<DealerReferrerDomainGroup, Long> entry : firstReferrerSummary.entrySet()) {
            Map firstReferrerMap = new HashMap();
            DealerReferrerDomainGroup key = entry.getKey();
            Long value = entry.getValue();
            firstReferrerMap.put("referrer", key);
            firstReferrerMap.put("count", value);
            firstReferrerList.add(firstReferrerMap);
        }

        System.out.println(firstReferrerSummary);

        Map<DealerReferrerDomainGroup, Long> lastReferrerSummary = submitReferrers.stream().collect(
                Collectors.groupingBy(SubmitReferrerBean::getLastDealerReferrer, Collectors.counting()));

        List lastReferrerList = new ArrayList();
        for (Map.Entry<DealerReferrerDomainGroup, Long> entry : lastReferrerSummary.entrySet()) {
            Map lastReferrerMap = new HashMap();
            DealerReferrerDomainGroup key = entry.getKey();
            Long value = entry.getValue();
            lastReferrerMap.put("referrer", key);
            lastReferrerMap.put("count", value);
            lastReferrerList.add(lastReferrerMap);
        }

        System.out.println(lastReferrerSummary);

        Map returnMap = new HashMap();
        returnMap.put("firstReferrer", firstReferrerList);
        returnMap.put("lastReferrer", lastReferrerList);
        return returnMap;
    }

    public Map getExtremeReferrerTypeSummary(Date startDate, Date endDate, Integer dealerSiteId) {
        List<SubmitReferrerBean> submitReferrers = getExtremeSubmitReferrers(startDate, endDate, dealerSiteId);
        Map<DealerReferrerTypeGroup, Long> firstReferrerSummary = submitReferrers.stream().collect(
                Collectors.groupingBy(SubmitReferrerBean::getFirstDealerReferrerType, Collectors.counting()));

        List firstReferrerList = new ArrayList();
        for (Map.Entry<DealerReferrerTypeGroup, Long> entry : firstReferrerSummary.entrySet()) {
            Map firstReferrerMap = new HashMap();
            DealerReferrerTypeGroup key = entry.getKey();
            Long value = entry.getValue();
            firstReferrerMap.put("referrer", key);
            firstReferrerMap.put("count", value);
            firstReferrerList.add(firstReferrerMap);
        }

        System.out.println(firstReferrerSummary);

        Map<DealerReferrerTypeGroup, Long> lastReferrerSummary = submitReferrers.stream().collect(
                Collectors.groupingBy(SubmitReferrerBean::getLastDealerReferrerType, Collectors.counting()));

        List lastReferrerList = new ArrayList();
        for (Map.Entry<DealerReferrerTypeGroup, Long> entry : lastReferrerSummary.entrySet()) {
            Map lastReferrerMap = new HashMap();
            DealerReferrerTypeGroup key = entry.getKey();
            Long value = entry.getValue();
            lastReferrerMap.put("referrer", key);
            lastReferrerMap.put("count", value);
            lastReferrerList.add(lastReferrerMap);
        }

        System.out.println(lastReferrerSummary);

        Map returnMap = new HashMap();
        returnMap.put("firstReferrer", firstReferrerList);
        returnMap.put("lastReferrer", lastReferrerList);
        return returnMap;
    }
}
