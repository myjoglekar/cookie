
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.dao;

import com.l2tmedia.cookie.Constants;
import com.l2tmedia.cookie.dao.BaseDao;
import com.l2tmedia.cookie.dashboard.bean.BrowserTypeBean;
import com.l2tmedia.cookie.dashboard.bean.DailyBean;
import com.l2tmedia.cookie.dashboard.bean.DealerVisitBean;
import com.l2tmedia.cookie.dashboard.bean.DashboardTickers;
import com.l2tmedia.cookie.dashboard.bean.DeviceTypeBean;
import com.l2tmedia.cookie.dashboard.bean.HourlyVisitBean;
import com.l2tmedia.cookie.dashboard.bean.MonthlyBean;
import com.l2tmedia.cookie.dashboard.bean.OsBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerBean;
import com.l2tmedia.cookie.dashboard.bean.ReferrerPageBean;
import com.l2tmedia.cookie.dashboard.bean.VisitGeoReportBean;
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
import org.apache.log4j.Logger;

/**
 *
 * @author jp
 */
@Transactional
@Repository("dashboardDao")
public class DashboardDao extends BaseDao {

    private Integer maxCount = 5;

    final static Logger logger = Logger.getLogger(DashboardDao.class);

    public void setMaxCount(Integer maxCount) {
        logger.debug("Calling a function to set count value to"+maxCount);
        this.maxCount = maxCount;
    }

    public List<DealerVisitBean> getTopDealersByVisit(Date startDate, Date endDate, Integer dealerSiteId) {
       logger.debug("Calling a function of get top dealers by visit for the dealerSiteId="+dealerSiteId+" and dates between "+startDate+"+ and "+endDate);
        
        String queryStr = "select '-' dealerName, "
                + "count(distinct(concat(visit_id, visit_count))) totalSiteVisit, count(1) totalPageVisit, "
                + "count(distinct(visit_id)) uniqueUserCount from visit_log_report "
                + "where visit_time between :startDate and :endDate ";
        if (dealerSiteId != 0) {
            queryStr += " and visit_log_report.site_id = :dealerSiteId ";
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
        logger.debug("Calling function of hourly visit chart for dealerSiteId="+dealerSiteId+"where date range between "+startDate+" and "+endDate);
        String queryStr = "select HOUR(visit_time) hour, "
                + "count(distinct(session_id)) totalSiteVisit, count(1) totalPageVisit, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log_report, dealer_report  "
                + "where dealer_report.id = visit_log_report.dealer_id and visit_time between :startDate and :endDate ";

        if (dealerSiteId != 0) {
            queryStr += " and dealer_report.site_id = :dealerSiteId ";
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
        logger.debug("Calling function of get dashboard tickers for the dealerSiteId "+dealerSiteId+" where date range between "+startDate+" and "+endDate);
        String queryStr = "select count(distinct(concat(visit_id, visit_count, domain_name))) totalSiteVisit, "
                + "count(distinct(concat(fingerprint, domain_name))) uniqueSiteVisit, "
                + "count(distinct(domain_name)) visitedDomains,"
                + "count(distinct(referrer_domain)) referrerDomains,"
                + "count(1) totalVisits, count(distinct(visit_id)) uniqueUserCount,"
                + "(select count(visit_id) from conversion where form_data is not null and action_time between :startDate and :endDate "
                + ((dealerSiteId != 0) ? " and dealer_id = :dealerSiteId " : "")
                + ") formFilled "
                + "from visit_log_report "
                + "where visit_time between :startDate and :endDate";

        if (dealerSiteId != 0) {
            queryStr += " and visit_log_report.dealer_id = :dealerSiteId ";
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
        logger.debug("Calling a function to get device type for the dealerSiteId "+dealerSiteId+" from dates between "+startDate+" and endDate "+endDate);
        String queryStr = "select case device_type when 'Not a Mobile Device' then 'Desktop' else device_type end deviceType, "
                + "count(distinct(concat(visit_id, visit_count))) visitCount,  count(distinct(concat(visit_id, visit_count)))/(select count(distinct(concat(visit_id, visit_count))) from visit_log_report v1 where v1.visit_time between :startDate and :endDate "
                + ((dealerSiteId != 0) ? " and v1.dealer_id = :dealerSiteId" : "")
                + " ) * 100 visitPercent, count(distinct(visit_id)) uniqueUserCount "
                + " from visit_log_report "
                + " where  visit_time between :startDate and :endDate";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += " and visit_log_report.dealer_id = :dealerSiteId ";
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
        if (maxCount > 0) {
            query.setMaxResults(maxCount);
        }
        return query.list();
    }

    public List<VisitGeoReportBean> getByGeoReport(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling function of get geo report for dealerSiteId="+dealerSiteId+" where date range between "+startDate+" and "+endDate);
        String queryStr = "select country country, city city, state state, "
                + "count(distinct(concat(visit_id, visit_count))) visitCount, "
                + "count(distinct(visit_id)) uniqueUserCount "
                + "from visit_log_report "
                + "where visit_date between :startDate and :endDate "
                + "and city != '' and city is not null ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += " and visit_log_report.dealer_id = :dealerSiteId ";
        }
        queryStr += " group  by 1, 2 order by 5 desc ";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("country", StringType.INSTANCE)
                .addScalar("city", StringType.INSTANCE)
                .addScalar("state", StringType.INSTANCE)
                //                .addScalar("dealerName", StringType.INSTANCE)
                .addScalar("visitCount", IntegerType.INSTANCE)
                //                .addScalar("visitPercent", DoubleType.INSTANCE)
                .addScalar("uniqueUserCount", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitGeoReportBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        if (maxCount > 0) {
            query.setMaxResults(maxCount);
        }
        return query.list();
    }

    public List getByBrowser(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling a function to get browser details for the dealerSiteId "+dealerSiteId+" from dates between "+startDate+" and  "+endDate);
        String queryStr = "select browser browser, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log_report, dealer_report "
                + "where dealer_report.id = visit_log_report.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer_report.site_id = :dealerSiteId";
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
        if (maxCount > 0) {
            query.setMaxResults(maxCount);
        }
        return query.list();
    }

    public List getByOs(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling a function to get OS details for the dealerSiteId "+dealerSiteId+" from dates between "+startDate+" and endDate "+endDate);
        String queryStr = "select  SUBSTRING_INDEX(os, ' ', 1) os, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log_report, dealer_report "
                + "where dealer_report.id = visit_log_report.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer_report.site_id = :dealerSiteId";
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
        logger.debug("Calling a function to get referrer Page for the dealerSiteId "+dealerSiteId+" from dates between "+startDate+" and "+endDate);
        String queryStr = "select case when first_referrer_url is null then 'Direct' else first_referrer_url end referrer, count(distinct(concat(visit_id, visit_count))) visitCount, "
                + "count(distinct(visit_id)) uniqueUserCount from visit_log_report "
                + "where referrer_domain not like domain_name and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and visit_log_report.dealer_id = :dealerSiteId";
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
        if (maxCount > 0) {
            query.setMaxResults(maxCount);
        }
        return query.list();
    }

    public List<ReferrerBean> getByReferrer(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling a function to get referrer for the dealerSiteId "+dealerSiteId+" from dates between "+startDate+" and  "+endDate);
        String queryStr = "select case when referrer_domain is null then 'Direct' else referrer_domain end referrer, count(distinct(concat(visit_id, visit_count))) visitCount, "
                + "count(distinct(visit_id)) uniqueUserCount from visit_log_report "
                + "where referrer_domain not like domain_name and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and visit_log_report.dealer_id = :dealerSiteId";
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
        if (maxCount > 0) {
            query.setMaxResults(maxCount);
        }
        return query.list();
    }

    public List getByMonthly(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling a function to get monthly dealer details by dealerSiteId for the dealerSiteId "+dealerSiteId+" from dates between "+startDate+" and "+endDate);
        String queryStr = "select monthname(visit_time) monthName, year(visit_time) year, month(visit_time) month, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log_report, dealer_report "
                + "where dealer_report.id = visit_log_report.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer_report.site_id = :dealerSiteId";
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
        if (maxCount > 0) {
            query.setMaxResults(maxCount);
        }
        return query.list();
    }

    public List getByDaily(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Calling a function to get daily dealer details by dealerSiteId for the dealerSiteId "+dealerSiteId+" from dates between "+startDate+" and "+endDate);
        String queryStr = "select date(visit_time) visitDate, year(visit_time) year, month(visit_time) month, count(1) visitCount, "
                + "count(distinct(fingerprint)) uniqueUserCount from visit_log_report, dealer_report "
                + "where dealer_report.id = visit_log_report.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer_report.site_id = :dealerSiteId";
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
        if (maxCount > 0) {
            query.setMaxResults(maxCount);
        }
        return query.list();
    }
}
