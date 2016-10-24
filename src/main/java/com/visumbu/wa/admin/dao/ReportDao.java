
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.Report.bean.TimeOnSiteBean;
import com.visumbu.wa.bean.ReportPage;
import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.dashboard.bean.DealerVisitBean;
import com.visumbu.wa.dashboard.bean.DashboardTickers;
import com.visumbu.wa.dashboard.bean.DeviceTypeBean;
import com.visumbu.wa.dashboard.bean.VisitLocationBean;
import com.visumbu.wa.model.VisitLog;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
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

    public List getVisitDetailedList(Date startDate, Date endDate, ReportPage page) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("VisitLog.findByVisitTimeRange");
        query.setParameter("startTime", startDate);
        System.out.println(startDate);
        query.setParameter("endTime", endDate);
        System.out.println(endDate);
        if (page != null) {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getCount());
        }
        return query.list();
    }

    public List getTimeOnSiteReport(Date startDate, Date endDate, ReportPage page) {
        String queryStr = "select fingerprint, visit_id visitId, ip_address ipAddress,"
                + " domain_name domainName, city, country, date_format(visit_time, '%m/%d/%Y') visitDay, count(1) count,"
                + " (select timediff(max(action_time), "
                + "min(action_time)) duration from action_log a "
                + "where a.visit_id=v.visit_id and "
                + "date_format(action_time, '%m/%d/%Y') = visitDay) duration "
                + "from visit_log v"
                + " where visit_time between :startDate and :endDate"
                + " group by 1, 2, 3, 4, 5, 6, 7";
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
        return query.list();
    }

}
