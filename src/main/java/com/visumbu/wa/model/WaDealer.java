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
@Table(name = "wa_dealer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WaDealer.findAll", query = "SELECT w FROM WaDealer w"),
    @NamedQuery(name = "WaDealer.findById", query = "SELECT w FROM WaDealer w WHERE w.id = :id"),
    @NamedQuery(name = "WaDealer.findByDealerRefId", query = "SELECT w FROM WaDealer w WHERE w.dealerRefId = :dealerRefId"),
    @NamedQuery(name = "WaDealer.findBySiteId", query = "SELECT w FROM WaDealer w WHERE w.siteId = :siteId"),
    @NamedQuery(name = "WaDealer.findByDealerName", query = "SELECT w FROM WaDealer w WHERE w.dealerName = :dealerName"),
    @NamedQuery(name = "WaDealer.findByWebsite", query = "SELECT w FROM WaDealer w WHERE w.website = :website"),
    @NamedQuery(name = "WaDealer.findByCreatedTime", query = "SELECT w FROM WaDealer w WHERE w.createdTime = :createdTime"),
    @NamedQuery(name = "WaDealer.findByEmail", query = "SELECT w FROM WaDealer w WHERE w.email = :email")})
public class WaDealer implements Serializable {

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
    @Size(max = 1024)
    @Column(name = "website")
    private String website;
    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 1024)
    @Column(name = "email")
    private String email;

    public WaDealer() {
    }

    public WaDealer(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WaDealer)) {
            return false;
        }
        WaDealer other = (WaDealer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.WaDealer[ id=" + id + " ]";
    }
    
}
