/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie;

/**
 *
 * @author varghees
 */
public class Constants {

    public static final int SUCCESS = 0;
    public static final int FAILURE = -1;
    public static final int EMAIL = 100;
    public static final int SMS = 101;
    // Job 
    public static final int JOB_ONE_TIME = 200;
    public static final int JOB_ONE_TIME_SPECIFIC_TIME = 201;
    public static final int JOB_REPEAT_FOR_EVER = 202;
    public static final int JOB_CRON_JOB = 203;

    // Mail
    public static final int UNKNOWN = -1;
    public static final int TEXT_MAIL = 300;
    public static final int HTML_MAIL = 301;
    public static final int TEXT_MAIL_WITH_ATTACHMENT = 302;
    public static final int HTML_WITH_ATTACHMENT = 303;
    public static final int HTML_WITH_EMBEDDED_IMAGE = 304;
    public static final int MAX_ROWS = 5;
    // File path
    
    //Error messages
    public static final String HTTP_ERROR = "Error reading HTTP message.";
    public static final String ERROR_DATE_PARSE = "Error parsing date";
    public static final String ERROR_NUMBER_PARSE = "Error parsing number";
    public static final String ERROR_JSON_PARSE = "Error parsing JSON data";
    public static final String ERROR_DEALER_MANDATORY_FIELDS = "Mandatory Field Missing: (%s) in the dealer";
    public static final String ERROR_DUPLICATE_DEALER = "Dealer Id already exists ";
    //Dates
    public static final String DATE_EXPECTED_FORMAT="MM/dd/yyyy";
    public static final String NULL_DATE_START = "Start Date cannot be null.";
    public static final String NULL_DATE_END = "End Date cannot be null.";
    public static final String INVALID_DATE_START = "Invalid start date: %s Expected format is: " + DATE_EXPECTED_FORMAT;
    public static final String INVALID_DATE_END = "Invalid end date: %s Expected format is: " + DATE_EXPECTED_FORMAT;
    public static final String END_DATE_BEFORE_START = "End Date must be greater than Start Date.  Received startDate=%s, received endDate=%s";
    
}
