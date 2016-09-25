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
@Table(name = "visit_plugin_properties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VisitPluginProperties.findAll", query = "SELECT v FROM VisitPluginProperties v"),
    @NamedQuery(name = "VisitPluginProperties.findById", query = "SELECT v FROM VisitPluginProperties v WHERE v.id = :id"),
    @NamedQuery(name = "VisitPluginProperties.findByPropertyName", query = "SELECT v FROM VisitPluginProperties v WHERE v.propertyName = :propertyName"),
    @NamedQuery(name = "VisitPluginProperties.findByPropertyValue", query = "SELECT v FROM VisitPluginProperties v WHERE v.propertyValue = :propertyValue")})
public class VisitPluginProperties implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 128)
    @Column(name = "property_name")
    private String propertyName;
    @Size(max = 1024)
    @Column(name = "property_value")
    private String propertyValue;
    @JoinColumn(name = "visit_log_id", referencedColumnName = "id")
    @ManyToOne
    private VisitLog visitLogId;

    public VisitPluginProperties() {
    }

    public VisitPluginProperties(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public VisitLog getVisitLogId() {
        return visitLogId;
    }

    public void setVisitLogId(VisitLog visitLogId) {
        this.visitLogId = visitLogId;
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
        if (!(object instanceof VisitPluginProperties)) {
            return false;
        }
        VisitPluginProperties other = (VisitPluginProperties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.VisitPluginProperties[ id=" + id + " ]";
    }
    
}
