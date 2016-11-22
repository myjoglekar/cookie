package com.visumbu.wa.admin.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.visumbu.wa.admin.dao.*;
import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.DealerSite;
import com.visumbu.wa.model.UniqueVisit;
import com.visumbu.wa.model.VisitLog;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Transactional
@Repository("visitDao")
public class VisitDao extends BaseDao {

    public UniqueVisit getUniqueIdByFingerPrint(String fingerPrint) {
        if (fingerPrint == null) {
            return null;
        }
        Query query = sessionFactory.getCurrentSession().getNamedQuery("UniqueVisit.findByFingerPrint");
        query.setParameter("fingerPrint", fingerPrint);

        List<UniqueVisit> uniqueVisits = query.list();
        if (uniqueVisits == null || uniqueVisits.isEmpty()) {
            return null;
        }
        return uniqueVisits.get(0);
    }

//    public List<Dealer> read() {
//        Query query = sessionFactory.getCurrentSession().createQuery("from Dealer where status is null or status != 'Deleted'");
//        return query.list();
//    }
    public UniqueVisit getUniqueIdByVisitId(String visitId) {
        if (visitId == null) {
            return null;
        }
        Query query = sessionFactory.getCurrentSession().getNamedQuery("UniqueVisit.findByVisitId");
        query.setParameter("visitId", visitId);

        List<UniqueVisit> uniqueVisits = query.list();
        if (uniqueVisits == null || uniqueVisits.isEmpty()) {
            return null;
        }
        return uniqueVisits.get(0);
    }

    public UniqueVisit getUniqueIdBySessionId(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        Query query = sessionFactory.getCurrentSession().getNamedQuery("UniqueVisit.findBySessionId");
        query.setParameter("sessionId", sessionId);

        List<UniqueVisit> uniqueVisits = query.list();
        if (uniqueVisits == null || uniqueVisits.isEmpty()) {
            return null;
        }
        return uniqueVisits.get(0);
    }

    public String getReferrerUrl(String visitId) {
        String queryStr = "from VisitLog where visitId = :visitId order by visitTime";
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
        query.setParameter("visitId", visitId);
        query.setMaxResults(1);
        List<VisitLog> visits = query.list();
        if (visits == null || visits.isEmpty()) {
            return null;
        }
        return visits.get(0).getReferrerUrl();
    }

}
