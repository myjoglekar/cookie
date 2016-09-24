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
 * @author netphenix
 */
@Entity
@Table(name = "visit_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VisitLog.findAll", query = "SELECT v FROM VisitLog v"),
    @NamedQuery(name = "VisitLog.findById", query = "SELECT v FROM VisitLog v WHERE v.id = :id"),
    @NamedQuery(name = "VisitLog.findBySiteId", query = "SELECT v FROM VisitLog v WHERE v.siteId = :siteId"),
    @NamedQuery(name = "VisitLog.findByDuration", query = "SELECT v FROM VisitLog v WHERE v.duration = :duration"),
    @NamedQuery(name = "VisitLog.findByVisitTime", query = "SELECT v FROM VisitLog v WHERE v.visitTime = :visitTime"),
    @NamedQuery(name = "VisitLog.findByResolution", query = "SELECT v FROM VisitLog v WHERE v.resolution = :resolution"),
    @NamedQuery(name = "VisitLog.findByOs", query = "SELECT v FROM VisitLog v WHERE v.os = :os"),
    @NamedQuery(name = "VisitLog.findByOsVersion", query = "SELECT v FROM VisitLog v WHERE v.osVersion = :osVersion"),
    @NamedQuery(name = "VisitLog.findByBrowser", query = "SELECT v FROM VisitLog v WHERE v.browser = :browser"),
    @NamedQuery(name = "VisitLog.findByBrowserVersion", query = "SELECT v FROM VisitLog v WHERE v.browserVersion = :browserVersion"),
    @NamedQuery(name = "VisitLog.findByFingerprint", query = "SELECT v FROM VisitLog v WHERE v.fingerprint = :fingerprint"),
    @NamedQuery(name = "VisitLog.findBySessionId", query = "SELECT v FROM VisitLog v WHERE v.sessionId = :sessionId"),
    @NamedQuery(name = "VisitLog.findByDeviceId", query = "SELECT v FROM VisitLog v WHERE v.deviceId = :deviceId"),
    @NamedQuery(name = "VisitLog.findByDeviceType", query = "SELECT v FROM VisitLog v WHERE v.deviceType = :deviceType"),
    @NamedQuery(name = "VisitLog.findByLocation", query = "SELECT v FROM VisitLog v WHERE v.location = :location"),
    @NamedQuery(name = "VisitLog.findByIpAddress", query = "SELECT v FROM VisitLog v WHERE v.ipAddress = :ipAddress"),
    @NamedQuery(name = "VisitLog.findByZipCode", query = "SELECT v FROM VisitLog v WHERE v.zipCode = :zipCode"),
    @NamedQuery(name = "VisitLog.findByCountry", query = "SELECT v FROM VisitLog v WHERE v.country = :country"),
    @NamedQuery(name = "VisitLog.findByState", query = "SELECT v FROM VisitLog v WHERE v.state = :state"),
    @NamedQuery(name = "VisitLog.findByCity", query = "SELECT v FROM VisitLog v WHERE v.city = :city"),
    @NamedQuery(name = "VisitLog.findByIsp", query = "SELECT v FROM VisitLog v WHERE v.isp = :isp"),
    @NamedQuery(name = "VisitLog.findByUrl", query = "SELECT v FROM VisitLog v WHERE v.url = :url"),
    @NamedQuery(name = "VisitLog.findByPageName", query = "SELECT v FROM VisitLog v WHERE v.pageName = :pageName"),
    @NamedQuery(name = "VisitLog.findByTimeZone", query = "SELECT v FROM VisitLog v WHERE v.timeZone = :timeZone"),
    @NamedQuery(name = "VisitLog.findByCookieAllowed", query = "SELECT v FROM VisitLog v WHERE v.cookieAllowed = :cookieAllowed"),
    @NamedQuery(name = "VisitLog.findByJavaAllowed", query = "SELECT v FROM VisitLog v WHERE v.javaAllowed = :javaAllowed"),
    @NamedQuery(name = "VisitLog.findByRefererUrl", query = "SELECT v FROM VisitLog v WHERE v.refererUrl = :refererUrl"),
    @NamedQuery(name = "VisitLog.findByRefererType", query = "SELECT v FROM VisitLog v WHERE v.refererType = :refererType"),
    @NamedQuery(name = "VisitLog.findByVisitCount", query = "SELECT v FROM VisitLog v WHERE v.visitCount = :visitCount"),
    @NamedQuery(name = "VisitLog.findByVisiterLocalTime", query = "SELECT v FROM VisitLog v WHERE v.visiterLocalTime = :visiterLocalTime"),
    @NamedQuery(name = "VisitLog.findByBrowserEngine", query = "SELECT v FROM VisitLog v WHERE v.browserEngine = :browserEngine"),
    @NamedQuery(name = "VisitLog.findByFirstVisitTime", query = "SELECT v FROM VisitLog v WHERE v.firstVisitTime = :firstVisitTime"),
    @NamedQuery(name = "VisitLog.findByLocationLatitude", query = "SELECT v FROM VisitLog v WHERE v.locationLatitude = :locationLatitude"),
    @NamedQuery(name = "VisitLog.findByLocationLongitude", query = "SELECT v FROM VisitLog v WHERE v.locationLongitude = :locationLongitude"),
    @NamedQuery(name = "VisitLog.findByPdfAllowed", query = "SELECT v FROM VisitLog v WHERE v.pdfAllowed = :pdfAllowed"),
    @NamedQuery(name = "VisitLog.findByFlashAllowed", query = "SELECT v FROM VisitLog v WHERE v.flashAllowed = :flashAllowed"),
    @NamedQuery(name = "VisitLog.findByDirectorAllowed", query = "SELECT v FROM VisitLog v WHERE v.directorAllowed = :directorAllowed"),
    @NamedQuery(name = "VisitLog.findByDeviceModel", query = "SELECT v FROM VisitLog v WHERE v.deviceModel = :deviceModel")})
public class VisitLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "site_id")
    private String siteId;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "visit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date visitTime;
    @Size(max = 256)
    @Column(name = "resolution")
    private String resolution;
    @Size(max = 256)
    @Column(name = "os")
    private String os;
    @Size(max = 256)
    @Column(name = "os_version")
    private String osVersion;
    @Size(max = 256)
    @Column(name = "browser")
    private String browser;
    @Size(max = 256)
    @Column(name = "browser_version")
    private String browserVersion;
    @Size(max = 256)
    @Column(name = "fingerprint")
    private String fingerprint;
    @Size(max = 256)
    @Column(name = "session_id")
    private String sessionId;
    @Size(max = 256)
    @Column(name = "device_id")
    private String deviceId;
    @Size(max = 256)
    @Column(name = "device_type")
    private String deviceType;
    @Size(max = 256)
    @Column(name = "location")
    private String location;
    @Size(max = 32)
    @Column(name = "ip_address")
    private String ipAddress;
    @Size(max = 16)
    @Column(name = "zip_code")
    private String zipCode;
    @Size(max = 256)
    @Column(name = "country")
    private String country;
    @Size(max = 256)
    @Column(name = "state")
    private String state;
    @Size(max = 1024)
    @Column(name = "city")
    private String city;
    @Size(max = 128)
    @Column(name = "isp")
    private String isp;
    @Size(max = 4098)
    @Column(name = "url")
    private String url;
    @Size(max = 4098)
    @Column(name = "pageName")
    private String pageName;
    @Size(max = 256)
    @Column(name = "timeZone")
    private String timeZone;
    @Column(name = "cookie_allowed")
    private Integer cookieAllowed;
    @Column(name = "java_allowed")
    private Integer javaAllowed;
    @Size(max = 4098)
    @Column(name = "referer_url")
    private String refererUrl;
    @Size(max = 1024)
    @Column(name = "referer_type")
    private String refererType;
    @Column(name = "visit_count")
    private Integer visitCount;
    @Size(max = 64)
    @Column(name = "visiter_local_time")
    private String visiterLocalTime;
    @Size(max = 256)
    @Column(name = "browser_engine")
    private String browserEngine;
    @Column(name = "first_visit_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstVisitTime;
    @Size(max = 16)
    @Column(name = "location_latitude")
    private String locationLatitude;
    @Size(max = 16)
    @Column(name = "location_longitude")
    private String locationLongitude;
    @Column(name = "pdf_allowed")
    private Integer pdfAllowed;
    @Column(name = "flash_allowed")
    private Integer flashAllowed;
    @Column(name = "director_allowed")
    private Integer directorAllowed;
    @Size(max = 45)
    @Column(name = "device_model")
    private String deviceModel;
    @JoinColumn(name = "dealer_id", referencedColumnName = "id")
    @ManyToOne
    private WaDealer dealerId;

    public VisitLog() {
    }

    public VisitLog(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getCookieAllowed() {
        return cookieAllowed;
    }

    public void setCookieAllowed(Integer cookieAllowed) {
        this.cookieAllowed = cookieAllowed;
    }

    public Integer getJavaAllowed() {
        return javaAllowed;
    }

    public void setJavaAllowed(Integer javaAllowed) {
        this.javaAllowed = javaAllowed;
    }

    public String getRefererUrl() {
        return refererUrl;
    }

    public void setRefererUrl(String refererUrl) {
        this.refererUrl = refererUrl;
    }

    public String getRefererType() {
        return refererType;
    }

    public void setRefererType(String refererType) {
        this.refererType = refererType;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public String getVisiterLocalTime() {
        return visiterLocalTime;
    }

    public void setVisiterLocalTime(String visiterLocalTime) {
        this.visiterLocalTime = visiterLocalTime;
    }

    public String getBrowserEngine() {
        return browserEngine;
    }

    public void setBrowserEngine(String browserEngine) {
        this.browserEngine = browserEngine;
    }

    public Date getFirstVisitTime() {
        return firstVisitTime;
    }

    public void setFirstVisitTime(Date firstVisitTime) {
        this.firstVisitTime = firstVisitTime;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public Integer getPdfAllowed() {
        return pdfAllowed;
    }

    public void setPdfAllowed(Integer pdfAllowed) {
        this.pdfAllowed = pdfAllowed;
    }

    public Integer getFlashAllowed() {
        return flashAllowed;
    }

    public void setFlashAllowed(Integer flashAllowed) {
        this.flashAllowed = flashAllowed;
    }

    public Integer getDirectorAllowed() {
        return directorAllowed;
    }

    public void setDirectorAllowed(Integer directorAllowed) {
        this.directorAllowed = directorAllowed;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public WaDealer getDealerId() {
        return dealerId;
    }

    public void setDealerId(WaDealer dealerId) {
        this.dealerId = dealerId;
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
        if (!(object instanceof VisitLog)) {
            return false;
        }
        VisitLog other = (VisitLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.VisitLog[ id=" + id + " ]";
    }
    
}