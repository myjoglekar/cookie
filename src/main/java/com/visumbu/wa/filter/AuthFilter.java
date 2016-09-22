/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class AuthFilter implements Filter {

    private List<String> urlList;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String urls = config.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<String>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());

        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession session = httpRequest.getSession();
            String url = httpRequest.getServletPath();
            boolean allowedRequest = false;
            System.out.println(httpRequest.getRequestURI());
            if (session != null) {
                System.out.println(session.getAttribute("isAuthenticated"));
                System.out.println(session.getAttribute("username"));
            } else {
                System.out.println("Session is empty");
            }

            if (urlList.contains(url)) {
                allowedRequest = true;
            } else if (session != null && session.getAttribute("isAuthenticated") != null && (Boolean) session.getAttribute("isAuthenticated") == Boolean.TRUE) {
                allowedRequest = true;
            } else if (url.equalsIgnoreCase("/index.html")) {
                allowedRequest = true;
            } else if (url.startsWith("/static/") && !httpRequest.getRequestURI().contains("static/view")) {
                allowedRequest = true;
            } else if (httpRequest.getRequestURI().endsWith("/admin/login")) {
                if (!allowedRequest) {
                    if (null == session) {
                        httpResponse.sendRedirect("/index.html");
                    }
                }
            }

            chain.doFilter(request, response);
        } catch (Exception ex) {
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
