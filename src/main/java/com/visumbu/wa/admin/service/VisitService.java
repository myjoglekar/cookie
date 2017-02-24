/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.service;

import com.visumbu.wa.admin.dao.DealerDao;
import com.visumbu.wa.admin.dao.VisitDao;
import com.visumbu.wa.bean.IpLocation;
import com.visumbu.wa.bean.VisitInputBean;
import com.visumbu.wa.model.ActionLog;
import com.visumbu.wa.model.Conversion;
import com.visumbu.wa.model.VisitLog;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.DealerSite;
import com.visumbu.wa.model.UniqueVisit;
import com.visumbu.wa.model.UniqueVisitFingerprint;
import com.visumbu.wa.model.UniqueVisitSessionId;
import com.visumbu.wa.model.UniqueVisitVisitId;
import com.visumbu.wa.model.VisitPluginProperties;
import com.visumbu.wa.utils.WaUtils;
import com.visumbu.wa.utils.DateUtils;
import com.visumbu.wa.utils.Rest;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Service("visitService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VisitService {

    @Autowired
    private VisitDao visitDao;
    @Autowired
    private DealerDao dealerDao;

    public Dealer read(Integer id) {
        return (Dealer) visitDao.read(Dealer.class, id);
    }

    public List<Dealer> read() {
        List<Dealer> dealer = visitDao.read(Dealer.class);
        return dealer;
    }

    public void newVisit() {

    }

    public Conversion saveConversion(VisitInputBean visitBean, Dealer dealer) {
        System.out.println("New Conversion for dealer " + dealer.getDealerName());
        Conversion conversion = new Conversion();
        BeanUtils.copyProperties(visitBean, conversion);
        Boolean isValid = isValidConversion(conversion);
        System.out.println("Is it a valid conversion -> " + isValid);
        if (isValid) {
            conversion.setDealerId(dealer);
            Date sessionVisitTime = getSessionVisitTime(visitBean);
            if (sessionVisitTime == null) {
                sessionVisitTime = new Date();
            }
            System.out.println("Session visit time " + sessionVisitTime);
            Date firstVisitTime = getFirstVisitTime(visitBean);
            if (firstVisitTime == null) {
                firstVisitTime = sessionVisitTime;
            }

            System.out.println("First visit time " + sessionVisitTime);
            Long durationToConvert = DateUtils.timeDiff(new Date(), firstVisitTime);

            System.out.println("Total duration to convert " + durationToConvert);

            Long duration = DateUtils.timeDiff(new Date(), sessionVisitTime);
            System.out.println("Current Session Duration " + duration);
            conversion.setDuration(duration);
            conversion.setDurationToConvert(durationToConvert);
            conversion.setFirstVisitTime(getFirstVisitTime(visitBean));
            conversion.setSessionVisitTime(sessionVisitTime);
            System.out.println("Saving new conversion " + conversion);

            visitDao.create(conversion);
            return conversion;
        }
        return null;
    }

    public ActionLog saveAction(VisitInputBean visitBean, Dealer dealer) {
        ActionLog actionLog = new ActionLog();
        BeanUtils.copyProperties(visitBean, actionLog);
        // Dealer dealer = updateDealerDetails(visitBean);
        actionLog.setDealerId(dealer);
        visitDao.create(actionLog);
        return actionLog;
    }

    public VisitLog saveLog(VisitInputBean visitBean, Dealer dealer) {
        VisitLog visitLog = new VisitLog();
        BeanUtils.copyProperties(visitBean, visitLog);
        //Dealer dealer = updateDealerDetails(visitBean);
        visitLog.setDealerId(dealer);
        visitDao.create(visitLog);
        // UniqueVisit uniqueVisit = updateUniqueVisitDetails(visitLog);
        return visitLog;
    }

    public Dealer updateDealerDetails(VisitInputBean visitBean) {
        Dealer dealer = dealerDao.findBySiteId(visitBean.getSiteId());
        DealerSite dealerSite = dealerDao.findDealerSite(dealer.getId(), visitBean.getDomainName());
        if (dealerSite == null) {
            dealerSite = new DealerSite();
            dealerSite.setDealerId(dealer);
            dealerSite.setSiteName(visitBean.getDomainName());
            dealerDao.create(dealerSite);
        }
        dealer.setLastSiteVisit(new Date());
        dealerDao.update(dealer);
        return dealer;
    }

    public void saveVisitProperties(Properties supportedPlugins, VisitLog visitLog) {
        Enumeration e = supportedPlugins.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = supportedPlugins.getProperty(key);
            VisitPluginProperties properties = new VisitPluginProperties();
            properties.setVisitLogId(visitLog);
            properties.setPropertyName(key);
            properties.setPropertyValue(value);
            dealerDao.create(properties);
        }
    }

    public UniqueVisit updateUniqueVisitDetails(VisitLog visitLog) {
        String fingerPrint = visitLog.getFingerprint();
        String visitId = visitLog.getVisitId();
        String sessionId = visitLog.getSessionId();
        UniqueVisit uniqueVisit = null;
        UniqueVisit uniqueVisitFp = visitDao.getUniqueIdByFingerPrint(fingerPrint);
        UniqueVisit uniqueVisitVi = visitDao.getUniqueIdByVisitId(visitId);
        UniqueVisit uniqueVisitSi = visitDao.getUniqueIdBySessionId(sessionId);
        uniqueVisit = uniqueVisitFp != null ? uniqueVisitFp : (uniqueVisitVi != null ? uniqueVisitVi : (uniqueVisitSi != null ? uniqueVisitSi : null));
        if (uniqueVisit == null) {
            uniqueVisit = new UniqueVisit();
            uniqueVisit.setTotalVisits(1);
            uniqueVisit = (UniqueVisit) visitDao.create(uniqueVisit);
        } else {
            uniqueVisit.setTotalVisits(uniqueVisit.getTotalVisits() + 1);
            uniqueVisit = (UniqueVisit) visitDao.update(uniqueVisit);
        }
        if (uniqueVisitFp == null) {
            UniqueVisitFingerprint uniqueVisitFingerprint = new UniqueVisitFingerprint();
            uniqueVisitFingerprint.setFingerprint(fingerPrint);
            uniqueVisitFingerprint.setUniqueVisitId(uniqueVisit);
            visitDao.create(uniqueVisitFingerprint);
        }
        if (uniqueVisitSi == null) {
            UniqueVisitSessionId uniqueVisitSessionId = new UniqueVisitSessionId();
            uniqueVisitSessionId.setSessionId(sessionId);
            uniqueVisitSessionId.setUniqueVisitId(uniqueVisit);
            visitDao.create(uniqueVisitSessionId);
        }
        if (uniqueVisitVi == null) {
            UniqueVisitVisitId uniqueVisitVisitId = new UniqueVisitVisitId();
            uniqueVisitVisitId.setVisitId(visitId);
            uniqueVisitVisitId.setUniqueVisitId(uniqueVisit);
            visitDao.create(uniqueVisitVisitId);
        }
        return uniqueVisit;
    }

    public String getReferrerUrl(String visitId, Integer visitCount) {
        return visitDao.getReferrerUrl(visitId, visitCount);
    }
    
    public void writeVisit(HttpServletRequest request, HttpServletResponse response) {
        VisitInputBean visitBean = new VisitInputBean();
        visitBean.setFingerprint(request.getParameter("fingerprint"));
        visitBean.setVisitTime(new Date());
        visitBean.setActionTime(new Date());
        visitBean.setUrl(request.getParameter("url"));
        visitBean.setVisiterLocalTime(request.getParameter("localTime"));
        visitBean.setUserAgent(request.getParameter("ua"));
        visitBean.setActionName(request.getParameter("viewAction"));
        visitBean.setLocalHour(WaUtils.toInteger(request.getParameter("h")));
        visitBean.setLocalMin(WaUtils.toInteger(request.getParameter("m")));
        visitBean.setLocalSec(WaUtils.toInteger(request.getParameter("s")));
        visitBean.setLocalTime(request.getParameter("localTime"));
        String visitId = request.getParameter("_id");
        visitBean.setVisitId(visitId);
        visitBean.setSiteId(request.getParameter("idsite"));
        visitBean.setTimeZone(request.getParameter("tzName"));
        visitBean.setTimeZoneOffset(request.getParameter("tz"));
        visitBean.setSessionId(request.getSession().getId());
        visitBean.setReferrerUrl(request.getParameter("urlref"));
        visitBean.setVisitCount(WaUtils.toInteger(request.getParameter("_idvc")));
        String referrerUrl = request.getParameter("urlref");
        String referrerDomain = WaUtils.getDomainName(referrerUrl);
        String domainName = WaUtils.getDomainName(request.getParameter("url"));
        visitBean.setDomainName(domainName);
        if (domainName.equalsIgnoreCase(referrerDomain)) {
            referrerUrl = getReferrerUrl(visitId, visitBean.getVisitCount());
        }
        visitBean.setFirstReferrerUrl(referrerUrl);
        visitBean.setReferrerDomain(WaUtils.getDomainName(referrerUrl));
        visitBean.setReferrerType(WaUtils.getReferrerType(referrerUrl, domainName));
        visitBean.setResolution(request.getParameter("res"));
        visitBean.setBrowser(WaUtils.getUserAgent(request).getBrowser().getName());
        visitBean.setBrowserVersion(WaUtils.getUserAgent(request).getBrowserVersion().getVersion());
        visitBean.setOs(WaUtils.getUserAgent(request).getOperatingSystem().getName());
        visitBean.setUserAgent(request.getParameter("ua"));
        visitBean.setDeviceType(WaUtils.getDeviceType(request.getParameter("ua")));
        visitBean.setCharSet(request.getParameter("ca"));

        Dealer dealer = updateDealerDetails(visitBean);
        if (request.getParameter("viewAction").equalsIgnoreCase("open")) {
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            visitBean.setIpAddress(ipAddress);
            visitBean.setJavaAllowed(WaUtils.toInteger(request.getParameter("java")));
            visitBean.setFlashAllowed(WaUtils.toInteger(request.getParameter("flash")));
            visitBean.setPdfAllowed(WaUtils.toInteger(request.getParameter("pdf")));
            visitBean.setCookieAllowed(WaUtils.toInteger(request.getParameter("cookie")));
            visitBean.setFirstVisitTs(request.getParameter("_idts"));
            visitBean.setLastVisitTs(request.getParameter("_viewts"));
            visitBean.setPageName(WaUtils.getPageName(visitBean.getUrl()));
            String ipDetailsJson = Rest.getData("http://freegeoip.net/json/" + ipAddress); ///
            IpLocation ipLocation = WaUtils.parseLocationJsonResponse(ipDetailsJson);
            if (ipLocation != null) {
                visitBean.setCity(ipLocation.getCity());
                visitBean.setCountry(ipLocation.getCountry_name());
                visitBean.setZipCode(ipLocation.getZip_code());
                visitBean.setLocationLatitude(ipLocation.getLatitude());
                visitBean.setLocationLongitude(ipLocation.getLongitude());
                visitBean.setLocationTimeZone(ipLocation.getTime_zone());
                visitBean.setRegionCode(ipLocation.getRegion_code());
                visitBean.setRegionName(ipLocation.getRegion_name());
                visitBean.setMetroCode(ipLocation.getMetro_code());
            } else {
                /*Location location = WaUtils.getLocation(ipAddress);
                 if (location != null) {
                 visitBean.setCity(WaUtils.getLocation(ipAddress).city);
                 visitBean.setCountry(WaUtils.getLocation(ipAddress).countryName);
                 visitBean.setZipCode(WaUtils.getLocation(ipAddress).postalCode);
                 }*/
            }
            //System.out.println(request.getParameterNames());
            ArrayList<String> parameterNames = new ArrayList<String>();
            Enumeration enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String parameterName = (String) enumeration.nextElement();
                //System.out.println("Parameter Name: " + parameterName + " Parameter Value: " + request.getParameter(parameterName));
                parameterNames.add(parameterName);
            }
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = (String) headerNames.nextElement();
                //System.out.println("Header Name: " + headerName + " Header Value " + request.getHeader(headerName));
            }
            VisitLog visitLog = saveLog(visitBean, dealer);
            saveVisitProperties(WaUtils.getSupportedPlugins(request), visitLog);
        }
        if (request.getParameter("viewAction").equalsIgnoreCase("submit")) {
            visitBean.setFormAction(request.getParameter("formAction"));
            visitBean.setFormData(request.getParameter("formData"));
            visitBean.setFormId(request.getParameter("formId"));
            visitBean.setFormName(request.getParameter("formName"));
            visitBean.setFormMethod(request.getParameter("formMethod"));
            saveConversion(visitBean, dealer);
        }
        saveAction(visitBean, dealer);
    }

    private Boolean isValidConversion(Conversion conversion) {
        String formData = conversion.getFormData();
        javax.json.JsonReader jr
                = javax.json.Json.createReader(new StringReader(formData));
        javax.json.JsonObject formObject = jr.readObject();
        for (Map.Entry<String, JsonValue> entrySet : formObject.entrySet()) {
            JsonValue value = entrySet.getValue();
            String dataValue = value.toString().replaceAll("\"", "");
            System.out.println("Checking valid email or phone for " + dataValue);
            if (WaUtils.isEmailValid(dataValue) || WaUtils.validatePhoneNumber(dataValue)) {
                System.out.println("Validation Successful -> " + dataValue);
                return true;
            }
        }
        System.out.println("Not a valid converstion " + conversion);
        return false;
    }

    private Date getFirstVisitTime(VisitInputBean visitBean) {
        return visitDao.getFirstVisitTime(visitBean.getVisitId());
    }

    private Date getSessionVisitTime(VisitInputBean visitBean) {
        return visitDao.getSessionVisitTime(visitBean.getVisitId(), visitBean.getVisitCount());
    }
}
