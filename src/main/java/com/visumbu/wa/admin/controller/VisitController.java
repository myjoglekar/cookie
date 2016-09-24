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
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        visitBean.setSessionId(request.getSession().getId());
        visitBean.setIpAddress(ipAddress);
        visitBean.setUrl(request.getParameter("url"));
        visitBean.setVisiterLocalTime(request.getParameter("localTime"));
        visitBean.setVisitTime(new Date());
        visitBean.setFingerprint(request.getParameter("fingerprint"));
        visitBean.setJavaAllowed(toInteger(request.getParameter("java")));
        visitBean.setFlashAllowed(toInteger(request.getParameter("flash")));
        visitBean.setPdfAllowed(toInteger(request.getParameter("pdf")));
        visitBean.setCookieAllowed(toInteger(request.getParameter("cookie")));
        visitBean.setVisitCount(toInteger(request.getParameter("_idvc")));
        visitBean.setFirstVisitTs(request.getParameter("_idts"));
        visitBean.setLastVisitTs(request.getParameter("_viewts"));
        visitBean.setVisitId(request.getParameter("_id"));
        visitBean.setPageName(getPageName(visitBean.getUrl()));
        visitBean.setSiteId(request.getParameter("idSite"));
        Location location = getLocation(ipAddress);
        if (location != null) {
            visitBean.setCity(getLocation(ipAddress).city);
            visitBean.setCountry(getLocation(ipAddress).countryName);
            visitBean.setZipCode(getLocation(ipAddress).postalCode);
        }
        visitBean.setDomainName(getDomainName(request.getParameter("url")));
        visitBean.setResolution(request.getParameter("res"));
        visitBean.setBrowser(getUserAgent(request).getBrowser().getName());
        visitBean.setBrowserVersion(getUserAgent(request).getBrowserVersion().getVersion());
        visitBean.setOs(getUserAgent(request).getOperatingSystem().getName());
        visitBean.setUserAgent(request.getParameter("ua"));
        visitBean.setDeviceType(getDeviceType(request.getParameter("ua")));
        visitBean.setCharSet(request.getParameter("ca"));
        visitBean.setRefererUrl(request.getParameter("urlref"));
        System.out.println(visitBean);
        System.out.println(request.getParameterNames());
        ArrayList<String> parameterNames = new ArrayList<String>();
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            parameterNames.add(parameterName);
        }
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            System.out.println(headerName);
            System.out.println(request.getHeader(headerName));
        }
        visitService.saveLog(visitBean);
        return dealerService.read();
    }

    private String getPageName(String url) {

        String baseName = FilenameUtils.getBaseName(url);
        String extension = FilenameUtils.getExtension(url);

        System.out.println("Basename : " + baseName);
        System.out.println("extension : " + extension);
        if (extension != null && !extension.isEmpty()) {
            return baseName + "." + extension;
        }
        return baseName;
    }

    private Properties getSupportedPlugins(HttpServletRequest request) {
        Properties properties = new Properties();
        /* 
         pdf: 'application/pdf',
         // media players
         qt: 'video/quicktime',
         realp: 'audio/x-pn-realaudio-plugin',
         wma: 'application/x-mplayer2',
         // interactive multimedia
         dir: 'application/x-director',
         fla: 'application/x-shockwave-flash',
         // RIA
         java: 'application/x-java-vm',
         gears: 'application/x-googlegears',
         ag: 'application/x-silverlight'
         */
        String[] plugins = {"pdf", "qt", "realp", "wma", "dir", "fla", "java", "gears", "ag"};
        for (int i = 0; i < plugins.length; i++) {
            String plugin = plugins[i];
            if (request.getParameter(plugin) != null) {
                properties.put(plugin, request.getParameter(plugin));
            }
        }
        return properties;
    }

    private String getDeviceType(String userAgent) {
        String ua = userAgent.toLowerCase();
        String deviceType = "Unknown";
        if (ua.matches("(?i)iphone|ipad|ipod|android|blackberry|mini|windows\\sce|palm")) {
            if (ua.contains("mobile") && ua.contains("android")) {
                deviceType = "Android Mobile";
            } else if (ua.contains("android")) {
                deviceType = "Android Tablet";
            } else if (ua.contains("ipod")) {
                deviceType = "IPOD";
            } else if (ua.contains("ipad")) {
                deviceType = "ipad";
            } else if (ua.contains("blackberry")) {
                deviceType = "BlackBerry";
            } else if (ua.contains("iphone")) {
                deviceType = "iphone";
            }
        } else {
            deviceType = "Not a Mobile Device";
        }
        return deviceType;
    }

    private Long toLong(String longVal) {
        if (longVal == null) {
            return 0L;
        }
        Long returnValue = 0L;
        try {
            returnValue = Long.parseLong(longVal);
        } catch (Exception e) {
            returnValue = 0L;
        }
        return returnValue;
    }

    private Integer toInteger(String integer) {
        if (integer == null) {
            return 0;
        }
        Integer returnValue = 0;
        try {
            returnValue = Integer.parseInt(integer);
        } catch (Exception e) {
            returnValue = 0;
        }
        return returnValue;
    }

    private Location getLocation(String ipAddress) {

        // http://stackoverflow.com/questions/1415851/best-way-to-get-geo-location-in-java
        try {
            LookupService cl = new LookupService("C:\\Users\\user\\Downloads\\GeoLiteCity\\GeoLiteCity.dat",
                    LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);

            Location location = cl.getLocation(ipAddress);
            return location;
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(VisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private UserAgent getUserAgent(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();

        String browserName = browser.getName();
        //or 
        // String browserName = browser.getGroup().getName();
        Version browserVersion = userAgent.getBrowserVersion();
        System.out.println("The user is using browser " + browserName + " - version " + browserVersion);
        return userAgent;
    }

    private AgentDetails getAgentDetails(HttpServletRequest request) {
        String browserDetails = request.getHeader("User-Agent");
        String userAgent = browserDetails;
        String user = userAgent.toLowerCase();

        String os = "";
        String browser = "";

        //=================OS=======================
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else {
            os = "UnKnown, More-Info: " + userAgent;
        }
        //===============Browser===========================
        if (user.contains("msie")) {
            String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera")) {
                browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
            }
        } else if (user.contains("chrome")) {
            browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox")) {
            browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            browser = "IE";
        } else {
            browser = "UnKnown, More-Info: " + userAgent;
        }
        AgentDetails ad = new AgentDetails();
        ad.setBrowser(browser);
        ad.setOs(os);
        return ad;
    }

    public static String getDomainName(String url) {
        // Alternative Solution
        // http://stackoverflow.com/questions/2939218/getting-the-external-ip-address-in-java
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException ex) {
            Logger.getLogger(VisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
