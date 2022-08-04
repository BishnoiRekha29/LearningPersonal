package com.pagefact.pages;

import com.DriverFactory.DriverFactory;
import com.DriverFactory.Wrapper;
import com.pagefact.workflows.Workflows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

import static org.openqa.selenium.support.PageFactory.initElements;

public class BasePage {
    protected Wrapper wrapper;
    protected WebDriver _driver;
    private HomePage homePage;
    private SignUpPage signUpPage;
    private Workflows workflow;
    public BasePage(WebDriver driver) {
        wrapper = new Wrapper(driver);
        _driver=driver;
    }

    public HomePage HomePage()
    {
        if (homePage==null)
            homePage=PageFactory.initElements(_driver, HomePage.class);
       return homePage;
    }
    public SignUpPage SignUpPage()
    {
        if (signUpPage==null)
        signUpPage=PageFactory.initElements(_driver, SignUpPage.class);
        return signUpPage;
     //   return  PageFactory.initElements(_driver, SignUpPage().getClass());
     //   return (signUpPage==null)?new SignUpPage(_driver):signUpPage;
    }

    public Workflows WorkFlows()
    {
        if (workflow==null)
            workflow=new Workflows(_driver);
        return workflow;
    }
}
