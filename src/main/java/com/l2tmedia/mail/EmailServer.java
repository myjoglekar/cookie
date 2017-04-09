/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.mail;

import com.l2tmedia.cookie.model.EmailConfig;
import com.l2tmedia.cookie.model.Dealer;
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

    private String USER_NAME = "varghees";   //User name of the Goole(gmail) account
    private String PASSWORD = "";  //Password of the Goole(gmail) account
    private String FROM_ADDRESS = "varghees@gmail.com";  //From addresss
    private String HOST_NAME;
    private Integer PORT;

    public static final int UNKNOWN = -1;
    public static final int TEXT_MAIL = 300;
    public static final int HTML_MAIL = 301;
    public static final int TEXT_MAIL_WITH_ATTACHMENT = 302;
    public static final int HTML_WITH_ATTACHMENT = 303;
    public static final int HTML_WITH_EMBEDDED_IMAGE = 304;

    final static Logger logger = Logger.getLogger(EmailServer.class);

    public EmailServer(String test) {
        logger.debug("Calling a function of EmailServer"+test);
        
        this.HOST_NAME = "mail.nmsworks.co.in";
        this.PORT = 25;
    }

    public EmailServer(EmailConfig emailConfig) {
        logger.debug("Calling function of EmailServer configuration"+emailConfig);
        this.USER_NAME = emailConfig.getUsername();
        this.PASSWORD = emailConfig.getPassword();
        this.HOST_NAME = emailConfig.getServerIp();
        this.PORT = emailConfig.getServerPort();
    }

    public void setFrom(String fromAddress) {
        logger.debug("Calling function of set from address"+fromAddress);
        FROM_ADDRESS = fromAddress;
    }

    public static void main(String[] args) {
        EmailServer email = new EmailServer("test");
        //Sending test email
        String[] attachFiles = {"d:\\work\\SeacomFiles\\1425457085123-1-TT USEr.txt", "d:\\work\\SeacomFiles\\1425464768539-1-logo.png"};
        email.createAndSendEmail("varghees@gmail.com", "varghees@netphenix.com", "Test email subject",
                "<b>Congratulations </b>!!! \nThis is test email sent by java class.");
    }

    public void createAndSendEmail(String emailAddressTo, String emailAddressCc,
            String msgSubject, String msgText, String[] attachments) {
        
        logger.debug("Calling a function of createAndSendEmail with attachments with "
                + "To="+emailAddressTo+" CC="+emailAddressCc+" subject="+msgSubject+" Text= "+msgText+
                " attachments="+attachments);
        
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
            this.emailAddressTo = FROM_ADDRESS;
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
        logger.debug("Calling a function of createAndSendEmail with attachments with "
                + "To="+emailAddressTo+" CC="+emailAddressCc+" subject="+msgSubject+" Text= "+msgText);
        this.createAndSendEmail(emailAddressTo, emailAddressCc, msgSubject, msgText, null);
    }

    private void sendEmailMessage() {
        logger.debug("Calling a function of sendEmailMessage");
        logger.debug("TO --> " + emailAddressTo);
        logger.debug("CC --> " + emailAddressCc);
        logger.debug("Subject --> " + msgSubject);
        logger.debug("Text --> " + msgText);
        logger.debug("attachments --> " + attachFiles);

        MailQ mailq = MailQ.getInstance();
        MailProperties props = new MailProperties();
        props.setType(HTML_MAIL);
        props.setHostName(this.HOST_NAME);
        props.setAuthUser(this.USER_NAME);
        props.setPort(this.PORT);
        props.setAuthPasswd(this.PASSWORD);
        props.setFrom(this.FROM_ADDRESS);
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
        if (USER_NAME.equals("")) {
            return;
        }
    }

    public void setEmailAddressTo(String emailAddressTo) {
        logger.debug("Calling function of setEmailAddressTo where To address="+emailAddressTo);
        this.emailAddressTo = emailAddressTo;
    }

    public void setSubject(String subject) {
        logger.debug("Calling function of setSubject where Subject="+subject);
        this.msgSubject = subject;
    }

    public void setMessageText(String msgText) {
        logger.debug("Calling function of setMessageText where Text="+msgText);
        this.msgText = msgText;
    }
}
