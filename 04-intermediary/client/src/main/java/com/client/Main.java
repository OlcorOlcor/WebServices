package com.client;

import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPBodyElement;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;


public class Main {
    public static void main(String[] args) {
        try {
            SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
            SOAPConnection soapc = soapcf.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage soapm = mf.createMessage();
            
            SOAPPart soapp = soapm.getSOAPPart();
            SOAPEnvelope soape = soapp.getEnvelope();
            SOAPBody soapb = soape.getBody();

            soape.getHeader().detachNode();

            QName name = new QName("http://bowling.com/", "confirmRequest");
            SOAPElement soapel = soapb.addBodyElement(name);
            String endpoint = "http://0.0.0.0:8000/ConfirmationService";
            SOAPMessage response = soapc.call(soapm, endpoint);
            soapc.close();
            
            SOAPBody responseBody = response.getSOAPBody();
            if (responseBody.hasFault()) {
                System.out.println("SOAP fault: " + responseBody.getFault().getFaultString());
            } else {
                System.out.println("success");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}