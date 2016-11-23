/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.dashboard.bean;

/**
 *
 * @author user
 */
public class DashboardTickers {

    private Integer totalSiteVisit;
    private Integer uniqueSiteVisit;
    private Integer visitedDomains;
    private Integer totalVisits;
    private Integer referrerDomains;
    private Integer formFilled;

    private Integer uniqueUserCount;

    public Integer getFormFilled() {
        return formFilled;
    }

    public void setFormFilled(Integer formFilled) {
        this.formFilled = formFilled;
    }
    
    public Integer getTotalSiteVisit() {
        return totalSiteVisit;
    }

    public void setTotalSiteVisit(Integer totalSiteVisit) {
        this.totalSiteVisit = totalSiteVisit;
    }

    public Integer getUniqueSiteVisit() {
        return uniqueSiteVisit;
    }

    public void setUniqueSiteVisit(Integer uniqueSiteVisit) {
        this.uniqueSiteVisit = uniqueSiteVisit;
    }

    public Integer getVisitedDomains() {
        return visitedDomains;
    }

    public void setVisitedDomains(Integer visitedDomains) {
        this.visitedDomains = visitedDomains;
    }

    public Integer getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(Integer totalVisits) {
        this.totalVisits = totalVisits;
    }

    public Integer getUniqueUserCount() {
        return uniqueUserCount;
    }

    public void setUniqueUserCount(Integer uniqueUserCount) {
        this.uniqueUserCount = uniqueUserCount;
    }

    public Integer getReferrerDomains() {
        return referrerDomains;
    }

    public void setReferrerDomains(Integer referrerDomains) {
        this.referrerDomains = referrerDomains;
    }
}
