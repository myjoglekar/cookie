/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class AuthFilter implements Filter {

    private List<String> urlList;

    final static Logger logger = Logger.getLogger(AuthFilter.class);

    FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.debug("Calling a function of init");
        String urls = config.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<String>();
        this.filterConfig = config;
        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());

        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("Calling a function of dofilter");
        try {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession session = httpRequest.getSession();
            String url = httpRequest.getServletPath();
            String contextPath = httpRequest.getContextPath();
            boolean allowRequest = false;
            String fullUrl = httpRequest.getRequestURI();
            if (session != null) {
                if (session.getAttribute("isAuthenticated") != null
                        && (boolean) session.getAttribute("isAuthenticated")
                        && session.getAttribute("username") != null) {
                    allowRequest = true;
                    chain.doFilter(request, response);
                    return;
                }
            }
            if (allowRequest == false) {
                if (fullUrl.endsWith("login") || fullUrl.endsWith("logout")) {
                    allowRequest = true;
                } else if (fullUrl.endsWith(".js") || fullUrl.endsWith(".css")
                        || fullUrl.endsWith("png") || fullUrl.endsWith("jpg")
                        || fullUrl.endsWith(".woff2") || fullUrl.endsWith(".woff")
                        || fullUrl.endsWith("ttf")) {
                    allowRequest = true;
                } else if (url.endsWith("/index.html")) {
                    allowRequest = true;
                } else if (fullUrl.endsWith("admin/wa")) {
                    allowRequest = true;
                } else if (fullUrl.endsWith("api/cookieData")) {
                    allowRequest = true;
                } else if (fullUrl.endsWith("api/v1/cookie")) {
                    allowRequest = true;
                } else if (fullUrl.endsWith("dealer")) {
                    allowRequest = true;
                }
                if (url.endsWith("/static/index.html")) {
                    allowRequest = false;
                }
            }
            if (allowRequest == false) {
                if (url.contains("admin") || url.contains("static/index.html")) {
                    httpResponse.sendRedirect(contextPath + "/index.html");
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception ex) {
            logger.error("Error in filter"+ex);
            
            ex.printStackTrace();
            request.setAttribute("errorMessage", ex);
            request.getRequestDispatcher("/WEB-INF/views/jsp/error.jsp")
                    .forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
