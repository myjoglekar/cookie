/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.report1.bean.CountBean;
import com.visumbu.wa.bean.ReportPage;
import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.DealerSite;
import com.visumbu.wa.utils.DateUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Transactional
@Repository("dealerDao")
public class DealerDao extends BaseDao {

    public Dealer create(Dealer dealer) {
        sessionFactory.getCurrentSession().save(dealer);
        return dealer;
    }

    public Dealer findBySiteId(String siteId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Dealer where siteId = :siteId");
        query.setParameter("siteId", siteId);
        List<Dealer> dealers = query.list();
        if (dealers == null || dealers.isEmpty()) {
            return null;
        }
        return dealers.get(0);
    }

    public DealerSite findDealerSite(Integer id, String domainName) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("DealerSite.findByDealerNSiteName");
        query.setParameter("dealerId", id);
        query.setParameter("siteName", domainName);

        List<DealerSite> sites = query.list();
        if (sites == null || sites.isEmpty()) {
            return null;
        }
        return sites.get(0);
    }

    private Long getCountDealer(String queryStr, String status) {
        String extraCondition = "";
        Date yesterday = DateUtils.getYesterday();
        if (status != null) {
            if (status.equalsIgnoreCase("active")) {
                extraCondition += " where last_site_visit > :yesterday ";
            } else if (status.equalsIgnoreCase("inactive")) {
                extraCondition += " where last_site_visit < :yesterday or last_site_visit is null";
            }
        }
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr + extraCondition)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
        if (status != null) {
            if (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("inactive")) {
                query.setParameter("yesterday", yesterday);
            }
        }
        List<CountBean> count = query.list();
        return count.get(0).getCount();
    }

    public Map getDealers(ReportPage page, String status) {
        String countQueryStr = "select count(1) count from dealer ";
        String queryStr = "from Dealer ";
        String extraCondition = "";
        if (status != null) {
            if (status.equalsIgnoreCase("active")) {
                extraCondition += " where lastSiteVisit > :yesterday ";
            } else if (status.equalsIgnoreCase("inactive")) {
                extraCondition += " where lastSiteVisit < :yesterday or lastSiteVisit is null ";

            }
        }
        Date yesterday = DateUtils.getYesterday();
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr + extraCondition);
        if (status != null) {
            if (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("inactive")) {
                query.setParameter("yesterday", yesterday);
            }
        }
        if (page != null) {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getCount());
        }
        List<Dealer> dealers = query.list();
        Map returnMap = new HashMap();
        returnMap.put("data", dealers);
        if (page != null) {
            returnMap.put("count", page.getCount());
        }
        returnMap.put("total", getCountDealer(countQueryStr, status));
        returnMap.put("activeDealers", getCountDealer(countQueryStr, "Active"));
        returnMap.put("inActiveDealers", getCountDealer(countQueryStr, "InActive"));
        return returnMap;
    }

    public Map getDealers(Integer dealerId, ReportPage page, String status) {
        String countQueryStr = "select count(1) count from dealer ";
        String queryStr = "from Dealer";
        if (dealerId != null && dealerId != 0) {
            queryStr += " where id = :dealerId";
        }
        String extraCondition = "";
        if (status != null) {
            if (status.equalsIgnoreCase("active")) {
                extraCondition += " where lastSiteVisit > :yesterday ";
            } else if (status.equalsIgnoreCase("inactive")) {
                extraCondition += " where lastSiteVisit < :yesterday or lastSiteVisit is null ";

            }
        }
        Date yesterday = DateUtils.getYesterday();
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr + extraCondition);
        if (status != null) {
            if (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("inactive")) {
                query.setParameter("yesterday", yesterday);
            }
        }
        if (page != null) {
            query.setFirstResult(page.getStart());
            query.setMaxResults(page.getCount());
        }
        if (dealerId != null && dealerId != 0) {
            query.setParameter("dealerId", dealerId);
        }
        List<Dealer> dealers = query.list();
        Map returnMap = new HashMap();
        returnMap.put("data", dealers);
        if (page != null) {
            returnMap.put("count", page.getCount());
        }
        returnMap.put("total", getCountDealer(countQueryStr, status));
        returnMap.put("activeDealers", getCountDealer(countQueryStr, "Active"));
        returnMap.put("inActiveDealers", getCountDealer(countQueryStr, "InActive"));
        return returnMap;
    }
}
