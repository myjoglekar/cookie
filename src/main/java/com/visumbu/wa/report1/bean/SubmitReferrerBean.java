/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.report1.bean;

import com.visumbu.wa.model.ActionLog;
import com.visumbu.wa.report.bean.groups.DealerReferrerDomainGroup;
import com.visumbu.wa.report.bean.groups.DealerReferrerTypeGroup;
import java.util.Date;

/**
 *
 * @author user
 */
public class SubmitReferrerBean {
    
    private String firstReferrerDomain;
    private String lastReferrerDomain;
    private String firstReferrerUrl;
    private String lastReferrerUrl;
    private String firstReferrerType;
    private String lastReferrerType;
    private ActionLog actionLog;
    private Date firstRefferTime;
    private Date lastRefferTime;
    private DealerReferrerDomainGroup firstDealerReferrer;
    private DealerReferrerDomainGroup lastDealerReferrer;
    private DealerReferrerTypeGroup firstDealerReferrerType;
    private DealerReferrerTypeGroup lastDealerReferrerType;

    public String getFirstReferrerType() {
        return firstReferrerType;
    }

    public void setFirstReferrerType(String firstReferrerType) {
        this.firstReferrerType = firstReferrerType;
    }

    public String getLastReferrerType() {
        return lastReferrerType;
    }

    public void setLastReferrerType(String lastReferrerType) {
        this.lastReferrerType = lastReferrerType;
    }
    
    public String getFirstReferrerDomain() {
        return firstReferrerDomain;
    }

    public void setFirstReferrerDomain(String firstReferrerDomain) {
        this.firstReferrerDomain = firstReferrerDomain;
    }

    public String getLastReferrerDomain() {
        return lastReferrerDomain;
    }

    public void setLastReferrerDomain(String lastReferrerDomain) {
        this.lastReferrerDomain = lastReferrerDomain;
    }

    public String getFirstReferrerUrl() {
        return firstReferrerUrl;
    }

    public void setFirstReferrerUrl(String firstReferrerUrl) {
        this.firstReferrerUrl = firstReferrerUrl;
    }

    public String getLastReferrerUrl() {
        return lastReferrerUrl;
    }

    public void setLastReferrerUrl(String lastReferrerUrl) {
        this.lastReferrerUrl = lastReferrerUrl;
    }

    public ActionLog getActionLog() {
        return actionLog;
    }

    public void setActionLog(ActionLog actionLog) {
        this.actionLog = actionLog;
    }

    public Date getFirstRefferTime() {
        return firstRefferTime;
    }

    public void setFirstRefferTime(Date firstRefferTime) {
        this.firstRefferTime = firstRefferTime;
    }

    public Date getLastRefferTime() {
        return lastRefferTime;
    }

    public void setLastRefferTime(Date lastRefferTime) {
        this.lastRefferTime = lastRefferTime;
    }

    public DealerReferrerDomainGroup getFirstDealerReferrer() {
        return firstDealerReferrer;
    }

    public void setFirstDealerReferrer(DealerReferrerDomainGroup firstDealerReferrer) {
        this.firstDealerReferrer = firstDealerReferrer;
    }

    public DealerReferrerDomainGroup getLastDealerReferrer() {
        return lastDealerReferrer;
    }

    public void setLastDealerReferrer(DealerReferrerDomainGroup lastDealerReferrer) {
        this.lastDealerReferrer = lastDealerReferrer;
    }

    public DealerReferrerTypeGroup getFirstDealerReferrerType() {
        return firstDealerReferrerType;
    }

    public void setFirstDealerReferrerType(DealerReferrerTypeGroup firstDealerReferrerType) {
        this.firstDealerReferrerType = firstDealerReferrerType;
    }

    public DealerReferrerTypeGroup getLastDealerReferrerType() {
        return lastDealerReferrerType;
    }

    public void setLastDealerReferrerType(DealerReferrerTypeGroup lastDealerReferrerType) {
        this.lastDealerReferrerType = lastDealerReferrerType;
    }

}
