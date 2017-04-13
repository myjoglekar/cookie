/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.utils;

import com.l2tmedia.cookie.Constants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jtermaat
 */
public class DateValidator {

    private List<String> errorMessages;
    private boolean error;
    private String inputStartDate;
    private String inputEndDate;

    public DateValidator() {
        error = false;
        errorMessages = new ArrayList<String>();
    }

    public DateValidator(String inputStartDate, String inputEndDate) {
        this();
        this.inputStartDate = inputStartDate;
        this.inputEndDate = inputEndDate;
    }

    public boolean validateHasErrors() {
        error = false;
        if (inputStartDate == null) {
            error = true;
            errorMessages.add(Constants.NULL_DATE_START);
        }
        if (inputEndDate == null) {
            error = true;
            errorMessages.add(Constants.NULL_DATE_END);
        }
        String expectedFormat = Constants.DATE_EXPECTED_FORMAT;
        if (inputStartDate != null) {
            if (!DateUtils.isValidDate(inputStartDate, expectedFormat)) {
                error = true;
                errorMessages.add(String.format(Constants.INVALID_DATE_START, inputStartDate));
            }
        }
        if (inputEndDate != null) {
            if (!DateUtils.isValidDate(inputEndDate, expectedFormat)) {
                error = true;
                errorMessages.add(String.format(Constants.INVALID_DATE_END, inputEndDate));
            }
        }
        
        Date startDate = DateUtils.getStartDate(inputStartDate);
        Date endDate = DateUtils.getEndDate(inputEndDate);

        Long timeDiff = DateUtils.dateDiffInSec(endDate, startDate);
        if (timeDiff <= 0) {
            error = true;
            errorMessages.add(String.format(Constants.END_DATE_BEFORE_START, startDate, endDate));
        }
        return error;
    }

    /**
     * @return the errorMessages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * @return the inputStartDate
     */
    public String getInputStartDate() {
        return inputStartDate;
    }

    /**
     * @param inputStartDate the inputStartDate to set
     */
    public void setInputStartDate(String inputStartDate) {
        this.inputStartDate = inputStartDate;
    }

    /**
     * @return the inputEndDate
     */
    public String getInputEndDate() {
        return inputEndDate;
    }

    /**
     * @param inputEndDate the inputEndDate to set
     */
    public void setInputEndDate(String inputEndDate) {
        this.inputEndDate = inputEndDate;
    }

}
