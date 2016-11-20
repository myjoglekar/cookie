/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.report1.bean;

/**
 *
 * @author netphenix
 */
public class FrequencyReportBean {

    private String dealerName;
    private String fingerprint;
    private String city;
    private Integer noOfVisits;
    private Integer count;
    private Integer totalTimes;
    private String noOfTimes;
    private Double avgDays;

    public FrequencyReportBean() {
    }
    
    public FrequencyReportBean(String noOfTimes, Double avgDays) {
        this.noOfTimes = noOfTimes;
        this.avgDays = avgDays;
    }

    public FrequencyReportBean(String noOfTimes, Integer count) {
        this.count = count;
        this.noOfTimes = noOfTimes;
    }

    
    
    public Double getAvgDays() {
        if (avgDays == null) {
            return 0.0;
        }
        return avgDays;
    }

    public void setAvgDays(Double avgDays) {
        this.avgDays = avgDays;
    }

    public String getNoOfTimes() {
        return noOfTimes;
    }

    public void setNoOfTimes(String noOfTimes) {
        this.noOfTimes = noOfTimes;
    }

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

    public Integer getCount() {
        if (count == null) {
            return 0;
        }
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNoOfVisits() {
        if (noOfVisits == null) {
            return 0;
        }
        return noOfVisits;
    }

    public void setNoOfVisits(Integer noOfVisits) {
        this.noOfVisits = noOfVisits;
    }

}
