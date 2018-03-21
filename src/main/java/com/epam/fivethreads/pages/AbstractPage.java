package com.epam.fivethreads.pages;

import static com.epam.fivethreads.constant.Constant.ALERT_WAIT_TIME;

import com.epam.fivethreads.driver.SafeThreadDriverCreator;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.fivethreads.decorator.CustomFieldDecorator;


public abstract class AbstractPage {
    private static final Logger LOG = Logger.getLogger(AbstractPage.class);
    protected final WebDriver driver;

    public AbstractPage() {
        this.driver = SafeThreadDriverCreator.getInstance().getDriverThreadLocal();  //Multi-thread driver
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void openPage(String pageURL) {
        driver.get(pageURL);
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
    }

     public boolean isAlertPresent() {
        boolean foundAlert = false;
        WebDriverWait wait = new WebDriverWait(driver, ALERT_WAIT_TIME);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException eTO) {

        }
        return foundAlert;
    }
}
