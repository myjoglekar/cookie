/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.report.bean;

/**
 *
 * @author netphenix
 */
public class VisitReportBean {
    private String dealerName;
    private String url;
    private Integer visitTime;
    private String deviceType;
    private String visiterLocalTime;
    private String locationTimezone;
    private String ipAddress;
    private String city;
    private String zipCode;
    private String country;
    private String referrerUrl;

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Integer visitTime) {
        this.visitTime = visitTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getVisiterLocalTime() {
        return visiterLocalTime;
    }

    public void setVisiterLocalTime(String visiterLocalTime) {
        this.visiterLocalTime = visiterLocalTime;
    }

    public String getLocationTimezone() {
        return locationTimezone;
    }

    public void setLocationTimezone(String locationTimezone) {
        this.locationTimezone = locationTimezone;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReferrerUrl() {
        return referrerUrl;
    }

    public void setReferrerUrl(String referrerUrl) {
        this.referrerUrl = referrerUrl;
    }
    
    
    
}
