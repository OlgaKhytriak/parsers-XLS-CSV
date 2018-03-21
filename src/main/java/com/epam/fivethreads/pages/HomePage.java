package com.epam.fivethreads.pages;

import static com.epam.fivethreads.constant.Constant.INBOX_URL;
import static com.epam.fivethreads.constant.Constant.IS_LOGGED_IN_URL;

import com.epam.fivethreads.decorator.anotation.ElementName;
import com.epam.fivethreads.decorator.anotation.PageName;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.epam.fivethreads.decorator.elements.Button;
import com.epam.fivethreads.decorator.elements.TextField;

@PageName("[HOME page]")
public class HomePage extends AbstractPage {
    private static final Logger LOG = Logger.getLogger(HomePage.class);

    @ElementName("[COMPOSE button]")
    @FindBy(xpath = "//div[@role='navigation']/preceding-sibling::div//div[@role='button']")
    private Button composeButton;

    @ElementName("[SENT TO textfield]")
    @FindBy(xpath = "//form[@enctype='multipart/form-data']//textarea[@name='to']")
    private TextField sentToTextarea;

    @ElementName("[MESSAGE SUBJECT textfield]")
    @FindBy(xpath = "//form[@enctype='multipart/form-data']//input[@name='subjectbox']")
    private TextField subjectBox;

    @ElementName("[MESSAGE TEXT textfield]")
    @FindBy(xpath = "//form[@enctype='multipart/form-data']/following::table//div[@role='textbox']")
    @CacheLookup
    private TextField messageTexbox;

    public boolean isOpened() {
        String ss = driver.getCurrentUrl();
        return ss.equals(IS_LOGGED_IN_URL);
    }

    public void openPage() {
        driver.get(INBOX_URL);
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
    }

    public void craeteMessage(String messageSentTo, String messageSubject, String messageText) {
        openNewMessageForm();
        inputMessageSentTo(messageSentTo);
        inputMessageSubject(messageSubject);
        inputMessageText(messageText);
        messageTexbox.type(Keys.ESCAPE);
    }

    private void openNewMessageForm() {
        composeButton.click();
    }

    private void inputMessageSentTo(String messageSentTo) {
        sentToTextarea.type(messageSentTo);
        sentToTextarea.click();
        messageTexbox.click();
    }

    private void inputMessageSubject(String messageSubject) {
        subjectBox.click();
        subjectBox.type(messageSubject);
    }

    private void inputMessageText(String messageText) {
        messageTexbox.click();
        messageTexbox.type(messageText);
    }

}
