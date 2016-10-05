/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.controller;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.visumbu.wa.admin.service.DealerService;
import com.visumbu.wa.admin.service.VisitService;
import com.visumbu.wa.bean.AgentDetails;
import com.visumbu.wa.bean.VisitInputBean;
import com.visumbu.wa.model.VisitLog;
import com.visumbu.wa.utils.WaUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
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

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List read(HttpServletRequest request, HttpServletResponse response) {
        VisitInputBean visitBean = new VisitInputBean();
        visitBean.setFingerprint(request.getParameter("fingerprint"));
        visitBean.setVisitTime(new Date());
        visitBean.setUrl(request.getParameter("url"));
        visitBean.setVisiterLocalTime(request.getParameter("localTime"));
        visitBean.setUserAgent(request.getParameter("ua"));
        visitBean.setViewAction(request.getParameter("viewAction"));
        visitBean.setLocalHour(WaUtils.toInteger(request.getParameter("h")));
        visitBean.setLocalHour(WaUtils.toInteger(request.getParameter("m")));
        visitBean.setLocalHour(WaUtils.toInteger(request.getParameter("s")));
        visitBean.setVisiterLocalTime(request.getParameter("localTime"));
        if (request.getParameter("viewAction").equalsIgnoreCase("open")) {
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            visitBean.setSessionId(request.getSession().getId());
            visitBean.setIpAddress(ipAddress);
            visitBean.setJavaAllowed(WaUtils.toInteger(request.getParameter("java")));
            visitBean.setFlashAllowed(WaUtils.toInteger(request.getParameter("flash")));
            visitBean.setPdfAllowed(WaUtils.toInteger(request.getParameter("pdf")));
            visitBean.setCookieAllowed(WaUtils.toInteger(request.getParameter("cookie")));
            visitBean.setVisitCount(WaUtils.toInteger(request.getParameter("_idvc")));
            visitBean.setFirstVisitTs(request.getParameter("_idts"));
            visitBean.setLastVisitTs(request.getParameter("_viewts"));
            visitBean.setVisitId(request.getParameter("_id"));
            visitBean.setPageName(WaUtils.getPageName(visitBean.getUrl()));
            visitBean.setSiteId(request.getParameter("idSite"));
            Location location = WaUtils.getLocation(ipAddress);
            if (location != null) {
                visitBean.setCity(WaUtils.getLocation(ipAddress).city);
                visitBean.setCountry(WaUtils.getLocation(ipAddress).countryName);
                visitBean.setZipCode(WaUtils.getLocation(ipAddress).postalCode);
            }
            visitBean.setDomainName(WaUtils.getDomainName(request.getParameter("url")));
            visitBean.setResolution(request.getParameter("res"));
            visitBean.setBrowser(WaUtils.getUserAgent(request).getBrowser().getName());
            visitBean.setBrowserVersion(WaUtils.getUserAgent(request).getBrowserVersion().getVersion());
            visitBean.setOs(WaUtils.getUserAgent(request).getOperatingSystem().getName());
            visitBean.setUserAgent(request.getParameter("ua"));
            visitBean.setDeviceType(WaUtils.getDeviceType(request.getParameter("ua")));
            visitBean.setCharSet(request.getParameter("ca"));
            visitBean.setRefererUrl(request.getParameter("urlref"));
            System.out.println(visitBean);
            //System.out.println(request.getParameterNames());
            ArrayList<String> parameterNames = new ArrayList<String>();
            Enumeration enumeration = request.getParameterNames();
            while (enumeration.hasMoreElements()) {
                String parameterName = (String) enumeration.nextElement();
                System.out.println("Parameter Name: " + parameterName + " Parameter Value: " + request.getParameter(parameterName));
                parameterNames.add(parameterName);
            }
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = (String) headerNames.nextElement();
                System.out.println("Header Name: " + headerName + " Header Value " + request.getHeader(headerName));
            }
            VisitLog visitLog = visitService.saveLog(visitBean);
            visitService.saveVisitProperties(WaUtils.getSupportedPlugins(request), visitLog);
        }
        visitService.saveAction(visitBean);
        return dealerService.read();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
