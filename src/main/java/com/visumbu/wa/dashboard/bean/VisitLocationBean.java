/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.dashboard.bean;

/**
 *
 * @author user
 */
public class VisitLocationBean {

    private String city;
    private String country;
    private Integer visitCount;
    private Double visitPercent;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Double getVisitPercent() {
        return visitPercent;
    }

    public void setVisitPercent(Double visitPercent) {
        this.visitPercent = visitPercent;
    }
}
