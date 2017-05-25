/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.model;

import com.l2tmedia.cookie.utils.DateUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author netphenix
 */
@Entity
@Table(name = "dealer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dealer.findAll", query = "SELECT w FROM Dealer w")
    ,
    @NamedQuery(name = "Dealer.findById", query = "SELECT w FROM Dealer w WHERE w.id = :id")
    ,
    @NamedQuery(name = "Dealer.findByDealerRefId", query = "SELECT w FROM Dealer w WHERE w.dealerRefId = :dealerRefId")
    ,
    @NamedQuery(name = "Dealer.findBySiteId", query = "SELECT w FROM Dealer w WHERE w.siteId = :siteId")
    ,
    @NamedQuery(name = "Dealer.findByDealerName", query = "SELECT w FROM Dealer w WHERE w.dealerName = :dealerName")
    ,
    @NamedQuery(name = "Dealer.findByWebsite", query = "SELECT w FROM Dealer w WHERE w.website = :website")
    ,
    @NamedQuery(name = "Dealer.findByCreatedTime", query = "SELECT w FROM Dealer w WHERE w.createdTime = :createdTime")
    ,
    @NamedQuery(name = "Dealer.findByEmail", query = "SELECT w FROM Dealer w WHERE w.email = :email")
    ,
    @NamedQuery(name = "Dealer.findByLastSiteVisit", query = "SELECT w FROM Dealer w WHERE w.lastSiteVisit = :lastSiteVisit")
    ,
    @NamedQuery(name = "Dealer.findByStatus", query = "SELECT w FROM Dealer w WHERE w.status = :status")})
public class Dealer implements Serializable {
    
    public static final String STATUS_ACTIVE = "Active";
    public static final String STATUS_INACTIVE = "InActive";
    public static final String STATUS_DUPLICATE = "Duplicate";
    public static final String STATUS_CANCELLED = "Cancelled";
    public static final String STATUS_DEFAULT = "Default";
    public static final String STATUS_MAP_INACTIVE = "Inactive";
    public static final String STATUS_NO_BUDGET = "NoBudget";
    
    @Size(max = 256)
    @Column(name = "dealer_group")
    private String dealerGroup;
    @Size(max = 1024)
    @Column(name = "oem")
    private String oem;
    @OneToMany(mappedBy = "dealerId")
    private Collection<DealerProduct> dealerProductCollection;

    @OneToMany(mappedBy = "dealerId")
    private Collection<DealerSite> dealerSiteCollection;
    @OneToMany(mappedBy = "dealerId")
    private Collection<VisitLog> visitLogCollection;
    @OneToMany(mappedBy = "dealerId")
    private Collection<ActionLog> actionLogCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "dealer_ref_id")
    private String dealerRefId;
    @Size(max = 128)
    @Column(name = "site_id")
    private String siteId;
    @Size(max = 1024)
    @Column(name = "dealer_name")
    private String dealerName;
    @Size(max = 4000)
    @Column(name = "dealer_address")
    private String dealerAddress;
    @Size(max = 4000)
    @Column(name = "dealer_state")
    private String dealerState;
    @Size(max = 4000)
    @Column(name = "dealer_city")
    private String dealerCity;
    @Size(max = 64)
    @Column(name = "dealer_zip")
    private String dealerZip;
    @Size(max = 1024)
    @Column(name = "segment_name")
    private String segmentName;
    @Size(max = 1024)
    @Column(name = "timezoneName")
    private String timezoneName;
    @Size(max = 1024)
    @Column(name = "oem_name")
    private String oemName;
    @Size(max = 32)
    @Column(name = "oem_program_id")
    private String oemProgramId;
    @Size(max = 1024)
    @Column(name = "oem_program_name")
    private String oemProgramName;
    @Size(max = 32)
    @Column(name = "oem_region_id")
    private String oemRegionId;
    @Size(max = 1024)
    @Column(name = "oem_region_name")
    private String oemRegionName;

    @Size(max = 1024)
    @Column(name = "active_clients_product_name")
    private String activeClientsProductName;
    @Size(max = 1024)
    @Column(name = "digital_advisor")
    private String digitalAdvisor;
    @Size(max = 64)
    @Column(name = "phone")
    private String phone;
    @Size(max = 1024)
    @Column(name = "website")
    private String website;
    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @Column(name = "campaign_launch_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date campaignLaunchDate;
    @Column(name = "first_contract_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstContractTime;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 1024)
    @Column(name = "email")
    private String email;
    @Size(max = 1024)
    @Column(name = "communication_email")
    private String communicationEmail;
    @Size(max = 1024)
    @Column(name = "accountmanager_email")
    private String accountManagerEmail;
    @Column(name = "last_site_visit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSiteVisit;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @Size(max = 45)
    @Column(name = "custom_status")
    private String customStatus;
    @Size(max = 4000)
    @Column(name = "custom_comment")
    private String customComment;
    @Size(max = 45)
    @Column(name = "map_status")
    private String mapStatus;
    @Column(name = "budget")
    private Double budget;
    @Column(name = "total_budget")
    private Double totalBudget;
    @Size(max = 45)
    @Column(name = "duplicate_status")
    private String duplicateStatus;

    public Dealer() {
    }

    public Dealer(Integer id) {
        this.id = id;
    }

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

    public String getAccountManagerEmail() {
        return accountManagerEmail;
    }

    public void setAccountManagerEmail(String accountManagerEmail) {
        this.accountManagerEmail = accountManagerEmail;
    }

    public Date getLastSiteVisit() {
        return lastSiteVisit;
    }

    public void setLastSiteVisit(Date lastSiteVisit) {
        this.lastSiteVisit = lastSiteVisit;
    }

    public String getStatus() {
        if (lastSiteVisit == null) {
            return STATUS_INACTIVE;
        }

        Date yesterday = DateUtils.getYesterday();

        Long dateDiff = DateUtils.dateDiffInSec(yesterday, lastSiteVisit);
        if (dateDiff >= 0) {
            System.out.println(this.getDealerName() + " - " + yesterday + " - " + lastSiteVisit + " Diff: " + dateDiff);
            return STATUS_INACTIVE;
        }

        return STATUS_ACTIVE;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMapStatus() {
        return mapStatus;
    }

    public void setMapStatus(String mapStatus) {
        this.mapStatus = mapStatus;
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

    public String getOemProgramId() {
        return oemProgramId;
    }

    public void setOemProgramId(String oemProgramId) {
        this.oemProgramId = oemProgramId;
    }

    public String getOemProgramName() {
        return oemProgramName;
    }

    public void setOemProgramName(String oemProgramName) {
        this.oemProgramName = oemProgramName;
    }

    public String getOemRegionId() {
        return oemRegionId;
    }

    public void setOemRegionId(String oemRegionId) {
        this.oemRegionId = oemRegionId;
    }

    public String getOemRegionName() {
        return oemRegionName;
    }

    public void setOemRegionName(String oemRegionName) {
        this.oemRegionName = oemRegionName;
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

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dealer)) {
            return false;
        }
        Dealer other = (Dealer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.Dealer[ id=" + id + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<DealerSite> getDealerSiteCollection() {
        return dealerSiteCollection;
    }

    public void setDealerSiteCollection(Collection<DealerSite> dealerSiteCollection) {
        this.dealerSiteCollection = dealerSiteCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<VisitLog> getVisitLogCollection() {
        return visitLogCollection;
    }

    public void setVisitLogCollection(Collection<VisitLog> visitLogCollection) {
        this.visitLogCollection = visitLogCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ActionLog> getActionLogCollection() {
        return actionLogCollection;
    }

    public void setActionLogCollection(Collection<ActionLog> actionLogCollection) {
        this.actionLogCollection = actionLogCollection;
    }

    public String getDealerGroup() {
        return dealerGroup;
    }

    public void setDealerGroup(String dealerGroup) {
        this.dealerGroup = dealerGroup;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<DealerProduct> getDealerProductCollection() {
        return dealerProductCollection;
    }

    public void setDealerProductCollection(Collection<DealerProduct> dealerProductCollection) {
        this.dealerProductCollection = dealerProductCollection;
    }

    public String getCustomStatus() {
        return customStatus;
    }

    public void setCustomStatus(String customStatus) {
        this.customStatus = customStatus;
    }

    /**
     * @return the customComment
     */
    public String getCustomComment() {
        return customComment;
    }

    /**
     * @param customComment the customComment to set
     */
    public void setCustomComment(String customComment) {
        this.customComment = customComment;
    }

    /**
     * @return the totalBudget
     */
    public Double getTotalBudget() {
        return this.totalBudget;
    }

    /**
     * @param totalBudget the totalBudget to set
     */
    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    /**
     * @return the duplicateStatus
     */
    public String getDuplicateStatus() {
        return duplicateStatus;
    }

    /**
     * @param duplicateStatus the duplicateStatus to set
     */
    public void setDuplicateStatus(String duplicateStatus) {
        this.duplicateStatus = duplicateStatus;
    }

}
