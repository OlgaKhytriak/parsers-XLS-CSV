package com.epam.fivethreads.data.handlers;

import com.epam.fivethreads.data.model.Letters;
import com.epam.fivethreads.data.model.Users;
import com.epam.fivethreads.data.utils.Data2ArrayConverter;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import static com.epam.fivethreads.constant.Constant.LETTERS_CSV_FILE_PATH;
import static com.epam.fivethreads.constant.Constant.USERS_XLS_FILE_PATH;

public class CustomDataProvider {
    private static final Logger LOG = Logger.getLogger(CustomDataProvider.class);
    @DataProvider(parallel = true)
    public static Object[][] getData() {
        Users users = new UsersDataReader().getUsers(USERS_XLS_FILE_PATH);
        Letters letters = new LattersDataCreator().getLetters(LETTERS_CSV_FILE_PATH);
        LOG.info(users.toString());
        LOG.info(letters.toString());
        return Data2ArrayConverter.getData(users, letters);
    }
}
