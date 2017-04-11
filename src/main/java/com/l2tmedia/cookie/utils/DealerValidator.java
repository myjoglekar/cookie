/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.utils;

import com.l2tmedia.cookie.Constants;
import com.l2tmedia.cookie.bean.DealerInputBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jtermaat
 */
public class DealerValidator {

    private List<String> errorMessages;
    private boolean error;
    private DealerInputBean dealer;

    public DealerValidator() {
        error = false;
        errorMessages = new ArrayList<String>();
    }

    public DealerValidator(DealerInputBean dealer) {
        this();
        this.dealer = dealer;
    }

    public void validate() {
        if (dealer.getDealerName() == null || dealer.getDealerName().isEmpty()) {
            error = true;
            errorMessages.add(String.format(Constants.ERROR_DEALER_MANDATORY_FIELDS, "Dealer Name") + dealer.toString());
        }
        if (dealer.getWebsite() == null || dealer.getWebsite().isEmpty()) {
            error = true;
            errorMessages.add(String.format(Constants.ERROR_DEALER_MANDATORY_FIELDS, "Dealer Website") + dealer.toString());
        }
        if (dealer.getDealerRefId() == null || dealer.getDealerRefId().isEmpty()) {
            error = true;
            errorMessages.add(String.format(Constants.ERROR_DEALER_MANDATORY_FIELDS, "Dealer Id") + dealer.toString());
        }
        if (dealer == null) {
            error = true;
            errorMessages.add(Constants.ERROR_JSON_PARSE);
        }
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
     * @return the dealer
     */
    public DealerInputBean getDealer() {
        return dealer;
    }

    /**
     * @param dealer the dealer to set
     */
    public void setDealer(DealerInputBean dealer) {
        this.dealer = dealer;
    }
}