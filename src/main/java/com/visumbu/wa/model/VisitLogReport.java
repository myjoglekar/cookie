/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.model;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "visit_log_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VisitLogReport.findAll", query = "SELECT v FROM VisitLogReport v"),
    @NamedQuery(name = "VisitLogReport.findById", query = "SELECT v FROM VisitLogReport v WHERE v.id = :id"),
    @NamedQuery(name = "VisitLogReport.findBySiteId", query = "SELECT v FROM VisitLogReport v WHERE v.siteId = :siteId"),
    @NamedQuery(name = "VisitLogReport.findByDuration", query = "SELECT v FROM VisitLogReport v WHERE v.duration = :duration"),
    @NamedQuery(name = "VisitLogReport.findByVisitTime", query = "SELECT v FROM VisitLogReport v WHERE v.visitTime = :visitTime"),
    @NamedQuery(name = "VisitLogReport.findByVisitTimeRange", query = "SELECT v FROM VisitLogReport v WHERE v.visitTime between :startTime and :endTime order by v.visitTime"),
    @NamedQuery(name = "VisitLogReport.findByResolution", query = "SELECT v FROM VisitLogReport v WHERE v.resolution = :resolution"),
    @NamedQuery(name = "VisitLogReport.findByOs", query = "SELECT v FROM VisitLogReport v WHERE v.os = :os"),
    @NamedQuery(name = "VisitLogReport.findByOsVersion", query = "SELECT v FROM VisitLogReport v WHERE v.osVersion = :osVersion"),
    @NamedQuery(name = "VisitLogReport.findByBrowser", query = "SELECT v FROM VisitLogReport v WHERE v.browser = :browser"),
    @NamedQuery(name = "VisitLogReport.findByBrowserVersion", query = "SELECT v FROM VisitLogReport v WHERE v.browserVersion = :browserVersion"),
    @NamedQuery(name = "VisitLogReport.findByFingerprint", query = "SELECT v FROM VisitLogReport v WHERE v.fingerprint = :fingerprint"),
    @NamedQuery(name = "VisitLogReport.findBySessionId", query = "SELECT v FROM VisitLogReport v WHERE v.sessionId = :sessionId"),
    @NamedQuery(name = "VisitLogReport.findByDeviceId", query = "SELECT v FROM VisitLogReport v WHERE v.deviceId = :deviceId"),
    @NamedQuery(name = "VisitLogReport.findByDeviceType", query = "SELECT v FROM VisitLogReport v WHERE v.deviceType = :deviceType"),
    @NamedQuery(name = "VisitLogReport.findByLocation", query = "SELECT v FROM VisitLogReport v WHERE v.location = :location"),
    @NamedQuery(name = "VisitLogReport.findByIpAddress", query = "SELECT v FROM VisitLogReport v WHERE v.ipAddress = :ipAddress"),
    @NamedQuery(name = "VisitLogReport.findByZipCode", query = "SELECT v FROM VisitLogReport v WHERE v.zipCode = :zipCode"),
    @NamedQuery(name = "VisitLogReport.findByCountry", query = "SELECT v FROM VisitLogReport v WHERE v.country = :country"),
    @NamedQuery(name = "VisitLogReport.findByState", query = "SELECT v FROM VisitLogReport v WHERE v.state = :state"),
    @NamedQuery(name = "VisitLogReport.findByCity", query = "SELECT v FROM VisitLogReport v WHERE v.city = :city"),
    @NamedQuery(name = "VisitLogReport.findByIsp", query = "SELECT v FROM VisitLogReport v WHERE v.isp = :isp"),
    @NamedQuery(name = "VisitLogReport.findByUrl", query = "SELECT v FROM VisitLogReport v WHERE v.url = :url"),
    @NamedQuery(name = "VisitLogReport.findByPageName", query = "SELECT v FROM VisitLogReport v WHERE v.pageName = :pageName"),
    @NamedQuery(name = "VisitLogReport.findByTimeZone", query = "SELECT v FROM VisitLogReport v WHERE v.timeZone = :timeZone"),
    @NamedQuery(name = "VisitLogReport.findByCookieAllowed", query = "SELECT v FROM VisitLogReport v WHERE v.cookieAllowed = :cookieAllowed"),
    @NamedQuery(name = "VisitLogReport.findByJavaAllowed", query = "SELECT v FROM VisitLogReport v WHERE v.javaAllowed = :javaAllowed"),
    @NamedQuery(name = "VisitLogReport.findByReferrerUrl", query = "SELECT v FROM VisitLogReport v WHERE v.referrerUrl = :referrerUrl"),
    @NamedQuery(name = "VisitLogReport.findByReferrerType", query = "SELECT v FROM VisitLogReport v WHERE v.referrerType = :referrerType"),
    @NamedQuery(name = "VisitLogReport.findByVisitCount", query = "SELECT v FROM VisitLogReport v WHERE v.visitCount = :visitCount"),
    @NamedQuery(name = "VisitLogReport.findByVisiterLocalTime", query = "SELECT v FROM VisitLogReport v WHERE v.visiterLocalTime = :visiterLocalTime"),
    @NamedQuery(name = "VisitLogReport.findByBrowserEngine", query = "SELECT v FROM VisitLogReport v WHERE v.browserEngine = :browserEngine"),
    @NamedQuery(name = "VisitLogReport.findByFirstVisitTime", query = "SELECT v FROM VisitLogReport v WHERE v.firstVisitTime = :firstVisitTime"),
    @NamedQuery(name = "VisitLogReport.findByLocationLatitude", query = "SELECT v FROM VisitLogReport v WHERE v.locationLatitude = :locationLatitude"),
    @NamedQuery(name = "VisitLogReport.findByLocationLongitude", query = "SELECT v FROM VisitLogReport v WHERE v.locationLongitude = :locationLongitude"),
    @NamedQuery(name = "VisitLogReport.findByPdfAllowed", query = "SELECT v FROM VisitLogReport v WHERE v.pdfAllowed = :pdfAllowed"),
    @NamedQuery(name = "VisitLogReport.findByFlashAllowed", query = "SELECT v FROM VisitLogReport v WHERE v.flashAllowed = :flashAllowed"),
    @NamedQuery(name = "VisitLogReport.findByDirectorAllowed", query = "SELECT v FROM VisitLogReport v WHERE v.directorAllowed = :directorAllowed"),
    @NamedQuery(name = "VisitLogReport.findByDeviceModel", query = "SELECT v FROM VisitLogReport v WHERE v.deviceModel = :deviceModel")})
public class VisitLogReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
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
    @Column(name = "referrer_url")
    private String referrerUrl;
    @Size(max = 4098)
    @Column(name = "first_referrer_url")
    private String firstReferrerUrl;
    @Size(max = 4098)
    @Column(name = "referrer_domain")
    private String referrerDomain;
    @Size(max = 1024)
    @Column(name = "referrer_type")
    private String referrerType;
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
    @Size(max = 4098)
    @Column(name = "user_agent")
    private String userAgent;
    @Size(max = 1024)
    @Column(name = "domain_name")
    private String domainName;
    @Size(max = 1024)
    @Column(name = "char_set")
    private String charSet;

    @Column(name = "first_visit_ts")
    private Long firstVisitTs;
    @Column(name = "last_visit_ts")
    private Long lastVisitTs;
    @Column(name = "visit_id")
    private String visitId;

    @Column(name = "location_timezone")
    private String locationTimeZone;
    @Column(name = "region_name")
    private String regionName;
    @Column(name = "metro_code")
    private String metroCode;
    @Column(name = "region_code")
    private String regionCode;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "processed")
    private Integer processed;
    
    @JoinColumn(name = "dealer_id", referencedColumnName = "id")
    @ManyToOne
    private Dealer dealerId;

    public VisitLogReport() {
    }

    public VisitLogReport(Integer id) {
        this.id = id;
    }

    public Integer getProcessed() {
        return processed;
    }

    public void setProcessed(Integer processed) {
        this.processed = processed;
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

    public String getReferrerUrl() {
        return referrerUrl;
    }

    public void setReferrerUrl(String referrerUrl) {
        this.referrerUrl = referrerUrl;
    }

    public String getFirstReferrerUrl() {
        return firstReferrerUrl;
    }

    public void setFirstReferrerUrl(String firstReferrerUrl) {
        this.firstReferrerUrl = firstReferrerUrl;
    }
    
    public String getReferrerDomain() {
        return referrerDomain;
    }

    public void setReferrerDomain(String referrerDomain) {
        this.referrerDomain = referrerDomain;
    }

    public String getReferrerType() {
        return referrerType;
    }

    public void setReferrerType(String referrerType) {
        this.referrerType = referrerType;
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

    public Dealer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Dealer dealerId) {
        this.dealerId = dealerId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public Long getFirstVisitTs() {
        return firstVisitTs;
    }

    public void setFirstVisitTs(Long firstVisitTs) {
        this.firstVisitTs = firstVisitTs;
    }

    public Long getLastVisitTs() {
        return lastVisitTs;
    }

    public void setLastVisitTs(Long lastVisitTs) {
        this.lastVisitTs = lastVisitTs;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getLocationTimeZone() {
        return locationTimeZone;
    }

    public void setLocationTimeZone(String locationTimeZone) {
        this.locationTimeZone = locationTimeZone;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getMetroCode() {
        return metroCode;
    }

    public void setMetroCode(String metroCode) {
        this.metroCode = metroCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
        if (!(object instanceof VisitLogReport)) {
            return false;
        }
        VisitLogReport other = (VisitLogReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.visumbu.wa.model.VisitLogReport[ id=" + id + " ]";
    }

}
