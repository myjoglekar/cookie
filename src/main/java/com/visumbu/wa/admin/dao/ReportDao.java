
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.Report.bean.CountBean;
import com.visumbu.wa.Report.bean.FrequencyReportBean;
import com.visumbu.wa.Report.bean.TimeOnSiteBean;
import com.visumbu.wa.Report.bean.VisitReportBean;
import com.visumbu.wa.bean.ReportPage;
import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.dashboard.bean.DealerVisitBean;
import com.visumbu.wa.dashboard.bean.DashboardTickers;
import com.visumbu.wa.dashboard.bean.DeviceTypeBean;
import com.visumbu.wa.dashboard.bean.VisitLocationBean;
import com.visumbu.wa.model.VisitLog;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jp
 */
@Transactional
@Repository("reportDao")
public class ReportDao extends BaseDao {

    private Long getCount(String queryStr, Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
        query.setParameter("startDate", startDate);
        System.out.println(startDate);
        query.setParameter("endDate", endDate);
        List<CountBean> count = query.list();
        return count.get(0).getCount();
    }

    public Map getVisitDetailedList(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {

        String additionalConditions = "";

        String countQueryStr = "select count(1) count from visit_log, dealer "
                + " where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate ";

        String queryStr = "select dealer_name dealerName, url, visit_time visitTime,"
                + " device_type deviceType, visiter_local_time visiterLocalTime, "
                + "location_timezone locationTimezone, ip_address ipAddress, city, "
                + "zip_code zipCode, country, referrer_url referrerUrl from visit_log, dealer "
                + " where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            additionalConditions += " and dealer.site_id = :dealerSiteId ";
        }
        queryStr += additionalConditions;
        countQueryStr += additionalConditions;
        Long count = getCount(countQueryStr, startDate, endDate);
        System.out.println(queryStr);
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("dealerName", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("visitTime", IntegerType.INSTANCE)
                .addScalar("deviceType", StringType.INSTANCE)
                .addScalar("visiterLocalTime", StringType.INSTANCE)
                .addScalar("locationTimezone", StringType.INSTANCE)
                .addScalar("ipAddress", StringType.INSTANCE)
                .addScalar("city", StringType.INSTANCE)
                .addScalar("zipCode", StringType.INSTANCE)
                .addScalar("country", StringType.INSTANCE)
                .addScalar("referrerUrl", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitReportBean.class));
        query.setParameter("startDate", startDate);
        System.out.println(startDate);
        query.setParameter("endDate", endDate);
        System.out.println(endDate);
        Map resultMap = new HashMap();
        if (page != null) {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getCount());
            resultMap.put("page", page.getPageNo());
            resultMap.put("count", page.getCount());
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        resultMap.put("count", count);
        resultMap.put("data", query.list());
        return resultMap;
    }

    public List getTimeOnSiteReport(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        String queryStr = "select fingerprint, visit_id visitId, ip_address ipAddress,"
                + " domain_name domainName, city, country, date_format(visit_time, '%m/%d/%Y') visitDay, count(1) count,"
                + " (select timediff(max(action_time), "
                + "min(action_time)) duration from action_log a "
                + "where a.visit_id=visit_log.visit_id and "
                + "date_format(action_time, '%m/%d/%Y') = visitDay) duration "
                + "from visit_log, dealer"
                + " where dealer.id = visit_log.dealer_id and visit_time between :startDate and :endDate";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer.site_id = :dealerSiteId";
        }
        queryStr += " group by 1, 2, 3, 4, 5, 6, 7";
        System.out.println(queryStr);
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("fingerprint", StringType.INSTANCE)
                .addScalar("visitId", StringType.INSTANCE)
                .addScalar("ipAddress", StringType.INSTANCE)
                .addScalar("visitDay", StringType.INSTANCE)
                .addScalar("count", IntegerType.INSTANCE)
                .addScalar("domainName", StringType.INSTANCE)
                .addScalar("city", StringType.INSTANCE)
                .addScalar("country", StringType.INSTANCE)
                .addScalar("duration", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(TimeOnSiteBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (page != null) {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getCount());
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    public List getByFrequency(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        String queryStr = "select count noOfVisits, dealer_name dealerName, fingerprint, count(1) totalTimes from "
                + "(select fingerprint, dealer.dealer_name, count(1) count from visit_log, dealer "
                + " where dealer.id = visit_log.dealer_id "
                + ((dealerSiteId != null && dealerSiteId != 0) ? " and visit_log.dealer_id = :dealerSiteId " : "")
                + " and visit_time between :startDate and :endDate group by 1, 2 order by 3) a "
                + "group by 1, 2, 3 order by 1 desc";

        System.out.println(queryStr);
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("noOfVisits", IntegerType.INSTANCE)
                .addScalar("dealerName", StringType.INSTANCE)
                .addScalar("fingerprint", StringType.INSTANCE)
                .addScalar("city", StringType.INSTANCE)
                .addScalar("totalTimes", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(FrequencyReportBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (page != null) {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getCount());
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }
    //select count, fingerprint, city, count(1) visited_time from (select fingerprint, city, count(1) count from visit_log group by 1 order by 3) a group by 1 order by 1;
// select count, count(1) visited_time from (select fingerprint, city, count(1) count from visit_log group by 1 order by 3) a group by 1 order by 1;
}
