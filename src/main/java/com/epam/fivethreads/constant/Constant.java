package com.epam.fivethreads.constant;

public class Constant {
	public static final int GLOBAL_WAIT_TIME = 60;
	public static final int ALERT_WAIT_TIME = 3;

	public static final String DRIVER_PROPERTIES_FILE_PATH="src/main/resources/driver.properties";

	public static final String IS_LOGGED_IN_URL = "https://accounts.google.com/signin/v2/sl/pwd?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin&cid=1&navigationDirection=forward";
	public static final String WEB_SITE_URL="https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";

	public static final String INBOX_URL = "https://mail.google.com/mail/#inbox";
	public static final String DRAFTS_URL = "https://mail.google.com/mail/#drafts";
	public static final String SENTED_URL ="https://mail.google.com/mail/#sent";

	public static final String USERS_XML_FILE_PATH= "src/main/resources/users.xml";
	public static final String LETTERS_XML_FILE_PATH= "src/main/resources/letters.xml";

	public static final String USERS_XLS_FILE_PATH= "src/main/resources/users.xls";
	public static final String LETTERS_XLS_FILE_PATH= "src/main/resources/letters.xls";

	public static final String USERS_CSV_FILE_PATH= "src/main/resources/users.csv";
	public static final String LETTERS_CSV_FILE_PATH= "src/main/resources/letters.csv";

	public static final String CSV_SPLITTER=";";
	private Constant() {
	}
}
