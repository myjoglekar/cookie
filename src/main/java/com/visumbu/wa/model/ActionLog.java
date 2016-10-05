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
@Table(name = "action_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionLog.findAll", query = "SELECT a FROM ActionLog a"),
    @NamedQuery(name = "ActionLog.findById", query = "SELECT a FROM ActionLog a WHERE a.id = :id"),
    @NamedQuery(name = "ActionLog.findByActionName", query = "SELECT a FROM ActionLog a WHERE a.actionName = :actionName"),
    @NamedQuery(name = "ActionLog.findByVisitId", query = "SELECT a FROM ActionLog a WHERE a.visitId = :visitId"),
    @NamedQuery(name = "ActionLog.findByFingerprint", query = "SELECT a FROM ActionLog a WHERE a.fingerprint = :fingerprint"),
    @NamedQuery(name = "ActionLog.findByActionTime", query = "SELECT a FROM ActionLog a WHERE a.actionTime = :actionTime"),
    @NamedQuery(name = "ActionLog.findByDuration", query = "SELECT a FROM ActionLog a WHERE a.duration = :duration"),
    @NamedQuery(name = "ActionLog.findByLocalHour", query = "SELECT a FROM ActionLog a WHERE a.localHour = :localHour"),
    @NamedQuery(name = "ActionLog.findByLocalMin", query = "SELECT a FROM ActionLog a WHERE a.localMin = :localMin"),
    @NamedQuery(name = "ActionLog.findByLocalSec", query = "SELECT a FROM ActionLog a WHERE a.localSec = :localSec"),
    @NamedQuery(name = "ActionLog.findByLocalTime", query = "SELECT a FROM ActionLog a WHERE a.localTime = :localTime"),
    @NamedQuery(name = "ActionLog.findByUserAgent", query = "SELECT a FROM ActionLog a WHERE a.userAgent = :userAgent")})
public class ActionLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1024)
    @Column(name = "action_name")
    private String actionName;
    @Column(name = "visit_id")
    private Integer visitId;
    @Size(max = 256)
    @Column(name = "fingerprint")
    private String fingerprint;
    @Size(max = 4096)
    @Column(name = "url")
    private String url;
    @Column(name = "action_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actionTime;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "local_hour")
    private Integer localHour;
    @Column(name = "local_min")
    private Integer localMin;
    @Column(name = "local_sec")
    private Integer localSec;
    @Column(name = "local_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date localTime;
    @Size(max = 1024)
    @Column(name = "user_agent")
    private String userAgent;

    public ActionLog() {
    }

    public ActionLog(Integer id) {
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

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
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

    public Date getLocalTime() {
        return localTime;
    }

    public void setLocalTime(Date localTime) {
        this.localTime = localTime;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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
        if (!(object instanceof ActionLog)) {
            return false;
        }
        ActionLog other = (ActionLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.ActionLog[ id=" + id + " ]";
    }
    
}
