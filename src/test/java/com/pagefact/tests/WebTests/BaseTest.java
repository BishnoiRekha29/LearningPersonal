package com.pagefact.tests.WebTests;

import com.DriverFactory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected ThreadLocal<RemoteWebDriver> driver ;

    @BeforeMethod
    @Parameters(value="browser")
    public void SetUp(String browser)
    {
        driver = DriverFactory.getDriver(browser);
    }
    public WebDriver getDriver()
    {
        return driver.get();
    }

//    @AfterMethod(alwaysRun = true)
//    public void teardown()
//    {
//        getDriver().quit();
//    }

}
