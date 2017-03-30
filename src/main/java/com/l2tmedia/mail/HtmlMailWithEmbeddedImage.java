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
        logger.debug("Start function of HtmlMailWithEmbeddedImage in HtmlMailWithEmbeddedImage class");
        this.props = props;
        logger.debug("End  function of HtmlMailWithEmbeddedImage  in HtmlMailWithEmbeddedImage class");
    }

    public String sendMail() {
        // TODO
        logger.debug("Start function of sendMail in HtmlMailWithEmbeddedImage class");
        logger.debug("End  function of semdMail  in HtmlMailWithEmbeddedImage class");
        return "Not Send";
    }
}
