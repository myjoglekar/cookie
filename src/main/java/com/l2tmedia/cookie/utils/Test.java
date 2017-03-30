/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.utils;

import com.l2tmedia.cookie.bean.IpLocation;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class Test {

    final static Logger logger = Logger.getLogger(Test.class);

    public static void main(String argv[]) {

        String ipDetailsJson = Rest.getData("http://freegeoip.net/json/49.206.124.82"); ///
        IpLocation ipLocation = WaUtils.parseLocationJsonResponse(ipDetailsJson);
        //System.out.println(getDeviceType("Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601."));
    }

    public static String getDeviceType(String userAgent) {
        logger.debug("Start function of getDeviceType in Test class");
        String ua = userAgent.toLowerCase();
        String deviceType = "Unknown";
        // if (ua.matches("(.*)iphone|ipad|ipod|android|blackberry|mini|windows\\sce|palm(.*)")) {
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
        } else if (ua.contains("windows")) {
            deviceType = "Windows Phone";
        } else if (ua.contains("palm")) {
            deviceType = "Palm";
        } else {
            deviceType = "Not a Mobile Device";
        }
        logger.debug("End  function of getDeviceType  in Test class");
        return deviceType;
    }
}
