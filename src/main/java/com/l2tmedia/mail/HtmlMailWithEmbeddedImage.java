/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.mail;

import org.apache.log4j.Logger;

/**
 *
 * @author varghees
 */
public class HtmlMailWithEmbeddedImage {

    final static Logger logger = Logger.getLogger(HtmlMailWithEmbeddedImage.class);

    private MailProperties props = null;

    public HtmlMailWithEmbeddedImage(MailProperties props) {
        logger.debug("Calling a function of HtmlMailWithEmbeddedImage with properties="+props);
        this.props = props;
    }

    public String sendMail() {
        // TODO
        logger.debug("Calling a function of sendMail");
        return "Not Send";
    }
}
