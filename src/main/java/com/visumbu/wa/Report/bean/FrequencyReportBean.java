/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.Report.bean;

/**
 *
 * @author netphenix
 */
public class FrequencyReportBean {
    private String dealerName;
    private String fingerprint;
    private String city;
    private Integer noOfVisits;
    private Integer totalTimes;

    public Integer getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes) {
        this.totalTimes = totalTimes;
    }
    
    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNoOfVisits() {
        return noOfVisits;
    }

    public void setNoOfVisits(Integer noOfVisits) {
        this.noOfVisits = noOfVisits;
    }
    
    
    
}
