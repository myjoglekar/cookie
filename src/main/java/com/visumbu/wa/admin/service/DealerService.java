/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.service;

import com.visumbu.mail.EmailServer;
import com.visumbu.wa.admin.dao.ConfigDao;
import com.visumbu.wa.admin.dao.DealerDao;
import com.visumbu.wa.bean.DealerInputBean;
import com.visumbu.wa.bean.ReportPage;
import com.visumbu.wa.model.Dealer;
import com.visumbu.wa.model.EmailConfig;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Service("dealerService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DealerService {

    @Autowired
    private DealerDao dealerDao;
    @Autowired
    private ConfigDao configDao;

    public Dealer create(Dealer dealer) {
        EmailConfig emailConfig = new EmailConfig();
        emailConfig = configDao.findByName("default");
        EmailServer emailServer = null;
        if (emailConfig != null) {
            emailServer = new EmailServer(emailConfig);
        }

        if (dealer.getDealerName() != null) {
            if (dealer.getCommunicationEmail() != null || dealer.getEmail() != null) {
                // send email

                String message = "Dealer Name cannot be null";
                if (emailServer != null) {
                    emailServer.createAndSendEmail(dealer.getCommunicationEmail() != null ? dealer.getCommunicationEmail() : dealer.getEmail(), dealer.getEmail() != null ? dealer.getEmail() : dealer.getCommunicationEmail(), "Invalid Dealer", message);
                }
            }
            //return null;
        }
        Dealer createdDealer = dealerDao.create(dealer);
        String scriptMessage = "<script src=\"//ec2-52-88-45-181.us-west-2.compute.amazonaws.com:8080/webanalytics/fingerprint2.js\"></script>\n"
                + "<script type=\"text/javascript\">\n"
                + "var _paq = _paq || [];\n"
                + "_paq.push(['trackPageView']);\n"
                + "_paq.push(['enableLinkTracking']);\n"
                + "(function () {\n"
                + "    var u = \"//ec2-52-88-45-181.us-west-2.compute.amazonaws.com:8080/webanalytics/\";\n"
                + "    _paq.push(['setTrackerUrl', u + '/admin/wa']);\n"
                + "    _paq.push(['setSiteId', '" + createdDealer.getId() + "']);\n"
                + "    var d = document, g = d.createElement('script'), s = d.getElementsByTagName('script')[0];\n"
                + "    g.type = 'text/javascript';\n"
                + "    g.async = true;\n"
                + "    g.defer = true;\n"
                + "    g.src = u + 'wa.js';\n"
                + "    s.parentNode.insertBefore(g, s);\n"
                + "})();\n"
                + "</script>";

        String subject = "Script to deploy for dealer " + dealer.getDealerName();
        if (emailServer != null) {
            emailServer.createAndSendEmail(dealer.getCommunicationEmail() != null ? dealer.getCommunicationEmail() : dealer.getEmail(), dealer.getEmail() != null ? dealer.getEmail() : dealer.getCommunicationEmail(), subject, scriptMessage);
        }
        return createdDealer;
    }

    public Dealer update(Dealer dealer) {
        return (Dealer) dealerDao.update(dealer);
    }

    public Dealer read(Integer id) {
        return (Dealer) dealerDao.read(Dealer.class, id);
    }

    public List<Dealer> read() {
        List<Dealer> dealer = dealerDao.read(Dealer.class);
        return dealer;
    }

    public Dealer create(DealerInputBean dealer) {
        System.out.println(dealer.toString());
        Dealer dbDealer = new Dealer();
        dbDealer.setDealerName(dealer.getDealerName());
        dbDealer.setCreatedTime(new Date());
        dbDealer.setWebsite(dealer.getWebsite());
        dbDealer.setDealerAddress(dealer.getDealerAddress());
        dbDealer.setDealerCity(dealer.getDealerCity());
        dbDealer.setDealerState(dealer.getDealerState());
        dbDealer.setDealerRefId(dealer.getDealerRefId());
        dbDealer.setDealerZip(dealer.getDealerZip());
        dbDealer.setId(dealer.getId());
        dbDealer.setFirstContractTime(dealer.getFirstContractTime());
        dbDealer.setCommunicationEmail(dealer.getCommunicationEmail());
        dbDealer.setEmail(dealer.getEmail());
        dbDealer.setOemName(dealer.getOemName());
        Dealer newDealer = create(dbDealer);
        newDealer.setSiteId(newDealer.getId() + "");
        update(newDealer);
        return newDealer;
    }

    public Map getDealers(ReportPage page, String status) {
        return dealerDao.getDealers(page, status);
    }
}
