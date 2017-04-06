/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.controller;

import com.l2tmedia.cookie.bean.ReportPage;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class BaseController {

    final static Logger logger = Logger.getLogger(BaseController.class);

    public ReportPage getPage(HttpServletRequest request) {

        ReportPage reportPage = new ReportPage();
        if (request.getParameter("page") == null && request.getParameter("count") == null) {
            
            return null;
        }
        Integer count = 50;
        if (request.getParameter("count") != null) {
            count = Integer.parseInt(request.getParameter("count"));
        }
        if (request.getParameter("page") != null) {
            Integer start = 0;
            Integer page = Integer.parseInt(request.getParameter("page"));
            start = count * (page - 1);
            reportPage.setStart(start);
            reportPage.setPageNo(page);
            reportPage.setCount(count);
        }
        return reportPage;
    }
}
