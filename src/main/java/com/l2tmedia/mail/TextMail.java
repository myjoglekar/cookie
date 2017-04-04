/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.mail;

//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

/**
 *
 * @author varghees
 */
public class TextMail {

    private MailProperties props = null;

    final static Logger logger = Logger.getLogger(TextMail.class);

    public TextMail(MailProperties props) {
        logger.debug("calling a function TextMail in TextMail class");
        this.props = props;
    }

    public String sendMail() {
        logger.debug("Start function of sendMail in TextMail class");
        try {
            Email email = new SimpleEmail();
            email.setHostName(props.getHostName());
            email.setSmtpPort(props.getPort());
            email.setAuthentication(props.getAuthUser(), props.getAuthPasswd());
            //email.setSSLOnConnect(props.isSetSSLOnConnect());
            email.setFrom(props.getFrom());
            email.setSubject(props.getSubject());
            email.setMsg(props.getTxtMessage());
            email.addTo(props.getTo());
            email.addCc(props.getCc());
            return email.send();
        } catch (EmailException ex) {
            logger.error("Exception in sendMail function in TextMail class"+ex);
            ex.printStackTrace();
//            Logger.getLogger(TextMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.debug("End  function of sendMail in TextMail class");
        return "Not Sent";
    }
}
