/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.mail;

import com.l2tmedia.cookie.Constants;
import java.util.concurrent.BlockingQueue;
import org.apache.log4j.Logger;

/**
 *
 * @author varghees
 */
public class MailConsumer implements Runnable {

    protected BlockingQueue queue = null;

    final static Logger logger = Logger.getLogger(MailConsumer.class);

    public MailConsumer(BlockingQueue queue) {
        logger.debug("Start function of MailConsumer in MailConsumer class");
        this.queue = queue;
    }

    @Override
    public void run() {

        logger.debug("Start function of run in MailConsumer class");

        while (true) {
            if (queue.isEmpty()) {
                queue.poll();
            }

            Object obj = null;
            try {
                obj = (Object) queue.take(); // take the element
            } catch (InterruptedException ex) {

                logger.error("InterrupedException in  run function in MailConsumer class"+ex);
                ex.printStackTrace();
            }
            String status = "";
            // Send mail
            MailProperties props = (MailProperties) obj;
            switch (props.getType()) {
                case Constants.TEXT_MAIL:
                    TextMail mail = new TextMail(props);
                    status = mail.sendMail();
                    break;
                case Constants.TEXT_MAIL_WITH_ATTACHMENT:
                    TextMailWithAttachment textMailWithAttachment = new TextMailWithAttachment(props);
                    status = textMailWithAttachment.sendMail();
                    break;
                case Constants.HTML_MAIL:
                    HtmlMail htmlMail = new HtmlMail(props);
                    status = htmlMail.sendMail();
                    break;
                case Constants.HTML_WITH_EMBEDDED_IMAGE:
                    // Dummy function
                    HtmlMailWithEmbeddedImage htmlMailWithEmbeddedImage = new HtmlMailWithEmbeddedImage(props);
                    status = htmlMailWithEmbeddedImage.sendMail();
                    break;
            }
            logger.debug("End  function of run  in MailConsumer class");
        }
    }
}
