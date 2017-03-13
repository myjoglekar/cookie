/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author user
 */
@Entity
@Table(name = "unique_visit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniqueVisit.findAll", query = "SELECT u FROM UniqueVisit u"),
    @NamedQuery(name = "UniqueVisit.findById", query = "SELECT u FROM UniqueVisit u WHERE u.id = :id"),
    @NamedQuery(name = "UniqueVisit.findByFingerPrint", query = "SELECT u.uniqueVisitId FROM UniqueVisitFingerprint u WHERE u.fingerprint = :fingerPrint"),
    @NamedQuery(name = "UniqueVisit.findByVisitId", query = "SELECT u.uniqueVisitId FROM UniqueVisitVisitId u WHERE u.visitId = :visitId"),
    @NamedQuery(name = "UniqueVisit.findBySessionId", query = "SELECT u.uniqueVisitId FROM UniqueVisitSessionId u WHERE u.sessionId = :sessionId"),
    @NamedQuery(name = "UniqueVisit.findByTotalVisits", query = "SELECT u FROM UniqueVisit u WHERE u.totalVisits = :totalVisits")})
public class UniqueVisit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "total_visits")
    private Integer totalVisits;
    @OneToMany(mappedBy = "uniqueVisitId")
    private Collection<UniqueVisitFingerprint> uniqueVisitFingerprintCollection;
    @OneToMany(mappedBy = "uniqueVisitId")
    private Collection<UniqueVisitVisitId> uniqueVisitVisitIdCollection;
    @OneToMany(mappedBy = "uniqueVisitId")
    private Collection<UniqueVisitSessionId> uniqueVisitSessionIdCollection;

    public UniqueVisit() {
    }

    public UniqueVisit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(Integer totalVisits) {
        this.totalVisits = totalVisits;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UniqueVisitFingerprint> getUniqueVisitFingerprintCollection() {
        return uniqueVisitFingerprintCollection;
    }

    public void setUniqueVisitFingerprintCollection(Collection<UniqueVisitFingerprint> uniqueVisitFingerprintCollection) {
        this.uniqueVisitFingerprintCollection = uniqueVisitFingerprintCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UniqueVisitVisitId> getUniqueVisitVisitIdCollection() {
        return uniqueVisitVisitIdCollection;
    }

    public void setUniqueVisitVisitIdCollection(Collection<UniqueVisitVisitId> uniqueVisitVisitIdCollection) {
        this.uniqueVisitVisitIdCollection = uniqueVisitVisitIdCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<UniqueVisitSessionId> getUniqueVisitSessionIdCollection() {
        return uniqueVisitSessionIdCollection;
    }

    public void setUniqueVisitSessionIdCollection(Collection<UniqueVisitSessionId> uniqueVisitSessionIdCollection) {
        this.uniqueVisitSessionIdCollection = uniqueVisitSessionIdCollection;
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
        if (!(object instanceof UniqueVisit)) {
            return false;
        }
        UniqueVisit other = (UniqueVisit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.UniqueVisit[ id=" + id + " ]";
    }
    
}
