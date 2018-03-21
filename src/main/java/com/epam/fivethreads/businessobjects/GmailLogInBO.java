package com.epam.fivethreads.businessobjects;

import com.epam.fivethreads.data.model.User;
import org.apache.log4j.Logger;

import com.epam.fivethreads.pages.LogInPage;
import com.epam.fivethreads.pages.PasswordPage;
import com.epam.fivethreads.pages.HomePage;

public class GmailLogInBO {
    private static final Logger LOG = Logger.getLogger(GmailLogInBO.class);
    private LogInPage logInPage;
    private HomePage homePage;

    public GmailLogInBO() {
        logInPage = new LogInPage();
        homePage = new HomePage();
    }

    public void openLoginPage() {
        LOG.info(String.format(" ---- %s.openLoginPage() ----- ", this.getClass().getSimpleName()));
        logInPage.openPage();
    }

    public void login(User user) {
        LOG.info(String.format(" ---- %s.login() ----- ", this.getClass().getSimpleName()));
        PasswordPage passwordPage = logInPage.inputLogIn(user.getUserLogin());
        passwordPage.inputPassword(user.getUserPassword());
    }

    public boolean isUserLoggedIn() {
        LOG.info(String.format(" ---- %s.isUserLoggedIn() ----- ", this.getClass().getSimpleName()));
        return homePage.isOpened();
    }

}
