/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.mail;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author varghees
 */
public class MailQ {

    private static MailQ instance = null;
    private BlockingQueue queue = new ArrayBlockingQueue(1024);
    private MailProducer producer = null;
    private MailConsumer consumer = null;

    final static Logger logger = Logger.getLogger(MailQ.class);

    private MailQ() {
        logger.debug("Start function of MailQ in MailQ class");
        producer = new MailProducer(queue);
        consumer = new MailConsumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
        logger.debug("End  function of MailQ  in MailQ class");
    }

    public static MailQ getInstance() {
        logger.debug("Start function of getInstance in MailQ class");
        if (instance == null) {
            synchronized (MailQ.class) {
                instance = new MailQ();
            }
        }
        logger.debug("End  function of getInstance  in MailQ class");
        return instance;
    }

    public void add(Object obj) {
        logger.debug("Start function of add in MailQ class");
        producer.accept(obj);
        logger.debug("End  function of add  in MailQ class");
    }

    public int count() {
        logger.debug("Start function of count in MailQ class");
        logger.debug("End  function of count  in MailQ class");
        return queue.size();
    }

    public int remainingCapacity() {
        logger.debug("Start function of remainingCapacity in MailQ class");
        logger.debug("End  function of remainingCapacity in MailQ class");
        return queue.remainingCapacity();
    }

    public static void main(String args[]) {
        MailQ q = MailQ.getInstance();
    }
}
