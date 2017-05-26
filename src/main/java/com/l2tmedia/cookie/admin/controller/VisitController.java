/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.controller;

import com.l2tmedia.cookie.Constants;
import com.l2tmedia.cookie.admin.service.VisitService;
import com.l2tmedia.cookie.bean.IpLocation;
import com.l2tmedia.cookie.bean.VisitInputBean;
import com.l2tmedia.cookie.model.Dealer;
import com.l2tmedia.cookie.model.VisitLog;
import com.l2tmedia.cookie.utils.Rest;
import com.l2tmedia.cookie.utils.WaUtils;
import eu.bitwalker.useragentutils.Version;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.log4j.Logger;

/**
 *
 * @author netphenix
 */
@Controller
@RequestMapping("wa")
public class VisitController {

    @Autowired
    private VisitService visitService;

    final static Logger logger = Logger.getLogger(VisitController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String read(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Saving visit data. Referrer -> " + request.getHeader("Referer"));
        
        VisitInputBean visitBean = new VisitInputBean();
        visitBean.setFingerprint(request.getParameter("fingerprint"));
        visitBean.setVisitTime(new Date());
        visitBean.setVisitDate(new Date());
        visitBean.setProcessed(0);
        visitBean.setActionTime(new Date());
        visitBean.setActionDate(new Date());
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
            referrerUrl = visitService.getReferrerUrl(visitId, visitBean.getVisitCount());
        }
        visitBean.setFirstReferrerUrl(referrerUrl);
        visitBean.setReferrerDomain(WaUtils.getDomainName(referrerUrl));
        visitBean.setReferrerType(WaUtils.getReferrerType(referrerUrl, domainName));
        visitBean.setResolution(request.getParameter("res"));
        visitBean.setBrowser(WaUtils.getUserAgent(request).getBrowser().getName());
        Version browserVersion = WaUtils.getUserAgent(request).getBrowserVersion();
        visitBean.setBrowserVersion(browserVersion == null ? "" : browserVersion.getVersion());
        visitBean.setOs(WaUtils.getUserAgent(request).getOperatingSystem().getName());
        visitBean.setUserAgent(request.getParameter("ua"));
        visitBean.setDeviceType(WaUtils.getDeviceType(request.getParameter("ua")));
        visitBean.setCharSet(request.getParameter("ca"));

        Dealer dealer = visitService.updateDealerDetails(visitBean);
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
            try {
                String ipDetailsJson = Rest.getData("http://freegeoip.net/json/" + ipAddress); 
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
                }
            } catch (RuntimeException re) {
                logger.error("Error loading IP Geo data. ", re);
            }
 
            VisitLog visitLog = visitService.saveLog(visitBean, dealer);
        }
        if (request.getParameter("viewAction").equalsIgnoreCase("submit")) {
            visitBean.setFormAction(request.getParameter("formAction"));
            visitBean.setFormData(request.getParameter("formData"));
            visitBean.setFormId(request.getParameter("formId"));
            visitBean.setFormName(request.getParameter("formName"));
            visitBean.setFormMethod(request.getParameter("formMethod"));
            visitService.saveConversion(visitBean, dealer);
        }
        visitService.saveAction(visitBean, dealer);
        return "Success";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.error(Constants.HTTP_ERROR, e);
    }
}
