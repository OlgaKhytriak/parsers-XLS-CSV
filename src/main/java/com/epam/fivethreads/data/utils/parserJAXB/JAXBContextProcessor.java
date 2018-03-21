package com.epam.fivethreads.data.utils.parserJAXB;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBContextProcessor {
	private JAXBContext jaxbContext;

	public JAXBContextProcessor(Class<?>[] clazzes){
		jaxbContext=null;
		try {
			jaxbContext = JAXBContext.newInstance(clazzes);
		} catch (JAXBException e) {
			e.printStackTrace();
			System.out.println("Exceprion in constructor JAXBContextProcessor()");
		}
	}

	public <T> T convertXMLToObject(Class<T> clazz, File file) {
		Object objectromXML = null;
		try {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			objectromXML = unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			System.out.println("Exceprion in  Object2XmlConverter.load()");
			e.printStackTrace();
		}

		return (T) objectromXML;
	}

	public <T> void convertObjectToXML(File file, T object) {
		System.out.println(object.getClass().getName());
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(object, file);
		} catch (JAXBException e) {
			System.out.println("Exception in Object2XmlConverter.save");
			e.printStackTrace();
		}

	}

}
