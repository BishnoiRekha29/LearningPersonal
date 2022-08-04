package com.pagefact.StepDefinitions;

import Cucumber.ScenarioContext;
import com.DriverFactory.DriverFactory;
import com.pagefact.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestContext {

        private WebDriver driver;
        private BasePage basePage;
        ScenarioContext scenarioContext;
        public TestContext(){
                driver= DriverFactory.getDriver("chrome").get();
                basePage=new BasePage(driver);
                scenarioContext = new ScenarioContext();
        }

        public WebDriver getDriver()
        {
            return driver;
        }

        public BasePage getBasePage()
        {
            return this.basePage;
        }

        public ScenarioContext getScenarioContext()
        {
            return this.scenarioContext;
        }

}
