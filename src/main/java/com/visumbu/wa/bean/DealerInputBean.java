/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.bean;

import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class DealerInputBean {
    private Integer id;
    private String dealerRefId;
    private String siteId;
    private String dealerName;
    private String dealerAddress;
    private String dealerState;
    private String dealerCity;
    private String dealerZip;
    private String segmentName;
    private String timezoneName;
    private String oemName;
    private String activeClientsProductName;
    private String digitalAdvisor;
    private String phone;
    private String website;
    private Date createdTime;
    private Date campaignLaunchDate;
    private Date firstContractTime;
    private String email;
    private String communicationEmail;
    private Date lastSiteVisit;
    private String status;
    private Double budget;
    private List<DealerServiceBean> dealerService;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDealerRefId() {
        return dealerRefId;
    }

    public void setDealerRefId(String dealerRefId) {
        this.dealerRefId = dealerRefId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public String getDealerState() {
        return dealerState;
    }

    public void setDealerState(String dealerState) {
        this.dealerState = dealerState;
    }

    public String getDealerCity() {
        return dealerCity;
    }

    public void setDealerCity(String dealerCity) {
        this.dealerCity = dealerCity;
    }

    public String getDealerZip() {
        return dealerZip;
    }

    public void setDealerZip(String dealerZip) {
        this.dealerZip = dealerZip;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    public String getOemName() {
        return oemName;
    }

    public void setOemName(String oemName) {
        this.oemName = oemName;
    }

    public String getActiveClientsProductName() {
        return activeClientsProductName;
    }

    public void setActiveClientsProductName(String activeClientsProductName) {
        this.activeClientsProductName = activeClientsProductName;
    }

    public String getDigitalAdvisor() {
        return digitalAdvisor;
    }

    public void setDigitalAdvisor(String digitalAdvisor) {
        this.digitalAdvisor = digitalAdvisor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCampaignLaunchDate() {
        return campaignLaunchDate;
    }

    public void setCampaignLaunchDate(Date campaignLaunchDate) {
        this.campaignLaunchDate = campaignLaunchDate;
    }

    public Date getFirstContractTime() {
        return firstContractTime;
    }

    public void setFirstContractTime(Date firstContractTime) {
        this.firstContractTime = firstContractTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCommunicationEmail() {
        return communicationEmail;
    }

    public void setCommunicationEmail(String communicationEmail) {
        this.communicationEmail = communicationEmail;
    }

    public Date getLastSiteVisit() {
        return lastSiteVisit;
    }

    public void setLastSiteVisit(Date lastSiteVisit) {
        this.lastSiteVisit = lastSiteVisit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DealerServiceBean> getDealerService() {
        return dealerService;
    }

    public void setDealerService(List<DealerServiceBean> dealerService) {
        this.dealerService = dealerService;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "DealerInputBean{" + "id=" + id + ", dealerRefId=" + dealerRefId + ", siteId=" + siteId + ", dealerName=" + dealerName + ", dealerAddress=" + dealerAddress + ", dealerState=" + dealerState + ", dealerCity=" + dealerCity + ", dealerZip=" + dealerZip + ", segmentName=" + segmentName + ", timezoneName=" + timezoneName + ", oemName=" + oemName + ", activeClientsProductName=" + activeClientsProductName + ", digitalAdvisor=" + digitalAdvisor + ", phone=" + phone + ", website=" + website + ", createdTime=" + createdTime + ", campaignLaunchDate=" + campaignLaunchDate + ", firstContractTime=" + firstContractTime + ", email=" + email + ", communicationEmail=" + communicationEmail + ", lastSiteVisit=" + lastSiteVisit + ", status=" + status + ", dealerService=" + dealerService + '}';
    }
}
