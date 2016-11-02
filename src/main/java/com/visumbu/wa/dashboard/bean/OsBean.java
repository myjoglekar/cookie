/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.dashboard.bean;

/**
 *
 * @author netphenix
 */
public class OsBean {
    private String os;
    private Integer visitCount;
    private Integer uniqueUserCount;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getUniqueUserCount() {
        return uniqueUserCount;
    }

    public void setUniqueUserCount(Integer uniqueUserCount) {
        this.uniqueUserCount = uniqueUserCount;
    }
    
    
}
