package com.epam.fivethreads;

import com.epam.fivethreads.businessobjects.DraftsBO;
import com.epam.fivethreads.businessobjects.GmailLogInBO;
import com.epam.fivethreads.data.handlers.CustomDataProvider;
import com.epam.fivethreads.data.model.Letter;
import com.epam.fivethreads.data.model.User;
import com.epam.fivethreads.driver.SafeThreadDriverCreator;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class DraftGmailTest {
    private static final Logger LOG = Logger.getLogger(DraftGmailTest.class);

    @Test(dataProvider = "getData", dataProviderClass = CustomDataProvider.class)
       public void draftSentLetterTest(User user, Letter letter) {
        LOG.info(user.toString());
        LOG.info(letter.toString());
        GmailLogInBO gmailLogInBO = new GmailLogInBO();
        gmailLogInBO.openLoginPage();
        gmailLogInBO.login(user);
        System.out.println(user.toString());
        assertTrue(gmailLogInBO.isUserLoggedIn());

        DraftsBO draftsBO = new DraftsBO();
        draftsBO.createDraft(letter);
        draftsBO.openDrafts();
        assertTrue(draftsBO.isMessageInDrafts(letter));

        draftsBO.sendMessageFromDrafts(letter);

        draftsBO.openSentMails();
        assertTrue(draftsBO.isMessageInSent(letter));

    }

    @AfterMethod
    public void driverQuit() {
        SafeThreadDriverCreator.getInstance().removeDriver();
    }

}
