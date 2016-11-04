
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.Report.bean.ActionDetailListBean;
import com.visumbu.wa.Report.bean.CountBean;
import com.visumbu.wa.Report.bean.FrequencyReportBean;
import com.visumbu.wa.Report.bean.TimeOnSiteBean;
import com.visumbu.wa.Report.bean.VisitDetailListBean;
import com.visumbu.wa.Report.bean.VisitReportBean;
import com.visumbu.wa.bean.ReportPage;
import com.visumbu.wa.dao.BaseDao;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
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

    public List getActionDetailsList(Date startDate, Date endDate, ReportPage page,
            Integer dealerSiteId, String fingerprint, String sessionId, String visitId) {
        String queryStr = "select url, action_name actionName, d.dealer_name dealerName, "
                + " referrer_url referrer, action_time actionTime "
                + " from action_log a, dealer d where action_time between :startDate and :endDate and a.dealer_id = d.id ";
        String whereCondition = "";
        if (sessionId != null) {
            whereCondition += " session_id = :sessionId or ";
        }
        if (visitId != null) {
            whereCondition += " visit_id = :visitId or ";
        }
        if (fingerprint != null) {
            whereCondition += " fingerprint = :fingerprint or ";
        }
        if (!whereCondition.isEmpty()) {
            queryStr += " and ( " + whereCondition + " 1 = 2 )";
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += " and dealer.id = :dealerSiteId";
        }

        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("referrer", StringType.INSTANCE)
                .addScalar("actionTime", DateType.INSTANCE)
                .addScalar("actionName", StringType.INSTANCE)
                .addScalar("zipcode", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("dealerName", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ActionDetailListBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (sessionId != null) {
            query.setParameter("sessionId", sessionId);
        }
        if (visitId != null) {
            query.setParameter("visitId", visitId);
        }
        if (fingerprint != null) {
            query.setParameter("fingerprint", fingerprint);
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    public List getVisitDetailsList(Date startDate, Date endDate, ReportPage page,
            Integer dealerSiteId, String fingerprint, String sessionId, String visitId) {
        String queryStr = "select os, browser, url, device_type deviceType, resolution, timeZone, d.dealer_name dealerName, "
                + " location_latitude latitude , location_longitude longitude, location_timezone tz, region_name regionName, "
                + " referrer_url referrer, visit_time visitTime, "
                + " ip_address ipAddress, city, state, country, zipcode from visit_log v, dealer d "
                + " where visit_time between :startDate and :endDate and v.dealer_id = d.id ";
        String whereCondition = "";
        if (sessionId != null) {
            whereCondition += " session_id = :sessionId or ";
        }
        if (visitId != null) {
            whereCondition += " visit_id = :visitId or ";
        }
        if (fingerprint != null) {
            whereCondition += " fingerprint = :fingerprint or ";
        }
        if (!whereCondition.isEmpty()) {
            queryStr += " and ( " + whereCondition + " 1 = 2 )";
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += " and dealer.id = :dealerSiteId";
        }

        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("referrer", StringType.INSTANCE)
                .addScalar("visitTime", DateType.INSTANCE)
                .addScalar("ipAddress", StringType.INSTANCE)
                .addScalar("city", StringType.INSTANCE)
                .addScalar("state", StringType.INSTANCE)
                .addScalar("country", DateType.INSTANCE)
                .addScalar("zipcode", StringType.INSTANCE)
                .addScalar("os", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("deviceType", StringType.INSTANCE)
                .addScalar("resolution", StringType.INSTANCE)
                .addScalar("timeZone", StringType.INSTANCE)
                .addScalar("dealerName", StringType.INSTANCE)
                .addScalar("latitude", StringType.INSTANCE)
                .addScalar("longitude", StringType.INSTANCE)
                .addScalar("tz", StringType.INSTANCE)
                .addScalar("regionName", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitDetailListBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (sessionId != null) {
            query.setParameter("sessionId", sessionId);
        }
        if (visitId != null) {
            query.setParameter("visitId", visitId);
        }
        if (fingerprint != null) {
            query.setParameter("fingerprint", fingerprint);
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        return query.list();
    }

    public List getFormDataList(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        String queryStr = "select url, action_time actionTime, fingerprint, session_id sessionId, domain_name domainName, "
                + "visit_id visitId, form_name formName, form_data formData "
                + "from action_log where form_data is not null ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += " and dealer.site_id = :dealerSiteId";
        }
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("fingerprint", StringType.INSTANCE)
                .addScalar("visitId", StringType.INSTANCE)
                .addScalar("sessionId", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("visitDay", StringType.INSTANCE)
                .addScalar("actionTime", DateType.INSTANCE)
                .addScalar("domainName", StringType.INSTANCE)
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
