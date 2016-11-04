/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.utils;

/*import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
*/
import com.visumbu.wa.admin.controller.VisitController;
import com.visumbu.wa.bean.AgentDetails;
import com.visumbu.wa.bean.IpLocation;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author user
 */
public class WaUtils {

    public static String getPageName(String url) {

        String baseName = FilenameUtils.getBaseName(url);
        String extension = FilenameUtils.getExtension(url);

        System.out.println("Basename : " + baseName);
        System.out.println("extension : " + extension);
        if (extension != null && !extension.isEmpty()) {
            return baseName + "." + extension;
        }
        return baseName;
    }

    public static Properties getSupportedPlugins(HttpServletRequest request) {
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

    public static String getDeviceType(String userAgent) {
        String ua = userAgent.toLowerCase();
        System.out.println(ua);
        String deviceType = "Unknown";
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
        } else if (ua.contains("mini")) {
            deviceType = "Mini Mobile";
        } else if (ua.contains("windows") && ua.contains("mobile")) {
            deviceType = "Windows Phone";
        } else if (ua.contains("palm")) {
            deviceType = "Palm";
        } else {
            deviceType = "Desktop/Laptop";
        }
        return deviceType;
    }

    public static Long toLong(String longVal) {
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

    public static Integer toInteger(String integer) {
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
/*
    public static Location getLocation(String ipAddress) {

        // http://stackoverflow.com/questions/1415851/best-way-to-get-geo-location-in-java
        try {
            ClassLoader classLoader = WaUtils.class.getClassLoader();
            File file = new File(classLoader.getResource("geolitecity/geolitecity.dat").getFile());
            LookupService cl = new LookupService(file,
                    LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);

            Location location = cl.getLocation(ipAddress);
            return location;
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(VisitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } */

    public static IpLocation parseLocationJsonResponse(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //String jsonInString = "{'name' : 'mkyong'}";
            IpLocation location = mapper.readValue(jsonString, IpLocation.class);
            return location;
        } catch (IOException ex) {
            Logger.getLogger(WaUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
/*
    public static Location parseLocationXmlResponse(String xmlString) {
        Location location = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Location.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            location = (Location) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {

        }
        return location;
    }
*/
    public static UserAgent getUserAgent(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();

        String browserName = browser.getName();
        //or 
        // String browserName = browser.getGroup().getName();
        Version browserVersion = userAgent.getBrowserVersion();
        System.out.println("The user is using browser " + browserName + " - version " + browserVersion);
        return userAgent;
    }

    public static AgentDetails getAgentDetails(HttpServletRequest request) {
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
            //browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            browser = "Safari";
        } else if (user.contains("opr") || user.contains("opera")) {
            /*
            if (user.contains("opera")) {
                browser = (userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
            } */
            browser = "Opera";
        } else if (user.contains("chrome")) {
            //browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
            browser = "Chrome";
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)) {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape";

        } else if (user.contains("firefox")) {
            //browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
            browser = "Firefox";
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
        if(url == null) {
            return null;
        }
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
}
