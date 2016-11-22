
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.dashboard.bean.BrowserTypeBean;
import com.visumbu.wa.dashboard.bean.DailyBean;
import com.visumbu.wa.dashboard.bean.DealerVisitBean;
import com.visumbu.wa.dashboard.bean.DashboardTickers;
import com.visumbu.wa.dashboard.bean.DeviceTypeBean;
import com.visumbu.wa.dashboard.bean.HourlyVisitBean;
import com.visumbu.wa.dashboard.bean.MonthlyBean;
import com.visumbu.wa.dashboard.bean.OsBean;
import com.visumbu.wa.dashboard.bean.ReferrerBean;
import com.visumbu.wa.dashboard.bean.ReferrerPageBean;
import com.visumbu.wa.dashboard.bean.VisitGeoReportBean;
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

    public List<DealerVisitBean> getTopDealersByVisit(Date startDate, Date endDate, Integer dealerSiteId) {

        String queryStr = "select dealer.dealer_name dealerName, "
                + "count(distinct(concat(visit_id, visit_count))) totalSiteVisit, count(1) totalPageVisit, "
                + "count(distinct(visit_id)) uniqueUserCount from visit_log , dealer "
                + "where visit_time between :startDate and :endDate and visit_log.site_id = dealer.id ";
        if (dealerSiteId != 0) {
            queryStr += " and dealer.site_id = :dealerSiteId ";
        }
        queryStr += " group by 1";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("dealerName", StringType.INSTANCE)
                //.addScalar("website", StringType.INSTANCE)
                .addScalar("totalSiteVisit", IntegerType.INSTANCE)
                .addScalar("totalPageVisit", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DealerVisitBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    public List hourlyVisitChart(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select HOUR(visit_time) hour, "
                + "count(distinct(session_id)) totalSiteVisit, count(1) totalPageVisit, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log, dealer  "
                + "where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate ";

        if (dealerSiteId != 0) {
            queryStr += " and dealer.site_id = :dealerSiteId ";
        }
        queryStr += " group by 1";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("hour", IntegerType.INSTANCE)
                .addScalar("totalSiteVisit", IntegerType.INSTANCE)
                .addScalar("totalPageVisit", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(HourlyVisitBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    public List getDashboardTickers(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select count(distinct(concat(visit_id, visit_count, domain_name))) totalSiteVisit, "
                + "count(distinct(concat(fingerprint, domain_name))) uniqueSiteVisit, "
                + "count(distinct(domain_name)) visitedDomains,"
                + "count(distinct(referrer_domain)) referrerDomains,"
                + "count(1) totalVisits, count(distinct(visit_id)) uniqueUserCount,"
                + "(select count(1) from action_log where form_data is not null and action_time between :startDate and :endDate "
                + ((dealerSiteId != 0) ? " and dealer_id = :dealerSiteId " : "")
                + ") formFilled "
                + "from visit_log, dealer "
                + "where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate";

        if (dealerSiteId != 0) {
            queryStr += " and dealer.site_id = :dealerSiteId ";
        }

        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("totalSiteVisit", IntegerType.INSTANCE)
                .addScalar("uniqueSiteVisit", IntegerType.INSTANCE)
                .addScalar("visitedDomains", IntegerType.INSTANCE)
                .addScalar("totalVisits", IntegerType.INSTANCE)
                .addScalar("referrerDomains", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .addScalar("formFilled", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DashboardTickers.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    public List<DeviceTypeBean> getByDeviceType(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select case device_type when 'Not a Mobile Device' then 'Desktop' else device_type end deviceType, "
                + "count(distinct(concat(visit_id, visit_count))) visitCount,  count(distinct(concat(visit_id, visit_count)))/(select count(distinct(concat(visit_id, visit_count))) from visit_log v1, dealer d1 where d1.id = v1.dealer_id and v1.visit_time between :startDate and :endDate " +
                ((dealerSiteId != 0) ? " and d1.id = :dealerSiteId" : "" )
                + " ) * 100 visitPercent, count(distinct(visit_id)) uniqueUserCount "
                + " from visit_log, dealer "
                + " where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += " and dealer.site_id = :dealerSiteId ";
        }
        queryStr += " group by 1 order by 2 desc";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("deviceType", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .addScalar("visitPercent", DoubleType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DeviceTypeBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    public List<VisitGeoReportBean> getByGeoReport(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select country country, city city, state state, dealer_name dealerName, "
                + "count(distinct(concat(visit_id, visit_count))) visitCount, count(distinct(concat(visit_id, visit_count)))/(select count(distinct(concat(v1.visit_id, v1.visit_count))) from visit_log v1, dealer d1 where d1.id = v1.dealer_id and v1.visit_time between :startDate and :endDate " +
                ((dealerSiteId != 0) ? " and d1.id = :dealerSiteId" : "" )
                + " ) * 100 visitPercent, "
                + "count(distinct(visit_id)) uniqueUserCount "
                + "from visit_log, dealer "
                + "where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate "
                + "and city != '' and city is not null ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += " and dealer.site_id = :dealerSiteId ";
        }
        queryStr += " group  by 1, 2 order by 5 desc ";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("country", StringType.INSTANCE)
                .addScalar("city", StringType.INSTANCE)
                .addScalar("state", StringType.INSTANCE)
                .addScalar("dealerName", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("visitPercent", DoubleType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitGeoReportBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    public List getByBrowser(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select browser browser, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log, dealer "
                + "where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer.site_id = :dealerSiteId";
        }
        queryStr += " group by 1 order by 2 desc";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("browser", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(BrowserTypeBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }
    public List getByOs(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select  SUBSTRING_INDEX(os, ' ', 1) os, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log, dealer "
                + "where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer.site_id = :dealerSiteId";
        }
        queryStr += " group by 1 order by 2 desc";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("os", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(OsBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    
    public List<ReferrerPageBean> getByReferrerPage(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select case when referrer_url is null then 'Direct' else referrer_url end referrer, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log, dealer "
                + "where referrer_domain not like domain_name and dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer.site_id = :dealerSiteId";
        }
        queryStr += " group by 1 order by 2 desc";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("referrer", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ReferrerPageBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    
    public List<ReferrerBean> getByReferrer(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select case when referrer_domain is null then 'Direct' else referrer_domain end referrer, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log, dealer "
                + "where referrer_domain not like domain_name and dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer.site_id = :dealerSiteId";
        }
        queryStr += " group by 1 order by 2 desc";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("referrer", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ReferrerBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    public List getByMonthly(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select monthname(visit_time) monthName, year(visit_time) year, month(visit_time) month, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log, dealer "
                + "where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer.site_id = :dealerSiteId";
        }
        queryStr += " group by 1, 2, 3 order by 2 desc, 3 desc";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("monthName", StringType.INSTANCE)
                .addScalar("month", IntegerType.INSTANCE)
                .addScalar("year", IntegerType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(MonthlyBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }
    
    public List getByDaily(Date startDate, Date endDate, Integer dealerSiteId) {
        String queryStr = "select date(visit_time) visitDate, year(visit_time) year, month(visit_time) month, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log, dealer "
                + "where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer.site_id = :dealerSiteId";
        }
        queryStr += " group by 1, 2, 3 order by 1 desc";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("visitDate", StringType.INSTANCE)
                .addScalar("month", IntegerType.INSTANCE)
                .addScalar("year", IntegerType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(DailyBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }
}
