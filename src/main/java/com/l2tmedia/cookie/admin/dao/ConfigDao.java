/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.admin.dao;

import com.l2tmedia.cookie.dao.BaseDao;
import com.l2tmedia.cookie.model.Dealer;
import com.l2tmedia.cookie.model.DealerSite;
import com.l2tmedia.cookie.model.EmailConfig;
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
