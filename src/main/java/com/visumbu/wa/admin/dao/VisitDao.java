package com.visumbu.wa.admin.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.visumbu.wa.admin.dao.*;
import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.model.Dealer;
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

//    public List<WaDealer> read() {
//        Query query = sessionFactory.getCurrentSession().createQuery("from Dealer where status is null or status != 'Deleted'");
//        return query.list();
//    }
}
