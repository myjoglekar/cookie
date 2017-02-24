/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.controller;

import com.visumbu.wa.admin.service.DealerService;
import com.visumbu.wa.admin.service.VisitService;
import com.visumbu.wa.bean.IpLocation;
import com.visumbu.wa.bean.VisitInputBean;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.VisitLog;
import com.visumbu.wa.utils.Rest;
import com.visumbu.wa.utils.WaUtils;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author netphenix
 */
@Controller
@RequestMapping("wa")
public class VisitController {

    @Autowired
    private DealerService dealerService;
    @Autowired
    private VisitService visitService;

    @RequestMapping(value = "test", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List testwa(HttpServletRequest request, HttpServletResponse response) {
        // System.out.println(request.getSession().getId());
        return new ArrayList();
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String read(HttpServletRequest request, HttpServletResponse response) {
        new Thread(() -> {
            visitService.writeVisit(request, response);
        }).start();
        return "Success";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
