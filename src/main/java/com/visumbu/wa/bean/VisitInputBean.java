/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.bean;

import java.util.Date;
import javax.persistence.Column;

/**
 *
 * @author user
 */
public class VisitInputBean {

    private String siteId;
    private Integer duration;
    private Date visitTime;
    private Date actionTime;
    private String resolution;
    private String os;
    private String osVersion;
    private String browser;
    private String browserVersion;
    private String fingerprint;
    private String sessionId;
    private String deviceId;
    private String deviceType;
    private String location;
    private String ipAddress;
    private String zipCode;
    private String country;
    private String state;
    private String city;
    private String isp;
    private String url;
    private String pageName;
    private String timeZone;
    private String timeZoneOffset;
    private Integer cookieAllowed;
    private Integer javaAllowed;
    private String refererUrl;
    private String refererType;
    private Integer visitCount;
    private String visiterLocalTime;
    private String browserEngine;
    private Date firstVisitTime;
    private String locationLatitude;
    private String locationLongitude;
    private Integer pdfAllowed;
    private Integer flashAllowed;
    private Integer directorAllowed;
    private String domainName;
    private String userAgent;
    private String actionName;
    private String charSet;
    private String firstVisitTs;
    private String lastVisitTs;
    private String visitId;
    private Integer localHour;
    private Integer localMin;
    private Integer localSec;
    private String localTime;
    private String formName;
    private String formId;
    private String formAction;
    private String formMethod;
    private String formData;

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
    
    public String getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
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

    public String getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public void setTimeZoneOffset(String timeZoneOffset) {
        this.timeZoneOffset = timeZoneOffset;
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

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public String getFirstVisitTs() {
        return firstVisitTs;
    }

    public void setFirstVisitTs(String firstVisitTs) {
        this.firstVisitTs = firstVisitTs;
    }

    public String getLastVisitTs() {
        return lastVisitTs;
    }

    public void setLastVisitTs(String lastVisitTs) {
        this.lastVisitTs = lastVisitTs;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
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

    
    @Override
    public String toString() {
        return "VisitInputBean{" + "siteId=" + siteId + ", duration=" + duration + ", visitTime=" + visitTime + ", resolution=" + resolution + ", os=" + os + ", osVersion=" + osVersion + ", browser=" + browser + ", browserVersion=" + browserVersion + ", fingerprint=" + fingerprint + ", sessionId=" + sessionId + ", deviceId=" + deviceId + ", deviceType=" + deviceType + ", location=" + location + ", ipAddress=" + ipAddress + ", zipCode=" + zipCode + ", country=" + country + ", state=" + state + ", city=" + city + ", isp=" + isp + ", url=" + url + ", pageName=" + pageName + ", timeZone=" + timeZone + ", cookieAllowed=" + cookieAllowed + ", javaAllowed=" + javaAllowed + ", refererUrl=" + refererUrl + ", refererType=" + refererType + ", visitCount=" + visitCount + ", visiterLocalTime=" + visiterLocalTime + ", browserEngine=" + browserEngine + ", firstVisitTime=" + firstVisitTime + ", locationLatitude=" + locationLatitude + ", locationLongitude=" + locationLongitude + ", pdfAllowed=" + pdfAllowed + ", flashAllowed=" + flashAllowed + ", directorAllowed=" + directorAllowed + ", domainName=" + domainName + ", userAgent=" + userAgent + ", charSet=" + charSet + '}';
    }
}
