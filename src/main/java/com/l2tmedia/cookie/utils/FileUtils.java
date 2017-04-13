/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.l2tmedia.cookie.utils;

import java.io.File;
import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class FileUtils {
    
    final static Logger logger = Logger.getLogger(FileUtils.class);

    public static Object readXML(String fileName, Class inputClass) {
        try {
            JAXBContext context = JAXBContext.newInstance(inputClass);
            Unmarshaller um = context.createUnmarshaller();
            Object obj = (Object) um.unmarshal(new FileReader(fileName));
            return obj;
        } catch (Exception ex) {
            logger.error("Error reading XML from file " + fileName, ex);
        }
        return null;
    }

    public static void writeXML(String fileName, Object object, Class inputClass) {
        try {
            // create JAXB context and instantiate marshaller
            JAXBContext context = JAXBContext.newInstance(inputClass);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to File
            m.marshal(object, new File(fileName));
            m.marshal(object, System.out);
        } catch (JAXBException ex) {
            logger.error("Error writing XML to file: fileName=" + fileName + ", inputClass=" + inputClass, ex);
        }
    }
}
