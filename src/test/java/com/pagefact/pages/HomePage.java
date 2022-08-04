package com.pagefact.pages;

import com.DriverFactory.Wrapper;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage  extends BasePage{
    private String PAGE_URL="https://www.lambdatest.com";
    @FindBy(how = How.XPATH, using = "//button[.='Start Free Testing']")
    private WebElement StartTestingButton;
    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    private WebElement emailTxt;

    public void enterEmail(String emailId)
    {
         wrapper.enter(emailTxt,emailId);
    }
    public HomePage(WebDriver driver)
    {
        super(driver);
    }
    public void loadPage()
    {
        wrapper.loadPage(PAGE_URL);
        wrapper.waitForPageLoadToComplete();
    }
    public void clickOnStartTestingButton()
    {
        wrapper.click(StartTestingButton);
    }
}