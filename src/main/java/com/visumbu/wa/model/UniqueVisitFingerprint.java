/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "unique_visit_fingerprint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniqueVisitFingerprint.findAll", query = "SELECT u FROM UniqueVisitFingerprint u"),
    @NamedQuery(name = "UniqueVisitFingerprint.findById", query = "SELECT u FROM UniqueVisitFingerprint u WHERE u.id = :id"),
    @NamedQuery(name = "UniqueVisitFingerprint.findByFingerprint", query = "SELECT u FROM UniqueVisitFingerprint u WHERE u.fingerprint = :fingerprint")})
public class UniqueVisitFingerprint implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "fingerprint")
    private String fingerprint;
    @JoinColumn(name = "unique_visit_id", referencedColumnName = "id")
    @ManyToOne
    private UniqueVisit uniqueVisitId;

    public UniqueVisitFingerprint() {
    }

    public UniqueVisitFingerprint(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public UniqueVisit getUniqueVisitId() {
        return uniqueVisitId;
    }

    public void setUniqueVisitId(UniqueVisit uniqueVisitId) {
        this.uniqueVisitId = uniqueVisitId;
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
        if (!(object instanceof UniqueVisitFingerprint)) {
            return false;
        }
        UniqueVisitFingerprint other = (UniqueVisitFingerprint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.UniqueVisitFingerprint[ id=" + id + " ]";
    }
    
}
