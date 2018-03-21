package com.epam.fivethreads.pages;

import com.epam.fivethreads.decorator.anotation.ElementName;
import com.epam.fivethreads.decorator.anotation.PageName;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.fivethreads.decorator.elements.Button;
import com.epam.fivethreads.decorator.elements.InputTextField;
@PageName("[PASSWORD page]")
public class PasswordPage extends AbstractPage {
	private static final Logger LOG = Logger.getLogger(PasswordPage.class);

	@ElementName("[PASSWORD textfield]")
	@FindBy(xpath = "//div[@id='password']//input[@name='password']")
	private InputTextField passwordField;

	@ElementName("[NEXT button]")
	@FindBy(id = "passwordNext")
	private Button passwordNextButton;

	public HomePage inputPassword(String userPassword) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement passField=driver.findElement(By.xpath("//div[@id='password']//input[@name='password']"));
		js.executeScript("elem=arguments[0]; elem.setAttribute('type','text'); ", passField);
		passwordField.type(userPassword);
		passwordNextButton.click();
		return new HomePage();

	}

}
