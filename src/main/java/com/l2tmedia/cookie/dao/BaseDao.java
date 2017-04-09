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
        logger.debug("Calling function of create in BaseDao class");
        try {
            logger.debug("Object: " + object);
            sessionFactory.getCurrentSession().save(object);
        } catch (Exception e) {
            // Exception need tobe logged
            logger.error("Error in create" +object+","+e);
            e.printStackTrace();
            return null;
        }
        return object;
    }

    public Object update(Object object) {
        logger.debug("Calling  function of update in BaseDao class");
        try {
            sessionFactory.getCurrentSession().merge(object);
            sessionFactory.getCurrentSession().flush();
        } catch (Exception e) {
            logger.error("Exception in update " +object+""+e);
            // Exception need tobe logged
            return null;
        }
        return object;
    }

    public List read(Class c) {
        logger.debug("Calling function of read in BaseDao class");
        return sessionFactory.getCurrentSession().createCriteria(c).list();
    }

    public Object read(Class c, Serializable id) {
        logger.debug("Calling function of read with id in BaseDao class");
        return sessionFactory.getCurrentSession().get(c, id);
    }

    public Object delete(Object object) {
        logger.debug("Calling function of delete in BaseDao class");
        try {
            sessionFactory.getCurrentSession().delete(object);
        } catch (Exception e) {
            // Exception need tobe logged
            logger.debug("Exception in delete"+object+","+e);
            return null;
        }
        return object;
    }

    public Long getCount(String queryStr, Date startDate, Date endDate, Integer dealerId) {
        logger.debug("Calling function of get count in BaseDao class where dealerSiteId="+dealerId+" and startDate="+startDate+" and endDate="+endDate+" and query="+queryStr);
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
        logger.debug("Calling function of get count in BaseDao class where query="+queryStr+" and startDate="+startDate+" and endDate="+endDate);
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
        logger.debug("Calling function of get count in BaseDao class where query="+queryStr);
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
        List<CountBean> count = query.list();
        return count.get(0).getCount();
    }
}
