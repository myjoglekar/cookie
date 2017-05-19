/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.dao;

import com.l2tmedia.cookie.report.bean.CountBean;
import com.l2tmedia.cookie.bean.ReportPage;
import com.l2tmedia.cookie.dao.BaseDao;
import com.l2tmedia.cookie.model.Dealer;
import com.l2tmedia.cookie.model.DealerProduct;
import com.l2tmedia.cookie.model.DealerSite;
import com.l2tmedia.cookie.utils.DateUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author netphenix
 */
@Transactional
@Repository("dealerDao")
public class DealerDao extends BaseDao {

    final static Logger logger = Logger.getLogger(DealerDao.class);

    public Dealer create(Dealer dealer) {
        logger.debug("saving dealer to database: " + dealer);
        
        sessionFactory.getCurrentSession().save(dealer);
        return dealer;
    }

    public Dealer findBySiteId(String siteId) {
        logger.debug("Querying database for dealer by siteId: " + siteId);
        
        Query query = sessionFactory.getCurrentSession().createQuery("from Dealer where siteId = :siteId");
        query.setParameter("siteId", siteId);
        List<Dealer> dealers = query.list();
        if (dealers == null || dealers.isEmpty()) {
            return null;
        }
        return dealers.get(0);
    }
    
    public DealerSite findDealerSite(Integer id, String domainName) {
        logger.debug("Querying database for DealerSite: id=" + id + ", domainName=" + domainName);
        
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
        logger.debug("Querying database for dealer count by status: " + status);
        
        String extraCondition = "";
        if (status != null) {
            if (status.equalsIgnoreCase(Dealer.STATUS_ACTIVE)) {
                extraCondition += " where custom_status = :default AND last_site_visit > :yesterday  AND total_budget > 0 ";
            } else if (status.equalsIgnoreCase(Dealer.STATUS_INACTIVE)) {
                extraCondition += " where custom_status = :default AND (last_site_visit < :yesterday or last_site_visit is null)  AND total_budget > 0 AND duplicate_status = :default ";
            } else if (status.equalsIgnoreCase(Dealer.STATUS_CANCELLED)) {
                extraCondition += " where custom_status = :cancelled ";
            } else if (status.equalsIgnoreCase(Dealer.STATUS_NO_BUDGET)) {
                extraCondition += " where total_budget <= 0 AND custom_status = :default ";
            } else if (status.equalsIgnoreCase(Dealer.STATUS_DUPLICATE)) {
                extraCondition += " where custom_status = :default AND (last_site_visit < :yesterday or last_site_visit is null) AND total_budget > 0 AND duplicate_status = :duplicate ";
            }
        }
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr + extraCondition)
                .addScalar("count", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(CountBean.class));
        
        setQueryParameters(query, status, null);
        List<CountBean> count = query.list();
        return count.get(0).getCount();
    }

    public Map getDealers(ReportPage page, String status) {
        return getDealers(null, page, status);
    }

    public Map getDealers(Integer dealerId, ReportPage page, String status) {
        logger.debug("Querying database for dealer by id: " + dealerId);
        
        setBudgetsAndDuplicates();
        
        String countQueryStr = "select count(1) count from dealer ";
        String queryStr = "from Dealer where 1 = 1 ";
        if (dealerId != null && dealerId != 0) {
            queryStr += " and id = :dealerId";
        }
        String extraCondition = "";
        if (status != null) {
            if (status.equalsIgnoreCase("active")) {
                extraCondition += " and custom_status = :default AND lastSiteVisit > :yesterday AND total_budget > 0 ";
            } else if (status.equalsIgnoreCase("inactive")) {
                extraCondition += " and (lastSiteVisit < :yesterday or lastSiteVisit is null) AND custom_status = :default AND total_budget > 0 AND duplicate_status = :default ";
            } else if (status.equalsIgnoreCase("cancelled")) {
                extraCondition += " and custom_status = :cancelled ";
            } else if (status.equalsIgnoreCase("noBudget")) {
                extraCondition += " and total_budget <= 0 AND custom_status = :default ";
            } else if (status.equalsIgnoreCase("duplicate")) {
                extraCondition += " and custom_status = :default AND (last_site_visit < :yesterday or last_site_visit is null) AND total_budget > 0 AND duplicate_status = :duplicate ";
            }
        }
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr + extraCondition);
        setQueryParameters(query, status, dealerId);
        
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
        returnMap.put("activeDealers", getCountDealer(countQueryStr, Dealer.STATUS_ACTIVE));
        returnMap.put("inActiveDealers", getCountDealer(countQueryStr, Dealer.STATUS_INACTIVE));
        returnMap.put("duplicateDealers", getCountDealer(countQueryStr, Dealer.STATUS_DUPLICATE));
        returnMap.put("cancelledDealers", getCountDealer(countQueryStr, Dealer.STATUS_CANCELLED));
        returnMap.put("noBudgetDealers", getCountDealer(countQueryStr, Dealer.STATUS_NO_BUDGET));
        
        return returnMap;
    }
    
    private void setBudgetsAndDuplicates() {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("Dealer.findAll"); 
        List<Dealer> dealers = query.list();
        setDuplicates(dealers);
        
        for (Dealer dealer : dealers) {
            Double totalBudget = 0.0;
            for (DealerProduct product : dealer.getDealerProductCollection()) {
                totalBudget += product.getBudget();
            }
            dealer.setTotalBudget(totalBudget);
            sessionFactory.getCurrentSession().save(dealer);
        }
    }
    
    private void setDuplicates(List<Dealer> dealers) {
        for (Dealer dealer : dealers) {
            if (dealer.getStatus().equals(Dealer.STATUS_ACTIVE)) {
                for (Dealer otherDealer : dealers) {
                    if (!dealer.equals(otherDealer) 
                            && dealer.getWebsite().equals(otherDealer.getWebsite())
                            && otherDealer.getStatus().equals(Dealer.STATUS_INACTIVE)) {
                        otherDealer.setDuplicateStatus(Dealer.STATUS_DUPLICATE);
                    }
                }
            }
        }
        for (Dealer dealer : dealers) {
            if (!Dealer.STATUS_DUPLICATE.equals(dealer.getDuplicateStatus())) {
                dealer.setDuplicateStatus(Dealer.STATUS_DEFAULT);
            }
            if (!Dealer.STATUS_CANCELLED.equals(dealer.getCustomStatus())) {
                dealer.setCustomStatus(Dealer.STATUS_DEFAULT);
            }
        }
    }
    
    private void setQueryParameters(Query query, String status, Integer dealerId) {
        Date yesterday = DateUtils.getYesterday();
        if (dealerId != null && dealerId != 0) {
            query.setParameter("dealerId", dealerId);
        } 
        if (status != null) {
            if (status.equalsIgnoreCase(Dealer.STATUS_ACTIVE) || status.equalsIgnoreCase(Dealer.STATUS_INACTIVE) || status.equalsIgnoreCase(Dealer.STATUS_DUPLICATE)) {
                query.setParameter("yesterday", yesterday);
            }
            if (status.equalsIgnoreCase(Dealer.STATUS_ACTIVE) || status.equalsIgnoreCase(Dealer.STATUS_INACTIVE) || status.equalsIgnoreCase(Dealer.STATUS_NO_BUDGET) || status.equalsIgnoreCase(Dealer.STATUS_DUPLICATE)) {
                query.setParameter("default", Dealer.STATUS_DEFAULT);
            }
            if (status.equalsIgnoreCase(Dealer.STATUS_CANCELLED)) {
                query.setParameter("cancelled", Dealer.STATUS_CANCELLED);
            }
            if (status.equalsIgnoreCase(Dealer.STATUS_DUPLICATE)) {
                query.setParameter("duplicate", Dealer.STATUS_DUPLICATE);
            }
        }
    }
}
