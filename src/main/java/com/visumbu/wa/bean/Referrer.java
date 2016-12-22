/*
 * To change this license header","choose License Headers in Project Properties.
 * To change this template file","choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.bean;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author user
 */
public class Referrer {

    public static final String PAID_SEARCH = "Paid";
    public static final String ORGANIC = "Organic";
    public static final String REFERRER = "Referral";
    public static final String SOCIAL = "Social";
    public static final String DIRECT = "Direct";

    public static final String[] SOCIAL_ARRAY = {"plus.google.com", "facebook", "twitter", "linkedin", "pininterest", "instagram", "utm_source=facebook", "utm_source=linkedin"};

    private static final String[] PAID_ARRAY = {"utm_source", "utm_medium", "utm_term", "utm_content", "utm_id", "utm_campaign", "gclid", "aclk", "utm_medium=cpc", "utm_medium=ppc", "utm_medium=paid search", "utm_medium=email", "utm_medium=banner","utm_medium=paid"};
    private static final String[] PAID_ARRAY_IGNORE = {"utm_medium=organic"};

    private static final String[] ORGANIC_ARRAY = {
        "daum.net",
        "eniro.se",
        "google",
        "naver.com",
        "yahoo.com",
        "msn.com",
        "bing.com",
        "aol.com",
        "aol.com",
        "lycos.com",
        "ask.com",
        "altavista.com",
        "search.netscape.com",
        "cnn.com",
        "about.com",
        "mamma.com",
        "alltheweb.com",
        "voila.fr",
        "search.virgilio.it",
        "bing.com",
        "baidu.com",
        "alice.com",
        "yandex.com",
        "najdi.org.mk",
        "aol.com",
        "mamma.com",
        "seznam.cz",
        "search.com",
        "wp.pl",
        "online.onetcenter.org",
        "szukacz.pl",
        "yam.com",
        "pchome.com",
        "kvasir.no",
        "sesam.no",
        "ozu.es",
        "terra.com",
        "mynet.com",
        "ekolay.net",
        "rambler.ru"};

    public static final List<String> ORGANIC_SITES_LIST = Arrays.asList(ORGANIC_ARRAY);
    public static final List<String> PAID_SITES_LIST = Arrays.asList(PAID_ARRAY);
    public static final List<String> PAID_SITES_IGNORE_LIST = Arrays.asList(PAID_ARRAY_IGNORE);
    public static final List<String> SOCIAL_SITES_LIST = Arrays.asList(SOCIAL_ARRAY);
}
