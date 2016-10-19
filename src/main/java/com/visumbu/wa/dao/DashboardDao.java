
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.dao;

import com.visumbu.wa.dashboard.bean.DealerVisitBean;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jp
 */
@Transactional
@Repository("groupDao")
public class DashboardDao extends BaseDao {

    public List getTopDealersByVisit(Date startDate, Date endDate) {
        String queryStr = "select dealer.dealer_name dealerName, count(distinct(session_id)) totalSiteVisit, count(1) totalPageVisit, count(distinct(fingerprint)) uniqueUserCount from visit_log, dealer where visit_log.site_id = dealer.id group by 1";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr).setResultTransformer(Transformers.aliasToBean(DealerVisitBean.class));
        return query.list();
    }

}
