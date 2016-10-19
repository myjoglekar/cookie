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
    private String totalSiteVisit;
    private String uniqueSiteVisit;
    private String visitedDomains;
    private String totalVisits;
    
    private String uniqueUserCount;

    public String getTotalSiteVisit() {
        return totalSiteVisit;
    }

    public void setTotalSiteVisit(String totalSiteVisit) {
        this.totalSiteVisit = totalSiteVisit;
    }

    public String getUniqueSiteVisit() {
        return uniqueSiteVisit;
    }

    public void setUniqueSiteVisit(String uniqueSiteVisit) {
        this.uniqueSiteVisit = uniqueSiteVisit;
    }

    public String getVisitedDomains() {
        return visitedDomains;
    }

    public void setVisitedDomains(String visitedDomains) {
        this.visitedDomains = visitedDomains;
    }

    public String getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(String totalVisits) {
        this.totalVisits = totalVisits;
    }

    public String getUniqueUserCount() {
        return uniqueUserCount;
    }

    public void setUniqueUserCount(String uniqueUserCount) {
        this.uniqueUserCount = uniqueUserCount;
    }

    
    
}
