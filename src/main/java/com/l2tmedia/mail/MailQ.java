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
        logger.debug("Calling function of MailQ");
        producer = new MailProducer(queue);
        consumer = new MailConsumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

    public static MailQ getInstance() {
        logger.debug("Calling function of getInstance");
        if (instance == null) {
            synchronized (MailQ.class) {
                instance = new MailQ();
            }
        }
        return instance;
    }

    public void add(Object obj) {
        logger.debug("Calling function of add where object="+obj);
        producer.accept(obj);
    }

    public int count() {
        logger.debug("Calling function of count");
        return queue.size();
    }

    public int remainingCapacity() {
        logger.debug("Calling function of remainingCapacity");
        return queue.remainingCapacity();
    }

    public static void main(String args[]) {
        MailQ q = MailQ.getInstance();
    }
}
