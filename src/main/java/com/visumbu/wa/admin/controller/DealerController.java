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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @RequestMapping(value = "{dealerId}", method = RequestMethod.GET, produces = "application/json")
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
    Object create(HttpServletRequest request, HttpServletResponse response, @RequestBody DealerInputBean dealer) {
        if (dealer.getDealerName() == null || dealer.getDealerName().isEmpty()) {
            System.out.println("Mandatory Fields Missing [Dealer Name] in the dealer " + dealer);
            return new ResponseEntity<String>("Missing Required Parameter [Dealer Name]", HttpStatus.BAD_REQUEST);
        }
        if (dealer.getWebsite() == null || dealer.getWebsite().isEmpty()) {
            System.out.println("Mandatory Fields Missing [Dealer Website] in the dealer " + dealer);
            return new ResponseEntity<String>("Missing Required Parameter [Dealer Website]", HttpStatus.BAD_REQUEST);
        }
        if (dealer.getDealerRefId() == null || dealer.getDealerRefId().isEmpty()) {
            System.out.println("Mandatory Fields Missing [Dealer Id] in the dealer " + dealer);
            return new ResponseEntity<String>("Missing Required Parameter [Dealer Id]", HttpStatus.BAD_REQUEST);
        }
        if (request.getHeader("Authorization") != null && !request.getHeader("Authorization").equalsIgnoreCase("98269750-9049-48c4-9acb-c73b70d55a21!25090222017020709045688243610000accde26a52104c74ba5b978da40d252e")) {
            System.out.println("Unauthorized " + dealer);
            return new ResponseEntity<String>("Unauthroized", HttpStatus.UNAUTHORIZED);
        }
        if (dealer == null) {
            System.out.println("Unparsable JSON");
            return new ResponseEntity<String>("Unparsable JSON", HttpStatus.BAD_REQUEST);
        }
        System.out.println("Inserting dealer to database " + dealer);
        try {
            return dealerService.create(dealer);
        } catch (Exception e) {
            System.out.println("Dealer Already Exisits " + dealer);
            return new ResponseEntity<String>("Dealer Id Alredy Exists " + dealer.getDealerRefId(), HttpStatus.BAD_REQUEST);

        }
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
