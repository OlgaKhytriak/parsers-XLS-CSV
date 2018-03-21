package com.epam.fivethreads.data.handlers;

import com.epam.fivethreads.constant.Constant;
import com.epam.fivethreads.data.model.User;
import com.epam.fivethreads.data.model.Users;
import com.epam.fivethreads.data.utils.parserJAXB.JAXBContextProcessor;
import com.epam.fivethreads.data.utils.parserJAXB.Object2Xml;
import com.epam.fivethreads.data.utils.parsersXLSandCSV.ParserCSV;
import com.epam.fivethreads.data.utils.parsersXLSandCSV.ParserXLS;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

import static com.epam.fivethreads.constant.Constant.CSV_SPLITTER;
import static com.epam.fivethreads.constant.Constant.USERS_CSV_FILE_PATH;
import static com.epam.fivethreads.constant.Constant.USERS_XLS_FILE_PATH;

public class UsersDataReader {
    private static final Logger LOG = Logger.getLogger(UsersDataReader.class);
    private final ParserCSV parserCSV ;
    private final ParserXLS parserXLS;

    public UsersDataReader(){
         parserCSV = new ParserCSV();
         parserXLS = new ParserXLS();
    }

    public Users getUsers(String filePath) {
        String fileType = filePath.substring(filePath.length() - 3);
        if (fileType.equals("xml")) {
            return readUsersFromXML(filePath);
        } else if (fileType.equals("xls")) {
            return readUsersFromXLS(filePath);
        } else if (fileType.equals("csv")) {
            return readUsersFromCSV(filePath);
        } else {
            return null;
        }
    }

    private Users readUsersFromCSV(String filePath) {
        List<User> parsedUsers = parserCSV.parseToList(filePath, User.class);
        Users users = new Users();
        users.setUsers(parsedUsers);
       // LOG.info("\n" + users.toString());
        return users;
    }

    private Users readUsersFromXLS(String filePath) {
        List<User> parsedUsers = parserXLS.parseToList(filePath, User.class);
        Users users = new Users();
        users.setUsers(parsedUsers);
        //LOG.info("\n" + users.toString());
        return users;
    }

    private Users readUsersFromXML(String filePath) {
        filePath = Constant.USERS_XML_FILE_PATH;
        JAXBContextProcessor jAXBContextProcessor = new JAXBContextProcessor(new Class[]{Users.class});
        Object2Xml xmlAdapter = new Object2Xml(jAXBContextProcessor);
        Users users = xmlAdapter.load(new File(filePath));
        LOG.info("\n" + users.toString());
        return users;
    }
}
