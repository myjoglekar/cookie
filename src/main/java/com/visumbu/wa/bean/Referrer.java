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

    public static final String PAID_SEARCH = "Paid Search";
    public static final String ORGANIC = "Organic";
    public static final String REFERRER = "Referrer";
    public static final String SOCIAL = "Social";
    public static final String DIRECT = "Direct";

    public static final String[] SOCIAL_ARRAY = {"facebook", "twitter", "linkedin", "pininterest", "instagram", "utm_source=facebook", "utm_source=linkedin"};

    private static final String[] PAID_ARRAY = {"utm_source", "utm_medium", "utm_term", "utm_content", "utm_id", "utm_campaign", "gclid", "aclk", "utm_medium=cpc", "utm_medium=ppc", "utm_medium=paid search", "utm_medium=email", "utm_medium=banner"};

    private static final String[] ORGANIC_ARRAY = {"http://www.daum.net/",
        "http://www.eniro.se/",
        "http://www.google",
        "http://www.naver.com/",
        "http://www.yahoo.com/",
        "http://www.msn.com/",
        "http://www.bing.com/",
        "http://www.aol.com/",
        "http://www.aol.com/",
        "http://www.lycos.com/",
        "http://www.ask.com/",
        "http://www.altavista.com/",
        "http://search.netscape.com/",
        "http://www.cnn.com/",
        "http://www.about.com/",
        "http://www.mamma.com/",
        "http://www.alltheweb.com/",
        "http://www.voila.fr/",
        "http://search.virgilio.it/",
        "http://www.bing.com/",
        "http://www.baidu.com/",
        "http://www.alice.com/",
        "http://www.yandex.com/",
        "http://www.najdi.org.mk/",
        "http://www.aol.com/",
        "http://www.mamma.com/",
        "http://www.seznam.cz/",
        "http://www.search.com/",
        "http://www.wp.pl/",
        "http://online.onetcenter.org/",
        "http://www.szukacz.pl/",
        "http://www.yam.com/",
        "http://www.pchome.com/",
        "http://www.kvasir.no/",
        "http://sesam.no/",
        "http://www.ozu.es/",
        "http://www.terra.com/",
        "http://www.mynet.com/",
        "http://www.ekolay.net/",
        "http://www.rambler.ru/"};

    public static final List<String> ORGANIC_SITES_LIST = Arrays.asList(ORGANIC_ARRAY);
    public static final List<String> PAID_SITES_LIST = Arrays.asList(PAID_ARRAY);
    public static final List<String> SOCIAL_SITES_LIST = Arrays.asList(SOCIAL_ARRAY);
}
