package com.epam.fivethreads.driverproperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.epam.fivethreads.constant.Constant.DRIVER_PROPERTIES_FILE_PATH;

public class DriverData {
    private Properties prop = new Properties();

    public DriverData() {
        FileInputStream input = null;
		try {
			input = new FileInputStream(DRIVER_PROPERTIES_FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public String getDriver() {
        return prop.getProperty("web.driver");
    }

    public String getUrl() {
        return prop.getProperty("path.driver");
    }
    
    
}
