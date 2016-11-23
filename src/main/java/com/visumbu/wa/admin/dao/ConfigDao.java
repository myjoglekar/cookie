/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.dao;

import com.visumbu.wa.dao.BaseDao;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.DealerSite;
import com.visumbu.wa.model.EmailConfig;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Transactional
@Repository("configDao")
public class ConfigDao extends BaseDao {

    public EmailConfig findByName(String configName) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("EmailConfig.findByConfigName");
        query.setParameter("configName", configName);
        List<EmailConfig> emailConfig = query.list();
        if (emailConfig == null || emailConfig.isEmpty()) {
            return null;
        }
        return emailConfig.get(0);
    }
}
