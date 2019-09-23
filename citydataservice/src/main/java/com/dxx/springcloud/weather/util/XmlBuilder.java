package com.dxx.springcloud.weather.util;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;



public class XmlBuilder {
	public static Object xmlStrToObject(Class<?>clazz, String xmlStr) throws Exception{
		Object xmlObject =null;
		Reader reader =null;
		
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshell = context.createUnmarshaller();
		
		reader = new StringReader(xmlStr);
		xmlObject = unmarshell.unmarshal(reader);
		
		if(null!=reader) {
			reader.close();
		}
		return xmlObject;
	}
}
