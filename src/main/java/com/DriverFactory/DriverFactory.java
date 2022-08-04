package com.DriverFactory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
//    protected RemoteWebDriver driver;
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
    public static CapabilityFactory capabilityFactory = new CapabilityFactory();


    public static void setDriver(String browser) {
        try {
//              System.setProperty("webdriver.chrome.driver", "C:\\Users\\rebishno\\Downloads\\chromedriver_win32\\chromedriver.exe");
//                Capabilities c=capabilityFactory.getCapabilities(browser);
//              driver.set(new ChromeDriver());
       // driver=new RemoteWebDriver(new URL(" http://192.168.43.106:4444/"), capabilityFactory.getCapabilities(browser));
            driver.set(new RemoteWebDriver(new URL(" http://localhost:4444/"), capabilityFactory.getCapabilities(browser)));
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public static ThreadLocal<RemoteWebDriver>  getDriver(String browser) {
        //Get driver from ThreadLocalMap
        if(driver.get()==null)
            setDriver(browser);
        return driver;
    }
}
