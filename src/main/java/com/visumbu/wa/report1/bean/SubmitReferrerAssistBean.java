/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.report1.bean;

import com.visumbu.wa.model.ActionLog;
import com.visumbu.wa.report.bean.groups.DealerReferrerGroup;
import java.util.Date;

/**
 *
 * @author user
 */
public class SubmitReferrerAssistBean {
    
    private String assistReferrerDomain;
    private String assistReferrerUrl;
    private DealerReferrerGroup dealerReferrerAssist;

    public String getAssistReferrerDomain() {
        return assistReferrerDomain;
    }

    public void setAssistReferrerDomain(String assistReferrerDomain) {
        this.assistReferrerDomain = assistReferrerDomain;
    }

    public String getAssistReferrerUrl() {
        return assistReferrerUrl;
    }

    public void setAssistReferrerUrl(String assistReferrerUrl) {
        this.assistReferrerUrl = assistReferrerUrl;
    }

    public DealerReferrerGroup getDealerReferrerAssist() {
        return dealerReferrerAssist;
    }

    public void setDealerReferrerAssist(DealerReferrerGroup dealerReferrerAssist) {
        this.dealerReferrerAssist = dealerReferrerAssist;
    }

    
    
}
