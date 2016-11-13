/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.report1.bean;

import com.visumbu.wa.model.ActionLog;
import com.visumbu.wa.report.bean.groups.DealerReferrerGroup;
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
    private ActionLog actionLog;
    private Date firstRefferTime;
    private Date lastRefferTime;
    private DealerReferrerGroup firstDealerReferrer;
    private DealerReferrerGroup lastDealerReferrer;

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

    public DealerReferrerGroup getFirstDealerReferrer() {
        return firstDealerReferrer;
    }

    public void setFirstDealerReferrer(DealerReferrerGroup firstDealerReferrer) {
        this.firstDealerReferrer = firstDealerReferrer;
    }

    public DealerReferrerGroup getLastDealerReferrer() {
        return lastDealerReferrer;
    }

    public void setLastDealerReferrer(DealerReferrerGroup lastDealerReferrer) {
        this.lastDealerReferrer = lastDealerReferrer;
    }
    
    

}
