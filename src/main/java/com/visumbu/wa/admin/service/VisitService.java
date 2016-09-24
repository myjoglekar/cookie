/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.service;

import com.visumbu.wa.admin.dao.DealerDao;
import com.visumbu.wa.admin.dao.VisitDao;
import com.visumbu.wa.bean.VisitInputBean;
import com.visumbu.wa.model.VisitLog;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.DealerSite;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Service("visitService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VisitService {

    @Autowired
    private VisitDao visitDao;
    @Autowired
    private DealerDao dealerDao;

    public Dealer read(Integer id) {
        return (Dealer) visitDao.read(Dealer.class, id);
    }

    public List<Dealer> read() {
        List<Dealer> waDealer = visitDao.read(Dealer.class);
        return waDealer;
    }

    public void newVisit() {

    }

    public VisitLog saveLog(VisitInputBean visitBean) {
        VisitLog visitLog = new VisitLog();
        BeanUtils.copyProperties(visitBean, visitLog);
        visitDao.create(visitLog);
        updateDealerDetails(visitBean);
        return visitLog;
    }
    
    private void updateDealerDetails(VisitInputBean visitBean) {
        Dealer dealer = dealerDao.findBySiteId(visitBean.getSiteId());
        DealerSite dealerSite = dealerDao.findDealerSite(dealer.getId(), visitBean.getDomainName());
        if(dealerSite == null) {
            dealerSite = new DealerSite();
            dealerSite.setDealerId(dealer);
            dealerSite.setSiteName(visitBean.getDomainName());
            dealerDao.create(dealerSite);
        }
        dealer.setLastSiteVisit(new Date());
        dealerDao.update(dealer);
    }
}
