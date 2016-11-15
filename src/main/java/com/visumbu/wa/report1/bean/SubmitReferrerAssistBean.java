/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.report1.bean;

import com.visumbu.wa.model.ActionLog;
import com.visumbu.wa.report.bean.groups.DealerReferrerDomainGroup;
import com.visumbu.wa.report.bean.groups.DealerReferrerTypeGroup;
import java.util.Date;

/**
 *
 * @author user
 */
public class SubmitReferrerAssistBean {
    
    private String assistReferrerDomain;
    private String assistReferrerUrl;
    private String assistReferrerType;
    private DealerReferrerDomainGroup dealerReferrerAssist;
    private DealerReferrerTypeGroup dealerReferrerTypeAssist;

    public String getAssistReferrerType() {
        return assistReferrerType;
    }

    public void setAssistReferrerType(String assistReferrerType) {
        this.assistReferrerType = assistReferrerType;
    }
    
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

    public DealerReferrerDomainGroup getDealerReferrerAssist() {
        return dealerReferrerAssist;
    }

    public void setDealerReferrerAssist(DealerReferrerDomainGroup dealerReferrerAssist) {
        this.dealerReferrerAssist = dealerReferrerAssist;
    }

    public DealerReferrerTypeGroup getDealerReferrerTypeAssist() {
        return dealerReferrerTypeAssist;
    }

    public void setDealerReferrerTypeAssist(DealerReferrerTypeGroup dealerReferrerTypeAssist) {
        this.dealerReferrerTypeAssist = dealerReferrerTypeAssist;
    }
    
    
}
