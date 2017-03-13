/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.model;

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
@Table(name = "unique_visit_visit_id")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UniqueVisitVisitId.findAll", query = "SELECT u FROM UniqueVisitVisitId u"),
    @NamedQuery(name = "UniqueVisitVisitId.findById", query = "SELECT u FROM UniqueVisitVisitId u WHERE u.id = :id"),
    @NamedQuery(name = "UniqueVisitVisitId.findByVisitId", query = "SELECT u FROM UniqueVisitVisitId u WHERE u.visitId = :visitId")})
public class UniqueVisitVisitId implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "visit_id")
    private String visitId;
    @JoinColumn(name = "unique_visit_id", referencedColumnName = "id")
    @ManyToOne
    private UniqueVisit uniqueVisitId;

    public UniqueVisitVisitId() {
    }

    public UniqueVisitVisitId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
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
        if (!(object instanceof UniqueVisitVisitId)) {
            return false;
        }
        UniqueVisitVisitId other = (UniqueVisitVisitId) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.UniqueVisitVisitId[ id=" + id + " ]";
    }
    
}
