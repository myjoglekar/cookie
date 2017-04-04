/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.service;

import com.l2tmedia.cookie.admin.dao.UserDao;
import com.l2tmedia.cookie.bean.LoginUserBean;
import com.l2tmedia.cookie.model.WaUser;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author jp
 */
@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserService {

    @Autowired
    private UserDao userDao;

    final static Logger logger = Logger.getLogger(UserService.class);

    public WaUser create(WaUser teUser) {
        List<WaUser> users = userDao.findByUserName(teUser.getUserName());
        if (users.isEmpty()) {
            teUser.setStatus("Active");
            teUser.setCreatedTime(new Date());
            teUser.setTheme("default");
            return (WaUser) userDao.create(teUser);
        }
        logger.debug("calling a function of create  in UserService class where status is set to Active and created time is"+new Date()+" and theme is set to default");
        return null;
    }

    public WaUser read(Integer id) {
        logger.debug("calling function to get user details by read method in UserService class where user id="+id);
        return (WaUser) userDao.read(WaUser.class, id);
    }

    public List<WaUser> read() {
        logger.debug("Calling a function of List all users in UserService class");
        List<WaUser> users = userDao.read();
        return users;
    }

    public WaUser update(WaUser teUser) {
        logger.debug("Calling a function to update user details in UserService class");
        return (WaUser) userDao.update(teUser);
    }

    public WaUser delete(Integer id) {
        logger.debug("Calling function of delete a user in UserService class for the user id="+id+" and update the status as deleted");
        WaUser teUser = read(id);
        teUser.setStatus("Deleted");
        return update(teUser);
    }

    public WaUser delete(WaUser teUser) {
        logger.debug("Calling function of delete in UserService class");
        return (WaUser) userDao.delete(teUser);
    }

    public LoginUserBean authenicate(LoginUserBean userBean) {
        List<WaUser> users = userDao.findByUserName(userBean.getUsername());
        LoginUserBean loginUserBean = null;
        if (!users.isEmpty()) {
            WaUser user = users.get(0);
            if (user.getPassword().equals(userBean.getPassword())) {
                user.setFailedLoginCount(0);
                user.setLastLoginTime(new Date());
                loginUserBean = toLoginUserBean(user);
                loginUserBean.setAuthenticated(Boolean.TRUE);
            } else {
                user.setFailedLoginCount(user.getFailedLoginCount() + 1);
                loginUserBean = toLoginUserBean(user);
                loginUserBean.setAuthenticated(Boolean.FALSE);
                loginUserBean.setErrorMessage("Invalid Username or Password");
            }
        }
        if (loginUserBean == null) {
            loginUserBean = new LoginUserBean();
            loginUserBean.setErrorMessage("Invalid Login");
        }
        logger.debug("Calling a user authentication function in UserService class where username="+userBean.getUsername()+" and password="+userBean.getPassword());
        return loginUserBean;
    }

    private LoginUserBean toLoginUserBean(WaUser teUser) {
        LoginUserBean userBean = new LoginUserBean();
        userBean.setUsername(teUser.getUserName());
        userBean.setFailLoginCount(teUser.getFailedLoginCount());
        userBean.setIsAdmin(teUser.getIsAdmin() != null && teUser.getIsAdmin() == true ? "admin" : "");
        logger.debug("Calling a function to check the current login is admin or not using toLoginUserBean method in UserService class where username="+teUser.getUserName()+" and password="+teUser.getPassword());
        return userBean;
    }
}
