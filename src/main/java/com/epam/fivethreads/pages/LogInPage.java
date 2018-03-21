package com.epam.fivethreads.pages;

import static com.epam.fivethreads.constant.Constant.WEB_SITE_URL;

import com.epam.fivethreads.decorator.anotation.ElementName;
import com.epam.fivethreads.decorator.anotation.PageName;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;

import com.epam.fivethreads.decorator.elements.Button;
import com.epam.fivethreads.decorator.elements.TextField;
@PageName("[LOGIN page]")
public class LogInPage extends AbstractPage {

	private static final Logger LOG = Logger.getLogger(LogInPage.class);

	@ElementName("[LOGIN textfield]")
	@FindBy(xpath = "//input[@type='email'][@name='identifier']")
	private TextField logInField;

	@ElementName("[NEXT button]")
	@FindBy(id = "identifierNext")
	private Button logInNextButton;

	public void openPage() {
		openPage(WEB_SITE_URL);
	}

	public PasswordPage inputLogIn(String userLogin) {
		logInField.type(userLogin);
		logInNextButton.click();
		return new PasswordPage();

	}

}
