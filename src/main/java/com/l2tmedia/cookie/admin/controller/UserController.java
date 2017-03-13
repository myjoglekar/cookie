/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.controller;

import com.l2tmedia.cookie.admin.service.UserService;
import com.l2tmedia.cookie.bean.LoginUserBean;
import com.l2tmedia.cookie.model.WaUser;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
 * @author jp
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    WaUser create(HttpServletRequest request, HttpServletResponse response, @RequestBody WaUser teUser) {
        return userService.create(teUser);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody
    WaUser update(HttpServletRequest request, HttpServletResponse response, @RequestBody WaUser teUser) {
        return userService.update(teUser);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List read(HttpServletRequest request, HttpServletResponse response) {
        return userService.read();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    WaUser read(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
        return userService.read(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    WaUser delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    LoginUserBean login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginUserBean loginUserBean) {
        LoginUserBean userBean = userService.authenicate(loginUserBean);
        HttpSession session = request.getSession();
        session.setAttribute("isAuthenticated", userBean.getAuthenticated());
        session.setAttribute("username", userBean.getUsername());
        return userBean;
    }
    
    
    @RequestMapping(value = "logout", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("../../index.html");
    }
    
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        e.printStackTrace();
    }
}
