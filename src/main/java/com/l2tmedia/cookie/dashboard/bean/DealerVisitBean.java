/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.dashboard.bean;

/**
 *
 * @author user
 */
public class DealerVisitBean {
    private String dealerName;
    private String website;
    private Integer totalSiteVisit;
    private Integer totalPageVisit;
    private Integer uniqueUserCount;

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getTotalSiteVisit() {
        return totalSiteVisit;
    }

    public void setTotalSiteVisit(Integer totalSiteVisit) {
        this.totalSiteVisit = totalSiteVisit;
    }

    public Integer getTotalPageVisit() {
        return totalPageVisit;
    }

    public void setTotalPageVisit(Integer totalPageVisit) {
        this.totalPageVisit = totalPageVisit;
    }

    public Integer getUniqueUserCount() {
        return uniqueUserCount;
    }

    public void setUniqueUserCount(Integer uniqueUserCount) {
        this.uniqueUserCount = uniqueUserCount;
    }
    
    
}
