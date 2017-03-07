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
import com.visumbu.wa.bean.Referrer;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.json.JsonValue;
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
            // Logger.getLogger(WaUtils.class.getName()).log(Level.SEVERE, null, ex);
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

    public static String getReferrerType(String referrerUrl, String domainName) {
        String referrerDomain = getDomainName(referrerUrl);

        if (referrerUrl == null || referrerUrl.isEmpty()) {
            return Referrer.DIRECT;
        }
        if (referrerUrl.equalsIgnoreCase(domainName)) {
            return Referrer.DIRECT;
        }
        if (matchesList(referrerUrl, Referrer.PAID_SITES_LIST)) {
            if (!matchesList(referrerUrl, Referrer.PAID_SITES_IGNORE_LIST)) {
                return Referrer.PAID_SEARCH;
            }
        }
        if (matchesList(referrerUrl, Referrer.SOCIAL_SITES_LIST)) {
            return Referrer.SOCIAL;
        }
        if (matchesList(referrerDomain, Referrer.ORGANIC_SITES_LIST)) {
            return Referrer.ORGANIC;
        }
        return Referrer.REFERRER;
    }

    public static Boolean matchesList(String text, List<String> listData) {
        for (String string : listData) {
            if (text.toLowerCase().indexOf(string.toLowerCase()) > -1) {
                return true;
            }
        }
        return false;
    }

    public static String getDomainName(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }
        url = url.replaceAll("\\?", "/");
        url = url.replaceAll("\\#", "/");
        url = url + "/";
        int slashslash = url.indexOf("//") + 2;
        String domain = url.substring(slashslash, url.indexOf('/', slashslash));
        return domain.startsWith("www.") ? domain.substring(4) : domain;

    }

    public static void main(String[] args) {
        // System.out.println(" Is Valid Email " + isEmailValid("test@test.co"));
        String json = "{\"email\":\"mack3381@gmail.com\"}";
        javax.json.JsonReader jr
                = javax.json.Json.createReader(new StringReader(json));
        javax.json.JsonObject formObject = jr.readObject();
        for (Map.Entry<String, JsonValue> entrySet : formObject.entrySet()) {
            JsonValue value = entrySet.getValue();
            String dataValue = value.toString().replaceAll("\"", "");
            System.out.println(dataValue);
            if (WaUtils.isEmailValid(dataValue) || WaUtils.validatePhoneNumber(dataValue)) {
                System.out.println("SUCCESS");
            }
        }
                System.out.println("FAILED");
    }

   

    public static boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.replace("+", "").replace(" ", "").replace("-", "").matches("\\d{10,14}")) {
            return true;
        }
        if (phoneNo.matches("^\\+(?:[0-9] ?){6,14}[0-9]$")) {
            return true;
        }
        if (phoneNo.matches("\\d{10}")) {
            return true;
        } //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        } //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return true;
        } //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return true;
        } //return false if nothing matches the input
        else {
            return false;
        }

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        /* 
         Email format: A valid email address will have following format: 
         [\\w\\.-]+: Begins with word characters, (may include periods and hypens). 
         @: It must have a '@' symbol after initial characters. 
         ([\\w\\-]+\\.)+: '@' must follow by more alphanumeric characters (may include hypens.). 
         This part must also have a "." to separate domain and subdomain names. 
         [A-Z]{2,4}$ : Must end with two to four alaphabets. 
         (This will allow domain names with 2, 3 and 4 characters e.g pa, com, net, wxyz) 
 
         Examples: Following email addresses will pass validation 
         abc@xyz.net; ab.c@tx.gov 
         */
//Initialize reg ex for email.  
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
//Make the comparison case-insensitive.  
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
