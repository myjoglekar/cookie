
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.dashboard.bean.DealerVisitBean;
import com.visumbu.wa.dashboard.bean.DashboardTickers;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jp
 */
@Transactional
@Repository("dashboardDao")
public class DashboardDao extends BaseDao {

    public List getTopDealersByVisit(Date startDate, Date endDate) {
        String queryStr = "select dealer.dealer_name dealerName, "
                + "count(distinct(session_id)) totalSiteVisit, count(1) totalPageVisit, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log, dealer "
                + "where visit_log.site_id = dealer.id group by 1";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("dealerName", StringType.INSTANCE)
                //.addScalar("website", StringType.INSTANCE)
                .addScalar("totalSiteVisit", IntegerType.INSTANCE)
                .addScalar("totalPageVisit", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DealerVisitBean.class));
        return query.list();
    }

    public List getDashboardTickers(Date startDate, Date endDate) {
        String queryStr = "select count(distinct(concat(session_id, domain_name))) totalSiteVisit, "
                + "count(distinct(concat(fingerprint, domain_name))) uniqueSiteVisit, "
                + "count(distinct(domain_name)) visitedDomains,"
                + "count(1) totalVisits, count(distinct(fingerprint)) uniqueUserCount "
                + "from visit_log, dealer where visit_log.site_id = dealer.id";
        
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("totalSiteVisit", IntegerType.INSTANCE)
                .addScalar("uniqueSiteVisit", IntegerType.INSTANCE)
                .addScalar("visitedDomains", IntegerType.INSTANCE)
                .addScalar("totalVisits", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DashboardTickers.class));
        return query.list();
    }

}
