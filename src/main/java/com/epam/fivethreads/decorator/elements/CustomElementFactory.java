package com.epam.fivethreads.decorator.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class CustomElementFactory {
	private static final Logger LOG = Logger.getLogger(CustomElementFactory.class);

	public <T extends Element> T create(Class<T> clazz, WebElement wrappedElement) {
		try {
			return (T) clazz.getDeclaredConstructor(WebElement.class).newInstance(wrappedElement);
		} catch (Exception e) {
			LOG.info(" ---- Exception in CustomElementFactory.create() ---- " + e.toString());
			return null;
		}

	}
}
