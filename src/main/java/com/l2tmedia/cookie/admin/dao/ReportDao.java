
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.dao;

import com.l2tmedia.cookie.report.bean.ActionDetailListBean;
import com.l2tmedia.cookie.report.bean.CountBean;
import com.l2tmedia.cookie.report.bean.FormDataBean;
import com.l2tmedia.cookie.report.bean.FrequencyReportBean;
import com.l2tmedia.cookie.report.bean.TimeOnSiteBean;
import com.l2tmedia.cookie.report.bean.VisitDetailListBean;
import com.l2tmedia.cookie.report.bean.VisitReportBean;
import com.l2tmedia.cookie.bean.ReportPage;
import com.l2tmedia.cookie.dao.BaseDao;
import com.l2tmedia.cookie.model.ActionLog;
import com.l2tmedia.cookie.model.VisitLog;
import com.l2tmedia.cookie.model.VisitLogReport;
import com.l2tmedia.cookie.report.bean.ConversionData;
import com.l2tmedia.cookie.report.bean.VisitDetailsBean;
import com.l2tmedia.cookie.report.bean.VisitLogServiceBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author jp
 */
@Transactional
@Repository("reportDao")
public class ReportDao extends BaseDao {

    final static Logger logger = Logger.getLogger(ReportDao.class);

    public List<ActionLog> getSubmitData(Date startDate, Date endDate, Integer dealerSiteId) {
        logger.debug("Querying database for submit data: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);

        String sqlQuery = "select visit_id visitId from conversion c, dealer_report d "
                + "where d.id = c.dealer_id and action_time between :startDate and :endDate";

        //String queryStr = "from ActionLog where actionTime between :startDate and :endDate and formData is not null ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            sqlQuery += " and c.dealer_id = " + dealerSiteId;
        }
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sqlQuery)
                .addScalar("visitId", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ActionLog.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }

    public Map getVisitDetailedList(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Querying database for detailed visit list: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId);

        String additionalConditions = "";

        String countQueryStr = "select count(1) count from visit_log_report, dealer_report "
                + " where dealer_report.id = visit_log_report.dealer_id and visit_time between :startDate and :endDate ";

        String queryStr = "select dealer_name dealerName, url, visit_time visitTime,"
                + " device_type deviceType, visiter_local_time visiterLocalTime, "
                + "location_timezone locationTimezone, ip_address ipAddress, city, "
                + "zip_code zipCode, country, referrer_url referrerUrl from visit_log_report, dealer_report "
                + " where dealer_report.id = visit_log_report.dealer_id and visit_time between :startDate and :endDate ";
        if (dealerSiteId != null && dealerSiteId != 0) {
            additionalConditions += " and dealer_report.site_id = :dealerSiteId ";
        }
        queryStr += additionalConditions;
        countQueryStr += additionalConditions;
        Long count = getCount(countQueryStr, startDate, endDate);

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

        query.setParameter("endDate", endDate);

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
        logger.debug("Querying database for action details list: dealerSiteId=" + dealerSiteId + ", startDate=" + startDate + ", endDate=" + endDate + ", visitId=" + visitId + ", sessionId=" + sessionId + ", fingerprint=" + fingerprint + ", page=" + page);

        String queryStr = "select url, action_name actionName, d.dealer_name dealerName, "
                + " referrer_url referrer, action_time actionTime "
                + " from conversion a, dealer_report d where action_time between :startDate and :endDate and a.dealer_id = d.id ";
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
            queryStr += " and dealer_report.id = :dealerSiteId";
        }

        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("referrer", StringType.INSTANCE)
                .addScalar("actionTime", TimestampType.INSTANCE)
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

    public Map getVisitDetailsList(Date startDate, Date endDate, ReportPage page,
            Integer dealerSiteId, String fingerprint, String sessionId, String visitId) {
        logger.debug("Querying database for visit details list: dealerSiteId=" + dealerSiteId + ", startDate=" + startDate + ", endDate=" + endDate + ", visitId=" + visitId + ", sessionId=" + sessionId + ", fingerprint=" + fingerprint + ", page=" + page);

        String queryStr = "select os, browser, url, device_type deviceType, resolution, timeZone, "
                + " location_latitude latitude , location_longitude longitude, location_timezone tz, region_name regionName, "
                + " referrer_url referrer, visit_time visitTime, "
                + " referrer_type referrerType, "
                + " ip_address ipAddress, city, state, country, zip_code zipcode from visit_log_report v "
                + " where 1 = 1 ";

        String whereCondition = "";
        if (visitId != null) {
            whereCondition += " visit_id = :visitId or ";
        }
        if (!whereCondition.isEmpty()) {
            queryStr += " and ( " + whereCondition + " 1 = 2 )";
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += " and v.dealer_id = :dealerSiteId";
        }
        queryStr += "  group by visit_id, visit_count ";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("referrer", StringType.INSTANCE)
                .addScalar("visitTime", TimestampType.INSTANCE)
                .addScalar("ipAddress", StringType.INSTANCE)
                .addScalar("city", StringType.INSTANCE)
                .addScalar("state", StringType.INSTANCE)
                .addScalar("country", StringType.INSTANCE)
                .addScalar("zipcode", StringType.INSTANCE)
                .addScalar("os", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("deviceType", StringType.INSTANCE)
                .addScalar("resolution", StringType.INSTANCE)
                .addScalar("timeZone", StringType.INSTANCE)
                //.addScalar("dealerName", StringType.INSTANCE)
                .addScalar("latitude", StringType.INSTANCE)
                .addScalar("longitude", StringType.INSTANCE)
                .addScalar("tz", StringType.INSTANCE)
                .addScalar("regionName", StringType.INSTANCE)
                //              .addScalar("referrerUrl", StringType.INSTANCE)
                .addScalar("referrerType", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitDetailListBean.class));
        if (visitId != null) {
            query.setParameter("visitId", visitId);
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        Long count = 0L; //getCountVisitDetails(countQuery, startDate, endDate, sessionId, visitId, fingerprint, dealerSiteId);// getCount(countQuery, startDate, endDate);
        Map returnMap = new HashMap();
        returnMap.put("total", count);
        returnMap.put("data", query.list());
        return returnMap;
    }

    public Long getCountVisitDetails(String queryStr, Date startDate, Date endDate, String sessionId, String visitId, String fingerprint, Integer dealerSiteId) {
        logger.debug("Querying database for count visit details: dealerSiteId=" + dealerSiteId + ", startDate=" + startDate + ", endDate=" + endDate + ", visitId=" + visitId + ", sessionId=" + sessionId + ", fingerprint=" + fingerprint);

        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
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
        List<CountBean> count = query.list();

        return count.get(0).getCount();
    }

    public Map getFormDataList(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Querying database for form data list: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId + ", page: " + page);

        String queryStr = "select url, action_time actionTime, referrer_type referrerType, referrer_url referrerUrl, "
                + "fingerprint, session_id sessionId, visit_id visitId, form_name formName, form_data formData from conversion where action_time between :startDate and :endDate ";
        String countQuery = "select count(1) count from conversion where action_time between :startDate and :endDate ";

        if (dealerSiteId != null && dealerSiteId != 0) {
            countQuery += " and conversion.dealer_id = :dealerSiteId";
            queryStr += " and conversion.dealer_id = :dealerSiteId";
        }
        queryStr += " order by action_time desc";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("fingerprint", StringType.INSTANCE)
                // .addScalar("dealerName", StringType.INSTANCE)
                .addScalar("visitId", StringType.INSTANCE)
                .addScalar("sessionId", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("actionTime", TimestampType.INSTANCE)
                .addScalar("formData", StringType.INSTANCE)
                .addScalar("formName", StringType.INSTANCE)
                .addScalar("referrerType", StringType.INSTANCE)
                .addScalar("referrerUrl", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(FormDataBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (page != null) {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getCount());
        }
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        Long count = getCount(countQuery, startDate, endDate, dealerSiteId);
        Map returnMap = new HashMap();
        returnMap.put("total", count);
        returnMap.put("data", query.list());
        return returnMap;
    }

    public List getTimeOnSiteReport(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Querying database for time on site report: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId + ", page=" + page);

        String queryStr = "select fingerprint, visit_id visitId, ip_address ipAddress,"
                + " domain_name domainName, city, country, date_format(visit_time, '%m/%d/%Y') visitDay, count(1) count,"
                + " (select timediff(max(action_time), "
                + "min(action_time)) duration from conversion a "
                + "where a.visit_id=visit_log_report.visit_id and "
                + "date_format(action_time, '%m/%d/%Y') = visitDay) duration "
                + "from visit_log_report, dealer_report"
                + " where dealer_report.id = visit_log_report.dealer_id and visit_time between :startDate and :endDate";
        if (dealerSiteId != null && dealerSiteId != 0) {
            queryStr += "and dealer_report.site_id = :dealerSiteId";
        }
        queryStr += " group by 1, 2, 3, 4, 5, 6, 7";

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

    public Double getAverage(List<VisitDetailsBean> visitList) {
        Integer count = visitList.size();
        Double sum = 0.0;
        for (Iterator<VisitDetailsBean> iterator = visitList.iterator(); iterator.hasNext();) {
            VisitDetailsBean bean = iterator.next();
            if (bean != null) {
                sum += (bean.getDuration() == null ? 0 : bean.getDuration());
            }
        }
        return sum / count;
    }

    public List<FrequencyReportBean> getByConversionFrequency(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Querying database for conversion frequency: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId + ", page: " + page);

        Map conversionList = getFormDataList(startDate, endDate, page, dealerSiteId);
        Map<Integer, List<VisitDetailsBean>> converstionMapByCount = new HashMap();
        Map<String, FrequencyReportBean> returnMap = new HashMap<>();
        List<FormDataBean> conversionData = (List<FormDataBean>) conversionList.get("data");
        for (Iterator<FormDataBean> iterator = conversionData.iterator(); iterator.hasNext();) {
            FormDataBean formData = iterator.next();
            VisitDetailsBean visitDetailBean = getVisitDetails(formData.getVisitId());
            List<VisitDetailsBean> visitList = new ArrayList<>();
            Integer numberOfTimes = visitDetailBean.getNumberOfTimes();
            if (numberOfTimes > 5) {
                numberOfTimes = 5;
            }
            if (converstionMapByCount.get(numberOfTimes) != null) {
                visitList = (List) converstionMapByCount.get(numberOfTimes);
            }
            visitList.add(visitDetailBean);
            converstionMapByCount.put(numberOfTimes, visitList);
        }
        for (Map.Entry<Integer, List<VisitDetailsBean>> entrySet : converstionMapByCount.entrySet()) {
            Integer key = entrySet.getKey();
            List<VisitDetailsBean> value = entrySet.getValue();
            String strKey = key + "";
            if (key == 5) {
                strKey = ">=5";
            }
            returnMap.put(strKey, new FrequencyReportBean(strKey, getAverage(value) / (60 * 60 * 24)));
        }

        List<FrequencyReportBean> returnFullList = new ArrayList<>();
        returnFullList.add(returnMap.get("1") == null ? (new FrequencyReportBean("1", 0.0)) : ((FrequencyReportBean) returnMap.get("1")));
        returnFullList.add(returnMap.get("2") == null ? (new FrequencyReportBean("2", 0.0)) : ((FrequencyReportBean) returnMap.get("2")));
        returnFullList.add(returnMap.get("3") == null ? (new FrequencyReportBean("3", 0.0)) : ((FrequencyReportBean) returnMap.get("3")));
        returnFullList.add(returnMap.get("4") == null ? (new FrequencyReportBean("4", 0.0)) : ((FrequencyReportBean) returnMap.get("4")));
        returnFullList.add(returnMap.get(">=5") == null ? (new FrequencyReportBean(">=5", 0.0)) : ((FrequencyReportBean) returnMap.get(">=5")));
        return returnFullList;
    }

    public List<FrequencyReportBean> getByFrequency(Date startDate, Date endDate, ReportPage page, Integer dealerSiteId) {
        logger.debug("Querying database for breakdown by frequency: startDate=" + startDate + ", endDate=" + endDate + ", dealerSiteId=" + dealerSiteId + ", page: " + page);

        String queryStr = "select case when count = 1 then 1 when count = 2 then 2 "
                + " when count = 3 then 3 when count = 4 then 4 "
                + " when count >= 5 then \">=5\" end noOfTimes, count(1) count "
                + " from  "
                + " (select visit_id, count(distinct(concat( visit_id, visit_count))) count "
                + " from visit_log_report "
                + "  "
                + ((dealerSiteId != null && dealerSiteId != 0) ? " where visit_log_report.dealer_id = :dealerSiteId and " : " where ")
                + " visit_time between :startDate and :endDate group by visit_id order by 2) a "
                + " group by 1;";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("noOfTimes", StringType.INSTANCE)
                .addScalar("count", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(FrequencyReportBean.class));
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        if (dealerSiteId != null && dealerSiteId != 0) {
            query.setParameter("dealerSiteId", dealerSiteId);
        }
        List<FrequencyReportBean> returnList = query.list();
        Map<String, FrequencyReportBean> valueMap = new HashMap<>();
        for (Iterator<FrequencyReportBean> iterator = returnList.iterator(); iterator.hasNext();) {
            FrequencyReportBean reportBean = iterator.next();
            valueMap.put(reportBean.getNoOfTimes(), reportBean);
        }
        List<FrequencyReportBean> returnFullList = new ArrayList<>();
        returnFullList.add(valueMap.get("1") == null ? (new FrequencyReportBean("1", 0)) : ((FrequencyReportBean) valueMap.get("1")));
        returnFullList.add(valueMap.get("2") == null ? (new FrequencyReportBean("2", 0)) : ((FrequencyReportBean) valueMap.get("2")));
        returnFullList.add(valueMap.get("3") == null ? (new FrequencyReportBean("3", 0)) : ((FrequencyReportBean) valueMap.get("3")));
        returnFullList.add(valueMap.get("4") == null ? (new FrequencyReportBean("4", 0)) : ((FrequencyReportBean) valueMap.get("4")));
        returnFullList.add(valueMap.get(">=5") == null ? (new FrequencyReportBean(">=5", 0)) : ((FrequencyReportBean) valueMap.get(">=5")));
        return returnFullList;
    }

    public Map getVisitLog(Date startDate, Date endDate, ReportPage page) {
        logger.debug("Querying database for visit log: startDate=" + startDate + ", endDate=" + endDate + ", page: " + page);
        String queryStr = "select v.id refId, visit_id visitId, browser, city, state, country, zip_code zipcode, device_type device, ip_address ipaddress, domain_name domainName,"
                + "  pageName page, url, visit_time lastVisitTime, visit_count visitCount, "
                + "(select max(visit_time) - min(visit_time) from visit_log_report v1 where v1.visit_id = v.visit_id and v.visit_time <= v.visit_time) duration, "
                + "referrer_url referrerUrl, referrer_type referrerType, d.dealer_ref_id dealerId, timeZone timeZone, "
                + "fingerprint fingerprint, os os from visit_log_report v, dealer_report d "
                + " where v.dealer_id = d.id and v.visit_time between :startDate and :endDate"
                + " order by visit_time desc";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("refId", StringType.INSTANCE)
                .addScalar("visitId", StringType.INSTANCE)
                .addScalar("browser", StringType.INSTANCE)
                .addScalar("city", StringType.INSTANCE)
                .addScalar("zipcode", StringType.INSTANCE)
                .addScalar("state", StringType.INSTANCE)
                .addScalar("country", StringType.INSTANCE)
                .addScalar("device", StringType.INSTANCE)
                .addScalar("ipaddress", StringType.INSTANCE)
                .addScalar("domainName", StringType.INSTANCE)
                .addScalar("page", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("lastVisitTime", StringType.INSTANCE)
                .addScalar("visitCount", StringType.INSTANCE)
                .addScalar("duration", LongType.INSTANCE)
                .addScalar("referrerUrl", StringType.INSTANCE)
                .addScalar("referrerType", StringType.INSTANCE)
                .addScalar("dealerId", StringType.INSTANCE)
                .addScalar("timeZone", StringType.INSTANCE)
                .addScalar("fingerprint", StringType.INSTANCE)
                .addScalar("os", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitLogServiceBean.class));
        Map resultMap = new HashMap();

        if (page != null) {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getCount());
            resultMap.put("page", page.getPageNo());
            resultMap.put("count", page.getCount());
        }
        String countQuery = "select count(*) count from visit_log_report v, dealer_report d where d.id = v.dealer_id and v.visit_time between :startDate and :endDate";
        Long count = getCount(countQuery, startDate, endDate);
        resultMap.put("count", count);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        resultMap.put("data", query.list());
        return resultMap;
    }

    public List<VisitLog> getVisitLog(String fingerprint, String sessionId, String visitId, String domainName, Date startDate, Date endDate) {
        logger.debug("Querying database for visit log: startDate=" + startDate + ", endDate=" + endDate + ", fingerprint=" + fingerprint + ", sessionId=" + sessionId + ", visitId=" + visitId + ", domainName=" + domainName);

        String queryStr = "from VisitLog where (fingerprint = :fingerprint or sessionId = :sessionId or visitId = :visitId) and domainName = :domainName "
                + " and visitTime between :startDate and :endDate order by visitTime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("fingerprint", fingerprint);
        query.setParameter("visitId", visitId);
        query.setParameter("sessionId", sessionId);
        query.setParameter("domainName", domainName);
        return query.list();
    }

    public List<VisitLogReport> getVisitLog(String visitId) {
        logger.debug("Querying database for visit log list: visitId=" + visitId);

        String queryStr = "from VisitLogReport where visitId = :visitId order by visitTime desc";
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
        query.setParameter("visitId", visitId);
        return query.list();
    }

    public List<VisitLog> getVisitLogReferrer(String fingerprint, String sessionId, String visitId, String domainName, Date startDate, Date endDate) {
        logger.debug("Querying database for visit log referrer: startDate=" + startDate + ", endDate=" + endDate + ", fingerprint=" + fingerprint + ", sessionId=" + sessionId + ", visitId=" + visitId + ", domainName=" + domainName);

        String queryStr = "from VisitLog where (fingerprint = :fingerprint or sessionId = :sessionId or visitId = :visitId) and domainName = :domainName "
                + " and visitTime between :startDate and :endDate and domain_name not like referrer_domain order by visitTime";
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("fingerprint", fingerprint);
        query.setParameter("visitId", visitId);
        query.setParameter("sessionId", sessionId);
        query.setParameter("domainName", domainName);
        return query.list();
    }

    public List<VisitLogReport> getVisitLogReferrer(String visitId) {
        logger.debug("Querying database for visit log referrer report: visitId=" + visitId);

        String queryStr = "from VisitLogReport where visitId = :visitId order by visitTime";
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
        query.setParameter("visitId", visitId);
        return query.list();
    }

    public List<ConversionData> getAllConversionData(Date date) {
        String queryStr = "select c.id conversion_id, action_name,action_time,duration,duration_to_convert,fingerprint, first_visit_time,form_action,form_data,form_id,form_method, form_name,local_hour, local_min,local_sec,local_time,referrer_domain,referrer_type,referrer_url,session_id, session_visit_time,url,user_agent, visit_count, visit_id, d.dealer_ref_id dealer_id  from conversion c, dealer d where d.id = c.dealer_id and date(action_time) = date(:date)";
        System.out.println(queryStr);
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("conversion_id", StringType.INSTANCE)
                .addScalar("action_name", StringType.INSTANCE)
                .addScalar("action_time", StringType.INSTANCE)
                .addScalar("duration", StringType.INSTANCE)
                .addScalar("duration_to_convert", StringType.INSTANCE)
                .addScalar("fingerprint", StringType.INSTANCE)
                .addScalar("first_visit_time", StringType.INSTANCE)
                .addScalar("form_action", StringType.INSTANCE)
                .addScalar("form_data", StringType.INSTANCE)
                .addScalar("form_id", StringType.INSTANCE)
                .addScalar("form_method", StringType.INSTANCE)
                .addScalar("form_name", StringType.INSTANCE)
                .addScalar("local_hour", StringType.INSTANCE)
                .addScalar("local_min", StringType.INSTANCE)
                .addScalar("local_sec", StringType.INSTANCE)
                .addScalar("local_time", StringType.INSTANCE)
                .addScalar("referrer_domain", StringType.INSTANCE)
                .addScalar("referrer_type", StringType.INSTANCE)
                .addScalar("referrer_url", StringType.INSTANCE)
                .addScalar("session_id", StringType.INSTANCE)
                .addScalar("session_visit_time", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("user_agent", StringType.INSTANCE)
                .addScalar("visit_count", StringType.INSTANCE)
                .addScalar("visit_id", StringType.INSTANCE)
                .addScalar("dealer_id", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ConversionData.class));
        query.setParameter("date", date);
        return (List<ConversionData>) query.list();
    }

    private VisitDetailsBean getVisitDetails(String visitId) {
        logger.debug("Querying database for visit details: visitId=" + visitId);

        String queryStr = "select count(distinct(visit_count)) numberOfTimes, TIMESTAMPDIFF(second, min(visit_time), max(visit_time)) duration from visit_log_report where visit_id = :visitId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("numberOfTimes", IntegerType.INSTANCE)
                .addScalar("duration", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitDetailsBean.class));
        query.setParameter("visitId", visitId);
        List<VisitDetailsBean> returnList = query.list();
        if (returnList == null || returnList.isEmpty()) {
            return null;
        }
        return returnList.get(0);
    }
}
