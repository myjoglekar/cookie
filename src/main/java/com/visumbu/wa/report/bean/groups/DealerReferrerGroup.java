/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.report.bean.groups;

/**
 *
 * @author user
 */
public class DealerReferrerGroup {

    private String domainName;
    private String referrerDomain;

    public DealerReferrerGroup(String domainName, String referrerDomain) {
        this.domainName = domainName;
        this.referrerDomain = referrerDomain;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getReferrerDomain() {
        return referrerDomain;
    }

    public void setReferrerDomain(String referrerDomain) {
        this.referrerDomain = referrerDomain;
    }

    @Override
    public boolean equals(Object obj) {
        DealerReferrerGroup drg = (DealerReferrerGroup) obj;
        if (drg.getDomainName().equalsIgnoreCase(domainName) && drg.getReferrerDomain().equalsIgnoreCase(referrerDomain)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return domainName.hashCode() + referrerDomain.hashCode();
    }

}
