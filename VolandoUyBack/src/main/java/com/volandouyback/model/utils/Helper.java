package com.volandouyback.model.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


public class Helper {
	public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) throws Exception {
	        GregorianCalendar calendar = new GregorianCalendar();
	        calendar.setTime(date);
	        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
	    }
	public static Date convertXMLGregorianCalendarToDate(XMLGregorianCalendar xmlCalendar) {
		    if (xmlCalendar == null) {
		        return null;
		    }
		    return xmlCalendar.toGregorianCalendar().getTime();
		}
	
	public static String getProperty(String key) {
    	String user = getUsername();
    	String filePath = "/ens/home01/"+user.charAt(0)+"/"+user+"/.VolandoUy/.properties";
    	System.out.println(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                return line;
            }
        } catch (IOException e) {
        	System.out.println("No se encontró el archivo");
        }
        return "http://localhost:9128/Publicador?wsdl";
    }
	
	private static String getUsername() {
    	String username = System.getenv("USER");
        if (username == null) {
            username = System.getenv("LOGNAME");
        }

        if (username != null) {
            return username;
        } else {
            return "sebastiano.benato";
        }
    }
}