package com.epam.fivethreads.decorator.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TextField implements Element {
    private static final Logger LOG = Logger.getLogger(TextField.class);
    protected WebElement wrappedElement;
    private String name;

    protected TextField(WebElement wrappedElement) {
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

    public void click() {
        wrappedElement.click();
    }

    public void type(Keys keys) {
        wrappedElement.sendKeys(keys);
        LOG.info(String.format("Text fiels %s type()", name));
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
