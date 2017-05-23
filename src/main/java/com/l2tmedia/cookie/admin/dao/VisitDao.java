package com.l2tmedia.cookie.admin.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.l2tmedia.cookie.dao.BaseDao;
import com.l2tmedia.cookie.model.UniqueVisit;
import com.l2tmedia.cookie.model.VisitLog;
import com.l2tmedia.cookie.model.VisitLogReport;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

/**
 *
 * @author netphenix
 */
@Transactional
@Repository("visitDao")
public class VisitDao extends BaseDao {

    final static Logger logger = Logger.getLogger(VisitDao.class);

    public UniqueVisit getUniqueIdByFingerPrint(String fingerPrint) {
        logger.debug("Querying database for unique id by fingerprint: fingerprint=" + fingerPrint);

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
        logger.debug("Querying database to get uniqueId by visitId: visitId=" + visitId);

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
        logger.debug("Querying database to get uniqueId by sessionId: sessionId=" + sessionId);

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

    public VisitLog getReferrerDetailsCurrent(String visitId) {
        logger.debug("Querying datbase to get referrer url: visitId=" + visitId);
        System.out.println("From Current");
        String queryStr = "from VisitLog where visitId = :visitId order by visitTime";
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
        query.setParameter("visitId", visitId);
        query.setMaxResults(1);
        List<VisitLog> visits = query.list();
        if (visits == null || visits.isEmpty()) {
            return null;
        }
        VisitLog visitLog = visits.get(0);
        System.out.println(" Visit ID " + visitLog.getVisitId() + " Visit Time " + visitLog.getVisitTime());
        return visitLog;
    }

    public VisitLogReport getReferrerDetailsFromHistory(String visitId) {
        logger.debug("Querying datbase to get referrer url: visitId=" + visitId);
        System.out.println("From History");
        String queryStr = "from VisitLogReport where visitId = :visitId order by visitTime";
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
        query.setParameter("visitId", visitId);
        query.setMaxResults(1);
        List<VisitLogReport> visits = query.list();
        if (visits == null || visits.isEmpty()) {
            return null;
        }
        VisitLogReport logReport = visits.get(0);
        System.out.println(" Visit ID " + logReport.getVisitId() + " Visit Time " + logReport.getVisitTime());

        return logReport;
    }

    public String getReferrerUrl(String visitId, Integer visitCount) {
        logger.debug("Querying datbase to get referrer url: visitId=" + visitId + ", visitCount=" + visitCount);

        String queryStr = "from VisitLog where visitId = :visitId and visitCount = :visitCount order by visitTime";
        Query query = sessionFactory.getCurrentSession().createQuery(queryStr);
        query.setParameter("visitId", visitId);
        query.setParameter("visitCount", visitCount);
        query.setMaxResults(1);
        List<VisitLog> visits = query.list();
        if (visits == null || visits.isEmpty()) {
            return null;
        }
        return visits.get(0).getReferrerUrl();
    }

    public Date getFirstVisitTime(String visitId) {
        logger.debug("Querying database for first visit time by visitId: visitId=" + visitId);

        String queryStr = "select min(visit_time) visitTime from visit_log where visit_id = :visitId";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("visitTime", DateType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitLog.class));
        query.setParameter("visitId", visitId);
        List<VisitLog> visits = query.list();
        if (visits == null || visits.isEmpty()) {
            return null;
        }
        return visits.get(0).getVisitTime();
    }

    public Date getSessionVisitTime(String visitId, Integer visitCount) {
        logger.debug("Querying database for session visit time: visitId=" + visitId + ", visitCount=" + visitCount);

        String queryStr = "select min(visit_time) visitTime from visit_log where visit_id = :visitId and visit_count = :visitCount";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryStr)
                .addScalar("visitTime", DateType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(VisitLog.class));
        query.setParameter("visitId", visitId);
        query.setParameter("visitCount", visitCount);
        List<VisitLog> visits = query.list();
        if (visits == null || visits.isEmpty()) {
            return null;
        }
        return visits.get(0).getVisitTime();
    }

}
