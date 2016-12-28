/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.report1.bean;

/**
 *
 * @author user
 */
public class VisitDetailsBean {
    private String visitId;
    private Integer numberOfTimes;
    private Long duration;

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Integer getNumberOfTimes() {
        return numberOfTimes;
    }

    public void setNumberOfTimes(Integer numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
