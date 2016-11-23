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
        "www.daum.net",
        "www.eniro.se",
        "www.google",
        "www.naver.com",
        "www.yahoo.com",
        "www.msn.com",
        "www.bing.com",
        "www.aol.com",
        "www.aol.com",
        "www.lycos.com",
        "www.ask.com",
        "www.altavista.com",
        "search.netscape.com",
        "www.cnn.com",
        "www.about.com",
        "www.mamma.com",
        "www.alltheweb.com",
        "www.voila.fr",
        "search.virgilio.it",
        "www.bing.com",
        "www.baidu.com",
        "www.alice.com",
        "www.yandex.com",
        "www.najdi.org.mk",
        "www.aol.com",
        "www.mamma.com",
        "www.seznam.cz",
        "www.search.com",
        "www.wp.pl",
        "online.onetcenter.org",
        "www.szukacz.pl",
        "www.yam.com",
        "www.pchome.com",
        "www.kvasir.no",
        "sesam.no",
        "www.ozu.es",
        "www.terra.com",
        "www.mynet.com",
        "www.ekolay.net",
        "www.rambler.ru"};

    public static final List<String> ORGANIC_SITES_LIST = Arrays.asList(ORGANIC_ARRAY);
    public static final List<String> PAID_SITES_LIST = Arrays.asList(PAID_ARRAY);
    public static final List<String> PAID_SITES_IGNORE_LIST = Arrays.asList(PAID_ARRAY_IGNORE);
    public static final List<String> SOCIAL_SITES_LIST = Arrays.asList(SOCIAL_ARRAY);
}
