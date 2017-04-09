/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.dao;

import com.l2tmedia.cookie.dao.BaseDao;
import com.l2tmedia.cookie.model.WaUser;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author jp
 */
@Transactional
@Repository("userDao")
public class UserDao extends BaseDao {

    final static Logger logger = Logger.getLogger(UserDao.class);
    public List<WaUser> read() {
        logger.debug("calling function of read user details");
        Query query = sessionFactory.getCurrentSession().createQuery("from WaUser where status is null or status != 'Deleted'");
        return query.list();
    }

    public List<WaUser> findByUserName(String username) {
        logger.debug("calling function to find the username of the name="+username);
        Query query = sessionFactory.getCurrentSession().getNamedQuery("WaUser.findByUserName");
        query.setParameter("userName", username);
        return query.list();
    }
}
