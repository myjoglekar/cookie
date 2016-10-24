
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.dashboard.bean.BrowserTypeBean;
import com.visumbu.wa.dashboard.bean.DealerVisitBean;
import com.visumbu.wa.dashboard.bean.DashboardTickers;
import com.visumbu.wa.dashboard.bean.DeviceTypeBean;
import com.visumbu.wa.dashboard.bean.HourlyVisitBean;
import com.visumbu.wa.dashboard.bean.VisitLocationBean;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
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
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log , dealer "
                + "where visit_time between :startDate and :endDate and visit_log.site_id = dealer.id group by 1";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("dealerName", StringType.INSTANCE)
                //.addScalar("website", StringType.INSTANCE)
                .addScalar("totalSiteVisit", IntegerType.INSTANCE)
                .addScalar("totalPageVisit", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DealerVisitBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }
    
    
    public List hourlyVisitChart(Date startDate, Date endDate) {
        String queryStr = "select HOUR(visit_time) hour, "
                + "count(distinct(session_id)) totalSiteVisit, count(1) totalPageVisit, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log  "
                + "where visit_time between :startDate and :endDate "
                + "group by 1";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("hour", IntegerType.INSTANCE)
                .addScalar("totalSiteVisit", IntegerType.INSTANCE)
                .addScalar("totalPageVisit", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(HourlyVisitBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }

    public List getDashboardTickers(Date startDate, Date endDate) {
        String queryStr = "select count(distinct(concat(session_id, domain_name))) totalSiteVisit, "
                + "count(distinct(concat(fingerprint, domain_name))) uniqueSiteVisit, "
                + "count(distinct(domain_name)) visitedDomains,"
                + "count(1) totalVisits, count(distinct(fingerprint)) uniqueUserCount"
                + "(select count(1) from action_log where form_data is not null and action_time between :startDate and :endDate) formFilled "
                + "from visit_log  where visit_time between :startDate and :endDate";

        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("totalSiteVisit", IntegerType.INSTANCE)
                .addScalar("uniqueSiteVisit", IntegerType.INSTANCE)
                .addScalar("visitedDomains", IntegerType.INSTANCE)
                .addScalar("totalVisits", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .addScalar("formFilled", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DashboardTickers.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }

    public List getByDeviceType(Date startDate, Date endDate) {
        String queryStr = "select case device_type when 'Not a Mobile Device' then 'Desktop' else device_type end deviceType, "
                + "count(1) visitCount, count(1)/(select count(*) from visit_log) * 100 visitPercent "
                + "from visit_log where visit_time between :startDate and :endDate group by 1 order by 2 desc limit 5";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("deviceType", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("visitPercent", DoubleType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DeviceTypeBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }

    public List getByLocation(Date startDate, Date endDate) {
        String queryStr = "select city city, country country,  "
                + "count(1) visitCount, count(1)/(select count(*) from visit_log) * 100 visitPercent  "
                + "from visit_log "
                + "where visit_time between :startDate and :endDate and city != '' and city is not null "
                + "group  by 1, 2 order by 3 desc limit 5;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("city", StringType.INSTANCE)
                .addScalar("country", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("visitPercent", DoubleType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitLocationBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }

    public List getByBrowser(Date startDate, Date endDate) {
        String queryStr = "select browser browser, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log "
                + "where visit_time between :startDate and :endDate group by 1";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("browser", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(BrowserTypeBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }
}
