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
public class DealerReferrerDomainGroup {

    private String domainName;
    private String referrerDomain;

    public DealerReferrerDomainGroup(String domainName, String referrerDomain) {
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
        DealerReferrerDomainGroup drg = (DealerReferrerDomainGroup) obj;
        if (drg.getDomainName().equalsIgnoreCase(domainName)) {
            if (referrerDomain == null && drg.getReferrerDomain() == null) {
                return true;
            }
            if (drg.getReferrerDomain() != null && drg.getReferrerDomain().equalsIgnoreCase(referrerDomain)) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hasCode = 0;
        if (domainName != null) {
            hasCode += domainName.hashCode();
        }
        if (referrerDomain != null) {
            hasCode += referrerDomain.hashCode();
        }
        return hasCode;
    }

    @Override
    public String toString() {
        return "DealerReferrerGroup: {" + "domainName:" + (domainName == null ? "-" : domainName) + ", referrerDomain:" + (referrerDomain == null ? "-" : referrerDomain) + '}';
    }

}
