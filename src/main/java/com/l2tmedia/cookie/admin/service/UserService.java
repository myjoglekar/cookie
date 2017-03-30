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
        logger.debug("Start function of create in UserService class");
        List<WaUser> users = userDao.findByUserName(teUser.getUserName());
        if (users.isEmpty()) {
            teUser.setStatus("Active");
            teUser.setCreatedTime(new Date());
            teUser.setTheme("default");
            return (WaUser) userDao.create(teUser);
        }
        logger.debug("End  function of create  in UserService class");
        return null;
    }

    public WaUser read(Integer id) {
        logger.debug("Start function of read By Id in UserService class");
        logger.debug("End  function of read  By Id in UserService class");
        return (WaUser) userDao.read(WaUser.class, id);
    }

    public List<WaUser> read() {
        logger.debug("Start function of read in UserService class");
        List<WaUser> users = userDao.read();
        logger.debug("End  function of read  in UserService class");
        return users;
    }

    public WaUser update(WaUser teUser) {
        logger.debug("Start function of update in UserService class");
        logger.debug("End  function of update  in UserService class");
        return (WaUser) userDao.update(teUser);
    }

    public WaUser delete(Integer id) {
        logger.debug("Start function of delete by id in UserService class");
        WaUser teUser = read(id);
        teUser.setStatus("Deleted");
        logger.debug("End  function of  delete by id in UserService class");
        return update(teUser);
    }

    public WaUser delete(WaUser teUser) {
        logger.debug("Start function of delete in UserService class");
        logger.debug("End  function of delete  in UserService class");
        return (WaUser) userDao.delete(teUser);
    }

    public LoginUserBean authenicate(LoginUserBean userBean) {
        logger.debug("Start function of authenticate in UserService class");
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
        logger.debug("End  function of authenticate in UserService class");
        return loginUserBean;
    }

    private LoginUserBean toLoginUserBean(WaUser teUser) {
        logger.debug("Start function of Login user bean in UserService class");
        LoginUserBean userBean = new LoginUserBean();
        userBean.setUsername(teUser.getUserName());
        userBean.setFailLoginCount(teUser.getFailedLoginCount());
        userBean.setIsAdmin(teUser.getIsAdmin() != null && teUser.getIsAdmin() == true ? "admin" : "");
        logger.debug("End  function of login user bean  in UserService class");
        return userBean;
    }
}
