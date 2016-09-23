/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.visumbu.wa.admin.service;

import com.visumbu.wa.admin.dao.DealerDao;
import com.visumbu.wa.model.WaDealer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author netphenix
 */
@Service("dealerService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DealerService {

    @Autowired
    private DealerDao dealerDao;

    public WaDealer read(Integer id) {
        return (WaDealer) dealerDao.read(WaDealer.class, id);
    }

    public List<WaDealer> read() {
        List<WaDealer> waDealer = dealerDao.read(WaDealer.class);
        return waDealer;
    }
}
