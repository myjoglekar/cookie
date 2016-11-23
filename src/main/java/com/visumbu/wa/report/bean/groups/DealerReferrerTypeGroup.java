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
public class DealerReferrerTypeGroup {

    private String domainName;
    private String referrerType;

    public DealerReferrerTypeGroup(String domainName, String referrerType) {
        this.domainName = domainName;
        this.referrerType = referrerType;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getReferrerType() {
        return referrerType;
    }

    public void setReferrerType(String referrerType) {
        this.referrerType = referrerType;
    }

    @Override
    public boolean equals(Object obj) {
        DealerReferrerTypeGroup drg = (DealerReferrerTypeGroup) obj;
        if (drg.getDomainName().equalsIgnoreCase(domainName)) {
            if (referrerType == null && drg.getReferrerType() == null) {
                return true;
            }
            if (drg.getReferrerType() != null && drg.getReferrerType().equalsIgnoreCase(referrerType)) {
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
        if (referrerType != null) {
            hasCode += referrerType.hashCode();
        }
        return hasCode;
    }

    @Override
    public String toString() {
        return "DealerReferrerGroup: {" + "domainName:" + (domainName == null ? "-" : domainName) + ", referrerType:" + (referrerType == null ? "-" : referrerType) + '}';
    }

}
