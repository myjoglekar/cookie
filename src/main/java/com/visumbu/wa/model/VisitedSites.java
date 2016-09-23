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
 * @author netphenix
 */
@Entity
@Table(name = "visited_sites")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VisitedSites.findAll", query = "SELECT v FROM VisitedSites v"),
    @NamedQuery(name = "VisitedSites.findById", query = "SELECT v FROM VisitedSites v WHERE v.id = :id"),
    @NamedQuery(name = "VisitedSites.findBySiteUrl", query = "SELECT v FROM VisitedSites v WHERE v.siteUrl = :siteUrl"),
    @NamedQuery(name = "VisitedSites.findByFirstVisitTime", query = "SELECT v FROM VisitedSites v WHERE v.firstVisitTime = :firstVisitTime"),
    @NamedQuery(name = "VisitedSites.findByLastVisitTime", query = "SELECT v FROM VisitedSites v WHERE v.lastVisitTime = :lastVisitTime"),
    @NamedQuery(name = "VisitedSites.findByTotalVisits", query = "SELECT v FROM VisitedSites v WHERE v.totalVisits = :totalVisits")})
public class VisitedSites implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1024)
    @Column(name = "site_url")
    private String siteUrl;
    @Column(name = "first_visit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstVisitTime;
    @Column(name = "last_visit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastVisitTime;
    @Column(name = "total_visits")
    private Integer totalVisits;

    public VisitedSites() {
    }

    public VisitedSites(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public Date getFirstVisitTime() {
        return firstVisitTime;
    }

    public void setFirstVisitTime(Date firstVisitTime) {
        this.firstVisitTime = firstVisitTime;
    }

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public Integer getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(Integer totalVisits) {
        this.totalVisits = totalVisits;
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
        if (!(object instanceof VisitedSites)) {
            return false;
        }
        VisitedSites other = (VisitedSites) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.VisitedSites[ id=" + id + " ]";
    }
    
}
