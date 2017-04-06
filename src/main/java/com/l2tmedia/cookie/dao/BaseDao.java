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
        try {
            logger.debug("Creating object in database: " + object.getClass());
            sessionFactory.getCurrentSession().save(object);
            //sessionFactory.getCurrentSession().flush();
        } catch (Exception e) {
            // Exception need tobe logged
            logger.error("Error saving object in database", e);
            return null;
        }
        return object;
    }

    public Object update(Object object) {
        try {
            sessionFactory.getCurrentSession().merge(object);
            sessionFactory.getCurrentSession().flush();
        } catch (Exception e) {
            logger.error("Error updating object in database", e);
            return null;
        }
        return object;
    }

    public List read(Class c) {
        return sessionFactory.getCurrentSession().createCriteria(c).list();
    }

    public Object read(Class c, Serializable id) {
        return sessionFactory.getCurrentSession().get(c, id);
    }

    public Object delete(Object object) {
        try {
            sessionFactory.getCurrentSession().delete(object);
        } catch (Exception e) {
            // Exception need tobe logged
            logger.debug("Error deleting object from database", e);
            return null;
        }
        return object;
    }

    public Long getCount(String queryStr, Date startDate, Date endDate, Integer dealerId) {
        logger.debug("Getting count from database: dealerSiteId=" + dealerId + ", startDate=" + startDate + ", endDate=" + endDate + ", query=" + queryStr);
        
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
        return count.get(0).getCount();
    }

    public Long getCount(String queryStr, Date startDate, Date endDate) {
        logger.debug("Getting count from database: startDate=" + startDate + ", endDate=" + endDate + ", query=" + queryStr);
        
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
        query.setParameter("startDate", startDate);
        logger.debug(startDate);
        query.setParameter("endDate", endDate);
        List<CountBean> count = query.list();
        return count.get(0).getCount();
    }

    public Long getCount(String queryStr) {
        logger.debug("Getting count from database: query=" + queryStr);
        
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
        List<CountBean> count = query.list();
        return count.get(0).getCount();
    }
}
