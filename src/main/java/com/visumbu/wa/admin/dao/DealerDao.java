/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.bean.ReportPage;
import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.DealerSite;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
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

    public Map getDealers(ReportPage page, String status) {
        String countQueryStr = "select count(1) count from dealer";
        String queryStr = "select * from dealer";
        String extraCondition = "";
        
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
        
        
        query.setFirstResult(page.getStart());
        query.setMaxResults(page.getCount());
        List<Dealer> dealers = query.list();
        Map returnMap = new HashMap();
        returnMap.put("data", dealers);
        return returnMap;
    }
}
