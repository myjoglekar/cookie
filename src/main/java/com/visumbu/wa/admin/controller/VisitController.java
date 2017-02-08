/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.controller;

import com.visumbu.wa.admin.service.DealerService;
import com.visumbu.wa.admin.service.VisitService;
import com.visumbu.wa.bean.IpLocation;
import com.visumbu.wa.bean.VisitInputBean;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.VisitLog;
import com.visumbu.wa.utils.Rest;
import com.visumbu.wa.utils.WaUtils;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
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

/**
 *
 * @author netphenix
 */
@Controller
@RequestMapping("wa")
public class VisitController {

    @Autowired
    private DealerService dealerService;
    @Autowired
    private VisitService visitService;

    @RequestMapping(value = "test", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List testwa(HttpServletRequest request, HttpServletResponse response) {
        // System.out.println(request.getSession().getId());
        return new ArrayList();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String read(HttpServletRequest request, HttpServletResponse response) {
        //request.getSession().setMaxInactiveInterval(i);
        // System.out.println("Referrer -> " + request.getHeader("Referer"));
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
            referrerUrl = visitService.getReferrerUrl(visitId, visitBean.getVisitCount());
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
            VisitLog visitLog = visitService.saveLog(visitBean, dealer);
            visitService.saveVisitProperties(WaUtils.getSupportedPlugins(request), visitLog);
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
        e.printStackTrace();
    }
}
