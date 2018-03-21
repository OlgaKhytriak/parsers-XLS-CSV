package com.epam.fivethreads.decorator.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class InputTextField implements Element {
    private static final Logger LOG = Logger.getLogger(InputTextField.class);
    protected WebElement wrappedElement;
    private String name;

    protected InputTextField(WebElement wrappedElement) {
        this.wrappedElement = wrappedElement;
    }

    public void type(String text) {
        wrappedElement.sendKeys(text);
        LOG.info(String.format("Text fiels %s type()", name));
    }

    public void clear() {
        wrappedElement.clear();
    }

    public void clearAndType(String text) {
        clear();
        type(text);
    }

    public boolean isDisplayed() {
        return wrappedElement.isDisplayed();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
