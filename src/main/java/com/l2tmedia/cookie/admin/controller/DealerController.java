/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.controller;

import com.l2tmedia.cookie.Constants;
import com.l2tmedia.cookie.admin.service.DealerService;
import com.l2tmedia.cookie.bean.DealerInputBean;
import com.l2tmedia.cookie.bean.ReportPage;
import com.l2tmedia.cookie.controller.BaseController;
import com.l2tmedia.cookie.model.Dealer;
import com.l2tmedia.cookie.utils.DealerValidator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.apache.log4j.Logger;

/**
 *
 * @author netphenix
 */
@Controller
@RequestMapping("dealer")
public class DealerController extends BaseController {

    @Autowired
    private DealerService dealerService;

    final static Logger logger = Logger.getLogger(DealerController.class);

    @RequestMapping(value = "{dealerId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map readById(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer dealerId) {
        logger.debug("Requesting dealer by id: dealerId: " + dealerId);
        
        String status = request.getParameter("status");
        ReportPage page = getPage(request);
        Map returnMap = dealerService.getDealers(dealerId, page, status);
        return returnMap;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map read(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Requesting dealer list.");
        
        String status = request.getParameter("status");
        ReportPage page = getPage(request);
        Map returnMap = dealerService.getDealers(page, status);
        return returnMap;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Object create(HttpServletRequest request, HttpServletResponse response, @RequestBody DealerInputBean dealer) {
        logger.debug("New dealer signup called for dealer: " + dealer);
        
        DealerValidator validator = new DealerValidator(dealer);
        validator.validate();
        if (validator.isError()) {
            List<String> errors = validator.getErrorMessages();
            logger.error("Errors parsing dealer from New Dealer Signup: " + errors);
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        
        try {
            return dealerService.create(dealer);
        } catch (Exception e) {
            logger.error(Constants.ERROR_DUPLICATE_DEALER + dealer, e);
            return new ResponseEntity<String>(Constants.ERROR_DUPLICATE_DEALER + dealer.getDealerRefId(), HttpStatus.BAD_REQUEST);
        }
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
        logger.error(Constants.HTTP_ERROR, e);
    }
}
