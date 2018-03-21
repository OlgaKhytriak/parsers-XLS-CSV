package com.epam.fivethreads.driver;

import com.epam.fivethreads.driverproperties.DriverData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static com.epam.fivethreads.constant.Constant.GLOBAL_WAIT_TIME;

public class SafeThreadDriverCreator {
   // private static Semaphore semaphore = new Semaphore(3);
    private DriverData driverData = new DriverData();
    private static SafeThreadDriverCreator instance = new SafeThreadDriverCreator();

    private ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            return getDriver();
        }
    };
    private WebDriver driver;

    private SafeThreadDriverCreator() {
    }

    public static SafeThreadDriverCreator getInstance() {
        return instance;
    }

    public WebDriver getDriverThreadLocal() {
        return driverThreadLocal.get();
    }

    public void removeDriver() {
        if (driverThreadLocal.get() != null) {
            try {
                driverThreadLocal.get().quit();
            } catch (Exception e) {
                System.out.println("Exception in driver remove");
            }
            driverThreadLocal.remove();
            //semaphore.release();
        }
    }

    private WebDriver createDriver() {
       /* try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        System.setProperty(driverData.getDriver(), driverData.getUrl());
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(GLOBAL_WAIT_TIME, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver getDriver() {
        if (null == driver) {
            driver = createDriver();
        }
        return driver;
    }
}

