/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.controller;

import com.visumbu.wa.admin.service.DealerService;
import com.visumbu.wa.bean.DealerInputBean;
import com.visumbu.wa.bean.ReportPage;
import com.visumbu.wa.controller.BaseController;
import com.visumbu.wa.model.Dealer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author netphenix
 */
@Controller
@RequestMapping("dealer")
public class DealerController extends BaseController {

    @Autowired
    private DealerService dealerService;

    @RequestMapping(value="{dealerId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map readById(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerId) {
        String status = request.getParameter("status");
        ReportPage page = getPage(request);
        Map returnMap = dealerService.getDealers(dealerId, page, status);
        return returnMap;
    }
    

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map read(HttpServletRequest request, HttpServletResponse response) {
        String status = request.getParameter("status");
        ReportPage page = getPage(request);
        Map returnMap = dealerService.getDealers(page, status);
        return returnMap;
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Dealer create(HttpServletRequest request, HttpServletResponse response, @RequestBody DealerInputBean dealer) {
        return dealerService.create(dealer);
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody
    Dealer update(HttpServletRequest request, HttpServletResponse response, @RequestBody DealerInputBean dealer) {
        return dealerService.create(dealer);
    }
    
    
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Dealer createParams(HttpServletRequest request, HttpServletResponse response) {
        Dealer dealer = new Dealer();
        dealer.setDealerName(request.getParameter("dealerName"));
        dealer.setCommunicationEmail(request.getParameter("communicationEmail"));
        dealer.setEmail(request.getParameter("email"));
        dealer.setDealerRefId(request.getParameter("dealerRefId"));
        dealer.setWebsite(request.getParameter("website"));
        dealer.setCreatedTime(new Date());
        return dealerService.create(dealer);
    }
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
