/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.report1.bean;

import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

/**
 *
 * @author user
 */
public class VisitLogServiceBean {

    private String refId;
    private String visitId;
    private String browser;
    private String city;
    private String zipcode;
    private String state;
    private String country;
    private String device;
    private String ipaddress;
    private String domainName;
    private String page;
    private String url;
    private String lastVisitTime;
    private String visitCount;
    private Long duration;
    private String referrerUrl;
    private String referrerType;
    private String dealerId;
    private String timeZome;
    private String fingerprint;
    private String os;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(String lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public String getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getReferrerUrl() {
        return referrerUrl;
    }

    public void setReferrerUrl(String referrerUrl) {
        this.referrerUrl = referrerUrl;
    }

    public String getReferrerType() {
        return referrerType;
    }

    public void setReferrerType(String referrerType) {
        this.referrerType = referrerType;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getTimeZome() {
        return timeZome;
    }

    public void setTimeZome(String timeZome) {
        this.timeZome = timeZome;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

}
