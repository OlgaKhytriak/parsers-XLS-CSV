package com.epam.fivethreads.pages;

import com.epam.fivethreads.decorator.anotation.ElementName;
import com.epam.fivethreads.decorator.anotation.PageName;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.epam.fivethreads.decorator.elements.Button;

import static com.epam.fivethreads.constant.Constant.*;

@PageName("[DRAFTS page]")
public class DraftsPage extends AbstractPage {
    private static final Logger LOG = Logger.getLogger(DraftsPage.class);

    @ElementName("[SEND button]")
    @FindBy(xpath = "//form[@enctype='multipart/form-data']/following::table/tbody/child::tr[2]/descendant::table//div[@role='button'][1]")
    private Button sentFormButton;


    public void openPage() {
        openPage(DRAFTS_URL);
    }

    private WebElement findMessageBySubject(String subject) {
        //isAlertPresent();
        WebElement element = driver.findElement(By.xpath("//table[@cellpadding='0']/tbody/tr/td[@class='xY a4W']/div/div/div/span[text()='" + subject + "']"));
        return element;
    }

    public boolean isMasageFound(String subject) {
        WebElement elementLinkDiv = findMessageBySubject(subject);
        return elementLinkDiv.isDisplayed();
    }

    public void sendMasageFound(String subject) {
        WebElement elementLinkDiv = findMessageBySubject(subject);
        Actions builder = new Actions(driver);
        builder.moveToElement(elementLinkDiv).click().perform();
        sentFormButton.click();
    }

}
