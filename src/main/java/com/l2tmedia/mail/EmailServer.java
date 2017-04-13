/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.mail;

import com.l2tmedia.cookie.model.EmailConfig;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author vsamraj
 */
public class EmailServer {

    private String emailAddressTo;
    private String emailAddressCc;
    private String msgSubject;
    private String msgText;
    private String[] attachFiles;

    private String userName = "varghees";   //User name of the Goole(gmail) account
    private String password = "";  //Password of the Goole(gmail) account
    private String fromAddress = "varghees@gmail.com";  //From addresss
    private String hostName;
    private Integer port;

    public static final int UNKNOWN = -1;
    public static final int TEXT_MAIL = 300;
    public static final int HTML_MAIL = 301;
    public static final int TEXT_MAIL_WITH_ATTACHMENT = 302;
    public static final int HTML_WITH_ATTACHMENT = 303;
    public static final int HTML_WITH_EMBEDDED_IMAGE = 304;

    final static Logger logger = Logger.getLogger(EmailServer.class);

    public EmailServer(String test) {        
        this.hostName = "mail.nmsworks.co.in";
        this.port = 25;
    }

    public EmailServer(EmailConfig emailConfig) {
        this.userName = emailConfig.getUsername();
        this.password = emailConfig.getPassword();
        this.hostName = emailConfig.getServerIp();
        this.port = emailConfig.getServerPort();
    }

    public void setFrom(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void createAndSendEmail(String emailAddressTo, String emailAddressCc,
            String msgSubject, String msgText, String[] attachments) {        
        String[] emailArray = emailAddressTo.split(",");
        List<String> emailArrayList = new ArrayList<>();
        for (int i = 0; i < emailArray.length; i++) {
            String email = emailArray[i];
            if (email != null && email.contains("@")) {
                emailArrayList.add(email);
            }
        }

        emailAddressTo = StringUtils.join(emailArrayList, ',');
        if (emailAddressCc == null) {
            emailAddressCc = "";
        }
        emailArray = emailAddressCc.split(",");
        emailArrayList = new ArrayList<>();
        for (int i = 0; i < emailArray.length; i++) {
            String email = emailArray[i];
            if (email != null && email.contains("@")) {
                emailArrayList.add(email);
            }
        }

        emailAddressCc = StringUtils.join(emailArrayList, ',');

        logger.debug("Sending email to " + emailAddressTo + " CC " + emailAddressCc + " Subject " + msgSubject);
        if (emailAddressTo == null || emailAddressTo.isEmpty()) {
            if (emailAddressCc == null || emailAddressCc.isEmpty()) {
                return;
            }
            this.emailAddressTo = fromAddress;
        }
        this.emailAddressTo = emailAddressTo;
        if (emailAddressCc != null && !emailAddressCc.isEmpty()) {
            this.emailAddressCc = emailAddressCc;
        }
        this.msgSubject = msgSubject;
        this.msgText = msgText;
        if (attachments != null) {
            this.attachFiles = attachments;
        }
        sendEmailMessage();

    }

    public void createAndSendEmail(String emailAddressTo, String emailAddressCc,
            String msgSubject, String msgText) {
        logger.debug("Creating and sending email: emailAddressTo=" + emailAddressTo + ", emailAddressCc=" + emailAddressCc + ", msgSubject=" + msgSubject);
        this.createAndSendEmail(emailAddressTo, emailAddressCc, msgSubject, msgText, null);
    }

    private void sendEmailMessage() {
        logger.debug("Start function of sendEmailMessage in EmailServer class");
        logger.debug("TO --> " + emailAddressTo);
        logger.debug("CC --> " + emailAddressCc);
        logger.debug("Subject --> " + msgSubject);
        logger.debug("Text --> " + msgText);
        logger.debug("attachments --> " + attachFiles);

        MailQ mailq = MailQ.getInstance();
        MailProperties props = new MailProperties();
        props.setType(HTML_MAIL);
        props.setHostName(this.hostName);
        props.setAuthUser(this.userName);
        props.setPort(this.port);
        props.setAuthPasswd(this.password);
        props.setFrom(this.fromAddress);
        props.setTo(emailAddressTo);
        if (attachFiles != null && attachFiles.length > 0) {
            List<MailAttachment> mailAttachments = new ArrayList<>();
            for (int i = 0; i < attachFiles.length; i++) {
                String attachFile = attachFiles[i];
                logger.debug("Attaching file " + attachFile);
                MailAttachment mailAttachment = new MailAttachment();
                mailAttachment.setAttachmentPath(attachFile);
                mailAttachment.setAttachName(Paths.get(attachFile).getFileName().toString());
                mailAttachments.add(mailAttachment);
            }
            props.setAttachment(mailAttachments);
        }
        if (emailAddressCc != null && !emailAddressCc.isEmpty()) {
            props.setCc(emailAddressCc);
        }

        props.setSubject(msgSubject);
        props.setHtmlMessage(msgText);

        mailq.add(props);
        if (1 == 1) {
            return;
        }

        if (userName.equals("")) {
            return;
        }
    }

    public void setEmailAddressTo(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
    }

    public void setSubject(String subject) {
        this.msgSubject = subject;
    }

    public void setMessageText(String msgText) {
        this.msgText = msgText;
    }
}
