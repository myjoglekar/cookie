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
        logger.debug("Calling a function of MailProducer where queue="+queue);
        this.queue = queue;
    }

    public void accept(Object obj) {
        try {
            queue.put(obj);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
    }
}
