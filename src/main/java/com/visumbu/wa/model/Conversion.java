/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kishore
 */
@Entity
@Table(name = "conversion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversion.findAll", query = "SELECT a FROM Conversion a"),
    @NamedQuery(name = "Conversion.findById", query = "SELECT a FROM Conversion a WHERE a.id = :id"),
    @NamedQuery(name = "Conversion.findByActionName", query = "SELECT a FROM Conversion a WHERE a.actionName = :actionName"),
    @NamedQuery(name = "Conversion.findByVisitId", query = "SELECT a FROM Conversion a WHERE a.visitId = :visitId"),
    @NamedQuery(name = "Conversion.findByFingerprint", query = "SELECT a FROM Conversion a WHERE a.fingerprint = :fingerprint"),
    @NamedQuery(name = "Conversion.findByActionTime", query = "SELECT a FROM Conversion a WHERE a.actionTime = :actionTime"),
    @NamedQuery(name = "Conversion.findByDuration", query = "SELECT a FROM Conversion a WHERE a.duration = :duration"),
    @NamedQuery(name = "Conversion.findByLocalHour", query = "SELECT a FROM Conversion a WHERE a.localHour = :localHour"),
    @NamedQuery(name = "Conversion.findByLocalMin", query = "SELECT a FROM Conversion a WHERE a.localMin = :localMin"),
    @NamedQuery(name = "Conversion.findByLocalSec", query = "SELECT a FROM Conversion a WHERE a.localSec = :localSec"),
    @NamedQuery(name = "Conversion.findByLocalTime", query = "SELECT a FROM Conversion a WHERE a.localTime = :localTime"),
    @NamedQuery(name = "Conversion.findByUserAgent", query = "SELECT a FROM Conversion a WHERE a.userAgent = :userAgent")})
public class Conversion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1024)
    @Column(name = "action_name")
    private String actionName;
    @Size(max = 256)
    @Column(name = "visit_id")
    private String visitId;
    @Column(name = "visit_count")
    private Integer visitCount;
    @Size(max = 256)
    @Column(name = "fingerprint")
    private String fingerprint;
    @Size(max = 256)
    @Column(name = "session_id")
    private String sessionId;
    @Size(max = 4096)
    @Column(name = "url")
    private String url;
    @Size(max = 4096)
    @Column(name = "referrer_url")
    private String referrerUrl;
    @Size(max = 4096)
    @Column(name = "referrer_domain")
    private String referrerDomain;
    @Size(max = 4096)
    @Column(name = "referrer_type")
    private String referrerType;
    @Column(name = "action_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionTime;
    @Column(name = "first_visit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstVisitTime;
    @Column(name = "session_visit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionVisitTime;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "local_hour")
    private Integer localHour;
    @Column(name = "local_min")
    private Integer localMin;
    @Column(name = "local_sec")
    private Integer localSec;
    @Column(name = "local_time")
    private String localTime;
    @Size(max = 1024)
    @Column(name = "user_agent")
    private String userAgent;
    @Size(max = 1024)
    @Column(name = "form_name")
    private String formName;
    @Size(max = 1024)
    @Column(name = "form_id")
    private String formId;
    @Size(max = 4098)
    @Column(name = "form_action")
    private String formAction;
    @Size(max = 32)
    @Column(name = "form_method")
    private String formMethod;
    @Column(name = "form_data", columnDefinition = "TEXT")
    private String formData;

    @JoinColumn(name = "dealer_id", referencedColumnName = "id")
    @ManyToOne
    private Dealer dealerId;
    
    public Conversion() {
    }

    public Conversion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferrerUrl() {
        return referrerUrl;
    }

    public void setReferrerUrl(String referrerUrl) {
        this.referrerUrl = referrerUrl;
    }

    public String getReferrerDomain() {
        return referrerDomain;
    }

    public void setReferrerDomain(String referrerDomain) {
        this.referrerDomain = referrerDomain;
    }
    
    public Integer getLocalHour() {
        return localHour;
    }

    public void setLocalHour(Integer localHour) {
        this.localHour = localHour;
    }

    public Integer getLocalMin() {
        return localMin;
    }

    public void setLocalMin(Integer localMin) {
        this.localMin = localMin;
    }

    public Integer getLocalSec() {
        return localSec;
    }

    public void setLocalSec(Integer localSec) {
        this.localSec = localSec;
    }

    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormAction() {
        return formAction;
    }

    public void setFormAction(String formAction) {
        this.formAction = formAction;
    }

    public String getFormMethod() {
        return formMethod;
    }

    public void setFormMethod(String formMethod) {
        this.formMethod = formMethod;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }
    
    public Dealer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Dealer dealerId) {
        this.dealerId = dealerId;
    }

    public String getReferrerType() {
        return referrerType;
    }

    public void setReferrerType(String referrerType) {
        this.referrerType = referrerType;
    }

    public Date getFirstVisitTime() {
        return firstVisitTime;
    }

    public void setFirstVisitTime(Date firstVisitTime) {
        this.firstVisitTime = firstVisitTime;
    }

    public Date getSessionVisitTime() {
        return sessionVisitTime;
    }

    public void setSessionVisitTime(Date sessionVisitTime) {
        this.sessionVisitTime = sessionVisitTime;
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
        if (!(object instanceof Conversion)) {
            return false;
        }
        Conversion other = (Conversion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.Conversion[ id=" + id + " ]";
    }

}
