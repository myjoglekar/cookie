/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.service;

import com.l2tmedia.cookie.admin.dao.ReportDao;
import com.l2tmedia.cookie.bean.ReportPage;
import com.l2tmedia.cookie.model.ActionLog;
import com.l2tmedia.cookie.model.VisitLogReport;
import com.l2tmedia.cookie.report.bean.SubmitReferrerBean;
import com.l2tmedia.cookie.report.bean.groups.DealerReferrerDomainGroup;
import com.l2tmedia.cookie.report.bean.groups.DealerReferrerTypeGroup;
import com.l2tmedia.cookie.report.bean.FrequencyReportBean;
import com.l2tmedia.cookie.report.bean.SubmitReferrerAssistBean;
import com.l2tmedia.cookie.utils.WaUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author netphenix
 */
@Service("reportService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ReportService {

    @Autowired
    private ReportDao reportDao;

    final static Logger logger = Logger.getLogger(ReportService.class);

    public Map getVisitDetailedList(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Calling a function getVisitDetailedlist for a particular dealerSite in ReportService class where dealerSiteId="+dealerSiteId+"and startDate="+startDate+"and endDate="+endDate);
        return reportDao.getVisitDetailedList(startDate, endDate, page, dealerSiteId);
    }

    public List getTimeOnSiteReport(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Calling a function getTimeOnSiteReport for a particular dealerSite in ReportService class where dealerSiteId="+dealerSiteId+"and startDate="+startDate+"and endDate="+endDate);
        return reportDao.getTimeOnSiteReport(startDate, endDate, page, dealerSiteId);
    }

    public List<FrequencyReportBean> getByFrequency(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Calling a function getByFrequency for a particular dealerSite in ReportService class where dealerSiteId="+dealerSiteId+"and startDate="+startDate+"and endDate="+endDate);
        return reportDao.getByFrequency(startDate, endDate, page, dealerSiteId);
    }

    public List getByConversionFrequency(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Calling a function getByConversionFrequency for a particular dealerSite in ReportService class where dealerSiteId="+dealerSiteId+"and startDate="+startDate+"and endDate="+endDate);
        return reportDao.getByConversionFrequency(startDate, endDate, page, dealerSiteId);
    }

    public Map getFormDataList(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Calling a function getFormDataList for a particular dealerSite in ReportService class where dealerSiteId="+dealerSiteId+"and startDate="+startDate+"and endDate="+endDate);
        return reportDao.getFormDataList(startDate, endDate, page, dealerSiteId);
    }

    public Map getVisitLog(Date startDate, Date endDate, ReportPage page) {
        logger.debug("Calling a function getVisitLog for a particular in ReportService class where startDate="+startDate+"and endDate="+endDate);
        return reportDao.getVisitLog(startDate, endDate, page);
    }

    public Map getVisitDetailsList(Date startDate, Date endDate, ReportPage page,
            Integer dealerSiteId, String fingerprint, String sessionId, String visitId) {
        logger.debug("Calling function to get visit log detail list using getVisitDetailsList in ReportService class for the dealerSiteId "+dealerSiteId+" and visitId "+visitId+" from date range startDate="+startDate+"and endDate="+endDate);
        return reportDao.getVisitDetailsList(startDate, endDate, page, dealerSiteId, fingerprint, sessionId, visitId);
    }

    public List getActionDetailsList(Date startDate, Date endDate, ReportPage page,
            Integer dealerSiteId, String fingerprint, String sessionId, String visitId) {
        logger.debug("Calling function to get action log detail list using getActionDetailsList in ReportService class for the dealerSiteId "+dealerSiteId+" and visitId "+visitId+" from date range startDate="+startDate+"and endDate="+endDate);
        return reportDao.getActionDetailsList(startDate, endDate, page, dealerSiteId, fingerprint, sessionId, visitId);
    }

    public List<SubmitReferrerAssistBean> getAssistsSubmitReferrers(Date startDate, Date endDate, Integer dealerSiteId) {
        List<ActionLog> submitData = reportDao.getSubmitData(startDate, endDate, dealerSiteId);
        
        List<SubmitReferrerAssistBean> referrerBeans = new ArrayList<>();
        for (Iterator<ActionLog> iterator = submitData.iterator(); iterator.hasNext();) {
            ActionLog submitClick = iterator.next();
            String fingerprint = submitClick.getFingerprint();
            String visitId = submitClick.getVisitId();
            String sessionId = submitClick.getSessionId();
            String domainName = WaUtils.getDomainName(submitClick.getUrl());
            Date conversionTime = submitClick.getActionTime();
            List<VisitLogReport> visitLogList = reportDao.getVisitLogReferrer(visitId);
            if (visitLogList.size() > 0) {
                int count = 1;
                for (Iterator<VisitLogReport> iterator1 = visitLogList.iterator(); iterator1.hasNext();) {
                    VisitLogReport currentVisitLog = iterator1.next();
                    if (count == visitLogList.size()) {
                        continue;
                    }
                    SubmitReferrerAssistBean referrerBean = new SubmitReferrerAssistBean();
                    referrerBean.setAssistReferrerDomain(currentVisitLog.getReferrerDomain());
                    referrerBean.setAssistReferrerType(currentVisitLog.getReferrerType() == null ? WaUtils.getReferrerType(currentVisitLog.getReferrerUrl(), currentVisitLog.getDomainName()) : currentVisitLog.getReferrerType());
                    referrerBean.setAssistReferrerUrl(currentVisitLog.getReferrerUrl());
                    referrerBean.setDealerReferrerAssist(new DealerReferrerDomainGroup(dealerSiteId == 0 ? "-" : currentVisitLog.getDomainName(), currentVisitLog.getReferrerDomain()));
                    referrerBean.setDealerReferrerTypeAssist(new DealerReferrerTypeGroup(dealerSiteId == 0 ? "-" : currentVisitLog.getDomainName(), referrerBean.getAssistReferrerType()));
                    referrerBeans.add(referrerBean);
                }
            }
        }
        logger.debug("Calling function to get Submit Referrals using getAssistsSubmitReferrers in ReportService class for the dealerSiteId "+dealerSiteId+"  from date range startDate="+startDate+"and endDate="+endDate);
        return referrerBeans;
    }

    public Map getReferrerDomainAssistSummary(Date startDate, Date endDate, Integer dealerSiteId) {
        List<SubmitReferrerAssistBean> submitReferrers = getAssistsSubmitReferrers(startDate, endDate, dealerSiteId);

        Map<DealerReferrerDomainGroup, Long> assistReferrerSummary = submitReferrers.stream().collect(
                Collectors.groupingBy(SubmitReferrerAssistBean::getDealerReferrerAssist, Collectors.counting()));

        
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
        logger.debug("End  function of get referrer domain assist summary in ReportService class");
        logger.debug("Calling function to get referrer domain assist summary using getReferrerDomainAssistSummary in ReportService class for the dealerSiteId "+dealerSiteId+"  from date range startDate="+startDate+"and endDate="+endDate);
        return returnMap;
    }

    public Map getReferrerTypeAssistSummary(Date startDate, Date endDate, Integer dealerSiteId) {
        List<SubmitReferrerAssistBean> submitReferrers = getAssistsSubmitReferrers(startDate, endDate, dealerSiteId);
        Map<DealerReferrerTypeGroup, Long> assistReferrerSummary = submitReferrers.stream().collect(
                Collectors.groupingBy(SubmitReferrerAssistBean::getDealerReferrerTypeAssist, Collectors.counting()));

        
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
        logger.debug("Calling function to get referrer type assist summary assist summary using getReferrerTypeAssistSummary in ReportService class for the dealerSiteId "+dealerSiteId+"  from date range startDate="+startDate+"and endDate="+endDate);
        return returnMap;
    }

    public List<SubmitReferrerBean> getExtremeSubmitReferrers(Date startDate, Date endDate, Integer dealerSiteId) {
        List<ActionLog> submitData = reportDao.getSubmitData(startDate, endDate, dealerSiteId);
        List<SubmitReferrerBean> referrerBeans = new ArrayList<>();
        for (Iterator<ActionLog> iterator = submitData.iterator(); iterator.hasNext();) {
            SubmitReferrerBean referrerBean = new SubmitReferrerBean();
            ActionLog submitClick = iterator.next();
            String visitId = submitClick.getVisitId();
            List<VisitLogReport> visitLogList = reportDao.getVisitLogReferrer(visitId);
            if (visitLogList.size() > 0) {
                /* First Visit Referrer */
                VisitLogReport firstVisitLog = visitLogList.get(0);
                referrerBean.setActionLog(submitClick);
                referrerBean.setFirstRefferTime(firstVisitLog.getVisitTime());
                referrerBean.setFirstReferrerDomain(firstVisitLog.getReferrerDomain());
                referrerBean.setFirstReferrerType(firstVisitLog.getReferrerType());
                referrerBean.setFirstReferrerUrl(firstVisitLog.getReferrerUrl());
                referrerBean.setFirstDealerReferrer(new DealerReferrerDomainGroup((dealerSiteId == 0 || firstVisitLog.getDomainName() == null) ? "-" : firstVisitLog.getDomainName(),
                        firstVisitLog.getReferrerDomain() == null ? "-" : firstVisitLog.getReferrerDomain()));
                referrerBean.setFirstDealerReferrerType(new DealerReferrerTypeGroup((dealerSiteId == 0 || firstVisitLog.getDomainName() == null) ? "-" : firstVisitLog.getDomainName(),
                        firstVisitLog.getReferrerType() == null ? "-" : firstVisitLog.getReferrerType()));
                /* Last Visit Referrer */
                VisitLogReport lastVisitLog = visitLogList.get(visitLogList.size() - 1);
                referrerBean.setLastRefferTime(lastVisitLog.getVisitTime());
                referrerBean.setLastReferrerDomain(lastVisitLog.getReferrerDomain());
                referrerBean.setLastReferrerType(lastVisitLog.getReferrerType());
                referrerBean.setLastReferrerUrl(lastVisitLog.getReferrerUrl());
                referrerBean.setLastDealerReferrer(new DealerReferrerDomainGroup((dealerSiteId == 0 || lastVisitLog.getDomainName() == null) ? "-" : lastVisitLog.getDomainName(),
                        lastVisitLog.getReferrerDomain() == null ? "-" : lastVisitLog.getReferrerDomain()));
                referrerBean.setLastDealerReferrerType(new DealerReferrerTypeGroup((dealerSiteId == 0 || lastVisitLog.getDomainName() == null) ? "-" : lastVisitLog.getDomainName(),
                        lastVisitLog.getReferrerType() == null ? "-" : lastVisitLog.getReferrerType()));
                referrerBeans.add(referrerBean);
            } else {
                visitLogList = reportDao.getVisitLog(visitId);
                if (visitLogList == null || visitLogList.isEmpty()) {
                    continue;
                }
                VisitLogReport firstVisitLog = visitLogList.get(0);

                referrerBean.setActionLog(submitClick);
                referrerBean.setFirstRefferTime(firstVisitLog.getVisitTime());
                referrerBean.setFirstReferrerDomain(firstVisitLog.getReferrerDomain());
                referrerBean.setFirstReferrerType(firstVisitLog.getReferrerType());
                referrerBean.setFirstReferrerUrl(firstVisitLog.getReferrerUrl());
                referrerBean.setFirstDealerReferrer(new DealerReferrerDomainGroup((dealerSiteId == 0 || firstVisitLog.getDomainName() == null) ? "-" : firstVisitLog.getDomainName(),
                        firstVisitLog.getReferrerDomain() == null ? "-" : firstVisitLog.getReferrerDomain()));
                referrerBean.setFirstDealerReferrerType(new DealerReferrerTypeGroup((dealerSiteId == 0 || firstVisitLog.getDomainName() == null) ? "-" : firstVisitLog.getDomainName(),
                        firstVisitLog.getReferrerType() == null ? "-" : firstVisitLog.getReferrerType()));
                /* Last Visit Referrer */
                VisitLogReport lastVisitLog = visitLogList.get(visitLogList.size() - 1);
                referrerBean.setLastRefferTime(lastVisitLog.getVisitTime());
                referrerBean.setLastReferrerDomain(lastVisitLog.getReferrerDomain());
                referrerBean.setLastReferrerType(lastVisitLog.getReferrerType());
                referrerBean.setLastReferrerUrl(lastVisitLog.getReferrerUrl());
                referrerBean.setLastDealerReferrer(new DealerReferrerDomainGroup((dealerSiteId == 0 || lastVisitLog.getDomainName() == null) ? "-" : lastVisitLog.getDomainName(),
                        lastVisitLog.getReferrerDomain() == null ? "-" : lastVisitLog.getReferrerDomain()));
                referrerBean.setLastDealerReferrerType(new DealerReferrerTypeGroup((dealerSiteId == 0 || lastVisitLog.getDomainName() == null) ? "-" : lastVisitLog.getDomainName(),
                        lastVisitLog.getReferrerType() == null ? "-" : lastVisitLog.getReferrerType()));
                referrerBeans.add(referrerBean);
            }
        }
        logger.debug("Calling function to get extreme submit referrers using getExtremeSubmitReferrers in ReportService class for the dealerSiteId "+dealerSiteId+"  from date range startDate="+startDate+"and endDate="+endDate);
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

        Map returnMap = new HashMap();
        returnMap.put("firstReferrer", firstReferrerList);
        returnMap.put("lastReferrer", lastReferrerList);
        logger.debug("Calling function to get extreme referrer summary using getExtremeReferrerDomainSummary in ReportService class for the dealerSiteId "+dealerSiteId+"  from date range startDate="+startDate+"and endDate="+endDate);
        return returnMap;
    }

    public Map getExtremeReferrerTypeSummary(Date startDate, Date endDate, Integer dealerSiteId) {
        List<SubmitReferrerBean> submitReferrers = getExtremeSubmitReferrers(startDate, endDate, dealerSiteId);
        logger.debug("Start function of get extreme referrer type summary in ReportService class");
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

        Map returnMap = new HashMap();
        returnMap.put("firstReferrer", firstReferrerList);
        returnMap.put("lastReferrer", lastReferrerList);
        logger.debug("Calling function to get extreme referer type summary using getExtremeReferrerTypeSummary in ReportService class for the dealerSiteId "+dealerSiteId+"  from date range startDate="+startDate+"and endDate="+endDate);
        return returnMap;
    }
}
