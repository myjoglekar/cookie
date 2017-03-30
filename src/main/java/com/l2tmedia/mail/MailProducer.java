/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.mail;

import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author varghees
 */
public class MailProducer implements Runnable {

    protected BlockingQueue queue = null;

    final static Logger logger = Logger.getLogger(MailProducer.class);

    public MailProducer(BlockingQueue queue) {
        logger.debug("Start function of MailProducer in MailProducer class");
        this.queue = queue;
    }

    public void accept(Object obj) {
        logger.debug("Start function of accept in MailProducer class");
        try {
            queue.put(obj);
        } catch (InterruptedException ex) {
            logger.error("InterruptedException in MailProducer Function in MailProducer class"+ex);
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
    }
}
