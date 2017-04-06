/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;

/**
 *
 * @author vsamraj
 */
public class Rest {

    final static Logger logger = Logger.getLogger(Rest.class);

    public static String getData(String urlString) {
        String returnStr = "";
        try {
            logger.debug(urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                logger.debug(urlString);
                logger.debug("Code ---->" + conn.getResponseCode() + " Message ----> " + conn.getResponseMessage());
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            logger.debug("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                logger.debug(output);
                returnStr += output;
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            logger.error("Malformed URL error", e);
        } catch (IOException e) {
            logger.error("Error reading data from URL", e);
        }
        return returnStr;
    }
}
