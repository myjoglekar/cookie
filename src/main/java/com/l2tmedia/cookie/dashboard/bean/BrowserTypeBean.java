/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.dashboard.bean;

/**
 *
 * @author netphenix
 */
public class BrowserTypeBean {
    private String browser;
    private Integer visitCount;
    private Integer uniqueUserCount;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }   

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getUniqueUserCount() {
        return uniqueUserCount;
    }

    public void setUniqueUserCount(Integer uniqueUserCount) {
        this.uniqueUserCount = uniqueUserCount;
    }
    
    
}
