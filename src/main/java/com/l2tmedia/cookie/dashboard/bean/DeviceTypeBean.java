/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.dashboard.bean;

/**
 *
 * @author user
 */
public class DeviceTypeBean {
    private String deviceType;
    private Integer visitCount;
    private Integer uniqueUserCount;
    private Double visitPercent;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    public Double getVisitPercent() {
        return visitPercent;
    }

    public void setVisitPercent(Double visitPercent) {
        this.visitPercent = visitPercent;
    }
    
    
}
