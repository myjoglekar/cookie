/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.dao;

import com.l2tmedia.cookie.report.bean.CountBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author jp
 */
@Transactional
@Repository("baseDao")
public class BaseDao {

    @Autowired
    protected SessionFactory sessionFactory;

    final static Logger logger = Logger.getLogger(BaseDao.class);

    public Object create(Object object) {
        logger.debug("Start function of create in BaseDao class");
        try {
            logger.debug("Object: " + object);
            sessionFactory.getCurrentSession().save(object);
            //sessionFactory.getCurrentSession().flush();
        } catch (Exception e) {
            // Exception need tobe logged
            logger.error("Exception in create function BaseDao class with exception" + e);
            e.printStackTrace();
            return null;
        }
        logger.debug("End  function of create  in BaseDao class");
        return object;
    }

    public Object update(Object object) {
        logger.debug("Start function of update in BaseDao class");
        try {
            sessionFactory.getCurrentSession().merge(object);
            sessionFactory.getCurrentSession().flush();
        } catch (Exception e) {
            logger.error("Exception in update function BaseDao class with exception" + e);
            // Exception need tobe logged
            return null;
        }
        logger.debug("End  function of update  in BaseDao class");
        return object;
    }

    public List read(Class c) {
        logger.debug("Start function of read in BaseDao class");
        logger.debug("End  function of read  in BaseDao class");
        return sessionFactory.getCurrentSession().createCriteria(c).list();
    }

    public Object read(Class c, Serializable id) {
        logger.debug("Start function of read with id in BaseDao class");
        logger.debug("End  function of read with id in BaseDao class");
        return sessionFactory.getCurrentSession().get(c, id);
    }

    public Object delete(Object object) {
        logger.debug("Start function of delete in BaseDao class");
        try {
            sessionFactory.getCurrentSession().delete(object);
        } catch (Exception e) {
            // Exception need tobe logged
            logger.debug("Exception in delete method in BaseDao class"+e);
            return null;
        }
        logger.debug("End  function of delete  in BaseDao class");
        return object;
    }

    public Long getCount(String queryStr, Date startDate, Date endDate, Integer dealerId) {
        logger.debug("Start function of get count by delaer id in BaseDao class");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
        query.setParameter("startDate", startDate);
        logger.debug(startDate);
        query.setParameter("endDate", endDate);
        if (dealerId != null && dealerId != 0) {
            query.setParameter("dealerSiteId", dealerId);
        }
        List<CountBean> count = query.list();
        logger.debug("End  function of get count by dealer id  in BaseDao class");
        return count.get(0).getCount();
    }

    public Long getCount(String queryStr, Date startDate, Date endDate) {
        logger.debug("Start function of get count in BaseDao class");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
        query.setParameter("startDate", startDate);
        logger.debug(startDate);
        query.setParameter("endDate", endDate);
        List<CountBean> count = query.list();
        logger.debug("End  function of get count in BaseDao class");
        return count.get(0).getCount();
    }

    public Long getCount(String queryStr) {
        logger.debug("Start function of get count by query string in BaseDao class");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
        List<CountBean> count = query.list();
        logger.debug("End  function of get count by query string  in BaseDao class");
        return count.get(0).getCount();
    }
}
