/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.DealerSite;
import java.util.List;
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

    public Dealer findBySiteId(String siteId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Dealer where status is null or status != 'Deleted'");
        List<Dealer> dealers = query.list();
        if (dealers == null || dealers.isEmpty()) {
            return null;
        }
        return dealers.get(0);
    }

//    public List<Dealer> read() {
//        Query query = sessionFactory.getCurrentSession().createQuery("from Dealer where status is null or status != 'Deleted'");
//        return query.list();
//    }
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
}
